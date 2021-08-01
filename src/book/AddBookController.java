package book;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.Optional;
import java.util.ResourceBundle;
import javax.imageio.ImageIO;
import entity.Book;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.embed.swing.SwingFXUtils;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.FileChooser.ExtensionFilter;
import utility.BookDataUtils;
import utility.MyAlert;

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
    private TextField tfAvaliable;

    @FXML
    private ComboBox<String> cobShelf;

    @FXML
    private ComboBox<String> cobStatus;

    @FXML
    private ComboBox<String> cobCategory;
    
    private String imageName;
    
    private BookDataUtils bookDataUtil = new BookDataUtils();
    private MyAlert alert = new MyAlert();
    
    @FXML
    void processClear(MouseEvent event) {
    	Optional<ButtonType> result = alert.getConfirmAlert("Confirmation Dialog", "Are u sure u want to clear all field?", "This action will clear all data from all fields.");
		
		if(result.get() == ButtonType.OK) {
			clearAllField();
		}
    }

	public void clearAllField() {
		tfId.clear();
		tfTitle.clear();
		tfAuthor.clear();
		tfPublisher.clear();
		cobShelf.getSelectionModel().clearSelection();
		cobStatus.getSelectionModel().clearSelection();
		cobCategory.getSelectionModel().clearSelection();
		bookImage.setImage(new Image(getClass().getResourceAsStream("../image/bookSection/addimg.png")));
		
	}

    @FXML
    void processAdd(MouseEvent event) throws SQLException, IOException {

    	String bookId = tfId.getText().trim();
    	String bookTitle = tfTitle.getText().trim();
    	String bookAuthor = tfAuthor.getText().trim();
    	String bookPublisher = tfPublisher.getText().trim();
    	Boolean bookAvaliable = Boolean.parseBoolean(tfAvaliable.getText());
    	String bookShelf = cobShelf.getValue();
    	String bookCategory = cobCategory.getValue();
    	String bookStatus = cobStatus.getValue();
    	
    	int index = this.imageName.indexOf(".");
		imageName = this.imageName.substring(0,index)+".jpg";
    	
    	Book book = new Book(bookId,bookTitle, bookAuthor, bookPublisher, bookAvaliable, bookShelf, bookCategory, bookStatus, imageName);
    	Boolean isSaveOk = bookDataUtil.saveBook(book);
    	if(!isSaveOk) {
			
    		alert.getConfirmAlert("Information Dialog", "Successfully Saved!", "Saved Book to DB");
    		
			File imageFile = new File("src/image/bookSection/"+imageName);
			
			BufferedImage bufferedImage = SwingFXUtils.fromFXImage(this.bookImage.getImage(),null);
			
			ImageIO.write(bufferedImage,"jpg",imageFile);
			
			Stage primaryStage =  (Stage) ((Node)event.getSource()).getScene().getWindow();
			primaryStage.hide();
		}
    }

    @FXML
    void processImage(MouseEvent event) {

    	FileChooser imageChooser = new FileChooser();
    	imageChooser.setInitialDirectory(new File("c://"));
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
		
		tfAvaliable.setText("true");
		
		ObservableList<String> shelfList = FXCollections.observableArrayList(
				"shelf1","shelf2","shelf3","shelf4","shelf5"
				);
		
		ObservableList<String> statusList = FXCollections.observableArrayList(
				"Good","Lose Book","Damage"
				);
		
		ObservableList<String> categoryList = FXCollections.observableArrayList(
				"Fantasy","Romance","Horror","Comedy","Sci-Fi","Mystery","Thriller","Science Fiction"
				);
		
	
		cobShelf.setItems(shelfList);
		cobStatus.setItems(statusList);
		cobCategory.setItems(categoryList);
	}

}
