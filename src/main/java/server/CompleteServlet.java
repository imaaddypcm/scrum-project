package server;

import java.io.*;
import jakarta.servlet.*;
import jakarta.servlet.http.*;

/**
 * MVC interface, displays confirmation message and reservation id.
 * @author Arie Geiger, Jose Cortes, Kyle Cushing, Erik Zeladita
 * @version Nov 21, 2023
 */
public class CompleteServlet extends HttpServlet {
	/**
	 * Displays confirmation number for reservation.
	 * @param request    User request structure
	 * @param response   HTTP response
	 */
	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
		response.setContentType("text/html");
		RequestDispatcher rd = getServletContext().getRequestDispatcher("/jsp/complete.jsp");
		try {
			rd.forward(request, response);
		} catch (ServletException ex) {
			ex.printStackTrace();
		}
	}
}
