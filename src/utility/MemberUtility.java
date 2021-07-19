package utility;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import DBConnection.DBConnection;
import entity.Member;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
/**
 * 
 * @author Win Pa Pa Aung
 *
 */

public class MemberUtility {

	 private Connection connection;
	    private Statement stmt;
	    private PreparedStatement preStmt;
	    private ResultSet rs;
	    
	    
	    public ObservableList<Member> getAllMembers(String Sql) throws SQLException{
	    	ObservableList<Member> memberlist = FXCollections.observableArrayList();
	    	connection = DBConnection.getConnection();
	    	stmt = connection.createStatement();
	    	rs = stmt.executeQuery(Sql);
	    	
	    	while(rs.next()) {
	    		memberlist.add(new Member(rs.getString("memberId"),rs.getString("memberName"),rs.getString("memberEmail"),rs.getString("memberPhone"),rs.getString("memberAddress"),rs.getString("memberFee")));
	    	}
	    	
	    	connection.close();
	    	
			return memberlist;
	    	
	    }
	    //Create
	    public Boolean saveMember(Member member) throws SQLException {
	    	
	    	  connection = DBConnection.getConnection();
	    	  preStmt = connection.prepareStatement("INSERT INTO `member` (`memberId`, `memberName`, `memberEmail`, `memberPhone`, `memberAddress`, `memberFee`) "
	    	  		+ "VALUES (?, ?, ?, ?, ?, ?);");
	    	  
	    	  preStmt.setString(1, member.getMemberId());
	    	  preStmt.setString(2, member.getMemberName());
	    	  preStmt.setString(3, member.getMemberEmail());
	    	  preStmt.setString(4, member.getMemberPhone());
	    	  preStmt.setString(5, member.getMemberAddress());
	    	  preStmt.setString(6, member.getMemberFee());
	    	  
	    	  Boolean isSaveOk = preStmt.execute();
	    	  connection.close();
			return isSaveOk;
	    }
	    //Delete
	    public Boolean deleMember(String memberId) throws SQLException {
	    	connection = DBConnection.getConnection();
	    	preStmt = connection.prepareStatement("delete from member where memberId = ?;");
	    	preStmt.setString(1, memberId);
	    	Boolean isDeleteOk = preStmt.execute();
	    	connection.close();
			return isDeleteOk;
	    	
	    }
	    
	    //getallcolumnfrommembertable
	    
	    
	    public ObservableList<String> getAllColumn() throws SQLException{
	    	ObservableList<String> columnList = FXCollections.observableArrayList();
	    	
	    	connection = DBConnection.getConnection();
	    	stmt = connection.createStatement();
	    	rs = stmt.executeQuery("select * from member;");
	    	ResultSetMetaData metaData = rs.getMetaData();
	    	Integer count = metaData.getColumnCount();
	    	for(int x = 1; x<=count; x++) {
	    		columnList.add(metaData.getColumnLabel(x));
	    	}
	    	
	    	
	    	
			return columnList;
	    	
	    	
	    	
	    	
	    }
	    
	    
	    
	    
	    
	    
	    
	    
	    
	    
	    
}
