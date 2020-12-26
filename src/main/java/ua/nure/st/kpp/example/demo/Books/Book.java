package ua.nure.st.kpp.example.demo.Books;

import ua.nure.st.kpp.example.demo.Memento.Memento;
import ua.nure.st.kpp.example.demo.MyDAO.DAOFactory;

public class Book implements Comparable<Book>
{

    private long id;
    private String book_name;
    private String author_name;
    private String binding_type;
    private String description;
    private String name;
    private int pages;




    public static class Builder {
        private long id = 0;
        private String book_name;
        private String author_name = "";
        private String binding_type = "";
        private String description = "";
        private String name = "";
        private int pages = 0;

        public Builder(String book_name)
        {
            this.book_name = book_name;
        }

        public Builder buildInt(int id)
        {
            this.id = id;
            return this;
        }

        public Builder buildPages(int pages)
        {
            this.pages = pages;
            return this;
        }
        public Builder buildAuthorName(String author_name){
            this.author_name = author_name;
            return this;
        }
        public Builder buildBindingType(String binding_type){
            this.binding_type = binding_type;
            return this;
        }
        public Builder buildBookDescription(String description){
            this.description = description;
            return this;
        }

        public Builder buildPbName(String name){
            this.name = name;
            return this;
        }

        public Book build() {
            return new Book(this);
        }
    }

    public Book() {
        // all null
    }

    public Book(Book temp)
    {
        copy(temp);
    }

    public Book(Builder builder)
    {
        this.id = builder.id;
        this.book_name = builder.book_name;
        this.author_name = builder.author_name;
        this.binding_type = builder.binding_type;
        this.description = builder.description;
        this.pages = builder.pages;
        this.name = builder.name;
    }


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }


    public Memento saveState() {
        return new Memento(this);
    }

    public void restoreState(Memento memento) {
        copy(memento.getState());
    }

    public void copy(Book temp)
    {
        this.id = temp.id;
        this.book_name = temp.book_name;
        this.author_name = temp.author_name;
        this.description = temp.description;
        this.binding_type = temp.binding_type;
        this.name = temp.name;
        this.pages = temp.pages;
    }


    public String getName() {
        return book_name;
    }

    public void setName(String name) {
        this.book_name = book_name;
    }

    public void setPages(int pages) {
        if (pages >= 0)
        {
            this.pages = pages;
        }
        else
        {
            throw new IllegalArgumentException("Bad input");
        }
    }
    public String getBinding_type() {
        return binding_type;
    }
    public void setGBinding_type(String binding_type) {
        this.binding_type = binding_type;
    }

    public String getAuthor_name() {
        return author_name;
    }

    public void setAuthor_name(String author_name) {
        this.author_name = author_name;
    }

    public String getBook_description() {
        return description;
    }

    public void setBook_description(String description) {
        this.description = description;
    }
    public String getPb_name() {
        return name;
    }

    public void setPb_name(String name) {
        this.name = name;
    }

    public int getPages() {
        return pages;
    }

    public void get_Description()
    {
        String message = "";
        message+="Информация о книге " + book_name + '\n';
        message+="Имя автора" + author_name + '\n';
        message+="Переплет" + binding_type + '\n';
        message+="Описание " + description + '\n';
        message+="Страниц " + pages + '\n';
        message+="Издательство " + name + '\n';
        System.out.print(message);
    }

    @Override
    public String toString()
    {
        return "Name: " + book_name + ", author name: " + author_name + ", binding type: " + binding_type + ", description: " + description+ ", pages: " + pages + ", publishing_house: " + name ;
    }

    public Book(long id, String book_name, String author_name, String description, String name, String binding_type, int pages)
    {
        this.id = id;
        this.book_name = book_name;
        this.description = description;
        this.author_name = author_name;
        this.binding_type = binding_type;
        this.name = name;
        this.pages = pages;
    }


    @Override
    public int compareTo(Book other)
    {
        if (this.pages == other.pages)
        {
            return 0;
        }
        else if (this.pages < other.pages)
        {
            return -1;
        }
        else
        {
            return 1;
        }
    }
}