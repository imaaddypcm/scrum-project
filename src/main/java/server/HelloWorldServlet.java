/**
 * Reference .
 * @author Arie Geiger, Jose Cortes, Kyle Cushing, Erik Zeladita
 * @version Nov 21, 2023
 */

package server;
import java.io.*;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.http.HttpServletResponse;
import backend.Customer;

public class HelloWorldServlet extends HttpServlet {
	private String mymsg;
	public void init() throws ServletException {
	   mymsg = "Http Servlet Demo";
	}
	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
		response.setContentType("text/html");
		RequestDispatcher rd = getServletContext().getRequestDispatcher("/jsp/reserve.jsp");
		try {
			rd.forward(request, response);
		} catch (ServletException ex) {
			ex.printStackTrace();
		}
	}
}
