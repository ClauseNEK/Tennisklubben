package model;
//
public class Senior extends Member {

    //kontruktør
    public Senior(String name, int age, Membership membership, int memberid, GameCategory gameCategory, Disciplin disciplin) {
        super(name, age, membership, memberid, gameCategory, disciplin);
    }

    public double sixtyPlusDiscount(){
        return 1500*0.25;
    }

    //to be fixed (after if stuff)
    @Override
    public double getPayment() {
        if (getMembership().membership()) {
            if (getAge() > 60) {
                return 1500-sixtyPlusDiscount();
            }
            return 1500;
        } else {
            return 250;
        }
    }

}