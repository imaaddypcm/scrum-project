import java.sql.*;

class Main {
	private static String url = "jdbc:sqlite:hotel.sqlite";
	public static void main(String[] args) {
		System.out.println("Starting...");
		Connection conn = null;
		try {
			conn = DriverManager.getConnection(url);
		} catch (SQLException ex) {
			System.out.println("Failed to connect to database:");
			System.out.println("SQLException: " + ex.getMessage());
			System.out.println("SQLState: " + ex.getSQLState());
			System.out.println("VendorError: " + ex.getErrorCode());
		}
		CustomerManager cm = new CustomerManager(conn);
		PaymentInfoManager pm = new PaymentInfoManager(conn);
		ReservationManager rm = new ReservationManager(conn);
		Gui ui = new Gui(rm);
	}
}
