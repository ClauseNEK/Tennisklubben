package exceptions;

/**
 * Exception der bruges, når der opstår fejl ved læsning eller skrivning af CSV-filer.
 */
public class CsvFileException extends RuntimeException {
    /**
     * Opretter en ny CSV-fil exception med en fejlbesked.
     * @param message beskeden der forklarer fejlen
     */
    public CsvFileException(String message) {
        super(message);
    }
}
