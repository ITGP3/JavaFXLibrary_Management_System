package member.addMember;

import java.sql.SQLException;

import entity.Member;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import utility.MemberUtility;
/**
 * 
 * @author Win Pa Pa Aung
 *
 */

public class AddMemberController {

    @FXML
    private TextField tfId;

    @FXML
    private TextField tfMemberName;

    @FXML
    private TextField tfMemberEmail;

    @FXML
    private TextField tfMemberPhone;

    @FXML
    private TextField tfMemberAddress;

    @FXML
    private TextField tfMemberFee;
    
    private final MemberUtility memberUtility = new MemberUtility();

    @FXML
    void processAdd(ActionEvent event) throws SQLException {
    	
    	String memberId = tfId.getText().trim();
    	String memberName = tfMemberName.getText().trim();
    	String memberEmail = tfMemberEmail.getText().trim();
    	String memberPhone = tfMemberPhone.getText().trim();
    	String memberAddress = tfMemberAddress.getText().trim();
    	String memberFee = tfMemberFee.getText().trim();
    	
    	Member member = new Member(memberId, memberName, memberEmail, memberPhone, memberAddress, memberFee);
    	Boolean isSaveOk = memberUtility.saveMember(member);
    	if(!isSaveOk) {
    		System.out.println("Save OK");
    	}
    	
    }

}
