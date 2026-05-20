package ui;

import logger.ConsoleLogger;
import logger.Logger;
import service.CoachService;
import service.TournamentService;
import java.util.Scanner;

public class CoachUI {
    private static Logger logger = new ConsoleLogger();


    public static void showCoach() {
        Scanner scanner = new Scanner(System.in);
        boolean running = true;

        while (running) {
            System.out.println(ConsoleStyle.GREEN + "\n==== Coach 🎾 ====" + ConsoleStyle.RESET);
            System.out.println("1. Top 5 (Disciplin)");
            System.out.println("2. Top 5 (Træningsresultat)");
            System.out.println("3. Vis træningsresultat for alle konkurrencespillere");
            System.out.println("4. Opdatere hele træningsresultat listen");
            System.out.println("5. Opdatere et enkelt træningsresultat");
            System.out.println("6. Turneringer");
            System.out.println("7. Gå tilbage");

            int input = Integer.parseInt(scanner.nextLine());

            switch (input) {
                case 1:
                    CoachService.printTopFiveDisciplin();
                    break;
                case 2:
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
                    showTournamentMenu(scanner);
                    break;
                case 7:
                    running = false;
                    break;
                default:
                    System.out.println("Ugyldigt. Vælg mellem 1-7.");
                    break;
            }

        }
    }

    /**
     * Undermenu hvor coachen styrer turneringer: oprette, vise, til-/afmelde deltagere og slette.
     * @param scanner Scanner sendes videre fra showCoach() så der kun bruges ét Scanner-objekt.
     */
    private static void showTournamentMenu(Scanner scanner) {
        boolean running = true;

        while (running) {
            System.out.println("\n === Turneringer ===");
            System.out.println("1. Opret turnering");
            System.out.println("2. Vis alle turneringer");
            System.out.println("3. Tilmeld medlem til turnering");
            System.out.println("4. Afmeld medlem fra turnering");
            System.out.println("5. Vis deltagere i en turnering");
            System.out.println("6. Slet turnering");
            System.out.println("7. Gå tilbage");

            int input = Integer.parseInt(scanner.nextLine());

            switch (input) {
                case 1:
                    TournamentService.createTournament(scanner);
                    break;
                case 2:
                    TournamentService.listTournaments();
                    break;
                case 3:
                    TournamentService.addParticipant(scanner);
                    break;
                case 4:
                    TournamentService.removeParticipant(scanner);
                    break;
                case 5:
                    TournamentService.showParticipants(scanner);
                    break;
                case 6:
                    TournamentService.removeTournament(scanner);
                    break;
                case 7:
                    running = false;
                    break;
                default:
                    ConsoleStyle.printError("Ugyldigt valg. Vælg 1-7.");
                    break;
            }
        }
    }
}