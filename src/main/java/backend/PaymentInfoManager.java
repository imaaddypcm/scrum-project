package backend;

import java.sql.*;
import java.util.ArrayList;

public class PaymentInfoManager {
	private ArrayList<PaymentInformation> paymentInfos;
	private Connection conn = null;

	public PaymentInfoManager(Connection conn) {
		paymentInfos = new ArrayList<>();
		this.conn = conn;
		try {
			Statement stmt = conn.createStatement();
			stmt.execute("CREATE TABLE IF NOT EXISTS 'billing' (\n"
			+ " 'id' INTEGER NOT NULL UNIQUE,\n"
			+ " 'cardNumber'     VARCHAR(255) NOT NULL UNIQUE,\n"
			+ "	'cardExpiration' VARCHAR(255) NOT NULL,\n"
			+ "	'ccvNumber'      VARCHAR(255),\n"
			+ "	'nameOnCard'     VARCHAR(255) NOT NULL,\n"
			+ "	'cardType'       VARCHAR(255) NOT NULL,\n"
			+ "	'zipCode'        VARCHAR(255),\n"
			+ "	PRIMARY KEY('id' AUTOINCREMENT)\n"
			+ ");");

			ResultSet rs = stmt.executeQuery("SELECT * FROM 'customers'");
			while (rs.next()) {
				int id = rs.getInt("id");
				String cardNumber = rs.getString("cardNumber");
				String cardExpiration = rs.getString("cardExpiration");
				String ccvNumber = rs.getString("ccvNumber");
				String nameOnCard = rs.getString("nameOnCard");
				String cardType = rs.getString("cardType");
				String zipCode = rs.getString("zipCode");
				PaymentInformation payment = new PaymentInformation(id, cardNumber, cardExpiration, ccvNumber, nameOnCard, cardType, zipCode);
				paymentInfos.add(payment);
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

	PaymentInformation CreatePaymentInformation(String cardNumber, String cardExpiration, String ccvNumber, String nameOnCard, String cardType, String zipCode) {
		PaymentInformation paymentInformation = null;
		try {
			PreparedStatement pstmt = conn.prepareStatement("INSERT INTO billing (cardNumber, cardExpiration, ccvNumber, nameOnCard, cardType, zipCode)\n"
			+ "VALUES (?, ?, ?, ?, ?, ?) RETURNING *;");

			//Insert dat
			pstmt.setString(1, cardNumber);
			pstmt.setString(2, cardExpiration);
			pstmt.setString(3, ccvNumber);
			pstmt.setString(4, nameOnCard);
			pstmt.setString(5, cardType);
			pstmt.setString(6, zipCode);

			ResultSet rs = pstmt.executeQuery();
			int id = rs.getInt("id");
			rs.close();

			System.out.println("=> <Billing> Id: "+id);
			paymentInformation = new PaymentInformation(id, cardNumber, cardExpiration, ccvNumber, nameOnCard, cardType, zipCode);
			paymentInfos.add(paymentInformation);

			pstmt.close();
		} catch (SQLException ex) {
			System.out.println("createBilling");
			System.out.println("SQLException: " + ex.getMessage());
			System.out.println("SQLState: " + ex.getSQLState());
			System.out.println("VendorError: " + ex.getErrorCode());
		}
		return paymentInformation;
	}
}
