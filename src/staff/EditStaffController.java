package staff;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ResourceBundle;
import entity.Staff;
import entity.StaffHolder;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import utility.MyAlert;
import utility.StaffDataUtils;

/**
 * 
 * @author Thin Yanant Zaw
 *
 */

public class EditStaffController implements Initializable {

    @FXML
    private TextField tfFirstName;

    @FXML
    private TextField tfLastName;

    @FXML
    private TextField tfEmail;

    @FXML
    private TextField tfPhone;

    @FXML
    private TextField tfAddress;

    @FXML
    private DatePicker dpDOB;

    @FXML
    private ComboBox<String> cobStatus;

    @FXML
    private PasswordField pfPassword;
    
    private MyAlert alert = new MyAlert();
    
    private final StaffDataUtils staffDataUtils = new StaffDataUtils();
    
    private Integer staffUpdateId;
    
    @FXML
    void processBack(MouseEvent event) throws IOException {
    	Stage adminStage = (Stage) ((Button) event.getSource()).getScene().getWindow();
        adminStage.hide();
        Parent root = FXMLLoader.load(getClass().getResource("staffMainUI.fxml"));
        adminStage.setScene(new Scene(root));
        adminStage.show();
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
		
		Staff staffUpdated = new Staff(this.staffUpdateId, staffFirstName, staffLastName, staffEmail, staffPassword, staffPhone, staffStatus, staffDOB, staffAddress);
		
		Boolean isUpdateOk = staffDataUtils.updateStaff(staffUpdated);
		
		if(!isUpdateOk) {
			alert.getConfirmAlert("Information Dialog", "Successfully Updated!", "Update Staff to DB");
			
		}
    }

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		ObservableList<String> status = FXCollections.observableArrayList("Active", "Inactive");

		cobStatus.setItems(status);
	
		StaffHolder staffHolder = StaffHolder.getStaffInstance();
		Staff staff = staffHolder.getStaff();
		
		tfFirstName.setText(staff.getStaffFirstName());
		tfLastName.setText(staff.getStaffLastName());
		tfEmail.setText(staff.getStaffEmail());
		tfPhone.setText(staff.getStaffPhone());
		pfPassword.setText(staff.getStaffPassword());
		tfAddress.setText(staff.getStaffAddress());
		cobStatus.setValue(staff.getStaffStatus());
		
		dpDOB.setValue(LocalDate.parse(staff.getStaffDOB()));
		
		this.staffUpdateId = staff.getStaffId();
	}

}
