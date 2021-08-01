package DBConnection;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class CheckUserCredential {

    private Connection connection;
    private Statement statement;
    private ResultSet resultSet;

    boolean isLoginOk = false;

    public boolean isUserValid(String email,String password,String role,String loginType, String status) throws SQLException {
        connection = DBConnection.getConnection();
        statement = connection.createStatement();
        String query = "select "+role+loginType+","+role+"password from "+role+
                " where "+role+loginType+" = '"+email+"' and "+role+status+"='Active' and "+role+"password = '"+password+"' ;";
        resultSet = statement.executeQuery(query);

        if (resultSet.next()){
            isLoginOk = true;
        }

        return isLoginOk;
    }
}
