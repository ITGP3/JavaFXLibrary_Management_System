package role.staff;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import entity.UserHolder;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class StaffMainController implements Initializable{

    @FXML
    private Label lblLoginManager;

    private void loadWindow(String location, String title){
        try{
            Stage stage = new Stage(StageStyle.DECORATED);
            Parent root = FXMLLoader.load(getClass().getResource(location));
            stage.setTitle(title);
            stage.setScene(new Scene(root));
            stage.show();
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }
    
    @FXML
    void processMembers(MouseEvent event) throws IOException {

    	Stage primaryStage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("StaffMemberUI.fxml"));
        primaryStage.setTitle("MEMBER SECTION");
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    	
    }
    
    @FXML
    void processBooks(MouseEvent event) throws IOException {
    	
    	 Stage primaryStage = new Stage();
         Parent root = FXMLLoader.load(getClass().getResource("StaffBookUI.fxml"));
         primaryStage.setTitle("BOOK SECTION");
         primaryStage.setScene(new Scene(root));
         primaryStage.show();

    }

    @FXML
    void processIssueBook(MouseEvent event) throws IOException {
    	 Stage primaryStage = new Stage();
         Parent root = FXMLLoader.load(getClass().getResource("StaffIssueUI.fxml"));
         primaryStage.setTitle("ISSUE SECTION");
         primaryStage.setScene(new Scene(root));
         primaryStage.show();
    }

    @FXML
    void processLogout(MouseEvent event) throws IOException {

    	 Stage staffStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
    	 staffStage.hide();
         Parent adminRoot = FXMLLoader.load(getClass().getResource("../../main/main.fxml"));
         staffStage.setTitle("STAFF MAIN");
         staffStage.setScene(new Scene(adminRoot));
         staffStage.show();
    }

    @FXML
    void processRenewBook(MouseEvent event) throws IOException {
    	Stage primaryStage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("RenewBookUI.fxml"));
        primaryStage.setTitle("RENEW BOOK SECTION");
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }

    @FXML
    void processSubmissionBook(MouseEvent event) throws IOException {
    	Stage primaryStage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("StaffSubmissionUI.fxml"));
        primaryStage.setTitle("SUBMISSION BOOK SECTION");
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
		 lblLoginManager.setText(UserHolder.getUserHolder().getEmail());
	}

}
