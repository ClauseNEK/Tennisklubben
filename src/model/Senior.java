package model;

import java.time.LocalDate;

//
public class Senior extends Member {

    //kontruktør
    public Senior(String name, int age, boolean activeMembership, Disciplin disciplin, GameCategory gameCategory) {
        super(name, age, activeMembership, disciplin, gameCategory);
    }

    @Override
    public String getMemberType(){
        return "Seniorspiller ";
    }

}