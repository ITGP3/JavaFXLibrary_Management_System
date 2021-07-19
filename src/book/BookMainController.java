/**
 * @author ThiriNandarNyan
 */
package book;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

import entity.Book;
import entity.BookHolder;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import utility.BookDataUtils;

public class BookMainController implements Initializable{

    @FXML
    private TableView<Book> bookTable;

    @FXML
    private TableColumn<Book, String> bookId;

    @FXML
    private TableColumn<Book, String> bookTitle;

    @FXML
    private TableColumn<Book, String> bookAuthor;

    @FXML
    private TableColumn<Book, String> bookPublisher;

    @FXML
    private TableColumn<Book, Boolean> bookAvaliable;

    @FXML
    private TableColumn<Book, String> bookShelf;

    @FXML
    private TableColumn<Book, String> bookCategory;

    @FXML
    private TableColumn<Book, String> bookStatus;

    @FXML
    private TableColumn<Book, String> bookImageName;
    
    private BookDataUtils bookDataUtils = new BookDataUtils();
    
    @FXML
    void processAdd(MouseEvent event) throws IOException {

    	Stage primaryStage = new Stage();
    	Parent root = FXMLLoader.load(getClass().getResource("AddBookUI.fxml"));
        primaryStage.setTitle("ADD BOOK SECTION");
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
     
    }

    @FXML
    void processDelete(MouseEvent event) {

    }

    @FXML
    void processEdit(MouseEvent event) throws IOException {
    	
    	Book book = bookTable.getSelectionModel().getSelectedItem();
    	
    	BookHolder bookHolder = BookHolder.getBookHolder();
    	bookHolder.setBook(book);
    	
    	Stage primaryStage = new Stage();
    	Parent root = FXMLLoader.load(getClass().getResource("editBook/EditBookUI.fxml"));
        primaryStage.setTitle("ADD BOOK SECTION");
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }

    @FXML
    void processRefresh(MouseEvent event) {

    	showAllBook("select * from book");
    }

    @FXML
    void processView(MouseEvent event) {

    }

    public void showAllBook(String sql) {
    	try {
			bookTable.setItems(bookDataUtils.getAllBook(sql));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
    
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
		bookId.setCellValueFactory(new PropertyValueFactory<>("bookId"));
		bookTitle.setCellValueFactory(new PropertyValueFactory<>("bookTitle"));
		bookAuthor.setCellValueFactory(new PropertyValueFactory<>("bookAuthor"));
		bookPublisher.setCellValueFactory(new PropertyValueFactory<>("bookPublisher"));
		bookAvaliable.setCellValueFactory(new PropertyValueFactory<>("bookAvaliable"));
		bookShelf.setCellValueFactory(new PropertyValueFactory<>("bookShelf"));
		bookCategory.setCellValueFactory(new PropertyValueFactory<>("bookCategory"));
		bookStatus.setCellValueFactory(new PropertyValueFactory<>("bookStatus"));
		bookImageName.setCellValueFactory(new PropertyValueFactory<>("bookImageName"));
		
		showAllBook("select * from book");
	}


}
