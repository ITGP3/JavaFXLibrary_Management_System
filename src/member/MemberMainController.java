package member;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

import entity.Member;
import entity.MemberHolder;
import entity.UserHolder;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import utility.MemberUtility;
/**
 * 
 * @author Win Pa Pa Aung
 *
 */

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
    private TextField tfSearchMember;

    @FXML
    private ComboBox<String> cobMemberCoulmn;

    @FXML
    private Label lblLoginEmail;
    
    private final MemberUtility memberUtility = new MemberUtility();
    
    public void showTable(String sql) {
    	try {
			memberTable.setItems(memberUtility.getAllMembers(sql));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }

    @FXML
    void processAdd(ActionEvent event) throws IOException {
    	Stage adminStage = (Stage) ((Button) event.getSource()).getScene().getWindow();
        adminStage.hide();
        Parent adminRoot = FXMLLoader.load(getClass().getResource("addMember/AddMemberUI.fxml"));
        adminStage.setTitle("Member Add Session");
        adminStage.setScene(new Scene(adminRoot));
        adminStage.show();
    }

    @FXML
    void processDelete(ActionEvent event) throws SQLException {
    	
    	Member member = memberTable.getSelectionModel().getSelectedItem();
    	
    	Boolean isDeleteOk = memberUtility.deleMember(member.getMemberId());
    	
    	if(!isDeleteOk) {

    		System.out.println("Delete Ok");
    	}

    }

    @FXML
    void processEdit(ActionEvent event) {

    }

    @FXML
    void processRefresh(ActionEvent event) {
    	showTable("select * from member;");
    }

    @FXML
    void processSearch(ActionEvent event) {
    	
    	String column = cobMemberCoulmn.getValue();
    	String search = tfSearchMember.getText().trim();
    	showTable("select * from member where "+column+" = '"+search+"';");

    }

    @FXML
    void processView(ActionEvent event) throws IOException {
    	
    	
    	
    	Member member = memberTable.getSelectionModel().getSelectedItem();
    	
    	MemberHolder memberHolder = MemberHolder.getMemberInstance();
    	memberHolder.setMember(member);
    	
    	Stage adminStage = (Stage) ((Button) event.getSource()).getScene().getWindow();
        adminStage.hide();
        Parent adminRoot = FXMLLoader.load(getClass().getResource("viewMember/ViewMember.fxml"));
        adminStage.setTitle("Member View Session");
        adminStage.setScene(new Scene(adminRoot));
        adminStage.show();
    	
    	
    	
    }

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		
		lblLoginEmail.setText(UserHolder.getUserHolder().getEmail());
		
		try {
			cobMemberCoulmn.setItems(memberUtility.getAllColumn());
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
