package service;

import exceptions.InvalidAgeException;
import file.FileHandlerMembers;
import model.*;
import ui.ChairmanUI;
import validation.MemberAgeValidator;

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


    //Redigere i medlemmets data og gemmer til CVS filen.
    public static void editMemberData(Scanner scanner){
        boolean running = true;

        System.out.print("Indtast medlemsID på det medlem du gerne vil ændre: ");
        int findID = scanner.nextInt();
        Member foundMember = seachForMember(findID);

        while(running) {
            System.out.println("\nHvad ville du ændre?:\n1. Navn \n2. Alder\n3. Medlemskab\n4. Disciplin\n5. Aktivitetsform\n6. Gem");
            int input = scanner.nextInt();
            scanner.nextLine();

            switch (input){
                case 1:
                    System.out.print("Indtast det nye navn: ");
                    String newName = scanner.nextLine();
                    editMemberName(foundMember, newName); //Der skal kaldes en exception til hvis foundMember er null, notFound eller OutOfBounds
                    break;
                case 2:
                    System.out.print("Indtast den nye alder: ");
                    int newAge = scanner.nextInt();
                    editMemberAge(foundMember, newAge);
                    break;
                case 3:
                    boolean newMembership = chooseMembership(scanner);
                    editMemberShip(foundMember, newMembership);
                    break;
                case 4:
                    Disciplin newDiscipline = chooseDisciplin(scanner);
                    editMemberDisciplin(foundMember, newDiscipline);
                    break;
                case 5:
                    GameCategory newGameCategory = chooseCategory(scanner);
                    editMemberGameCategory(foundMember, newGameCategory);
                    break;
                case 6:
                    fileHandlerMembers.writeToFile();
                    running = false;
                    break;
                default:
                    System.out.println("Ukendt input. Indtast et tal mellem 1-6");
            }
        }
    }

    //Finder medlemmet ud fra det indtastet medlemsID
    public static Member seachForMember(int memberID) {
        for (Member member : memberList) {
            if(memberID == member.getMemberid()) {
                return member;
            }
        }
        return null;
    }



    //Redigere medlemmets navn
    public static void editMemberName(Member member, String newName) {
        member.setName(newName);
    }

    //Redigere medlemmets alder
    public static void editMemberAge(Member member, int newAge) {
        member.setValidator(new MemberAgeValidator());
        try {
            member.setAge(newAge);
        } catch (InvalidAgeException e) {
            System.out.println("Fejl: " + e.getMessage());
        }
    }

    //Redigere medlemmets membership (passivt/aktivt)
    public static void editMemberShip(Member member, boolean newMemberShip) {
        member.setMembership(newMemberShip);
    }

    //Redigere medlemmets disciplin
    public static void editMemberDisciplin(Member member, Disciplin newDisciplin) {
        member.setDisciplin(newDisciplin);
    }

    //Redigere medlemmets aktivitetsform (motionist eller konkurrencespiller)
    public static void editMemberGameCategory(Member member, GameCategory newGameCategory) {
        member.setGameCategory(newGameCategory);
    }

    //Tager arraylisten af medlemmer og printer dem som en string
    public static void showList(ArrayList<Member> memberArrayList) {
            String allMembers = "";

            if(memberArrayList.isEmpty()) {
                System.out.println("Listen er tom");
            } else {
                for (Member member : memberArrayList) {
                    allMembers = allMembers.concat(member.toString() + "\n");
                }
                System.out.println(allMembers);
            }
    }

    public static void removeMember(){
        System.out.print("indtast medlemsID på medlemmet du vil slette");
        Scanner scanner = new Scanner(System.in);
        int medlemsID = scanner.nextInt();
        Member foundMember = seachForMember(medlemsID);
        memberList.remove(foundMember);
        fileHandlerMembers.writeToFile();
        System.out.print("Medlemmet er nu slettet\n");

    }


}
