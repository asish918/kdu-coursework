package asish.kdu.entities;

import asish.kdu.exceptions.PatronException;
import asish.kdu.utils.Utils;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Patron object (borrower) with its attributes
 * and Getters and Setters
 */
public class Patron {
    public Patron(String name, int checkoutLimit) {
        this.name = name;
        this.checkoutLimit = checkoutLimit;
        this.checkedOutBooks = new ArrayList<>();
    }

    private String name;
    private int checkoutLimit;
    private List<Book> checkedOutBooks;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCheckoutLimit() {
        return checkoutLimit;
    }

    public void setCheckoutLimit(int checkoutLimit) {
        this.checkoutLimit = checkoutLimit;
    }

    public List<Book> getCheckedOutBooks() {
        return checkedOutBooks;
    }

    public void setCheckedOutBooks(List<Book> checkedOutBooks) {
        this.checkedOutBooks = checkedOutBooks;
    }

    /**
     * This is the function that checks out the book, adds its due date and increases
     * its popularity
     * @param book Takes a Book object to be checked out
     * @throws PatronException When it is not possible to check out
     */
    public void checkoutBook(Book book) throws PatronException {
        if(checkedOutBooks.size() == checkoutLimit || book.isCheckedOut()) {
            throw new PatronException("Checkout of Patron Not Possible", new IllegalArgumentException());
        }

        Date newDate = Utils.getNextDueDate();

        book.setDueDate(newDate);
        book.setPopularity(book.getPopularity() + 1);
        checkedOutBooks.add(book);
    }
}