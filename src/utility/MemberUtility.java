package utility;

import java.sql.Connection;
import java.sql.Date;
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
	    		memberlist.add(new Member(rs.getString("memberId"),rs.getString("memberName"),
	    				rs.getString("memberEmail"),rs.getString("memberPhone"),
	    				rs.getString("memberAddress"),rs.getString("memberFee"),rs.getDate("memberDOB").toString()));
	    	}
	    	
	    	connection.close();
	    	
			return memberlist;
	    	
	    }
	    //Create
	    public Boolean saveMember(Member member) throws SQLException {
	    	
	    	  connection = DBConnection.getConnection();
	    	  preStmt = connection.prepareStatement("INSERT INTO `member` (`memberId`, `memberName`, `memberEmail`, `memberPhone`, `memberAddress`, `memberFee`, `memberDOB`) "
	    	  		+ "VALUES (?, ?, ?, ?, ?, ?, ?);");
	    	  
	    	  preStmt.setString(1, member.getMemberId());
	    	  preStmt.setString(2, member.getMemberName());
	    	  preStmt.setString(3, member.getMemberEmail());
	    	  preStmt.setString(4, member.getMemberPhone());
	    	  preStmt.setString(5, member.getMemberAddress());
	    	  preStmt.setString(6, member.getMemberFee());
	    	  Date date = Date.valueOf(member.getMemberDOB());
	    	  preStmt.setDate(7, date);

	    	  
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
	    //Update
	    public Boolean updateMember(Member member) throws SQLException {
	    	connection = DBConnection.getConnection();
	    	preStmt = connection.prepareStatement("UPDATE `member` SET "
	    			+ "`memberName` = ?, `memberEmail` = ?, "
	    			+ "`memberPhone` = ?, `memberAddress` = ?,"
	    			+ " `memberFee` = ?,  `memberDOB` = ? "
	    			+ "WHERE (`memberId` = ?);");
	    	 preStmt.setString(1, member.getMemberName());
	    	  preStmt.setString(2, member.getMemberEmail());
	    	  preStmt.setString(3, member.getMemberPhone());
	    	  preStmt.setString(4, member.getMemberAddress());
	    	  preStmt.setString(5, member.getMemberFee());
	    	 
	    	  Date date = Date.valueOf(member.getMemberDOB());
	    	  preStmt.setDate(6, date);
	    	  preStmt.setString(7, member.getMemberId());
	    	  
	    	  Boolean isUpdateOk = preStmt.execute();
	  		connection.close();
	  		return isUpdateOk;
	    	
	    }
	    //alreadexitsissue
	    public boolean memberAlreadyIssue(Member member) throws SQLException {
	    	connection = DBConnection.getConnection();
	    	String query = "select count(*) from issue where memberId = ?";
			preStmt = connection.prepareStatement(query);
			preStmt.setString(1, member.getMemberId());
			rs = preStmt.executeQuery();
			if (rs.next()){
				int count = rs.getInt(1);
				System.out.println(count);
				return (count>0);
			}
			return false;
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
