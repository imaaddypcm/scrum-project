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
		System.out.println(tempDir.toAbsolutePath()+"/dummy.sqlite");
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
		Customer customer2 = cman.findOrMake(firstName, lastName, phoneNumber, email, address)
		assertTrue(customer == customer2, "findOrMake failed to retrieve previous customer.");
    }

	@Test void testGetCustomer(@TempDir Path tempDir) throws Exception {
		Connection conn = DriverManager.getConnection("jdbc:sqlite:"+tempDir.toAbsolutePath()+"/getcustomer.sqlite");
		CustomerManager cman = new CustomerManager(conn);
		String firstName = "Name1";
		String lastName = "Name2";
		String phoneNumber = "Number";
		String email = "email";
		String address = "Address";
		Customer customer = cman.createCustomer(firstName, lastName, phoneNumber, email, address);
		Customer customer2 = cman.getCustomer(customer.getId());
		assertTrue(customer == customer2, "getCustomer failed to get customer.");
    }
}
