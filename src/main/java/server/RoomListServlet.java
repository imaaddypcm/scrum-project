package server;
import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.http.HttpServletResponse;
import backend.Room;
import backend.DatabaseConnection;
import java.util.ArrayList;
import java.sql.Connection;

public class RoomListServlet extends HttpServlet {
	public void init() throws ServletException {
		//getServletContext()
		Connection conn = DatabaseConnection.getConnection();
	}
	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
		ArrayList<Room> rooms = new ArrayList<>();
		rooms.add(new Room(100, "test1"));
		rooms.add(new Room(101, "test2"));
		rooms.add(new Room(102, "test3"));
		response.setContentType("text/html");
		request.setAttribute("rooms", rooms);
		RequestDispatcher rd = getServletContext().getRequestDispatcher("/room-list.jsp");
		try {
			rd.forward(request, response);
		} catch (ServletException e) {
			e.printStackTrace();
		}
	}
}
