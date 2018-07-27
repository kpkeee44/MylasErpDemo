package mylas.com.erp.demo.dao;



import java.util.List;

import mylas.com.erp.demo.TblManRoleTransfer;

public interface RoleTrasforDao {
	public String save(TblManRoleTransfer roletransfor);
	public void updateStatus(int id);
	public void deleteDetails(int id);
	public List<TblManRoleTransfer> viewAll();
	public String returnToMainManager(String manid , int id);
	public String changeMainManager(String frommanid, String tomanid, int id);
	}
