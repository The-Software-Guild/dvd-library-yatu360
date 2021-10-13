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
        io.print("3. View a DVD");
        io.print("4. Remove a DVD");
        io.print("5. Exit");

        return io.readInt("Please select from the above choices.", 1, 5);
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

    public void displayExitBanner() {
        io.print("Good Bye!!!");
    }

    public void displayUnknownCommandBanner() {
        io.print("Unknown Command!!!");
    }

}
