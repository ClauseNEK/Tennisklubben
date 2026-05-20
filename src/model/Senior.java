package model;
public class Senior extends Member {

    /**
     * Konstruktør for Senior.
     * Opretter et nyt seniormedlem med dens stamoplysninger.
     * @param name medlemmets navn
     * @param age medlemmets alder
     * @param activeMembership true hvis medlemskabet er aktivt, ellers passivet
     * @param disciplin medlemmets disciplin, fx single, double eller mixed double
     * @param gameCategory medlemmets kategori, fx motionist eller konkurrencespiller
     */
    public Senior(String name, int age, boolean activeMembership, Disciplin disciplin, GameCategory gameCategory) {
        super(name, age, activeMembership, disciplin, gameCategory);
    }

    /**
     * Returnerer medlemstypen for et seniormedlem.
     * @return teksten "Seniorspiller"
     */
    @Override
    public String getMemberType(){
        return "Seniorspiller";
    }

}