/**
 * @author ThiriNandarNyan
 */

package utility;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import DBConnection.DBConnection;
import entity.Book;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class BookDataUtils {
	
	private Connection connection;
	private Statement statement;
	private PreparedStatement preStmt;
	private ResultSet resultSet;
	
	//select 
	public ObservableList<Book> getAllBook(String sql) throws SQLException{
		
		ObservableList<Book> bookList = FXCollections.observableArrayList();
		
		connection = DBConnection.getConnection();
		statement = connection.createStatement();
		resultSet = statement.executeQuery(sql);
		
		while(resultSet.next()) {
			bookList.add(
					new Book(
							resultSet.getString("bookId"),
							resultSet.getString("bookTitle"),
							resultSet.getString("bookAuthor"),
							resultSet.getString("bookPublisher"),
							resultSet.getBoolean("bookAvaliable"),
							resultSet.getString("bookShelf"),
							resultSet.getString("bookCategory"),
							resultSet.getString("bookStatus"),
							resultSet.getString("bookImageName")
							)
					);
		}
		
		connection.close();
		return bookList;
	}
	
	//Save
	public Boolean saveBook(Book book) throws SQLException {
		
		connection = DBConnection.getConnection();
		preStmt = connection.prepareStatement("INSERT INTO `book` ("
				+ "`bookId`,`bookTitle`, `bookAuthor`, `bookPublisher`, `bookAvaliable`, `bookShelf`, "
				+ "`bookCategory`, `bookStatus`, `bookImageName`) "
				+ "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)");
		
		preStmt.setString(1, book.getBookId());
		preStmt.setString(2, book.getBookTitle());
		preStmt.setString(3, book.getBookAuthor());
		preStmt.setString(4, book.getBookPublisher());
		preStmt.setBoolean(5, book.getBookAvaliable());
		preStmt.setString(6, book.getBookShelf());
		preStmt.setString(7, book.getBookCategory());
		preStmt.setString(8, book.getBookStatus());
		preStmt.setString(9, book.getBookImageName());
		
		Boolean isSaveOk = preStmt.execute();
		
		connection.close();
		return isSaveOk;
	}

}