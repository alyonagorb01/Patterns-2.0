package ua.nure.st.kpp.example.demo.MyDAO;
import ua.nure.st.kpp.example.demo.Books.Book;

import java.util.ArrayList;


public interface IDAO {
	Book Insert_Book(Book book);
	ArrayList<Book> Get_Books();
	ArrayList<Book> SelectByAuthorName(String book_name);
	void ChangeBindingType(String binding_type, String book_name);
	void DeleteBookByName(String Book_name);
	ArrayList<Book> Get_Books_By_Name(String book_name);
}
