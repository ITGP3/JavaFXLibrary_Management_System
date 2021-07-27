/**
 * @author ThinYanantZaw
 */
package book;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

import javax.imageio.ImageIO;

import entity.Book;
import entity.BookHolder;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.embed.swing.SwingFXUtils;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
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
    private TextField tfAvaliable;

    @FXML
    private ComboBox<String> cobShelf;

    @FXML
    private ComboBox<String> cobStatus;

    @FXML
    private ComboBox<String> cobCategory;

    @FXML
    private TextField tfId;
    
    private String oldImagename;
    
    private String imageName;
    
    private final BookDataUtils bookDataUtils = new BookDataUtils();
    
    private MyAlert alert = new MyAlert();

    
    @FXML
    void processBack(MouseEvent event) throws IOException {
    	Stage adminStage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        adminStage.hide();
        Parent root = FXMLLoader.load(getClass().getResource("bookMainUI.fxml"));
        adminStage.setScene(new Scene(root));
        adminStage.show();
    }
    
    @FXML
    void processEdit(MouseEvent event) throws SQLException, IOException {

    	String bookId = tfId.getText();
    	String bookTitle = tfTitle.getText().trim();
    	String bookAuthor = tfAuthor.getText().trim();
    	String bookPublisher = tfPublisher.getText().trim();
    	Boolean bookAvaliable = Boolean.parseBoolean(tfAvaliable.getText());
    	String bookShelf = cobShelf.getValue();
    	String bookCategory = cobCategory.getValue();
    	String bookStatus = cobStatus.getValue();
    	   	
    	if(this.imageName != null && !this.imageName.isEmpty()) {
    		int index = this.imageName.indexOf(".");
    		imageName = this.imageName.substring(0,index)+".jpg";
    	}else {
    		imageName = this.oldImagename;
    	}
    	
    	Book book = new Book(bookId, bookTitle, bookAuthor, bookPublisher, bookAvaliable, bookShelf, bookCategory, bookStatus, imageName);
    	
    	Boolean updateOk = bookDataUtils.updateBook(book);
    	
    	if(!updateOk) {
    		alert.getConfirmAlert("Information Dialog", "Successfully Updated!", "Updated Book to DB");
    		
    		File deletedImg = new File("src/image/bookSection/"+this.oldImagename);
			
			deletedImg.delete();
			
			File imageFile = new File("src/image/bookSection/"+imageName);
			
			BufferedImage bufferedImage = SwingFXUtils.fromFXImage(this.bookImage.getImage(),null);
			
			ImageIO.write(bufferedImage,"jpg",imageFile);
			
			Stage primaryStage = (Stage)((Node)event.getSource()).getScene().getWindow();
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
		
		
		BookHolder bookHolder = BookHolder.getBookHolder();
		Book book = bookHolder.getBook();
		
		bookImage.setImage(new Image(getClass().getResourceAsStream("../image/bookSection/"+book.getBookImageName())));
		this.oldImagename = book.getBookImageName();
		tfId.setText(book.getBookId());
		tfTitle.setText(book.getBookTitle());
		tfAuthor.setText(book.getBookAuthor());
		tfPublisher.setText(book.getBookPublisher());
		tfAvaliable.setText(book.getBookAvaliable().toString());
		cobShelf.setValue(book.getBookShelf());
		cobCategory.setValue(book.getBookCategory());
		cobStatus.setValue(book.getBookStatus());
		
	}

}
