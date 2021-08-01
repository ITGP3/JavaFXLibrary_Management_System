package issueBook.issueTable;


/**
 *
 * @author Sit Min Htet
 *
 */

import entity.IssueBook;
import javafx.animation.FadeTransition;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.Duration;
import utility.IssueBookUtility;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class IssueTableController implements Initializable {

    @FXML
    private AnchorPane rootPane;

    @FXML
    private TableView<IssueBook> issueBookTable;

    @FXML
    private TableColumn<IssueBook, String> bookId;

    @FXML
    private TableColumn<IssueBook, String> memberId;

    @FXML
    private TableColumn<IssueBook, String> issueDate;

    @FXML
    private TableColumn<IssueBook, Integer> renewCount;

    private final IssueBookUtility issueBookUtility = new IssueBookUtility();

    @FXML
    void backPage(MouseEvent event) throws IOException {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.hide();
        
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        rootPane.setOpacity(0);
        makeFade();
        bookId.setCellValueFactory(new PropertyValueFactory<>("bookId"));
        memberId.setCellValueFactory(new PropertyValueFactory<>("memberId"));
        issueDate.setCellValueFactory(new PropertyValueFactory<>("issueTime"));
        renewCount.setCellValueFactory(new PropertyValueFactory<>("renewCount"));
        try {
            issueBookTable.setItems(issueBookUtility.getAllIssueBook("select * from issue"));
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    private void makeFade(){
        FadeTransition fadeTransition = new FadeTransition();
        fadeTransition.setDuration(Duration.millis(1000));
        fadeTransition.setNode(rootPane);
        fadeTransition.setFromValue(0);
        fadeTransition.setToValue(1);
        fadeTransition.play();
    }


}