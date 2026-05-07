package ui;
import java.util.Scanner;

public class SmashUI {
    Scanner scanner = new Scanner(System.in);   // Scanner objekt
    public void start(){

        boolean running = true;

        while (true){
            System.out.println("Vælg din rolle\n 1. Formand\n 2. Kasserer\n 3. Coach\n 4. Luk program");
            try{
                int input = Integer.parseInt(scanner.nextLine());

                switch (input){

                    case 1:
                        ChairmanUI.showChairman();
                        break;

                    case 2:
                        CashierUI.showCashier();
                        break;

                    case 3:
                        CoachUI.showCoach();
                        break;

                    case 4:
                        scanner.close();
                        running = false;
                        break;

                    default:
                        System.out.println("Fejl valg: Vælg et tal mellem 1-3");
                }
            } catch (Exception e){
                System.out.println("KALD EN EXCEPTION HER!");
            }
        }


    }
}
