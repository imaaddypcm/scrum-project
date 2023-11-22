import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import backend.*;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;
import static org.junit.jupiter.api.Assertions.*;
import java.nio.file.Path;

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
}
