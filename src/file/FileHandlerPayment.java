package file;

import model.*;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.util.ArrayList;


public class FileHandlerPayment {

    final static String payments = "src/csv/payments";
    public static ArrayList<Member> memberPaymentList = new ArrayList<>();
    //public static ArrayList<Double> paymentList = new ArrayList<Double>();

    public static ArrayList<String> mergedList = new ArrayList<>();


    //Skriver arraylisten til csv filen "payments"
    public void writeToFile() {
        try {
            FileWriter fileWriter = new FileWriter(payments);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);

            bufferedWriter.write(mergedList.toString());

            bufferedWriter.close();

        } catch (Exception e) {

        }

    }


    //Fejl: Betalingsprisen og paymentStatus driller (paymentStatus bliver passiv når man gøre programmet med eksisterende dataer)
    public void addMemberToPaymentFile(Member member, double payment, String paymentStatus) {
        mergedList.add(memberPaymentToString(member.getMemberid(), payment, paymentStatus));
        writeToFile();
    }

    //Returnere dataerne som en String, så addMemberToPaymentFile() kan sætte dem samlet ind i en ArraList<String>
    public String memberPaymentToString(int memberID, double payment, String paymentStatus) {
        return memberID + "," + payment + "," + paymentStatus + "\n";
    }

}
