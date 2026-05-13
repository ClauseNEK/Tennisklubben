package service;

import model.Member;

import static file.FileHandlerMembers.memberList;

public class CoachService {

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
}