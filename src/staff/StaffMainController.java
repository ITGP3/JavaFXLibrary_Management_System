package staff;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.Optional;
import java.util.ResourceBundle;

import entity.StaffHolder;
import entity.UserHolder;
import entity.Staff;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import utility.MyAlert;
import utility.StaffDataUtils;

public class StaffMainController implements Initializable{

    @FXML
    private TableView<Staff> staffTable;

    @FXML
    private TableColumn<Staff, Integer> staffId;

    @FXML
    private TableColumn<Staff, String> staffFirstName;

    @FXML
    private TableColumn<Staff, String> staffLastName;

    @FXML
    private TableColumn<Staff, String> staffEmail;

    @FXML
    private TableColumn<Staff, String> staffPassword;

    @FXML
    private TableColumn<Staff, String> staffPhone;

    @FXML
    private TableColumn<Staff, String> staffStatus;

    @FXML
    private TableColumn<Staff, String> staffDOB;

    @FXML
    private TableColumn<Staff, String> staffAddress;

    @FXML
    private TextField tfSearch;

    @FXML
    private ComboBox<String> cobColumn;

    @FXML
    private Label lblLoginEmail;
    
    private final StaffDataUtils staffDataUtils = new StaffDataUtils();

    private MyAlert alert = new MyAlert();
    
	@FXML
	void processAdd(MouseEvent event) throws IOException {
		Stage primaryStage = new Stage();
		Parent root = FXMLLoader.load(getClass().getResource("AddStaffUI.fxml"));
		primaryStage.setTitle("Add Staff Section");
		primaryStage.setScene(new Scene(root));
		primaryStage.show();
	}

	@FXML
	void processDelete(MouseEvent event) throws SQLException {
		Staff staff = staffTable.getSelectionModel().getSelectedItem();

		if (staff == null){
            Alert alertIssue = new Alert(Alert.AlertType.ERROR);
            alertIssue.setTitle("Warning!");
            alertIssue.setHeaderText(null);
            alertIssue.setContentText("Select Staff to Delete");
            alertIssue.showAndWait();
            return;
        }
		
		
		Optional<ButtonType> result = alert.getConfirmAlert("Confirmation Dialog", "Are you sure to delete?",
				"This action cannot be undone");

		if (result.get() == ButtonType.OK) {
			
			Boolean isDeleteOk = staffDataUtils.deleteStaff(staff.getStaffId());
			
			if (!isDeleteOk) {
				Alert alertIssue = new Alert(Alert.AlertType.INFORMATION);
                alertIssue.setTitle("Success!!!");
                alertIssue.setHeaderText(null);
                alertIssue.setContentText("Staff Deleted!");
                alertIssue.showAndWait();
                
				showTable("select * from staff;");
			}
		} else {
			Alert alertIssue = new Alert(Alert.AlertType.INFORMATION);
            alertIssue.setTitle("Fail!!!");
            alertIssue.setHeaderText(null);
            alertIssue.setContentText("Staff Delete Fail!");
            alertIssue.showAndWait();

		}
	}

    @FXML
    void processEdit(MouseEvent event) throws IOException {

		Staff staff = staffTable.getSelectionModel().getSelectedItem();
		
		if (staff == null){
            Alert alertIssue = new Alert(Alert.AlertType.ERROR);
            alertIssue.setTitle("Warning!");
            alertIssue.setHeaderText(null);
            alertIssue.setContentText("Select Staff to Update");
            alertIssue.showAndWait();
            return;
        }
		
		StaffHolder staffHolder = StaffHolder.getStaffInstance();
		staffHolder.setStaff(staff);
        
		Stage primaryStage = (Stage)((Node)event.getSource()).getScene().getWindow();
        primaryStage.hide();
        
		Parent root = FXMLLoader.load(getClass().getResource("EditStaffUI.fxml"));
		primaryStage.setTitle("Edit Staff Section");
		primaryStage.setScene(new Scene(root));
		primaryStage.show();
    }

    @FXML
    void processRefresh(MouseEvent event) {
    	showTable("select * from staff;");
    }

    @FXML
    void processSearch(MouseEvent event) {
    	String column = cobColumn.getValue();
    	
    	String query = tfSearch.getText().trim();
    	
    	showTable("select * from staff where " + column + " = '" + query + "';");
    	
    	cobColumn.setValue("Staff Column");
    	tfSearch.clear();
    }

    @FXML
    void processView(MouseEvent event) throws IOException {
    	Staff staff = staffTable.getSelectionModel().getSelectedItem();
    	
    	if (staff == null){
            Alert alertIssue = new Alert(Alert.AlertType.ERROR);
            alertIssue.setTitle("Warning!");
            alertIssue.setHeaderText(null);
            alertIssue.setContentText("Select Staff to View");
            alertIssue.showAndWait();
            return;
        }
    	
		StaffHolder staffHolder = StaffHolder.getStaffInstance();
		
		staffHolder.setStaff(staff);
		
		Stage primaryStage = new Stage();
		AnchorPane root = (AnchorPane) FXMLLoader.load(getClass().getResource("ViewStaffUI.fxml"));
		Scene scene = new Scene(root);
		primaryStage.setTitle("View Staff Section");
		primaryStage.setScene(scene);
		primaryStage.show();
		
    }

    public void showTable(String sql) {
		try {
			staffTable.setItems(staffDataUtils.getAllStaffs(sql));

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
    
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
		lblLoginEmail.setText(UserHolder.getUserHolder().getEmail());
		
		staffId.setCellValueFactory(new PropertyValueFactory<>("staffId"));
		staffFirstName.setCellValueFactory(new PropertyValueFactory<>("staffFirstName"));
		staffLastName.setCellValueFactory(new PropertyValueFactory<>("staffLastName"));
		staffEmail.setCellValueFactory(new PropertyValueFactory<>("staffEmail"));
		staffPassword.setCellValueFactory(new PropertyValueFactory<>("staffPassword"));
		staffPhone.setCellValueFactory(new PropertyValueFactory<>("staffPhone"));
		staffAddress.setCellValueFactory(new PropertyValueFactory<>("staffAddress"));
		staffStatus.setCellValueFactory(new PropertyValueFactory<>("staffStatus"));
		staffDOB.setCellValueFactory(new PropertyValueFactory<>("staffDOB"));

		try {
			cobColumn.setItems(staffDataUtils.getAllColumnLabel());
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		showTable("select * from staff;");
	}

}
