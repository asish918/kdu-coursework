package asish.kdu.spring_exercise_4.models;

import asish.kdu.spring_exercise_4.exceptions.AuthorException;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import lombok.Data;
import lombok.experimental.Accessors;


/**
 * Author class with all its attributes, Getters and Setters
 */
@Data
@Accessors(fluent = true)
public class Author {
    public Author(String name) throws AuthorException {
        if (Objects.equals(name, "")) {
            throw new AuthorException("Specify All Parameters for the Author Constructor",
                    new IllegalArgumentException());
        }
        this.name = name;
        this.books = new ArrayList<>();
    }

    private String name;

    private List<Book> books;
}