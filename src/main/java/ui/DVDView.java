package ui;

import dto.DVD;

import java.time.LocalDate;
import java.util.List;

public class DVDView {

    private UserIO io;

    public DVDView(UserIO io){
        this.io = io;
    }

    public int printMenuAndGetSelection() {
        io.print("Main Menu");
        io.print("1. List all DVDs");
        io.print("2. Add new DVD");
        io.print("3. Edit DVD");
        io.print("4. View a DVD");
        io.print("5. Remove a DVD");
        io.print("6. Exit");

        return io.readInt("Please select from the above choices.", 1, 6);
    }

    public int printEditMenuAndGetSelection() {
        io.print("Which field do you want to change?");
        io.print("Edit DVD menu");
        io.print("1. Release date");
        io.print("2. MPAA rating");
        io.print("3. Director's name");
        io.print("4. User rating");
        io.print("5. Studio name");
        io.print("6. Exit edit menu");
        return io.readInt("Please select from the above choices.", 1,6);
    }

    public DVD getNewDVDInfo(){
        String title = io.readString("Please enter DVD title");
        LocalDate releaseDate = io.readDate("Please enter date in the format YYYY-MM-DD");
        String MPAARating= io.readString("Please enter MPAA Rating:");
        String directorName= io.readString("Please enter director's name:");
        String studio= io.readString("Please studio name:");
        String userRating= io.readString("Please enter user rating:");
        DVD currentDVD = new DVD (title);
        currentDVD.setMPAARating(MPAARating);
        currentDVD.setReleaseDate(releaseDate);
        currentDVD.setStudio(studio);
        currentDVD.setUserRating(userRating);
        currentDVD.setDirectorName(directorName);
        return currentDVD;

    }

    public void displayDVDlist(List<DVD> dvdList){
        for (DVD currentDVD: dvdList){
            String dvdInfo = String.format("%s %s %s %s %s",
                    currentDVD.getTitle(),
                    currentDVD.getReleaseDate().toString(),
                    currentDVD.getMPAARating(),
                    currentDVD.getStudio(),
                    currentDVD.getUserRating());
            io.print(dvdInfo);
        }
    }

    public void displayDVD(DVD dvd){
        if (dvd != null){
            io.print(dvd.getTitle());
            io.print(dvd.getReleaseDate().toString());
            io.print(dvd.getMPAARating());
            io.print(dvd.getDirectorName());
            io.print(dvd.getStudio());
            io.print(dvd.getUserRating());
        }
    }

    public void removeDVD(DVD dvd){
        if (dvd != null){
            io.print("Successfully removed DVD");
        } else {
            io.print("No such DVD");
        }
        io.print("Please hit enter to continue");
    }


    public String getDVDTitleChoice(){
        return io.readString("Please enter the DVD title");
    }


    public void displayDisplayDVDBanner () {
        io.print("=== Display DVD ===");
    }


    public void displayAddDVDBanner() {
        io.print("=== ADD DVD ===");
    }

    public void displayADDSuccessBanner() {
        io.readString(
                "DVD successfully added.  Please hit enter to continue");
    }

    public void displayDisplayAllBanner() {
        io.print("=== Display All Students ===");
    }

    public void displayRemoveDVDBanner () {
        io.print("=== Remove DVD ===");
    }

    public void displayErrorMessage(String errorMsg) {
        io.print("=== ERROR ===");
        io.print(errorMsg);
    }

    public void displayEditDvdBanner() {
        io.print("=== Edit DVD ===");
    }

    public void displayEditReleaseDateBanner() {
        io.print("=== Edit DVD Release Date ===");
    }

    public void displayExitBanner() {
        io.print("Good Bye!!!");
    }

    public LocalDate getReleaseDate() {
        return io.readDate("Please enter the new DVD release date.");
    }

    public void displayEditResult(){
        io.print("DVD Successfully edited.");
    }

    public String getMpaaRating() {
        return io.readString("Please enter the new DVD MPAA rating.");
    }

    public String getDirectorName() {
        return io.readString("Please enter the new director's name.");
    }

    public String getUserRating() {
        return io.readString("Please enter the new user rating.");
    }

    public String getStudioName() {
        return io.readString("Please enter the studio name.");
    }

    public void displayUnknownCommandBanner() {
        io.print("Unknown Command!!!");
    }

    public void displayNullDvd(){
        io.print("No such DVD");
    }

}
