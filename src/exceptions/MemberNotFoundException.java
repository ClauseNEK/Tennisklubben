package exceptions;
/**
 * Exception der bruges, når et medlem ikke kan findes.
 */
public class MemberNotFoundException extends RuntimeException {
    /**
     * Opretter en ny member-not-found exception med en fejlbesked.
     * @param message beskeden der forklarer fejlen
     */
    public MemberNotFoundException(String message) {
        super(message);
    }
}
