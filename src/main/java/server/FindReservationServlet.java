/**
 * MVC interface for searching reservation information using reservation number and email
 * @author Arie Geiger, Jose Cortes, Kyle Cushing, Erik Zeladita
 * @version Nov 21, 2023
 */
package server;
import java.io.*;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.http.HttpServletResponse;
import backend.*;

public class FindReservationServlet extends HttpServlet {
	private ReservationManager resman = null;
	private CustomerManager cman = null;

	public void init() throws ServletException {
		Manager man = Manager.getManager();
		resman = man.getReservationManager();
		cman = man.getCustomerManager();
	}
	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
		response.setContentType("text/html");
		RequestDispatcher rd = getServletContext().getRequestDispatcher("/jsp/find-reservation.jsp");
		try {
			rd.forward(request, response);
		} catch (ServletException ex) {
			ex.printStackTrace();
		}
	}

	/**
	 * Passes information inputed to respective manager classes to retrieve a Reservation instance
	 * @param request    Servlet request structure
	 * @param response   HTTP-specific response
	 */
	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String email = request.getParameter("email");
		int resvNum = Integer.parseInt(request.getParameter("reservationNumber"));
		Reservation res = resman.getReservation(resvNum);
		boolean valid = false;
		if (res != null) {
			Customer customer = res.getCustomer();
			String resvEmail = customer.getEmail();
			if (resvEmail.equals(email))
				valid = true;
		}

		if (valid) {
			HttpSession session = request.getSession();
			session.setAttribute("viewReservationId", resvNum);
			response.sendRedirect("/display");
		}
		else {
			// if input is invalid
			request.setAttribute("error", "invalidInput");
			doGet(request, response);
		}
	}
}
