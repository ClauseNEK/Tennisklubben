package model;

import java.time.LocalDate;

//
public class Senior extends Member {

    //kontruktør
    public Senior(String name, int age, boolean activeMembership, Disciplin disciplin, GameCategory gameCategory) {
        super(name, age, activeMembership, disciplin, gameCategory);
    }

    public Senior(int memberid, String paymentStatus, LocalDate localDate) {
        super(memberid, paymentStatus, localDate);
    }


    /*public double sixtyPlusDiscount(){
        return 1500*0.25;
    }*/


    /*@Override
    public double getPayment() {
        if (membership()) {
            if (getAge() > 60) {
                return 1500-sixtyPlusDiscount();
            }
            return 1500;
        } else {
            return 250;
        }
    }*/

}