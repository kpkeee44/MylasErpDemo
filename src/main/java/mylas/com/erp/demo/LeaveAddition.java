package mylas.com.erp.demo;

import javax.persistence.Column;

public class LeaveAddition {
	public LeaveAddition() {
		super();
	}
	private int leavetypeid;
	public int getLeavetypeid() {
		return leavetypeid;
	}
	public void setLeavetypeid(int leavetypeid) {
		this.leavetypeid = leavetypeid;
	}
	private String leaveType;
	private int numleavedays;
	private boolean isactive;

	public boolean isIsactive() {
		return isactive;
	}
	public void setIsactive(boolean isactive) {
		this.isactive = isactive;
	}
	public String getLeaveType() {
		return leaveType;
	}
	public void setLeaveType(String leaveType) {
		this.leaveType = leaveType;
	}
	
	public int getNumleavedays() {
		return numleavedays;
	}
	public void setNumleavedays(int numleavedays) {
		this.numleavedays = numleavedays;
	}
	@Override
	public String toString() {
		return "LeaveAddition [leavetypeid=" + leavetypeid + ", leaveType=" + leaveType + ", numleavedays="
				+ numleavedays + ", isactive=" + isactive + "]";
	}
	public LeaveAddition(int leavetypeid, String leaveType, int numleavedays, boolean isactive) {
		super();
		this.leavetypeid = leavetypeid;
		this.leaveType = leaveType;
		this.numleavedays = numleavedays;
		this.isactive = isactive;
	}
	
	

}
