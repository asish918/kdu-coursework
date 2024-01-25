package asish.kdu.spring_exercise_4.services;

import asish.kdu.spring_exercise_4.dtos.AuthorDTO;
import asish.kdu.spring_exercise_4.dtos.BookDTO;
import asish.kdu.spring_exercise_4.dtos.PatronDTO;
import asish.kdu.spring_exercise_4.dtos.mappers.BookMapper;
import asish.kdu.spring_exercise_4.exceptions.AuthorException;
import asish.kdu.spring_exercise_4.exceptions.BookException;
import asish.kdu.spring_exercise_4.exceptions.PatronException;
import asish.kdu.spring_exercise_4.logging.CustomLogger;
import asish.kdu.spring_exercise_4.models.Book;
import asish.kdu.spring_exercise_4.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BookService {
    AuthorService authorService;
    PatronService patronService;
    BookRepository bookRepository;

    @Autowired
    public BookService(BookRepository bookRepository, AuthorService authorService, PatronService patronService) {
        this.authorService = authorService;
        this.patronService = patronService;
        this.bookRepository = bookRepository;
    }
    public void sendToLibrary(BookDTO bookDTO) throws AuthorException, BookException, PatronException {
        Book book = BookMapper.dtoToEntity(bookDTO);
        authorService.parseAuthor(new AuthorDTO(bookDTO.getAuthorName()));
        patronService.parsePatron(new PatronDTO(bookDTO.getPatronName()));
        bookRepository.addBookToLibrary(book);
    }

    public BookDTO getBookByName(String name) throws BookException {
        Book book = bookRepository.getBookByName(name);
        return BookMapper.entityToDto(book, book.authors(), book.patrons());
    }

    public void updateBook(BookDTO bookDTO) throws AuthorException, BookException, PatronException {
        Book book = BookMapper.dtoToEntity(bookDTO);
        bookRepository.updateBookInLibrary(book);
    }

    public void deleteBook(String name) {
        try {
            bookRepository.removeBookFromLibrary(name);
        } catch (BookException b) {
            CustomLogger.printLogger("Book could not be deleted", CustomLogger.LoggerType.ERROR);
        }
    }
}
