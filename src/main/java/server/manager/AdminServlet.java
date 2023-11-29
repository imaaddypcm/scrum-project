package server.manager;

import java.io.*;
import java.util.HashMap;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import jakarta.servlet.annotation.ServletSecurity.TransportGuarantee;
import jakarta.servlet.http.HttpServletResponse;
import backend.*;

/**
 * MVC interface for the manager interface homepage
 * @author Arie Geiger, Jose Cortes, Kyle Cushing, Erik Zeladita
 * @version Nov 21, 2023
 */
public class AdminServlet extends HttpServlet {
	private CustomerManager cman = null;
	private ReservationManager resman = null;
	private RoomManager rooman = null;
	private BillingManager bman = null;
	private RoomTypeManager rtypeman = null;
	/**
	 * Initialize manager objects used by AdminServlet
	 */
	public void init() throws ServletException {
		Manager man = Manager.getManager();
		resman = man.getReservationManager();
		rooman = man.getRoomManager();
		bman = man.getBillingManager();
		rtypeman = man.getRoomTypeManager();
		cman = man.getCustomerManager();
	}

	/**
	 * Displays administrator page
	 * @param request    User request structure
	 * @param response   HTTP response
	 */
	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
		response.setContentType("text/html");
		request.setAttribute("reservations", resman.getReservations());
		request.setAttribute("roomTypes", rtypeman.getRoomTypes());
		request.setAttribute("rooms", rooman.getRooms());
		request.setAttribute("customers", cman.getCustomers());
		RequestDispatcher rd = getServletContext().getRequestDispatcher("/jsp/admin.jsp");
		try {
			rd.forward(request, response);
		} catch (ServletException ex) {
			ex.printStackTrace();
		}
	}
}
