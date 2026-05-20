package model;
/**
 * Repræsenterer en turnering i tennisklubben.
 * Klassen gemmer turneringens ID, navn, dato, disciplin, spilkategori
 * og en liste over ID'er på de medlemmer, der deltager i turneringen.
 */
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Tournament {

    private int tournamentId;
    private static int tournamentIdCounter = 0;

    private String name;
    private LocalDate date;
    private Disciplin disciplin;
    private GameCategory gameCategory;
    private List<Integer> participantIds;  // referencer til Member.memberid

    /**
     * Konstruktør til nye turneringer (auto-genererer ID).
     * Opretter en ny turnering og genererer automatisk et nyt turnerings-ID.
     * Listen over deltagere starter som tom.
     * @param name turneringens navn
     * @param date datoen for turneringen
     * @param disciplin turneringens disciplin, fx single eller double
     * @param gameCategory turneringens spilkategori, fx motionist eller konkurrencespiller
     */
    public Tournament(String name, LocalDate date, Disciplin disciplin, GameCategory gameCategory) {
        tournamentIdCounter++;
        this.tournamentId = tournamentIdCounter;
        this.name = name;
        this.date = date;
        this.disciplin = disciplin;
        this.gameCategory = gameCategory;
        this.participantIds = new ArrayList<>();
    }


    /**
     * Konstruktør til indlæsning fra CSV (bevarer eksisterende ID).
     * Opretter en turnering ud fra data, der er indlæst fra CSV.
     * Det eksisterende turnerings-ID bevares, og ID-tælleren opdateres,
     * så nye turneringer ikke får samme ID.
     * @param tournamentId turneringens eksisterende ID
     * @param name turneringens navn
     * @param date datoen for turneringen
     * @param disciplin turneringens disciplin, fx single eller double
     * @param gameCategory turneringens spilkategori, fx motionist eller konkurrencespiller
     * @param participantIds liste med medlems-ID'er for deltagere i turneringen
     */
    public Tournament(int tournamentId, String name, LocalDate date, Disciplin disciplin,
                      GameCategory gameCategory, List<Integer> participantIds) {
        this.tournamentId = tournamentId;
        // Hold counter i sync så næste nye turnering ikke får et duplikeret ID
        if (tournamentId > tournamentIdCounter) {
            tournamentIdCounter = tournamentId;
        }
        this.name = name;
        this.date = date;
        this.disciplin = disciplin;
        this.gameCategory = gameCategory;
        this.participantIds = participantIds;
    }

    /**
     * Getter for turneringens ID
     * @return "tournamentId" som en int.
     */
    public int getTournamentId() { return tournamentId; }

    /**
     * Getter for turneringens navn
     * @return "name" som String
     */
    public String getName() { return name; }

    /**
     * Getter for turneringens dato
     * @return "date" som LocalDate
     */
    public LocalDate getDate() { return date; }

    /**
     * Getter for turneringens disciplin.
     * @return "disciplin" som Enum(Disciplin).
     */
    public Disciplin getDisciplin() { return disciplin; }

    /**
     * Getter for turneringens spilkategori.
     * @return "gameCategory" som Enum(GameCategory).
     */
    public GameCategory getGameCategory() { return gameCategory; }

    /**
     * Getter for liste med medlems-ID'er for deltagere i turneringen.
     * @return listen "participantIds".
     */
    public List<Integer> getParticipantIds() { return participantIds; }

    // Setters

    /**
     * Setter for turneringens navn.
     * @param name Turneringens navn.
     */
    public void setName(String name) { this.name = name; }

    /**
     * Setter for turneringens dato
     * @param date Turneringens dato.
     */
    public void setDate(LocalDate date) { this.date = date; }

    /**
     * Setter for turneringens disciplin.
     * @param disciplin Turneringens disciplin.
     */
    public void setDisciplin(Disciplin disciplin) { this.disciplin = disciplin; }

    /**
     * Setter for turneringens spilkategori.
     * @param gameCategory Turneringens spilkategori.
     */
    public void setGameCategory(GameCategory gameCategory) { this.gameCategory = gameCategory; }

    /**
     * Tilføjer et medlem til turneringen, hvis medlemmet ikke allerede er tilføjet.
     * @param memberId ID'et på medlemmet, der skal tilføjes
     */
    public void addParticipant(int memberId) {
        if (!participantIds.contains(memberId)) {
            participantIds.add(memberId);
        }
    }

    /**
     * Fjerner et medlem fra turneringens deltagerliste.
     * @param memberId ID'et på medlemmet, der skal fjernes
     */
    public void removeParticipant(int memberId) {
        participantIds.remove(Integer.valueOf(memberId));  // OBS: Integer.valueOf, ikke int
    }

    /**
     * Konverterer turneringen til en CSV-linje.
     * Deltager-ID'er adskilles med tegnet |, så de ikke kolliderer med kommaerne i CSV-formatet.
     * @return turneringens oplysninger som CSV-linje
     */
    public String toTournamentCSV() {
        // Deltagere adskilles med | så de ikke kolliderer med CSV's komma
        StringBuilder participants = new StringBuilder();
        for (int i = 0; i < participantIds.size(); i++) {
            if (i > 0) participants.append("|");
            participants.append(participantIds.get(i));
        }
        return tournamentId + "," + name + "," + date + "," +
                disciplin + "," + gameCategory + "," + participants;
    }

    /**
     * Returnerer en læsevenlig tekst med turneringens oplysninger.
     * @return turneringen som tekst
     */
    @Override
    public String toString() {
        return "Turnering #" + tournamentId + ": " + name
                + "\nDato: " + date
                + "\nDisciplin: " + disciplin
                + "\nAktivitetsform: " + gameCategory
                + "\nAntal deltagere: " + participantIds.size() + "\n";
    }
}