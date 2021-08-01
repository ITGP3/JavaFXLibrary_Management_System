package utility;

import DBConnection.DBConnection;
import entity.Book;
import entity.IssueBook;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

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

    public ObservableList<IssueBook> getAllIssueBook(String sql) throws SQLException{

        ObservableList<IssueBook> issueBooks = FXCollections.observableArrayList();

        connection = DBConnection.getConnection();
        stmt = connection.createStatement();
        rs = stmt.executeQuery(sql);

        while(rs.next()) {
            issueBooks.add(
                    new IssueBook(
                            rs.getString("bookId"),
                            rs.getString("memberId"),
                            rs.getString("issueTime"),
                            rs.getInt("renewCount")

                    )
            );
        }

        connection.close();
        return issueBooks;
    }


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

        String issueQuery = "update issue set issueTime = CURRENT_TIMESTAMP , renewCount = renewCount+1 where bookId = ?";
        preStmt = connection.prepareStatement(issueQuery);

        preStmt.setString(1,issueBook.getBookId());


        boolean isInsertOk= preStmt.execute();
        connection.close();
        return  isInsertOk;
    }


    public boolean isIssueBook(IssueBook issueBook) throws SQLException {

        connection = DBConnection.getConnection();

        String issueQuery = "update issue set issueTime = CURRENT_TIMESTAMP , renewCount = renewCount+1 where bookId = ?";
        preStmt = connection.prepareStatement(issueQuery);

        preStmt.setString(1,issueBook.getBookId());


        boolean isInsertOk= preStmt.execute();
        connection.close();
        return  isInsertOk;
    }




}

