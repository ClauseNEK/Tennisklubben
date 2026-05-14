package service;

import file.FileHandlerTrainingResults;
import model.*;

import java.util.ArrayList;
import java.util.Scanner;

import static file.FileHandlerMembers.memberList;

public class CoachService {
    private static Scanner scanner = new Scanner(System.in);
    private static FileHandlerTrainingResults handlerTrainingResults = new FileHandlerTrainingResults();

    private static ArrayList<String> competitionList = new ArrayList<>();

    private static ArrayList<String> juniorSingleList = new ArrayList<>();
    private static ArrayList<String> juniorDoubleList = new ArrayList<>();
    private static ArrayList<String> juniorMixedDoubleList = new ArrayList<>();

    private static ArrayList<String> seniorSingleList = new ArrayList<>();
    private static ArrayList<String> seniorDoubleList = new ArrayList<>();
    private static ArrayList<String> seniorMixedDoubleList = new ArrayList<>();


    public ArrayList<String> getJuniorSingleList() {
        return juniorSingleList;
    }

    public ArrayList<String> getJuniorDoubleList() {
        return juniorDoubleList;
    }

    public ArrayList<String> getJuniorMixedDoubleList() {
        return juniorMixedDoubleList;
    }

    public ArrayList<String> getSeniorSingleList() {
        return seniorSingleList;
    }

    public ArrayList<String> getSeniorDoubleList() {
        return seniorDoubleList;
    }

    public ArrayList<String> getSeniorMixedDoubleList() {
        return seniorMixedDoubleList;
    }

    public static void showTop5ByDiscipline() {
        System.out.println("Top 5 efter disciplin:");

        for (Member member : memberList) {
            System.out.println(
                    member.getMemberid() + " - " +
                            member.getName() + " - " +
                            member.getDisciplin()
            );
        }
    }

    public static void showTop5ByTrainingResult() {
        System.out.println("Top 5 efter træningsresultat:");

        for (Member member : memberList) {
            System.out.println(
                    member.getMemberid() + " - " +
                            member.getName()
            );
        }
    }


    //Går igennem medlemslisten og finder alle de aktive konkurrencespillere
    public static void getCompetitionPlayers() {
        for(Member member : memberList) {
            if (member.membership()) { //Tjekker om medlemmet er aktivt
                if(member.getGameCategory().equals(GameCategory.COMPETITION_PLAYER)) {

                    Disciplin disciplin = member.getDisciplin();

                    System.out.print("Indtast " + member.getName() + "s bedste træningsresultat: ");
                    int result = scanner.nextInt();

                    scanner.nextLine();

                    System.out.print("Indtast datoen for dette resultat (dd-mm-yyyy): ");
                    String date = scanner.nextLine();

                    if (member.getAge() < 18) {
                        if(member.getDisciplin().equals(Disciplin.SINGLE)) {
                            juniorSingleList.add(memberResultToString(member.getMemberid(), disciplin, result, date));
                        } else if (member.getDisciplin().equals(Disciplin.DOUBLE)) {
                            juniorDoubleList.add(memberResultToString(member.getMemberid(), disciplin, result, date));
                        } else {
                            juniorMixedDoubleList.add(memberResultToString(member.getMemberid(), disciplin, result, date));
                        }
                    } else {
                        if(member.getDisciplin().equals(Disciplin.SINGLE)) {
                            seniorSingleList.add(memberResultToString(member.getMemberid(), disciplin, result, date));
                        } else if (member.getDisciplin().equals(Disciplin.DOUBLE)) {
                            seniorDoubleList.add(memberResultToString(member.getMemberid(), disciplin, result, date));
                        } else {
                            seniorMixedDoubleList.add(memberResultToString(member.getMemberid(), disciplin, result, date));
                        }
                    }

                    competitionList.add(memberResultToString(member.getMemberid(), disciplin, result, date));

                    handlerTrainingResults.writeToTrainingResultsFile();
                }
            }
        }
    }

    //Returnere dataerne som en String, så addMemberToPaymentFile() kan sætte dem samlet ind i en ArrayList<String>
    public static String memberResultToString(int memberID, Disciplin disciplin, int result, String date) {
        return memberID + "," + disciplin + "," + result + "," + date + "\n";
    }


    public void addResultAndDateToMember() {

    }

    public static void printLists() {
        getCompetitionPlayers();
        System.out.println("Alle konkurrencespillere:\n" + competitionList);

        System.out.println("Bedste træningsresultat for Junior i disciplinen SINGLE:\n" + juniorSingleList + "\n");
        System.out.println("Bedste træningsresultat for Junior i disciplinen DOUBLE:\n" + juniorDoubleList + "\n");
        System.out.println("Bedste træningsresultat for Junior i disciplinen MIXED DOUBLE:\n" + juniorMixedDoubleList + "\n\n");

        System.out.println("Bedste træningsresultat for Senior i disciplinen SINGLE:\n" + seniorSingleList + "\n");
        System.out.println("Bedste træningsresultat for Senior i disciplinen DOUBLE:\n" + seniorDoubleList + "\n");
        System.out.println("Bedste træningsresultat for Senior i disciplinen MIXED DOUBLE:\n" + seniorMixedDoubleList + "\n");

    }
}