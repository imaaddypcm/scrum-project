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
	private PaymentInfoManager pman = null;
	private RoomTypeManager rtypeman = null;

	public void init() throws ServletException {
		//getServletContext()
		Connection conn = DatabaseConnection.getConnection();
		cman = new CustomerManager(conn);
		resman = new ReservationManager(conn);
		rooman = new RoomManager(conn);
		rtypeman = new RoomTypeManager(conn);
		pman = new PaymentInfoManager(conn);
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
	private void sendBooking(HttpServletRequest request, HttpServletResponse response) throws IOException {
		//HttpSession session = request.getSession();
		//String roomType = request.getParameter("roomType");
		response.setContentType("text/html");
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		request.setAttribute("roomTypes", rtypeman.getRoomTypes());

		HashMap<String,String> hm = convertToQueryStringToHashMap(request.getQueryString());
		System.out.println(hm);

		if (hm.containsKey("checkin"))
			request.setAttribute("checkin", hm.get("checkin"));
		else
			request.setAttribute("checkin", LocalDate.now().format(formatter)); // today

		if (hm.containsKey("checkout"))
			request.setAttribute("checkout", hm.get("checkout")); // tomorrow
		else
			request.setAttribute("checkout", LocalDate.now().plusDays(1).format(formatter)); // tomorrow

		if (hm.containsKey("numGuests"))
			request.setAttribute("numGuests", hm.get("numGuests"));
		else
			request.setAttribute("numGuests", 1);

		if (hm.containsKey("numRooms"))
			request.setAttribute("numRooms", hm.get("numRooms"));
		else
			request.setAttribute("numRooms", 1);

		RequestDispatcher rd = getServletContext().getRequestDispatcher("/booking.jsp");
		try {
			rd.forward(request, response);
		} catch (ServletException ex) {
			ex.printStackTrace();
		}
	}

	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
		sendBooking(request, response);
	}
	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
		sendBooking(request, response);
		/*String roomType = request.getParameter("roomType");
        String firstName = request.getParameter("firstName");
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        out.println("<h1>" + roomType + "</h1>");
        out.println("<p>" + "Ahoy ahoy!" + "</p>");*/
	}
}
