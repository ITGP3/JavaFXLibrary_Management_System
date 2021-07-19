package book;

import java.net.URL;
import java.util.ResourceBundle;

import entity.Book;
import entity.BookHolder;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

public class EditBookController implements Initializable{

    @FXML
    private ImageView bookImage;

    @FXML
    private TextField tfTitle;

    @FXML
    private TextField tfAuthor;

    @FXML
    private TextField tfPublisher;

    @FXML
    private ComboBox<String> cobShelf;

    @FXML
    private ComboBox<String> cobStatus;

    @FXML
    private ComboBox<String> cobCategory;

    @FXML
    private ComboBox<String> cobAvaliable;

    @FXML
    private TextField tfId;

    @FXML
    void processEdit(ActionEvent event) {

    	
    }

    @FXML
    void processImage(MouseEvent event) {

    }

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
		ObservableList<String> avaliableList = FXCollections.observableArrayList(
				"True","False"
				);
		
		ObservableList<String> shelfList = FXCollections.observableArrayList(
				"shelf1","shelf2"
				);
		ObservableList<String> statusList = FXCollections.observableArrayList(
				"Lose Book","Damage"
				);
		ObservableList<String> categoryList = FXCollections.observableArrayList(
				"Fantasy","Romance","Horror","Comedy","Sci-Fi","Mystery","Thriller","Science Fiction"
				);
		
		cobAvaliable.setItems(avaliableList);
		cobShelf.setItems(shelfList);
		cobStatus.setItems(statusList);
		cobCategory.setItems(categoryList);
		
		
		BookHolder bookHolder = BookHolder.getBookHolder();
		Book book = bookHolder.getBook();
		
		bookImage.setImage(new Image(getClass().getResourceAsStream("../image/bookSection/"+book.getBookImageName())));
		tfId.setText(book.getBookId());
		tfTitle.setText(book.getBookTitle());
		tfAuthor.setText(book.getBookAuthor());
		tfPublisher.setText(book.getBookPublisher());
		cobAvaliable.setValue(book.getBookAvaliable().toString());
		cobShelf.setValue(book.getBookShelf());
		cobCategory.setValue(book.getBookCategory());
		cobStatus.setValue(book.getBookStatus());
		
	}

}
