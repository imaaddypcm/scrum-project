package backend;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Factory interface for managing rooms using the specified database connection.
 * @author Arie Geiger, Jose Cortes, Kyle Cushing, Erik Zeladita
 * @version Nov 21, 2023
 */
public class RoomManager {
	private ArrayList<Room> rooms;

	private Connection conn = null;
	/**
	 * Constructs a RoomManager with a given database connection
	 * @param conn The database connection to use for room managment.
	 */
	public RoomManager(Connection conn) {
		this.conn = conn;
		rooms = new ArrayList<>();
		Manager man = Manager.getManager(conn);
		RoomTypeManager rtypeman = man.getRoomTypeManager();
		try {
			// Create table if it doesn't already exist
			Statement stmt = conn.createStatement();
			stmt.execute("CREATE TABLE IF NOT EXISTS 'rooms' (\n"
			+ " 'number'     INTEGER NOT NULL UNIQUE,\n"
			+ " 'roomTypeID' INTEGER NOT NULL,\n"
			+ " FOREIGN KEY('roomTypeID') REFERENCES 'roomTypes'('id'),\n"
			+ "	PRIMARY KEY('number')\n"
			+ ");");

			// Insert preexisting entries into rooms HashMap
			ResultSet rs = stmt.executeQuery("SELECT * FROM rooms");
			while(rs.next()){
				int roomNumber = rs.getInt("number");
				int roomTypeID = rs.getInt("roomTypeID");

				Room room = new Room(roomNumber, rtypeman.getRoomType(roomTypeID));
				rooms.add(room);
			}

			rs.close();
			stmt.close();
		} catch (SQLException e){
			e.printStackTrace();
		}
	}

	/**
	 * Get a list of all rooms managed by this RoomManger.
	 * @return A list of rooms objects representing the rooms.
	 */
	public List<Room> getRooms() {
		return rooms;
	}

	/**
	 * Get a list of all rooms managed by this RoomManger of a specified roomType.
	 * @param rt The roomType of the rooms.
	 * @return A list of rooms objects with the specified roomType.
	 */
	public List<Room> getRooms(RoomType rt) {
		List<Room> ret = new ArrayList<>();
		for (Room room : rooms) {
			if (room.getType().getId() == rt.getId()) {
				ret.add(room);
			}
		}
		return ret;
	}

	/**
	 * Add a room to be managed by this RoomManager.
	 * @param roomNumber The number of the room to create.
	 * @param roomType The type of the room to create.
	 * @return The new Room object, or null if an error occurs.
	 */
	public Room createRoom(int roomNumber, RoomType roomType) {
		Room room = null;
		try {
			PreparedStatement pstmt = conn.prepareStatement("INSERT INTO rooms (number, roomTypeID)\n"
			+ "VALUES (?, ?) RETURNING *;");

			//Insert data
			pstmt.setInt(1, roomNumber);
			pstmt.setInt(2, roomType.getId());

			ResultSet rs = pstmt.executeQuery();
			int id = rs.getInt("number");
			rs.close();
			assert(id == roomNumber);

			// Create Room object
			System.out.println("=> <CreateRoom> Number: "+roomNumber);
			room = new Room(roomNumber, roomType);
			rooms.add(room);
			pstmt.close();

		}
		catch (SQLException ex){
			System.out.println("SQLException: " + ex.getMessage());
			System.out.println("SQLState: " + ex.getSQLState());
			System.out.println("VendorError: " + ex.getErrorCode());
		}

		return room;
	}

}

