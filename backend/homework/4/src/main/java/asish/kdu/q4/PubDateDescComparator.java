package asish.kdu.q4;

import java.util.Comparator;

public class PubDateDescComparator implements Comparator<Book> {

    /**
     * The return function is one-line ternary operator equivalent of -
     * if (o1.getYear() > o2.getYear())
     * return -1;
     * if (o1.getYear() < o2.getYear())
     * return 1;
     * if (o1.getYear() == o2.getYear()) {
     * return o1.getTitle().compareTo(o2.getTitle());
     * }
     */

    @Override
    public int compare(Book o1, Book o2) {
        return o2.getYear() - o1.getYear() != 0 ? o2.getYear() - o1.getYear() : o1.getTitle().compareTo(o2.getTitle());
    }
}