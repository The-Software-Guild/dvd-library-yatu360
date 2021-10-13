import controller.DVDController;
import dao.DVDLibraryDao;
import dao.DVDLibraryDaoImpl;
import ui.DVDView;
import ui.UserIO;
import ui.UserIOConsoleImpl;

public class App {

    public static void main(String[] args) {
        UserIO myIo = new UserIOConsoleImpl();
        DVDView myView = new DVDView(myIo);
        DVDLibraryDao myDao = new DVDLibraryDaoImpl();
        DVDController controller = new DVDController(myView, myDao);
        controller.run();
    }
}
