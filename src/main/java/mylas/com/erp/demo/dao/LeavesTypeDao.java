package mylas.com.erp.demo.dao;

	import java.util.Date;
import java.util.List;

import mylas.com.erp.demo.Tblleavestype;

	public interface LeavesTypeDao {

	/*	public String saveLeaveType(Tblleavestype lt);*/
		public List<Tblleavestype> viewAll();
		public Tblleavestype getById(int id);
	/*	public void deleteLeaveType(int id);
		public String updateLeaveType(int id,String leavetype,String eid,String active);*/
		public Tblleavestype getLeaveTypeById(int id);
		/*--------new method by ganga-----------*/
		public String saveLeaveType(int id, String ltype, String active, int cby, int uby);
	}

