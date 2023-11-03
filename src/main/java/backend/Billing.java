package backend;

public class Billing {
    private int id;
    private String cardNumber;
    private String cardExpiration;
    private String ccvNumber;
    private String nameOnCard;
    private String cardType;
    private String zipCode;

    public Billing(int id, String cardNumber, String cardExpiration, String ccvNumber, String nameOnCard, String cardType, String zipCode) {
        this.cardNumber = cardNumber;
        this.cardExpiration = cardExpiration;
        this.ccvNumber = ccvNumber;
        this.nameOnCard = nameOnCard;
        this.cardType = cardType;
        this.zipCode = zipCode;
        this.id = id;
    }

    public int getId() {
        return id;
    }
}
