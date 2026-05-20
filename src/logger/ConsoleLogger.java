package logger;

import ui.ConsoleStyle;

public class ConsoleLogger implements Logger {

    /**
     * Opretter en besked med en bekræftelse.
     * @param message Tekst (String) beskeden.
     */
    @Override
    public void confirmed(String message) {
        System.out.println(ConsoleStyle.GREEN + "[CONFIRMED] " + message + ConsoleStyle.RESET);
    }

    /**
     * Opretter en besked med en advarsel.
     * @param message Tekst (String) beskeden.
     */
    @Override
    public void warning(String message) {
        System.out.println(ConsoleStyle.RED + "[WARNING] " + message + ConsoleStyle.RESET);
    }
}
