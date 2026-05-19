package file;

import exceptions.CsvFileException;
import model.*;

import java.io.*;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;

/**
 * Håndterer læsning og skrivning af turneringer til CSV-filen Tournaments.
 * Klassen gemmer turneringer i tournamentList, så de kan bruges i programmet.
 */
public class FileHandlerTournaments {

    final static String tournaments = "src/csv/Tournaments";
    public static ArrayList<Tournament> tournamentList = new ArrayList<>();

    // Skriver arraylisten til csv filen "Tournaments"
    public void writeToFile() {
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(tournaments))) {
            for (Tournament tournament : tournamentList) {
                bufferedWriter.write(tournament.toTournamentCSV());
                bufferedWriter.newLine();
            }
        } catch (IOException e) {
            throw new CsvFileException("Kunne ikke skrive til turneringsfilen: " + e.getMessage());
        }
    }

    // Læser CSV filen "Tournaments"
    /**
     * Læser turneringer fra CSV-filen Tournaments og tilføjer dem til tournamentList.
     * Metoden læser også deltager-ID'er, hvis de findes på linjen.
     * @throws CsvFileException hvis filen ikke kan læses, eller hvis en linje har ugyldige data
     */
    public void readCSV() {
        try (BufferedReader reader = new BufferedReader(new FileReader(tournaments))) {
            String line;
            int linenumber = 0;
            while ((line = reader.readLine()) != null) {
                linenumber++;

                if (line.trim().isEmpty()) continue;  // spring tomme linjer over

                String[] parts = line.split(",");

                if (parts.length < 5) {
                    throw new CsvFileException("Forkert antal felter på linje " + linenumber);
                }

                try {
                    int tournamentId = Integer.parseInt(parts[0]);
                    String name = parts[1];
                    LocalDate date = LocalDate.parse(parts[2]);
                    Disciplin disciplin = Disciplin.valueOf(parts[3]);
                    GameCategory gameCategory = GameCategory.valueOf(parts[4]);

                    // Deltagere: pipe-separeret felt, kan være tomt
                    List<Integer> participantIds = new ArrayList<>();
                    if (parts.length >= 6 && !parts[5].isEmpty()) {
                        for (String idStr : parts[5].split("\\|")) {
                            participantIds.add(Integer.parseInt(idStr));
                        }
                    }

                    tournamentList.add(new Tournament(tournamentId, name, date,
                            disciplin, gameCategory, participantIds));

                } catch (IllegalArgumentException | DateTimeParseException e) {
                    throw new CsvFileException(
                            "Kunne ikke parse linje " + linenumber + ": " + e.getMessage());
                }
            }
        } catch (FileNotFoundException e) {
            throw new exceptions.FileNotFoundException("Filen kunne læses til");
        } catch (IOException e) {
            throw new CsvFileException("Kunne ikke læse turneringsfilen: " + e.getMessage());
        }
    }

    /**
     * Tilføjer en turnering til tournamentList og gemmer derefter listen i CSV-filen.
     * @param tournament turneringen der skal tilføjes og gemmes
     */
    public void addTournamentToFile(Tournament tournament) {
        tournamentList.add(tournament);
        writeToFile();
    }
}