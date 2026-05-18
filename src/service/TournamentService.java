package service;

import exceptions.MemberNotFoundException;
import exceptions.TournamentNotFoundException;
import file.FileHandlerTournaments;
import logger.ConsoleLogger;
import logger.Logger;
import model.*;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

import static file.FileHandlerTournaments.tournamentList;

public class TournamentService {
    private static FileHandlerTournaments fileHandlerTournaments = new FileHandlerTournaments();
    private static Logger logger = new ConsoleLogger();


    /**
     Opretter en ny turnering ud fra brugerens indtastninger og gemmer den til Tournaments.csv.
     @param scanner Scanner bruges til at indtaste turneringens stamoplysninger.
     */

    public static void createTournament(Scanner scanner) {
        System.out.print("Navn på turneringen: ");
        String name = scanner.nextLine();

        LocalDate date = chooseDate(scanner);
        Disciplin disciplin = MemberService.chooseDisciplin(scanner);
        GameCategory gameCategory = MemberService.chooseCategory(scanner);

        Tournament tournament = new Tournament(name, date, disciplin, gameCategory);
        fileHandlerTournaments.addTournamentToFile(tournament);

        logger.confirmed("\n\033[3mTurnering oprettet\033[0m");
    }

    /**
     * Beder brugeren om en dato i ISO-format og bliver ved indtil datoen er gyldig.
     * @param scanner Scanner bruges til at indtaste datoen.
     * @return Datoen som LocalDate.
     */
    public static LocalDate chooseDate(Scanner scanner) {
        while (true) {
            System.out.print("Indtast dato for turneringen (yyyy-mm-dd): ");
            String input = scanner.nextLine();
            try {
                return LocalDate.parse(input);
            } catch (DateTimeParseException e) {
                System.out.println("Forkert datoformat. Brug (yyyy-mm-dd):");
            }
        }
    }

    /**
     * Printer alle turneringer i listen. Hvis listen er tom gives en besked om det.
     */
    public static void listTournaments() {
        if (tournamentList.isEmpty()) {
            System.out.println("Der er ingen turneringer endnu.");
        } else {
            for (Tournament tournament : tournamentList) {
                System.out.println(tournament);
            }
        }
    }

    /**
     * Går igennem turneringslisten og finder den turnering der matcher det indtastede turnerings-ID.
     * @param tournamentId Turneringens ID nummer.
     * @return Turneringen der matcher ID'et.
     */
    public static Tournament findTournament(int tournamentId) {
        for (Tournament tournament : tournamentList) {
            if (tournament.getTournamentId() == tournamentId) {
                return tournament;
            }
        }
        throw new TournamentNotFoundException("Ingen turnering fundet med ID " + tournamentId);
    }

    /**
     * Tilmelder et eksisterende medlem til en eksisterende turnering.
     * Både turnerings-ID og medlemsID valideres, så man ikke kan tilmelde et ikke-eksisterende medlem.
     * @param scanner Scanner bruges til at indtaste turnerings-ID og medlemsID.
     */
    public static void addParticipant(Scanner scanner) {
        listTournaments();
        System.out.print("Indtast turnerings-ID: ");
        int tournamentId = scanner.nextInt();
        scanner.nextLine();

        System.out.print("Indtast medlemsID der skal tilmeldes: ");
        int memberId = scanner.nextInt();
        scanner.nextLine();

        try {
            Tournament tournament = findTournament(tournamentId);
            Member member = MemberService.seachForMember(memberId); // kaster MemberNotFoundException

            tournament.addParticipant(member.getMemberid());
            fileHandlerTournaments.writeToFile();
            logger.confirmed("\n\033[3m" + member.getName()
                    + " er tilmeldt " + tournament.getName() + "\033[0m");
        } catch (TournamentNotFoundException | MemberNotFoundException e) {
            System.out.println("Fejl: " + e.getMessage());
        }
    }

    /**
     * Fjerner et medlem fra turnering.
     * @param scanner Scanner bruges til at indtaste turnerings-ID og medlemsID.
     */
    public static void removeParticipant(Scanner scanner) {
        listTournaments();
        System.out.print("Indtast turnerings-ID: ");
        int tournamentId = scanner.nextInt();
        scanner.nextLine();

        System.out.print("Indtast medlemsID der skal afmeldes: ");
        int memberId = scanner.nextInt();
        scanner.nextLine();

        try {
            Tournament tournament = findTournament(tournamentId);
            tournament.removeParticipant(memberId);
            fileHandlerTournaments.writeToFile();
            logger.confirmed("\n\033[3mMedlem afmeldt\033[0m");
        } catch (TournamentNotFoundException e) {
            System.out.println("Fejl: " + e.getMessage());
        }
    }

    /**
     * Viser deltagerne i en turnering med navn og medlemsID i stedet for kun ID.
     * @param scanner Scanner bruges til at indtaste turnerings-ID.
     */
    public static void showParticipants(Scanner scanner) {
        listTournaments();
        System.out.print("Indtast turnerings-ID: ");
        int tournamentId = scanner.nextInt();
        scanner.nextLine();

        try {
            Tournament tournament = findTournament(tournamentId);
            if (tournament.getParticipantIds().isEmpty()) {
                System.out.println("Ingen deltagere tilmeldt endnu.");
                return;
            }
            System.out.println("Deltagere i " + tournament.getName() + ":");
            for (int memberId : tournament.getParticipantIds()) {
                try {
                    Member member = MemberService.seachForMember(memberId);
                    System.out.println(" - " + member.getName() + " (ID " + memberId + ")");
                } catch (MemberNotFoundException e) {
                    // Medlemmet er blevet slettet siden tilmeldingen
                    System.out.println(" - [Ukendt medlem, ID " + memberId + "]");
                }
            }
        } catch (TournamentNotFoundException e) {
            System.out.println("Fejl: " + e.getMessage());
        }
    }

    /**
     * Sletter en turnering fra listen og opdaterer Tournaments.csv.
     * @param scanner Scanner bruges til at indtaste turnerings-ID.
     */
    public static void removeTournament(Scanner scanner) {
        listTournaments();
        System.out.print("Indtast turnerings-ID på den turnering du vil slette: ");
        int tournamentId = scanner.nextInt();
        scanner.nextLine();

        try {
            Tournament tournament = findTournament(tournamentId);
            tournamentList.remove(tournament);
            fileHandlerTournaments.writeToFile();
            System.out.println("Turneringen er nu slettet.");
        } catch (TournamentNotFoundException e) {
            System.out.println("Fejl: " + e.getMessage());
        }
    }
}