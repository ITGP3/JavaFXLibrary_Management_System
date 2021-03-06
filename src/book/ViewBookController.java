/**
 * @author ThiriNandarNyan
 */
package book;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import entity.Book;
import entity.BookHolder;
import javafx.scene.Node;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class ViewBookController implements Initializable {

    @FXML
    private ImageView bookImage;

    @FXML
    private Label lblId;

    @FXML
    private Label lblTitle;

    @FXML
    private Label lblAuthor;

    @FXML
    private Label lblPublisher;

    @FXML
    private Label lblAvaliable;

    @FXML
    private Label lblShelf;

    @FXML
    private Label lblCategory;

    @FXML
    private Label lblStatus;

    @FXML
    void processBack(MouseEvent event) throws IOException {

    	Stage primaryStage = (Stage)((Node)event.getSource()).getScene().getWindow();
		primaryStage.hide();
    }
    
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
		BookHolder bookHolder = BookHolder.getBookHolder();
		Book book = bookHolder.getBook();
		
		bookImage.setImage(new Image(getClass().getResourceAsStream("../image/bookSection/"+book.getBookImageName())));
		lblId.setText(book.getBookId());
		lblTitle.setText(book.getBookTitle());
		lblAuthor.setText(book.getBookAuthor());
		lblPublisher.setText(book.getBookPublisher());
		lblAvaliable.setText(book.getBookAvaliable().toString());
		lblShelf.setText(book.getBookShelf());
		lblCategory.setText(book.getBookCategory());
		lblStatus.setText(book.getBookStatus());
		
	}

}
