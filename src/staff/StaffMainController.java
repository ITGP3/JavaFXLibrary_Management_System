package staff;

import java.net.URL;
import java.util.ResourceBundle;

import entity.Staff;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

public class StaffMainController implements Initializable {

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
    private ComboBox<?> cobColumn;

    @FXML
    void processAdd(MouseEvent event) {

    }

    @FXML
    void processDelete(MouseEvent event) {

    }

    @FXML
    void processEdit(MouseEvent event) {

    }

    @FXML
    void processRefresh(MouseEvent event) {

    }

    @FXML
    void processSearch(MouseEvent event) {

    }

    @FXML
    void processView(MouseEvent event) {

    }

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
	}

}
