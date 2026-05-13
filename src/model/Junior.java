package model;

import java.time.LocalDate;

//
public class Junior extends Member {

    //kontruktør
    public Junior(String name, int age, boolean activeMembership, Disciplin disciplin, GameCategory gameCategory) {
        super(name, age, activeMembership, disciplin, gameCategory);
    }


    @Override
    public String getMemberType(){
        return "Juniorspiller ";
    }

}