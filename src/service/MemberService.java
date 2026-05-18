package service;

import exceptions.InvalidAgeException;
import exceptions.MemberNotFoundException;
import file.FileHandlerMembers;
import logger.ConsoleLogger;
import logger.Logger;
import model.*;
import validation.MemberAgeValidator;

import java.util.ArrayList;
import java.util.Scanner;

import static file.FileHandlerMembers.memberList;

public class MemberService {
    private static FileHandlerMembers fileHandlerMembers = new FileHandlerMembers();
    private static Logger logger = new ConsoleLogger();

    /**
     * Beder og gemmer alle stamoplysningerne på et nyt medlem
     */
    public static void addMember(){
        System.out.print("Navnet på medlemmet: ");
        Scanner scanner = new Scanner(System.in);
        String name = scanner.nextLine();

        int age = chooseAge(scanner);

        scanner.nextLine();

        boolean membership = chooseMembership(scanner);

        GameCategory gameCategory = chooseCategory(scanner);
        Disciplin disciplin = chooseDisciplin(scanner);

        if(age < 18) {
            fileHandlerMembers.addMemberToFile(new Junior(name, age, membership, disciplin, gameCategory));
        } else {
            fileHandlerMembers.addMemberToFile(new Senior(name, age, membership, disciplin, gameCategory));
        }

        logger.confirmed("\033[3mMedlem gemt\033[0m");
    }


    /**
     * Beder om alderen på medlemmet og gemmer oplysningen. Kalder en exception hvis inputtet er udenfor aldersgrænsen.
     * @param scanner Bruger Scanner til at få og gemme den ønskede alder.
     * @return Alderen som en int
     */
    public static int chooseAge(Scanner scanner) {
        int age;
        while (true) {
            try {
                System.out.print("Alder på medlemmet: ");
                if(scanner.hasNext()) {
                    age = scanner.nextInt();
                    if (age < 0 || age > 120) {
                        throw new InvalidAgeException("Ugyldig alder! Et medlem skal være mellem 0 og 120år");
                    } else {
                        return age;
                    }
                }
            } catch (InvalidAgeException e) {
                System.out.println("Fejl: Ugyldig alder. Et medlem skal være mellem 0 og 120år");
            }
        }
    }



    /**
     * Vælger om medlemskabet skal være aktivt eller passivt
     * @param scanner Bruger Scanner til at vælge mellem mulighederne
     * @return En boolean der siger om medlemskabet er aktivt(true) eller passivt(false)
     */
    public static boolean chooseMembership(Scanner scanner) {
        int input;

        while (true) {
            System.out.println("Vælg medlemskab:\n1.Aktivt\n2.Passivt");
            if(scanner.hasNextInt()) {
                input = scanner.nextInt();

                switch (input) {
                    case 1:
                        return true;
                    case 2:
                        return false;
                    default:
                        System.out.println("Ukendt input. Tryk 1 eller 2.");
                }
            } else {
                System.out.println("Forkert indtastning. Tryk 1 eller 2");
            }
        }
    }

    //Finder det næste medlemsID
    //Den finder det seneste medlemsID i listen og lægger 1 til.
    //Denne metode skal kaldes når der oprettes et nyt medlem (int memberID = getNextMemberID())
    /*public static int getNextMemberID() {
        return memberList.getLast().getMemberid()+1;
    }*/

    /**
     * Vælger om medlemmet er konkurrencespiller eller motionist
     * @param scanner Scanner bliver brugt til at vælge mellem mulighederne
     * @return GameCategory (enum)
     */
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

    /**
     * Vælger medlemmets disciplin
     * @param scanner Gemmer det indtastet input
     * @return Disciplin (enum)
     */
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

    /**
     * Redigere i medlemmets data og gemmer til CVS filen.
     * @param scanner Scanner bruges både til at vælge kategorien der skal redigeres i, samt til at gemme
     *                den nye indtastet data.
     */
    public static void editMemberData(Scanner scanner){
        boolean running = true;

        System.out.print("Indtast medlemsID på det medlem du gerne vil ændre: ");
        int findID = scanner.nextInt();
        try {
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
                        logger.confirmed("\n\033[3mData gemt\033[0m");
                        fileHandlerMembers.writeToFile();
                        running = false;
                        break;
                    default:
                        System.out.println("Ukendt input. Indtast et tal mellem 1-6");
                }
            }
        } catch (MemberNotFoundException e) {
            System.out.println("Fejl: " + e.getMessage());
            return;
        }
    }

    /**
     * Finder medlemmet ud fra det indtastet medlemsID
     * @param memberID Medlemmet, der skal findes, ID nummer
     * @return Medlemmet der har det ID nummer, der er identisk med "memberID"
     */
    public static Member seachForMember(int memberID) {
        for (Member member : memberList) {
            if (memberID == member.getMemberid()) {
                return member;
            }
        }
        throw new MemberNotFoundException("Intet medlem fundet med ID " + memberID);
    }

    /**
     * Redigere medlemmets navn
     * @param member Medlemmet der skal redigeres i
     * @param newName Det nye navn medlemmet skal have
     */
    public static void editMemberName(Member member, String newName) {
        member.setName(newName);
    }

    /**
     * Redigere medlemmets alder
     * @param member Medlemmet der skal redigeres i
     * @param newAge Medlemmets nye alder
     */
    public static void editMemberAge(Member member, int newAge) {
        member.setValidator(new MemberAgeValidator());
        try {
            member.setAge(newAge);
        } catch (InvalidAgeException e) {
            System.out.println("Fejl: " + e.getMessage());
        }
    }

    /**
     * Redigere medlemmets membership (passivt/aktivt)
     * @param member Medlemmet der skal redigeres i
     * @param newMemberShip Medlemmets nye medlemskab
     */
    public static void editMemberShip(Member member, boolean newMemberShip) {
        member.setMembership(newMemberShip);
    }

    /**
     * Redigere medlemmets disciplin
     * @param member Medlemmet der skal redigeres i
     * @param newDisciplin Medlemmets nye disciplin
     */
    public static void editMemberDisciplin(Member member, Disciplin newDisciplin) {
        member.setDisciplin(newDisciplin);
    }

    /**
     * Redigere medlemmets aktivitetsform (motionist eller konkurrencespiller)
     * @param member Medlemmet der skal redigeres i
     * @param newGameCategory Medlemmets nye aktivitetsform
     */
    public static void editMemberGameCategory(Member member, GameCategory newGameCategory) {
        member.setGameCategory(newGameCategory);
    }

    /**
     * Tager arraylisten af medlemmer og printer dem som en string
     * @param memberArrayList ArrayListen der skal printes ud som en String
     */
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

    /**
     * Fjerner et medlem fra medlemslisten og opdatere Members.csv.
     * Medlemmet findes ud fra deres medlemsID.
     */
    public static void removeMember() {
        System.out.print("Indtast medlemsID på medlemmet du vil slette: ");
        Scanner scanner = new Scanner(System.in);
        int medlemsID = scanner.nextInt();
        try {
            Member foundMember = seachForMember(medlemsID);
            memberList.remove(foundMember);
            fileHandlerMembers.writeToFile();
            System.out.println("Medlemmet er nu slettet");
        } catch (MemberNotFoundException e) {
            System.out.println("Fejl: " + e.getMessage());
        }
    }

    /**
     * Går igennem medlemslisten og giver en advarsel, hvis en juniorspiller nærmere sig 18år eller hvis en
     * seniorspiller snart bliver over 60år.
     */
    public static void checkAges() {
        for (Member member : memberList) {
            if (member.getAge() == 17) {
                member.juniorWarning();
            } else if (member.getAge() == 60) {
                member.seniorWarning();
            }
        }
    }

}
