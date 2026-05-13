package ui;

import service.CoachService;
import java.util.Scanner;
public class CoachUI {

    /*
    Coachen skal muligvis have en mulighed for at tilføje medlemmers disciplin så formanden opretter medlememt men
    Coachen skal ku tildele disciplinen til medlemmet (Så det ikke sker hos formanden men hos coachen)
    KIG PÅ SENERE
     */

    public static void showCoach() {
        Scanner scanner = new Scanner(System.in);
        boolean running = true;

        while (running) {
            System.out.println("\n === Coach ===");
            System.out.println("1. Top 5 (Disciplin)");
            System.out.println("2. Top 5 (Træningsresultat)");
            System.out.println("3. Gå tilbage");

            int input = Integer.parseInt(scanner.nextLine());

            switch (input) {
                case 1:
                    CoachService.showTop5ByDiscipline();
                    break;
                case 2:
                    CoachService.showTop5ByTrainingResult();
                    break;
                case 3:
                    running = false;
                    break;
                default:
                    System.out.println("Ugyldigt valg. Vælg 1-3.");
                    break;

            }

        }
    }
}