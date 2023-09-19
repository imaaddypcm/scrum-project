import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

class Main {
	static String url = "jdbc:sqlite:hotel.db";

	public static void main(String[] args) {
		try {
			// The newInstance() call is a work around for some
			// broken Java implementations
			Class.forName("com.mysql.cj.jdbc.Driver")/*.newInstance()*/;
			Class.forName("org.sqlite.jdbc4")/*.newInstance()*/;
		} catch (Exception ex) {
			// handle the error
		}
		System.out.println("Connecting database...");
		Connection conn = null;
		try {
			conn = DriverManager.getConnection(url);
			// Do something with the Connection
		} catch (SQLException ex) {
			// handle any errors
			System.out.println("SQLException: " + ex.getMessage());
			System.out.println("SQLState: " + ex.getSQLState());
			System.out.println("VendorError: " + ex.getErrorCode());
		}
	}
}
