package utility;

import java.util.Optional;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Alert.AlertType;

public class MyAlert {
	
	public Optional<ButtonType> getConfirmAlert(String title,String header, String content){
			
			Alert alert = new Alert(AlertType.CONFIRMATION);
			
			alert.setTitle(title);
			alert.setHeaderText(header);
			
			alert.setContentText(content);
			
			Optional<ButtonType> result = alert.showAndWait();
			
			return result;
		}

}
