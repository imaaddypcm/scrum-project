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

    public Customer(int id, String fN, String lN, String pN, String eM, String addr){
        firstName = fN;
        lastName = lN;
        phoneNumber = pN;
        email = eM;
        address = addr;
        this.id = id;
    }

    public int getId() {
        return id;
    }
}
