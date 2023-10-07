import java.util.Date;

public class Reservation {
	private int reservationId;
	private Date reservationStart;
	private Date reservationEnd;
	private int numberRooms;
	private int numberOfGuests;
	//Room roomsReserved[];
	//Customer customer;


	public Reservation(int id, Date start, Date end){
		reservationStart = start;
		reservationEnd = end;
		reservationId = id;
	}

}
