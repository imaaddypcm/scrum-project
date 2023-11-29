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
 * MVC interface for the manager feature allowing the creation of a room type
 * @author Arie Geiger, Jose Cortes, Kyle Cushing, Erik Zeladita
 * @version Nov 21, 2023
 */
@WebServlet(name = "AddRoomServlet", urlPatterns = "/admin/addRoom")
public class AddRoomServlet extends HttpServlet {
	private CustomerManager cman = null;
	private ReservationManager resman = null;
	private RoomManager rooman = null;
	private BillingManager bman = null;
	private RoomTypeManager rtypeman = null;
	/**
	 * Initialize manager objects used by AddRoomTypeServlet
	 */
	public void init() throws ServletException {
		Manager man = Manager.getManager();
		resman = man.getReservationManager();
		rooman = man.getRoomManager();
		bman = man.getBillingManager();
		rtypeman = man.getRoomTypeManager();
	}

	/**
	 * Passes prospective room type information inputed to the instance of RoomTypeManager to add a new room type
	 * @param request    Servlet request structure
	 * @param response   HTTP-specific response
	 */
	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
		Manager man = Manager.getManager();
		RoomTypeManager rtypeman = man.getRoomTypeManager();
		// customer = cman.CreateCustomer(firstName, lastName, phoneNumber, email);
		int roomNumber;
 		RoomType roomType;

		try {
			if (request.getParameter("roomNumber") == null) throw new IllegalArgumentException("roomNumber");
			roomNumber = Integer.parseInt(request.getParameter("roomNumber"));
			if (request.getParameter("roomType") == null) throw new IllegalArgumentException("roomType");
			roomType = rtypeman.getRoomType(Integer.parseInt(request.getParameter("roomType")));
		} catch (Exception ex) {
			ex.printStackTrace();
			response.sendError(HttpServletResponse.SC_BAD_GATEWAY);
			return;
		}
		Room room = rooman.createRoom(roomNumber, roomType);
		HttpSession session = request.getSession();
		response.sendRedirect("/admin");
	}
}
