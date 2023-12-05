package server.manager;

import java.io.*;
import java.util.HashMap;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import jakarta.servlet.annotation.ServletSecurity.TransportGuarantee;
import jakarta.servlet.http.HttpServletResponse;
import backend.*;

import java.util.Date;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

// Arie Nov 30 2023: TODO: Daily occupancy rate and Revenue tracking - Weekly or monthly

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

	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
		doGet(request, response);
	}

	/**
	 * Displays administrator page
	 * @param request    User request structure
	 * @param response   HTTP response
	 */
	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
		response.setContentType("text/html");
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		request.setAttribute("reservations", resman.getReservations());
		request.setAttribute("roomTypes", rtypeman.getRoomTypes());
		request.setAttribute("rooms", rooman.getRooms());
		request.setAttribute("customers", cman.getCustomers());
		if (request.getParameter("startDate") != null) {
			request.setAttribute("startDate", request.getParameter("startDate"));
		} else {
			request.setAttribute("startDate", LocalDate.now().minusMonths(1).format(formatter));
		}
		if (request.getParameter("endDate") != null) {
			request.setAttribute("endDate", request.getParameter("endDate"));
		} else {
			request.setAttribute("endDate", LocalDate.now().plusMonths(1).format(formatter));
		}

		Date start = Date.from(LocalDate.parse(request.getAttribute("startDate").toString()).atStartOfDay(ZoneId.systemDefault()).toInstant());
		Date end = Date.from(LocalDate.parse(request.getAttribute("endDate").toString()).atStartOfDay(ZoneId.systemDefault()).toInstant());
		request.setAttribute("revenue", bman.getRevenue(start, end));
		request.setAttribute("occupancy", resman.getNumOfActiveReservations(null, start, end));

		RequestDispatcher rd = getServletContext().getRequestDispatcher("/jsp/admin.jsp");
		try {
			rd.forward(request, response);
		} catch (ServletException ex) {
			ex.printStackTrace();
		}
	}
}
