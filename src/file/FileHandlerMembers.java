package file;

import model.*;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;

public class FileHandlerMembers {

    final static String members = "src/Members";
    private static ArrayList<Member> memberList = new ArrayList<Member>(); //Dette kunne blive gemt i en Database package for at holde tingene adskilte.


    //Skriver arraylisten til csv filen "members"
    public void writeToFile() {
        try {
            FileWriter fileWriter = new FileWriter(members);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);

            for (Member member : memberList) {
                bufferedWriter.write(member.toCSV());
                bufferedWriter.newLine();
            }

            bufferedWriter.close();

        } catch (Exception e) {

        }

    }

    //Læser CSV filen "members"
    public void readCSV() {

        try (BufferedReader reader =
                     new BufferedReader(new FileReader(members))) {

            String line;

            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");

                String name = parts[0];
                int age = Integer.parseInt(parts[1]);
                boolean activeMembership = Boolean.parseBoolean(parts[2]);
                int memberid = Integer.parseInt(parts[3]);
                Disciplin disciplin = Disciplin.valueOf(parts[4]);
                GameCategory gameCategory = GameCategory.valueOf(parts[5]);

                if(age < 18) {
                    memberList.add(new Junior(name, age, activeMembership, memberid, disciplin, gameCategory));
                } else {
                    memberList.add(new Senior(name, age, activeMembership, memberid, disciplin, gameCategory));
                }

            }

        } catch (Exception e){

        }

    }

    //Finder det næste medlemsID
    //Den finder det seneste medlemsID i listen og lægger 1 til.
    //Denne metode skal kaldes når der oprettes et nyt medlem (int memberID = getNextMemberID())
    public int getNextMemberID() {
        return memberList.getLast().getMemberid()+1;

    }

}
