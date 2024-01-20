package asish.kdu.data;

import asish.kdu.entities.Patron;
import asish.kdu.entities.Author;
import asish.kdu.entities.Book;
import asish.kdu.exceptions.PatronException;
import asish.kdu.exceptions.QueryException;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Library class has been implemented using the Singleton Pattern
 * since only one instance of the class is used across the application
 * and is the single source of truth for all the data.
 */
public class Library {

    private Library() {

    }

    private static Library INSTANCE;

    public static Library getINSTANCE() {
        if (INSTANCE == null) {
            INSTANCE = new Library();
        }

        return INSTANCE;
    }

    private List<Book> books = new ArrayList<>();
    private List<Author> authors = new ArrayList<>();
    private List<Patron> patrons = new ArrayList<>();
    private List<Book> checkedOutBooks = new ArrayList<>();

    public List<Book> getBooks() {
        return books;
    }

    public void setBooks(List<Book> books) {
        this.books = books;
    }

    public List<Author> getAuthors() {
        return authors;
    }

    public void setAuthors(List<Author> authors) {
        this.authors = authors;
    }

    public List<Patron> getPatrons() {
        return patrons;
    }

    public void setPatrons(List<Patron> patrons) {
        this.patrons = patrons;
    }

    public List<Book> getCheckedOutBooks() {
        return checkedOutBooks;
    }

    public void setCheckedOutBooks(List<Book> checkedOutBooks) {
        this.checkedOutBooks = checkedOutBooks;
    }

    /**
     * Finds all the available books. Basically those that
     * haven't been checked out.
     * @return List of Books
     * @throws QueryException When some unexpected Runtime error occurs
     */
    public List<Book> findAllAvailableBooks() throws QueryException {
        try {
            return books.stream()
                    .filter(b -> !b.isCheckedOut())
                    .toList();
        } catch (RuntimeException e) {
            throw new QueryException("Some error occurred in Querying Available Books", e);
        }

    }

    /**
     * Takes two parameters and checks out the Book is possible
     * @param book Book to be checked out
     * @param patron Patron borrowing the book
     * @throws PatronException When checkout is not possible
     */
    public void checkOutBook(Book book, Patron patron) throws PatronException {
        if (!book.isCheckedOut()) {
            checkedOutBooks.add(book);
            patron.checkoutBook(book);
            book.setCheckedOut(true);
            return;
        }

        throw new PatronException("Can't checkout the book", new RuntimeException());
    }

    /**
     * Finds books written by a given author
     * @param author Author of the Books
     * @return List of books written by the given Author
     * @throws QueryException When some unexpected Runtime error occurs
     */
    public List<Book> findBooksByAuthor(Author author) throws QueryException {
        try {
            return books.stream()
                    .filter(book -> book.getAuthors().contains(author))
                    .toList();
        } catch (RuntimeException e) {
            throw new QueryException("Some error occurred in Querying Books by Author", e);
        }
    }

    /**
     * Return books that are overdue by comparing the due date to current one
     * @return List of Books which are overdue
     * @throws QueryException When some unexpected Runtime error occurs
     */
    public List<Book> findOverdueBooks() throws QueryException {
        try {
            return checkedOutBooks.stream()
                    .filter(Book::isOverDue)
                    .toList();
        } catch (RuntimeException e) {
            throw new QueryException("Some error occurred in Querying Overdue books", e);
        }
    }

    /**
     * Return books sorted by popularity
     * @param topN Top N books to find
     * @return List of Books arranged according to popularity
     * @throws QueryException When some unexpected Runtime error occurs
     */
    public List<Book> findPopularBooks(int topN) throws QueryException {
        try {
            return books.stream()
                    .sorted(Comparator.comparingInt(Book::getPopularity).reversed())
                    .limit(topN)
                    .toList();
        } catch (RuntimeException e) {
            throw new QueryException("Some error occurred in Querying Popular Books", e);
        }
    }

    /**
     * Generates a Map by grouping Books of similar Genre together.
     * @return Map of Genre and the List of corresponding {@link  asish.kdu.entities.Book  Books}
     * @throws QueryException When some unexpected Runtime error occurs
     */
    public Map<Book.BookGenre, List<Book>> groupBooksByGenre() throws QueryException {
        try {
            return books.stream()
                    .collect(Collectors.groupingBy(Book::getGenre));
        } catch (RuntimeException e) {
            throw new QueryException("Some error occurred in Querying Books by Genre", e);
        }
    }
}
