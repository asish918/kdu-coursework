package asish.kdu.q4;

import java.util.Comparator;

public class PubDateAscComparator implements Comparator<Book> {
    /**
     * The return function is one-line ternary operator equivalent of -
     * if (o1.getYear() > o2.getYear())
     * return 1;
     * if (o1.getYear() < o2.getYear())
     * return -1;
     * if (o1.getYear() == o2.getYear()) {
     * return o1.getTitle().compareTo(o2.getTitle());
     * }
     */

    @Override
    public int compare(Book o1, Book o2) {
        return o1.getYear() - o2.getYear() != 0 ? o1.getYear() - o2.getYear() : o1.getTitle().compareTo(o2.getTitle());
    }
}
