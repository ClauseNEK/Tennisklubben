package model;
//
public class Senior extends Member {

    //kontruktør
    public Senior(String name, int age, boolean activeMembership, int memberid, Disciplin disciplin, GameCategory gameCategory) {
        super(name, age, activeMembership, memberid, disciplin, gameCategory);
    }

    public double sixtyPlusDiscount(){
        return 1500*0.25;
    }

    //to be fixed (after if stuff)
    @Override
    public double getPayment() {
        if (membership()) {
            if (getAge() > 60) {
                return 1500-sixtyPlusDiscount();
            }
            return 1500;
        } else {
            return 250;
        }
    }

}