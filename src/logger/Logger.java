package logger;

public interface Logger {
    /**
     * Abstrakt metode der, når implementeret, skal oprette en besked med en bekræftelse.
     * @param message Tekst (String) beskeden.
     */
    void confirmed(String message);

    /**
     * Abstrakt metode der, når implementeret, skal oprette en besked med en advarsel.
     * @param message Tekst (String) beskeden.
     */
    void warning(String message);
}
