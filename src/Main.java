import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

class Main {
	static String url = "jdbc:sqlite:hotel.sqlite";

	public static void main(String[] args) {
		System.out.println("Connecting database...");
		Connection conn = null;
		try {
			conn = DriverManager.getConnection(url);
			Statement stmt = conn.createStatement();
			stmt.execute("CREATE TABLE IF NOT EXISTS users (\n"
			+ "	id integer PRIMARY KEY,\n"
			+ "	name text NOT NULL,\n"
			+ "	capacity real\n"
			+ ");");
			// Do something with the Connection
		} catch (SQLException ex) {
			// handle any errors
			System.out.println("SQLException: " + ex.getMessage());
			System.out.println("SQLState: " + ex.getSQLState());
			System.out.println("VendorError: " + ex.getErrorCode());
		}
	}
}
