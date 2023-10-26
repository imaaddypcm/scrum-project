package server;
import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.sql.Connection;

import backend.*;

public class RoomListServlet extends HttpServlet {
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
	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
		//TODO Manage state for each individual user
		HttpSession session = request.getSession(true);

		response.setContentType("text/html");
		request.setAttribute("roomTypes", rtypeman.getRoomTypes());
		RequestDispatcher rd = getServletContext().getRequestDispatcher("/reservation-form.jsp");
		try {
			rd.forward(request, response);
		} catch (ServletException e) {
			e.printStackTrace();
		}
	}
	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String roomType = request.getParameter("roomType");
        String firstName = request.getParameter("firstName");
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        out.println("<h1>" + roomType + "</h1>");
        out.println("<p>" + "Ahoy ahoy!" + "</p>");
	}
}
