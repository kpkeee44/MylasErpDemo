package mylas.com.erp.demo;
// Generated Jul 19, 2018 12:46:33 PM by Hibernate Tools 5.3.0.Beta2

import java.util.Date;

/**
 * TblEmpLeavereq generated by hbm2java
 */
public class TblEmpLeavereq implements java.io.Serializable {

	private Integer id;
	private Integer leavecount;
	private String employeeid;
	private Date fromdate;
	private String leavereason;
	private Integer leavetypeid;
	private Integer managerid;
	private Integer leavestatusid;
	private Date todate;
	private Boolean isactive;
	private Integer createdby;
	private Date createddate;
	private Integer updatedby;
	private Date updateddate;

	public TblEmpLeavereq() {
	}

	public TblEmpLeavereq(Integer leavecount, String employeeid, Date fromdate, String leavereason, Integer leavetypeid,
			Integer managerid, Integer leavestatusid, Date todate, Boolean isactive, Integer createdby,
			Date createddate, Integer updatedby, Date updateddate) {
		this.leavecount = leavecount;
		this.employeeid = employeeid;
		this.fromdate = fromdate;
		this.leavereason = leavereason;
		this.leavetypeid = leavetypeid;
		this.managerid = managerid;
		this.leavestatusid = leavestatusid;
		this.todate = todate;
		this.isactive = isactive;
		this.createdby = createdby;
		this.createddate = createddate;
		this.updatedby = updatedby;
		this.updateddate = updateddate;
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getLeavecount() {
		return this.leavecount;
	}

	public void setLeavecount(Integer leavecount) {
		this.leavecount = leavecount;
	}

	public String getEmployeeid() {
		return this.employeeid;
	}

	public void setEmployeeid(String employeeid) {
		this.employeeid = employeeid;
	}

	public Date getFromdate() {
		return this.fromdate;
	}

	public void setFromdate(Date fromdate) {
		this.fromdate = fromdate;
	}

	public String getLeavereason() {
		return this.leavereason;
	}

	public void setLeavereason(String leavereason) {
		this.leavereason = leavereason;
	}

	public Integer getLeavetypeid() {
		return this.leavetypeid;
	}

	public void setLeavetypeid(Integer leavetypeid) {
		this.leavetypeid = leavetypeid;
	}

	public Integer getManagerid() {
		return this.managerid;
	}

	public void setManagerid(Integer managerid) {
		this.managerid = managerid;
	}

	public Integer getLeavestatusid() {
		return this.leavestatusid;
	}

	public void setLeavestatusid(Integer leavestatusid) {
		this.leavestatusid = leavestatusid;
	}

	public Date getTodate() {
		return this.todate;
	}

	public void setTodate(Date todate) {
		this.todate = todate;
	}

	public Boolean getIsactive() {
		return this.isactive;
	}

	public void setIsactive(Boolean isactive) {
		this.isactive = isactive;
	}

	public Integer getCreatedby() {
		return this.createdby;
	}

	public void setCreatedby(Integer createdby) {
		this.createdby = createdby;
	}

	public Date getCreateddate() {
		return this.createddate;
	}

	public void setCreateddate(Date createddate) {
		this.createddate = createddate;
	}

	public Integer getUpdatedby() {
		return this.updatedby;
	}

	public void setUpdatedby(Integer updatedby) {
		this.updatedby = updatedby;
	}

	public Date getUpdateddate() {
		return this.updateddate;
	}

	public void setUpdateddate(Date updateddate) {
		this.updateddate = updateddate;
	}

}
