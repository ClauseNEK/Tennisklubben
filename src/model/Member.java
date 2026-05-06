package model;

//Abstract klasse
public abstract class Member  {

    private String name;
    private int age;
    private boolean membership;
    private int memberid;
    private Disciplin disciplin;
    private GameCategory gameCategory;


    public Member(String name, int age, boolean membership, int memberid, GameCategory gameCategory, Disciplin disciplin){
        memberid++;
        this.name = name;
        this.age = age;
        this.membership = membership;
        this.gameCategory = gameCategory;
        this.disciplin = disciplin;
    }


    // Getters
    public String getName(){
        return name;
    }

    public int getAge(){
        return age;
    }

    public boolean getMembership(){
        return membership;
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

    public abstract double getPayment();
    // Udregner kontigentbeløbet:


    // Setters


    // TooString metode
    @Override
    public String toString(){
        return "Navn: " + name
                + "\nalder:" + age
                + "\nMedlemskab" + membership
                + "\nMedlemsID" + memberid
                + "\nDisciplin: " + disciplin
                + "\nAktivitetsform:" + gameCategory;
    }


}
