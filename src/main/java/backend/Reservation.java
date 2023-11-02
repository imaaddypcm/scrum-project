package backend;

import java.util.Date;

public class Reservation {
    private int id;
    private Customer customer;
    private Room rooms[];
    private Date startDate;
    private Date endDate;
    private int numberOfGuests;

    public Reservation(int id, Customer customer, Room rooms[], int numberOfGuests, Date start, Date end) {
        this.id = id;
        this.customer = customer;
        this.rooms = rooms;
        this.startDate = start;
        this.endDate = end;
        this.numberOfGuests = numberOfGuests;
    }

    public int getId() {
        return id;
    }

    public Customer getCustomer() {
        return customer;
    }

    public Date getStartDate() {
        return startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public int getNumberOfGuests() {
        return numberOfGuests;
    }

    // Add setters as needed
}
