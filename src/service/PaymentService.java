package service;

import file.FileHandlerPayment;
import model.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

import static file.FileHandlerMembers.memberList;
import static file.FileHandlerPayment.*;

public class PaymentService {

    public static Scanner scanner = new Scanner(System.in);

    public static void addPaymentToMember(){

        for(Member member : memberList) {
            System.out.print(member.getMemberType() + " " + member.getName() + "\n");
            String paymentStatus = choosePaymentStatus(scanner);
            double payment;

            if(member.membership() && member.getAge() < 18) {
                payment = 800;
            } else if (member.membership() && member.getAge() > 18 && member.getAge() < 60) {
                payment = 1500;
            } else if (member.membership() && member.getAge() > 60) {
                payment = 1500-sixtyPlusDiscount();
            } else {
                payment = 250;
            }

            Payment paymentMember = new Payment(member.getMemberid(), payment, paymentStatus);
            addPaymentToCSV(paymentMember);

        }

    }


    public static double sixtyPlusDiscount(){
        return 1500*0.25;
    }


    public static String choosePaymentStatus(Scanner scanner) {
        String status = "";
        int input;

        //while(true) {
        System.out.println("Vælg betalingsstatus:\n1.Betalt\n2.Ikke betalt");
        input = scanner.nextInt();

        switch (input) {
            case 1:
                status = "Betalt";
                break;
            case 2:
                status = "Ikke betalt";
                break;
            default:
                System.out.println("Ukendt input. Tryk 1 eller 2.");
        }
        //}
        return status;

    }

    /**
     * Tager en ArrayList<Payment> og printer den som en String
     * @param paymentList en ArrayList bestående af Payment
     */
    public static void showPayment(ArrayList<Payment> paymentList) {
        if(paymentList.isEmpty()) {
            System.out.println("Listen er tom");
        } else {
            for (Payment payment : paymentList) {
                System.out.println(payment);
            }
        }

    }

    /*public static void showMembersInArrears() {
        System.out.println("Medlemmer i restance:");
    }*/
    public static void showPaymentsSortedByName() {
        //Collections.sort(restanceList);
        //FileHandlerPayment.printRestance();

    }

    public static void showPaymentsSortedByAmount() {
    }

    public static double calculatePayment(Member member) {
        return 0;
    }

    /**
     * De Payment objekter der bliver defineret i addPaymentToMember() bliver her føjet til ArrayListen
     * paymentList og hvis deres betalingsstatus er "Ikke betalt" bliver de også ført til ArrayListen restanceList.
     * @param payment Payment objekt, der bliver defineret i addPaymentToMember().
     */
    public static void addPaymentToCSV(Payment payment) {
        paymentList.add(payment);

        if (payment.getPaymentStatus().equalsIgnoreCase("Ikke betalt")) {
            restanceList.add(payment);
        }

        FileHandlerPayment.writeToPaymentFile();
        FileHandlerPayment.writeToRestanceFile();
    }

    /**
     * Tager restance listen og sortere den efter beløbet og sætter hele ArrayListen i en String, så den printes pænt.
     * @return Alle oplysningerne i "restanceList" som en String.
     */
    public static String sortByAmountAndConvertToString() {
        String sortedString = "";
        Collections.sort(restanceList);
        for(Payment payment : restanceList) {
            sortedString = sortedString.concat(payment.toString() + "\n");
        }
        return sortedString;
    }

    /**
     * Kalder showPayment() på ArrayListen paymentList.
     */
    public static void printPayments() {
        PaymentService.showPayment(paymentList);
    }

    /**
     * Kalder showPayment() på ArrayListen restanceList og skriver den til restance.csv.
     */
    public static void printRestance() {
        PaymentService.showPayment(restanceList);
        FileHandlerPayment.writeToRestanceFile();
    }


}
