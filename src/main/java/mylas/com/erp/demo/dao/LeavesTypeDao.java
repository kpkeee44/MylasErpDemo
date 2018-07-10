package mylas.com.erp.demo.dao;

	import java.util.List;
	import mylas.com.erp.demo.Tblleavestype;

	public interface LeavesTypeDao {

		public String saveLeaveType(Tblleavestype lt);
		public List<Tblleavestype> viewAll();
		public Tblleavestype getById(int id);
		public void deleteLeaveType(int id);
		public String updateLeaveType(int id,Tblleavestype leavetype);
		public Tblleavestype getLeaveTypeById(int id);
	}
