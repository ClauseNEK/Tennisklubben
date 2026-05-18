package util;
import model.Member;

import java.util.ArrayList;
import java.util.Comparator;

public class MemberAgeComparator {
    /**
     * Tager en ArrayList af medlemmer og sorter den efter deres alder.
     * @param memberList ArrayList af medlemmer.
     */
    public static void sortByAge(ArrayList<Member> memberList) {
        memberList.sort(Comparator.comparingInt(Member::getAge));

    }
}
