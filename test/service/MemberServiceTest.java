package service;

import exceptions.InvalidAgeException;
import exceptions.MemberNotFoundException;
import model.*;
import org.junit.Test;
import validation.MemberAgeValidator;

import static file.FileHandlerMembers.memberList;
import static model.Disciplin.*;
import static model.GameCategory.*;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Tester at exceptions bliver kastet korrekt fra MemberService og det tilhørende lag (Member, MemberAgeValidator).
 */
public class MemberServiceTest {

    // ==================== InvalidAgeException ====================

    /**
     * Tester at MemberAgeValidator kaster InvalidAgeException, når alderen er negativ.
     */
    @Test
    public void testInvalidAgeException_negativeAge() {
        MemberAgeValidator validator = new MemberAgeValidator();
        assertThrows(InvalidAgeException.class, () -> validator.validate(-1));
    }

    /**
     * Tester at MemberAgeValidator kaster InvalidAgeException, når alderen er over 120.
     */
    @Test
    public void testInvalidAgeException_tooOldAge() {
        MemberAgeValidator validator = new MemberAgeValidator();
        assertThrows(InvalidAgeException.class, () -> validator.validate(150));
    }

    /**
     * Tester at MemberAgeValidator IKKE kaster en exception for gyldige aldre,
     * herunder grænseværdierne 0 og 120.
     */
    @Test
    public void testInvalidAgeException_notThrownForValidAges() {
        MemberAgeValidator validator = new MemberAgeValidator();
        //Må ikke kaste noget
        validator.validate(0);
        validator.validate(30);
        validator.validate(120);
    }

    /**
     * Tester at Member.setAge propagerer InvalidAgeException, når validatoren afviser alderen.
     */
    @Test
    public void testInvalidAgeException_thrownFromSetAge() {
        Member m = new Junior("Test", 17, true, SINGLE, COMPETITION_PLAYER);
        m.setValidator(new MemberAgeValidator());
        assertThrows(InvalidAgeException.class, () -> m.setAge(200));
    }

    /**
     * Tester at InvalidAgeException's besked bevares og indeholder relevant information.
     */
    @Test
    public void testInvalidAgeException_messageIsPreserved() {
        MemberAgeValidator validator = new MemberAgeValidator();
        InvalidAgeException e = assertThrows(InvalidAgeException.class, () -> validator.validate(-5));
        assertNotNull(e.getMessage());
        assertFalse(e.getMessage().isEmpty());
    }

    // ==================== MemberNotFoundException ====================

    /**
     * Tester at MemberService.seachForMember kaster MemberNotFoundException,
     * når der søges efter et ID som ikke findes i memberList.
     */
    @Test
    public void testMemberNotFoundException_fromSeachForMember() {
        memberList.clear();
        assertThrows(MemberNotFoundException.class,
                () -> MemberService.seachForMember(99999));
    }

    /**
     * Tester at seachForMember IKKE kaster en exception, når medlemmet faktisk findes.
     */
    @Test
    public void testMemberNotFoundException_notThrownWhenMemberExists() {
        memberList.clear();
        Member m = new Senior("Anders", 40, true, SINGLE, COMPETITION_PLAYER);
        memberList.add(m);
        Member found = MemberService.seachForMember(m.getMemberid());
        assertNotNull(found);
    }

    /**
     * Tester at MemberNotFoundException's besked indeholder det medlemsID, der ikke blev fundet.
     * Det er vigtigt for fejlsøgning, at brugeren får at vide præcis hvilket ID, der mangler.
     */
    @Test
    public void testMemberNotFoundException_messageContainsId() {
        memberList.clear();
        MemberNotFoundException e = assertThrows(MemberNotFoundException.class,
                () -> MemberService.seachForMember(12345));
        assertTrue(e.getMessage().contains("12345"));
    }
}
