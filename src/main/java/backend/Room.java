package backend;

/**
 * Object class representing hotel rooms
 * @author Arie Geiger, Jose Cortes, Kyle Cushing, Erik Zeladita
 * @version Nov 21, 2023
 */
public class Room {
	private int roomNumber;
	private int roomType;
	//private String rules;

	/**
	 * Create Room object, associated with a room entry in the database.
	 * @param roomNumber Number associated with room entry in the database
	 * @param roomType The type of the room
	 */
	public Room(int roomNumber, int roomType) {
		this.roomNumber = roomNumber;
		this.roomType   = roomType;
	}

	/**
	 * Get a Room object's number.
	 * @return Returns a room number represented as an integer.
	 */
	public int getRoomNumber() {
		return roomNumber;
	}

	/**
	 * Get a Room object's RoomType.
	 * @return Returns a room type represented as an integer.
	 */
	public int getRoomType() {
		return roomType;
	}
}
