package asish.kdu.spring_exercise_4.models;

import asish.kdu.spring_exercise_4.exceptions.BookException;
import asish.kdu.spring_exercise_4.models.enums.BookGenre;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * Book class with all its attributes, getters and setters.
 */
@Data
@Accessors(fluent = true)
public class Book {
    private String title;
    private String isbn;
    private boolean checkedOut;
    private BookGenre genre;
    private Author authors;
    private Patron patrons;

    public Book(String title, String isbn, boolean checkedOut, BookGenre genre, Author author, Patron patron)
            throws BookException {
        if ("".equals(title) || "".equals(isbn) || genre == null) {
            throw new BookException("Invalid Parameters", new IllegalArgumentException());
        }

        this.title = title;
        this.isbn = isbn;
        this.checkedOut = checkedOut;
        this.genre = genre;
        this.authors = author;
        this.patrons = patron;
    }

    @Override
    public String toString() {
        return "Title: " + title + "\n" +
                "ISBN: " + isbn + "\n" +
                "Authors: " + authors + "\n" +
                "Genre: " + genre + "\n" +
                "Checked Out: " + checkedOut + "\n";
    }
}