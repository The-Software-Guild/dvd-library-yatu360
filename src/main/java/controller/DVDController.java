package controller;

import dao.DVDLibraryDao;
import dao.DVDLibraryDaoImpl;
import dto.DVD;
import ui.DVDView;
import ui.UserIO;
import ui.UserIOConsoleImpl;

import java.util.ArrayList;
import java.util.List;

public class DVDController {

    private DVDView view;
    private DVDLibraryDao dao;

    public DVDController(DVDView view, DVDLibraryDao dao){
        this.view = view;
        this.dao = dao;
    }


    public void run() {
        boolean keepGoing = true;
        int menuSelection = 0;
        while (keepGoing) {


            menuSelection = getMenuSelection();

            switch (menuSelection) {
                case 1:
                    ListALLDVD();
                    break;
                case 2:
                    addDVD();
                    break;
                case 3:
                    viewDVD();
                    break;
                case 4:
                    removeDVD();
                    break;
                case 5:
                    keepGoing = false;
                    break;
                default:
                    unknownCommand();
            }
        }
        exitMessage();
    }

    private int getMenuSelection(){
        return view.printMenuAndGetSelection();
    }


    private void addDVD (){
        view.displayAddDVDBanner();
        DVD newDVD = view.getNewDVDInfo();
        dao.addDVD(newDVD.getTitle(), newDVD);
        view.displayADDSuccessBanner();
    }

    private void ListALLDVD(){
        view.displayDisplayAllBanner();
        List<DVD> allDVD = dao.getAllDVD();
        view.displayDVDlist(allDVD);
    }

    private void viewDVD(){
        view.displayDisplayDVDBanner();
        DVD dvd = dao.getDVD(view.getDVDTitleChoice());
        view.displayDVD(dvd);
    }

    private void removeDVD(){
        view.displayRemoveDVDBanner();
        DVD dvd = dao.removeDVD(view.getDVDTitleChoice());
        view.removeDVD(dvd);
    }

    private void unknownCommand() {
        view.displayUnknownCommandBanner();
    }

    private void exitMessage() {
        view.displayExitBanner();
    }
}



