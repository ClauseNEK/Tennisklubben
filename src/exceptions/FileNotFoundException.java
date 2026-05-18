package exceptions;

// Tilføjer en simpel metode der kalder hvis enkelte FIL ikke findes så oprettes den
/**
 * Exception der bruges, når en fil ikke kan findes.
 */
public class FileNotFoundException extends RuntimeException {
    /**
     * Opretter en ny file-not-found exception med en fejlbesked.
     * @param message beskeden der forklarer fejlen
     */
    public FileNotFoundException(String message) {
        super(message);
    }
}
