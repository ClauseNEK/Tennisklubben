package ui;
import java.util.Scanner;
import service.MemberService;
import util.MemberAgeComparator;
import util.MemberNameComparator;

import static file.FileHandlerMembers.memberList;

public class ChairmanUI {

    /**
     * Hvis "Formand" bliver valgt i SmashUI, bliver denne menu med formandens muligheder fremvist.
     */
    public static void showChairman(){
        Scanner scanner = new Scanner(System.in);
        boolean running = true;

        while (running){
            System.out.println(ConsoleStyle.BLUE + "\n==== Formand 👔 ====" + ConsoleStyle.RESET);
            System.out.println("1. Opret nyt medlem");
            System.out.println("2. Redigere medlem");
            System.out.println("3. Vis medlem (navn)");
            System.out.println("4. Vis medlem (alder)");
            System.out.println("5. Slet medlem");
            System.out.println("6. Gå tilbage");
            try {
                int input = Integer.parseInt(scanner.nextLine());

                switch (input){

                    case 1:
                        MemberService.addMember();
                        break;
                    case 2:
                        MemberService.showList(memberList);
                        MemberService.editMemberData(scanner);
                        break;
                    case 3:
                        MemberNameComparator.sortByName(memberList);
                        MemberService.showList(memberList);
                        break;
                    case 4:
                        MemberAgeComparator.sortByAge(memberList);
                        MemberService.showList(memberList);
                        MemberService.checkAges();
                        break;
                    case 5:
                        MemberService.removeMember();
                        MemberService.showList(memberList);
                        break;
                    case 6:
                        running = false;
                        break;
                    default:
                        ConsoleStyle.printError("Fejl valg: Vælg et tal mellem 1-6");
                }
            } catch (Exception e){
                throw new RuntimeException(e);
            }
        }
    }
}
