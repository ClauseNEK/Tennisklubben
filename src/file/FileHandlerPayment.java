package file;

import exceptions.CsvFileException;
import model.*;

import java.io.*;
import java.util.ArrayList;


public class FileHandlerPayment {

    final static String payments = "src/csv/payments";
    final static String restance = "src/csv/restance";
    //public static ArrayList<Member> memberPaymentList = new ArrayList<>();

    public static ArrayList<Payment> restanceList = new ArrayList<>();
    public static ArrayList<Payment> paymentList = new ArrayList<>();


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
            throw new CsvFileException("Kunne ikke læse medlemsfilen: " + e.getMessage());
        }
    }


    //Skriver arraylisten til csv filen "payments"
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

        }

    }


    //Skriver arraylisten til csv filen "restance"
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
            throw new CsvFileException("Kunne ikke skrive til payments filen" + e.getMessage());
        }
    }

}
