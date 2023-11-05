package backend;

public class Room {
	private int roomNumber;
	private int roomType;
	//private String rules;

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
