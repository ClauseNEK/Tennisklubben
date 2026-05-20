package service;

import exceptions.TournamentNotFoundException;
import model.Tournament;
import org.junit.Test;

import java.time.LocalDate;

import static file.FileHandlerTournaments.tournamentList;
import static model.Disciplin.*;
import static model.GameCategory.*;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Tester at exceptions bliver kastet korrekt fra TournamentService.
 */
public class TournamentServiceTest {

    /**
     * Tester at TournamentService.findTournament kaster TournamentNotFoundException
     * for et ikke-eksisterende turnerings-ID.
     */
    @Test
    public void testTournamentNotFoundException_thrownForUnknownId() {
        tournamentList.clear();
        assertThrows(TournamentNotFoundException.class,
                () -> TournamentService.findTournament(99999));
    }

    /**
     * Tester at findTournament IKKE kaster en exception, når turneringen faktisk findes.
     */
    @Test
    public void testTournamentNotFoundException_notThrownWhenTournamentExists() {
        tournamentList.clear();
        Tournament t = new Tournament("Test Cup", LocalDate.of(2026, 1, 1),
                SINGLE, COMPETITION_PLAYER);
        tournamentList.add(t);

        Tournament found = TournamentService.findTournament(t.getTournamentId());
        assertNotNull(found);
        assertEquals("Test Cup", found.getName());
    }

    /**
     * Tester at TournamentNotFoundException's besked indeholder det turnerings-ID,
     * der ikke blev fundet.
     */
    @Test
    public void testTournamentNotFoundException_messageContainsId() {
        tournamentList.clear();
        TournamentNotFoundException e = assertThrows(TournamentNotFoundException.class,
                () -> TournamentService.findTournament(54321));
        assertTrue(e.getMessage().contains("54321"));
    }

    /**
     * Tester at findTournament kaster exception også når listen indeholder andre turneringer,
     * men ikke den vi søger efter.
     */
    @Test
    public void testTournamentNotFoundException_thrownEvenWhenListIsNotEmpty() {
        tournamentList.clear();
        Tournament t = new Tournament("Anden turnering", LocalDate.of(2026, 5, 1),
                DOUBLE, COMPETITION_PLAYER);
        tournamentList.add(t);

        //ID 99999 findes ikke selvom listen ikke er tom
        assertThrows(TournamentNotFoundException.class,
                () -> TournamentService.findTournament(99999));
    }
}
