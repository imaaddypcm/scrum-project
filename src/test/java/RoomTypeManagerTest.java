import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import backend.*;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;
import static org.junit.jupiter.api.Assertions.*;
import java.nio.file.Path;

class RoomTypeManagerTest {
	@Test void creation(@TempDir Path tempDir) throws Exception {
		Connection conn = DriverManager.getConnection("jdbc:sqlite:"+tempDir.toAbsolutePath()+"/rtypemancreation.sqlite");
		RoomTypeManager rtypeman = new RoomTypeManager(conn);
		System.out.println(tempDir.toAbsolutePath()+"/rtypemancreation.sqlite");
		assertNotNull(rtypeman, "RoomType manager creation failed!");
    }

	@Test void testAddRoomType(@TempDir Path tempDir) throws Exception {
		String name = "a name";
		String description = "description";
		String rules = "rule";
		int numberOfBeds = 2;
		int price = 1;
		Connection conn = DriverManager.getConnection("jdbc:sqlite:"+tempDir.toAbsolutePath()+"/addRoomType.sqlite");
		RoomTypeManager rtypeman = new RoomTypeManager(conn);
		assertNotNull(rtypeman, "Reservation manager creation failed!");
		RoomType roomType = rtypeman.addRoomType(name,description,rules,numberOfBeds,price);
		assertNotNull(roomType);
		assertEquals(name, roomType.getName(), "Invalid name");
		assertEquals(description, roomType.getDescription(), "Invalid description");
		assertEquals(rules, roomType.getRules(), "Invalid rules");
		assertEquals(numberOfBeds, roomType.getNumberOfBeds(), "Invalid number of beds");
		assertEquals(price, roomType.getPrice(), "Invalid price");
	}

	@Test void testGetRoomType(@TempDir Path tempDir) throws Exception {
		String name = "a name";
		String description = "description";
		String rules = "rule";
		int numberOfBeds = 2;
		int price = 1;
		Connection conn = DriverManager.getConnection("jdbc:sqlite:"+tempDir.toAbsolutePath()+"/getRoomType.sqlite");
		RoomTypeManager rtypeman = new RoomTypeManager(conn);
		assertNotNull(rtypeman, "Reservation manager creation failed!");
		RoomType roomType = rtypeman.addRoomType(name,description,rules,numberOfBeds,price);
		assertNotNull(roomType);
		int id = roomType.getId();
		RoomType roomType2 = rtypeman.getRoomType(id);
		assertTrue(roomType == roomType2, "getRoomType failed to get roomType.");
	}

}
