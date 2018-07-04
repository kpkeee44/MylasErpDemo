package mylas.com.erp.demo.dao;

import java.util.List;

import org.hibernate.Query;

import mylas.com.erp.demo.EmpDetails;

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
}
