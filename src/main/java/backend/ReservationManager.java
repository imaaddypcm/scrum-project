package backend;
import java.util.Date;
import java.sql.*;
import java.util.HashMap;
import java.util.Map;
import java.util.ArrayList;

/**
 * Factory interface for managing rooms types using the specified database connection.
 * @author Arie Geiger, Jose Cortes, Kyle Cushing, Erik Zeladita
 * @version Nov 21, 2023
 */
public class ReservationManager {

	private Map<Integer, Reservation> reservations;
	private Connection conn = null;

	RoomManager rooman = null;
	BillingManager bman = null;

	/**
	 * Constructor for objects of class ReservationManager.
	 * @param conn The database conncection to use for reservation managment.
	 */
	public ReservationManager(Connection conn) {
		Manager man = Manager.getManager(conn);
		CustomerManager cman = man.getCustomerManager();
		RoomTypeManager rtypeman = man.getRoomTypeManager();
		rooman = man.getRoomManager();
		bman = man.getBillingManager();

		reservations = new HashMap<>();
		this.conn = conn;
		try {
			// Create table if it doesn't already exist
			Statement stmt = conn.createStatement();
			stmt.execute("CREATE TABLE IF NOT EXISTS 'reservations' (\n"
			+ " 'id' INTEGER NOT NULL UNIQUE,\n"
			+ " 'startDate'      DATE NOT NULL,\n"
			+ " 'endDate'        DATE NOT NULL,\n"
			+ " 'numberOfRooms'  INTEGER NOT NULL,\n"
			+ " 'numberOfGuests' INTEGER NOT NULL,\n"
			+ " 'roomTypeID'     INTEGER NOT NULL,\n"
			+ " 'customerID'     INTEGER NOT NULL,\n"
			+ " 'billingID'      INTEGER NOT NULL,\n"
			+ " FOREIGN KEY('customerID') REFERENCES 'customers'('id'),\n"
			+ " FOREIGN KEY('billingID') REFERENCES 'billings'('id'),\n"
			+ " FOREIGN KEY('roomTypeID') REFERENCES 'roomTypes'('id'),\n"
			+ " PRIMARY KEY('id' AUTOINCREMENT)\n"
			+ ");");

			// Insert preexisting entries into reservations HashMap
			ResultSet rs = stmt.executeQuery("SELECT * FROM 'reservations'");
			while (rs.next()) {
				int id = rs.getInt("id");
				int numberOfRooms = rs.getInt("numberOfRooms");
				int numberOfGuests = rs.getInt("numberOfGuests");
				int roomTypeID = rs.getInt("roomTypeID");
				Date startDate = rs.getDate("startDate");
				Date endDate = rs.getDate("endDate");
				int customerID = rs.getInt("customerID");
				int billingID = rs.getInt("billingID");
				Customer customer = cman.getCustomer(customerID);
				Billing billing = bman.getBilling(billingID);
				RoomType roomType = rtypeman.getRoomType(roomTypeID);
				Reservation reservation = new Reservation(id, customer, billing, roomType, numberOfRooms, numberOfGuests, startDate, endDate);
				reservations.put(id, reservation);
			}
			rs.close();
		} catch (SQLException ex) {
			// handle any errors
			System.out.println("ReservationManager");
			System.out.println("SQLException: " + ex.getMessage());
			System.out.println("SQLState: " + ex.getSQLState());
			System.out.println("VendorError: " + ex.getErrorCode());
		}
	}

	/**
	 * Adds a reservation object to the list and saves it in the database.
	 * @param customer The customer making a reservation.
	 * @param billing The billing oformation for the reservation.
	 * @param roomType The type of room for the reservation.
	 * @param numberOfRooms The number of rooms to rerserve.
	 * @param numberOfGuests The number of Guests for thereservation.
	 * @param startTime The date when a reservation starts.
	 * @param endTime The date when a reservation ends.
	 * @return Created reservation object
	 * @throws ReservationOverflowException if there are too many reservations for the given period.
	 */
	public Reservation createReservation(Customer customer, Billing billing, RoomType roomType, int numberOfRooms, int numberOfGuests, Date startTime, Date endTime) throws ReservationOverflowException {
		Reservation reservation = null;
		java.sql.Date startDate = new java.sql.Date(startTime.getTime());
		java.sql.Date endDate = new java.sql.Date(endTime.getTime());

		if (getNumReservationOverlaps(roomType, startTime, endTime) + numberOfRooms > rooman.getRooms(roomType).size()) {
			throw new ReservationOverflowException("Too many reservations for given period specified.");
		}

		try {
			// Insert data
			PreparedStatement pstmt = conn.prepareStatement("INSERT INTO reservations (numberOfRooms, numberOfGuests, roomTypeID, startDate, endDate, customerID, billingID)\n"
			+ "VALUES (?, ?, ?, ?, ?, ?, ?) RETURNING *;");
			pstmt.setInt(1, numberOfRooms);
			pstmt.setInt(2, numberOfGuests);
			pstmt.setInt(3, roomType.getId());
			pstmt.setDate(4, startDate);
			pstmt.setDate(5, endDate);
			pstmt.setInt(6, customer.getId());
			pstmt.setInt(7, billing.getId());
			System.out.println("CreateReservation: Billing id: " + billing.getId());

			ResultSet rs = pstmt.executeQuery();

			int id = rs.getInt("id");
			Date start = new java.util.Date(rs.getDate("startDate").getTime());
			Date end = new java.util.Date(rs.getDate("endDate").getTime());
			rs.close();

			// Create Reservation object
			System.out.println("=> <Reservation> Id: "+id+" Start: "+start+" End: "+end);
			reservation = new Reservation(id, customer, billing, roomType, numberOfRooms, numberOfGuests, start, end);
			reservations.put(id, reservation);
			pstmt.close();
		} catch (SQLException ex) {
			System.out.println("SQLException: " + ex.getMessage());
			System.out.println("SQLState: " + ex.getSQLState());
			System.out.println("VendorError: " + ex.getErrorCode());
		}
		return reservation;
	}

	/**
	 * Get reservation with given id
	 * @param id Reservation number
	 * @return Reservation object
	 */
	public Reservation getReservation(int id) {
		return reservations.get(id);
	}

	/**
	 * Gets all reservations
	 * @return Iterable of reservations
	 */
	public Iterable<Reservation> getReservations() {
		return reservations.values();
	}

	/**
	 * Cancel a reservation with the given reservatio ID
	 * @param reservationId The ID of the reservation to cancel.
	 * @return True if the reservation is canceled, false if otherwise
	 */
	public boolean cancelReservation(int reservationId) {
		try {
			Reservation res = reservations.get(reservationId);
			if (res.getBilling().getEffective().before(new Date())) {
				bman.deleteBilling(res.getBilling().getId());
			}
			PreparedStatement pstmt = conn.prepareStatement("DELETE FROM reservations WHERE id = ?;");
			pstmt.setInt(1, reservationId);
			pstmt.executeUpdate();
			pstmt.close();
			reservations.remove(reservationId);
			return true;
		} catch (SQLException ex) {
			System.out.println("SQLException: " + ex.getMessage());
			System.out.println("SQLState: " + ex.getSQLState());
			System.out.println("VendorError: " + ex.getErrorCode());
		}

		return false;
	}

	/**
	 * Get number of overlapping reservations
	 * @param roomType Type of the room.
	 * @param start Start date of reservation.
	 * @param end End date of reservation.
	 * @return Number of reservation overlaps.
	 */
	public int getNumReservationOverlaps(RoomType roomType, Date start, Date end) {
		int overlaps = 0;
		ArrayList<ReservationDateCheck> dates = new ArrayList<>();
		for (Reservation reservation : reservations.values()) {
			if (roomType != null && reservation.getRoomType().getId() != roomType.getId()) {
				continue;
			}
			if (reservation.getStartDate().before(end) && reservation.getEndDate().after(start)) {
				dates.add(new ReservationDateCheck(reservation.getStartDate(), true, reservation.getNumberOfRooms()));
				dates.add(new ReservationDateCheck(reservation.getEndDate(), false, reservation.getNumberOfRooms()));
			}
		}
		dates.sort((a, b) -> a.date.compareTo(b.date));
		int count = 0;
		for (ReservationDateCheck date : dates) {
			//System.out.println("Date: " + date.date + " isStart: " + date.isStart);
			if (date.isStart) {
				count += date.count;
			} else {
				count -= date.count;
			}
			overlaps = Math.max(overlaps, count);
		}
		return overlaps;
	}
}

/**
 * Private object for use in checking for overlapping reservation dates.
 * @author Arie Geiger, Jose Cortes, Kyle Cushing, Erik Zeladita
 * @version Dec 17, 2023
 */
class ReservationDateCheck {
	public Date date;
	public boolean isStart;
	public int count;

	/**
	 * Constructor for objects of class ReservationDateCheck.
	 * @param date Date of reservation.
	 * @param isStart Whether reservation started or not.
	 * @param count Represents current count.
	 */
	ReservationDateCheck(Date date, boolean isStart, int count) {
		this.date = date;
		this.isStart = isStart;
		this.count = count;
	}
}
