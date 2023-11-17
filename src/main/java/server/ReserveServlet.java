package server;
import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.http.HttpServletResponse;
import java.util.*;
import java.sql.Connection;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAccessor;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;

import backend.*;

public class ReserveServlet extends HttpServlet {
	private CustomerManager cman = null;
	private ReservationManager resman = null;
	private RoomManager rooman = null;
	private BillingManager bman = null;
	private RoomTypeManager rtypeman = null;

	public void init() throws ServletException {
		Manager man = Manager.getManager();
		cman = man.getCustomerManager();
		resman = man.getReservationManager();
		rooman = man.getRoomManager();
		rtypeman = man.getRoomTypeManager();
		bman = man.getBillingManager();
	}

	/**
	 * Foward attribue value from GET request to JSP
	 * @param request    Servlet request structure
	 * @param hm         Hash map of all GET arguments
	 * @param attribute  Name of attribute you want to forward to setAttribute
	 * @return true if attribute was forwarded successfully, false ortherwise
	 */
	private static boolean forwardAttribute(HttpServletRequest request, HashMap<String,String> hm, String attribute) {
		if (hm.containsKey(attribute)) {
			request.setAttribute(attribute, hm.get(attribute));
			return true;
		}
		return false;
	}

	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
		//HttpSession session = request.getSession();
		//String roomType = request.getParameter("roomType");
		response.setContentType("text/html");

		HashMap<String,String> hm = utils.convertToQueryStringToHashMap(request.getQueryString());

		forwardAttribute(request, hm, "room");
		forwardAttribute(request, hm, "numRooms");
		forwardAttribute(request, hm, "numGuests");
		forwardAttribute(request, hm, "checkin");
		forwardAttribute(request, hm, "checkout");

		RequestDispatcher rd = getServletContext().getRequestDispatcher("/jsp/reserve.jsp");
		try {
			rd.forward(request, response);
		} catch (ServletException ex) {
			ex.printStackTrace();
		}
	}

	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
		// customer = cman.CreateCustomer(firstName, lastName, phoneNumber, email);
		int roomType;
		int numRooms;
		int numGuests;
		Date checkin;
		Date checkout;
		String firstName;
		String lastName;
		String phoneNumber;
		String email;

		String cardNumber;
		String nameOnCard;
		String cardType;
		String cardExpiration;
		String cvcNumber;
		String postalCode;

		try {
			roomType = Integer.parseInt(request.getParameter("roomType"));
			numRooms = Integer.parseInt(request.getParameter("numRooms"));
			numGuests = Integer.parseInt(request.getParameter("numGuests"));
			checkin = Date.from(LocalDate.parse(request.getParameter("checkin")).atStartOfDay(ZoneId.systemDefault()).toInstant());
			checkout = Date.from(LocalDate.parse(request.getParameter("checkout")).atStartOfDay(ZoneId.systemDefault()).toInstant());

			firstName = request.getParameter("firstName");
			if (firstName == null) throw new IllegalArgumentException("firstName");
			lastName = request.getParameter("lastName");
			if (lastName == null) throw new IllegalArgumentException("lastName");
			phoneNumber = request.getParameter("phoneNumber");
			if (phoneNumber == null) throw new IllegalArgumentException("phoneNumber");
			email = request.getParameter("email");
			System.out.println("Email: " + email);
			if (email == null || email.isEmpty()) throw new IllegalArgumentException("email");

			cardNumber = request.getParameter("cardNumber");
			if (cardNumber == null) throw new IllegalArgumentException("cardNumber");
			nameOnCard = request.getParameter("nameOnCard");
			if (nameOnCard == null) throw new IllegalArgumentException("nameOnCard");
			cardType = request.getParameter("cardType");
			if (cardType == null) throw new IllegalArgumentException("cardType");
			cardExpiration = request.getParameter("cardExpiration");
			if (cardExpiration == null) throw new IllegalArgumentException("cardExpiration");
			cvcNumber = request.getParameter("cvcNumber");
			if (cvcNumber == null) throw new IllegalArgumentException("cvcNumber");
			postalCode = request.getParameter("postalCode");
			if (postalCode == null) throw new IllegalArgumentException("postalCode");
		} catch (Exception ex) {
			ex.printStackTrace();
			response.sendError(HttpServletResponse.SC_BAD_GATEWAY);
			return;
		}
		System.out.println("=> Create reservation\nroomType "+roomType+" numRooms: "+numRooms+" numGuests: "+numGuests+" Checkin: "+checkin+" Checkout: "+checkout);
		Customer customer = cman.findOrMake(firstName, lastName, phoneNumber, email, "");
		Billing billing = bman.createBilling(cardNumber, cardExpiration, cvcNumber, nameOnCard, cardType, postalCode);
		Reservation res = resman.createReservation(customer, billing, rtypeman.getRoomType(roomType), numRooms, numGuests, checkin, checkout);
		HttpSession session = request.getSession();
		session.setAttribute("confirmationId", res.getId());
		response.sendRedirect("/complete");
	}
}
