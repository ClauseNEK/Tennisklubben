package ui;

import logger.ConsoleLogger;
import logger.Logger;
import service.CoachService;
import java.util.Scanner;

public class CoachUI {
    private static Logger logger = new ConsoleLogger();

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
            System.out.println("3. Hvis træningsresultat for alle konkurrencespillere");
            System.out.println("4. Opdatere hele træningsresultat listen");
            System.out.println("5. Opdatere et enkelt træningsresultat");
            System.out.println("6. Gå tilbage");

            int input = Integer.parseInt(scanner.nextLine());

            switch (input) {
                case 1:
                    //CoachService.showTop5ByDiscipline();
                    CoachService.printTopFiveDisciplin();
                    break;
                case 2:
                    //CoachService.showTop5ByTrainingResult();
                    //CoachService.sortJuniorListByResults();
                    CoachService.printSortedLists();
                    break;
                case 3:
                    CoachService.printResults();
                    break;
                case 4:
                    CoachService.getCompetitionPlayers();
                    logger.confirmed("\n\033[3mListen er opdateret\033[0m");
                    break;
                case 5:
                    CoachService.editCompetitionPlayer(scanner);
                    break;
                case 6:
                    running = false;
                    break;
                default:
                    System.out.println("Ugyldigt valg. Vælg 1-6.");
                    break;

            }

        }
    }
}