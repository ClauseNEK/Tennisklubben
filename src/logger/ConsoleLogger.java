package logger;

public class ConsoleLogger implements Logger {

    /**
     * Opretter en besked med en bekræftelse.
     * @param message Tekst (String) beskeden.
     */
    @Override
    public void confirmed(String message) {
        System.out.println("[CONFIRMED] " + message); //"[CONFIRMED] " i grøn farve?
    }

    /**
     * Opretter en besked med en advarsel.
     * @param message Tekst (String) beskeden.
     */
    @Override
    public void warning(String message) {
        System.out.println("[WARNING] " + message); //"[WARNING] " i rød farve?
    }
}
