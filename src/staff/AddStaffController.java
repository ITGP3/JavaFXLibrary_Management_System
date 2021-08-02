package staff;

import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Optional;
import java.util.ResourceBundle;
import entity.Staff;
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
import utility.MyAlert;
import utility.StaffDataUtils;

/**
 * 
 * @author Hnin Yu Hlaing
 *
 */

public class AddStaffController implements Initializable {

    @FXML
    private TextField tfFirstName;

    @FXML
    private TextField tfLastName;

    @FXML
    private TextField tfEmail;

    @FXML
    private TextField pfPassword;

    @FXML
    private TextField tfPhone;

    @FXML
    private TextField tfAddress;

    @FXML
    private DatePicker dpDOB;

    @FXML
    private ComboBox<String> cobStatus;

    private MyAlert alert = new MyAlert();
    
    private final StaffDataUtils staffDataUtils = new StaffDataUtils();
    
    @FXML
    void processClear(MouseEvent event) {
    	Optional<ButtonType> result = alert.getConfirmAlert("Confirmation Dialog", "Are u sure u want to clear all field?", "This action will clear all data from all fields.");
		
		if(result.get() == ButtonType.OK) {
			clearAllField();
		}
    }

	public void clearAllField() {
		tfFirstName.clear();
		tfLastName.clear();
		tfEmail.clear();
		pfPassword.clear();
		tfPhone.clear();
		tfAddress.clear();
		cobStatus.setValue("Status");
		dpDOB.setValue(LocalDate.now());
	}
    
	@FXML
	void processSave(MouseEvent event) throws SQLException {
		
		String staffFirstName = tfFirstName.getText().trim();
		String staffLastName = tfLastName.getText().trim();
		String staffEmail = tfEmail.getText().trim();
		String staffPassword = pfPassword.getText();
		String staffPhone = tfPhone.getText().trim();
		String staffAddress = tfAddress.getText().trim();
		String staffStatus = cobStatus.getValue();

		String staffDOB = dpDOB.getValue().toString();
		
		if (staffFirstName.isEmpty()||staffLastName.isEmpty()||staffEmail.isEmpty()||staffPassword.isEmpty()||staffPhone.isEmpty()||staffAddress.isEmpty()||staffStatus.isEmpty()||dpDOB.getValue().toString().isEmpty()){
			Alert alertIssue = new Alert(Alert.AlertType.ERROR);
			alertIssue.setTitle("Fail!!!");
			alertIssue.setHeaderText(null);
			alertIssue.setContentText("Fill all fields");
			alertIssue.showAndWait();
		}
		else {	
			
			Staff staff = new Staff(staffFirstName, staffLastName, staffEmail, staffPassword, staffPhone, staffStatus, staffDOB, staffAddress);

			if(!staff.getStaffFirstName().matches("^([a-zA-Z]+\\s)*[a-zA-Z]+$")) {
				Alert alertIssue = new Alert(Alert.AlertType.ERROR);
				alertIssue.setTitle("Validate First Name Fail!!!");
				alertIssue.setHeaderText(null);
				alertIssue.setContentText("Please Enter Valid First Name.");
				alertIssue.showAndWait();			
				return;
			}
			
			if(!staff.getStaffLastName().matches("^([a-zA-Z]+\\s)*[a-zA-Z]+$")) {
				Alert alertIssue = new Alert(Alert.AlertType.ERROR);
				alertIssue.setTitle("Validate Last Name Fail!!!");
				alertIssue.setHeaderText(null);
				alertIssue.setContentText("Please Enter Valid Last Name.");
				alertIssue.showAndWait();			
				return;
			}
			
			if(!staff.getStaffEmail().matches("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$")) {
				Alert alertIssue = new Alert(Alert.AlertType.ERROR);
				alertIssue.setTitle("Validate Email Fail!!!");
				alertIssue.setHeaderText(null);
				alertIssue.setContentText("Please Enter Valid Email Address.");
				alertIssue.showAndWait();			
				return;
			}
			
			if(!staff.getStaffPassword().matches("^(?=.*[0-9])(?=.*[a-z])(?=\\S+$).*[A-Za-z0-9]$")) {
				Alert alertIssue = new Alert(Alert.AlertType.ERROR);
				alertIssue.setTitle("Validate Password Fail!!!");
				alertIssue.setHeaderText(null);
				alertIssue.setContentText("Please Enter Valid Password.");
				alertIssue.showAndWait();			
				return;
			}
			
			if(!staff.getStaffPhone().matches("\\d{11}")) {
				Alert alertIssue = new Alert(Alert.AlertType.ERROR);
				alertIssue.setTitle("Validate Phone Fail!!!");
				alertIssue.setHeaderText(null);
				alertIssue.setContentText("Please Enter Valid Phone No.");
				alertIssue.showAndWait();			
				return;
			}
			
			if(!staff.getStaffAddress().matches("^[0-9a-zA-Z\\s,-._]+$")) {
				Alert alertIssue = new Alert(Alert.AlertType.ERROR);
				alertIssue.setTitle("Validate Address Fail!!!");
				alertIssue.setHeaderText(null);
				alertIssue.setContentText("Please Enter Valid Address.");
				alertIssue.showAndWait();			
				return;
			}
			
			Optional<ButtonType> result = alert.getConfirmAlert("Information Dialog", "Successfully Saved!", "Saved Staff to DB");

			if (result.get() == ButtonType.OK) {

				Boolean isSaveOk = staffDataUtils.saveStaff(staff);

				if (!isSaveOk) {
					Alert alertIssue = new Alert(Alert.AlertType.INFORMATION);
					alertIssue.setTitle("Success!!!");
					alertIssue.setHeaderText(null);
					alertIssue.setContentText("Staff Saved");
					alertIssue.showAndWait();

					clearAllField();
				}
			} else {
				Alert alertIssue = new Alert(Alert.AlertType.INFORMATION);
				alertIssue.setTitle("Fail!!!");
				alertIssue.setHeaderText(null);
				alertIssue.setContentText("Staff Save Fail");
				alertIssue.showAndWait();
			}
		}
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		ObservableList<String> status = FXCollections.observableArrayList("Active", "Inactive");

		cobStatus.setItems(status);
		
	}

}
