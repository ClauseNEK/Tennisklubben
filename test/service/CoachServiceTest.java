package service;

import model.CompetitionPlayer;
import model.Disciplin;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;
import static service.CoachService.*;

public class CoachServiceTest {

    /**
     * Tester at konkurrencespilleren med det bedste træningsresultat bliver placeret først, når listen bliver sorteret.
     */
    @Test
    public void testSortListByResults() {
        ArrayList<CompetitionPlayer> competitionList = new ArrayList<>();
        competitionList.add(new CompetitionPlayer(1, "Juniorspiller ", Disciplin.SINGLE, 2, "12-12-2001"));
        competitionList.add(new CompetitionPlayer(2, "Juniorspiller ", Disciplin.DOUBLE, 10, "12-12-2001"));
        competitionList.add(new CompetitionPlayer(3, "Juniorspiller ", Disciplin.MIXED_DOUBLE, 1, "12-12-2001"));
        competitionList.add(new CompetitionPlayer(4, "Juniorspiller ", Disciplin.MIXED_DOUBLE, 8, "12-12-2001"));
        competitionList.add(new CompetitionPlayer(5, "Juniorspiller ", Disciplin.SINGLE, 5, "12-12-2001"));

        sortListByResults(competitionList);

        assertEquals(2, competitionList.get(0).getMemberID());
    }

}