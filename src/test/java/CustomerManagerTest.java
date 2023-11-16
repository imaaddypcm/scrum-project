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
		CustomerManager resman = new CustomerManager(conn);
		System.out.println(tempDir.toAbsolutePath()+"/dummy.sqlite");
		assertNotNull(resman, "Customer manager creation failed!");
    }

	@Test void symbolcheck(@TempDir Path tempDir) throws Exception {
		Connection conn = DriverManager.getConnection("jdbc:sqlite:"+tempDir.toAbsolutePath()+"/symbolcheck.sqlite");
		CustomerManager cman = new CustomerManager(conn);
		String firstName = "Name1";
		String lastName = "Name2";
		String phoneNumber = "Number";
		String email = "email";
		String address = "Adress";
		Customer customer = cman.createCustomer();// No existing reservation matches given id
		assertNull(customer, "Invalid email Address!");
    }
}
