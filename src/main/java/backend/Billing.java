package backend;

import java.math.BigDecimal;
import java.util.Date;

/**
 * Stores customer billing information and interfaces with the database
 * @author Arie Geiger, Jose Cortes, Kyle Cushing, Erik Zeladita
 * @version 11/21/2023
 */
public class Billing {
    private int id;
    private String cardNumber;
    private String cardExpiration;// Probably should be date instead of string
    private String cvcNumber;
    private String nameOnCard;
    private String cardType;
    private String zipCode;
    private BigDecimal amount;
    private Date effective;

     /**
     * Create Billing object, associated with billing entry in the database
     * @param id             id of billing
     * @param cardNumber     number on card
     * @param cardExpiration card expiration date
     * @param cvcNumber      security code
     * @param nameOnCard     name associated with card
     * @param cardType       brand of card
     * @param zipCode        postal code
     * @param amount         dollar ammount that will be charged
     * @param effective      date/time of charging
     */
    public Billing(int id, String cardNumber, String cardExpiration, String cvcNumber, String nameOnCard, String cardType, String zipCode, BigDecimal amount, Date effective) {
        this.cardNumber = cardNumber;
        this.cardExpiration = cardExpiration;
        this.cvcNumber = cvcNumber;
        this.nameOnCard = nameOnCard;
        this.cardType = cardType;
        this.zipCode = zipCode;
        this.amount = amount;
        this.effective = effective;
        this.id = id;
    }
     /**
     * Retrieves card number on the card
     * @return The card n
     */
    public String getCardNumber() {
        return cardNumber;
    }
     /**
     * Retrieves card expiration date on the card
     * @return The card expiration
     */
    public String getcardExpiration() {
        return cardExpiration;
    }

     /**
     * Retrieves the card verifivation code
     * @return The cvc number
     */
    public String getCvcNumber() {
        return cvcNumber;
    }

     /**
     * Retrieves the name on the card
     * @return The name on card
     */
    public String getNameOnCard() {
        return nameOnCard;
    }

     /**
     * Retrieves the type of card
     * @return The card type
     */
    public String getCardType() {
        return cardType;
    }

     /**
     * Retrieves customer's billing zip code
     * @return The zip code
     */
    public String getZipCode() {
        return zipCode;
    }

     /**
     * Retrieves identifier for the billing entry
     * @return The Id
     */
    public int getId() {
        return id;
    }

    /**
     * Retrieves price that will be billed
     * @return The billing amount
     */
    public BigDecimal getAmount() {
        return amount;
    }

    /**
     * Retrieves effective date for the billing
     * @return The date that the billing wil be charged
     */
    public Date getEffective() {
        return effective;
    }
}
