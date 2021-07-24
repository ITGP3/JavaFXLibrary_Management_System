package utility;

import DBConnection.DBConnection;
import entity.Book;
import entity.IssueBook;

import java.sql.*;

/**
 *
 * @author Sit Min Htet
 *
 */

public class SubmissionUtility {

    private Connection connection;
    private Statement stmt;
    private PreparedStatement preStmt;
    private ResultSet rs;


    public boolean deleteIssueBook(IssueBook issueBook) throws  SQLException {
        connection = DBConnection.getConnection();

        String searchQuery = "delete from issue where bookid = ?;";
        preStmt = connection.prepareStatement(searchQuery);

        preStmt.setString(1,issueBook.getBookId());

        boolean isDeleteOk= preStmt.execute();
        connection.close();
        return isDeleteOk;
    }

    public boolean updateIssueBook(Book book) throws  SQLException {
        connection = DBConnection.getConnection();

        String searchQuery = "update book set bookAvaliable= true  where bookid = ?;";
        preStmt = connection.prepareStatement(searchQuery);

        preStmt.setString(1,book.getBookId());

        boolean isDeleteOk= preStmt.execute();
        connection.close();
        return isDeleteOk;
    }


}
