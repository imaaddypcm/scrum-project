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
			+ "	'beds'   INTEGER NOT NULL,\n"
			+ "	PRIMARY KEY('id' AUTOINCREMENT)\n"
			+ ");");

			ResultSet prs = null;
            prs = stmt.executeQuery("SELECT * FROM roomTypes WHERE id = 0");
			if (!prs.next()) {
				System.out.println("=> Creating room type 0");
				prs.close();
				PreparedStatement instmt = conn.prepareStatement("INSERT INTO 'roomTypes' (id, name, description, rules, beds)\n"
				+ "VALUES (?,?,?,?,?) RETURNING *;");
				instmt.setInt(1, 0);
				instmt.setString(2, "Deluxe Suite");
				instmt.setString(3,"- Fully-equipped kitchen with refrigerator, stovetop and microwave\n"
					+ "- Free Wifi");
				instmt.setString(4,"Rules");
				instmt.setInt(5, 1);
				instmt.execute();
				instmt.close();
			}
			prs.close();

            prs = stmt.executeQuery("SELECT * FROM roomTypes WHERE id = 1");
			if (!prs.next()) {
				System.out.println("=> Creating room type 1");
				prs.close();
				PreparedStatement instmt = conn.prepareStatement("INSERT INTO roomTypes (id, name, description, rules, beds)\n"
				+ "VALUES (?,?,?,?,?) RETURNING *;");
				instmt.setInt(1, 1);
				instmt.setString(2, "Studio Suite");
				instmt.setString(3,"- Fully-equipped kitchen with refrigerator, stovetop and microwave\n"
					+ "- Free Wifi");
				instmt.setString(4,"Rules");
				instmt.setInt(5, 1);
				instmt.execute();
				instmt.close();
			}
			prs.close();

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
