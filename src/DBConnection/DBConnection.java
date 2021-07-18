package DBConnection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

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
}
