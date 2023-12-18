package backend;

// id - autoincrement
// Name
// Description
// Rules
import java.sql.*;
import java.util.HashMap;

/**
 * Factory interface for managing rooms types using the specified database connection.
 * @author Arie Geiger, Jose Cortes, Kyle Cushing, Erik Zeladita
 * @version Nov 21, 2023
 */
public class RoomTypeManager {
	private HashMap<Integer, RoomType> roomTypes;
	private Connection conn = null;

	/**
	 * Constructs a RoomTypeManager object.
	 * @param conn A connection with a specific database.
	 */
	public RoomTypeManager(Connection conn) {
		this.conn = conn;
		roomTypes = new HashMap<>();
		try {
			// Create table if it doesn't already exist
			Statement stmt = conn.createStatement();
			stmt.execute("CREATE TABLE IF NOT EXISTS 'roomTypes' (\n"
			+ " 'id'    INTEGER NOT NULL UNIQUE,\n"
			+ "	'name'  VARCHAR(255) NOT NULL,\n"
			+ "	'description'  VARCHAR(255) NOT NULL,\n"
			+ "	'rules'  VARCHAR(255) NOT NULL,\n"
			+ "	'beds'   INTEGER NOT NULL,\n"
			+ "	'price'  INTEGER NOT NULL,\n"
			+ "	PRIMARY KEY('id' AUTOINCREMENT)\n"
			+ ");");

			// Insert preexisting entries into roomTypes HashMap
			ResultSet rs = stmt.executeQuery("SELECT * FROM roomTypes");
			while(rs.next()){
				int roomTypeID = rs.getInt("id");
				String roomName = rs.getString("name");
				String roomDescription = rs.getString("description");
				String roomRules = rs.getString("rules");
				int numberOfBeds = rs.getInt("beds");
				int price = rs.getInt("price");

				RoomType roomType = new RoomType(roomTypeID, roomName, roomDescription, roomRules, numberOfBeds, price);
				roomTypes.put(roomTypeID, roomType);
			}
			rs.close();
			stmt.close();

			// Create default room types
			ResultSet prs = null;
            prs = stmt.executeQuery("SELECT * FROM roomTypes WHERE id = 0");
			if (!prs.next()) {
				System.out.println("=> Creating room type 0");
				prs.close();
				addRoomTypei(0, "Deluxe Suite", "- Fully-equipped kitchen with refrigerator, stovetop and microwave\n- Free Wifi", "No pets allowed", 1, 80);
			}
			prs.close();

            prs = stmt.executeQuery("SELECT * FROM roomTypes WHERE id = 1");
			if (!prs.next()) {
				System.out.println("=> Creating room type 1");
				prs.close();
				addRoomTypei(1, "Studio Suite", "- Fully-equipped kitchen with refrigerator, stovetop and microwave\n- Free Wifi", "No pets allowed", 1, 90);
			}
			prs.close();
			stmt.close();
		} catch (SQLException e){
			e.printStackTrace();
		}
	}

	/**
	 * Retrieves a RoomType object with the specifies ID
	 * @param id id of room type
	 * @return Returns the RoomType object corresponding to the provided ID, or null if not found
	 */
	public RoomType getRoomType(int id) {
		return roomTypes.get(id);
	}

	/**
	 * Get a room object's type
	 * @return returns a list of room types
	 */
	public Iterable<RoomType> getRoomTypes() {
		return roomTypes.values();
	}

	/**
	 * Add room type with specified id
	 * @param id id of room type
	 * @param name name of the type of room
	 * @param description acessories available for set roomt type
	 * @param rules rules in place for this specific room
	 * @param numberOfBeds number of beds for set room type
	 * @param price Price per night of room type
	 * @return Room type structure
	 */
	private RoomType addRoomTypei(int id, String name, String description, String rules, int numberOfBeds, int price) {
		RoomType roomType = null;
		try {
			PreparedStatement pstmt = conn.prepareStatement("INSERT INTO roomTypes (id, name, description, rules, beds, price)\n"
			+ "VALUES (?,?,?,?,?,?) RETURNING *;");

			pstmt.setInt(1, id);
			pstmt.setString(2, name);
			pstmt.setString(3, description);
			pstmt.setString(4, rules);
			pstmt.setInt(5, numberOfBeds);
			pstmt.setInt(6, price);
			pstmt.execute();

			System.out.println("=> <RoomType> Id: "+id+" Description: "+description+" Rules: "+rules);
			roomType = new RoomType(id, name, description, rules, numberOfBeds, price);
			roomTypes.put(id, roomType);
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
	 * @param price Price per night of room type
	 * @return Room type structure
	 */
	public RoomType addRoomType(String name, String description, String rules, int numberOfBeds, int price) {
		RoomType roomType = null;
		try {
			PreparedStatement pstmt = conn.prepareStatement("INSERT INTO roomTypes (name, description, rules, beds, price)\n"
			+ "VALUES (?,?,?,?,?) RETURNING *;");

			pstmt.setString(1, name);
			pstmt.setString(2, description);
			pstmt.setString(3, rules);
			pstmt.setInt(4, numberOfBeds);
			pstmt.setInt(5, price);

			ResultSet rs = pstmt.executeQuery();

			int id = rs.getInt("id");
			rs.close();

			System.out.println("=> <RoomType> Id: "+id+" Description: "+description+" Rules: "+rules);
			roomType = new RoomType(id, name, description, rules, numberOfBeds, price);
			roomTypes.put(id, roomType);
			pstmt.close();
		} catch (SQLException ex) {
			System.out.println("SQLException: " + ex.getMessage());
			System.out.println("SQLState: " + ex.getSQLState());
			System.out.println("VendorError: " + ex.getErrorCode());
		}
		return roomType;
	}
}
