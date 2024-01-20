package asish.kdu.entities;

import asish.kdu.exceptions.AuthorException;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Author class with all its attributes, Getters and Setters
 */
public class Author {
    public Author(String name) throws AuthorException {
        if(Objects.equals(name, "")) {
            throw new AuthorException("Specify All Parameters for the Author Constructor", new IllegalArgumentException());
        }
        this.name = name;
        this.books = new ArrayList<>();
    }

    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Book> getBooks() {
        return books;
    }

    public void setBooks(List<Book> books) {
        this.books = books;
    }

    private List<Book> books;

    public void addBooks(Book book) {
        books.add(book);
    }
}