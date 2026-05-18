package exceptions;
/**
 * Exception der bruges, når et medlems alder ikke er gyldig.
 */
public class InvalidAgeException extends RuntimeException {
    /**
     * Opretter en ny invalid-age exception med en fejlbesked.
     * @param message beskeden der forklarer fejlen
     */
    public InvalidAgeException(String message) {
        super(message);
    }
}
