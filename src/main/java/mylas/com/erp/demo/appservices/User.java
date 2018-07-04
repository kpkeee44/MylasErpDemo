package mylas.com.erp.demo.appservices;

import java.util.List;

import mylas.com.erp.demo.EmpDetails;
import mylas.com.erp.demo.exceptions.UserBlockedException;

public interface User {

	public static final boolean Login_Status_Active = true;
	public static final boolean Login_Status_Blocked = false;
	
	public static final boolean Role_Admin = false;
	public static final boolean Role_User = true;
	
	public static final boolean Emp_Role = true;
	public static final boolean Man_Role = false;
	
	public void Register(EmpDetails emp);
	
	public List<EmpDetails> Login(String loginName, String Password) throws UserBlockedException;
	
	public List<EmpDetails> getEmpList();
	
	public List getAllAttendanceList();
	
	public void applyLeave();
	
	public void approveLeave(int empid, boolean leavestatus);
	
	
}
