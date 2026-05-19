package file;

import exceptions.CsvFileException;
import exceptions.InvalidAgeException;
import model.*;
import java.io.*;
import java.util.ArrayList;
import java.io.IOException;

/**
 * Håndterer læsning og skrivning af medlemmer til CSV-filen Members.
 * Klassen gemmer medlemmer i en fælles liste, som kan bruges af resten af programmet.
 */
public class FileHandlerMembers {

    final static String members = "src/csv/Members";
    public static ArrayList<Member> memberList = new ArrayList<>();


        //Skriver arraylisten til csv filen "members"
    // Tilføjet: Exception
    // Rettet: BufferedWriter oppe i paramteren)

    /**
     * Skriver alle medlemmer fra memberList til CSV-filen Members.
     * Hvert medlem konverteres til CSV-format med metoden toMemberCSV().
     * @throws CsvFileException hvis der opstår en fejl under skrivning til filen
     */
    public void writeToFile() {
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(members))) {
            for (Member member : memberList) {
                bufferedWriter.write(member.toMemberCSV());
                bufferedWriter.newLine();
            }
        } catch (IOException e) {
            throw new CsvFileException("Kunne ikke skrive til medlemsfilen: " + e.getMessage());
        }
    }

    /**
     * Læser medlemmer fra CSV-filen Members og tilføjer dem til memberList.
     * Metoden opretter Junior- eller Senior-objekter ud fra medlemmets alder.
     * @throws CsvFileException hvis filen ikke kan læses, eller hvis en linje har ugyldige data
     */
    public void readCSV() {

        try (BufferedReader reader = new BufferedReader(new FileReader(members))) {
            String line;
            int linenumber = 0;
            while ((line = reader.readLine()) != null) {
                linenumber++;
                String[] parts = line.split(",");

                if (parts.length < 6) {
                    throw new CsvFileException("Forkerte antal felter på linjer" + linenumber);
                }


                try {
                    String name = parts[0];
                    int age = Integer.parseInt(parts[1]);

                    if (age < 0 || age > 120) {
                        throw new InvalidAgeException(
                                "Ugyldig alder (" + age + ") på linje " + linenumber);
                    }

                    boolean activeMembership = parts[2].equalsIgnoreCase("aktiv");
                    Disciplin disciplin = Disciplin.valueOf(parts[4]);
                    GameCategory gameCategory = GameCategory.valueOf(parts[5]);

                    if (age < 18) {
                        memberList.add(new Junior(name, age, activeMembership, disciplin, gameCategory));
                    } else {
                        memberList.add(new Senior(name, age, activeMembership, disciplin, gameCategory));
                    }
                } catch (IllegalArgumentException e) {
                    // IllegalArgumentException dækker også Disciplin.valueOf/GameCategory med value.of
                    throw new CsvFileException("Kunne ikke parse linje " + linenumber + ": " + e.getMessage());
                }
            }
        } catch (IOException e) {
            throw new CsvFileException("Kunne ikke læse medlemsfilen: " + e.getMessage());
        }
    }

    //Tilføjer et medlem til ArrayListen og skriver den til CVS filen
    public void addMemberToFile(Member member) {
        memberList.add(member);
        writeToFile();
    }


}
