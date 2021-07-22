/**
 * @author ThiriNandarNyan
 */
package entity;

public class BookHolder {

	private Book book;
	
	private final static BookHolder BOOK_HOLDER = new BookHolder();

	public Book getBook() {
		return book;
	}

	public void setBook(Book book) {
		this.book = book;
	}

	public static BookHolder getBookHolder() {
		return BOOK_HOLDER;
	}
	
	
}
