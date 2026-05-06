package file;

import model.Disciplin;
import model.GameCategory;
import model.Member;
import model.Membership;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.lang.classfile.Interfaces;
import java.sql.Timestamp;
import java.util.ArrayList;

public class FileHandlerMembers {

    final static String members = "scr/Members";
    private static ArrayList<Member> memberList = new ArrayList<Member>();


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

    //læser CSV filen "members"
    public void readCSV() {

        try (BufferedReader reader =
                     new BufferedReader(new FileReader(members))) {

            String line;

            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");

                String name = parts[0];
                int age = Integer.parseInt(parts[1]);
                //Membership membership = new Membership(parts[2]);
                Boolean.valueOf(parts[2]); //Skal ikke være en boolean, men skal stå som Membership interface
                int memberid = Integer.parseInt(parts[3]);
                Disciplin disciplin = Disciplin.valueOf(parts[4]);
                GameCategory gameCategory = GameCategory.valueOf(parts[5]);

            }

        } catch (Exception e){

        }

    }



    public void editMember() {

    }

    public void removeMember() {

    }


}
