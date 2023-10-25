package backend;

public class Room {
	int roomNumber;
	String rules;
	String roomType;

	public Room(int roomNumber, String roomType){
		this.roomNumber=roomNumber;
		//this.rules=rules;
		this.rules = roomType;
	}

	public int getRoomNumber(){
		return roomNumber;
	}
	public int getPrice(){
		return 0; //TODO
		//return price;
	}
}
