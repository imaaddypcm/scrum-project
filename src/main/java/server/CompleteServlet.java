package server;

import java.io.*;
import jakarta.servlet.*;
import jakarta.servlet.http.*;

public class CompleteServlet extends HttpServlet {
	private String mymsg;
	public void init() throws ServletException {
	   mymsg = "Http Servlet Demo";
	}
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
