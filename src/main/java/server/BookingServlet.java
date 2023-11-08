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

public class BookingServlet extends HttpServlet {
	private CustomerManager cman = null;
	private ReservationManager resman = null;
	private RoomManager rooman = null;
	private BillingManager bman = null;
	private RoomTypeManager rtypeman = null;

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
	 * Get hash map of arguments from GET query
	 * @param source GET query string
	 * @return Hash map of arguments
	 */
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
		response.setContentType("text/html");
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		request.setAttribute("roomTypes", rtypeman.getRoomTypes());

		HashMap<String,String> hm = convertToQueryStringToHashMap(request.getQueryString());
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
