package backend;

// id - autoincrement
// Name
// Description
// Rules
import java.sql.*;
import java.util.ArrayList;

public class RoomTypeManager {
	private ArrayList<RoomType> roomTypes;

	private Connection conn = null;
	public RoomTypeManager(Connection conn){
		this.conn = conn;
		roomTypes = new ArrayList<>();
		try{
			Statement stmt = conn.createStatement();
			stmt.execute("CREATE TABLE IF NOT EXISTS 'roomTypes' (\n"
			+ " 'id'    INTEGER NOT NULL UNIQUE,\n"
			+ "	'name'  VARCHAR(255) NOT NULL,\n"
			+ "	'description'  VARCHAR(255) NOT NULL,\n"
			+ "	'rules'  VARCHAR(255) NOT NULL,\n"
			+ "	PRIMARY KEY('id' AUTOINCREMENT)\n"
			+ ");");

			ResultSet rs = stmt.executeQuery("SELECT * FROM roomTypes");
			while(rs.next()){
				int roomTypeID = rs.getInt("id");
				String roomName = rs.getString("name");
				String roomDescription = rs.getString("description");
				String roomRules = rs.getString("rules");

				RoomType roomType = new RoomType(roomTypeID, roomName, roomDescription, roomRules);
				roomTypes.add(roomType);
			}

			rs.close();
			stmt.close();
		} catch (SQLException e){
			e.printStackTrace();
		}
	}

	public ArrayList<RoomType> getRoomTypes() {
		return roomTypes;
	}
}
