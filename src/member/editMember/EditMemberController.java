package member.editMember;

import java.net.URL;

import java.sql.SQLException;
import java.util.ResourceBundle;

import entity.Member;
import entity.MemberHolder;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
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
    
    private final MemberUtility memberUtility = new MemberUtility();
    private MyAlert alert = new MyAlert();
    

    @FXML
    void processSave(MouseEvent event) throws SQLException {
    	
    	String memberId = tfMemberId.getText().trim();
    	String memberName = tfMemberName.getText().trim();
    	String memberEmail = tfMemberEmail.getText().trim();
    	String memberPhone = tfMemberPhone.getText().trim();
    	String memberAddress = tfAddress.getText().trim();
    	String memberFee = cobMemberFee.getValue();
    	
    	Member member = new Member(memberId, memberName, memberEmail, memberPhone, memberAddress, memberFee);
    	Boolean isUpdateOk = memberUtility.updateMember(member);
    	if(!isUpdateOk) {
    		alert.getConfirmAlert("Information Dialog", "Successfully Update!", "Update Member to DB");
			
    	}

    }

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		ObservableList<String> fee = FXCollections.observableArrayList("5000","10000","15000","20000","25000","3000");
		cobMemberFee.setItems(fee);
		
		MemberHolder memberholder = MemberHolder.getMemberInstance();
		Member member = memberholder.getMember();
		tfMemberId.setText(member.getMemberId());
		tfMemberName.setText(member.getMemberName());
		tfMemberEmail.setText(member.getMemberEmail());
		tfMemberPhone.setText(member.getMemberPhone());
		tfAddress.setText(member.getMemberAddress());
		
	}

}
