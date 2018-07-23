package mylas.com.erp.demo.procedures;

import java.io.Serializable;
import java.util.Date;

public class EmpLeaveRequestJoin implements Serializable {
	

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
	private String leavetype;
	private String leavestatus;
	private String eid;
	

	public EmpLeaveRequestJoin() {
		super();
		// TODO Auto-generated constructor stub
	}

	public EmpLeaveRequestJoin(Integer id, Integer leavecount, String employeeid, Date fromdate, String leavereason,
			Integer leavetypeid, Integer managerid, Integer leavestatusid, Date todate, Boolean isactive,
			Integer createdby, Date createddate, Integer updatedby, Date updateddate, String leavetype,
			String leavestatus, String eid) {
		super();
		this.id = id;
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
		this.leavetype = leavetype;
		this.leavestatus = leavestatus;
		this.eid = eid;
	}

	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getLeavecount() {
		return leavecount;
	}
	public void setLeavecount(Integer leavecount) {
		this.leavecount = leavecount;
	}
	public String getEmployeeid() {
		return employeeid;
	}
	public void setEmployeeid(String employeeid) {
		this.employeeid = employeeid;
	}
	public Date getFromdate() {
		return fromdate;
	}
	public void setFromdate(Date fromdate) {
		this.fromdate = fromdate;
	}
	public String getLeavereason() {
		return leavereason;
	}
	public void setLeavereason(String leavereason) {
		this.leavereason = leavereason;
	}
	public Integer getLeavetypeid() {
		return leavetypeid;
	}
	public void setLeavetypeid(Integer leavetypeid) {
		this.leavetypeid = leavetypeid;
	}
	public Integer getManagerid() {
		return managerid;
	}
	public void setManagerid(Integer managerid) {
		this.managerid = managerid;
	}
	public Integer getLeavestatusid() {
		return leavestatusid;
	}
	public void setLeavestatusid(Integer leavestatusid) {
		this.leavestatusid = leavestatusid;
	}
	public Date getTodate() {
		return todate;
	}
	public void setTodate(Date todate) {
		this.todate = todate;
	}
	public Boolean getIsactive() {
		return isactive;
	}
	public void setIsactive(Boolean isactive) {
		this.isactive = isactive;
	}
	public Integer getCreatedby() {
		return createdby;
	}
	public void setCreatedby(Integer createdby) {
		this.createdby = createdby;
	}
	public Date getCreateddate() {
		return createddate;
	}
	public void setCreateddate(Date createddate) {
		this.createddate = createddate;
	}
	public Integer getUpdatedby() {
		return updatedby;
	}
	public void setUpdatedby(Integer updatedby) {
		this.updatedby = updatedby;
	}
	public Date getUpdateddate() {
		return updateddate;
	}
	public void setUpdateddate(Date updateddate) {
		this.updateddate = updateddate;
	}
	public String getLeavetype() {
		return leavetype;
	}
	public void setLeavetype(String leavetype) {
		this.leavetype = leavetype;
	}
	public String getLeavestatus() {
		return leavestatus;
	}
	public void setLeavestatus(String leavestatus) {
		this.leavestatus = leavestatus;
	}
	
	public String getEid() {
		return eid;
	}
	public void setEid(String eid) {
		this.eid = eid;
	}


}
