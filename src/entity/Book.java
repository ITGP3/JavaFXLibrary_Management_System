/**
 * @author ThiriNandarNyan
 */

package entity;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Book {

	private StringProperty bookId;
	private StringProperty bookTitle;
	private StringProperty bookAuthor;
	private StringProperty bookPublisher;
	private BooleanProperty bookAvaliable;
	private StringProperty bookShelf;
	private StringProperty bookCategory;
	private StringProperty bookStatus;
	private StringProperty bookImageName;
	
	public Book(String bookId, String bookTitle, String bookAuthor, String bookPublisher, Boolean bookAvaliable,
			String bookShelf, String bookCategory, String bookStatus, String bookImageName) {
		super();
		this.bookId = new SimpleStringProperty(bookId);
		this.bookTitle = new SimpleStringProperty(bookTitle);
		this.bookAuthor = new SimpleStringProperty(bookAuthor);
		this.bookPublisher = new SimpleStringProperty(bookPublisher);
		this.bookAvaliable = new SimpleBooleanProperty(bookAvaliable);
		this.bookShelf = new SimpleStringProperty(bookShelf);
		this.bookCategory = new SimpleStringProperty(bookCategory);
		this.bookStatus = new SimpleStringProperty(bookStatus);
		this.bookImageName = new SimpleStringProperty(bookImageName);
	}
	
	public Book(String bookTitle, String bookAuthor, String bookPublisher, Boolean bookAvaliable, String bookShelf,
			String bookCategory, String bookStatus, String bookImageName) {
		super();
		this.bookTitle = new SimpleStringProperty(bookTitle);
		this.bookAuthor = new SimpleStringProperty(bookAuthor);
		this.bookPublisher = new SimpleStringProperty(bookPublisher);
		this.bookAvaliable = new SimpleBooleanProperty(bookAvaliable);
		this.bookShelf = new SimpleStringProperty(bookShelf);
		this.bookCategory = new SimpleStringProperty(bookCategory);
		this.bookStatus = new SimpleStringProperty(bookStatus);
		this.bookImageName = new SimpleStringProperty(bookImageName);
	}

	public Book(String bookId){
		this.bookId = new SimpleStringProperty(bookId);
	}
	
	public String getBookId() {
		return bookId.get();
	}
	public void setBookId(String bookId) {
		this.bookId = new SimpleStringProperty(bookId);
	}
	public String getBookTitle() {
		return bookTitle.get();
	}
	public void setBookTitle(String bookTitle) {
		this.bookTitle = new SimpleStringProperty(bookTitle);
	}
	public String getBookAuthor() {
		return bookAuthor.get();
	}
	public void setBookAuthor(String bookAuthor) {
		this.bookAuthor = new SimpleStringProperty(bookAuthor);
	}
	public String getBookPublisher() {
		return bookPublisher.get();
	}
	public void setBookPublisher(String bookPublisher) {
		this.bookPublisher = new SimpleStringProperty(bookPublisher);
	}
	public Boolean getBookAvaliable() {
		return bookAvaliable.get();
	}
	public void setBookAvaliable(Boolean bookAvaliable) {
		this.bookAvaliable = new SimpleBooleanProperty(bookAvaliable);
	}
	public String getBookShelf() {
		return bookShelf.get();
	}
	public void setBookShelf(String bookShelf) {
		this.bookShelf = new SimpleStringProperty(bookShelf);
	}
	public String getBookCategory() {
		return bookCategory.get();
	}
	public void setBookCategory(String bookCategory) {
		this.bookCategory = new SimpleStringProperty(bookCategory);
	}
	public String getBookStatus() {
		return bookStatus.get();
	}
	public void setBookStatus(String bookStatus) {
		this.bookStatus = new SimpleStringProperty(bookStatus);
	}
	public String getBookImageName() {
		return bookImageName.get();
	}
	public void setBookImageName(String bookImageName) {
		this.bookImageName = new SimpleStringProperty(bookImageName);
	}
	
}
