package backend;

public class Customer {
    String firstName;
    String lastName;
    String phoneNumber;
    String email;
    String country;
    String state;
    String city;
    String address;
    String zipcode;
    private int id = 0;

    public Customer(int id, String firstName, String lastName, String phoneNumber, String email, String address){
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.address = address;
        this.id = id;
    }

    public int getId() {
        return id;
    }
}
