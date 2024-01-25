package asish.kdu.spring_exercise_4.controller;

import asish.kdu.spring_exercise_4.dtos.BookDTO;
import asish.kdu.spring_exercise_4.exceptions.AuthorException;
import asish.kdu.spring_exercise_4.exceptions.BookException;
import asish.kdu.spring_exercise_4.exceptions.PatronException;
import asish.kdu.spring_exercise_4.services.BookService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import asish.kdu.spring_exercise_4.logging.CustomLogger;
import asish.kdu.spring_exercise_4.logging.CustomLogger.LoggerType;

/**
 * The main REST Controller for the entire app
 */
@RestController
@RequestMapping("/api")
public class AppController {
    private final BookService bookService;

    @Autowired
    public AppController(BookService bookService) {
        this.bookService = bookService;
    }

    /**
     * An endpoint just to test whether our server is working nicely or not
     * @return Response with test String
     */
    @GetMapping("/test")
    public ResponseEntity<String> testApi() {
        CustomLogger.printLogger("Test GET Request just happened. Server is working fine....", LoggerType.INFO);
        return new ResponseEntity<>("Test API Request Successful. Server is working fine....", HttpStatus.OK);
    }

    /**
     * Endpoint to find a book by its name
     * @param name Name of the book
     * @return Response with all the Book details
     * @throws BookException When book cant be found
     */
    @GetMapping("/book")
    public ResponseEntity<BookDTO> getBook(@RequestParam String name) throws BookException {
        BookDTO res = bookService.getBookByName(name);
        return new ResponseEntity<>(res, HttpStatus.OK);
    }

    /**
     * POST Request to create a new Book alongwith its Author and Patron
     * @param bookDTO Request body of the POST
     * @return Confirmation of creation
     * @throws AuthorException When some error occurred in creating a new Author
     * @throws BookException When some error occurred in creating a new Book
     * @throws PatronException When some error occurred in creating a new Patron
     */
    @PostMapping("/book")
    public ResponseEntity<String> createBook(@RequestBody BookDTO bookDTO) throws AuthorException, BookException, PatronException {
        bookService.sendToLibrary(bookDTO);
        return new ResponseEntity<>("Book Created", HttpStatus.OK);
    }

    /**
     *
     * @param bookDTO
     * @return
     * @throws AuthorException
     * @throws BookException
     * @throws PatronException
     */
    @PutMapping("/book")
    public ResponseEntity<String> updateBook(@Valid @RequestBody BookDTO bookDTO) throws AuthorException, BookException, PatronException {
        bookService.updateBook(bookDTO);
        return new ResponseEntity<>("Book Updated", HttpStatus.OK);
    }

    @DeleteMapping("/book")
    public ResponseEntity<String> deleteBook(@RequestParam String name) {
            bookService.deleteBook(name);
            return new ResponseEntity<>("Book deleted", HttpStatus.OK);
    }
}
