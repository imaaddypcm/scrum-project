package backend;

public class ReservationOverflowException extends Exception {
    public ReservationOverflowException(String errorMessage) {
        super(errorMessage);
    }
}
