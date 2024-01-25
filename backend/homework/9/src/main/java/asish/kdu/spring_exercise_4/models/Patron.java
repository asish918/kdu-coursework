package asish.kdu.spring_exercise_4.models;

import asish.kdu.spring_exercise_4.exceptions.PatronException;
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.ArrayList;
import java.util.List;


/**
 * Patron object (borrower) with its attributes
 * and Getters and Setters
 */

@Data
@Accessors(fluent = true)
public class Patron {
    public Patron(String name) throws PatronException {
        if ("".equals(name)) {
            throw new PatronException("Invalid Object initialization", new IllegalArgumentException());
        }
        this.name = name;
        this.checkedOutBooks = new ArrayList<>();
    }

    private String name;
    private List<Book> checkedOutBooks;
}