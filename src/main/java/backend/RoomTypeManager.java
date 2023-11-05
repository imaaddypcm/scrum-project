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

	/**
	 * Constructs aRoomTypeManager object.
	 * @param conn A connection with a specific database.
	 */
	public RoomTypeManager(Connection conn) {
		this.conn = conn;
		roomTypes = new ArrayList<>();
		try{
			Statement stmt = conn.createStatement();
			stmt.execute("CREATE TABLE IF NOT EXISTS 'roomTypes' (\n"
			+ " 'id'    INTEGER NOT NULL UNIQUE,\n"
			+ "	'name'  VARCHAR(255) NOT NULL,\n"
			+ "	'description'  VARCHAR(255) NOT NULL,\n"
			+ "	'rules'  VARCHAR(255) NOT NULL,\n"
			+ "	'beds'   INTEGER NOT NULL,\n"
			+ "	PRIMARY KEY('id' AUTOINCREMENT)\n"
			+ ");");

			ResultSet rs = stmt.executeQuery("SELECT * FROM roomTypes");
			while(rs.next()){
				int roomTypeID = rs.getInt("id");
				String roomName = rs.getString("name");
				String roomDescription = rs.getString("description");
				String roomRules = rs.getString("rules");
				int numberOfBeds = rs.getInt("beds");

				RoomType roomType = new RoomType(roomTypeID, roomName, roomDescription, roomRules, numberOfBeds);
				roomTypes.add(roomType);
			}
			rs.close();
			stmt.close();

			ResultSet prs = null;
            prs = stmt.executeQuery("SELECT * FROM roomTypes WHERE id = 0");
			if (!prs.next()) {
				System.out.println("=> Creating room type 0");
				prs.close();
				addRoomTypei(0, "Deluxe Suite", "- Fully-equipped kitchen with refrigerator, stovetop and microwave\n- Free Wifi", "Rules", 1);
			}
			prs.close();

            prs = stmt.executeQuery("SELECT * FROM roomTypes WHERE id = 1");
			if (!prs.next()) {
				System.out.println("=> Creating room type 1");
				prs.close();
				addRoomTypei(1, "Studio Suite", "- Fully-equipped kitchen with refrigerator, stovetop and microwave\n- Free Wifi", "Rules", 1);
			}
			prs.close();
			stmt.close();
		} catch (SQLException e){
			e.printStackTrace();
		}
	}

	/**
	 * Get a room object's type
	 * @return returns a list of room types
	 */
	public ArrayList<RoomType> getRoomTypes() {
		return roomTypes;
	}

	/**
	 * Add room type with specified id
	 * @param id id of room type
	 * @param name name of the type of room
	 * @param description acessories available for set roomt type
	 * @param rules rules in place for this specific room
	 * @param numberOfBeds number of beds for set room type
	 * @return Room type structure
	 */
	private RoomType addRoomTypei(int id, String name, String description, String rules, int numberOfBeds) {
		RoomType roomType = null;
		try {
			PreparedStatement pstmt = conn.prepareStatement("INSERT INTO roomTypes (id, name, description, rules, beds)\n"
			+ "VALUES (?,?,?,?,?) RETURNING *;");

			pstmt.setInt(1, id);
			pstmt.setString(2, name);
			pstmt.setString(3, description);
			pstmt.setString(4, rules);
			pstmt.setInt(5, numberOfBeds);
			pstmt.execute();

			System.out.println("=> <RoomType> Id: "+id+" Description: "+description+" Rules: "+rules);
			roomType = new RoomType(id, name, description, rules, numberOfBeds);
			roomTypes.add(roomType);
			pstmt.close();
		} catch (SQLException ex) {
			System.out.println("SQLException: " + ex.getMessage());
			System.out.println("SQLState: " + ex.getSQLState());
			System.out.println("VendorError: " + ex.getErrorCode());
		}
		return roomType;
	}


	/**
	 * Add room type
	 * @param name name of the type of room
	 * @param description acessories available for set room type
	 * @param rules rules in place for this specific room
	 * @param numberOfBeds number of beds for set room type
	 * @return Room type structure
	 */
	public RoomType addRoomType(String name, String description, String rules, int numberOfBeds) {
		RoomType roomType = null;
		try {
			PreparedStatement pstmt = conn.prepareStatement("INSERT INTO roomTypes (name, description, rules, beds)\n"
			+ "VALUES (?,?,?,?) RETURNING *;");

			pstmt.setString(2, name);
			pstmt.setString(3, description);
			pstmt.setString(4, rules);
			pstmt.setInt(5, numberOfBeds);

			ResultSet rs = pstmt.executeQuery();

			int id = rs.getInt("id");
			rs.close();

			System.out.println("=> <RoomType> Id: "+id+" Description: "+description+" Rules: "+rules);
			roomType = new RoomType(id, name, description, rules, numberOfBeds);
			roomTypes.add(roomType);
			pstmt.close();
		} catch (SQLException ex) {
			System.out.println("SQLException: " + ex.getMessage());
			System.out.println("SQLState: " + ex.getSQLState());
			System.out.println("VendorError: " + ex.getErrorCode());
		}
		return roomType;
	}
}
