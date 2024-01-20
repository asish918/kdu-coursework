package asish.kdu.utils;

import asish.kdu.data.Library;
import asish.kdu.entities.Author;
import asish.kdu.entities.Book;
import asish.kdu.entities.Patron;
import asish.kdu.exceptions.AuthorException;
import asish.kdu.exceptions.BookException;
import asish.kdu.exceptions.PatronException;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Utils class has been implemented using Singleton pattern
 * since only a single instance of this class will be used throughout
 * the Application
 */
public class Utils {
    private Utils() {

    }

    public static Utils getINSTANCE() {
        if (INSTANCE == null)
            INSTANCE = new Utils();

        return INSTANCE;
    }

    private static Utils INSTANCE;

    /**
     * This function has been created for generating dummy data for all the query types.
     * I have also commented out a few lines which cause exceptions that are handled
     * and logged nicely to the logs/app.log file. This has been done on purpose to showcase
     * exception handling.
     * @throws BookException Is thrown when there's an error in making/manipulating Book object
     * @throws PatronException Is thrown when there's an error in making/manipulating Patron object
     * @throws AuthorException Is thrown when there's an error in making/manipulating Author object
     */
    public void generateDummyData() throws BookException, PatronException, AuthorException {
        Library LIBRARY_INSTANCE = Library.getINSTANCE();

        List<Author> authors = new ArrayList<>();
        Author author1 = new Author("Morgan Housel");
        Author author2 = new Author("Joseph Murphy");
        Author author3 = new Author("Robert T Kiyosaki");
        Author author4 = new Author("Rick Riordan");
        Author author5 = new Author("Amish Triparthi");

        authors.add(author1);
        authors.add(author2);
        authors.add(author3);
        authors.add(author4);
        authors.add(author5);

        List<Book> books = new ArrayList<>();
        Book book1 = new Book(
                "The Psychology of Money",
                "9390166268",
                Book.BookGenre.SELF_HELP,
                List.of(author1)
        );
        Book book2 = new Book(
                "The Power of Subconcious Mind",
                "8194790832",
                Book.BookGenre.SELF_HELP,
                List.of(author2)
        );
        Book book3 = new Book(
                "Rich Dad Poor Dad",
                "1612681131",
                Book.BookGenre.SELF_HELP,
                List.of(author3)
        );
        Book book4 = new Book(
                "Percy Jackson - The Chalice of Gods",
                "0241647568",
                Book.BookGenre.FICTION,
                List.of(author4)
        );
        Book book5 = new Book(
                "Heroes of Olympus",
                "9390166456",
                Book.BookGenre.FICTION,
                List.of(author4)
        );
        Book book6 = new Book(
                "The Immortals of Meluha",
                "93905466456",
                Book.BookGenre.HISTORICAL_FICTION,
                List.of(author5)
        );
        Book book7 = new Book(
                "Ram - Scion of Ikshvaku",
                "93905499456",
                Book.BookGenre.HISTORICAL_FICTION,
                List.of(author5)
        );

        author1.addBooks(book1);
        author2.addBooks(book2);
        author3.addBooks(book3);
        author4.addBooks(book4);
        author4.addBooks(book5);
        author5.addBooks(book6);
        author5.addBooks(book7);

        books.add(book1);
        books.add(book2);
        books.add(book3);
        books.add(book4);
        books.add(book5);
        books.add(book6);
        books.add(book7);

        LIBRARY_INSTANCE.setBooks(books);
        LIBRARY_INSTANCE.setAuthors(authors);

        List<Patron> patrons = new ArrayList<>();
        Patron p1 = new Patron("Asish Mahapatra", 5);
        Patron p2 = new Patron("Aayushi", 3);
        Patron p3 = new Patron("Arjun", 1);
        patrons.add(p1);
        patrons.add(p2);
        patrons.add(p3);

        LIBRARY_INSTANCE.setPatrons(patrons);

        LIBRARY_INSTANCE.checkOutBook(book1, p3);
//        LIBRARY_INSTANCE.checkOutBook(book2, p3);

        book1.setDueDate(new Date());
    }

    /**
     * The due date for every checked out book is set to 5 days later
     * @return Returns date after adding 5 to current date
     */
    public static Date getNextDueDate() {

        Date currentDate = new Date();

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(currentDate);

        calendar.add(Calendar.DAY_OF_MONTH, 5);

        return calendar.getTime();
    }
}
