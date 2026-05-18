package file;

import exceptions.CsvFileException;
import model.CompetitionPlayer;
import model.Disciplin;

import java.io.*;
import java.util.ArrayList;


public class FileHandlerTrainingResults {

    final static String training_results = "src/csv/training_results";

    public static ArrayList<CompetitionPlayer> trainingResultList = new ArrayList<>();

    public static ArrayList<CompetitionPlayer> juniorSingleList = new ArrayList<>();
    public static ArrayList<CompetitionPlayer> juniorDoubleList = new ArrayList<>();
    public static ArrayList<CompetitionPlayer> juniorMixedDoubleList = new ArrayList<>();

    public static ArrayList<CompetitionPlayer> seniorSingleList = new ArrayList<>();
    public static ArrayList<CompetitionPlayer> seniorDoubleList = new ArrayList<>();
    public static ArrayList<CompetitionPlayer> seniorMixedDoubleList = new ArrayList<>();

    public static ArrayList<CompetitionPlayer> allJunior = new ArrayList<>();
    public static ArrayList<CompetitionPlayer> allSenior = new ArrayList<>();


    /**
     * Læser csv-filen "training_results" og gemmer den eksisterende data i diverse ArrayLister af konkurrencespiller,
     * som skal bruges i klassen "CoachService".
     */
    public void readTrainingResultsCSV() {
        try(BufferedReader reader = new BufferedReader(new FileReader(training_results))) {
            String line;
            int linenumber = 0;

            while((line = reader.readLine()) != null) {
                linenumber++;
                String[] parts = line.split(",");

                if (parts.length < 4) {
                    throw new CsvFileException("Forkerte antal felter på linjer" + linenumber);
                }

                try {
                    int memberID = Integer.parseInt(parts[0]);
                    String memberType = parts[1];
                    Disciplin disciplin = Disciplin.valueOf(parts[2]);
                    int trainingResult = Integer.parseInt(parts[3]);
                    String date = parts[4];


                    if (memberType.equalsIgnoreCase("Juniorspiller")) {
                        if(disciplin.equals(Disciplin.SINGLE)) {
                            CompetitionPlayer juniorSingle = new CompetitionPlayer(memberID, memberType, disciplin, trainingResult, date);
                            juniorSingleList.add(juniorSingle);
                            allJunior.add(juniorSingle);
                        } else if (disciplin.equals(Disciplin.DOUBLE)) {
                            CompetitionPlayer juniorDouble = new CompetitionPlayer(memberID, memberType, disciplin, trainingResult, date);
                            juniorDoubleList.add(juniorDouble);
                            allJunior.add(juniorDouble);
                        } else {
                            CompetitionPlayer juniorMixedDouble = new CompetitionPlayer(memberID, memberType, disciplin, trainingResult, date);
                            juniorMixedDoubleList.add(juniorMixedDouble);
                            allJunior.add(juniorMixedDouble);
                        }
                    } else {
                        if(disciplin.equals(Disciplin.SINGLE)) {
                            CompetitionPlayer seniorSingle = new CompetitionPlayer(memberID, memberType, disciplin, trainingResult, date);
                            seniorSingleList.add(seniorSingle);
                            allSenior.add(seniorSingle);
                        } else if (disciplin.equals(Disciplin.DOUBLE)) {
                            CompetitionPlayer seniorDouble = new CompetitionPlayer(memberID, memberType, disciplin, trainingResult, date);
                            seniorDoubleList.add(seniorDouble);
                            allSenior.add(seniorDouble);
                        } else {
                            CompetitionPlayer seniorMixedDouble = new CompetitionPlayer(memberID, memberType, disciplin, trainingResult, date);
                            seniorMixedDoubleList.add(seniorMixedDouble);
                            allSenior.add(seniorMixedDouble);
                        }
                    }

                    CompetitionPlayer competitionPlayer = new CompetitionPlayer(memberID, memberType, disciplin, trainingResult, date);

                    trainingResultList.add(competitionPlayer);

                } catch (IllegalArgumentException e) {
                    throw new CsvFileException(
                            "Kunne ikke parse linje " + linenumber + ": " + e.getMessage());
                }
            }
        } catch (IOException e) {
            throw new CsvFileException("Kunne ikke læse træningsresultat filen: " + e.getMessage());
        }
    }


    /**
     * Tager ArrayListen "trainingResultList" og skriver den til csv-filen "training_results".
     */
    public void writeToTrainingResultsFile() {
        try {
            FileWriter fileWriter = new FileWriter(training_results);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);

            for(CompetitionPlayer competitionPlayer : trainingResultList) {
                bufferedWriter.write(competitionPlayer.toString());
                bufferedWriter.newLine();
            }
/* Når en konkurrencespiller bliver redigeret bliver den ikke vist i top 5 listerne, da den her kun
bliver skrevet til "trainingResultList" listen og ikke "allJunior" og "allSenior" (de bliver ikke opdateret kun "trainingResultList")
            for(CompetitionPlayer competitionPlayer : allJunior) {
                bufferedWriter.write(competitionPlayer.toString());
                bufferedWriter.newLine();
            }

            for(CompetitionPlayer competitionPlayer : allSenior) {
                bufferedWriter.write(competitionPlayer.toString());
                bufferedWriter.newLine();
            }
*/
            bufferedWriter.close();

        } catch (Exception e) {
            throw new CsvFileException("Kunne ikke skrive til træningsresultat filen" + e.getMessage());
        }
    }
}
