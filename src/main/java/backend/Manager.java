package backend;

import java.sql.Connection;

public class Manager {
	private static CustomerManager cman = null;
	private static ReservationManager resman = null;
	private static RoomManager rooman = null;
	private static BillingManager bman = null;
	private static RoomTypeManager rtypeman = null;

	/**
	 * Creates a CustomerManager object if not already created.
	 * @return Returns the newly or already created CustomerManager object.
	 */
	public static CustomerManager getCustomerManager() {
		Connection conn = DatabaseConnection.getConnection();
		if (cman == null) {
			cman = new CustomerManager(conn);
		}
		return cman;
	}

	/**
	 * Creates a ReservationManager object if not already created.
	 * @return Returns the newly or already created ReservationManager object.
	 */
	public static ReservationManager getReservationManager() {
		Connection conn = DatabaseConnection.getConnection();
		if (resman == null) {
			resman = new ReservationManager(conn);
		}
		return resman;
	}

	/**
	 * Creates a RoomManager object if not already created.
	 * @return Returns the newly or already created RoomManager object.
	 */
	public static RoomManager getRoomManager() {
		Connection conn = DatabaseConnection.getConnection();
		if (rooman == null) {
			rooman = new RoomManager(conn);
		}
		return rooman;
	}

	/**
	 * Creates a RoomTypeManager object if not already created.
	 * @return Returns the newly or already created RoomTypeManager object.
	 */
	public static RoomTypeManager getRoomTypeManager() {
		Connection conn = DatabaseConnection.getConnection();
		if (rtypeman == null) {
			rtypeman = new RoomTypeManager(conn);
		}
		return rtypeman;
	}

	/**
	 * Creates a BillingManager object if not already created.
	 * @return Returns the newly or already created BillingManager object.
	 */
	public static BillingManager getBillingManager() {
		Connection conn = DatabaseConnection.getConnection();
		if (bman == null) {
			bman = new BillingManager(conn);
		}
		return bman;
	}
}
