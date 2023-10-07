import java.util.Date;
import java.sql.*;
import java.util.ArrayList;

public class ReservationManager {
	private ArrayList<Reservation> reservations;
	private static String url = "jdbc:sqlite:hotel.sqlite";
	private Connection conn = null;

	public ReservationManager() {
		reservations = new ArrayList<>();
		try {
			conn = DriverManager.getConnection(url);
			Statement stmt = conn.createStatement();
			stmt.execute("CREATE TABLE IF NOT EXISTS reservations (\n"
			+ " id INTEGER PRIMARY KEY AUTOINCREMENT,\n"
			+ "	start DATETIME,\n"
			+ "	end DATETIME\n"
			+ ");");
		} catch (SQLException ex) {
			// handle any errors
			System.out.println("SQLException: " + ex.getMessage());
			System.out.println("SQLState: " + ex.getSQLState());
			System.out.println("VendorError: " + ex.getErrorCode());
		}
	}

	public Reservation CreateReservation(Customer customer, Room roomsReserved[], int numberOfGuests, Date startTime, Date endTime) {
		Reservation reservation = null;
		java.sql.Date startDate = new java.sql.Date(startTime.getTime());
		java.sql.Date endDate = new java.sql.Date(endTime.getTime());
		try {
			PreparedStatement pstmt = conn.prepareStatement("INSERT INTO reservations (start, end)\n"
			+ "VALUES (?, ?) RETURNING *;");
			pstmt.setDate(1, startDate);
			pstmt.setDate(2, endDate);
			ResultSet rs = pstmt.executeQuery();

			int id = rs.getInt("id");
			Date start = new java.util.Date(rs.getDate("start").getTime());
			Date end = new java.util.Date(rs.getDate("end").getTime());
			rs.close();

			System.out.println("Id: "+id+" Start: "+start+" End: "+end);
			reservation = new Reservation(id, start, end);
			reservations.add(reservation);
			pstmt.close();
		} catch (SQLException ex) {
			System.out.println("SQLException: " + ex.getMessage());
			System.out.println("SQLState: " + ex.getSQLState());
			System.out.println("VendorError: " + ex.getErrorCode());
		}
		return reservation;
	}

	public boolean CancelReservation(int reservationId) {
        //DELETE FROM reservation WHERE id = 1;
		return false;
	}
}
