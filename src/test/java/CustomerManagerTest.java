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

	@Test void symbolCheck(@TempDir Path tempDir) throws Exception {
		Connection conn = DriverManager.getConnection("jdbc:sqlite:"+tempDir.toAbsolutePath()+"/symbolcheck.sqlite");
		CustomerManager cman = new CustomerManager(conn);
		String firstName = "Name1";
		String lastName = "Name2";
		String phoneNumber = "Number";
		String email = "email";
		String address = "Address";
		Customer customer = cman.createCustomer(firstName, lastName, phoneNumber, email, address);
		assertNull(customer, "Invalid email Address!");
    }

	@Test void symbolCheck2(@TempDir Path tempDir) throws Exception {
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

	@Test void testFindOrMake(@TempDir Path tempDir) throws Exception {
		Connection conn = DriverManager.getConnection("jdbc:sqlite:"+tempDir.toAbsolutePath()+"/findormake.sqlite");
		CustomerManager cman = new CustomerManager(conn);
		String firstName = "Name1";
		String lastName = "Name2";
		String phoneNumber = "Number";
		String email = "email";
		String address = "Address";
		Customer customer = cman.createCustomer(firstName, lastName, phoneNumber, email, address);
		assertNull(customer, "Should be null!");
		Customer customer2 = cman.findOrMake(firstName, lastName, phoneNumber, email, address);
		assertNull(customer2, "Should be null!");
		assertTrue(customer == customer2, "findOrMake failed to retrieve previous customer.");
    }

	@Test void testFindOrMake2(@TempDir Path tempDir) throws Exception {
		Connection conn = DriverManager.getConnection("jdbc:sqlite:"+tempDir.toAbsolutePath()+"/findormake.sqlite");
		CustomerManager cman = new CustomerManager(conn);
		String firstName = "John";
		String lastName = "Doe";
		String phoneNumber = "1-800-666-6666";
		String email = "test@example.com";
		String address = "18111 Nordhoff St, Northridge, CA 91330";
		Customer customer = cman.createCustomer(firstName, lastName, phoneNumber, email, address);
		assertNotNull(customer, "Should not be null!");
		Customer customer2 = cman.findOrMake(firstName, lastName, phoneNumber, email, address);
		assertNotNull(customer2, "Should not be null!");
		assertTrue(customer == customer2, "findOrMake failed to retrieve previous customer.");
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
}
