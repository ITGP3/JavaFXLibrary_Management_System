/**
 * 
 * @author ThiriNandarNyan
 *
 */
package role.staff;

import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;
import java.util.ResourceBundle;

import DBConnection.DBConnection;
import entity.Book;
import entity.IssueBook;
import entity.UserHolder;
import javafx.event.ActionEvent;
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
import javafx.stage.Stage;
import utility.IssueBookUtility;
import utility.MyAlert;

public class StaffIssueController implements Initializable {

    @FXML
    private Label lblLoginEmail;

    @FXML
    private TextField tfIssueBookId;

    @FXML
    private TextField tfIssueMemberId;

    @FXML
    private Label lblBookName;

    @FXML
    private Label lblBookAuthor;

    @FXML
    private Label lblBookShelf;

    @FXML
    private Label lblBookStatus;

    @FXML
    private Label lblMemberName;

    @FXML
    private Label lblMemberEmail;

    @FXML
    private Label lblMemberPhone;

    @FXML
    private Label lblMemberAddress;

    @FXML
    private Label lblNoBook;

    @FXML
    private Label lblNoMember;
    
    @FXML
    private Label lblStatus;
    
    private Boolean isSearchOk = false;
    
    private final DBConnection dbConnection = new DBConnection();
    
    private final IssueBookUtility issueBookUtility = new IssueBookUtility();
    
    private MyAlert alert = new MyAlert();

    @FXML
    void processIssue(ActionEvent event) throws SQLException {

    	String bookId = tfIssueBookId.getText().trim();
        String memberId = tfIssueMemberId.getText().trim();
        
        Optional<ButtonType> issueButton = alert.getConfirmAlert("Comfirm Issue Operation", null, "Are you sure want to issue the book"+bookId+"\n to"+memberId+"?");
        if (issueButton.get()== ButtonType.OK) {
        	
            IssueBook issueBook = new IssueBook(bookId, memberId);

            Book book = new Book(bookId);
            //String updateIssueQuery = "update library.book set bookAvaliable=false where bookId='"+bookId+"';";
            //System.out.println(updateIssueQuery);

            if (!issueBookUtility.saveIssueBook(issueBook) && !issueBookUtility.updateBookAvaliable(book)) {
            	
                Alert alertIssue = new Alert(Alert.AlertType.INFORMATION);
                alertIssue.setTitle("Success!!!");
                alertIssue.setHeaderText(null);
                alertIssue.setContentText("Book Issue Complete");
                alertIssue.showAndWait();
            }else {
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
            
            tfIssueBookId.setText("");
            tfIssueMemberId.setText("");
            lblBookName.setText("");
            lblBookAuthor.setText("");
            lblBookShelf.setText("");
            lblBookStatus.setText("");
            lblMemberName.setText("");
            lblMemberEmail.setText("");
            lblMemberPhone.setText("");
            lblMemberAddress.setText("");
        }
    }

    @FXML
    void processIssueBookId(ActionEvent event) throws SQLException {

    	String bookId = tfIssueBookId.getText().trim();
    	String query = "select * from book where bookId = '"+ bookId +"'";
    	ResultSet resultSet = dbConnection.executeQuery(query);
    	
    	if(resultSet.next()) {
    		 lblNoBook.setText("");
    		 lblBookName.setText(resultSet.getString("bookTitle"));
             lblBookAuthor.setText(resultSet.getString("bookAuthor"));
             lblBookShelf.setText(resultSet.getString("bookShelf"));
             String status = (resultSet.getBoolean("bookAvaliable"))?"Avaliable":"Not Avaliable";
             lblBookStatus.setText(String.valueOf(status));
             lblStatus.setText(resultSet.getString("bookStatus"));

             isSearchOk = true;
    	}
    	
    	 if (!isSearchOk){
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

    	String memberId =  tfIssueMemberId.getText().trim();
    	String query = "select * from member where memberId = '"+ memberId +"'";
    	ResultSet resultSet = dbConnection.executeQuery(query);
    	
    	if(resultSet.next()) {
    		 lblNoMember.setText("");
    		 lblMemberName.setText(resultSet.getString("memberName"));
             lblMemberEmail.setText(resultSet.getString("memberEmail"));
             lblMemberPhone.setText(resultSet.getString("memberPhone"));
             lblMemberAddress.setText(resultSet.getString("memberAddress"));
             
             isSearchOk = true;
    	}
    	
    	 if (!isSearchOk){
             lblNoMember.setText("No Such Member is Register!!!");
             lblMemberName.setText("");
             lblMemberEmail.setText("");
             lblMemberPhone.setText("");
             lblMemberAddress.setText("");
         }
    }
    
    @FXML
    void processIssueHistory(MouseEvent event) throws IOException {

    	Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("StaffIssueTable.fxml"));
        stage.setTitle("ISSUE HISTORY");
        stage.setScene(new Scene(root));
        stage.show();
    }

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		lblLoginEmail.setText(UserHolder.getUserHolder().getEmail());
	}

}
