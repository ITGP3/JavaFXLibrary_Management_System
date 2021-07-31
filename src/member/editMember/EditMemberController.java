package member.editMember;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ResourceBundle;
import entity.Member;
import entity.MemberHolder;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import utility.MemberUtility;
import utility.MyAlert;
/**
 * 
 * @author Thin Yanant Zaw
 *
 */

public class EditMemberController implements Initializable{

	@FXML
    private TextField tfMemberId;

    @FXML
    private TextField tfMemberName;
    
    @FXML
    private TextField tfMemberEmail;

    @FXML
    private TextField tfMemberPhone;

    @FXML
    private TextField tfAddress;

    @FXML
    private ComboBox<String> cobMemberFee;
    
    @FXML
    private DatePicker tfMemberDOB;
    
    private final MemberUtility memberUtility = new MemberUtility();
    private MyAlert alert = new MyAlert();
    
       
    @FXML
    void processBack(MouseEvent event) throws IOException {
    	
    	Stage adminStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        adminStage.hide();
        
    }
    

    @FXML
    void processSave(MouseEvent event) throws SQLException, IOException {
    	
    	String memberId = tfMemberId.getText().trim();
    	String memberName = tfMemberName.getText().trim();
    	String memberEmail = tfMemberEmail.getText().trim();
    	String memberPhone = tfMemberPhone.getText().trim();
    	String memberAddress = tfAddress.getText().trim();
    	String memberFee = cobMemberFee.getValue();
    	String memberDOB = tfMemberDOB.getValue().toString();
    	
    	Member member = new Member(memberId, memberName, memberEmail, memberPhone, memberAddress, memberFee, memberDOB);
    	Boolean isUpdateOk = memberUtility.updateMember(member);
    	
    	if(!isUpdateOk) {
    		
    		alert.getConfirmAlert("Information Dialog", "Successfully Update!", "Update Member to DB");
    		Stage primaryStage = (Stage)((Node)event.getSource()).getScene().getWindow();
			primaryStage.hide();
						
	  	}

    }

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
		ObservableList<String> fee = FXCollections.observableArrayList("5000","10000","15000","20000","25000","3000");
		cobMemberFee.setItems(fee);
		
		MemberHolder memberholder = MemberHolder.getMemberInstance();
		Member member = memberholder.getMember();
		
		tfMemberId.setText(member.getMemberId());
		tfMemberName.setText(member.getMemberName());
		tfMemberEmail.setText(member.getMemberEmail());
		tfMemberPhone.setText(member.getMemberPhone());
		tfAddress.setText(member.getMemberAddress());
		cobMemberFee.setValue(member.getMemberFee());
		tfMemberDOB.setValue(LocalDate.parse(member.getMemberDOB()));
		
	}

}
