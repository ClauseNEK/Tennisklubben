package file;

import exceptions.CsvFileException;
import model.*;

import java.io.*;
import java.util.ArrayList;
/**
 * Håndterer læsning og skrivning af betalinger og restancer til CSV-filer.
 * Klassen bruger paymentList til betalinger og restanceList til medlemmer i Restance.
 */
public class FileHandlerPayment {

    final static String payments = "src/csv/Payments";
    final static String restance = "src/csv/Restance";

    public static ArrayList<Payment> restanceList = new ArrayList<>();
    public static ArrayList<Payment> paymentList = new ArrayList<>();

    /**
     * Læser betalinger fra CSV-filen Payments og tilføjer dem til paymentList.
     *
     * @throws CsvFileException hvis filen ikke kan læses, eller hvis en linje har ugyldige data
     */
    public void readPaymentsCSV() {
        try(BufferedReader reader = new BufferedReader(new FileReader(payments))) {
            String line;
            int linenumber = 0;

            while((line = reader.readLine()) != null) {
                linenumber++;
                String[] parts = line.split(",");

                if (parts.length < 3) {
                    throw new CsvFileException("Forkerte antal felter på linjer" + linenumber);
                }

                try {
                    int memberID = Integer.parseInt(parts[0]);
                    double paymentAmount = Double.parseDouble(parts[1]);
                    String paymentStatus = parts[2];

                    Payment payment = new Payment(memberID, paymentAmount, paymentStatus);

                    paymentList.add(payment);

                } catch (IllegalArgumentException e) {
                    throw new CsvFileException(
                            "Kunne ikke parse linje " + linenumber + ": " + e.getMessage());
                }
            }
        } catch (IOException e) {
            throw new CsvFileException("Kunne ikke læse Payments filen: " + e.getMessage());
        }
    }


    public void readRestanceCSV() {
        try(BufferedReader reader = new BufferedReader(new FileReader(restance))) {
            String line;
            int linenumber = 0;

            while((line = reader.readLine()) != null) {
                linenumber++;
                String[] parts = line.split(",");

                if (parts.length < 3) {
                    throw new CsvFileException("Forkerte antal felter på linjer" + linenumber);
                }

                try {
                    int memberID = Integer.parseInt(parts[0]);
                    double paymentAmount = Double.parseDouble(parts[1]);
                    String paymentStatus = parts[2];

                    Payment payment = new Payment(memberID, paymentAmount, paymentStatus);

                    restanceList.add(payment);

                } catch (IllegalArgumentException e) {
                    throw new CsvFileException(
                            "Kunne ikke parse linje " + linenumber + ": " + e.getMessage());
                }
            }
        } catch (IOException e) {
            throw new CsvFileException("Kunne ikke læse Restance filen: " + e.getMessage());
        }
    }



    /**
     * Skriver alle betalinger fra paymentList til CSV-filen Payments.
     * @throws CsvFileException hvis der opstår en fejl under skrivning til filen
     */
    public static void writeToPaymentFile() {
        try {
            FileWriter fileWriter = new FileWriter(payments);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);

            for(Payment p : paymentList) {
                bufferedWriter.write(p.toString());
                bufferedWriter.newLine();
            }

            bufferedWriter.close();

        } catch (Exception e) {
            throw new CsvFileException("Kunne ikke skrive til Payments filen" + e.getMessage());
        }

    }


    //Skriver arraylisten til csv filen "Restance"
    public static void writeToRestanceFile() {
        try {
            FileWriter fileWriter = new FileWriter(restance);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);

            for(Payment p : restanceList) {
                bufferedWriter.write(p.toString());
                bufferedWriter.newLine();
            }

            bufferedWriter.close();

        } catch (Exception e) {
            throw new CsvFileException("Kunne ikke skrive til Restance filen" + e.getMessage());
        }
    }

}
