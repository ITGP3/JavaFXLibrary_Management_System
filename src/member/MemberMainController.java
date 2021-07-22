package member;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.Optional;
import java.util.ResourceBundle;

import entity.Member;
import entity.MemberHolder;
import entity.UserHolder;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import utility.MemberUtility;
import utility.MyAlert;

public class MemberMainController implements Initializable{

    @FXML
    private TableView<Member> memberTable;

    @FXML
    private TableColumn<Member, String> memberId;

    @FXML
    private TableColumn<Member, String> memberName;

    @FXML
    private TableColumn<Member, String> memberEmail;

    @FXML
    private TableColumn<Member, String> memberPhone;

    @FXML
    private TableColumn<Member, String> memberAddress;

    @FXML
    private TableColumn<Member, String> memberFee;

    @FXML
    private Label lblEmail;

    @FXML
    private ComboBox<String> cobMemberColumn;

    @FXML
    private TextField tfSearch;
    
private final MemberUtility memberUtility = new MemberUtility();
private MyAlert alert = new MyAlert();
    
    public void showTable(String sql) {
    	try {
			memberTable.setItems(memberUtility.getAllMembers(sql));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }

    @FXML
    void processAdd(MouseEvent event) throws IOException {
    	Stage primaryStage = new Stage();
    	Parent root = FXMLLoader.load(getClass().getResource("addMember/AddMemberUI.fxml"));
        primaryStage.setTitle("Add Member Section");
        primaryStage.setScene(new Scene(root));
        primaryStage.show();

    }

    @FXML
    void processDelete(MouseEvent event) throws SQLException {
    	Member member = memberTable.getSelectionModel().getSelectedItem();
    	
    	if (member == null){
            Alert alertIssue = new Alert(Alert.AlertType.ERROR);
            alertIssue.setTitle("Warning!");
            alertIssue.setHeaderText(null);
            alertIssue.setContentText("Select Member to Delete");
            alertIssue.showAndWait();
            return;
        }
    	if(memberUtility.memberAlreadyIssue(member)){
            Alert alertIssue = new Alert(Alert.AlertType.ERROR);
            alertIssue.setTitle("Warning!!!");
            alertIssue.setHeaderText(null);
            alertIssue.setContentText("Issue Member Can not delete");
            alertIssue.showAndWait();
            return;

        }
    	 Optional<ButtonType> result = alert.getConfirmAlert("Confirmation Dialog", "Are u sure u want to delete?",  "This action will delete.");

    	 if(result.get() == ButtonType.OK) {
    	Boolean isDeleteOk = memberUtility.deleMember(member.getMemberId());
    	
    	if(!isDeleteOk) {

    		System.out.println("Delete Ok");
    		showTable("select * from member;");
    	}
    	 }
    }

    @FXML
    void processEdit(MouseEvent event) throws IOException {
    	Member member = memberTable.getSelectionModel().getSelectedItem();
    	
    	MemberHolder memberHolder = MemberHolder.getMemberInstance();
    	memberHolder.setMember(member);
    	Stage primaryStage = new Stage();
    	Parent root = FXMLLoader.load(getClass().getResource("editMember/EditMemberUI.fxml"));
        primaryStage.setTitle("Edit Member Section");
        primaryStage.setScene(new Scene(root));
        primaryStage.show();

    }

    @FXML
    void processRefresh(MouseEvent event) {
    	showTable("select * from member;");
    }

    @FXML
    void processSearch(MouseEvent event) {
    	String column = cobMemberColumn.getValue();
    	String search = tfSearch.getText();
    	showTable("select * from member where "+column+" = '"+search+"';");

    }

    @FXML
    void processView(MouseEvent event) throws IOException {
    	Member member = memberTable.getSelectionModel().getSelectedItem();
    	
    	MemberHolder memberHolder = MemberHolder.getMemberInstance();
    	memberHolder.setMember(member);
    	
    	Stage primaryStage = new Stage();
    	Parent root = FXMLLoader.load(getClass().getResource("viewMember/ViewMember.fxml"));
        primaryStage.setTitle("View Member Section");
        primaryStage.setScene(new Scene(root));
        primaryStage.show();

    }

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		lblEmail.setText(UserHolder.getUserHolder().getEmail());
		
		try {
			cobMemberColumn.setItems(memberUtility.getAllColumn());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		memberId.setCellValueFactory(new PropertyValueFactory<>("memberId"));
		memberName.setCellValueFactory(new PropertyValueFactory<>("memberName"));
		memberEmail.setCellValueFactory(new PropertyValueFactory<>("memberEmail"));
		memberPhone.setCellValueFactory(new PropertyValueFactory<>("memberPhone"));
		memberAddress.setCellValueFactory(new PropertyValueFactory<>("memberAddress"));
		memberFee.setCellValueFactory(new PropertyValueFactory<>("memberFee"));
		
		showTable("select * from member;");
		
	}

}
