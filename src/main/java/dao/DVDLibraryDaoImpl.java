package dao;

import dto.DVD;

import java.util.*;

public class DVDLibraryDaoImpl implements DVDLibraryDao{

    private Map<String, DVD> dvds = new HashMap<>();

    @Override
    public DVD addDVD(String title, DVD dvd) {
        DVD newDVD = dvds.put(title, dvd);
        return newDVD;
    }

    @Override
    public List<DVD> getAllDVD() {
        return new ArrayList<DVD>(dvds.values());
    }

    @Override
    public DVD getDVD(String title) {
        return dvds.get(title);
    }

    @Override
    public DVD removeDVD(String title) {
        DVD removedDVD = dvds.remove(title);
        return removedDVD;
    }


}
