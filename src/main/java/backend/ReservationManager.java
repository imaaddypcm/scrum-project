package backend;
import java.util.Date;
import java.sql.*;
import java.util.ArrayList;

public class ReservationManager {
	private ArrayList<Reservation> reservations;
	private Connection conn = null;

	public ReservationManager(Connection conn) {
		reservations = new ArrayList<>();
		this.conn = conn;
		try {
			Statement stmt = conn.createStatement();
			stmt.execute("CREATE TABLE IF NOT EXISTS 'reservations' (\n"
			+ " 'id' INTEGER NOT NULL UNIQUE,\n"
			+ " 'numberOfRooms'  INTEGER NOT NULL,\n"
			+ " 'numberOfGuests' INTEGER NOT NULL,\n"
			+ " 'roomType'       INTEGER NOT NULL,\n"
			+ " 'startDate'      DATE NOT NULL,\n"
			+ " 'endDate'        DATE NOT NULL,\n"
			+ " 'customerID'     INTEGER NOT NULL,\n"
			+ " 'billingID'      INTEGER NOT NULL,\n"
			+ " FOREIGN KEY('customerID') REFERENCES 'customers'('id'),\n"
			+ " FOREIGN KEY('billingID') REFERENCES 'billing'('id'),\n"
			+ " PRIMARY KEY('id' AUTOINCREMENT)\n"
			+ ");");

			/*ResultSet rs = stmt.executeQuery("SELECT * FROM 'customers'");
			while (rs.next()) {
				int id = rs.getInt("id");
				String numberOfRooms = rs.getString("numberOfRooms");
				String numberOfGuests = rs.getString("numberOfGuests");
				String rooms = rs.getString("rooms");
				Date startDate = rs.getDate("startDate");
				Date endDate = rs.getDate("endDate");
				int customerID = rs.getInt("customerID");
				int paymentInfo = rs.getInt("paymentInfo");
				Reservation reservation = new Reservation(id, numberOfRooms, numberofGuests, rooms, startDate, endDate, customerID, paymentInfo);
				reservations.add(reservation);
			}
			rs.close();*/
		} catch (SQLException ex) {
			// handle any errors
			System.out.println("ReservationManager");
			System.out.println("SQLException: " + ex.getMessage());
			System.out.println("SQLState: " + ex.getSQLState());
			System.out.println("VendorError: " + ex.getErrorCode());
		}
	}

	public Reservation CreateReservation(Customer customer, int roomType, int numberOfRooms, int numberOfGuests, Date startTime, Date endTime) {
		Reservation reservation = null;
		java.sql.Date startDate = new java.sql.Date(startTime.getTime());
		java.sql.Date endDate = new java.sql.Date(endTime.getTime());
		try {
			PreparedStatement pstmt = conn.prepareStatement("INSERT INTO reservations (numberOfRooms, numberOfGuests, roomType, startDate, endDate, customerID, billingID)\n"
			+ "VALUES (?, ?, ?, ?, ?, ?, ?) RETURNING *;");
			pstmt.setInt(1, numberOfRooms);
			pstmt.setInt(2, numberOfGuests);
			pstmt.setInt(3, roomType);
			pstmt.setDate(4, startDate);
			pstmt.setDate(5, endDate);
			pstmt.setInt(6, 0);
			pstmt.setInt(7, 0);

			ResultSet rs = pstmt.executeQuery();

			int id = rs.getInt("id");
			Date start = new java.util.Date(rs.getDate("startDate").getTime());
			Date end = new java.util.Date(rs.getDate("endDate").getTime());
			rs.close();

			System.out.println("=> <Reservation> Id: "+id+" Start: "+start+" End: "+end);
			reservation = new Reservation(id, customer, roomType, numberOfRooms, numberOfGuests, start, end);
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
