package ua.nure.st.kpp.example.demo.form;

public class AddBookForm {

	private long id;
	private String book_name;
	private String author_name;
	private String binding_type;
	private String description;
	private String name;
	private int pages;

	public AddBookForm() {
		// all null
	}

	public AddBookForm(long id, String book_name, String author_name, String description, String name, String binding_type, int pages)
	{
		this.id = id;
		this.book_name = book_name;
		this.description = description;
		this.author_name = author_name;
		this.binding_type = binding_type;
		this.name = name;
		this.pages = pages;
		setPages(pages);

	}

	public int getPages() {
		return pages;
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


	public String getName() {
		return book_name;
	}

	public void setName(String book_name) {
		this.book_name = book_name;
	}
	public String getAuthor_name() {
		return author_name;
	}

	public void setAuthor_name(String author_name) {
		this.author_name = author_name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}



	public String getBinding_type() {
		return binding_type;
	}

	public void setBinding_type(String binding_type) {
		this.binding_type = binding_type;
	}



	public String getPb_name() {
		return name;
	}

	public void setPb_name(String name) {
		this.name = name;
	}


}
