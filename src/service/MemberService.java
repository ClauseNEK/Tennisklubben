package service;

import file.FileHandlerMembers;
import model.*;

import java.util.ArrayList;
import java.util.Scanner;

import static file.FileHandlerMembers.memberList;

public class MemberService {
    private static FileHandlerMembers fileHandlerMembers = new FileHandlerMembers();

    public static void addMember(){

        System.out.print("Navnet på medlemmet: ");
        Scanner scanner = new Scanner(System.in);
        String name = scanner.nextLine();

        System.out.print("Alder på medlemmet: ");
        int age = scanner.nextInt();

        boolean membership = chooseMembership(scanner);

        // genererer det næste medlemsID
        //int memberID = getNextMemberID();

        GameCategory gameCategory = chooseCategory(scanner);
        Disciplin disciplin = chooseDisciplin(scanner);

        if(age < 18) {
            fileHandlerMembers.addMemberToFile(new Junior(name, age, membership, disciplin, gameCategory));
        } else {
            fileHandlerMembers.addMemberToFile(new Senior(name, age, membership, disciplin, gameCategory));
        }

    }

    //Vælger om medlemskabet skal være aktivt eller passivt
    public static boolean chooseMembership(Scanner scanner) {
        boolean membership = true;
        int input;

        //while(true) {
        System.out.println("Vælg medlemskab:\n1.Aktivt\n2.Passivt");
        input = scanner.nextInt();

        switch (input) {
            case 1:
                membership = true;
                break;
            case 2:
                membership = false;
                break;
            default:
                System.out.println("Ukendt input. Tryk 1 eller 2.");
        }
        //}
        return membership;

    }

    //Finder det næste medlemsID
    //Den finder det seneste medlemsID i listen og lægger 1 til.
    //Denne metode skal kaldes når der oprettes et nyt medlem (int memberID = getNextMemberID())
    /*public static int getNextMemberID() {
        return memberList.getLast().getMemberid()+1;
    }*/


    //Vælger om medlemmet er konkurrencespiller eller motionist
    public static GameCategory chooseCategory(Scanner scanner){
        GameCategory gameCategory = null;
        String input;
        System.out.print("Competition_player eller Exercise_player: ");
        while (true){
            if (scanner.hasNext()){
                input = scanner.nextLine().toUpperCase();

                for (GameCategory gc : GameCategory.values()){
                    if (gc.name().equalsIgnoreCase(input)){
                        gameCategory = GameCategory.valueOf(input);
                        return gameCategory;
                    }
                }
            }
            else {
                System.out.print("Ukendt input");
            }
        }
    }

    public static Disciplin chooseDisciplin(Scanner scanner) {
        Disciplin disciplin = null;
        String input;

        while (true) {
            System.out.print("Er disciplinen single/double/mixed_double: ");
            if(scanner.hasNext()) {
                input = scanner.nextLine().toUpperCase();

                for (Disciplin d : Disciplin.values()) {
                    if (d.name().equalsIgnoreCase(input)) {
                        disciplin = Disciplin.valueOf(input);
                        return disciplin;
                    }
                }
            } else {
                System.out.println("Ukendt input :(");
            }
        }
    }


/*  Tilføje returns på de enkelte edits
    public static void editMemberData(Scanner scanner){
        System.out.println("Hvad ville du ændre?:\n1. Navn \n 2. Alder\n 3. Medlemskab\n 4. Disciplin\n 5. Aktivitetsform");
        int input = scanner.nextInt();

        switch (input){
            case 1:
                //name
                break;
            case 2:
                editMemberAge();
                break;
            case 3:
                editMemberShip();
                break;
            case 4:
                editMemberDisciplin();
                break;
            case 5:
                editMemberGameCategory();
                break;
        }
    }
*/





    //Redigere medlemmets alder og opdatere CSV filen.
    public static void editMemberAge(Member member, int newAge) {
        member.setAge(newAge);
        //writeToFile();
        //Enten skal writeToFile ske her eller også skal den kaldes når man er færdig med at redigere
    }

    //Redigere medlemmets membership (passivt/aktivt) og opdatere CSV filen.
    public static void editMemberShip(Member member, boolean newMemberShip) {
        member.setMembership(newMemberShip);
        //writeToFile();
    }

    //Redigere medlemmets disciplin og opdatere CSV filen.
    public static void editMemberDisciplin(Member member, Disciplin newDisciplin) {
        member.setDisciplin(newDisciplin);
        //writeToFile();
    }

    //Redigere medlemmets aktivitetsform (motionist eller konkurrencespiller) og opdatere CSV filen.
    public static void editMemberGameCategory(Member member, GameCategory newGameCategory) {
        member.setGameCategory(newGameCategory);
        //writeToFile();
    }


}
