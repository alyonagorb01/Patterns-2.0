package ua.nure.st.kpp.example.demo.StringBookPair;

import ua.nure.st.kpp.example.demo.Books.Book;

public class BookPair {
    public String getEvent() {
        return event;
    }

    public String event;
    public Book book;

    public BookPair(String event, Book book) {
        this.event = event;
        this.book = book;
    }
}
