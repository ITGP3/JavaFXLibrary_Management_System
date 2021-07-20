package utility;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
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
		preStmt.setString(6, staff.getStaffStatus());
		
		Date date = Date.valueOf(staff.getStaffDOB());
		preStmt.setDate(7, date);
		
		preStmt.setString(8, staff.getStaffAddress());				
		
		Boolean isSaveOk = preStmt.execute();
		connection.close();
		return isSaveOk;
	}
	
	// D delete
	public Boolean deleteStaff(Integer staffId) throws SQLException {
		connection = DBConnection.getConnection();

		preStmt = connection.prepareStatement("delete from staff where staffId = ?;");

		preStmt.setInt(1, staffId);

		Boolean isDeleteOk = preStmt.execute();
		connection.close();
		return isDeleteOk;

	}
	
	//getAllStaffColumnLabel
	
	public ObservableList<String> getAllColumnLabel() throws SQLException{
		ObservableList<String> columnLabelList = FXCollections.observableArrayList();
		
		connection = DBConnection.getConnection();
		
		statement = connection.createStatement();
		
		resultSet = statement.executeQuery("select * from staff;");
		
		ResultSetMetaData metaData = resultSet.getMetaData();
		
		Integer count = metaData.getColumnCount();
	
		for(int x = 1; x <= count; x++) {
			columnLabelList.add(metaData.getColumnLabel(x));
		}
		return columnLabelList;
		
	}	
	
}
