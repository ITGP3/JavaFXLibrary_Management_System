package entity;

import javafx.beans.property.SimpleStringProperty;

/**
 * 
 * @author Win Pa Pa Aung
 *
 */
public class Member {

	private SimpleStringProperty memberId;
	private SimpleStringProperty memberName;
	private  SimpleStringProperty memberEmail;
	private  SimpleStringProperty memberPhone;
	private SimpleStringProperty memberAddress;
	private  SimpleStringProperty memberFee;
	

	public Member(String memberId, String memberName, String memberEmail, String memberPhone, String memberAddress, String memberFee) {
		super();
		this.memberId = new SimpleStringProperty(memberId);
		this.memberName = new SimpleStringProperty(memberName);
		this.memberEmail = new SimpleStringProperty(memberEmail);
		this.memberPhone = new SimpleStringProperty(memberPhone);
		this.memberAddress = new SimpleStringProperty(memberAddress);
		this.memberFee = new SimpleStringProperty(memberFee);
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
		this.memberEmail = new SimpleStringProperty("memberEmail");
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

	public void setMemberFee(String memberFee) {
		this.memberFee = new SimpleStringProperty(memberFee);
	}
	
	public SimpleStringProperty memberIdProperty() {
		return memberId;
	}
	
	public SimpleStringProperty memberNameProperty() {
		return memberName;
	}
	
	public SimpleStringProperty memberEmailProperty() {
		return memberEmail;
	}
	
	public SimpleStringProperty memberPhoneProperty() {
		return memberPhone;
	}

	
	public SimpleStringProperty memberFeeProperty() {
		return memberFee;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
