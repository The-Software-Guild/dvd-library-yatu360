package dao;

import dto.DVD;

import java.io.*;
import java.time.LocalDate;
import java.util.*;

public class DVDLibraryDaoImpl implements DVDLibraryDao{

    private Map<String, DVD> dvds = new HashMap<>();
    public static final String ROSTER_FILE = "DVDlibrary.txt";
    public static final String DELIMITER = "::";

    @Override
    public DVD addDVD(String title, DVD dvd) throws DVDLibraryDaoException {
        loadRoster();
        DVD newDVD = dvds.put(title, dvd);
        writeRoster();
        return newDVD;
    }

    @Override
    public List<DVD> getAllDVD() throws DVDLibraryDaoException {
        loadRoster();
        return new ArrayList<DVD>(dvds.values());
    }

    @Override
    public DVD getDVD(String title) throws DVDLibraryDaoException {
        loadRoster();
        return dvds.get(title);
    }

    @Override
    public DVD removeDVD(String title) throws DVDLibraryDaoException {
        loadRoster();
        DVD removedDVD = dvds.remove(title);
        writeRoster();
        return removedDVD;
    }

    @Override
    public DVD changeReleaseDate(String title, LocalDate releaseDate) throws DVDLibraryDaoException {
        loadRoster();
        DVD editDVD = dvds.get(title);
        editDVD.setReleaseDate(releaseDate);
        writeRoster();
        return editDVD;
    }

    @Override
    public DVD changeMPAARating(String title, String mpaaRating) throws DVDLibraryDaoException {
        loadRoster();
        DVD editDVD = dvds.get(title);
        editDVD.setMPAARating(mpaaRating);
        writeRoster();
        return editDVD;
    }

    @Override
    public DVD changeDirectorsName(String title, String directorsName) throws DVDLibraryDaoException {
        loadRoster();
        DVD editDVD = dvds.get(title);
        editDVD.setDirectorName(directorsName);
        writeRoster();
        return editDVD;
    }

    @Override
    public DVD changeStudio(String title, String studio) throws DVDLibraryDaoException {
        loadRoster();
        DVD editDVD = dvds.get(title);
        editDVD.setStudio(studio);
        writeRoster();
        return editDVD;
    }

    @Override
    public DVD changeUserRating(String title, String userRating) throws DVDLibraryDaoException {
        loadRoster();
        DVD editDVD = dvds.get(title);
        editDVD.setUserRating(userRating);
        writeRoster();
        return editDVD;
    }


    private DVD unmarshallDVD(String DVDAsText) {
        String[] DVDTokens = DVDAsText.split(DELIMITER);
        String DVDTitle = DVDTokens[0];
        DVD DVDFromFile = new DVD(DVDTitle);
        DVDFromFile.setReleaseDate(LocalDate.parse(DVDTokens[1]));
        DVDFromFile.setMPAARating(DVDTokens[2]);
        DVDFromFile.setDirectorName(DVDTokens[3]);
        DVDFromFile.setStudio(DVDTokens[4]);
        DVDFromFile.setUserRating(DVDTokens[5]);
        return DVDFromFile;
    }

    private String marshallDVD(DVD aDVD){
        String DVDAsText = aDVD.getTitle() + DELIMITER;
        DVDAsText = DVDAsText + aDVD.getReleaseDate() + DELIMITER;
        DVDAsText += aDVD.getMPAARating() + DELIMITER;
        DVDAsText += aDVD.getDirectorName() + DELIMITER;
        DVDAsText += aDVD.getStudio() + DELIMITER;
        DVDAsText += aDVD.getUserRating();
        return DVDAsText;
    }

    private void writeRoster() throws DVDLibraryDaoException {
        // NOTE FOR APPRENTICES: We are not handling the IOException - but
        // we are translating it to an application specific exception and
        // then simple throwing it (i.e. 'reporting' it) to the code that
        // called us.  It is the responsibility of the calling code to
        // handle any errors that occur.
        PrintWriter out;

        try {
            out = new PrintWriter(new FileWriter(ROSTER_FILE));
        } catch (IOException e) {
            throw new DVDLibraryDaoException(
                    "Could not save student data.", e);
        }

        // Write out the Student objects to the roster file.
        // NOTE TO THE APPRENTICES: We could just grab the student map,
        // get the Collection of Students and iterate over them but we've
        // already created a method that gets a List of Students so
        // we'll reuse it.
        String DVDAsText;
        List<DVD> DVDList = this.getAllDVD();
        for (DVD currentDVD : DVDList) {
            // turn a Student into a String
            DVDAsText = marshallDVD(currentDVD);
            // write the Student object to the file
            out.println(DVDAsText);
            // force PrintWriter to write line to the file
            out.flush();
        }
        // Clean up
        out.close();
    }


    private void loadRoster() throws DVDLibraryDaoException {
        Scanner scanner;
        try {
            // Create Scanner for reading the file
            scanner = new Scanner(
                    new BufferedReader(
                            new FileReader(ROSTER_FILE)));
        } catch (FileNotFoundException e) {
            throw new DVDLibraryDaoException(
                    "-_- Could not load roster data into memory.", e);
        }
        String currentLine;
        DVD currentDVD;
        while (scanner.hasNextLine()) {
            // get the next line in the file
            currentLine = scanner.nextLine();
            // unmarshall the line into a Student
            currentDVD = unmarshallDVD(currentLine);

            // We are going to use the student id as the map key for our student object.
            // Put currentStudent into the map using student id as the key
            dvds.put(currentDVD.getTitle(), currentDVD);
        }
        // close scanner
        scanner.close();
    }


}
