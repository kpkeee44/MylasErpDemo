package mylas.com.erp.demo;

import javax.persistence.Column;

public class LeaveAddition {
	private int id;
	private int leavetypeid;
	private String leaveType;
	private int numleavedays;
	private boolean isactive;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getLeavetypeid() {
		return leavetypeid;
	}
	public void setLeavetypeid(int leavetypeid) {
		this.leavetypeid = leavetypeid;
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
	public boolean isIsactive() {
		return isactive;
	}
	public void setIsactive(boolean isactive) {
		this.isactive = isactive;
	}
	@Override
	public String toString() {
		return "LeaveAddition [id=" + id + ", leavetypeid=" + leavetypeid + ", leaveType=" + leaveType
				+ ", numleavedays=" + numleavedays + ", isactive=" + isactive + "]";
	}
	public LeaveAddition() {
		super();
	}

}
