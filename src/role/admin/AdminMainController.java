package role.admin;

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

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class AdminMainController implements Initializable {

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
    void processBooks(MouseEvent event) {
        loadWindow("../../book/bookMainUI.fxml","BOOKS");
    }

    @FXML
    void processIssueBook(MouseEvent event) {
        loadWindow("../../issueBook/issueUI.fxml","ISSUE BOOK");
    }

    @FXML
    void processLogout(MouseEvent event) throws IOException {
        Stage adminStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        adminStage.hide();
        Parent adminRoot = FXMLLoader.load(getClass().getResource("../../main/main.fxml"));
        adminStage.setTitle("ADMIN MAIN");
        adminStage.setScene(new Scene(adminRoot));
        adminStage.show();
    }

    @FXML
    void processMembers(MouseEvent event) {
        loadWindow("../../member/memberMainUI.fxml","MEMBERS");
    }

    @FXML
    void processRenewBook(MouseEvent event) {

    }

    @FXML
    void processStaff(MouseEvent event) {
        loadWindow("../../staff/staffMainUI.fxml","STAFF");
    }

    @FXML
    void processSubmissionBook(MouseEvent event) {

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        lblLoginManager.setText(UserHolder.getUserHolder().getEmail());
    }
}
