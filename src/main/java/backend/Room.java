package backend;

public class Room {
	private int roomNumber;
	private String roomType;
	//private String rules;

	public Room(int roomNumber, String roomType){
		this.roomNumber=roomNumber;
		//this.rules=rules;
		this.roomType = roomType;
	}

	public int getRoomNumber(){
		return roomNumber;
	}

	public String getRoomType(){
		return roomType;
	}

	public int getPrice(){
		return 0; //TODO
		//return price;
	}
}
