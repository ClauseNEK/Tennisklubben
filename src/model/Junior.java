package model;

public class Junior extends Member {

        /**
         * Opretter et nyt juniormedlem med stamoplysninger.
         * @param name medlemmets navn
         * @param age medlemmets alder
         * @param activeMembership true hvis medlemskabet er aktivt, ellers false
         * @param disciplin medlemmets disciplin, fx single, double eller mixed double
         * @param gameCategory medlemmets kategori, fx motionist eller konkurrencespiller
         */

        //kontruktør
    public Junior(String name, int age, boolean activeMembership, Disciplin disciplin, GameCategory gameCategory) {
        super(name, age, activeMembership, disciplin, gameCategory);
    }
        /**
         * Returnerer medlemstypen for et juniormedlem.
         * @return teksten "Juniorspiller"
         */

    @Override
    public String getMemberType(){
        return "Juniorspiller";
    }

}