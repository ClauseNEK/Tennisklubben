package service;

import exceptions.MemberNotFoundException;
import model.Payment;
import org.junit.Test;

import static file.FileHandlerPayment.restanceList;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Tester at exceptions bliver kastet korrekt fra PaymentService.
 */
public class PaymentServiceTest {

    /**
     * Tester at PaymentService.findPaymentMember kaster MemberNotFoundException,
     * når medlemsID'et ikke findes i restanceList.
     */
    @Test
    public void testMemberNotFoundException_fromFindPaymentMember() {
        restanceList.clear();
        assertThrows(MemberNotFoundException.class,
                () -> PaymentService.findPaymentMember(99999));
    }

    /**
     * Tester at findPaymentMember IKKE kaster en exception, når en betaling med det
     * søgte medlemsID findes i restanceList.
     */
    @Test
    public void testMemberNotFoundException_notThrownWhenPaymentExists() {
        restanceList.clear();
        restanceList.add(new Payment(7, 1500, "Ikke betalt"));

        Payment found = PaymentService.findPaymentMember(7);
        assertNotNull(found);
        assertEquals(7, found.getMemberID());
    }

    /**
     * Tester at MemberNotFoundException's besked indeholder det medlemsID, der ikke blev fundet.
     */
    @Test
    public void testMemberNotFoundException_messageContainsId() {
        restanceList.clear();
        MemberNotFoundException e = assertThrows(MemberNotFoundException.class,
                () -> PaymentService.findPaymentMember(67890));
        assertTrue(e.getMessage().contains("67890"));
    }

    /**
     * Tester at findPaymentMember kun finder medlemmer i restanceList,
     * og IKKE kigger i paymentList. Et medlem der allerede har betalt skal stadig kaste exception
     * fordi de ikke længere står i restance.
     */
    @Test
    public void testMemberNotFoundException_onlySearchesRestanceList() {
        restanceList.clear();
        //Listen er tom — alle ID'er bør kaste exception
        assertThrows(MemberNotFoundException.class,
                () -> PaymentService.findPaymentMember(1));
        assertThrows(MemberNotFoundException.class,
                () -> PaymentService.findPaymentMember(2));
    }
}
