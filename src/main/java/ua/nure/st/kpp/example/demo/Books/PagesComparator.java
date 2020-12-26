

import ua.nure.st.kpp.example.demo.Books.Book;

import java.util.Comparator;

public class PagesComparator implements Comparator<Book> {
    public int compare(Book first, Book second) {
        if (first.getPages() == second.getPages()) {
            return 0;
        }
        if (first.getPages() < second.getPages()) {
            return -1;
        }
        else {
            return 1;
        }
    }
}