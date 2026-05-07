package service;

import file.FileHandlerMembers;
import model.Disciplin;
import model.GameCategory;
import model.Member;

import java.util.ArrayList;
import java.util.Scanner;

import static file.FileHandlerMembers.memberList;

public class MemberService {

    public static void addMember(){
        System.out.print("Navnet på medlemmet: ");
        Scanner scanner = new Scanner(System.in);
        String name = scanner.nextLine();

        System.out.print("Alder på medlemmet: ");
        int age = scanner.nextInt();

        /*System.out.print("Passiv eller aktiv medlemsskab");
        boolean member = scanner.nextBoolean();
        if (member== true){
            setMembership(true);
        }
        */
        // genererer det næste medlemsID
        int memberID = getNextMemberID();
        chooseCategory(scanner);

    }
    //Finder det næste medlemsID
    //Den finder det seneste medlemsID i listen og lægger 1 til.
    //Denne metode skal kaldes når der oprettes et nyt medlem (int memberID = getNextMemberID())
    public static int getNextMemberID() {
        return memberList.getLast().getMemberid()+1;

    }

    public static GameCategory chooseCategory(Scanner scanner){
        GameCategory gameCategory = null;
        String input;
        while (true){
            System.out.print("Competition_player eller Exercise_player");
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



    //Redigere medlemmets alder og opdatere CSV filen.
    public void editMemberAge(Member member, int newAge) {
        member.setAge(newAge);
        //writeToFile();
        //Enten skal writeToFile ske her eller også skal den kaldes når man er færdig med at redigere
    }

    //Redigere medlemmets membership (passivt/aktivt) og opdatere CSV filen.
    public void editMemberShip(Member member, boolean newMemberShip) {
        member.setMembership(newMemberShip);
        //writeToFile();
    }

    //Redigere medlemmets disciplin og opdatere CSV filen.
    public void editMemberDisciplin(Member member, Disciplin newDisciplin) {
        member.setDisciplin(newDisciplin);
        //writeToFile();
    }

    //Redigere medlemmets aktivitetsform (motionist eller konkurrencespiller) og opdatere CSV filen.
    public void editMemberGameCategory(Member member, GameCategory newGameCategory) {
        member.setGameCategory(newGameCategory);
        //writeToFile();
    }


}
