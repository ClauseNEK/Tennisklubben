package ui;

import file.FileHandlerPayment;
import service.MemberService;
import service.PaymentService;

import java.util.Scanner;


public class CashierUI {

    public static void showCashier(){
        Scanner scanner = new Scanner(System.in);
        boolean running = true;

        while(running) {
            System.out.println("\n === Kasserer ===");
            System.out.println("1. Liste over indbetalinger");
            System.out.println("2. Oversigt over medlemmer i restance");
            System.out.println("3. Sorter efter navn");
            System.out.println("4. Sorter restance liste efter beløb"); //comparable sortering
            System.out.println("5. Gå tilbage");

            try {
                int input = Integer.parseInt(scanner.nextLine());

                switch (input) {
                    case 1:
                        PaymentService.addPaymentToMember();
                        PaymentService.printPayments();
                        break;
                    case 2:
                        PaymentService.printRestance();
                        break;
                    case 3:
                        PaymentService.showPaymentsSortedByName();
                        break;
                    case 4:
                        //PaymentService.showPaymentsSortedByAmount();
                        System.out.println(PaymentService.sortByAmountAndConvertToString());
                        break;
                    case 5:
                        running = false;
                        break;
                    default:
                        System.out.println("Ukendt input. Indtast et tal mellem 1-5");
                }
            } catch (Exception e) {
                throw new RuntimeException(e);
            }

        }


    }

}
