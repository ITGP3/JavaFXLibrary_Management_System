/**
 * 
 * @author ThinYanantZaw
 *
 */

package role.staff;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

import entity.Member;
import entity.MemberHolder;
import entity.UserHolder;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import utility.MemberUtility;

public class StaffMemberController implements Initializable{

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
    private TableColumn<Member, String> memberDOB;

    @FXML
    private TextField tfSearch;

    @FXML
    private ComboBox<String> cobMemberCol;

    @FXML
    private Label lblLoginMail;
    
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
    void processRefresh(MouseEvent event) {
    	
    	cobMemberCol.getSelectionModel().clearSelection();
        tfSearch.setText("");
        showTable("select * from member;");

    }

    @FXML
    void processSearch(MouseEvent event) {
    	
    	String column = cobMemberCol.getValue();
    	String search = tfSearch.getText();
    	showTable("select * from member where "+column+" = '"+search+"';");

    }

    @FXML
    void processView(MouseEvent event) throws IOException {
    	
    	Member member = memberTable.getSelectionModel().getSelectedItem();
    	
    	MemberHolder memberHolder = MemberHolder.getMemberInstance();
    	memberHolder.setMember(member);
    	
    	Stage primaryStage = new Stage();
    	Parent root = FXMLLoader.load(getClass().getResource("../../member/viewMember/ViewMember.fxml"));
        primaryStage.setTitle("View Member Section");
        primaryStage.setScene(new Scene(root));
        primaryStage.show();

    }

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
		lblLoginMail.setText(UserHolder.getUserHolder().getEmail());
		
		try {
			cobMemberCol.setItems(memberUtility.getAllColumn());
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
		memberDOB.setCellValueFactory(new PropertyValueFactory<>("memberDOB"));
		
		showTable("select * from member;");
		
	}

}
