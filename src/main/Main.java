package main;

import file.FileHandlerMembers;
import file.FileHandlerPayment;
import file.FileHandlerTrainingResults;
import ui.SmashUI;

public class Main {
    void main(){
        //Start med at indlæse de eksisterende CSV filer
        FileHandlerMembers handlerMembers = new FileHandlerMembers();
        handlerMembers.readCSV();

        FileHandlerPayment handlerPayment = new FileHandlerPayment();
        handlerPayment.readPaymentsCSV();
        handlerPayment.readRestanceCSV();

        FileHandlerTrainingResults handlerTrainingResults = new FileHandlerTrainingResults();
        handlerTrainingResults.readTrainingResultsCSV();

        SmashUI smashui = new SmashUI();
        smashui.start();
    }
}
