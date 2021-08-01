package member.addMember;

import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Optional;
import java.util.ResourceBundle;
import entity.Member;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
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
    
    @FXML
    private DatePicker dpDOB;
    
    private final MemberUtility memberUtility = new MemberUtility();
    private MyAlert alert = new MyAlert();
   
    
    @FXML
    void processClear(MouseEvent event) {
    	Optional<ButtonType> result = alert.getConfirmAlert("Confirmation Dialog", "Are u sure u want to clear all field?", "This action will clear all data from all fields.");
		
		if(result.get() == ButtonType.OK) {
			clearAllField();
		}
    }

	public void clearAllField() {
		tfId.clear();
		tfMemberName.clear();
		tfMemberEmail.clear();
		tfMemberPhone.clear();
		tfMemberAddress.clear();
		cobMemberFee.setValue("Fee");
		dpDOB.setValue(LocalDate.now());
		
	}

    @FXML
    void processAdd(MouseEvent event) throws SQLException {
        dpDOB.setValue(LocalDate.now());
    	String memberId = tfId.getText().trim();
    	String memberName = tfMemberName.getText().trim();
    	String memberEmail = tfMemberEmail.getText().trim();
    	String memberPhone = tfMemberPhone.getText().trim();
    	String memberAddress = tfMemberAddress.getText().trim();
    	String memberFee = cobMemberFee.getValue();
    	String memberDOB = dpDOB.getValue().toString();
    	
        if(memberId.isEmpty()||memberName.isEmpty()||memberEmail.isEmpty()||memberPhone.isEmpty()||memberAddress.isEmpty()||memberFee.isEmpty()||memberDOB.isEmpty()){
            Alert alertIssue = new Alert(Alert.AlertType.ERROR
            );
            alertIssue.setTitle("Fail!!!");
            alertIssue.setHeaderText(null);
            alertIssue.setContentText("Please fill all fields");
            alertIssue.showAndWait();
        }
        else {

            Member member = new Member(memberId, memberName, memberEmail, memberPhone, memberAddress, memberFee, memberDOB);

            Optional<ButtonType> result = alert.getConfirmAlert("Information Dialog", "Successfully Saved!", "Saved Member to DB");
            if(result.get() == ButtonType.OK)
            {
                Boolean isSaveOk = memberUtility.saveMember(member);
                if(!isSaveOk) {
                    Alert alertIssue = new Alert(Alert.AlertType.INFORMATION);
                    alertIssue.setTitle("Success!!!");
                    alertIssue.setHeaderText(null);
                    alertIssue.setContentText("Member Saved");
                    alertIssue.showAndWait();

                    clearAllField();
                }
            } else {
                Alert alertIssue = new Alert(Alert.AlertType.INFORMATION);
                alertIssue.setTitle("Fail!!!");
                alertIssue.setHeaderText(null);
                alertIssue.setContentText("Member Save Fail");
                alertIssue.showAndWait();
            }
        }
    }
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
		ObservableList<String> fee = FXCollections.observableArrayList("5000","10000","15000","20000","25000","30000");
		cobMemberFee.setItems(fee);
	}

}
