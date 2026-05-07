package model;

//Abstract klasse
public abstract class Member implements Membership  {

    private String name;
    private int age; //Implementer fødselsdags dato
    private boolean activeMembership;
    private int memberid;
    private Disciplin disciplin;
    private GameCategory gameCategory;


    public Member(String name, int age, boolean activeMembership, int memberid, Disciplin disciplin, GameCategory gameCategory){
        //memberid++;
        this.memberid = memberid;
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

    public abstract double getPayment();
    // Udregner kontigentbeløbet:


    // Setters
    public void setName(String name) {
        this.name = name;
    }

    public void setAge(int age) {
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


    // TooString metode
    @Override
    public String toString(){
        return "Navn: " + name
                + "\nAlder: " + age
                + "\nMedlemskab: " + activeMembership
                + "\nMedlemsID: " + memberid
                + "\nDisciplin: " + disciplin
                + "\nAktivitetsform: " + gameCategory;
    }

    public String convertMembership(){
        if (membership()){
            return "aktiv";
        } else {
            return "passiv";
        }
    }

    public String toCSV() {
        return name + "," +
                age + "," +
                convertMembership() + "," +
                memberid + "," +
                disciplin + "," +
                gameCategory;
    }

}
