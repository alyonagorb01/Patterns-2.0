package ua.nure.st.kpp.example.demo.MyDAO;



import ua.nure.st.kpp.example.demo.Books.Book;
import ua.nure.st.kpp.example.demo.Memento.Caretaker;


import java.util.ArrayList;

public class TestMain {
	public static void main(String[] args) {
		MySQLDAO dao = MySQLDAO.getInstance();
		Caretaker me = new Caretaker();
		Book book;
		dao.ChangeBindingType("Tommy and Co", "Твердий");
		book = dao.Get_Books().get(0);
		me.addMemento(book.saveState());
		System.out.println("Состояние №0 (начальное): ");
		System.out.println(book);

		dao.ChangeBindingType("Tommy and Co", "Твердий");
		book = dao.Get_Books().get(0);
		me.addMemento(book.saveState());
		System.out.println("\nСостояние №1 (измененное): ");
		System.out.println(book);

		dao.ChangeBindingType("Tommy and Co", "М'який");
		book = dao.Get_Books().get(0);
		me.addMemento(book.saveState());
		System.out.println("\nСостояние №2 (измененное): ");
		System.out.println(book);

		book.restoreState(me.getMemento(0));
		dao.restore_book(book);
		book = dao.Get_Books().get(0);
		System.out.println("\nВозврат состояния №0: ");
		System.out.println(book);

		book.restoreState(me.getMemento(1));
		dao.restore_book(book);
		book = dao.Get_Books().get(0);
		System.out.println("\nВозврат состояния №1: ");
		System.out.println(book);
	}
}
