package service;

import exceptions.MemberNotFoundException;
import file.FileHandlerPayment;
import logger.ConsoleLogger;
import logger.Logger;
import model.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

import static file.FileHandlerMembers.memberList;
import static file.FileHandlerPayment.*;

public class PaymentService {

    public static Scanner scanner = new Scanner(System.in);
    private static Logger logger = new ConsoleLogger();

    public static void addPaymentToMember(){
        paymentList.removeAll(paymentList);
        restanceList.removeAll(restanceList);
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
     * Tager Restance listen og sortere den efter beløbet og sætter hele ArrayListen i en String, så den printes pænt.
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
     * Kalder showPayment() på ArrayListen restanceList og skriver den til Restance.csv.
     */
    public static void printRestance() {
        PaymentService.showPayment(restanceList);
        FileHandlerPayment.writeToRestanceFile();
    }

    /**
     * Giver kasserene lov til at redigere et medlem i restance fra "Ikke betalt" til "Betalt" og fjerner dem
     * fra restanceList og opdatere paymentList.
     * @param scanner Bruger Scanner til at tage imod et medlemsID, som medlemmet i restance vil blive fundet
     *                og opdateret ud fra
     */
    public static void editRestance(Scanner scanner) {
        printRestance();
        System.out.print("Indtast medlemsID på det medlem i Restance du gerne vil redigere." +
                "\nBetalingsstatus vil blive sat til \"Betalt\" på det valgte medlem: ");
        int findID = scanner.nextInt();
        Payment paymentMember = findPaymentMember(findID);

        restanceList.remove(paymentMember);
        searchPaymentList(findID);
        scanner.nextLine();
        logger.confirmed("\n\033[3mBetalingsstatus opdateret\033[0m");

        FileHandlerPayment.writeToPaymentFile();
    }


    /**
     * Går igennem restanceList og returnere en payment, der er knyttet til det medlem, der matcher memberID
     * @param memberID Finder paymenten ud fra medlemsID'et i restanceList
     * @return payment ud fra det indtastet medlemsID
     */
    public static Payment findPaymentMember(int memberID) {
        for(Payment paymentMember : restanceList) {
            if(memberID == paymentMember.getMemberID()) {
                return paymentMember;
            }
        }
        throw new MemberNotFoundException("Intet medlem fundet med ID " + memberID);
    }

    /**
     * Går igennem paymentList og finder medlemmet, der skal redigeres i og ændre deres betalingsstatus til "Betalt"
     * @param memberID Finder paymenten/medlemmet ud fra medlemsID'et i paymentList
     */
    public static void searchPaymentList(int memberID) {
        for(Payment payment : paymentList) {
            if(memberID == payment.getMemberID()) {
                payment.setPaymentStatus("Betalt");
            }
        }
        //throw new MemberNotFoundException("Intet medlem fundet med ID " + memberID);
    }
}
