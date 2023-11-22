package backend;
/**
 * Stores customer information
 * @author Arie Geiger, Jose Cortes, Kyle Cushing, Erik Zeladita
 * @version Nov 21, 2023
 */
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

    /**
     * Create customer object, associated with customer entry in the database
     * @param id            id of customer
     * @param firstName     first name of customer
     * @param lastName      last name of customer
     * @param phoneNumber   phone number of customer
     * @param email         email of customer
     * @param address       address of customer
     */
    public Customer(int id, String firstName, String lastName, String phoneNumber, String email, String address){
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.address = address;
        this.id = id;
    }

    /**
     * Get ID of customer
     * @return ID of customer
     */
    public int getId() {
        return id;
    }
    /**
     * Get first name of customer
     * @return Returns first name represented as a string
     */
    public String getFirstName() {
        return firstName;
    }
    /**
     * Get last name of customer
     * @return Returns last name represented as a string
     */
    public String getLastName() {
        return lastName;
    }
    /**
     * Get phone number of customer
     * @return Returns phone number represented as a string
     */
    public String getPhoneNumber() {
        return phoneNumber;
    }
    /**
     * Get email of customer
     * @return Returns email represented as a string
     */
    public String getEmail() {
        return email;
    }
    /**
     * Get country of customer
     * @return Returns country represented as a string
     */
    public String getCountry() {
        return country;
    }
     /**
     * Get state of customer
     * @return Returns state represented as a string
     */
    public String getState() {
        return state;
    }
    /**
     * Get city of customer
     * @return Returns city represented as a string
     */
    public String getCity() {
        return city;
    }
     /**
     * Get address of customer
     * @return Returns address represented as a string
     */
    public String getAddress() {
        return address;
    }
     /**
     * Retrieves customer's billing zip code
     * @return The zip code
     */
    public String getZipCode() {
        return zipcode;
    }
}
