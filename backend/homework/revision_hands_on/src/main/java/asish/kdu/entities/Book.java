package asish.kdu.entities;

import asish.kdu.exceptions.BookException;

import java.util.Date;
import java.util.List;
import java.util.Objects;

/**
 * Book class with all its attributes, getters and setters.
 */
public class Book {

    /**
     * Enum used to define various types of Genres
     */
    public enum BookGenre {
        FICTION,
        MYSTERY,
        SCIENCE_FICTION,
        ROMANCE,
        HORROR,
        FANTASY,
        NON_FICTION,
        THRILLER,
        HISTORICAL_FICTION,
        CHILDRENS,
        POETRY,
        DRAMA,
        COMEDY,
        BIOGRAPHY,
        SELF_HELP,
        CRIME,
        ADVENTURE,
        SCIENCE,
        PHILOSOPHY,
        TRAVEL;

        /**
         * Utility function to convert a given string to BookGenre type
         * @param text String to convert
         * @return BookGenre corresponding to it
         */
        public static BookGenre fromString(String text) {
            for (BookGenre genre : BookGenre.values()) {
                if (genre.name().equalsIgnoreCase(text)) {
                    return genre;
                }
            }
            throw new IllegalArgumentException("No constant with text " + text + " found in BookGenre enum");
        }
    }
    public Book(String title, String isbn, BookGenre genre, List<Author> authors) throws BookException {
        if(Objects.equals(title, "") || Objects.equals(isbn, "") || authors.isEmpty())
            throw new BookException("Specify All Parameters for the Book Constructor", new IllegalArgumentException());
        this.title = title;
        this.isbn = isbn;
        this.genre = genre;
        this.authors = authors;
        this.popularity = 0;
    }

    private String title;
    private String isbn;
    private boolean checkedOut;
    private BookGenre genre;
    private Date dueDate;
    private int popularity;

    public Date getDueDate() {
        return dueDate;
    }

    public void setDueDate(Date dueDate) {
        this.dueDate = dueDate;
    }

    private List<Author> authors;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public boolean isCheckedOut() {
        return checkedOut;
    }

    public void setCheckedOut(boolean checkedOut) {
        this.checkedOut = checkedOut;
    }

    public BookGenre getGenre() {
        return genre;
    }

    public void setGenre(BookGenre genre) {
        this.genre = genre;
    }

    public List<Author> getAuthors() {
        return authors;
    }

    public void setAuthors(List<Author> authors) {
        this.authors = authors;
    }

    public int getPopularity() {
        return popularity;
    }

    public void setPopularity(int popularity) {
        this.popularity = popularity;
    }

    public boolean isOverDue() {
        return new Date().after(dueDate);
    }

    /**
     * To show the Book object nicely in logs and terminal
     * @return Nicely formatted String
     */
    @Override
    public String toString() {
        return "Title: " + title + "\n" +
                "ISBN: " + isbn + "\n" +
                "Authors: " + authors + "\n" +
                "Genre: " + genre + "\n" +
                "Popularity: " + popularity + "\n" +
                "Checked Out: " + checkedOut + "\n";
    }
}