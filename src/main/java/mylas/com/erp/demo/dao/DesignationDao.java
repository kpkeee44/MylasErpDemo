package mylas.com.erp.demo.dao;

import java.util.List;

import mylas.com.erp.demo.EmpDetails;
import mylas.com.erp.demo.TblDesignation;



public interface DesignationDao {

	public void saveDetails(TblDesignation tbldesg);
	public List<TblDesignation> getDetails();
	public void updateDetails(TblDesignation tbldesg);
	public void updateDetails(int id,String newDep,String newDep1, String todate);
	public void deleteDetails(int id);
	 public TblDesignation getById(int id);

}
