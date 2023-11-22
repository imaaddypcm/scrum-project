/**
 * MVC interface, displays confirmation message and reservation id.
 * @author Arie Geiger, Jose Cortes, Kyle Cushing, Erik Zeladita
 * @version Nov 21, 2023
 */
package server;

import java.io.*;
import jakarta.servlet.*;
import jakarta.servlet.http.*;

public class CompleteServlet extends HttpServlet {

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
