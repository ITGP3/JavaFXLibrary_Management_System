package submissionBook;

import DBConnection.DBConnection;
import entity.Book;
import entity.IssueBook;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import utility.SubmissionUtility;

import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Date;
import java.util.ResourceBundle;
import java.util.concurrent.TimeUnit;

/**
 *
 * @author Sit Min Htet
 *
 */

public class SubmissionBookController implements Initializable {

    @FXML
    private TextField bookId;

    @FXML
    private Label lblMemberName;

    @FXML
    private Label lblMemberEmail;

    @FXML
    private Label lblMemberPhone;

    @FXML
    private Label lblBookName;

    @FXML
    private Label lblBookShelf;

    @FXML
    private Label lblBookCategory;

    @FXML
    private Label lblIssueDate;

    @FXML
    private Label lblNoOfDays;

    private DBConnection dbConnection = new DBConnection();
    private final SubmissionUtility submissionUtility = new SubmissionUtility();
    boolean isSubmissionBook = false;

    @FXML
    void processBookId(ActionEvent event) throws SQLException {

        ObservableList<String> issueList = FXCollections.observableArrayList();
        isSubmissionBook = false;
        String id = bookId.getText().trim();
        /*
        String query = "select * from issue where issue.bookId= '"+id+"';";
        ResultSet resultSet = dbConnection.executeQuery(query);

        if (resultSet.next()) {
            lblMemberName.setText(resultSet.getString("issueTime"));
        }
         */


        String query = "select issue.bookId, issue.memberId, issue.issueTime, issue.renewCount, " +
                "member.memberName, member.memberEmail, member.memberPhone, " +
                "book.bookTitle, book.bookShelf, book.bookCategory " +
                "from ((issue "+
                "INNER JOIN member ON issue.memberId = member.memberId) "+
                "INNER JOIN book ON issue.bookId = book.bookId) "+
                "where issue.bookId = '"+id+"' ;";


        /*
        String searchQuery = "select issue.bookId, issue.memberId, issue.issueTime, issue.renewCount, " +
                "member.memberName, member.memberEmail, member.memberPhone, " +
                "book.bookTitle, book.bookShelf, book.bookCategory " +
                "from library.issue " +
                "left join member " +
                "on issue.memberId = member.memberId " +
                "left join book " +
                "on issue.bookId = book.bookId " +
                "where issue.bookId = '"+id+"' ;";
         */
        ResultSet resultSet = dbConnection.executeQuery(query);

        if (resultSet.next()){
            lblMemberName.setText(resultSet.getString("memberName"));
            lblMemberEmail.setText(resultSet.getString("memberEmail"));
            lblMemberPhone.setText(resultSet.getString("memberPhone"));

            lblBookName.setText(resultSet.getString("bookTitle"));
            lblBookShelf.setText(resultSet.getString("bookShelf"));
            lblBookCategory.setText(resultSet.getString("bookCategory"));

            Timestamp timestamp =resultSet.getTimestamp("issueTime");
            Date date = new Date(timestamp.getTime());
            lblIssueDate.setText(date.toString());

            Long time = System.currentTimeMillis() - timestamp.getTime();
            Long day = TimeUnit.DAYS.convert(time,TimeUnit.MILLISECONDS);
            lblNoOfDays.setText(day.toString()+" day(s)");

            isSubmissionBook = true;
        }
        else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Warning!!!");
            alert.setHeaderText(null);
            alert.setContentText("This Book is not issue book");
            alert.showAndWait();
            return;
        }


    }

    @FXML
    void processSubmission(ActionEvent event) throws SQLException {
        if (!isSubmissionBook){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Warning!!!");
            alert.setHeaderText(null);
            alert.setContentText("Enter a book for submission");
            alert.showAndWait();
            return;
        }

        String id = bookId.getText().trim();
        IssueBook issueBook = new IssueBook(id);
        Book book = new Book(id);

        if (!submissionUtility.deleteIssueBook(issueBook) && !submissionUtility.updateIssueBook(book)){
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Success!!!");
            alert.setHeaderText(null);
            alert.setContentText("Submission Successful");
            alert.showAndWait();
            return;
        }
        else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Warning!!!");
            alert.setHeaderText(null);
            alert.setContentText("Submission Fail");
            alert.showAndWait();
            return;
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}
