package server;
import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
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

	private void sendBooking(HttpServletRequest request, HttpServletResponse response) throws IOException {
		//HttpSession session = request.getSession();
		String roomType = request.getParameter("roomType");
		response.setContentType("text/html");
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		request.setAttribute("today", LocalDate.now().format(formatter));
		request.setAttribute("tomorrow", LocalDate.now().plusDays(1).format(formatter));
		request.setAttribute("roomTypes", rtypeman.getRoomTypes());
		RequestDispatcher rd = getServletContext().getRequestDispatcher("/booking.jsp");
		try {
			rd.forward(request, response);
		} catch (ServletException e) {
			e.printStackTrace();
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
