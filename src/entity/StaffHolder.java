package entity;

public class StaffHolder {

	public Staff staff;
	
	public final static StaffHolder STAFF_INSTANCE = new StaffHolder();

	public StaffHolder() {
		
	}
	
	public static StaffHolder getStaffInstance() {
		return STAFF_INSTANCE;
	}

	public Staff getStaff() {
		return staff;
	}

	public void setStaff(Staff staff) {
		this.staff = staff;
	}	
	
}
