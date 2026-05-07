package ui;
import java.util.Scanner;
import service.MemberService;

public class ChairmanUI {

    public static void showChairman(){
        Scanner scanner = new Scanner(System.in);
        boolean running = true;

        while (true){
            System.out.println("\n === Formands ===");
            System.out.println("1. Opret nyt medlem");
            System.out.println("2. Redigere medlem");
            System.out.println("3. Vis medlem (navn)");
            System.out.println("4. Vis medlem (alder)");
            System.out.println("5. Slet medlem");
            System.out.println("6. Gå tilbage");
            try{
                int input = Integer.parseInt(scanner.nextLine());

                switch (input){

                    case 1:
                        MemberService.addMember();
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
