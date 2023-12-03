import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.math.BigDecimal;
import java.util.Date;

import backend.BillingManager;
import backend.Billing;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;
import static org.junit.jupiter.api.Assertions.*;
import java.nio.file.Path;

class BillingTests {
	@Test void testBillingObjectGetters(@TempDir Path tempDir) throws Exception {
		String cardNumber = "1010101";
		String cardExpiration = "12/23";
		String cvcNumber = "123";
		String nameOnCard = "John Doe";
		String cardType = "Visa";
		String zipCode = "12345";

		Billing billing = new Billing(0, cardNumber, cardExpiration, cvcNumber, nameOnCard, cardType, zipCode, BigDecimal.valueOf(100), new Date());
		assertNotNull(billing);
		assertEquals(cardNumber, billing.getCardNumber(), "Invalid card number");
		assertEquals(cardExpiration, billing.getcardExpiration(), "Invalid expiration date");
		assertEquals(cvcNumber, billing.getCvcNumber(), "Invalid cvc number");
		assertEquals(nameOnCard, billing.getNameOnCard(), "Invalid name on card");
		assertEquals(cardType, billing.getCardType(), "Invalid card type");
		assertEquals(zipCode, billing.getZipCode(), "Invalid zip code");
	}

	@Test void testConstructor(@TempDir Path tempDir) throws Exception {
		Connection conn = DriverManager.getConnection("jdbc:sqlite:"+tempDir.toAbsolutePath()+"/dummy.sqlite");
		BillingManager bman = new BillingManager(conn);
		assertNotNull(bman, "Reservation manager creation failed!");
    }

	@Test void testCreateBilling(@TempDir Path tempDir) throws Exception {
		String cardNumber = "1010101";
		String cardExpiration = "12/23";
		String cvcNumber = "123";
		String nameOnCard = "John Doe";
		String cardType = "Visa";
		String zipCode = "12345";
		Connection conn = DriverManager.getConnection("jdbc:sqlite:"+tempDir.toAbsolutePath()+"/dummy.sqlite");
		BillingManager bman = new BillingManager(conn);
		assertNotNull(bman, "Reservation manager creation failed!");
		Billing billing = bman.createBilling(cardNumber, cardExpiration, cvcNumber, nameOnCard, cardType, zipCode, BigDecimal.valueOf(100), new Date());
		assertNotNull(billing);
		assertEquals(cardNumber, billing.getCardNumber(), "Invalid card number");
		assertEquals(cardExpiration, billing.getcardExpiration(), "Invalid expiration date");
		assertEquals(cvcNumber, billing.getCvcNumber(), "Invalid cvc number");
		assertEquals(nameOnCard, billing.getNameOnCard(), "Invalid name on card");
		assertEquals(cardType, billing.getCardType(), "Invalid card type");
		assertEquals(zipCode, billing.getZipCode(), "Invalid zip code");
	}

	@Test void testGetBilling(@TempDir Path tempDir) throws Exception {
		String cardNumber = "1010101";
		String cardExpiration = "12/23";
		String cvcNumber = "123";
		String nameOnCard = "John Doe";
		String cardType = "Visa";
		String zipCode = "12345";
		Connection conn = DriverManager.getConnection("jdbc:sqlite:"+tempDir.toAbsolutePath()+"/dummy.sqlite");
		BillingManager bman = new BillingManager(conn);
		assertNotNull(bman, "Reservation manager creation failed!");
		Billing billing = bman.createBilling(cardNumber, cardExpiration, cvcNumber, nameOnCard, cardType, zipCode, BigDecimal.valueOf(100), new Date());
		assertNotNull(billing);
		assertEquals(cardNumber, billing.getCardNumber(), "Invalid card number");
		assertEquals(cardExpiration, billing.getcardExpiration(), "Invalid expiration date");
		assertEquals(cvcNumber, billing.getCvcNumber(), "Invalid cvc number");
		assertEquals(nameOnCard, billing.getNameOnCard(), "Invalid name on card");
		assertEquals(cardType, billing.getCardType(), "Invalid card type");
		assertEquals(zipCode, billing.getZipCode(), "Invalid zip code");
		Billing billing2 = bman.getBilling(billing.getId());
		assertTrue(billing == billing2, "Result of createBilling() and getBilling() are not the same");
	}

	@Test void testGetNoneexistentBilling(@TempDir Path tempDir) throws Exception {
		Connection conn = DriverManager.getConnection("jdbc:sqlite:"+tempDir.toAbsolutePath()+"/dummy.sqlite");
		BillingManager bman = new BillingManager(conn);
		assertNotNull(bman, "Reservation manager creation failed!");
		Billing retrivedBilling = bman.getBilling(999);
		assertNull(retrivedBilling, "Returned invalid billing object!");
	}

}
