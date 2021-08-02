package issueBook;

import DBConnection.DBConnection;
import entity.Book;
import entity.IssueBook;
import entity.UserHolder;
import javafx.animation.FadeTransition;
import javafx.beans.property.SimpleStringProperty;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;
import utility.IssueBookUtility;

import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;
import java.util.ResourceBundle;


/**
 *
 * @author Sit Min Htet
 *
 */

public class IssueController implements Initializable {

    @FXML
    private TextField tfIssueBookId;

    @FXML
    private Label lblBookName;

    @FXML
    private Label lblBookAuthor;

    @FXML
    private Label lblBookShelf;

    @FXML
    private Label lblBookStatus;

    @FXML
    private TextField tfIssueMemberId;

    @FXML
    private Label lblMemberName;

    @FXML
    private Label lblMemberEmail;

    @FXML
    private Label lblMemberPhone;

    @FXML
    private Label lblLoginEmail;

    @FXML
    private Label lblMemberAddress;

    @FXML
    private Label lblNoBook;

    @FXML
    private Label lblNoMember;

    @FXML
    private Label lblStatus;

    @FXML
    private AnchorPane rootPane;

    private final IssueBookUtility issueBookUtility = new IssueBookUtility();
    private final DBConnection dbConnection = new DBConnection();

    @FXML
    void processIssueBookId(ActionEvent event) throws SQLException {
        boolean isSearchOK = false;
        String id = tfIssueBookId.getText().trim();

        String searchQuery = "select * from library.book where bookId= '"+ id +"'";
        ResultSet resultSet = dbConnection.executeQuery(searchQuery);

        if (resultSet.next()){
        	lblNoBook.setText("");
            lblBookName.setText(resultSet.getString("bookTitle"));
            lblBookAuthor.setText(resultSet.getString("bookAuthor"));
            lblBookShelf.setText(resultSet.getString("bookShelf"));
            String status = (resultSet.getBoolean("bookAvaliable"))?"Avaliable":"Not Avaliable";
            lblBookStatus.setText(String.valueOf(status));
            lblStatus.setText(resultSet.getString("bookStatus"));



            isSearchOK = true;
        }

        if (!isSearchOK){
            lblNoBook.setText("No Such Book Avaliable!!");
            lblBookName.setText("");
            lblBookAuthor.setText("");
            lblBookShelf.setText("");
            lblBookStatus.setText("");
            lblStatus.setText("");
        }

    }

    @FXML
    void processIssueMemberId(ActionEvent event) throws SQLException {
        boolean isSearchOK = false;
        String memberId = tfIssueMemberId.getText().trim();
        String query = "select * from library.member where memberId= '"+ memberId +"'";
        ResultSet resultSet = dbConnection.executeQuery(query);

        if (resultSet.next()){
            String name = resultSet.getString("memberName");
            String email = resultSet.getString("memberEmail");
            String phone = resultSet.getString("memberPhone");
            String address = resultSet.getString("memberAddress");

            lblNoMember.setText("");
            lblMemberName.setText(name);
            lblMemberEmail.setText(email);
            lblMemberPhone.setText(phone);
            lblMemberAddress.setText(address);


            isSearchOK = true;
        }

        if (!isSearchOK){
            lblNoMember.setText("No Such Member is Register!!!");
            lblMemberName.setText("");
            lblMemberEmail.setText("");
            lblMemberPhone.setText("");
            lblMemberAddress.setText("");
        }
    }

    @FXML
    void processIssue(ActionEvent event) throws SQLException {
        String bookId = tfIssueBookId.getText().trim();
        String memberId = tfIssueMemberId.getText().trim();

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Comfirm Issue Operation");
        alert.setHeaderText(null);
        alert.setContentText("Are you sure want to issue the book"+
                bookId+"\n to"+memberId+"?");

        Optional<ButtonType> issueButton = alert.showAndWait();
        if (issueButton.get()== ButtonType.OK) {
            IssueBook issueBook = new IssueBook(bookId, memberId);

            Book book = new Book(bookId);
            String issueQuery = "select * from library.book where bookAvaliable=false and bookId='"+bookId+"';";
            ResultSet resultSet = dbConnection.executeQuery(issueQuery);
            if (resultSet.next()){

                Alert alertIssue = new Alert(Alert.AlertType.ERROR);
                alertIssue.setTitle("Fail!!!");
                alertIssue.setHeaderText(null);
                alertIssue.setContentText("Book is already Issued");
                alertIssue.showAndWait();


            }
            else {
                if (!issueBookUtility.saveIssueBook(issueBook) && !issueBookUtility.updateBookAvaliable(book)) {
                    Alert alertIssue = new Alert(Alert.AlertType.INFORMATION);
                    alertIssue.setTitle("Success!!!");
                    alertIssue.setHeaderText(null);
                    alertIssue.setContentText("Book Issue Complete");
                    alertIssue.showAndWait();
                } else {
                    Alert alertIssue = new Alert(Alert.AlertType.ERROR);
                    alertIssue.setTitle("Fail!!!");
                    alertIssue.setHeaderText(null);
                    alertIssue.setContentText("Issue Operation Fail!");
                    alertIssue.showAndWait();
                }
            }

        }else {
            Alert alertCancel= new Alert(Alert.AlertType.INFORMATION);
            alertCancel.setTitle("Cancel");
            alertCancel.setHeaderText(null);
            alertCancel.setContentText("Issue Operation Canceled");
            alertCancel.showAndWait();
        }
    }

    @FXML
    void processIssueHistory(MouseEvent event) throws IOException {
        makeFade();
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        lblLoginEmail.setText(UserHolder.getUserHolder().getEmail());
    }

    private void makeFade(){
        FadeTransition fadeTransition = new FadeTransition();
        fadeTransition.setDuration(Duration.millis(1000));
        fadeTransition.setNode(rootPane);
        fadeTransition.setFromValue(1);
        fadeTransition.setToValue(0);
        fadeTransition.setOnFinished((ActionEvent event)->{
            try {
                issueHistory();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        fadeTransition.play();
    }

    public void issueHistory() throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("issueTable/issueTableUI.fxml"));
        Scene scene = new Scene(root);
        Stage stage = (Stage) rootPane.getScene().getWindow();
        stage.setTitle("ISSUE HISTORY");
        stage.setScene(scene);
    }

}
