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

/**
 *
 * @author Sit Min Htet
 *
 */

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
    void processBooks(MouseEvent event) throws IOException {
        Stage stage = new Stage(StageStyle.DECORATED);
        Parent root = FXMLLoader.load(getClass().getResource("../../book/bookMainUI.fxml"));
        stage.setTitle("BOOKS");
        stage.setScene(new Scene(root));
        stage.show();
    }

    @FXML
    void processIssueBook(MouseEvent event) throws IOException {
        Stage stage = new Stage(StageStyle.DECORATED);
        Parent root = FXMLLoader.load(getClass().getResource("../../issueBook/issueUI.fxml"));
        stage.setTitle("ISSUE BOOKS");
        stage.setScene(new Scene(root));
        stage.show();

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
    void processMembers(MouseEvent event) throws IOException {
        Stage stage = new Stage(StageStyle.DECORATED);
        Parent root = FXMLLoader.load(getClass().getResource("../../member/memberMainUI.fxml"));
        stage.setTitle("MEMBERS");
        stage.setScene(new Scene(root));
        stage.show();

    }

    @FXML
    void processRenewBook(MouseEvent event) throws IOException {
        Stage stage = new Stage(StageStyle.DECORATED);
        Parent root = FXMLLoader.load(getClass().getResource("../../renewBook/renewBookUI.fxml"));
        stage.setTitle("RENEW");
        stage.setScene(new Scene(root));
        stage.show();
    }

    @FXML
    void processStaff(MouseEvent event) throws IOException {
        Stage stage = new Stage(StageStyle.DECORATED);
        Parent root = FXMLLoader.load(getClass().getResource("../../staff/staffMainUI.fxml"));
        stage.setTitle("STAFF");
        stage.setScene(new Scene(root));
        stage.show();

    }

    @FXML
    void processSubmissionBook(MouseEvent event) throws IOException {
        Stage stage = new Stage(StageStyle.DECORATED);
        Parent root = FXMLLoader.load(getClass().getResource("../../submissionBook/submissionBookUI.fxml"));
        stage.setTitle("SUBMISSION");
        stage.setScene(new Scene(root));
        stage.show();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        lblLoginManager.setText(UserHolder.getUserHolder().getEmail());
    }
}
