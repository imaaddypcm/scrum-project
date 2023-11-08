package server;

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;

public class HelpServlet extends HttpServlet {
	private String mymsg;
	public void init() throws ServletException {
	   mymsg = "Http Servlet Demo";
	}
	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
		response.setContentType("text/html");
		RequestDispatcher rd = getServletContext().getRequestDispatcher("/jsp/help.jsp");
		try {
			rd.forward(request, response);
		} catch (ServletException ex) {
			ex.printStackTrace();
		}
	}
}
