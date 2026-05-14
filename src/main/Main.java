package main;

import file.FileHandlerMembers;
import file.FileHandlerPayment;
import ui.SmashUI;

public class Main {
    void main(){
        //Start med at indlæse de eksisterende CSV filer
        FileHandlerMembers handlerMembers = new FileHandlerMembers();
        handlerMembers.readCSV();

        FileHandlerPayment handlerPayment = new FileHandlerPayment();
        handlerPayment.readRestanceCSV();

        SmashUI smashui = new SmashUI();
        smashui.start();
    }
}
