package mylas.com.erp.demo.procedures;

import java.sql.Date;

public class EmployeeViewPage {
//	t1.id,t1.eid,t1.emplfirstname,t1.empllastname,t1.email,t1.isactive,t1.Adderss,
//	t1.phone,t1.aadharno,t1.createdby,t1.createdDate,t1.updatedBy,t1.updatedDate,
//	t1.role,t1.uname,t2.[departmentname],t3.[designation]
	
	int id;
	String eid;
	String emplfirstname;
	String empllastname;
	String email;
	boolean isactive;
	String adderss;
	String phone;
	String aadharno;
	int createdby;
	Date createdDate;
	int updatedBy;
	Date updatedDate;
	String role;
	String uname;
	int departmentid;
	int designationid;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getEid() {
		return eid;
	}
	public void setEid(String eid) {
		this.eid = eid;
	}
	public String getEmplfirstname() {
		return emplfirstname;
	}
	public void setEmplfirstname(String emplfirstname) {
		this.emplfirstname = emplfirstname;
	}
	public String getEmpllastname() {
		return empllastname;
	}
	public void setEmpllastname(String empllastname) {
		this.empllastname = empllastname;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public boolean isIsactive() {
		return isactive;
	}
	public void setIsactive(boolean isactive) {
		this.isactive = isactive;
	}
	public String getAdderss() {
		return adderss;
	}
	public void setAdderss(String adderss) {
		this.adderss = adderss;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getAadharno() {
		return aadharno;
	}
	public void setAadharno(String aadharno) {
		this.aadharno = aadharno;
	}
	public int getCreatedby() {
		return createdby;
	}
	public void setCreatedby(int createdby) {
		this.createdby = createdby;
	}
	public Date getCreatedDate() {
		return createdDate;
	}
	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}
	public int getUpdatedBy() {
		return updatedBy;
	}
	public void setUpdatedBy(int updatedBy) {
		this.updatedBy = updatedBy;
	}
	public Date getUpdatedDate() {
		return updatedDate;
	}
	public void setUpdatedDate(Date updatedDate) {
		this.updatedDate = updatedDate;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	public String getUname() {
		return uname;
	}
	public void setUname(String uname) {
		this.uname = uname;
	}
	public int getDepartmentnameid() {
		return departmentid;
	}
	public void setDepartmentnameid(int departmentid) {
		this.departmentid = departmentid;
	}
	public int getDesignationid() {
		return designationid;
	}
	public void setDesignationid(int designationid) {
		this.designationid = designationid;
	}
	
	
	
	public EmployeeViewPage() {
		super();
	}
	@Override
	public String toString() {
		return "EmployeeViewPage [id=" + id + ", eid=" + eid + ", emplfirstname=" + emplfirstname + ", empllastname="
				+ empllastname + ", email=" + email + ", isactive=" + isactive + ", adderss=" + adderss + ", phone="
				+ phone + ", aadharno=" + aadharno + ", createdby=" + createdby + ", createdDate=" + createdDate
				+ ", updatedBy=" + updatedBy + ", updatedDate=" + updatedDate + ", role=" + role + ", uname=" + uname
				+ ", departmentname=" + departmentid + ", designation=" + designationid + "]";
	}
	
	
	
	
	
	
	
	
	
	
	
	

}
