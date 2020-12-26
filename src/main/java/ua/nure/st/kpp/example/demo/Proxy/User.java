package ua.nure.st.kpp.example.demo.Proxy;

import ua.nure.st.kpp.example.demo.Books.Book;
import ua.nure.st.kpp.example.demo.MyDAO.MySQLDAO;

import java.util.ArrayList;

public class User {
    private String login;
    private String pass;
    private Role role;
    private boolean auth;
    private MySQLDAO dao;

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public User(String login, String pass) {
        this.login = login;
        this.pass = pass;
        role = Role.None;
        dao = new MySQLDAO();
    }

    public User()
    {
        role = Role.None;
        dao = new MySQLDAO();
    }

    public void login()
    {
        role = dao.login_user(this);
    }

    public void register()
    {
        dao.register_user(this);
    }

    public void changeRole(Role rl)
    {
        role = rl;
    }

    public Plant Insert_Book(Book book)
    {
        return dao.Insert_Book(book, role);
    }

    public ArrayList<Book> Get_Books()
    {
        return dao.Get_Books();
    }

    public ArrayList<Books> SelectByAuthorName(String author_name)
    {
        return dao.SelectByAuthorName(author_name);
    }
    public void ChangeBindingType(String book_name, String binding_type)
    {
        dao. ChangeBindingType (book_name, binding_type, role);
    }
    public void DeleteBookByName(String book_name)
    {
        dao. DeleteBookByName (book_name, role);
    }
    public ArrayList<Book> Get_Books_By_Name(String book_name)
    {
        return dao.Get_Books_By_Name(book_name);
    }
}

