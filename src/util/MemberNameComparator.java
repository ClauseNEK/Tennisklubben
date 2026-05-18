package util;

import model.Member;

import java.util.ArrayList;
import java.util.Comparator;

public class MemberNameComparator {
    /**
     * Tager en ArrayList af medlemmer og sorter den efter deres navn.
     * @param memberListName ArrayList af medlemmer.
     */
    public static void sortByName(ArrayList<Member> memberListName){
        memberListName.sort(Comparator.comparing(Member::getName));
    }
}
