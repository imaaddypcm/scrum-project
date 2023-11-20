package backend;
import java.util.Date;

/**
 *  Name:
    Date: ??/??/2023
    Programmers:
    Description:
    Functions:
    Data Structures:
    Algorithms:
*/
public class Reservation {
	private int id;
	private Customer customer;
	private Billing billing;
	private RoomType roomType;
	private int numberOfRooms;
	private int numberOfGuests;
	private Date startDate;
	private Date endDate;


	public Reservation(int id, Customer customer, Billing billing, RoomType roomType, int numberOfRooms, int numberOfGuests, Date start, Date end){
		this.id = id;
		this.customer = customer;
		this.billing = billing;
		this.roomType = roomType;
		this.numberOfRooms = numberOfRooms;
		this.startDate = start;
		this.endDate = end;
		this.numberOfGuests = numberOfGuests;
	}

	public int getId() {
		return id;
	}

	public Date getStartDate() {
		return startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public RoomType getRoomType() {
		return roomType;
	}

	public Customer getCustomer() {
		return customer;
	}

	public Billing getBilling() {
		return billing;
	}

	public int getNumberOfRooms() {
		return numberOfRooms;
	}

	public int getNumberOfGuests() {
		return numberOfGuests;
	}
}
