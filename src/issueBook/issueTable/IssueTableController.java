package issueBook.issueTable;


/**
 *
 * @author Sit Min Htet
 *
 */

import entity.IssueBook;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import utility.IssueBookUtility;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class IssueTableController implements Initializable {

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

    @Override
    public void initialize(URL location, ResourceBundle resources) {

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

}