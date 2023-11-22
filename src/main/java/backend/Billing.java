/**
 * Stores customer billing information and interfaces with the database
 * @author Arie Geiger, Jose Cortes, Kyle Cushing, Erik Zeladita
 * @version Nov 21, 2023
 */
package backend;

public class Billing {
    private int id;
    private String cardNumber;
    private String cardExpiration;// Probably should be date instead of string
    private String cvcNumber;
    private String nameOnCard;
    private String cardType;
    private String zipCode;

    public Billing(int id, String cardNumber, String cardExpiration, String cvcNumber, String nameOnCard, String cardType, String zipCode) {
        this.cardNumber = cardNumber;
        this.cardExpiration = cardExpiration;
        this.cvcNumber = cvcNumber;
        this.nameOnCard = nameOnCard;
        this.cardType = cardType;
        this.zipCode = zipCode;
        this.id = id;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public String getcardExpiration() {
        return cardExpiration;
    }

    public String getCvcNumber() {
        return cvcNumber;
    }

    public String getNameOnCard() {
        return nameOnCard;
    }

    public String getCardType() {
        return cardType;
    }

    public String getZipCode() {
        return zipCode;
    }

    public int getId() {
        return id;
    }
}
