import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Calendar;

import backend.*;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;
import static org.junit.jupiter.api.Assertions.*;
import java.nio.file.Path;
import java.util.Date;
import java.time.LocalDate;

class CreateReservation {
	@Test void creation(@TempDir Path tempDir) throws Exception {
		Connection conn = DriverManager.getConnection("jdbc:sqlite:"+tempDir.toAbsolutePath()+"/dummy.sqlite");
		ReservationManager resman = new ReservationManager(conn);
		assertNotNull(resman, "Reservation manager creation failed!");
    }
	@Test void getInvalidID(@TempDir Path tempDir) throws Exception {
		Connection conn = DriverManager.getConnection("jdbc:sqlite:"+tempDir.toAbsolutePath()+"/funkyinput.sqlite");
		ReservationManager resman = new ReservationManager(conn);
		Reservation res = resman.getReservation(99999);// No existing reservation matches given id
		assertNull(res, "Reservation fetch failed!");
    }
	@Test void get(@TempDir Path tempDir) throws Exception {
		Connection conn = DriverManager.getConnection("jdbc:sqlite:"+tempDir.toAbsolutePath()+"/funkyinput.sqlite");
		ReservationManager resman = new ReservationManager(conn);
		Reservation res = resman.getReservation(99999);// No existing reservation matches given id
		assertNull(res, "Reservation fetch failed!");
    }
	@Test void cancelReservation(@TempDir Path tempDir) throws Exception {
		Connection conn = DriverManager.getConnection("jdbc:sqlite:"+tempDir.toAbsolutePath()+"/cancelreservation.sqlite");
		ReservationManager resman = new ReservationManager(conn);
		CustomerManager cman = new CustomerManager(conn);
		String firstName = "John";
		String lastName = "Doe";
		String phoneNumber = "1-800-666-6666";
		String email = "test@example.com";
		String address = "18111 Nordhoff St, Northridge, CA 91330";
		Customer customer = cman.createCustomer(firstName, lastName, phoneNumber, email, address);
		assertNotNull(customer, "Customer creation failed!");

		String cardNumber = "1010101";
		String cardExpiration = "12/23";
		String cvcNumber = "123";
		String nameOnCard = "John Doe";
		String cardType = "Visa";
		String zipCode = "12345";

		BillingManager billman = new BillingManager(conn);
		Billing billing = billman.createBilling(cardNumber, cardExpiration, cvcNumber, nameOnCard, cardType, zipCode);
		assertNotNull(billing, "Billing creation failed!");

		RoomTypeManager rtypeman = new RoomTypeManager(conn);
		String name = "a name";
		String description = "description";
		String rules = "rule";
		int numberOfBeds = 2;
		int price = 1;
		RoomType roomType = rtypeman.addRoomType(name, description, rules, numberOfBeds, price);
		assertNotNull(roomType, "Room type creation failed!");

		RoomManager rooman = Manager.getManager(conn).getRoomManager();
		rooman.createRoom(1, roomType);

		Date today = new Date();
		Date tomorrow = new Date();
		Reservation res = resman.createReservation(customer, billing, roomType, 1, 2, today, tomorrow);
		assertNotNull(res, "Reservation creation failed!");
		assertTrue(resman.cancelReservation(res.getId()));
	}
}
