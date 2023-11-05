package server;
import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.http.HttpServletResponse;
import backend.Customer;

public class DisplayServlet extends HttpServlet {
	private String mymsg;
    public void init() throws ServletException {
       mymsg = "Http Servlet Demo";
    }

	@Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html");
		HttpSession session = request.getSession(false);
		request.setAttribute("viewReservationId", session.getAttribute("viewReservationId"));
		session.removeAttribute("viewReservationId");

		RequestDispatcher rd = getServletContext().getRequestDispatcher("/jsp/display.jsp");
		try {
			rd.forward(request, response);
		} catch (ServletException ex) {
			ex.printStackTrace();
		}
    }
}
