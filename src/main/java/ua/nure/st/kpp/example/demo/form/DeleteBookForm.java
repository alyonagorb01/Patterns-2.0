package ua.nure.st.kpp.example.demo.form;

public class DeleteBookForm {
	private String bookName;

	public String getBookName() {
		return bookName;
	}
	public void setBookName(String bookName) {
		this.bookName = bookName;
	}

	public DeleteBookForm( String bookName) {
		this.bookName = bookName;

	}

	public DeleteBookForm() {
	}
}
