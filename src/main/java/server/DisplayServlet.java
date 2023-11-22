/**
 * MVC interface for viewing and modifying reservation information after inputting a valid reservation id and email in FindReservationServlet
 * @author Arie Geiger, Jose Cortes, Kyle Cushing, Erik Zeladita
 * @version Nov 21, 2023
 */
package server;
import java.io.*;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.http.HttpServletResponse;
import backend.*;

public class DisplayServlet extends HttpServlet {
	private ReservationManager resman = null;
    public void init() throws ServletException {
		Manager man = Manager.getManager();
		resman = man.getReservationManager();
    }

	@Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html");
		HttpSession session = request.getSession();
		if (session.getAttribute("viewReservationId") != null) {
			int resvNum = Integer.parseInt(session.getAttribute("viewReservationId").toString());
			Reservation reservation = resman.getReservation(resvNum);
			request.setAttribute("reservation", reservation);
			session.removeAttribute("viewReservationId");
		}

		RequestDispatcher rd = getServletContext().getRequestDispatcher("/jsp/display.jsp");
		try {
			rd.forward(request, response);
		} catch (ServletException ex) {
			ex.printStackTrace();
		}
    }
}
