package mylas.com.erp.demo;
// Generated Jul 4, 2018 5:29:27 PM by Hibernate Tools 5.3.0.Beta2

/**
 * TblEmpLeavereq generated by hbm2java
 */
public class TblEmpLeavereq implements java.io.Serializable {

	private Integer id;
	private Integer count;
	private String employeeid;
	private String fromdate;
	private String leavereason;
	private String leavetype;
	private String managerid;
	private Boolean status;
	private String todate;
	private String reason;
	private String mantrans;
	private Integer referenceid;
	private Integer daycount;

	public TblEmpLeavereq() {
	}

	public TblEmpLeavereq(Integer count, String employeeid, String fromdate, String leavereason, String leavetype,
			String managerid, Boolean status, String todate, String reason, String mantrans, Integer referenceid,
			Integer daycount) {
		this.count = count;
		this.employeeid = employeeid;
		this.fromdate = fromdate;
		this.leavereason = leavereason;
		this.leavetype = leavetype;
		this.managerid = managerid;
		this.status = status;
		this.todate = todate;
		this.reason = reason;
		this.mantrans = mantrans;
		this.referenceid = referenceid;
		this.daycount = daycount;
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getCount() {
		return this.count;
	}

	public void setCount(Integer count) {
		this.count = count;
	}

	public String getEmployeeid() {
		return this.employeeid;
	}

	public void setEmployeeid(String employeeid) {
		this.employeeid = employeeid;
	}

	public String getFromdate() {
		return this.fromdate;
	}

	public void setFromdate(String fromdate) {
		this.fromdate = fromdate;
	}

	public String getLeavereason() {
		return this.leavereason;
	}

	public void setLeavereason(String leavereason) {
		this.leavereason = leavereason;
	}

	public String getLeavetype() {
		return this.leavetype;
	}

	public void setLeavetype(String leavetype) {
		this.leavetype = leavetype;
	}

	public String getManagerid() {
		return this.managerid;
	}

	public void setManagerid(String managerid) {
		this.managerid = managerid;
	}

	public Boolean getStatus() {
		return this.status;
	}

	public void setStatus(Boolean status) {
		this.status = status;
	}

	public String getTodate() {
		return this.todate;
	}

	public void setTodate(String todate) {
		this.todate = todate;
	}

	public String getReason() {
		return this.reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

	public String getMantrans() {
		return this.mantrans;
	}

	public void setMantrans(String mantrans) {
		this.mantrans = mantrans;
	}

	public Integer getReferenceid() {
		return this.referenceid;
	}

	public void setReferenceid(Integer referenceid) {
		this.referenceid = referenceid;
	}

	public Integer getDaycount() {
		return this.daycount;
	}

	public void setDaycount(Integer daycount) {
		this.daycount = daycount;
	}

}
