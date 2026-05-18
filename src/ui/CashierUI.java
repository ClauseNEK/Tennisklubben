package ui;

import service.PaymentService;
import java.util.Scanner;

public class CashierUI {

    /*Skal kasserne have mulighed for at redigere medlemmer i restance? Dette kan ske hvis man trykker 2 og
    opdatere hele listen, men der kunne være en mulighed for at ændre et enkelt medlem (ligesom i CoachUI).
    Det er dog ikke en del af opgave beskrivelsen.*/

    public static void showCashier(){
        Scanner scanner = new Scanner(System.in);
        boolean running = true;

        while(running) {
            System.out.println("\n === Kasserer ===");
            System.out.println("1. Vis liste over indbetalinger");
            System.out.println("2. Opdater liste over indbetalinger");
            System.out.println("3. Oversigt over medlemmer i restance");
            System.out.println("4. Sorter restance liste efter beløb"); //comparable sortering
            System.out.println("5. Gå tilbage");

            try {
                int input = Integer.parseInt(scanner.nextLine());

                switch (input) {
                    case 1:
                        PaymentService.printPayments();
                        //PaymentService.showPayment(paymentList);
                        break;
                    case 2:
                        PaymentService.addPaymentToMember();
                        PaymentService.printPayments();
                        break;
                    case 3:
                        PaymentService.printRestance();
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
