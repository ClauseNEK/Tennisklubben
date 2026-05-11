package util;
import model.Member;

import java.util.ArrayList;
import java.util.Comparator;

public class MemberAgeComparator {
    public static void sortByAge(ArrayList<Member> memberList) {
        memberList.sort(Comparator.comparingInt(Member::getAge));

    }
}
