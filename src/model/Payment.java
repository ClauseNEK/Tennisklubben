package model;
public class Payment implements Comparable<Payment>{

    private int memberID;
    private String paymentStatus;
    private double payment;

    /**
     * Opretter en ny betaling for et medlem.
     * @param memberID ID'et på medlemmet, som betalingen tilhører
     * @param payment betalingsbeløbet
     * @param paymentStatus status for betalingen, fx betalt eller restance
     */

    public Payment(int memberID, double payment, String paymentStatus) {
        this.memberID = memberID;
        this.payment = payment;
        this.paymentStatus = paymentStatus;
    }

    /**
     * Returnerer medlemmets ID.
     * @return medlemmets ID
     */
    public int getMemberID() {
        return memberID;
    }

    /**
     * Returnerer betalingsstatussen.
     * @return betalingsstatus som tekst
     */
    public String getPaymentStatus() {
        return paymentStatus;
    }

    /**
     * Returnerer betalingsbeløbet.
     * @return betalingsbeløbet
     */
    public double getPayment() {
        return payment;
    }

    /**
     * Konverterer betalingen til en tekststreng, der kan gemmes i en CSV-fil.
     * @return betalingens oplysninger som CSV-linje
     */
    @Override
    public String toString() {
        return memberID + "," +
                payment + "," +
                paymentStatus;
    }

    /**
     * Sammenligner to betalinger ud fra betalingsbeløbet.
     * @param other den anden betaling, der sammenlignes med
     * @return et negativt tal, 0 eller et positivt tal afhængigt af beløbenes rækkefølge
     */
    @Override
    public int compareTo(Payment other) {
        return Double.compare(this.payment, other.payment);
    }
}
