package model;

import exceptions.InvalidAgeException;
import validation.AgeValidator;
import logger.*;

//Abstract klasse
public abstract class Member implements Membership  {

    private String name;
    private int age; //Implementer fødselsdags dato
    private boolean activeMembership;
    private int memberid;
    private static int memberIDCounter = 0;
    private Disciplin disciplin;
    private GameCategory gameCategory;

    private Payment payment;
    private Logger logger = new ConsoleLogger();

    //Validator
    private AgeValidator validator;

    /**
     * Members konstruktør.
     * @param name Medlemmets navn (String).
     * @param age Medlemmets alder (int).
     * @param activeMembership Medlemmets medlemskab (boolean).
     * @param disciplin Medlemmets disciplin (Enum).
     * @param gameCategory Medlemmets aktivitetsform (Enum).
     */
    public Member(String name, int age, boolean activeMembership, Disciplin disciplin, GameCategory gameCategory){
        memberIDCounter++;
        this.memberid = memberIDCounter;
        this.name = name;
        this.age = age;
        this.activeMembership = activeMembership;
        this.gameCategory = gameCategory;
        this.disciplin = disciplin;
    }

    /**
     * Overrider interface metoden, samt er en getters for boolean "activeMembership" i Members konstruktør.
     * @return En boolean, som bruges i convertMembership() til at definere om medlemskabet er aktiv eller passiv.
     */
    @Override
    public boolean membership() {
        return activeMembership;
    }

    /**
     * Getter for medlemmets navn.
     * @return "name" som en String.
     */
    public String getName(){
        return name;
    }

    /**
     * Getter for medlemmets alder.
     * @return "age" som en int.
     */
    public int getAge(){
        return age;
    }

    /**
     * Getter for medlemmets ID.
     * @return "memberid" som en int.
     */
    public int getMemberid(){
        return memberid;
    }

    /**
     * Getter for medlemmets disciplin (single/double/mixed_double).
     * @return "disciplin" som enum Disciplin.
     */
    public Disciplin getDisciplin() {
        return disciplin;
    }

    /**
     * Getter for medlemmets aktivitetsform, motionist (Exercise_player) eller konkurrence (Competition_player).
     * @return "gameCategory" som enum GameCategory.
     */
    public GameCategory getGameCategory() {
        return gameCategory;
    }

    /**
     * Abstrakt metode, der fortæller om medlemmet er Juniorspiller eller Seniorspiller.
     * @return String
     */
    public abstract String getMemberType();

    /**
     * Setter for medlemmets navn.
     * @param name Medlemmets nye navn.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Setter for medlemmets alder.
     * @param age Medlemmets nye alder (int).
     * @throws InvalidAgeException Hvis "age" er ude for aldersgrænsen kaldes denne exception.
     */
    public void setAge(int age) throws InvalidAgeException {
        validator.validate(age);
        this.age = age;
    }

    /**
     * Setter for medlemmets medlemskab.
     * @param membership En boolean, der definere om medlemskabet er aktivt(true) eller passivt(false).
     */
    public void setMembership(boolean membership) {
        this.activeMembership = membership;
    }

    /**
     * Giver en advarsel, hvis en juniorspiller nærmere sig 18år.
     */
    public void juniorWarning() {
        logger.warning("MedlemsID " + memberid + ": " + name + " er ved at nærme sig 18år. Husk at ændre stamoplysningerne så kontingentbetaling bliver opdateret.");
    }

    /**
     * Giver en advarsel, hvis en seniorspiller er ved at blive over 60år.
     */
    public void seniorWarning() {
        logger.warning("MedlemsID " + memberid + ": " + name + " er ved at blive over 60år. Husk at ændre stamoplysningerne så kontingentbetaling bliver opdateret.");
    }

    /**
     * Setter for medlemmets disciplin.
     * @param disciplin Enum Disciplin
     */
    public void setDisciplin(Disciplin disciplin) {
        this.disciplin = disciplin;
    }

    /**
     * Setter for medlemmets aktivitetsform.
     * @param gameCategory Enum GameCategory.
     */
    public void setGameCategory(GameCategory gameCategory) {
        this.gameCategory = gameCategory;
    }


    /**
     * Setter for medlemmets AgeValidator.
     * @param validator AgeValidator.
     */
    public void setValidator(AgeValidator validator) {
        this.validator = validator;
    }

    /**
     * ToString metode
     * @return String med medlemmets stamoplysninger.
     */
    @Override
    public String toString(){
        return "Navn: " + name
                + "\nAlder: " + age
                + "\nMedlemskab: " + convertMembership()
                + "\nMedlemsID: " + memberid
                + "\nDisciplin: " + disciplin
                + "\nAktivitetsform: " + gameCategory + "\n";
    }

    /**
     * Konvertere booleanen fra membership() til en String
     * @return Hvis true returneres "aktiv" eller returneres "passiv".
     */
    public String convertMembership(){
        if (membership()){
            return "aktiv";
        } else {
            return "passiv";
        }
    }

    /**
     * Metode til hvordan medlemmets oplysninger skal skrives til Members.csv
     * @return Alle oplysningerne som en String.
     */
    public String toMemberCSV() {
        return name + "," +
                age + "," +
                convertMembership() + "," +
                memberid + "," +
                disciplin + "," +
                gameCategory;
    }

}
