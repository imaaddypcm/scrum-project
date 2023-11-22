/**
 * Singleton interface for getting a connection to hotel.sqlite.
 * @author Arie Geiger, Jose Cortes, Kyle Cushing, Erik Zeladita
 * @version Nov 21, 2023
 */
package backend;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
	private static Connection conn = null;
	private static String url = "jdbc:sqlite:hotel.sqlite";
	/**
	 * Returns connection to hotel.sqlite if exists, otherwise creates one.
	 * @return Database connection to hotel.sqlite
	 */
	public static Connection getConnection() {
		if (conn == null) {
			try {
				conn = DriverManager.getConnection(url);
			} catch (SQLException ex) {
				System.out.println("Failed to connect to database:");
				System.out.println("SQLException: " + ex.getMessage());
				System.out.println("SQLState: " + ex.getSQLState());
				System.out.println("VendorError: " + ex.getErrorCode());
			}
		}
		return conn;
	}
}
