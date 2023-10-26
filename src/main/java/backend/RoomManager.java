package backend;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class RoomManager {
	private ArrayList<Room> rooms;

	private Connection conn = null;
	public RoomManager(Connection conn){
		this.conn = conn;
		rooms = new ArrayList<>();
		try{
			/*CREATE TABLE "rooms" (
			"roomID"	INTEGER,
			"room_number"	TEXT NOT NULL,
			"room_type"	TEXT NOT NULL,
			"price"	REAL NOT NULL,
			 **"description"	TEXT,**********************
			"rules"         TEXT*********************
			PRIMARY KEY("room_ID")
			);*/
			Statement stmt = conn.createStatement();
			stmt.execute("CREATE TABLE IF NOT EXISTS 'rooms' (\n"
			+ " 'number' INTEGER NOT NULL UNIQUE,\n"
			+ "	'type'   INTEGER NOT NULL,\n"
			+ "	PRIMARY KEY('number')\n"
			+ ");");

			ResultSet rs = stmt.executeQuery("SELECT * FROM rooms");
			while(rs.next()){
				int roomNumber = rs.getInt("number");
				int roomType   = rs.getInt("type");

				Room room = new Room(roomNumber, roomType);
				rooms.add(room);
			}

			rs.close();
			stmt.close();
		} catch (SQLException e){
			e.printStackTrace();
		}
	}
	public List<Room> getRooms(){
		return rooms;
	}

	public Room CreateRoom(int roomNumber, int roomType){
		Room room = null;
		try {
			PreparedStatement pstmt = conn.prepareStatement("INSERT INTO rooms (name, type)\n"
			+ "VALUES (?, ?) RETURNING *;");

			//Insert data
			pstmt.setInt(1, roomNumber);
			pstmt.setInt(2, roomType);

			ResultSet rs = pstmt.executeQuery();
			int id = rs.getInt("id");
			rs.close();
			assert(id == roomNumber);

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

