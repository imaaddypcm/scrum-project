/**
 * Stores customer information
 * @author Arie Geiger, Jose Cortes, Kyle Cushing, Erik Zeladita
 * @version Nov 21, 2023
 */
package backend;

public class Customer {
    private String firstName;
    private String lastName;
    private String phoneNumber;
    private String email;
    private String country;
    private String state;
    private String city;
    private String address;
    private String zipcode;
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

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getEmail() {
        return email;
    }
    public String getCountry() {
        return country;
    }
    public String getState() {
        return state;
    }
    public String getCity() {
        return city;
    }
    public String getAddress() {
        return address;
    }
    public String getZipCode() {
        return zipcode;
    }
}
