package backend;
import java.util.Date;
import java.sql.*;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;

public class CustomerManager {
	private Map<Integer, Customer> customers;
	private static String url = "jdbc:sqlite:hotel.sqlite";
	private Connection conn = null;

	public CustomerManager(Connection conn) {
		customers = new HashMap<>();
		this.conn=conn;
		try {
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
			+ " 'firstName'   VARCHAR(255) NOT NULL,\n"
			+ " 'lastName'    VARCHAR(255) NOT NULL,\n"
			+ " 'phoneNumber' VARCHAR(255) NOT NULL,\n"
			+ " 'email'       VARCHAR(255) NOT NULL,\n"
			+ " 'address'     VARCHAR(255) NOT NULL,\n"
			+ " PRIMARY KEY('id' AUTOINCREMENT)\n"
			+ ");");

			ResultSet rs = stmt.executeQuery("SELECT * FROM 'customers'");
			while (rs.next()) {
				int id = rs.getInt("id");
				String firstName = rs.getString("firstName");
				String lastName = rs.getString("lastName");
				String phoneNumber = rs.getString("phoneNumber");
				String email = rs.getString("email");
				String address = rs.getString("address");
				Customer customer = new Customer(id, firstName, lastName, phoneNumber, email, address);
				System.out.println("Existing customer id: " + id);
				customers.put(id, customer);
			}
			rs.close();
		} catch (SQLException ex) {
			// handle any errors
			System.out.println("CustomerManager");
			System.out.println("SQLException: " + ex.getMessage());
			System.out.println("SQLState: " + ex.getSQLState());
			System.out.println("VendorError: " + ex.getErrorCode());
		}
		System.out.println(customers);
	}

	/**
	 * Takes customer information and checks if the customer's email exists in database, otherwise creates a new Customer object.
	 * @param firstName Customer first name
	 * @param lastName Customer last name
	 * @param phoneNumber Customer phone number
	 * @param email Customer email
	 * @param address Customer address
	 * @return Returns Customer object or null if unsuccessful
	 */
	public Customer findOrMake(String firstName, String lastName, String phoneNumber, String email, String address) {
		Customer customer = null;
		try {
			PreparedStatement pstmt = conn.prepareStatement("SELECT * FROM customers WHERE email = ?;");

			//Insert data
			pstmt.setString(1, email);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				int id = rs.getInt("id");
				rs.close();
				System.out.println("<Customer.findOrMake> Found customer id: " + id);
				return customers.get(id);
			}
			rs.close();

			// No sutable canidate found
			System.out.println("<Customer.findOrMake> Create customer");
			customer = createCustomer(firstName, lastName, phoneNumber, email, address);
			pstmt.close();

		} catch (SQLException ex) {
			System.out.println("<Customer.findOrMake>");
			System.out.println("SQLException: " + ex.getMessage());
			System.out.println("SQLState: " + ex.getSQLState());
			System.out.println("VendorError: " + ex.getErrorCode());
		}
		return customer;
	}

	/**
	 * Takes customer information and inserts it into the database, then creates a Customer object.
	 * @param firstName Customer first name
	 * @param lastName Customer last name
	 * @param phoneNumber Customer phone number
	 * @param email Customer email
	 * @param address Customer address
	 * @return Returns newly created Customer object or null if unsuccessful
	 */
	public Customer createCustomer(String firstName, String lastName, String phoneNumber, String email, String address){
		Customer customer = null;

		try {
			PreparedStatement pstmt = conn.prepareStatement("INSERT INTO customers (firstName, lastName, phoneNumber, email, address)\n"
			+ "VALUES (?, ?, ?, ?, ?) RETURNING *;");

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
			customers.put(id, customer);
			pstmt.close();

		} catch (SQLException ex) {
			System.out.println("createCustomer");
			System.out.println("SQLException: " + ex.getMessage());
			System.out.println("SQLState: " + ex.getSQLState());
			System.out.println("VendorError: " + ex.getErrorCode());
		}
		return customer;
	}

	public Customer getCustomer(int id) {
		return customers.get(id);
	}



}
