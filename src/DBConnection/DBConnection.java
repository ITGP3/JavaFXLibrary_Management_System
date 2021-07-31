package DBConnection;

import entity.Book;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.chart.PieChart;

import java.sql.*;

public class DBConnection {

    private static DBConnection handler = null;

    private static String url="jdbc:mysql://localhost:3306/library?useSSL=false";
    private static String username="root";
    private static String password="1234";

    public static Connection getConnection() throws SQLException {
        Connection connection = DriverManager.getConnection(url,username,password);

        return connection;
    }


    public static DBConnection getInstance() {
        if (handler == null) {
            handler = new DBConnection();
        }
        return handler;
    }


    Connection connection;
    ResultSet resultSet;
    PreparedStatement preparedStatement;
    Statement statement;

    public ResultSet executeQuery(String query){

        try {
            connection = DBConnection.getConnection();
            statement = connection.createStatement();
            resultSet = statement.executeQuery(query);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return resultSet;
    }

    public boolean bookAlreadyIssued(Book book) throws SQLException {
        connection = DBConnection.getConnection();
        String query = "select count(*) from issue where bookId = ?";
        preparedStatement = connection.prepareStatement(query);
        preparedStatement.setString(1, book.getBookId());
        resultSet = preparedStatement.executeQuery();
        if (resultSet.next()){
            int count = resultSet.getInt(1);
            System.out.println(count);
            return (count>0);
        }

        //Boolean isCheckOk = preparedStatement.execute();
        //connection.close();
        //return isCheckOk;
        return false;
    }

    public ObservableList<PieChart.Data> getBookPieChart() throws SQLException {
        ObservableList<PieChart.Data> data = FXCollections.observableArrayList();
        String query1 = "select count(*) from book;";
        String query2 = "select count(*) from issue;";
        resultSet = executeQuery(query1);
        if (resultSet.next()){
            int count = resultSet.getInt(1);
            data.add(new PieChart.Data("Total Book", count));
        }
        resultSet = executeQuery(query2);
        if (resultSet.next()){
            int count = resultSet.getInt(1);
            data.add(new PieChart.Data("Total Issue", count));
        }

        return  data;
    }
}
