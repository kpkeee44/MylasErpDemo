package mylas.com.erp.demo.dao;

import java.util.List;
import java.util.Map;

import mylas.com.erp.demo.LeaveAddition;
import mylas.com.erp.demo.Tblleaves;
import mylas.com.erp.demo.Tblleavestype;

public interface LeaveManiplication {
	public List<Tblleavestype> getDetails();
	public String save(String leavetype,int count,String uid);
	
	public List<LeaveAddition> getDetailsofleavetye();
	public Tblleaves getDetaisById(int id);
	public String updateLeave(int id,int days,String eid,String active);
	public void deleteByid(int id);
	public String proexe();
	
		
	

}
