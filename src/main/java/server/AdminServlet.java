package server;

import java.io.*;
import java.util.HashMap;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import javax.servlet.annotation.ServletSecurity.TransportGuarantee;
import javax.servlet.http.HttpServletResponse;
import backend.*;

@WebServlet(name = "AdminServlet", urlPatterns = {"/greeting"})
@ServletSecurity(@HttpConstraint(rolesAllowed = {"TutorialUser"}))
public class AdminServlet extends HttpServlet {
	private CustomerManager cman = null;
	private ReservationManager resman = null;
	private RoomManager rooman = null;
	private BillingManager bman = null;
	private RoomTypeManager rtypeman = null;

	public void init() throws ServletException {
		Manager man = Manager.getManager();
		resman = man.getReservationManager();
		rooman = man.getRoomManager();
		bman = man.getBillingManager();
		rtypeman = man.getRoomTypeManager();
	}

	/**
	 * Get hash map of arguments from GET query
	 * @param source GET query string
	 * @return Hash map of arguments
	 */
	private static HashMap<String, String> convertToQueryStringToHashMap(String source) {
		HashMap<String, String> data = new HashMap<String, String>();

		if (source == null)
			return data;

		final String[] arrParameters = source.split("&");
		for (final String tempParameterString : arrParameters) {

			final String[] arrTempParameter = tempParameterString
					.split("=");

			if (arrTempParameter.length >= 2) {
				final String parameterKey = arrTempParameter[0];
				final String parameterValue = arrTempParameter[1];
				data.put(parameterKey, parameterValue);
			} else {
				final String parameterKey = arrTempParameter[0];
				data.put(parameterKey, "");
			}
		}

		return data;
	}

	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
		response.setContentType("text/html");
		request.setAttribute("roomTypes", rtypeman.getRoomTypes());
		PrintWriter out = response.getWriter();
		out.println("<h1>" + "mymsg" + "</h1>");
		out.println("<p>" + "Ahoy ahoy!" + "</p>");
	}
}
