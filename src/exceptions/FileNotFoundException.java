package exceptions;

// Tilføjer en simpel metode der kalder hvis enkelte FIL ikke findes så oprettes den

public class FileNotFoundException extends RuntimeException {
    public FileNotFoundException(String message) {
        super(message);
    }
}
