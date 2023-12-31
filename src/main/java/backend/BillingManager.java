package backend;

import java.math.BigDecimal;
import java.util.Date;
import java.sql.*;
import java.util.Map;
import java.util.HashMap;
/**
	 * Factory interface for managing billing records using the specified database connection.
	 * @author Arie Geiger, Jose Cortes, Kyle Cushing, Erik Zeladita
	 * @version Nov 21, 2023
	 */
public class BillingManager {
	private Map<Integer, Billing> billings;
	private Connection conn = null;

	/**
	 * Constructor for objects of class BillingManager.
	 * @param conn The database conncection to use for billing managment.
	 */
	public BillingManager(Connection conn) {
		billings = new HashMap<>();
		this.conn = conn;
		try {
			// Create table if it doesn't already exist
			Statement stmt = conn.createStatement();
			stmt.execute("CREATE TABLE IF NOT EXISTS 'billings' (\n"
			+ " 'id' INTEGER NOT NULL UNIQUE,\n"
			+ " 'cardNumber'     VARCHAR(255) NOT NULL,\n"
			+ "	'cardExpiration' VARCHAR(255) NOT NULL,\n"
			+ "	'cvcNumber'      VARCHAR(255),\n"
			+ "	'nameOnCard'     VARCHAR(255) NOT NULL,\n"
			+ "	'cardType'       VARCHAR(255) NOT NULL,\n"
			+ "	'zipCode'        VARCHAR(255),\n"
			+ "	'amount'         MONEY NOT NULL,\n"
			+ "	'effective'      DATE,\n"
			+ "	PRIMARY KEY('id' AUTOINCREMENT)\n"
			+ ");");

			// Insert preexisting entries into billings HashMap
			ResultSet rs = stmt.executeQuery("SELECT * FROM 'billings'");
			while (rs.next()) {
				int id = rs.getInt("id");
				String cardNumber = rs.getString("cardNumber");
				String cardExpiration = rs.getString("cardExpiration");
				String cvcNumber = rs.getString("cvcNumber");
				String nameOnCard = rs.getString("nameOnCard");
				String cardType = rs.getString("cardType");
				String zipCode = rs.getString("zipCode");
				BigDecimal amount = rs.getBigDecimal("amount");
				Date effective = rs.getDate("effective");
				System.out.println("Existing billing id: " + id);
				Billing payment = new Billing(id, cardNumber, cardExpiration, cvcNumber, nameOnCard, cardType, zipCode, amount, effective);
				billings.put(id, payment);
			}
			rs.close();
		} catch (SQLException ex) {
			// handle any errors
            System.out.println("BillingManager");
			System.out.println("SQLException: " + ex.getMessage());
			System.out.println("SQLState: " + ex.getSQLState());
			System.out.println("VendorError: " + ex.getErrorCode());
		}
	}

	/**
	 * Takes billing information and inserts into database then creates a corresponding Billing object
	 * @param cardNumber Number the card
	 * @param cardExpiration Date of card expiration
	 * @param cvcNumber Card Verification Code
	 * @param nameOnCard Name listed on card
	 * @param cardType Brand/Type of Card
	 * @param zipCode Postal/Zip code associated with card
	 * @param amount  Charging amount of billing
	 * @param effective Charging date of billing
	 * @return Returns the newly created Billing object which will be null if unsuccessful
	 */
	public Billing createBilling(String cardNumber, String cardExpiration, String cvcNumber, String nameOnCard, String cardType, String zipCode, BigDecimal amount, Date effective) {
		Billing billing = null;
		try {
			PreparedStatement pstmt = conn.prepareStatement("INSERT INTO billings (cardNumber, cardExpiration, cvcNumber, nameOnCard, cardType, zipCode, amount, effective)\n"
			+ "VALUES (?, ?, ?, ?, ?, ?, ?, ?) RETURNING *;");

			// Insert data
			pstmt.setString(1, cardNumber);
			pstmt.setString(2, cardExpiration);
			pstmt.setString(3, cvcNumber);
			pstmt.setString(4, nameOnCard);
			pstmt.setString(5, cardType);
			pstmt.setString(6, zipCode);
			pstmt.setBigDecimal(7, amount);
			pstmt.setDate(8, new java.sql.Date(effective.getTime()));

			ResultSet rs = pstmt.executeQuery();
			int id = rs.getInt("id");
			rs.close();

			// Create Billing object
			System.out.println("=> <Billing> Id: "+id);
			billing = new Billing(id, cardNumber, cardExpiration, cvcNumber, nameOnCard, cardType, zipCode, amount, effective);
			billings.put(id, billing);

			pstmt.close();
		} catch (SQLException ex) {
			System.out.println("createBilling");
			System.out.println("SQLException: " + ex.getMessage());
			System.out.println("SQLState: " + ex.getSQLState());
			System.out.println("VendorError: " + ex.getErrorCode());
		}
		return billing;
	}

	/**
	 * Get billing object for billing id
	 * @param id billing id
	 * @return billing object
	 */
	public Billing getBilling(int id) {
		return billings.get(id);
	}

	/**
	 * Delete billing entry from database
	 * @param id billing id
	 * @return true if suceeded, false otherwise
	 */
	public boolean deleteBilling(int id) {
		try {
			PreparedStatement pstmt = conn.prepareStatement("DELETE FROM billings WHERE id = ?;");
			pstmt.setInt(1, id);
			pstmt.executeUpdate();
			pstmt.close();
			billings.remove(id);
			return true;
		} catch (SQLException ex) {
			System.out.println("SQLException: " + ex.getMessage());
			System.out.println("SQLState: " + ex.getSQLState());
			System.out.println("VendorError: " + ex.getErrorCode());
		}

		return false;
	}

	/**
	 * Get estimated revenue of past and future billings for time period
	 * @param start Starting date
	 * @param end   Ending date
	 * @return Estimated revenue
	 */
	public BigDecimal getRevenue(Date start, Date end) {
		BigDecimal value = new BigDecimal(0);
		for (Billing billing : billings.values()) {
			Date effective = billing.getEffective();
			if (!(effective.before(start) || effective.after(end)))
				value = value.add(billing.getAmount());
		}
		return value;
	}
}
