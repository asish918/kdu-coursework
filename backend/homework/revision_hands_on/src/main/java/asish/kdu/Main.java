package asish.kdu;

import asish.kdu.data.Library;
import asish.kdu.entities.Author;
import asish.kdu.entities.Book;
import asish.kdu.entities.Patron;
import asish.kdu.exceptions.AuthorException;
import asish.kdu.exceptions.BookException;
import asish.kdu.exceptions.PatronException;
import asish.kdu.exceptions.QueryException;
import asish.kdu.logging.CustomLogger;
import asish.kdu.utils.Utils;

import java.util.ArrayList;
import java.util.List;

/**
 * The actual entry point of my program
 */
public class Main {
    public static void main(String[] args) {
        Utils UTILS = Utils.getINSTANCE();
        Library LIBRARY = Library.getINSTANCE();
        CustomLogger.LoggerType errorLogType = CustomLogger.LoggerType.ERROR;
        CustomLogger.LoggerType infoLogType = CustomLogger.LoggerType.INFO;

        try {
            UTILS.generateDummyData();
        } catch (BookException | PatronException | AuthorException e) {
            CustomLogger.printLogger("Some error occured while generating Dummy Data", errorLogType);
            CustomLogger.printLogger(e.toString(), errorLogType);
        }

        try {
            LIBRARY.findAllAvailableBooks().forEach(b -> CustomLogger.printLogger(b.toString(), infoLogType));

//            Author author = LIBRARY.getAuthors().get(4);
//            LIBRARY.findBooksByAuthor(author).forEach(b -> CustomLogger.printLogger(b.toString(), infoLogType));

//            LIBRARY.findPopularBooks(3).forEach(b -> CustomLogger.printLogger(b.toString(), infoLogType));

//            LIBRARY.findAllAvailableBooks().forEach(b -> CustomLogger.printLogger(b.toString(), infoLogType));

//            LIBRARY.groupBooksByGenre().forEach((k, v) -> {
//                CustomLogger.printLogger(k.name() + " - ", infoLogType);
//                for (Book b : v) {
//                    CustomLogger.printLogger(b.toString(), infoLogType);
//                }
//                CustomLogger.printLogger("---------------", infoLogType);
//            });

            LIBRARY.findOverdueBooks().forEach(b -> CustomLogger.printLogger(b.toString(), infoLogType));
        } catch (QueryException e) {
            CustomLogger.printLogger("Some error occured in the output functions", errorLogType);
            CustomLogger.printLogger(e.toString(), errorLogType);
        }
    }
}