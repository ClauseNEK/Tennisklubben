package model;
//
public class Junior extends Member {

    //kontruktør
    public Junior(String name, int age, Membership membership, int memberid, GameCategory gameCategory, Disciplin disciplin) {
        super(name, age, membership, memberid, gameCategory, disciplin);
    }

    //to be fixed (after if stuff)
    @Override
    public double getPayment() {
        if (getMembership().membership()) {
            return 800;
        } else {
            return 250;
        }
    }

}