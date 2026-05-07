package model;
//
public class Junior extends Member {

    //kontruktør
    public Junior(String name, int age, boolean activeMembership, int memberid, Disciplin disciplin, GameCategory gameCategory) {
        super(name, age, activeMembership, memberid, disciplin, gameCategory);
    }


    @Override
    public double getPayment() {
        if (membership()) {
            return 800;
        } else {
            return 250;
        }
    }

}