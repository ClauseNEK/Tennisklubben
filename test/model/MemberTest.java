package model;

import org.junit.Test;
import validation.MemberAgeValidator;

import static model.Disciplin.*;
import static model.GameCategory.*;
import static org.junit.jupiter.api.Assertions.*;

public class MemberTest {
    /**
     * Tester at det rigtige navn bliver fundet
     */
    @Test
    public void testName() {
        Member bobby = new Junior("Bobby", 17, true, SINGLE, COMPETITION_PLAYER);
        assertEquals("Bobby", bobby.getName());
    }

    /**
     * Tester at man kan redigere i navnet
     */
    @Test
    public void testSetName() {
        Member bobby = new Junior("Bobby", 17, true, SINGLE, COMPETITION_PLAYER);
        bobby.setName("Rebekka");
        assertEquals("Rebekka", bobby.getName());
    }

    /**
     * Tester at den rigtige alder bliver fundet
     */
    @Test
    public void testAge() {
        Member bobby = new Junior("Bobby", 17, true, SINGLE, COMPETITION_PLAYER);
        assertEquals(17, bobby.getAge());
    }

    /**
     * Tester at man kan redigere i alderen
     */
    @Test
    public void testSetAge() {
        Member franky = new Senior("Franky", 72, false, DOUBLE, EXERCISE_PLAYER);
        franky.setValidator(new MemberAgeValidator());
        franky.setAge(27);
        assertEquals(27, franky.getAge());
    }

    /**
     * Tester at medlemmet har et aktivt medlemskab
     */
    @Test
    public void testActiveMembership() {
        Member bobby = new Junior("Bobby", 17, true, SINGLE, COMPETITION_PLAYER);
        assertTrue(bobby.membership());
    }

    /**
     * Tester at medlemmet har et passivt medlemskab
     */
    @Test
    public void testPassivMembership() {
        Member franky = new Senior("Franky", 72, false, DOUBLE, EXERCISE_PLAYER);
        assertFalse(franky.membership());
    }

    /**
     * Tester at man kan redigere i medlemskabet
     */
    @Test
    public void testSetMemberShip() {
        Member bobby = new Junior("Bobby", 17, true, SINGLE, COMPETITION_PLAYER);
        bobby.setMembership(false);
        assertFalse(bobby.membership());
    }
/*
    //Denne test virker hvis KUN køre den, men fejler hvis man kører alle testene samtidig.
    @Test
    public void testMemberID() {
        Member bobby = new Junior("Bobby", 17, true, SINGLE, COMPETITION_PLAYER);
        assertEquals(1, bobby.getMemberid());
    }
*/

    /**
     * Tester at medlemstypen er en junior
     */
    @Test
    public void testMemberTypeJunior() {
        Member bobby = new Junior("Bobby", 17, true, SINGLE, COMPETITION_PLAYER);
        assertEquals("Juniorspiller", bobby.getMemberType());
    }

    /**
     * Tester at medlemstypen er en senior
     */
    @Test
    public void testMemberTypeSenior() {
        Member franky = new Senior("Franky", 72, false, DOUBLE, EXERCISE_PLAYER);
        assertEquals("Seniorspiller", franky.getMemberType());
    }

    /**
     * Tester at disciplinen er "Single"
     */
    @Test
    public void testDisciplinSingle() {
        Member bobby = new Junior("Bobby", 17, true, SINGLE, COMPETITION_PLAYER);
        assertEquals(SINGLE, bobby.getDisciplin());
    }

    /**
     * Tester at disciplinen er "Double"
     */
    @Test
    public void testDisciplinDouble() {
        Member franky = new Senior("Franky", 72, false, DOUBLE, EXERCISE_PLAYER);
        assertEquals(DOUBLE, franky.getDisciplin());
    }

    /**
     * Tester at disciplinen er "Mixed Double"
     */
    @Test
    public void testDisciplinMixedDouble() {
        Member susan = new Senior("Franky", 72, true, MIXED_DOUBLE, EXERCISE_PLAYER);
        assertEquals(MIXED_DOUBLE, susan.getDisciplin());
    }

    /**
     * Tester at man kan redigere i Disciplinen
     */
    @Test
    public void testSetDisciplin() {
        Member susan = new Senior("Franky", 72, true, MIXED_DOUBLE, EXERCISE_PLAYER);
        susan.setDisciplin(SINGLE);
        assertEquals(SINGLE, susan.getDisciplin());
    }

    /**
     * Tester at medlemmet er en konkurrencespiller
     */
    @Test
    public void testGameCategoryCompetition() {
        Member bobby = new Junior("Bobby", 17, true, SINGLE, COMPETITION_PLAYER);
        assertEquals(COMPETITION_PLAYER, bobby.getGameCategory());
    }

    /**
     * Tester at medlemmet er en motionist
     */
    @Test
    public void testGameCategoryExercise() {
        Member susan = new Senior("Franky", 72, true, MIXED_DOUBLE, EXERCISE_PLAYER);
        assertEquals(EXERCISE_PLAYER, susan.getGameCategory());
    }

    /**
     * Tester at man kan redigere i spillerkategori
     */
    @Test
    public void testSetGameCategory() {
        Member bobby = new Junior("Bobby", 17, true, SINGLE, COMPETITION_PLAYER);
        bobby.setGameCategory(EXERCISE_PLAYER);
        assertEquals(EXERCISE_PLAYER, bobby.getGameCategory());
    }

    /**
     * Tester at convertMembership() returnere "aktiv" når medlemskabet er true
     */
    @Test
    public void testConvertMembershipTrue() {
        Member bobby = new Junior("Bobby", 17, true, SINGLE, COMPETITION_PLAYER);
        assertEquals("aktiv", bobby.convertMembership());
    }

    /**
     * Tester at convertMembership() returnere "passiv" når medlemskabet er false
     */
    @Test
    public void testConvertMembershipFalse() {
        Member bobby = new Junior("Bobby", 17, false, SINGLE, COMPETITION_PLAYER);
        assertEquals("passiv", bobby.convertMembership());
    }


    //Test at der bliver kastet en exception hvis der ikke bliver indtastet et navn

    //Test at der bliver kastet en exception hvis alder ikke bliver indtastet eller hvis medlemmet er for ung

}