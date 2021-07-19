package issueBook;

import DBConnection.DBConnection;
import entity.IssueBook;
import entity.UserHolder;
import javafx.beans.property.SimpleStringProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import utility.IssueBookUtility;

import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;
import java.util.ResourceBundle;

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

    private final IssueBookUtility issueBookUtility = new IssueBookUtility();
    private final DBConnection dbConnection = new DBConnection();

    @FXML
    void processIssueBookId(ActionEvent event) throws SQLException {
        boolean isSearchOK = false;
        String id = tfIssueBookId.getText().trim();

        String searchQuery = "select * from library.book where bookId= '"+ id +"'";
        ResultSet resultSet = dbConnection.executeQuery(searchQuery);

        if (resultSet.next()){
            lblBookName.setText(resultSet.getString("bookTitle"));
            lblBookAuthor.setText(resultSet.getString("bookAuthor"));
            lblBookShelf.setText(resultSet.getString("bookShelf"));
            String status = (resultSet.getBoolean("bookAvaliable"))?"Avaliable":"Not Avaliable";
            lblBookStatus.setText(String.valueOf(status));

            isSearchOK = true;
        }

        if (!isSearchOK){
            lblBookName.setText("No Such Book Avaliable!!");
            lblBookAuthor.setText("");
            lblBookShelf.setText("");
            lblBookStatus.setText("");
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

            lblMemberName.setText(name);
            lblMemberEmail.setText(email);
            lblMemberPhone.setText(phone);

            isSearchOK = true;
        }

        if (!isSearchOK){
            lblMemberName.setText("No Such Member is not Register!!!");
            lblMemberEmail.setText("");
            lblMemberPhone.setText("");
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

            String updateIssueQuery = "update library.book set bookAvaliable=false where bookId='"+bookId+"';";
            System.out.println(updateIssueQuery);

            if (!issueBookUtility.saveIssueBook(issueBook)) {
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

        }else {
            Alert alertCancel= new Alert(Alert.AlertType.INFORMATION);
            alertCancel.setTitle("Cancel");
            alertCancel.setHeaderText(null);
            alertCancel.setContentText("Issue Operation Canceled");
            alertCancel.showAndWait();
        }
    }

    @FXML
    void processIssueHistory(MouseEvent event) {

    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        lblLoginEmail.setText(UserHolder.getUserHolder().getEmail());
    }
}
