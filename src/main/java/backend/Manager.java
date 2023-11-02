package backend;

import java.sql.Connection;

public class Manager {
	private static CustomerManager cman = null;
	private static ReservationManager resman = null;
	private static RoomManager rooman = null;
	private static PaymentInfoManager pman = null;
	private static RoomTypeManager rtypeman = null;

	public static CustomerManager getCustomerManager() {
		Connection conn = DatabaseConnection.getConnection();
		if (cman == null) {
			cman = new CustomerManager(conn);
		}
		return cman;
	}

	public static ReservationManager getReservationManager() {
		Connection conn = DatabaseConnection.getConnection();
		if (resman == null) {
			resman = new ReservationManager(conn);
		}
		return resman;
	}

	public static RoomManager getRoomManager() {
		Connection conn = DatabaseConnection.getConnection();
		if (rooman == null) {
			rooman = new RoomManager(conn);
		}
		return rooman;
	}

	public static RoomTypeManager getRoomTypeManager() {
		Connection conn = DatabaseConnection.getConnection();
		if (rtypeman == null) {
			rtypeman = new RoomTypeManager(conn);
		}
		return rtypeman;
	}

	public static PaymentInfoManager getPaymentInfoManager() {
		Connection conn = DatabaseConnection.getConnection();
		if (pman == null) {
			pman = new PaymentInfoManager(conn);
		}
		return pman;
	}
}
