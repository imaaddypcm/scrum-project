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
			Statement stmt = conn.createStatement();
			stmt.execute("CREATE TABLE IF NOT EXISTS 'rooms' (\n"
			+ " 'id'    INTEGER NOT NULL UNIQUE,\n"
			+ "	'type'  VARCHAR(255) NOT NULL,\n"
			+ "	PRIMARY KEY('id' AUTOINCREMENT)\n"
			+ ");");
		} catch (SQLException e){
			e.printStackTrace();
		}
	}
	public List<Room> getAllRooms(){
			/*CREATE TABLE "rooms" (
			"roomID"	INTEGER,
			"room_number"	TEXT NOT NULL,
			"room_type"	TEXT NOT NULL,
			"price"	REAL NOT NULL,
			 **"description"	TEXT,**********************
			"rules"         TEXT*********************
			PRIMARY KEY("room_ID")
			);*/

		try{
			Statement stmt = conn.createStatement();

			ResultSet rs = stmt.executeQuery("SELECT * FROM rooms");
			while(rs.next()){
				int roomNumber = rs.getInt("id");
				//String roomNumber = rs.getString("room_number");
				String roomType = rs.getString("room_type");
				//double price = rs.getDouble("price");

				Room room = new Room(roomNumber, roomType);
				rooms.add(room);
			}

			rs.close();
			stmt.close();
		} catch (SQLException e){
			e.printStackTrace();
		}
		return rooms;
	}

	public Room CreateRoom(int roomNumber, String rules, String roomType, int price){
		Room room = null;
		try {
			PreparedStatement pstmt = conn.prepareStatement("INSERT INTO rooms (roomNumber, rules, roomType, price, add)\n"
			+ "VALUES (?, ?, ?, ?, ?,) RETURNING *;");

			//Insert data
			pstmt.setString(1, rules);
			pstmt.setString(2, roomType);
			pstmt.setInt(3, roomNumber);
			pstmt.setInt(4, price);

			ResultSet rs = pstmt.executeQuery();
			int id = rs.getInt("id");
			rs.close();

			System.out.println("=> <Room> Number: "+roomNumber);
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

