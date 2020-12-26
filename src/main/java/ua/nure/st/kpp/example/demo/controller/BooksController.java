
package ua.nure.st.kpp.example.demo.controller;

import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import ua.nure.st.kpp.example.demo.Books.Book;
import ua.nure.st.kpp.example.demo.MyDAO.*;

import ua.nure.st.kpp.example.demo.Observer.DAOObserver;
import ua.nure.st.kpp.example.demo.Observer.BookObservable;
import ua.nure.st.kpp.example.demo.StringBookPair.BookPair;


@Controller
public class BooksController {
	private MySQLDAO dao = new MySQLDAO();

	BooksController() {
		dao.events.registerObserver(new DAOObserver());
	}


	@RequestMapping(value = {"/", "/books"}, method = {RequestMethod.GET, RequestMethod.POST})
	public String showAllPBooks(Model model) {
		List<Book> list = dao.Get_Books();
		model.addAttribute("allBook", list);
		return "booksPage";
	}

	@RequestMapping(value = {"/log"}, method = {RequestMethod.GET, RequestMethod.POST})
	public String showLog(Model model) {
		ArrayList<BookPair> list = dao.events.Sumary();
		model.addAttribute("allBooks", list);
		return "logPage";
	}

}


