package ui;

import model.Disciplin;
import model.GameCategory;
import model.Junior;
import model.Member;
import model.Senior;
import org.junit.jupiter.api.Test;
import util.MemberAgeComparator;
import util.MemberNameComparator;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ChairmanRequirementsTest {

    @Test
    void shouldSortMembersByNameForChairmanOverview() {
        // Arrange:
        // Create a small unsorted member list (the same kind of list ChairmanUI shows)
        ArrayList<Member> members = new ArrayList<>();
        members.add(new Senior("Zara", 35, true, Disciplin.SINGLE, GameCategory.EXERCISE_PLAYER));
        members.add(new Junior("Adam", 16, true, Disciplin.DOUBLE, GameCategory.COMPETITION_PLAYER));
        members.add(new Senior("Mikkel", 42, false, Disciplin.MIXED_DOUBLE, GameCategory.EXERCISE_PLAYER));

        // Act:
        // Call the production sorting logic used indirectly by ChairmanUI option "Vis medlem (navn)"
        MemberNameComparator.sortByName(members);

        // Assert:
        // Verify the list is now sorted alphabetically by name
        assertEquals("Adam", members.get(0).getName());
        assertEquals("Mikkel", members.get(1).getName());
        assertEquals("Zara", members.get(2).getName());
    }

    @Test
    void shouldSortMembersByAgeForChairmanOverview() {
        // Arrange:
        // Create an unsorted member list with mixed ages
        ArrayList<Member> members = new ArrayList<>();
        members.add(new Senior("Ida", 41, true, Disciplin.SINGLE, GameCategory.EXERCISE_PLAYER));
        members.add(new Junior("Noah", 13, true, Disciplin.DOUBLE, GameCategory.COMPETITION_PLAYER));
        members.add(new Senior("Lars", 27, false, Disciplin.MIXED_DOUBLE, GameCategory.EXERCISE_PLAYER));

        // Act:
        // Call the production sorting logic used indirectly by ChairmanUI option "Vis medlem (alder)"
        MemberAgeComparator.sortByAge(members);

        // Assert:
        // Verify ascending age order
        assertEquals(13, members.get(0).getAge());
        assertEquals(27, members.get(1).getAge());
        assertEquals(41, members.get(2).getAge());
    }
}