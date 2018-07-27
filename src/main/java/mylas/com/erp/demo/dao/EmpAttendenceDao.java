package mylas.com.erp.demo.dao;

import java.util.List;

import mylas.com.erp.demo.TblDepartment;
import mylas.com.erp.demo.TblEmpLeavereq;
import mylas.com.erp.demo.TblEmpattendanceNew;
import mylas.com.erp.demo.TblLeavestatus;
import mylas.com.erp.demo.procedures.Attendance;

public interface EmpAttendenceDao {
	/*public void save(TblEmpAttendanceNew tbl);
	public void update(Boolean status,int id);
	public String delete(int id);
	public List<TblEmpAttendanceNew> viewbyid(String empid);
	public List<TblEmpAttendanceNew> viewbymanagerid(String manpid);
	public List<TblEmpAttendanceNew> getDetails();
	public String updatetransManager(int id,String tramsmanid);
	public String ChangeManager(int id);
	public int countEmployee(String managerid);
	public List<TblEmpAttendanceNew> viewSearch(String firstname, String lastname, String month, String status); 
	public List<TblEmpAttendanceNew> Search(String month, String status,String id);*/
	/*------------------------------new methods-----------------*/
	public String saveEmpAttendence(int mgrid,int monthid,int empid,int yearid,int cby,int uby,int date1,int date2,int date3,int date4,int date5,int date6,int date7,int date8,int date9,int date10,int date11,int date12,int date13,int date14,int date15,int date16,int date17,int date18,int date19,int date20,int date21,int date22,int date23,int date24,int date25,int date26,int date27,int date28,int date29,int date30,int date31,int status);public List<Attendance> view(int id);
	public List<TblLeavestatus> getLeavestatus();

}
