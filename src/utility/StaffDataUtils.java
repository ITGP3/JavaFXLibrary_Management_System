package utility;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import DBConnection.DBConnection;
import entity.Staff;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 * 
 * @author Hnin Yu Hlaing
 *
 */

public class StaffDataUtils {
	private Connection connection;
	private Statement statement;
	private PreparedStatement preStmt;
	private ResultSet resultSet;
	
	//R read
	public ObservableList<Staff> getAllStaffs(String sql) throws SQLException{
		
		ObservableList<Staff> staffList = FXCollections.observableArrayList();
		
		connection = DBConnection.getConnection();
		
		statement = connection.createStatement();
		
		resultSet = statement.executeQuery(sql);
		
		
		while(resultSet.next()) {
			staffList.add(new Staff(resultSet.getInt("staffId"),
					resultSet.getString("staffFirstName"), 
					resultSet.getString("staffLastName"),
					resultSet.getString("staffEmail"), 
					resultSet.getString("staffPassword"), 
					resultSet.getString("staffPhone"), 
					resultSet.getString("staffStatus"), 
					resultSet.getDate("staffDOB").toString(), 
					resultSet.getString("staffAddress"))
			);
		}
		
		connection.close();
		return staffList;		
		
	}
	
	//C create
	public Boolean saveStaff(Staff staff) throws SQLException {
		connection = DBConnection.getConnection();
		
		preStmt = connection.prepareStatement("INSERT INTO `staff` ("
				+ " `staffFirstName`, "
				+ " `staffLastName`,"
				+ " `staffEmail`, "
				+ " `staffPassword`,"
				+ " `staffPhone`, "
				+ " `staffStatus`,"
				+ " `staffDOB`,"
				+ " `staffAddress`) VALUES (?, ?, ?, ?, ?, ?, ?, ?)"  
				);
		
		preStmt.setString(1, staff.getStaffFirstName());
		preStmt.setString(2, staff.getStaffLastName());
		preStmt.setString(3, staff.getStaffEmail());
		preStmt.setString(4, staff.getStaffPassword());
		preStmt.setString(5, staff.getStaffPhone());
		preStmt.setString(6, staff.getStaffAddress());
		preStmt.setString(7, staff.getStaffStatus());
		
		Date date = Date.valueOf(staff.getStaffDOB());
		preStmt.setDate(8, date);
		
		Boolean isSaveOk = preStmt.execute();
		connection.close();
		return isSaveOk;
	}
	
}
