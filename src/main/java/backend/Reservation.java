package backend;
import java.util.Date;

/**
 * Stores details for a given reservation.
 * @author Arie Geiger, Jose Cortes, Kyle Cushing, Erik Zeladita
 * @version Nov 21, 2023
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


	/**
	 * Create reservation object, associated with reservation entry in the database.
	 * @param id               Reservation number.
	 * @param customer         Customer that created reservation.
	 * @param billing          Billing record that will be used to pay for hotel stay.
	 * @param roomType         Room type selected for reservation.
	 * @param numberOfRooms    Number of rooms that have been reserved.
	 * @param numberOfGuests   Number of guests that will stay during reservation.
	 * @param start            Day that reservation begins.
	 * @param end              Day that reservation ends.
	 */
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

	/**
	 * Get id of reservation.
	 * @return id of reservation.
	 */
	public int getId() {
		return id;
	}

	/**
	 * Get start date of reservation.
	 * @return start date of reservation.
	 */
	public Date getStartDate() {
		return startDate;
	}

	/**
	 * Get end date of reservation.
	 * @return end date of reservation.
	 */
	public Date getEndDate() {
		return endDate;
	}

	/**
	 * Get room type of reservation.
	 * @return room type of reservation.
	 */
	public RoomType getRoomType() {
		return roomType;
	}

	/**
	 * Get customer that created reservation.
	 * @return customer that created reservation.
	 */
	public Customer getCustomer() {
		return customer;
	}

	/**
	 * Get billing information used for reservation.
	 * @return billing information used for reservation.
	 */
	public Billing getBilling() {
		return billing;
	}

	/**
	 * Get start date of reservation.
	 * @return start date of reservation.
	 */
	public int getNumberOfRooms() {
		return numberOfRooms;
	}

	/**
	 * Get start date of reservation.
	 * @return start date of reservation.
	 */
	public int getNumberOfGuests() {
		return numberOfGuests;
	}
}
