package asish.kdu.spring_exercise_4.repository;

import asish.kdu.spring_exercise_4.exceptions.BookException;
import asish.kdu.spring_exercise_4.logging.CustomLogger;
import asish.kdu.spring_exercise_4.models.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Repository
public class BookRepository {
    List<Book> books;
    AuthorRepository authorRepository;
    PatronRepository patronRepository;

    @Autowired
    public BookRepository(AuthorRepository authorRepository, PatronRepository patronRepository) {
        this.authorRepository = authorRepository;
        this.patronRepository = patronRepository;
        this.books = new ArrayList<>();
    }
    public void addBookToLibrary(Book book) {
        books.add(book);
    }

    public Book getBookByName(String name) throws BookException {
        Book book = null;
        for(Book b : books) {
            if(Objects.equals(b.title().toLowerCase(), name.toLowerCase()))
                book = b;
        }

        if(book == null)
            throw new BookException("Book not found", new Exception());

        CustomLogger.printLogger("Book found", CustomLogger.LoggerType.ERROR);
        return book;
    }

    public void updateBookInLibrary(Book book) throws BookException {
        for(Book b : books) {
            if(Objects.equals(b.title(), book.title())) {
                b.title(book.title());
                b.genre(book.genre());
                b.isbn(book.isbn());
                b.authors(book.authors());
                b.patrons(book.patrons());
                CustomLogger.printLogger("Book updated Successfully", CustomLogger.LoggerType.INFO);
                return;
            }
        }

        throw new BookException("Book does not exist", new Exception());
    }

    public void removeBookFromLibrary(String name) throws BookException {
        for(int i = 0; i <= books.size(); i++) {
            if(Objects.equals(books.get(i).title(), name)){
                books.remove(i);
                CustomLogger.printLogger("Book Removed Successfully", CustomLogger.LoggerType.INFO);
                return;
            }
        }

        throw new BookException("Book does not exist", new Exception());
    }
}
