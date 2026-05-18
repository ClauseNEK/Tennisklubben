package model;

import org.junit.Test;

import static model.Disciplin.*;
import static org.junit.jupiter.api.Assertions.*;

public class CompetitionPlayerTest {

    /**
     * Tester at det rigtige medlemsID bliver fundet
     */
    @Test
    public void testGetMemberID() {
        CompetitionPlayer betty = new CompetitionPlayer(1, "Seniorspiller", DOUBLE, 24, "14-02-2012");
        assertEquals(1, betty.getMemberID());
    }

    /**
     * Tester at den rigtige medlemstype bliver fundet
     */
    @Test
    public void testGetMemberType() {
        CompetitionPlayer betty = new CompetitionPlayer(1, "Seniorspiller", DOUBLE, 24, "14-02-2012");
        assertEquals("Seniorspiller", betty.getMemberType());
    }

    /**
     * Tester at den rigtige disciplin bliver fundet
     */
    @Test
    public void testGetDisciplin() {
        CompetitionPlayer betty = new CompetitionPlayer(1, "Seniorspiller", DOUBLE, 24, "14-02-2012");
        assertEquals(DOUBLE, betty.getDisciplin());
    }

    /**
     * Tester at det rigtige træningsresultat bliver fundet
     */
    @Test
    public void testGetTrainingResult() {
        CompetitionPlayer betty = new CompetitionPlayer(1, "Seniorspiller", DOUBLE, 24, "14-02-2012");
        assertEquals(24, betty.getTrainingResult());
    }

    /**
     * Tester at den rige dato bliver fundet
     */
    @Test
    public void testGetDate() {
        CompetitionPlayer betty = new CompetitionPlayer(1, "Seniorspiller", DOUBLE, 24, "14-02-2012");
        assertEquals("14-02-2012", betty.getDate());
    }

    /**
     * Tester at det nye træningsresultat bliver gemt og fundet
     */
    @Test
    public void testSetTrainingResult() {
        CompetitionPlayer betty = new CompetitionPlayer(1, "Seniorspiller", DOUBLE, 24, "14-02-2012");
        betty.setTrainingResult(27);
        assertEquals(27, betty.getTrainingResult());
    }

    /**
     * Tester at den nye dato bliver gemt og fundet
     */
    @Test
    public void testSetDate() {
        CompetitionPlayer betty = new CompetitionPlayer(1, "Seniorspiller", DOUBLE, 24, "14-02-2012");
        betty.setDate("04-07-2024");
        assertEquals("04-07-2024", betty.getDate());
    }

}