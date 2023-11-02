package backend;
import java.util.Date;

public class Reservation {
	private int id;
	private Customer customer;
	private int roomType;
	private int numberOfRooms;
	private int numberOfGuests;
	private Date startDate;
	private Date endDate;
	//Room roomsReserved[];
	//Customer customer;


	public Reservation(int id, Customer customer, int roomType, int numberOfRooms, int numberOfGuests, Date start, Date end){
		this.id = id;
		this.customer = customer;
		this.roomType = roomType;
		this.numberOfRooms = numberOfRooms;
		this.startDate = start;
		this.endDate = end;
		this.numberOfGuests = numberOfGuests;
	}

	public int getId(){
		return id;
	}

	public Customer getCustomer(){
		return customer;
	}
}
