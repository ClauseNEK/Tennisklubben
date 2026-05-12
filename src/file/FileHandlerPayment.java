package file;

import model.*;
import service.PaymentService;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;

import static file.FileHandlerMembers.memberList;
import static service.PaymentService.*;


public class FileHandlerPayment {

    final static String payments = "src/csv/payments";
    public static ArrayList<Member> memberPaymentList = new ArrayList<Member>();
    public static ArrayList<Double> paymentList = new ArrayList<Double>();

    public static ArrayList<String> mergedList = new ArrayList<>();

    /*public void readPaymentCSV() {

        try (BufferedReader reader =
                     new BufferedReader(new FileReader(members))) {

            String line;

            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");

                int memberid = Integer.parseInt(parts[0]);
                double payment = Double.parseDouble(parts[1]);
                boolean paymentStatus = choosePaymentStatus(scanner);

                if(member.getAge() < 18) {
                    fileHandlerPayment.addMemberToPaymentFile(new Junior(paymentStatus, member.getLocalDate()));
                } else {
                    fileHandlerPayment.addMemberToPaymentFile(new Senior(paymentStatus, member.getLocalDate()));
                }

            }

        } catch (Exception e){
            e.printStackTrace();
        }

    }*/


    //Skriver arraylisten til csv filen "payments"
    public void writeToFile() {
        try {
            FileWriter fileWriter = new FileWriter(payments);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);

            bufferedWriter.write(convertMemberList());
            bufferedWriter.write(convertPayList());

            /*for (Member member : memberPaymentList) {
                bufferedWriter.write(member.getMemberid() + "," +

                        //member.getPayment() + "," +
                        member.getPaymentstatus() + "," +
                        member.getLocalDate());
                bufferedWriter.newLine();
            }*/

            bufferedWriter.close();

        } catch (Exception e) {

        }

    }

    //Fejl med paymentStatus og dens placering i payments filen
    //Samt at betalingsprisen driller når vi ændre paymentStatus
    public void addMemberToPaymentFile(Member member, double payment/*, String paymentStatus*/) {
        memberPaymentList.add(member);
        paymentList.add(payment);
        //mergedList.add(paymentStatus);
        writeToFile();
    }


    public String convertMemberList() {
        for(Member member : memberPaymentList) {
            mergedList.add(member.toPaymentCSV());
        }
        return mergedList.toString();
    }


    public String convertPayList() {
        for(double d : paymentList) {
            String pay = d + "";
            mergedList.add(pay);
        }
        return mergedList.toString();
    }

}
