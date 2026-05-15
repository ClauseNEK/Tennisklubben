import file.FileHandlerTournaments;
import model.*;

import java.time.LocalDate;

public class TournamentTest {

    public static void main(String[] args) {
        FileHandlerTournaments handler = new FileHandlerTournaments();

        // === Test 1: Opret og gem ===
        Tournament t1 = new Tournament(
                "Sommer Open 2026",
                LocalDate.of(2026, 6, 15),
                Disciplin.SINGLE,
                GameCategory.COMPETITION_PLAYER);
        t1.addParticipant(1);
        t1.addParticipant(2);
        t1.addParticipant(3);

        Tournament t2 = new Tournament(
                "Klub-mesterskab",
                LocalDate.of(2026, 9, 1),
                Disciplin.DOUBLE,
                GameCategory.COMPETITION_PLAYER);
        // bevidst ingen deltagere — test at tomt felt virker

        handler.addTournamentToFile(t1);
        handler.addTournamentToFile(t2);

        System.out.println("Gemt " + FileHandlerTournaments.tournamentList.size() + " turneringer");

        // === Test 2: Ryd listen og læs tilbage fra disk ===
        FileHandlerTournaments.tournamentList.clear();
        System.out.println("Listen ryddet. Størrelse: " + FileHandlerTournaments.tournamentList.size());

        handler.readCSV();
        System.out.println("\nIndlæst fra fil:");
        for (Tournament t : FileHandlerTournaments.tournamentList) {
            System.out.println(t);
        }

        // === Test 3: Tjek at deltagere overlevede round-trippet ===
        Tournament loaded = FileHandlerTournaments.tournamentList.get(0);
        System.out.println("Deltagere i '" + loaded.getName() + "': "
                + loaded.getParticipantIds());

        // === Test 4: Fjern en deltager og gem igen ===
        loaded.removeParticipant(2);
        handler.writeToFile();
        System.out.println("Efter fjernelse af deltager 2: " + loaded.getParticipantIds());
    }
}