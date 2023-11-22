package server;
import java.io.*;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.http.HttpServletResponse;
import java.util.*;
import java.sql.Connection;
import java.time.format.DateTimeFormatter;
import java.time.LocalDate;

import backend.*;

/**
 * MVC interface for main page, displays available room types to book that meet specified criteria.
 * @author Arie Geiger, Jose Cortes, Kyle Cushing, Erik Zeladita
 * @version Nov 21, 2023
 */
public class BookingServlet extends HttpServlet {
	private CustomerManager cman = null;
	private ReservationManager resman = null;
	private RoomManager rooman = null;
	private BillingManager bman = null;
	private RoomTypeManager rtypeman = null;
	/**
	 * Initialize manager objects used by BookingServlet
	 */
	public void init() throws ServletException {
		//getServletContext()
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

	/**
	 * Displays a list of rooms and sets default values for the filters
	 * @param request    User request structure
	 * @param response   HTTP response
	 */
	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
		response.setContentType("text/html");
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		request.setAttribute("roomTypes", rtypeman.getRoomTypes());

		HashMap<String,String> hm = utils.convertToQueryStringToHashMap(request.getQueryString());
		System.out.println(hm);

		if (!forwardAttribute(request, hm, "checkin"))
			request.setAttribute("checkin", LocalDate.now().format(formatter)); // today

		if (!forwardAttribute(request, hm, "checkout"))
			request.setAttribute("checkout", LocalDate.now().plusDays(1).format(formatter)); // tomorrow

		if (!forwardAttribute(request, hm, "numGuests"))
			request.setAttribute("numGuests", 1);

		if (!forwardAttribute(request, hm, "numRooms"))
			request.setAttribute("numRooms", 1);

		RequestDispatcher rd = getServletContext().getRequestDispatcher("/jsp/booking.jsp");
		try {
			rd.forward(request, response);
		} catch (ServletException ex) {
			ex.printStackTrace();
		}
	}
}
