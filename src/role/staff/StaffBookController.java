/**
 * @author ThinYanantZaw
 */

package role.staff;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

import entity.Book;
import entity.BookHolder;
import entity.UserHolder;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import utility.BookDataUtils;

public class StaffBookController implements Initializable{

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

    @FXML
    private TextField tfSearch;

    @FXML
    private ComboBox<String> cobBookCol;

    @FXML
    private Label lblLoginMail;
    
    private BookDataUtils bookDataUtils = new BookDataUtils();

    @FXML
    void processRefresh(MouseEvent event) {
    	
    	cobBookCol.getSelectionModel().clearSelection();
        tfSearch.setText("");
        showAllBook("select * from book;");

    }

    @FXML
    void processSearch(MouseEvent event) {
    	
    	String column = cobBookCol.getValue();

        String query = tfSearch.getText().trim();

        showAllBook("select * from book where "+column+" ='"+query+"';");

    }

    @FXML
    void processView(MouseEvent event) throws IOException {
    	
    	 Book book = bookTable.getSelectionModel().getSelectedItem();

         BookHolder bookHolder = BookHolder.getBookHolder();
         bookHolder.setBook(book);

         Stage primaryStage = new Stage();
         Parent root = FXMLLoader.load(getClass().getResource("../../book/ViewBookUI.fxml"));
         primaryStage.setTitle("VIEW BOOK SECTION");
         primaryStage.setScene(new Scene(root));
         primaryStage.show();

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
		
		UserHolder userHolder = UserHolder.getUserHolder();
        lblLoginMail.setText(userHolder.getEmail());

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

        try {
            cobBookCol.setItems(bookDataUtils.getAllColumn());
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
		
	}

}
