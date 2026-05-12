package ui;

import file.FileHandlerPayment;
import service.MemberService;
import service.PaymentService;

import java.util.Scanner;

import static file.FileHandlerPayment.memberPaymentList;

public class CashierUI {

    private static FileHandlerPayment fileHandlerPayment = new FileHandlerPayment();

    public static void showCashier(){
        Scanner scanner = new Scanner(System.in);
        boolean running = true;

        while(running) {
            System.out.println("\n === Kasserer ===");
            System.out.println("1. Liste over indbetalinger");
            System.out.println("2. Oversigt over medlemmer i restance"); //comparable sortering?
            System.out.println("3. Sorter efter navn");
            System.out.println("4. Sorter efter beløb");

            try {
                int input = Integer.parseInt(scanner.nextLine());

                switch (input) {
                    case 1:
                        PaymentService.addPaymentToMember();
                        break;
                    case 2:
                        break;
                    case 3:
                        break;
                    case 4:
                        break;
                    default:
                        System.out.println("Ukendt input. Indtast et tal mellem 1-4");
                }
            } catch (Exception e) {
                throw new RuntimeException(e);
            }

        }


    }

}
