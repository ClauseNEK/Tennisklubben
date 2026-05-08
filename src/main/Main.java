package main;

import file.FileHandlerMembers;
import ui.SmashUI;

public class Main {
    void main(){
        //Start med at indlæse de eksisterende CSV filer
        FileHandlerMembers handlerMembers = new FileHandlerMembers();
        handlerMembers.readCSV();

        SmashUI smashui = new SmashUI();
        smashui.start();
    }
}
