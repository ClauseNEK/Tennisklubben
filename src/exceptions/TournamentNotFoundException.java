package exceptions;
/**
 * Exception der bruges, når en turnering ikke kan findes.
 */
public class TournamentNotFoundException extends RuntimeException {
    /**
     * Opretter en ny tournament-not-found exception med en fejlbesked.
     * @param message beskeden der forklarer fejlen
     */
    public TournamentNotFoundException(String message) {
        super(message);
    }
}