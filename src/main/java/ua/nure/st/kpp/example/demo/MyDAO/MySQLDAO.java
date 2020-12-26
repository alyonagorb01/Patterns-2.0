package ua.nure.st.kpp.example.demo.MyDAO;

import org.jvnet.hk2.spring.bridge.api.SpringIntoHK2Bridge;
import ua.nure.st.kpp.example.demo.Books.Book;
import ua.nure.st.kpp.example.demo.Observer.BookObservable;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class MySQLDAO implements IDAO {
	public BookObservable events;
	private static MySQLDAO instance = null;
	private static Connection con = null;
	private static String add_binding_type = "INSERT INTO binding_type (binding_type) select ?, ? WHERE NOT EXISTS (select id from binding_type where binding_type = ?)";
	private static String add_publishing_house = "INSERT INTO publishing_house (name) select ? WHERE NOT EXISTS(select id from publishing_house where name = ?)";
	private static String add_book = "INSERT INTO book (id_publishing_house, id_binding_type, book_name, author_name, description, pages) VALUES(?, ?, ?, ?, ?, ?)";
	private static String get_publishing_house_id = "select id from publishing_house where name = ? LIMIT 1";
	private static String get_binding_id = "select id from binding_type where binding_type = ? LIMIT 1";
	private static String edit_binding_type_by_book_name = "update book set id_binding_type = ? where book_name = ?";
	private static String delete_book_by_name = "DELETE FROM book WHERE book_name = ?;";
	private static String get_all_books = "select book.id,  publishing_house.name, book_name, author_name, description, pages, binding_type from book join publishing_house on book.id_publishing_house = publishing_house.id join binding_type on binding_type.id = book.id_binding_type";
	private static String get_books_by_name = "select book.id,  publishing_house.name, book_name, author_name, description, pages, binding_type from book join publishing_house on book.id_publishing_house = publishing_house.id join binding_type on binding_type.id = book.id_binding_type where book_name = ?";
	private static String restore_book = "UPDATE book set id = ?, id_publishing_house = ?, id_binding_type = ?, book_name = ?, author_name = ?, description = ?, pages = ? where id = ?";
	private static String reg_usr = "insert into user(login, password) select ?, ? where not exists(select id from user where login = ?)";
	private static String get_usr = "select id, login, password, id_role from user where login = ? and password = ?";



	public MySQLDAO() {
		events = new BookObservable();
		try {
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/flowers?serverTimezone=UTC", "root", "12121212");
		} catch (Exception ex) {
			System.out.println("Connection faliure...");
			System.out.println(ex);
		}
	}

	public static MySQLDAO getInstance() {
		if (instance == null) {
			instance = new MySQLDAO();
		}
		return instance;
	}

	@Override
	public Book Insert_Book(Book book) {
		try {
			PreparedStatement insert_binding_type = con.prepareStatement(add_binding_type);
			PreparedStatement insert_publishing_house = con.prepareStatement(add_publishing_house);
			PreparedStatement insert_book = con.prepareStatement(add_book, Statement.RETURN_GENERATED_KEYS);

			PreparedStatement get_binding_type = con.prepareStatement(get_binding_id);
			PreparedStatement get_publishing_house = con.prepareStatement(get_publishing_house_id);

			insert_binding_type.setString(1, book.getBinding_type());
			insert_binding_type.setString(3, book.getBinding_type());
			insert_binding_type.executeUpdate();

			insert_publishing_house.setString(1, book.getPb_name());
			insert_publishing_house.setString(2, book.getPb_name());
			insert_publishing_house.executeUpdate();

			get_publishing_house.setString(1, book.getPb_name());
			ResultSet resultSet = get_publishing_house.executeQuery();
			resultSet.next();
			int id_publishing_house = resultSet.getInt("id");
			get_binding_type.setString(1, book.getBinding_type());
			resultSet = get_binding_type.executeQuery();
			resultSet.next();
			int id_binding_type = resultSet.getInt("id");

			insert_book.setInt(1, id_publishing_house);
			insert_book.setInt(2, id_binding_type);
			insert_book.setString(3, book.getName());
			insert_book.setString(4, book.getAuthor_name());
			insert_book.setString(5, book.get_Description());
			insert_book.setDouble(6, book.getPages());
			insert_book.executeUpdate();
			try (ResultSet generatedKeys = insert_book.getGeneratedKeys()) {
				if (generatedKeys.next()) {
					book.setId(generatedKeys.getLong(1));
				} else {
					throw new SQLException("Creating user failed, no ID obtained.");
				}
			}
			ArrayList<Book> books = new ArrayList<Book>();
			books.add(book);
			events.notifyObservers("Add", books);
		} catch (Exception ex) {
			System.out.println("Query failed...");
			System.out.println(ex);
		} else{
			System.out.println("Недостаточно прав для соверешения операции");
		}
	}
catch (Exception ex) {
			System.out.println("Query failed...");
			System.out.println(ex);

		}

		return book;
	}

	void restore_book(Book book) {
		try {
			PreparedStatement sql_restore = con.prepareStatement(restore_book);
			PreparedStatement insert_publishing_house = con.prepareStatement(add_publishing_house);
			PreparedStatement insert_binding_type = con.prepareStatement(add_binding_type);
			PreparedStatement get_publishing_house= con.prepareStatement(get_publishing_house_id);
			PreparedStatement get_binding_type = con.prepareStatement(get_binding_id);

			insert_binding_type.setString(1, book.getBinding_type());
			insert_binding_type.setString(2, book.getBinding_type());
			insert_binding_type.executeUpdate();

			insert_publishing_house.setString(1, book.getPb_name());
			insert_publishing_house.setString(2, book.getPb_name());
			insert_publishing_house.executeUpdate();

			get_publishing_house.setString(1, book.getPb_name());
			ResultSet resultSet = get_publishing_house.executeQuery();
			resultSet.next();
			int id_publishing_house = resultSet.getInt("id");
			get_binding_type.setString(1, book.getBinding_type());
			resultSet = get_binding_type.executeQuery();
			resultSet.next();
			int id_binding_type = resultSet.getInt("id");

			sql_restore.setLong(1, book.getId());
			sql_restore.setInt(2, id_publishing_house);
			sql_restore.setInt(3, id_binding_type);
			sql_restore.setString(4, book.getName());
			sql_restore.setString(5, book.getAuthor_name());
			sql_restore.setString(6, book.getBook_description());
			sql_restore.setInt(7, book.getPages());
			sql_restore.setLong(8, book.getId());
			sql_restore.executeUpdate();
			ArrayList<Book> books = new ArrayList<Book>();
			books.add(book);
			events.notifyObservers("Edit", books);

		} catch (Exception ex) {
			System.out.println("Query failed...");
			System.out.println(ex);
		}
	}

	@Override
	public ArrayList<Book> Get_Books() {
		try {
			PreparedStatement get_book = con.prepareStatement(get_all_books);
			ResultSet resultSet = get_book.executeQuery();
			ArrayList<Book> result = new ArrayList<Book>();
			while (resultSet.next()) {
				result.add(new Book(resultSet.getInt("id"),
						resultSet.getString("book_name"),
						resultSet.getString("author_name"),
						resultSet.getString("description"),
						resultSet.getString("name"),
						resultSet.getString("binding_type"),
						resultSet.getInt("pages")));
			}
			return result;
		} catch (Exception ex) {
			System.out.println("Query failed...");
			System.out.println(ex);
			return null;
		}
	}

	@Override
	public ArrayList<Book> Get_Books_By_Name(String book_name) {
		try {
			PreparedStatement get_book = con.prepareStatement(get_books_by_name);
			get_book.setString(1, book_name);
			ResultSet resultSet = get_book.executeQuery();
			ArrayList<Book> result = new ArrayList<Book>();
			while (resultSet.next()) {
				result.add(new Book(resultSet.getInt("id"),
						resultSet.getString("book_name"),
						resultSet.getString("author_name"),
						resultSet.getString("description"),
						resultSet.getString("name"),
						resultSet.getString("binding_type"),
						resultSet.getInt("pages")));
			}
			return result;
		} catch (Exception ex) {
			System.out.println("Query failed...");
			System.out.println(ex);
			return null;
		}
	}

	@Override
	public ArrayList<Book> SelectByAuthorName(String author_name) {
		try {
			PreparedStatement get_book = con.prepareStatement(get_books_by_name);
			get_book.setString(1, author_name);
			ResultSet resultSet = get_book.executeQuery();
			ArrayList<Book> result = new ArrayList<Book>();
			while (resultSet.next()) {
				result.add(new Book(resultSet.getInt("id"),
						resultSet.getString("book_name"),
						resultSet.getString("author_name"),
						resultSet.getString("description"),
						resultSet.getString("name"),
						resultSet.getString("binding_type"),
						resultSet.getInt("pages")));
			}
			return result;
		} catch (Exception ex) {
			System.out.println("Query failed...");
			System.out.println(ex);
			return null;
		}
	}

	@Override
	public void ChangeBindingType(String book_name, String binding_type) {
		try {
			PreparedStatement edit_binding_type = con.prepareStatement(edit_binding_type_by_book_name);
			ArrayList<Book> books = Get_Books_By_Name(book_name);
			PreparedStatement insert_binding_type = con.prepareStatement(add_binding_type);
			PreparedStatement get_binding_type= con.prepareStatement(get_binding_id);

			insert_binding_type.setString(1, binding_type);
			insert_binding_type.setString(2, binding_type);
			insert_binding_type.executeUpdate();

			get_binding_type.setString(1, binding_type);
			ResultSet resultSet = get_binding_type.executeQuery();
			resultSet.next();
			int id_binding_type = resultSet.getInt("id");

			edit_binding_type.setInt(1, id_binding_type);
			edit_binding_type.setString(2, binding_type);
			edit_binding_type.executeUpdate();
			events.notifyObservers("Edit", books);
		} catch (Exception ex) {
			System.out.println("Query failed...");
			System.out.println(ex);
		}
	}

	@Override
	public void DeleteBookByName(String book_name) {
		try {
			ArrayList<Book> books = Get_Books_By_Name(book_name);
			PreparedStatement delete_book= con.prepareStatement(delete_book_by_name);
			delete_book.setString(1, book_name);
			delete_book.executeUpdate();
			events.notifyObservers("Delete", books);
		} catch (Exception ex) {
			System.out.println("Query failed...");
			System.out.println(ex);
		}
	}

	public void DeleteBookByNameProcedure(String book_name) {
		try {
			ArrayList<Book> books = Get_Books_By_Name(book_name);
			String query = "{CALL del_books(?)}";
			CallableStatement stmt = con.prepareCall(query);
			stmt.setString(1, book_name);
			stmt.executeQuery();
			events.notifyObservers("Delete", books);
		} catch (SQLException ex) {
			System.out.println(ex.getMessage());
		}
	}
}

	public void DeletePlantByNameProcedure(String plant_name) {
		try
		{
			ArrayList<Plant> plants = Get_Plants_By_Name(plant_name);
			String query = "{CALL del_plants(?)}";
			CallableStatement stmt = con.prepareCall(query);
			stmt.setString(1, plant_name);
			stmt.executeQuery();
			events.notifyObservers("Delete", plants);
		}
		catch (SQLException ex)
		{
			System.out.println(ex.getMessage());
		}
	}

	public void register_user(User usr)
	{
		try {
			PreparedStatement query = con.prepareStatement(reg_usr);
			query.setString(1, usr.getLogin());
			query.setString(2, usr.getPass());
			query.setString(3, usr.getLogin());
			query.executeUpdate();

		} catch (Exception ex) {
			System.out.println("Query failed...");
			System.out.println(ex);
		}
	}

	public Role login_user(User usr)
	{
		try {
			PreparedStatement query = con.prepareStatement(get_usr);
			query.setString(1, usr.getLogin());
			query.setString(2, usr.getPass());

			ResultSet resultSet = query.executeQuery();
			int counter = 0;
			if (resultSet.next()) {

				counter++;
				if (resultSet.getInt("id_role") == 1){
					return USER;
				}
				else {
					return ADMIN;
				}
			}
			else
			{
				return None;
			}
		} catch (Exception ex) {
			System.out.println("Query failed...");
			System.out.println(ex);
			return None;
		}
	}
}
