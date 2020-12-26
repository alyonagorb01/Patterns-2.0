package ua.nure.st.kpp.example.demo.form;

public class EditBookBindingForm {
    private String bindingType;
    private String bookName;

    public EditBookBindingForm() {

    }

    public EditBookBindingForm(String bindingType, String bookName) {

        this.bindingType = bindingType;
        this.bookName = bookName;
    }

    public String getBinding_type() {
        return bindingType;
    }

    public void setBinding_type(String bindingType) {
        this.bindingType = bindingType;
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }


}
