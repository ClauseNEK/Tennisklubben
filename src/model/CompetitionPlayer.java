package model;

public class CompetitionPlayer implements Comparable<CompetitionPlayer>{

    private int memberID;
    private String memberType;
    private Disciplin disciplin;
    private int trainingResult;
    private String date;

    /**
     * CompetitionPlayers(konkurrencespiller) konstruktør
     * @param memberID Konkurrencespillerens medlemsID (int)
     * @param memberType Konkurrencespillerens medlems type, der fortæller om de er en senior eller junior (String)
     * @param disciplin Konkurrencespillerens disciplin (enum)
     * @param trainingResult Konkurrencespillerens træningsresultat (int)
     * @param date Datoen for konkurrencespillerens træningsresultat (String)
     */
    public CompetitionPlayer(int memberID, String memberType, Disciplin disciplin, int trainingResult, String date) {
        this.memberID = memberID;
        this.memberType = memberType;
        this.disciplin = disciplin;
        this.trainingResult = trainingResult;
        this.date = date;
    }

    /**
     * Getter for konkurrencespillerens medlemsID
     * @return medlemsID
     */
    public int getMemberID() {
        return memberID;
    }

    /**
     * Getter for konkurrencespillerens medlemstype
     * @return Juniorspiller/Seniorspiller som String
     */
    public String getMemberType() {
        return memberType;
    }

    /**
     * Getter for konkurrencespillerens disciplin
     * @return "disciplin" som enum Disciplin (single/double/mixed_double)
     */
    public Disciplin getDisciplin() {
        return disciplin;
    }

    /**
     * Getter for konkurrencespillerens træningsresultat
     * @return "trainingResult" som int
     */
    public int getTrainingResult() {
        return trainingResult;
    }

    /**
     * Getter for træningsresultatets dato
     * @return "date" som String (dd-mm-yyyy)
     */
    public String getDate() {
        return date;
    }

    /**
     * Setter for konkurrencespillerens træningsresultat
     * @param trainingResult Det nye træningsresultat
     */
    public void setTrainingResult(int trainingResult) {
        this.trainingResult = trainingResult;
    }

    /**
     * Setter til datoen for det nye træningsresultat
     * @param date Datoen givet som en String (dd-mm-yyyy)
     */
    public void setDate(String date) {
        this.date = date;
    }

    /**
     * toString metode
     * @return Konkurrencespillerens oplysninger
     */
    @Override
    public String toString() {
        return memberID + "," +
                memberType + "," +
                disciplin + "," +
                trainingResult + "," +
                date;
    }

    /**
     * Klassens sammenlignings metode, der sammenligner træningsresultatet
     * @param other objektet der skal sammenlignes med
     * @return -1, 0 eller 1, der indikere rækkefølgen af hvad der skal komme først
     */
    @Override
    public int compareTo(CompetitionPlayer other) {
        return Integer.compare(this.trainingResult, other.trainingResult);
    }

}
