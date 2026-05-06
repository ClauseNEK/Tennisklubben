package exceptions;

public class CsvFileException extends RuntimeException {
    public CsvFileException(String message) {
        super(message);
    }
}
