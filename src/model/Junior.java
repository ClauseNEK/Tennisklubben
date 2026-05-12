package model;

import java.time.LocalDate;

//
public class Junior extends Member {

    //kontruktør
    public Junior(String name, int age, boolean activeMembership, Disciplin disciplin, GameCategory gameCategory) {
        super(name, age, activeMembership, disciplin, gameCategory);
    }

    public Junior(int memberid, String paymentStatus, LocalDate localDate) {
        super(memberid, paymentStatus, localDate);
    }


    /*@Override
    public double getPayment() {
        if (membership()) {
            return 800;
        } else {
            return 250;
        }
    }*/

}