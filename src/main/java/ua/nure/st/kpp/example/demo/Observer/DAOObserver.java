package ua.nure.st.kpp.example.demo.Observer;

import ua.nure.st.kpp.example.demo.Books.Book;
import ua.nure.st.kpp.example.demo.StringPlantPair.BookPair;

import java.util.ArrayList;

public class DAOObserver implements Observer {
    private ArrayList<BookPair> log = new ArrayList<BookPair>();

    @Override
    public ArrayList<BookPair> getLog() {
        return log;
    }

    @Override
    public void update(String eventType, ArrayList<Book> plant) {
        for (Book p: plant) {
            System.out.println(eventType + " | " + p);
            log.add(new BookPair(eventType, p));
        }
    }
}