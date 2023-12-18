package backend;

/**
 * Exception object for when there are too many overlapping reservations.
 * @author Arie Geiger, Jose Cortes, Kyle Cushing, Erik Zeladita
 * @version Dec 17, 2023
 */
public class ReservationOverflowException extends Exception {
    /**
     * Create ReservationOverflowException object
     * @param errorMessage Message for exception
     */
    public ReservationOverflowException(String errorMessage) {
        super(errorMessage);
    }
}
