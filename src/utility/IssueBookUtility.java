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

public class IssueBookUtility {

    private Connection connection;
    private Statement stmt;
    private PreparedStatement preStmt;
    private ResultSet rs;


    public boolean saveIssueBook(IssueBook issueBook) throws SQLException {
        connection = DBConnection.getConnection();

        String issueQuery = "insert into library.issue(bookId,memberId) values (?,?);";
        preStmt = connection.prepareStatement(issueQuery);

        preStmt.setString(1,issueBook.getBookId());
        preStmt.setString(2,issueBook.getMemberId());

        boolean isInsertOk= preStmt.execute();
        connection.close();
        return  isInsertOk;
    }

    public boolean updateBookAvaliable(Book book) throws SQLException {
        connection = DBConnection.getConnection();

        String updateIssueQuery = "update library.book set bookAvaliable=false where bookId= ?;";
        preStmt = connection.prepareStatement(updateIssueQuery);

        preStmt.setString(1,book.getBookId());

        boolean isInsertOk= preStmt.execute();
        connection.close();
        return  isInsertOk;
    }

    public boolean updateIssueBook(IssueBook issueBook) throws SQLException {
        connection = DBConnection.getConnection();

        String issueQuery = "update issue set issueTime = CURRENT_TIMESTAMP , renewCount= renewCount+1 where bookId = ?";
        preStmt = connection.prepareStatement(issueQuery);

        preStmt.setString(1,issueBook.getBookId());


        boolean isInsertOk= preStmt.execute();
        connection.close();
        return  isInsertOk;
    }


}

