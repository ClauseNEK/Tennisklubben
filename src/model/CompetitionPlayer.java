package model;

import file.FileHandlerMembers;

import java.util.ArrayList;

public class CompetitionPlayer {

    private ArrayList<Member> juniorList = new ArrayList<>();
    private ArrayList<Member> seniorList = new ArrayList<>();
    private FileHandlerMembers handler = new FileHandlerMembers();


    //Læser CSV "members" og gemmer alle konkurrencespillerne som er juniors
    public ArrayList<Member> getJuniorList() {

        return juniorList;
    }


    //Læser CSV "members" og gemmer alle konkurrencespillerne som er seniors
    public ArrayList<Member> getSeniorList() {
        return seniorList;
    }

}
