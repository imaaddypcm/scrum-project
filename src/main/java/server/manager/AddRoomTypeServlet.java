package server.manager;

import java.io.*;
import java.util.HashMap;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import javax.servlet.annotation.ServletSecurity.TransportGuarantee;
import javax.servlet.http.HttpServletResponse;
import backend.*;

public class AddRoomTypeServlet extends HttpServlet {
	private CustomerManager cman = null;
	private ReservationManager resman = null;
	private RoomManager rooman = null;
	private BillingManager bman = null;
	private RoomTypeManager rtypeman = null;

	public void init() throws ServletException {
		Manager man = Manager.getManager();
		resman = man.getReservationManager();
		rooman = man.getRoomManager();
		bman = man.getBillingManager();
		rtypeman = man.getRoomTypeManager();
	}

	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
		// customer = cman.CreateCustomer(firstName, lastName, phoneNumber, email);
		String name;
		String description;
		String rules;
		int numberOfBeds;
 		int price;

		try {
			name = request.getParameter("name");
			if (name == null) throw new IllegalArgumentException("name");
			description = request.getParameter("description");
			if (description == null) throw new IllegalArgumentException("description");
			rules = request.getParameter("rules");
			if (rules == null) throw new IllegalArgumentException("rules");

			if (request.getParameter("numberOfBeds") == null) throw new IllegalArgumentException("numberOfBeds");
			numberOfBeds = Integer.parseInt(request.getParameter("numberOfBeds"));
			if (request.getParameter("price") == null) throw new IllegalArgumentException("price");
			price = Integer.parseInt(request.getParameter("price"));
		} catch (Exception ex) {
			ex.printStackTrace();
			response.sendError(HttpServletResponse.SC_BAD_GATEWAY);
			return;
		}
		RoomType roomType = rtypeman.addRoomType(name, description, rules, numberOfBeds, price);
		HttpSession session = request.getSession();
		response.sendRedirect("/admin");
	}
}