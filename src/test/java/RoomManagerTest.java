import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import backend.*;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;
import static org.junit.jupiter.api.Assertions.*;
import java.nio.file.Path;

class RoomManagerTest {
	@Test void creation(@TempDir Path tempDir) throws Exception {
		Connection conn = DriverManager.getConnection("jdbc:sqlite:"+tempDir.toAbsolutePath()+"/roomancreation.sqlite");
		RoomManager rooman = new RoomManager(conn);
		System.out.println(tempDir.toAbsolutePath()+"/roomancreation.sqlite");
		assertNotNull(rooman, "Room manager creation failed!");
    }

	@Test void testCreateRoom(@TempDir Path tempDir) throws Exception {
		int roomNumber = 1;
		RoomType roomType = new RoomType(0, "example room type", "example room type", "", 1, 1);
		Connection conn = DriverManager.getConnection("jdbc:sqlite:"+tempDir.toAbsolutePath()+"/createRoom.sqlite");
		RoomManager rooman = new RoomManager(conn);
		assertNotNull(rooman, "Reservation manager creation failed!");
		Room room = rooman.createRoom(roomNumber,roomType);
		assertNotNull(room);
		assertEquals(roomNumber, room.getNumber(), "Invalid room number");
		assertEquals(roomType, room.getType(), "Invalid room type");
	}
}
