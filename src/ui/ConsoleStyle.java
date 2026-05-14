package ui;

public class ConsoleStyle {

    public static final boolean USE_COLOR = true;

    public static final String RESET = USE_COLOR ? "\u001B[0m" : "";
    public static final String BOLD = USE_COLOR ? "\u001B[1m" : "";

    public static final String CYAN = USE_COLOR ? "\u001B[36m" : "";
    public static final String BLUE = USE_COLOR ? "\u001B[34m" : "";
    public static final String YELLOW = USE_COLOR ? "\u001B[33m" : "";
    public static final String GREEN = USE_COLOR ? "\u001B[32m" : "";
    public static final String RED = USE_COLOR ? "\u001B[31m" : "";
    public static final String WHITE = USE_COLOR ? "\u001B[37m" : "";

    private ConsoleStyle() {}

    public static void printTitle(String title) {
        String borderTop = "╔══════════════════════════════╗";
        String borderBottom = "╚══════════════════════════════╝";
        String titleLine = String.format("║ %-28s ║", title);

        System.out.println(CYAN + BOLD + borderTop + RESET);
        System.out.println(CYAN + BOLD + titleLine + RESET);
        System.out.println(CYAN + BOLD + borderBottom + RESET);
    }

    public static void printMenuLine(String color, int number, String text, String icon) {
        String line = String.format("%d. %-25s %s", number, text, icon);
        System.out.println(color + line + RESET);
    }

    public static void printError(String message) {
        System.out.println(RED + BOLD + message + RESET);
    }
}