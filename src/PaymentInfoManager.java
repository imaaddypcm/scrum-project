import java.util.Date;
import java.sql.*;
import java.util.ArrayList;

public class PaymentInfoManager {
	//private ArrayList<Reservation> reservations;
	private static String url = "jdbc:sqlite:hotel.sqlite";
	private Connection conn = null;

	public PaymentInfoManager() {
		try {
			conn = DriverManager.getConnection(url);
			Statement stmt = conn.createStatement();
			//CREATE TABLE "paymentInformation" (
			//	"ID"	INTEGER NOT NULL UNIQUE,
			//	"cardNumber"	VARCHAR(255) NOT NULL,
			//	"cardExpiration"	VARCHAR(255) NOT NULL,
			//	"ccvNumber"	VARCHAR(255) NOT NULL,
			//	"nameOnCard"	VARCHAR(255) NOT NULL,
			//	"cardType"	VARCHAR(255) NOT NULL,
			//	"zipCode"	VARCHAR(255),
			//	PRIMARY KEY("ID" AUTOINCREMENT)
			//);
			stmt.execute("CREATE TABLE IF NOT EXISTS 'paymentInformation' (\n"
			+ " 'id' INTEGER NOT NULL UNIQUE,\n"
			+ " 'cardNumber'     VARCHAR(255) NOT NULL UNIQUE,\n"
			+ "	'cardExpiration' VARCHAR(255) NOT NULL,\n"
			+ "	'ccvNumber'      VARCHAR(255),\n"
			+ "	'nameOnCard'     VARCHAR(255) NOT NULL,\n"
			+ "	'cardType'       VARCHAR(255) NOT NULL,\n"
			+ "	'zipCode'        VARCHAR(255),\n"
			+ "	PRIMARY KEY('id' AUTOINCREMENT)\n"
			+ ");");
		} catch (SQLException ex) {
			// handle any errors
            System.out.println("PaymentInfoManager");
			System.out.println("SQLException: " + ex.getMessage());
			System.out.println("SQLState: " + ex.getSQLState());
			System.out.println("VendorError: " + ex.getErrorCode());
		}
	}
}
