package mylas.com.erp.demo.dao;

import java.util.List;

import mylas.com.erp.demo.EmpDetails;
import mylas.com.erp.demo.TblDesignation;



public interface DesignationDao {

	public String saveDetails(TblDesignation tbldesg);
	public List<TblDesignation> getDetails();
	public void updateDetails(TblDesignation tbldesg);
	public String updateDetails(int id,String newDep,String newDep1, String todate);
	public void deleteDetails(int id);
	 public TblDesignation getById(int id);

}
