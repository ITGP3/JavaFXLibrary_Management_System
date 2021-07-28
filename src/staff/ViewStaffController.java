package staff;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import entity.Staff;
import entity.StaffHolder;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class ViewStaffController implements Initializable {

    @FXML
    private Label lblId;

    @FXML
    private Label lblFirstName;

    @FXML
    private Label lblLastName;

    @FXML
    private Label lblEmail;

    @FXML
    private Label lblPassword;

    @FXML
    private Label lblPhone;

    @FXML
    private Label lblAddress;

    @FXML
    private Label lblStatus;

    @FXML
    private Label lblDOB;
    
    @FXML
    void processBack(MouseEvent event) throws IOException {
    	
    	Stage adminStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        adminStage.hide();
        
    }

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		StaffHolder staffHolder = StaffHolder.getStaffInstance();
		
		Staff staff = staffHolder.getStaff();
		
		lblId.setText(staff.getStaffId().toString());
		lblFirstName.setText(staff.getStaffFirstName());
		lblLastName.setText(staff.getStaffLastName());
		lblEmail.setText(staff.getStaffEmail());
		lblPassword.setText(staff.getStaffPassword());
		lblPhone.setText(staff.getStaffPhone());
		lblAddress.setText(staff.getStaffAddress());
		lblStatus.setText(staff.getStaffStatus());
		lblDOB.setText(staff.getStaffDOB());
	}

}
