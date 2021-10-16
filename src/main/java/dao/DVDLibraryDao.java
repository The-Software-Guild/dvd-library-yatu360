package dao;

import dto.DVD;

import java.time.LocalDate;
import java.util.List;

public interface DVDLibraryDao {

    DVD addDVD(String title, DVD dvd) throws DVDLibraryDaoException;

    List<DVD> getAllDVD() throws DVDLibraryDaoException;


    DVD getDVD(String title) throws DVDLibraryDaoException;

    DVD removeDVD(String title) throws DVDLibraryDaoException;

    DVD changeReleaseDate (String title, LocalDate releaseDate) throws DVDLibraryDaoException;

    DVD changeMPAARating (String title, String mpaaRating) throws DVDLibraryDaoException;

    DVD changeDirectorsName (String title, String directorsName) throws DVDLibraryDaoException;

    DVD changeStudio (String title, String studio) throws DVDLibraryDaoException;

    DVD changeUserRating (String title, String userRating) throws DVDLibraryDaoException;

}
