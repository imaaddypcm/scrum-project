import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import backend.*;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;
import static org.junit.jupiter.api.Assertions.*;
import java.nio.file.Path;

class CustomerManagerTest {
	@Test void creation(@TempDir Path tempDir) throws Exception {
		Connection conn = DriverManager.getConnection("jdbc:sqlite:"+tempDir.toAbsolutePath()+"/dummy.sqlite");
		CustomerManager cman = new CustomerManager(conn);
		assertNotNull(cman, "Customer manager creation failed!");
    }

	@Test void invalidDatabase(@TempDir Path tempDir) throws Exception {
		Exception exception = assertThrows(NullPointerException.class, () -> {
			new CustomerManager(null);
		});
		//assertTrue(exception.getMessage().contains(""));
	}

	@Test void testGetCustomer(@TempDir Path tempDir) throws Exception {
		Connection conn = DriverManager.getConnection("jdbc:sqlite:"+tempDir.toAbsolutePath()+"/getcustomer.sqlite");
		CustomerManager cman = new CustomerManager(conn);
		String firstName = "John";
		String lastName = "Doe";
		String phoneNumber = "1-800-666-6666";
		String email = "test@example.com";
		String address = "18111 Nordhoff St, Northridge, CA 91330";
		Customer customer = cman.createCustomer(firstName, lastName, phoneNumber, email, address);
		assertNotNull(customer, "Should not be null!");
		Customer customer2 = cman.getCustomer(customer.getId());
		assertNotNull(customer2, "Should not be null!");
		assertTrue(customer == customer2, "getCustomer failed to get customer.");
    }

	@Test void emailCheckInvalid(@TempDir Path tempDir) throws Exception {
		Connection conn = DriverManager.getConnection("jdbc:sqlite:"+tempDir.toAbsolutePath()+"/symbolcheck.sqlite");
		CustomerManager cman = new CustomerManager(conn);
		String firstName = "Name1";
		String lastName = "Name2";
		String phoneNumber = "Number";
		String email = "email";
		String address = "Address";
		Exception exception = assertThrows(IllegalArgumentException.class, () -> {
			cman.createCustomer(firstName, lastName, phoneNumber, email, address);
		});
		assertTrue(exception.getMessage().contains("Invalid email"));
    }

	@Test void emailCheckValid(@TempDir Path tempDir) throws Exception {
		Connection conn = DriverManager.getConnection("jdbc:sqlite:"+tempDir.toAbsolutePath()+"/symbolcheck.sqlite");
		CustomerManager cman = new CustomerManager(conn);
		String firstName = "John";
		String lastName = "Doe";
		String phoneNumber = "1-800-666-6666";
		String email = "test@example.com";
		String address = "18111 Nordhoff St, Northridge, CA 91330";
		Customer customer = cman.createCustomer(firstName, lastName, phoneNumber, email, address);
		assertNotNull(customer, "Invalid email Address!");
    }

	@Test void testFindOrMakeInvalidEmail(@TempDir Path tempDir) throws Exception {
		Connection conn = DriverManager.getConnection("jdbc:sqlite:"+tempDir.toAbsolutePath()+"/findormake.sqlite");
		CustomerManager cman = new CustomerManager(conn);
		String firstName = "Name1";
		String lastName = "Name2";
		String phoneNumber = "Number";
		String email = "email";
		String address = "Address";
		Exception exception = assertThrows(IllegalArgumentException.class, () -> {
			cman.createCustomer(firstName, lastName, phoneNumber, email, address);
		});
		assertTrue(exception.getMessage().contains("Invalid email"));
		Exception exception2 = assertThrows(IllegalArgumentException.class, () -> {
			cman.findOrMake(firstName, lastName, phoneNumber, email, address);
		});
		assertTrue(exception2.getMessage().contains("Invalid email"));

    }

	@Test void testFindOrMakeExistent(@TempDir Path tempDir) throws Exception {
		Connection conn = DriverManager.getConnection("jdbc:sqlite:"+tempDir.toAbsolutePath()+"/findormake.sqlite");
		CustomerManager cman = new CustomerManager(conn);
		String firstName = "John";
		String lastName = "Doe";
		String phoneNumber = "1-800-666-6666";
		String email = "test@example.com";
		String address = "18111 Nordhoff St, Northridge, CA 91330";
		Customer customer = cman.createCustomer(firstName, lastName, phoneNumber, email, address);
		assertNotNull(customer, "Should not be null!");
		assertEquals(customer.getFirstName(), firstName);
		assertEquals(customer.getLastName(), lastName);
		assertEquals(customer.getPhoneNumber(), phoneNumber);
		assertEquals(customer.getEmail(), email);
		assertEquals(customer.getAddress(), address);
		Customer customer2 = cman.findOrMake(firstName, lastName, phoneNumber, email, address);
		assertNotNull(customer2, "Should not be null!");
		assertEquals(customer, customer2, "findOrMake failed to retrieve previous customer.");
    }

	@Test void testFindOrNonexistent(@TempDir Path tempDir) throws Exception {
		Connection conn = DriverManager.getConnection("jdbc:sqlite:"+tempDir.toAbsolutePath()+"/findormake.sqlite");
		CustomerManager cman = new CustomerManager(conn);
		String firstName = "John";
		String lastName = "Doe";
		String phoneNumber = "1-800-666-6666";
		String email = "test@example.com";
		String address = "18111 Nordhoff St, Northridge, CA 91330";
		Customer customer = cman.findOrMake(firstName, lastName, phoneNumber, email, address);
		assertNotNull(customer, "Should not be null!");
		assertEquals(customer.getFirstName(), firstName);
		assertEquals(customer.getLastName(), lastName);
		assertEquals(customer.getPhoneNumber(), phoneNumber);
		assertEquals(customer.getEmail(), email);
		assertEquals(customer.getAddress(), address);
		Customer customer2 = cman.findOrMake(firstName, lastName, phoneNumber, email, address);
		assertNotNull(customer2, "Should not be null!");
		assertEquals(customer, customer2, "findOrMake failed to retrieve previous customer.");
	}
}
