package server;
import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.http.HttpServletResponse;
import java.util.*;
import java.sql.Connection;
import java.time.format.DateTimeFormatter;
import java.time.LocalDate;

import backend.*;

public class ReserveServlet extends HttpServlet {
	private CustomerManager cman = null;
	private ReservationManager resman = null;
	private RoomManager rooman = null;
	private PaymentInfoManager pman = null;
	private RoomTypeManager rtypeman = null;

	public void init() throws ServletException {
		cman = Manager.getCustomerManager();
		resman = Manager.getReservationManager();
		rooman = Manager.getRoomManager();
		rtypeman = Manager.getRoomTypeManager();
		pman = Manager.getPaymentInfoManager();
	}

	private static HashMap<String, String> convertToQueryStringToHashMap(String source) {
		HashMap<String, String> data = new HashMap<String, String>();

		if (source == null)
			return data;

		final String[] arrParameters = source.split("&");
		for (final String tempParameterString : arrParameters) {

			final String[] arrTempParameter = tempParameterString
					.split("=");

			if (arrTempParameter.length >= 2) {
				final String parameterKey = arrTempParameter[0];
				final String parameterValue = arrTempParameter[1];
				data.put(parameterKey, parameterValue);
			} else {
				final String parameterKey = arrTempParameter[0];
				data.put(parameterKey, "");
			}
		}

		return data;
	}

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

		HashMap<String,String> hm = convertToQueryStringToHashMap(request.getQueryString());

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
		String roomType = request.getParameter("roomType");
		String numRooms = request.getParameter("numRooms");
		String numGuests = request.getParameter("numGuests");
		String checkin = request.getParameter("checkin");
		String checkout = request.getParameter("checkout");
		//resman.CreateReservation(null, request.getParameter("roomType"), request.getParameter("numRooms"));
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        out.println("<h1>" + roomType + "</h1>");
		out.println("<h1>" + numRooms + "</h1>");
		out.println("<h1>" + numGuests + "</h1>");
		out.println("<h1>" + checkin + "</h1>");
		out.println("<h1>" + checkout + "</h1>");
		out.println("<p>" + "Ahoy ahoy!" + "</p>");
	}
}
