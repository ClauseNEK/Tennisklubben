package file;

import exceptions.CsvFileException;
import service.CoachService;

import java.io.BufferedWriter;
import java.io.FileWriter;


public class FileHandlerTrainingResults {

    final static String training_results = "src/csv/training_results";
    private CoachService coachService = new CoachService();


    //Skriver arraylisten til csv filen "restance"
    public void writeToTrainingResultsFile() {
        try {
            FileWriter fileWriter = new FileWriter(training_results);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);

            bufferedWriter.write("Bedste træningsresultat for Junior i disciplinen SINGLE:\n");
            for(String data : coachService.getJuniorSingleList()) {
                bufferedWriter.write(data);
                bufferedWriter.newLine();
            }

            bufferedWriter.write("Bedste træningsresultat for Junior i disciplinen DOUBLE:\n");
            for(String data : coachService.getJuniorDoubleList()) {
                bufferedWriter.write(data);
                bufferedWriter.newLine();
            }

            bufferedWriter.write("Bedste træningsresultat for Junior i disciplinen MIXED DOUBLE:\n");
            for(String data : coachService.getJuniorMixedDoubleList()) {
                bufferedWriter.write(data);
                bufferedWriter.newLine();
            }


            bufferedWriter.write("Bedste træningsresultat for Senior i disciplinen SINGLE:\n");
            for(String data : coachService.getSeniorSingleList()) {
                bufferedWriter.write(data);
                bufferedWriter.newLine();
            }

            bufferedWriter.write("Bedste træningsresultat for Senior i disciplinen DOUBLE:\n");
            for(String data : coachService.getSeniorDoubleList()) {
                bufferedWriter.write(data);
                bufferedWriter.newLine();
            }

            bufferedWriter.write("Bedste træningsresultat for Senior i disciplinen MIXED DOUBLE:\n");
            for(String data : coachService.getSeniorMixedDoubleList()) {
                bufferedWriter.write(data);
                bufferedWriter.newLine();
            }

            bufferedWriter.close();

        } catch (Exception e) {
            throw new CsvFileException("Kunne ikke skrive til training_results filen" + e.getMessage());
        }
    }
}
