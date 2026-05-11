package util;

import model.Member;

import java.util.ArrayList;
import java.util.Comparator;

public class MemberNameComparator {
    public static void sortByName(ArrayList<Member> memberListName){
        memberListName.sort(Comparator.comparing(Member::getName));
    }
}
