package model;

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

    // Konstruktør til nye turneringer (auto-genererer ID)
    public Tournament(String name, LocalDate date, Disciplin disciplin, GameCategory gameCategory) {
        tournamentIdCounter++;
        this.tournamentId = tournamentIdCounter;
        this.name = name;
        this.date = date;
        this.disciplin = disciplin;
        this.gameCategory = gameCategory;
        this.participantIds = new ArrayList<>();
    }

    // Konstruktør til indlæsning fra CSV (bevarer eksisterende ID)
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

    // Getters
    public int getTournamentId() { return tournamentId; }
    public String getName() { return name; }
    public LocalDate getDate() { return date; }
    public Disciplin getDisciplin() { return disciplin; }
    public GameCategory getGameCategory() { return gameCategory; }
    public List<Integer> getParticipantIds() { return participantIds; }

    // Setters
    public void setName(String name) { this.name = name; }
    public void setDate(LocalDate date) { this.date = date; }
    public void setDisciplin(Disciplin disciplin) { this.disciplin = disciplin; }
    public void setGameCategory(GameCategory gameCategory) { this.gameCategory = gameCategory; }

    public void addParticipant(int memberId) {
        if (!participantIds.contains(memberId)) {
            participantIds.add(memberId);
        }
    }

    public void removeParticipant(int memberId) {
        participantIds.remove(Integer.valueOf(memberId));  // OBS: Integer.valueOf, ikke int
    }

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

    @Override
    public String toString() {
        return "Turnering #" + tournamentId + ": " + name
                + "\nDato: " + date
                + "\nDisciplin: " + disciplin
                + "\nAktivitetsform: " + gameCategory
                + "\nAntal deltagere: " + participantIds.size() + "\n";
    }
}