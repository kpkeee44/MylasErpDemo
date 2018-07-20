package mylas.com.erp.demo.dao;

import java.util.List;

import mylas.com.erp.demo.EmpDetails;
import mylas.com.erp.demo.TblDepartment;




public interface DepartmentDao {

	
	public String saveDepartment(int id,String dname,String active,int cby,int uby);
	public List<TblDepartment> getDetails();
	/*public void updateDetails(TblDepartment tbl);
	public void deleteDetails(int id);
	public String updateDetails(int id,String newDep, String todate);*/
	public TblDepartment getById(int id);
	public List<TblDepartment> getDetailsview();
}
