package server;
import java.io.*;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import jakarta.servlet.http.HttpServletResponse;
import backend.*;

/**
 * MVC interface for canceling reservation specified by DisplayServlet
 * @author Arie Geiger, Jose Cortes, Kyle Cushing, Erik Zeladita
 * @version Nov 28, 2023
 */
@WebServlet(name = "RemoveReservationServlet", urlPatterns = "/cancelReservation")
public class RemoveReservationServlet extends HttpServlet {
	private ReservationManager resman = null;
	/**
	 * Initialize manager objects used by DisplayServlet
	 * @throws ServletException if there is an issue during servlet initialization
	 */
    public void init() throws ServletException {
		Manager man = Manager.getManager();
		resman = man.getReservationManager();
    }

	/**
	 * Handles GET requests for displaying reservation details before cancellation.
	 * Retrives the reservation based on the reservation ID stored in the user session,
	 * sets it as a request attribute, and forwards the request to the cancelReservation.jsp page
	 *
	 * @param request    HTTP request
	 * @param response	HTTP responce
	 * @throws IOException      If an I/O error occurs
	 */
	@Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
		HttpSession session = request.getSession();
		if (session.getAttribute("viewReservationId") != null) {
			int resvNum = Integer.parseInt(session.getAttribute("viewReservationId").toString());
			Reservation reservation = resman.getReservation(resvNum);
			request.setAttribute("reservation", reservation);
		}
		RequestDispatcher rd = getServletContext().getRequestDispatcher("/jsp/cancelReservation.jsp");
		try {
			rd.forward(request, response);
		} catch (ServletException ex) {
			ex.printStackTrace();
		}
	}

	/**
	 * Process reservation deletion request
	 * @param request    User request structure
	 * @param response   HTTP response
	 */
	@Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html");
		HttpSession session = request.getSession();
		if (session.getAttribute("viewReservationId") != null) {
			int resvNum = Integer.parseInt(session.getAttribute("viewReservationId").toString());
			if (resman.cancelReservation(resvNum)) {
				session.removeAttribute("viewReservationId");
				response.sendRedirect("/?cancelComplete=1");
				return;
			}
		}

		response.sendError(HttpServletResponse.SC_BAD_GATEWAY);
    }
}
