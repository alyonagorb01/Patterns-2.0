package ua.nure.st.kpp.example.demo.Memento;

import ua.nure.st.kpp.example.demo.Flowers.Book;

public class Memento {
    private final Book state;

    public Memento(Book state) {
        this.state = new Book(state);
    }

    public Book getState() {
        return state;
    }
}