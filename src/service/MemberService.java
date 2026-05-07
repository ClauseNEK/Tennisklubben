package service;

import file.FileHandlerMembers;
import model.Disciplin;
import model.GameCategory;
import model.Member;

import java.util.ArrayList;

public class MemberService {

    //Redigere medlemmets alder og opdatere CSV filen.
    public void editMemberAge(Member member, int newAge) {
        member.setAge(newAge);
        //writeToFile();
        //Enten skal writeToFile ske her eller også skal den kaldes når man er færdig med at redigere
    }

    //Redigere medlemmets membership (passivt/aktivt) og opdatere CSV filen.
    public void editMemberShip(Member member, boolean newMemberShip) {
        member.setMembership(newMemberShip);
        //writeToFile();
    }

    //Redigere medlemmets disciplin og opdatere CSV filen.
    public void editMemberDisciplin(Member member, Disciplin newDisciplin) {
        member.setDisciplin(newDisciplin);
        //writeToFile();
    }

    //Redigere medlemmets aktivitetsform (motionist eller konkurrencespiller) og opdatere CSV filen.
    public void editMemberGameCategory(Member member, GameCategory newGameCategory) {
        member.setGameCategory(newGameCategory);
        //writeToFile();
    }


}
