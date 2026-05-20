package ui;

import service.PaymentService;
import java.util.Scanner;

public class CashierUI {

    /**
     * Hvis "Kasserer" bliver valgt i SmashUI, bliver denne menu og kasserernes meuligheder fremvist.
     */
    public static void showCashier(){
        Scanner scanner = new Scanner(System.in);
        boolean running = true;

        while(running) {
            System.out.println("\n === Kasserer ===");
            System.out.println("1. Vis liste over indbetalinger");
            System.out.println("2. Opdater liste over indbetalinger");
            System.out.println("3. Oversigt over medlemmer i Restance");
            System.out.println("4. Sorter Restance liste efter beløb"); //comparable sortering
            System.out.println("5. Rediger medlem i restance");
            System.out.println("6. Gå tilbage");

            try {
                int input = Integer.parseInt(scanner.nextLine());

                switch (input) {
                    case 1:
                        PaymentService.printPayments();
                        break;
                    case 2:
                        PaymentService.addPaymentToMember();
                        PaymentService.printPayments();
                        break;
                    case 3:
                        PaymentService.printRestance();
                        break;
                    case 4:
                        System.out.println(PaymentService.sortByAmountAndConvertToString());
                        break;
                    case 5:
                        PaymentService.editRestance(scanner);
                        break;
                    case 6:
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
