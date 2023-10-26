package backend;

public class Room {
	private int roomNumber;
	private int roomType;
	//private String rules;

	public Room(int roomNumber, int roomType){
		this.roomNumber = roomNumber;
		this.roomType   = roomType;
	}

	public int getRoomNumber() {
		return roomNumber;
	}

	public int getRoomType() {
		return roomType;
	}
}
