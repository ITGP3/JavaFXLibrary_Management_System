package member.viewMember;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import entity.Member;
import entity.MemberHolder;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;
/**
 * 
 * @author Win Pa Pa Aung
 *
 */

public class ViewMemberController implements Initializable {

    @FXML
    private Label lblId;

    @FXML
    private Label lblName;

    @FXML
    private Label lblEmail;

    @FXML
    private Label lblPhone;
    
    @FXML
    private Label lblAddress;

    @FXML
    private Label lblFee;

    @FXML
    void processBack(ActionEvent event) throws IOException {
    	Stage adminStage = (Stage) ((Button) event.getSource()).getScene().getWindow();
        adminStage.hide();
        Parent adminRoot = FXMLLoader.load(getClass().getResource("../memberMainUI.fxml"));
        adminStage.setScene(new Scene(adminRoot));
        adminStage.show();

    }

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		MemberHolder memberHolder = MemberHolder.getMemberInstance();
		Member member = memberHolder.getMember();
				
		
		lblId.setText(member.getMemberId());
		lblName.setText(member.getMemberName());
		lblEmail.setText(member.getMemberEmail());
		lblPhone.setText(member.getMemberPhone());
		lblAddress.setText(member.getMemberAddress());
		lblFee.setText(member.getMemberFee());
	}

}














