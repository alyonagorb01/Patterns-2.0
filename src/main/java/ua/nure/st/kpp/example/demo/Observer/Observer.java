package ua.nure.st.kpp.example.demo.Observer;
import ua.nure.st.kpp.example.demo.Books.Book;
import ua.nure.st.kpp.example.demo.StringBookPair.BookPair;

import java.util.ArrayList;

public interface Observer {
    void update(String eventType, ArrayList<Book> book);
    ArrayList<BookPair> getLog();
}