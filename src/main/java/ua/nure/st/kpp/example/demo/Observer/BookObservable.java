package ua.nure.st.kpp.example.demo.Observer;

import ua.nure.st.kpp.example.demo.Books.Book;
import ua.nure.st.kpp.example.demo.StringPlantPair.BookPair;

import java.util.*;

public class BookObservable implements Observable{
    private ArrayList<Observer> observers = new ArrayList<Observer>();

    @Override
    public void registerObserver(Observer observer) {
        observers.add(observer);
    }

    @Override
    public void removeObserver(Observer observer) {
        observers.remove(observer);
    }

    @Override
    public void notifyObservers(String eventType, ArrayList<Book> book) {
        for (Observer user : observers) {
            user.update(eventType, book);
        }
    }

    public ArrayList<BookPair> Sumary() {
        ArrayList<BookPair> sumary = new ArrayList<BookPair>();
        for (Observer ob : observers) {
            ArrayList<BookPair> log = ob.getLog();

            for (int i = log.size()-1; i >= 0; i--)
            {
                sumary.add(log.get(i));
            }
        }
        return sumary;
    }
}
