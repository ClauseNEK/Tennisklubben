package ui;

import java.util.Scanner;

public class SmashUI {

    Scanner scanner = new Scanner(System.in);   // Scanner objekt

    /**
     * Metoden der starter vores program og fremviser de muligheder/roller, som brugeren kan vælge.
     */
    public void start(){
        boolean running = true;

        while (running){
            System.out.println();
            ConsoleStyle.printTitle("VÆLG DIN ROLLE");
            ConsoleStyle.printMenuLine(ConsoleStyle.BLUE, 1, "Formand", "👔");
            ConsoleStyle.printMenuLine(ConsoleStyle.YELLOW, 2, "Kasserer", "💰");
            ConsoleStyle.printMenuLine(ConsoleStyle.GREEN, 3, "Coach", "🎾");
            ConsoleStyle.printMenuLine(ConsoleStyle.GREY, 4, "Luk program", "\uD83D\uDD1A");
            System.out.print(ConsoleStyle.WHITE + "> " + ConsoleStyle.RESET);
            try{
                int input = Integer.parseInt(scanner.nextLine());

                switch (input){
                    case 1:
                        ChairmanUI.showChairman();
                        break;
                    case 2:
                        CashierUI.showCashier();
                        break;
                    case 3:
                        CoachUI.showCoach();
                        break;
                    case 4:
                        scanner.close();
                        running = false;
                        break;
                    default:
                        ConsoleStyle.printError("Fejl valg: Vælg et tal mellem 1-4");
                }
            } catch (Exception e){
                throw new RuntimeException(e);
            }
        }
    }
}
