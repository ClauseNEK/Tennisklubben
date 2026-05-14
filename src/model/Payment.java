package model;

public class Payment implements Comparable<Payment>{

    private int memberID;
    private String paymentStatus;
    private double payment;

    public Payment(int memberID, double payment, String paymentStatus) {
        this.memberID = memberID;
        this.payment = payment;
        this.paymentStatus = paymentStatus;
    }

    public int getMemberID() {
        return memberID;
    }

    public String getPaymentStatus() {
        return paymentStatus;
    }

    public double getPayment() {
        return payment;
    }

    @Override
    public String toString() {
        return memberID + "," +
                payment + "," +
                paymentStatus;
    }

    @Override
    public int compareTo(Payment other) {
        return Double.compare(this.payment, other.payment);
    }

}
