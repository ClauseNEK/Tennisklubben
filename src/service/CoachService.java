package service;

import exceptions.MemberNotFoundException;
import file.FileHandlerTrainingResults;
import logger.ConsoleLogger;
import logger.Logger;
import model.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

import static file.FileHandlerMembers.memberList;
import static file.FileHandlerTrainingResults.trainingResultList;

public class CoachService {
    private static Scanner scanner = new Scanner(System.in);
    private static FileHandlerTrainingResults handlerTrainingResults = new FileHandlerTrainingResults();
    private static Logger logger = new ConsoleLogger();

    public static ArrayList<CompetitionPlayer> juniorSingleList = new ArrayList<>();
    public static ArrayList<CompetitionPlayer> juniorDoubleList = new ArrayList<>();
    public static ArrayList<CompetitionPlayer> juniorMixedDoubleList = new ArrayList<>();

    public static ArrayList<CompetitionPlayer> seniorSingleList = new ArrayList<>();
    public static ArrayList<CompetitionPlayer> seniorDoubleList = new ArrayList<>();
    public static ArrayList<CompetitionPlayer> seniorMixedDoubleList = new ArrayList<>();

    public static ArrayList<CompetitionPlayer> allJunior = new ArrayList<>();
    public static ArrayList<CompetitionPlayer> allSenior = new ArrayList<>();


    /**
     * Går igennem listen af træningsresultater for alle konkurrencespillerne og finder alle juniorspillerne
     * og føjer dem til en liste, samt en liste for hver deres disciplin.
     */
    public static void getJuniorList() {
        for (CompetitionPlayer competitionPlayer : trainingResultList) {
            if (competitionPlayer.getMemberType().equalsIgnoreCase("Juniorspiller")) {
                if (competitionPlayer.getDisciplin().equals(Disciplin.SINGLE)) {
                    juniorSingleList.add(competitionPlayer);
                    allJunior.add(competitionPlayer);
                } else if (competitionPlayer.getDisciplin().equals(Disciplin.DOUBLE)) {
                    juniorDoubleList.add(competitionPlayer);
                    allJunior.add(competitionPlayer);
                } else {
                    juniorMixedDoubleList.add(competitionPlayer);
                    allJunior.add(competitionPlayer);
                }
            }
        }
    }


    /**
     * Går igennem listen af træningsresultater for alle konkurrencespillerne og finder alle seniorspillerne
     * og føjer dem til en liste, samt en liste for hver deres disciplin.
     */
    public static void getSeniorList() {
        for (CompetitionPlayer competitionplayer : trainingResultList) {
            if (competitionplayer.getMemberType().equalsIgnoreCase("Seniorspiller")) {
                if(competitionplayer.getDisciplin().equals(Disciplin.SINGLE)) {
                    seniorSingleList.add(competitionplayer);
                    allSenior.add(competitionplayer);
                } else if (competitionplayer.getDisciplin().equals(Disciplin.DOUBLE)) {
                    seniorDoubleList.add(competitionplayer);
                    allSenior.add(competitionplayer);
                } else {
                    seniorMixedDoubleList.add(competitionplayer);
                    allSenior.add(competitionplayer);
                }
            }
        }
    }


    /**
     * Tager en liste af konkurrencespiller, sorter dem efter deres træningsresultat og printer de fem bedste.
     * @param playerList En ArrayList af konkurrencespiller, som enten er junior eller senior.
     */
    public static void sortListByResults(ArrayList<CompetitionPlayer> playerList) {
        if(playerList.isEmpty()) {
            System.out.println("Listen er tom.");
        } else {
            Collections.sort(playerList, Collections.reverseOrder());
            for(int i = 0; i < 5 && i < playerList.size(); i++) {
                System.out.println(playerList.get(i));
            }
        }
    }

    /**
     * Kalder sortListByResults() og printer de fem bedste resultater fra junior og senior.
     */
    public static void printSortedLists() {
        allJunior.removeAll(allJunior);
        allSenior.removeAll(allSenior);

        getJuniorList();
        getSeniorList();

        System.out.println("Top 5 bedste træningsresultat for Juniorspillere:");
        sortListByResults(allJunior);
        System.out.println("\nTop 5 bedste træningsresultat for Seniorspillere:");
        sortListByResults(allSenior);
    }

    /**
     * Kalder sortListByResults() og printer de fem bedste resultater fra junior og senior for hver disciplin.
     */
    public static void printTopFiveDisciplin() {
        juniorSingleList.removeAll(juniorSingleList);
        juniorDoubleList.removeAll(juniorDoubleList);
        juniorMixedDoubleList.removeAll(juniorMixedDoubleList);

        seniorSingleList.removeAll(seniorSingleList);
        seniorDoubleList.removeAll(seniorDoubleList);
        seniorMixedDoubleList.removeAll(seniorMixedDoubleList);

        getJuniorList();
        getSeniorList();

        System.out.println("Top 5 Juniorspillere i disciplinen SINGLE:");
        sortListByResults(juniorSingleList);
        System.out.println("\nTop 5 Juniorspillere i disciplinen DOUBLE:");
        sortListByResults(juniorDoubleList);
        System.out.println("\nTop 5 Juniorspillere i disciplinen MIXED DOUBLE:");
        sortListByResults(juniorMixedDoubleList);

        System.out.println("\n\nTop 5 Seniorspillere i disciplinen SINGLE:");
        sortListByResults(seniorSingleList);
        System.out.println("\nTop 5 Seniorspillere i disciplinen DOUBLE:");
        sortListByResults(seniorDoubleList);
        System.out.println("\nTop 5 Seniorspillere i disciplinen MIXED DOUBLE:");
        sortListByResults(seniorMixedDoubleList);
    }


    public static void showTop5ByDiscipline() {
        System.out.println("Top 5 efter disciplin:");

        for (Member member : memberList) {
            System.out.println(
                    member.getMemberid() + " - " +
                            member.getName() + " - " +
                            member.getDisciplin()
            );
        }
    }

    public static void showTop5ByTrainingResult() {
        System.out.println("Top 5 efter træningsresultat:");

        for (Member member : memberList) {
            System.out.println(
                    member.getMemberid() + " - " +
                            member.getName()
            );
        }
    }


    /**
     * Går igennem medlemslisten, finder alle de aktive konkurrencespillere og kalder addCompetitionPlayerToCSV(),
     * der skriver dem til training_results.csv.
     */
    public static void getCompetitionPlayers() {
        trainingResultList.removeAll(trainingResultList);
        for(Member member : memberList) {
            if (member.membership()) { //Tjekker om medlemmet er aktivt
                if(member.getGameCategory().equals(GameCategory.COMPETITION_PLAYER)) {

                    Disciplin disciplin = member.getDisciplin();

                    System.out.print("Indtast " + member.getName() + "s bedste træningsresultat: ");
                    int result = scanner.nextInt();

                    scanner.nextLine();

                    String date = chooseDate(scanner);

                    CompetitionPlayer competitionPlayer = new CompetitionPlayer(member.getMemberid(), member.getMemberType(), disciplin, result, date);
                    addCompetitionPlayerToCSV(competitionPlayer);
                }
            }
        }
    }

    /**
     * Datoen for træningsresultatet indtastet og metoden tjekker at datoen er 10 char lang.
     * @param scanner Scanner bruges til at gemme datoen
     * @return Datoen som en String (dd-mm-yyyy)
     */
    public static String chooseDate(Scanner scanner) {
        String date;
        while (true) {
            System.out.print("Indtast datoen for dette resultat (dd-mm-yyyy): ");
            if(scanner.hasNext()) {
                date = scanner.nextLine();
                if(date.length() != 10) {
                    System.out.println("Forkert indtastning. Prøv igen.");
                } else {
                    return date;
                }
            }
        }
    }

    /**
     * Får en konkurrencespiller og føjer dem til ArrayListen "trainingResultList", samt skriver dem til CSV filen
     * @param competitionPlayer En konkurrencespiller
     */
    public static void addCompetitionPlayerToCSV(CompetitionPlayer competitionPlayer) {
        trainingResultList.add(competitionPlayer);

        handlerTrainingResults.writeToTrainingResultsFile();
    }


    public void addResultAndDateToMember() {

    }

    /**
     * Giver brugeren lov til at indskrive et nyt træningsresultat på en konkurrencespiller, samt datoen på resultatet.
     * Den nye data skrives til csv filen "trainingResultList".
     * @param scanner Der bruges en Scanner til at gemme de nye værdier, samt at finde den ønskede konkurrencespiller,
     *                ud fra det indtastet medlemsID.
     */
    public static void editCompetitionPlayer(Scanner scanner) {
        printResults();
        System.out.print("Indtast medlemsID på den konkurrencespiller du gerne vil ændre: ");
        int findID = scanner.nextInt();
        CompetitionPlayer player = findCompetitionPlayer(findID);

        System.out.print("Indtast det nye bedste træningsresultat: ");
        int result = scanner.nextInt();
        player.setTrainingResult(result);

        scanner.nextLine();

        String date = chooseDate(scanner);
        player.setDate(date);

        logger.confirmed("\n\033[3mResultat og dato gemt\033[0m");

        handlerTrainingResults.writeToTrainingResultsFile();
    }

    /**
     * Går igennem listen af konkurrencespiller og finder den spiller der matcher det indtastet medlemsID.
     * @param memberID MedlemsID'et (int), der bruges til at finde den ønskede spiller.
     * @return Konkurrencespilleren der matcher det indtastet medlemsID.
     */
    public static CompetitionPlayer findCompetitionPlayer(int memberID) {
        for (CompetitionPlayer competitionPlayer : trainingResultList) {
            if (memberID == competitionPlayer.getMemberID()) {
                return competitionPlayer;
            }
        }
        throw new MemberNotFoundException("Intet medlem fundet med ID " + memberID);
    }

    /**
     * Tjekker om listen er tom og hvis ikke, så printes hver konkurrencespiller i listen ud.
     * @param trainingList En ArrayList af konkurrencespiller.
     */
    public static void showTrainingResults(ArrayList<CompetitionPlayer> trainingList) {
        if(trainingList.isEmpty()) {
            System.out.println("Listen er tom");
        } else {
            for (CompetitionPlayer competitionPlayer : trainingList) {
                System.out.println(competitionPlayer);
            }
        }

    }

    /**
     * Kalder showTrainingResults() og printer ArrayListen "trainingResultList".
     */
    public static void printResults() {
        showTrainingResults(trainingResultList);
        handlerTrainingResults.writeToTrainingResultsFile();
    }

}