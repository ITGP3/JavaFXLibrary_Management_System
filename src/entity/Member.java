package entity;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 * 
 * @author Win Pa Pa Aung
 *
 */
public class Member {

	private StringProperty memberId;
	private StringProperty memberName;
	private  StringProperty memberEmail;
	private  StringProperty memberPhone;
	private StringProperty memberAddress;
	private  StringProperty memberFee;
	

	public Member(String memberId, String memberName, String memberEmail, String memberPhone, String memberAddress, String memberFee)
	{
		super();
		this.memberId = new SimpleStringProperty(memberId);
		this.memberName = new SimpleStringProperty(memberName);
		this.memberEmail = new SimpleStringProperty(memberEmail);
		this.memberPhone = new SimpleStringProperty(memberPhone);
		this.memberAddress = new SimpleStringProperty(memberAddress);
		this.memberFee = new SimpleStringProperty(memberFee);
	}
	public Member(String memberName, String memberEmail, String memberPhone,
			String memberAddress, String memberFee) {
		super();
		this.memberName = new SimpleStringProperty(memberName);
		this.memberEmail = new SimpleStringProperty(memberEmail);
		this.memberPhone = new SimpleStringProperty(memberPhone);
		this.memberAddress = new SimpleStringProperty(memberAddress);
		this.memberFee = new SimpleStringProperty(memberFee);
	}
	
	
	
	public Member(String memberId) {
		super();
		this.memberId = new SimpleStringProperty(memberId) ;
	}
	public String getMemberId() {
		return memberId.get();
	}

	public void setMemberId(String memberId) {
		this.memberId = new SimpleStringProperty(memberId);
	}

	public String getMemberName() {
		return memberName.get();
	}


	public void setMemberName(String memberName) {
		this.memberName = new SimpleStringProperty(memberName);
	}

	public String getMemberEmail() {
		return memberEmail.get();
	}

	public void setMemberEmail(String memberEmail) {
		this.memberEmail = new SimpleStringProperty(memberEmail);
	}

	public String getMemberPhone() {
		return memberPhone.get();
	}

	public void setMemberPhone(String memberPhone) {
		this.memberPhone = new SimpleStringProperty(memberPhone);
	}
	
	
	public String getMemberAddress() {
		return memberAddress.get();
	}

	public void setMemberAddress(String memberAddress) {
		this.memberAddress = new SimpleStringProperty(memberAddress);
	}
	
	public String getMemberFee() {
		return memberFee.get();
	}
	
}
