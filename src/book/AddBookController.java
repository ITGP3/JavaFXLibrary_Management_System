package book;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

import javax.imageio.ImageIO;

import entity.Book;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.FileChooser.ExtensionFilter;
import utility.BookDataUtils;

public class AddBookController implements Initializable {

    @FXML
    private ImageView bookImage;

    @FXML
    private TextField tfId;
    
    @FXML
    private TextField tfTitle;

    @FXML
    private TextField tfAuthor;

    @FXML
    private TextField tfPublisher;

    @FXML
    private ComboBox<String> cobAvaliable;

    @FXML
    private ComboBox<String> cobShelf;

    @FXML
    private ComboBox<String> cobStatus;

    @FXML
    private ComboBox<String> cobCategory;
    
    private String imageName;
    
    private BookDataUtils bookDataUtil = new BookDataUtils();

    @FXML
    void processAdd(ActionEvent event) throws SQLException, IOException {

    	String bookId = tfId.getText().trim();
    	String bookTitle = tfTitle.getText().trim();
    	String bookAuthor = tfAuthor.getText().trim();
    	String bookPublisher = tfPublisher.getText().trim();
    	Boolean bookAvaliable = Boolean.parseBoolean(cobAvaliable.getValue());
    	String bookShelf = cobShelf.getValue();
    	String bookCategory = cobCategory.getValue();
    	String bookStatus = cobStatus.getValue();
    	
    	int index = this.imageName.indexOf(".");
		imageName = this.imageName.substring(0,index)+".jpg";
    	
    	Book book = new Book(bookId,bookTitle, bookAuthor, bookPublisher, bookAvaliable, bookShelf, bookCategory, bookStatus, imageName);
    	Boolean isSaveOk = bookDataUtil.saveBook(book);
    	if(!isSaveOk) {
			
			File imageFile = new File("src/image/bookSection/"+imageName);
			
			BufferedImage bufferedImage = SwingFXUtils.fromFXImage(this.bookImage.getImage(),null);
			
			ImageIO.write(bufferedImage,"jpg",imageFile);
		
			
			Stage primaryStage = (Stage)((Button)event.getSource()).getScene().getWindow();
			primaryStage.hide();
		}
    }

    @FXML
    void processImage(MouseEvent event) {

    	FileChooser imageChooser = new FileChooser();
    	imageChooser.setInitialDirectory(new File("/Users/sitminhtet/Downloads"));
    	imageChooser.getExtensionFilters().add(new ExtensionFilter("Image Files", "*.png","*.jpg","*.jpeg","*.ico"));
    	
    	File imageFile = imageChooser.showOpenDialog(null);
    	if(imageFile != null) {
    		Image image = new Image(imageFile.toURI().toString());
    		bookImage.setImage(image);
    		this.imageName = imageFile.getName();
    	}
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
				"Good","Lose Book","Damage"
				);
		ObservableList<String> categoryList = FXCollections.observableArrayList(
				"Fantasy","Romance","Horror","Comedy","Sci-Fi","Mystery","Thriller","Science Fiction"
				);
		
		cobAvaliable.setItems(avaliableList);
		cobShelf.setItems(shelfList);
		cobStatus.setItems(statusList);
		cobCategory.setItems(categoryList);
	}

}
