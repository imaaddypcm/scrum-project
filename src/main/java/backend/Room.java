package backend;

public class Room {
	int roomNumber;
    String rules;
    String roomType;
    int price;

    public Room(int roomNumber, String rules, String roomType, int price){
        this.roomNumber=roomNumber;
        this.rules=rules;
        this.rules = roomType;
        this.price=price;
    }

    public int getRoomNumber(){
        return roomNumber;
    }
    public int getPrice(){
        return price;
    }

    public void setPrice(int p){
        price = p;
    }
}
