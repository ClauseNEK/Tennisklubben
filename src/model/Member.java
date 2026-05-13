package model;

import exceptions.InvalidAgeException;
import service.PaymentService;
import validation.AgeValidator;

import java.time.LocalDate;

//Abstract klasse
public abstract class Member implements Membership  {

    private String name;
    private int age; //Implementer fødselsdags dato
    private boolean activeMembership;
    private int memberid;
    private static int memberIDCounter = 0;
    private Disciplin disciplin;
    private GameCategory gameCategory;

    //Bruges til kassereren
    private String paymentStatus;
    private LocalDate localDate;
    private double payment;

    //Validator
    private AgeValidator validator;


    public Member(String name, int age, boolean activeMembership, Disciplin disciplin, GameCategory gameCategory){
        memberIDCounter++;
        this.memberid = memberIDCounter;
        this.name = name;
        this.age = age;
        this.activeMembership = activeMembership;
        this.gameCategory = gameCategory;
        this.disciplin = disciplin;
    }


    //Overrider interface metode, samt er en getters for boolean "activeMembership"
    @Override
    public boolean membership() {
        return activeMembership;
    }


    // Getters
    public String getName(){
        return name;
    }

    public int getAge(){
        return age;
    }

    public int getMemberid(){
        return memberid;
    }

    public Disciplin getDisciplin() {
        return disciplin;
    }

    public GameCategory getGameCategory() {
        return gameCategory;
    }

    public String getPaymentstatus() {
        return paymentStatus;
    }

    public LocalDate getLocalDate() {
        return localDate;
    }

    //Printer medlemstypen (junior/senior)
    public abstract String getMemberType();


    // Setters
    public void setName(String name) {
        this.name = name;
    }

    public void setAge(int age) throws InvalidAgeException {
        validator.validate(age);
        this.age = age;
    }

    public void setMembership(boolean membership) {
        this.activeMembership = membership;
    }

    public void setDisciplin(Disciplin disciplin) {
        this.disciplin = disciplin;
    }

    public void setGameCategory(GameCategory gameCategory) {
        this.gameCategory = gameCategory;
    }

    public void setValidator(AgeValidator validator) {
        this.validator = validator;
    }


    // TooString metode
    @Override
    public String toString(){
        return "Navn: " + name
                + "\nAlder: " + age
                + "\nMedlemskab: " + convertMembership()
                + "\nMedlemsID: " + memberid
                + "\nDisciplin: " + disciplin
                + "\nAktivitetsform: " + gameCategory + "\n";
    }

    public String convertMembership(){
        if (membership()){
            return "aktiv";
        } else {
            return "passiv";
        }
    }

    public String toMemberCSV() {
        return name + "," +
                age + "," +
                convertMembership() + "," +
                memberid + "," +
                disciplin + "," +
                gameCategory;
    }

    public String toPaymentCSV() {
        return memberid + "," +
                paymentStatus + "," +
                localDate;
    }

}
