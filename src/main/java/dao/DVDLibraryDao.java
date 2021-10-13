package dao;

import dto.DVD;

import java.util.List;

public interface DVDLibraryDao {

    DVD addDVD(String title, DVD dvd);

    List<DVD> getAllDVD();


    DVD getDVD(String title);

    DVD removeDVD(String title);

}
