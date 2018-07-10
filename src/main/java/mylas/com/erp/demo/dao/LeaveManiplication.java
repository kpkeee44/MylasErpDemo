package mylas.com.erp.demo.dao;

import java.util.List;
import java.util.Map;

import mylas.com.erp.demo.Tblleaves;
import mylas.com.erp.demo.Tblleavestype;

public interface LeaveManiplication {
	public List<Tblleavestype> getDetails();
	public String save(Tblleaves empleave);
	
	public List<Tblleaves> getDetailsofleavetye();
	public Tblleaves getDetaisById(int id);
	public String updateLeave(int id,Tblleaves leave);
	public void deleteByid(int id);
	
		
	

}
