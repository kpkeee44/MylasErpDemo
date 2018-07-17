package mylas.com.erp.demo.dao;

import java.util.List;

import org.hibernate.Query;

import mylas.com.erp.demo.EmpDetails;
import mylas.com.erp.demo.TblEmployees;

public interface EmployeeDao {
	
	public String getConnection(EmpDetails emp);
	public List<EmpDetails> getDetails();
	public void updateDetails(EmpDetails emp, int id);
	public void deleteDetails(int id);
	public EmpDetails getById(int id);
	public EmpDetails getByUName(String empuname);
	public List<EmpDetails> getByManid(String managerid);
	public List<EmpDetails> getByManIdAndTransManId(String managerid,String transmanid);
	public String TransferManager(int id,String mantrans);
	public String ChangeManager(int id);
	public String ChangeTransManager(int id,String tomanagerid);
	 public void updateEditDetails(int id,String firstname,String lastname,String uname,String empid,String pswd,String cpswd,String joindate,String phone,String company,String department,String lastworkingday);
	 public List<EmpDetails> viewSearch(String firstname, String lastname, String department, String designation);
	 public List<EmpDetails> simulateSearchResult(String tagName);
	 public List<EmpDetails> simulateSearchResultLastName(String tagName);
	 public String getMail(String mngid);
	 /*new methods*/
	 public String saveEmpDetails(int id,String empid,String fname,String lname,String email,String uname,String pswd,String addres,String ph,String adr,String crby,String upby,String role,String dept,String des);
	 public List<TblEmployees> getDetails1();
}
