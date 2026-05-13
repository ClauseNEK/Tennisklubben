package file;

import exceptions.CsvFileException;
import model.*;
import service.PaymentService;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.util.ArrayList;


public class FileHandlerPayment {

    final static String payments = "src/csv/payments";
    final static String restance = "src/csv/restance";
    public static ArrayList<Member> memberPaymentList = new ArrayList<>();

    public static ArrayList<String> restanceList = new ArrayList<>();
    public static ArrayList<String> mergedList = new ArrayList<>();


    //Skriver arraylisten til csv filen "payments"
    public void writeToPaymentFile() {
        try {
            FileWriter fileWriter = new FileWriter(payments);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);

            for(String data : mergedList) {
                bufferedWriter.write(data);
                bufferedWriter.newLine();
            }

            bufferedWriter.close();

        } catch (Exception e) {

        }

    }


    //Skriver arraylisten til csv filen "restance"
    public void writeToRestanceFile() {
        try {
            FileWriter fileWriter = new FileWriter(restance);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);

            for(String data : restanceList) {
                bufferedWriter.write(data);
                bufferedWriter.newLine();
            }

            bufferedWriter.close();

        } catch (Exception e) {
            throw new CsvFileException("Kunne ikke skrive til payments filen" + e.getMessage());
        }
    }


    //Fejl: Betalingsprisen og paymentStatus driller (paymentStatus bliver passiv når man gøre programmet med eksisterende dataer)
    public void addMemberToPaymentFile(Member member, double payment, String paymentStatus) {
        mergedList.add(memberPaymentToString(member.getMemberid(), payment, paymentStatus));

        if (paymentStatus.equalsIgnoreCase("Ikke betalt")) {
            restanceList.add(memberPaymentToString(member.getMemberid(), payment, paymentStatus));
        }

        writeToPaymentFile();
        writeToRestanceFile();
    }

    //Returnere dataerne som en String, så addMemberToPaymentFile() kan sætte dem samlet ind i en ArrayList<String>
    public String memberPaymentToString(int memberID, double payment, String paymentStatus) {
        return memberID + "," + payment + "," + paymentStatus + "\n";
    }

    public static void printPayments() {
        PaymentService.showPayment(mergedList);
    }

    public static void printRestance() {
        PaymentService.showPayment(restanceList);
    }

}
