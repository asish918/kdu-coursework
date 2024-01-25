package asish.kdu.spring_exercise_4.dtos.mappers;

import asish.kdu.spring_exercise_4.dtos.BookDTO;
import asish.kdu.spring_exercise_4.exceptions.AuthorException;
import asish.kdu.spring_exercise_4.exceptions.BookException;
import asish.kdu.spring_exercise_4.exceptions.PatronException;
import asish.kdu.spring_exercise_4.models.Author;
import asish.kdu.spring_exercise_4.models.Book;
import asish.kdu.spring_exercise_4.models.Patron;
import asish.kdu.spring_exercise_4.models.enums.BookGenre;

public class BookMapper {
    private BookMapper() {}
    public static Book dtoToEntity(BookDTO bookDTO) throws BookException, AuthorException, PatronException {
        Author author = new Author(bookDTO.getAuthorName());
        Patron patron = new Patron(bookDTO.getPatronName());
        return new Book(bookDTO.getName(), bookDTO.getIsbn(), Boolean.parseBoolean(bookDTO.getCheckedOut()), BookGenre.fromString(bookDTO.getGenre()), author, patron);
    }

    public static BookDTO entityToDto(Book book, Author author, Patron patron) {
        return new BookDTO(book.title(), book.isbn(), book.genre().toString(), String.valueOf(book.checkedOut()), author.name(), patron.name());
    }
}
