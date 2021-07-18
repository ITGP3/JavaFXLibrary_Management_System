package entity;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Staff {

	private IntegerProperty staffId;
	private StringProperty staffFirstName;
	private StringProperty staffLastName;
	private StringProperty staffEmail;
	private StringProperty staffPassword;
	private StringProperty staffPhone;
	private StringProperty staffStatus;
	private StringProperty staffDOB;
	private StringProperty staffAddress;

	public Staff(Integer staffId, String staffFirstName, String staffLastName, String staffEmail, String staffPassword,
			String staffPhone, String staffStatus, String staffDOB, String staffAddress) {
		super();
		this.staffId = new SimpleIntegerProperty(staffId);
		this.staffFirstName = new SimpleStringProperty();
		this.staffLastName = new SimpleStringProperty();
		this.staffEmail = new SimpleStringProperty();
		this.staffPassword = new SimpleStringProperty();
		this.staffPhone = new SimpleStringProperty();
		this.staffStatus = new SimpleStringProperty();
		this.staffDOB = new SimpleStringProperty();
		this.staffAddress = new SimpleStringProperty();
	}

	public Staff(String staffFirstName, String staffLastName, String staffEmail, String staffPassword,
			String staffPhone, String staffStatus, String staffDOB, String staffAddress) {
		super();
		this.staffFirstName = new SimpleStringProperty();
		this.staffLastName = new SimpleStringProperty();
		this.staffEmail = new SimpleStringProperty();
		this.staffPassword = new SimpleStringProperty();
		this.staffPhone = new SimpleStringProperty();
		this.staffStatus = new SimpleStringProperty();
		this.staffDOB = new SimpleStringProperty();
		this.staffAddress = new SimpleStringProperty();
	}

	public Integer getStaffId() {
		return staffId.get();
	}

	public void setStaffId(Integer staffId) {
		this.staffId = new SimpleIntegerProperty(staffId);
	}

	public String getStaffFirstName() {
		return staffFirstName.get();
	}

	public void setStaffFirstName(String staffFirstName) {
		this.staffFirstName = new SimpleStringProperty();
	}

	public String getStaffLastName() {
		return staffLastName.get();
	}

	public void setStaffLastName(String staffLastName) {
		this.staffLastName = new SimpleStringProperty();
	}

	public String getStaffEmail() {
		return staffEmail.get();
	}

	public void setStaffEmail(String staffEmail) {
		this.staffEmail = new SimpleStringProperty();
	}

	public String getStaffPassword() {
		return staffPassword.get();
	}

	public void setStaffPassword(String staffPassword) {
		this.staffPassword = new SimpleStringProperty();
	}

	public String getStaffPhone() {
		return staffPhone.get();
	}

	public void setStaffPhone(String staffPhone) {
		this.staffPhone = new SimpleStringProperty();
	}

	public String getStaffStatus() {
		return staffStatus.get();
	}

	public void setStaffStatus(String staffStatus) {
		this.staffStatus = new SimpleStringProperty();
	}

	public String getStaffDOB() {
		return staffDOB.get();
	}

	public void setStaffDOB(String staffDOB) {
		this.staffDOB = new SimpleStringProperty();
	}

	public String getStaffAddress() {
		return staffAddress.get();
	}

	public void setStaffAddress(String staffAddress) {
		this.staffAddress = new SimpleStringProperty();
	}

}
