package member.addMember;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

import entity.Member;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import utility.MemberUtility;
/**
 * 
 * @author Win Pa Pa Aung
 *
 */
import utility.MyAlert;

public class AddMemberController implements Initializable{

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
    private ComboBox<String> cobMemberFee;
    
    private final MemberUtility memberUtility = new MemberUtility();
    private MyAlert alert = new MyAlert();
   
    
    @FXML
    void processBack(MouseEvent event) throws IOException {
    	Stage adminStage = (Stage) ((Button) event.getSource()).getScene().getWindow();
        adminStage.hide();
        Parent adminRoot = FXMLLoader.load(getClass().getResource("../memberMainUI.fxml"));
        adminStage.setScene(new Scene(adminRoot));
        adminStage.show();
    }

    @FXML
    void processAdd(MouseEvent event) throws SQLException {
    	
    	String memberId = tfId.getText().trim();
    	String memberName = tfMemberName.getText().trim();
    	String memberEmail = tfMemberEmail.getText().trim();
    	String memberPhone = tfMemberPhone.getText().trim();
    	String memberAddress = tfMemberAddress.getText().trim();
    	String memberFee = cobMemberFee.getValue();
    	
        Member member = new Member(memberId, memberName, memberEmail, memberPhone, memberAddress, memberFee);	
        Boolean isSaveOk = memberUtility.saveMember(member);
    	if(!isSaveOk) {
    		alert.getConfirmAlert("Information Dialog", "Successfully Saved!", "Saved Member to DB");
			
    		System.out.println("Save OK");
    	}
    	
    }

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		ObservableList<String> fee = FXCollections.observableArrayList("5000","10000","15000","20000","25000","30000");
		cobMemberFee.setItems(fee);
	}

}
