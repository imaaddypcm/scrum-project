import java.util.Date;
import java.sql.*;
import java.util.ArrayList;

public class CustomerManager {
	private ArrayList<Customer> customers;
	private static String url = "jdbc:sqlite:hotel.sqlite";
	private Connection conn = null;

	public CustomerManager() {
		customers = new ArrayList<>();
		try {
			conn = DriverManager.getConnection(url);
			Statement stmt = conn.createStatement();
			//CREATE TABLE "customers" (
			//	"ID"	INTEGER NOT NULL UNIQUE,
			//	"firstName"	VARCHAR(255) NOT NULL,
			//	"lastName"	VARCHAR(255) NOT NULL,
			//	"phoneNumber"	VARCHAR(255) NOT NULL,
			//	"email"	VARCHAR(255),
			//	"address"	VARCHAR(255) NOT NULL,
			//	PRIMARY KEY("ID")
			//);
			stmt.execute("CREATE TABLE IF NOT EXISTS 'customers' (\n"
			+ " 'id'          INTEGER NOT NULL UNIQUE,\n"
			+ "	'firstName'   VARCHAR(255) NOT NULL,\n"
			+ "	'lastName'    VARCHAR(255) NOT NULL,\n"
			+ "	'phoneNumber' VARCHAR(255) NOT NULL,\n"
			+ "	'email'       VARCHAR(255),\n"
			+ "	'address'     VARCHAR(255) NOT NULL,\n"
			+ " PRIMARY KEY('id' AUTOINCREMENT)\n"
			+ ");");
		} catch (SQLException ex) {
			// handle any errors
			System.out.println("CustomerManager");
			System.out.println("SQLException: " + ex.getMessage());
			System.out.println("SQLState: " + ex.getSQLState());
			System.out.println("VendorError: " + ex.getErrorCode());
		}
	}

	public Customer CreateCustomer(String firstName, String lastName, String phoneNumber, String email, String address){
		Customer customer = null;

		try {
			PreparedStatement pstmt = conn.prepareStatement("INSERT INTO customers (firstName, lastName, phoneNumber, email, address)\n"
			+ "VALUES (?, ?, ?, ?, ?,) RETURNING *;");

			//Insert dat
			pstmt.setString(1, firstName);
			pstmt.setString(2, lastName);
			pstmt.setString(3, phoneNumber);
			pstmt.setString(4, email);
			pstmt.setString(5, address);

			ResultSet rs = pstmt.executeQuery();
			int id = rs.getInt("id");
			rs.close();

			System.out.println("=> <Customer> Id: "+id+" First: "+firstName+" Last: "+lastName);
			customer = new Customer(id, firstName, lastName, phoneNumber, email, address);
			customers.add(customer);
			pstmt.close();

		} catch (SQLException ex) {
			System.out.println("createCustomer");
			System.out.println("SQLException: " + ex.getMessage());
			System.out.println("SQLState: " + ex.getSQLState());
			System.out.println("VendorError: " + ex.getErrorCode());
		}
		return customer;
	}
}
