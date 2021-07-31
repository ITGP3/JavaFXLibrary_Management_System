package main;

import DBConnection.CheckUserCredential;
import entity.UserHolder;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.paint.Paint;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

enum Role{
    Admin,Staff
}

public class MainController implements Initializable {

    @FXML
    private TextField tfEmail;

    @FXML
    private PasswordField tfPassword;

    @FXML
    private ComboBox<Role> cob;

    @FXML
    private Label lblFailStatus;

    private final CheckUserCredential checkUserCredential = new CheckUserCredential();

    @FXML
    void processLogin(ActionEvent event) throws SQLException, IOException {
        String email = tfEmail.getText();
        String password = tfPassword.getText();
        String role = cob.getSelectionModel().getSelectedItem().toString().toLowerCase();
        String loginType = "Email";

        if (checkUserCredential.isUserValid(email,password,role,loginType)){

            UserHolder userHolder = UserHolder.getUserHolder();
            userHolder.setEmail(email);

            if (role.equals("admin")){
                Stage adminStage = (Stage) ((Button) event.getSource()).getScene().getWindow();
                adminStage.hide();
                Parent adminRoot = FXMLLoader.load(getClass().getResource("../role/admin/adminMainUI.fxml"));
                adminStage.setTitle("Admin Main");
                adminStage.setScene(new Scene(adminRoot));
                adminStage.show();
            }
            if (role.equals("staff")){
                Stage memberStage = (Stage) ((Button) event.getSource()).getScene().getWindow();
                memberStage.hide();
                Parent memberRoot = FXMLLoader.load(getClass().getResource("../role/staff/StaffMainUI.fxml"));
                memberStage.setTitle("Staff Main");
                memberStage.setScene(new Scene(memberRoot));
                memberStage.show();
            }
        }
        else {
            lblFailStatus.setText("Invalid Email or Password or Role");
            lblFailStatus.setTextFill(Paint.valueOf("Red"));
            lblFailStatus.setVisible(true);
        }

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        ObservableList<Role> roles = FXCollections.observableArrayList();
        roles.addAll(Role.Admin,Role.Staff);
        cob.setItems(roles);

        tfEmail.setText("admin@gmail.com");
        tfPassword.setText("admin");
        cob.setValue(Role.valueOf("Admin"));

        /*
        tfEmail.setText("staff@gmail.com");
        tfPassword.setText("staff");
        cob.setValue(Role.valueOf("Staff"));
         */
    }
}
