package book;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.Optional;
import java.util.ResourceBundle;

import DBConnection.DBConnection;
import entity.Book;
import entity.BookHolder;
import entity.UserHolder;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import utility.BookDataUtils;
import utility.MyAlert;

public class BookMainController implements Initializable{

    @FXML
    private Label lblLoginMail;

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

    private BookDataUtils bookDataUtils = new BookDataUtils();

    private MyAlert alert = new MyAlert();

    ObservableList<Book> bookList = FXCollections.observableArrayList();

    @FXML
    void processAdd(MouseEvent event) throws IOException {

        Stage primaryStage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("AddBookUI.fxml"));
        primaryStage.setTitle("ADD BOOK SECTION");
        primaryStage.setScene(new Scene(root));
        primaryStage.show();


    }

    @FXML
    void processDelete(MouseEvent event) throws SQLException {

        Book book = bookTable.getSelectionModel().getSelectedItem();
        if (book == null){
            Alert alertIssue = new Alert(Alert.AlertType.ERROR);
            alertIssue.setTitle("Warning!");
            alertIssue.setHeaderText(null);
            alertIssue.setContentText("Select Book to Delete");
            alertIssue.showAndWait();
            return;
        }

        if(bookDataUtils.bookAlreadyIssued(book)){
            Alert alertIssue = new Alert(Alert.AlertType.ERROR);
            alertIssue.setTitle("Warning!!!");
            alertIssue.setHeaderText(null);
            alertIssue.setContentText("Issue Book Can not delete");
            alertIssue.showAndWait();
            return;

        }



        Optional<ButtonType> result = alert.getConfirmAlert("Confirmation Dialog", "Are u sure u want to delete?",  "This action will delete your selected item");

        if(result.get() == ButtonType.OK) {

            Boolean deleteOk = bookDataUtils.deleteBook(book);


            if(!deleteOk) {

                File deletedFile = new File("src/image/bookSection/"+book.getBookImageName());
                deletedFile.delete();


                Alert alertIssue = new Alert(Alert.AlertType.INFORMATION);
                alertIssue.setTitle("Success!!!");
                alertIssue.setHeaderText(null);
                alertIssue.setContentText("Book Deleted");
                alertIssue.showAndWait();

                showAllBook("select * from book");
            }
        }
        else {
            Alert alertIssue = new Alert(Alert.AlertType.INFORMATION);
            alertIssue.setTitle("Fail!!!");
            alertIssue.setHeaderText(null);
            alertIssue.setContentText("Book Delete Fail");
            alertIssue.showAndWait();
        }
    }

    @FXML
    void processEdit(MouseEvent event) throws IOException {

        Book book = bookTable.getSelectionModel().getSelectedItem();

        BookHolder bookHolder = BookHolder.getBookHolder();
        bookHolder.setBook(book);

        Stage primaryStage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("EditBookUI.fxml"));
        primaryStage.setTitle("EDIT BOOK SECTION");
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }

    @FXML
    void processRefresh(MouseEvent event) {

        cobBookCol.getSelectionModel().clearSelection();
        tfSearch.setText("");
        showAllBook("select * from book");
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
        Parent root = FXMLLoader.load(getClass().getResource("ViewBookUI.fxml"));
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
