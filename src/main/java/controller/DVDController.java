package controller;

import dao.DVDLibraryDao;
import dao.DVDLibraryDaoException;
import dao.DVDLibraryDaoImpl;
import dto.DVD;
import ui.DVDView;
import ui.UserIO;
import ui.UserIOConsoleImpl;
import java.time.LocalDate;

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
        try {
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
                        editDvd();
                    case 4:
                        viewDVD();
                        break;
                    case 5:
                        removeDVD();
                        break;
                    case 6:
                        keepGoing = false;
                        break;
                    default:
                        unknownCommand();
                }
            }
            exitMessage();
        }catch (DVDLibraryDaoException e){
            view.displayErrorMessage(e.getMessage());
        }
    }

    private int getMenuSelection(){
        return view.printMenuAndGetSelection();
    }


    private void addDVD () throws DVDLibraryDaoException {
        view.displayAddDVDBanner();
        DVD newDVD = view.getNewDVDInfo();
        dao.addDVD(newDVD.getTitle(), newDVD);
        view.displayADDSuccessBanner();
    }

    private void ListALLDVD() throws DVDLibraryDaoException {
        view.displayDisplayAllBanner();
        List<DVD> allDVD = dao.getAllDVD();
        view.displayDVDlist(allDVD);
    }

    private void viewDVD() throws DVDLibraryDaoException {
        view.displayDisplayDVDBanner();
        DVD dvd = dao.getDVD(view.getDVDTitleChoice());
        view.displayDVD(dvd);
    }

    private void removeDVD() throws DVDLibraryDaoException {
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

    private void editDvd() throws DVDLibraryDaoException  {
        view.displayEditDvdBanner();
        String title = view.getDVDTitleChoice();
        DVD dvdToEdit = dao.getDVD(title);
        if (dvdToEdit==null) {
            view.displayNullDvd();
        } else {
            view.displayDVD(dvdToEdit);
            int editMenuSelection = 0;
            boolean keepGoing = true;
            while (keepGoing) {
                editMenuSelection = view.printEditMenuAndGetSelection();

                switch (editMenuSelection){
                    case 1:
                        editReleaseDate(title);
                        break;
                    case 2:
                        editMpaaRating(title);
                        break;
                    case 3:
                        editDirectorName(title);
                        break;
                    case 4:
                        editUserRating(title);
                        break;
                    case 5:
                        editStudioName(title);
                        break;
                    case 6:
                        keepGoing = false;
                        break;
                    default:
                        unknownCommand();
                }
                if (keepGoing == false) {
                    break;
                }
            }
        }
    }


    private void editReleaseDate(String title) throws DVDLibraryDaoException {
        view.displayEditReleaseDateBanner();
        LocalDate newReleaseDate = view.getReleaseDate();
        DVD editedDvd = dao.changeReleaseDate(title, newReleaseDate);
        view.displayEditResult();
    }
    private void editMpaaRating(String title) throws DVDLibraryDaoException {
        //view.displayEditMpaaRatingBanner();
        String newMpaaRating = view.getMpaaRating();
        DVD editedDvd = dao.changeMPAARating(title, newMpaaRating);
        view.displayEditResult();
    }
    private void editDirectorName(String title) throws DVDLibraryDaoException {
        //view.displayEditDirectorNameBanner();
        String newDirectorName = view.getDirectorName();
        DVD editedDvd = dao.changeDirectorsName(title, newDirectorName);
        view.displayEditResult();
    }
    private void editUserRating(String title) throws DVDLibraryDaoException{
        String newUserRating = view.getUserRating();
        DVD editedDvd = dao.changeUserRating(title, newUserRating);
        view.displayEditResult();
    }
    private void editStudioName(String title) throws DVDLibraryDaoException {
        String newStudioName = view.getStudioName();
        DVD editedDvd = dao.changeStudio(title, newStudioName);
        view.displayEditResult();
    }



}




