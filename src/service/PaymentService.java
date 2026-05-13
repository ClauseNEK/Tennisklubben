package service;

import file.FileHandlerPayment;
import model.*;

import java.time.LocalDate;
import java.util.Scanner;

import static file.FileHandlerMembers.memberList;

public class PaymentService {

    private static FileHandlerPayment fileHandlerPayment = new FileHandlerPayment();
    public static Scanner scanner = new Scanner(System.in);

    public static void addPaymentToMember(){

        for(Member member : memberList) {
            System.out.print(member.getName() + "\n");
            String paymentStatus = choosePaymentStatus(scanner);
            //int memberid = member.getMemberid();
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

            fileHandlerPayment.addMemberToPaymentFile(member, payment, paymentStatus);

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

}
