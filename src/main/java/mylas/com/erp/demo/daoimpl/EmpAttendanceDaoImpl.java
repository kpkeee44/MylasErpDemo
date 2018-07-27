package mylas.com.erp.demo.daoimpl;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.ParameterMode;
import javax.persistence.StoredProcedureQuery;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.hibernate.internal.SessionImpl;
import org.springframework.stereotype.Repository;

import mylas.com.erp.demo.LeaveAddition;
/*import mylas.com.erp.demo.TblEmpAttendanceNew;*/
import mylas.com.erp.demo.TblEmpLeavereq;
import mylas.com.erp.demo.TblEmpattendanceNew;
import mylas.com.erp.demo.TblLeavestatus;
import mylas.com.erp.demo.appservices.GetSession;
import mylas.com.erp.demo.appservices.HibernateUtil;
import mylas.com.erp.demo.dao.EmpAttendenceDao;
import mylas.com.erp.demo.procedures.Attendance;

@Repository("attimpl")
public class EmpAttendanceDaoImpl implements EmpAttendenceDao {

	
	public String saveEmpAttendence(int mgrid,int monthid,int empid,int yearid,int cby,int uby,int date1,int date2,int date3,int date4,int date5,int date6,int date7,int date8,int date9,int date10,int date11,int date12,int date13,int date14,int date15,int date16,int date17,int date18,int date19,int date20,int date21,int date22,int date23,int date24,int date25,int date26,int date27,int date28,int date29,int date30,int date31,int status)
	{
		System.out.println(monthid+" "+" "+yearid+" "+date28+" "+date29+" "+date30+" "+date31);
	System.out.println("inside method");
	/*System.out.println(mgrid+" "+monthid+"  "+" "+empid+" "+yearid);*/
	try(Session  s=HibernateUtil.getSessionFactory().openSession())
	{StoredProcedureQuery query=s.createStoredProcedureQuery("sp_Insert_Attendance");
	query.registerStoredProcedureParameter(1,Integer.class, ParameterMode.IN);
	query.registerStoredProcedureParameter(2,Integer.class, ParameterMode.IN);
	query.registerStoredProcedureParameter(3,Integer.class, ParameterMode.IN);
	query.registerStoredProcedureParameter(4,Integer.class, ParameterMode.IN);
	query.registerStoredProcedureParameter(5,Integer.class, ParameterMode.IN);
	query.registerStoredProcedureParameter(6,Date.class, ParameterMode.IN);
	query.registerStoredProcedureParameter(7,Integer.class, ParameterMode.IN);
	query.registerStoredProcedureParameter(8,Date.class, ParameterMode.IN);
	query.registerStoredProcedureParameter(9,Integer.class, ParameterMode.IN);
	query.registerStoredProcedureParameter(10,Integer.class, ParameterMode.IN);
	query.registerStoredProcedureParameter(11,Integer.class, ParameterMode.IN);
	query.registerStoredProcedureParameter(12,Integer.class, ParameterMode.IN);
	query.registerStoredProcedureParameter(13,Integer.class, ParameterMode.IN);
	query.registerStoredProcedureParameter(14,Integer.class, ParameterMode.IN);
	query.registerStoredProcedureParameter(15,Integer.class, ParameterMode.IN);
	query.registerStoredProcedureParameter(16,Integer.class, ParameterMode.IN);
	query.registerStoredProcedureParameter(17,Integer.class, ParameterMode.IN);
	query.registerStoredProcedureParameter(18,Integer.class, ParameterMode.IN);
	query.registerStoredProcedureParameter(19,Integer.class, ParameterMode.IN);
	query.registerStoredProcedureParameter(20,Integer.class, ParameterMode.IN);
	query.registerStoredProcedureParameter(21,Integer.class, ParameterMode.IN);
	query.registerStoredProcedureParameter(22,Integer.class, ParameterMode.IN);
	query.registerStoredProcedureParameter(23,Integer.class, ParameterMode.IN);
	query.registerStoredProcedureParameter(24,Integer.class, ParameterMode.IN);
	query.registerStoredProcedureParameter(25,Integer.class, ParameterMode.IN);
	query.registerStoredProcedureParameter(26,Integer.class, ParameterMode.IN);
	query.registerStoredProcedureParameter(27,Integer.class, ParameterMode.IN);
	query.registerStoredProcedureParameter(28,Integer.class, ParameterMode.IN);
	query.registerStoredProcedureParameter(29,Integer.class, ParameterMode.IN);
	query.registerStoredProcedureParameter(30,Integer.class, ParameterMode.IN);
	query.registerStoredProcedureParameter(31,Integer.class, ParameterMode.IN);
	query.registerStoredProcedureParameter(32,Integer.class, ParameterMode.IN);
	query.registerStoredProcedureParameter(33,Integer.class, ParameterMode.IN);
	query.registerStoredProcedureParameter(34,Integer.class, ParameterMode.IN);
	query.registerStoredProcedureParameter(35,Integer.class, ParameterMode.IN);
	query.registerStoredProcedureParameter(36,Integer.class, ParameterMode.IN);
	query.registerStoredProcedureParameter(37,Integer.class, ParameterMode.IN);
	query.registerStoredProcedureParameter(38,Integer.class, ParameterMode.IN);
	query.registerStoredProcedureParameter(39,Integer.class, ParameterMode.IN);
	query.registerStoredProcedureParameter(40,Integer.class, ParameterMode.IN);
			
/*	query.registerStoredProcedureParameter(41,Integer.class, ParameterMode.OUT);*/

	System.out.println(mgrid);
	query.setParameter(1,mgrid);
	query.setParameter(2,monthid);
	query.setParameter(3,empid);
	query.setParameter(4,yearid);
	query.setParameter(5,cby);
	query.setParameter(6,new Date());
	query.setParameter(7,uby);
	query.setParameter(8,new Date());
	query.setParameter(9,date1);
	query.setParameter(10,date2);
	query.setParameter(11,date3);
	query.setParameter(12,date4);
	query.setParameter(13,date5);
	query.setParameter(14,date6);
	query.setParameter(15,date7);
	query.setParameter(16,date8);
	query.setParameter(17,date9);
	query.setParameter(18,date10);
	query.setParameter(19,date11);
	query.setParameter(20,date12);
	query.setParameter(21,date13);
	query.setParameter(22,date14);
	query.setParameter(23,date15);
	query.setParameter(24,date16);
	query.setParameter(25,date17);
	query.setParameter(26,date18);
	query.setParameter(27,date19);
	query.setParameter(28,date20);
	query.setParameter(29,date21);
	query.setParameter(30,date22);
	query.setParameter(31,date23);
	query.setParameter(32,date24);
	query.setParameter(33,date25);
	query.setParameter(34,date26);
	query.setParameter(35,date27);
	query.setParameter(36,date28);
	query.setParameter(37,date29);
	query.setParameter(38,date30);
	query.setParameter(39,date31);
	query.setParameter(40,status);

	System.out.println("after sending parameters");
	
	query.execute();
	System.out.println("after execute");
	/*int a=(int) query.getOutputParameterValue(41);
	System.out.println(a);*/
		}
		catch(Exception e)
		{
			System.out.println(e);
			e.printStackTrace();
			return "TimeSheet Not Submitted";
		}
	return "Timesheet Submitted";
	
}




	
	@Override
	public List<Attendance> view(int id) {
		List<Attendance> data=new ArrayList<>();
		try(Session  session=HibernateUtil.getSessionFactory().openSession()){
			session.beginTransaction();
			CallableStatement cst = null;
	        ResultSet rs = null;
	        SessionImpl sessionImpl = (SessionImpl) session; Connection con = sessionImpl.connection();
	        cst = con.prepareCall("{call sp_get_attendance(?)}");
	        cst.setInt(1, id);
            cst.execute();
            rs = cst.getResultSet();
           /* t1.id,t1.monthid,t1.yearid,t1.createdby,t1.createddate,t1.updatedby,t1.updateddate,t1.day1,t1.day2,t1.day3,t1.day4,t1.day5,t1.day6,t1.day7,t1.day8,
            t1.day9,t1.day10,t1.day11,t1.day12,t1.day13,t1.day14,t1.day15,t1.day16,t1.day17,t1.day18,t1.day19,t1.day20,t1.day21,t1.day22,t1.day23,t1.day24,t1.day25,t1.day26,
            t1.day27,t1.day28,t1.day29,t1.day30,t1.day31,t2.emplfirstname,t2.empllastname,t3.leavestatus */
            while(rs.next()) {
            	Attendance lea=new Attendance();
lea.setId(rs.getInt(1));lea.setMonth(rs.getInt(2));lea.setYear(rs.getInt(3));lea.setCreatedby(rs.getInt(4));lea.setCreateddate(rs.getDate(5));
lea.setUpdatedby(rs.getInt(6));lea.setUpdateddate(rs.getDate(7));
lea.setDay1(rs.getString(8));lea.setDay2(rs.getString(9));lea.setDay3(rs.getString(10));lea.setDay4(rs.getString(11));lea.setDay5(rs.getString(12));
lea.setDay6(rs.getString(13));lea.setDay7(rs.getString(14));lea.setDay8(rs.getString(15));lea.setDay9(rs.getString(16));lea.setDay10(rs.getString(17));
lea.setDay11(rs.getString(18));lea.setDay12(rs.getString(19));lea.setDay13(rs.getString(20));lea.setDay14(rs.getString(21));lea.setDay15(rs.getString(22));
lea.setDay16(rs.getString(23));lea.setDay17(rs.getString(24));lea.setDay18(rs.getString(25));lea.setDay19(rs.getString(26));lea.setDay20(rs.getString(27));
lea.setDay21(rs.getString(28));lea.setDay22(rs.getString(29));lea.setDay23(rs.getString(30));lea.setDay24(rs.getString(31));lea.setDay25(rs.getString(32));
lea.setDay26(rs.getString(33));lea.setDay27(rs.getString(34));lea.setDay28(rs.getString(35));lea.setDay29(rs.getString(36));lea.setDay30(rs.getString(37));
lea.setDay31(rs.getString(38));lea.setEmpfname(rs.getString(39));lea.setEmplname(rs.getString(40));lea.setLeavestatus(rs.getInt(41));
data.add(lea);
    			
            }
	      
			
			if(!data.isEmpty())
			for(Attendance ll:data)
			{
				
				System.out.println(ll.getEmpfname());
			}
			else {
				System.out.println("nodata");
			}
	}catch(Exception e) {
		System.out.println(e+"hi");
	}
		return data;
	}

	@Override
	public List<TblLeavestatus> getLeavestatus() {
		List<TblLeavestatus> userList=null;
		try(Session  session=HibernateUtil.getSessionFactory().openSession()){
			session.beginTransaction();
			System.out.println("123");
			Query Q = session.createSQLQuery("EXEC sp_VWLeaveStatus").addEntity(TblLeavestatus.class);
			System.out.println(Q);
			userList =(List<TblLeavestatus>)Q.list();
			if(!userList.isEmpty())
			for(TblLeavestatus ll:userList)
			{
				System.out.println(ll.getLeavestatus());
			}
			else {
				System.out.println("nodata");
			}
	}catch(Exception e) {
		System.out.println(e);
	}
		
		return userList;
	}




	/*@Override
	public void save(TblEmpAttendanceNew tbl) {

		Session session = GetSession.buildSession().getSessionFactory().getCurrentSession();
		session.beginTransaction();
		session.save(tbl);

		session.getTransaction().commit();

	}

	@Override
	public void update(Boolean status, int id) {
		Session session = GetSession.buildSession().getSessionFactory().getCurrentSession();
		session.beginTransaction();
		TblEmpAttendanceNew emp = session.load(TblEmpAttendanceNew.class,id);

		emp.setStatas(status);
		session.update(emp);
		session.getTransaction().commit();

	}

	@Override
	public String delete(int id) {


		Session session = GetSession.buildSession().getSessionFactory().getCurrentSession();
		session.beginTransaction();
		TblEmpAttendanceNew empdel = session.load(TblEmpAttendanceNew.class, id);
		session.delete(empdel);
		session.getTransaction().commit();
		System.out.println("Object Deleted successfully.....!!");

		return "Object Deleted successfully.....!!";

	}

	@Override
	public List<TblEmpAttendanceNew> getDetails() {
		Session session = GetSession.buildSession().getSessionFactory().getCurrentSession();
		session.beginTransaction();
		Query q = session.createQuery("from TblEmpAttendanceNew");
		List<TblEmpAttendanceNew> empatt = q.list();
		session.getTransaction().commit();
		return empatt;

	}

	@Override
	public List<TblEmpAttendanceNew> viewbyid(String empid) {
		String sqlquery = "FROM TblEmpAttendanceNew WHERE empid ='"+empid+"'";
		Session session = GetSession.buildSession().getSessionFactory().getCurrentSession();
		session.beginTransaction();
		Query query = session.createQuery(sqlquery);
		List<TblEmpAttendanceNew> EmpAtt = query.list();
		session.getTransaction().commit();	
		return EmpAtt;
	}

	@Override
	public List<TblEmpAttendanceNew> viewbymanagerid(String manpid) {
		String sqlquery = "SELECT * FROM tbl_emp_attendance_new WHERE managerid ='"+manpid+"'";
		Session session = GetSession.buildSession().getSessionFactory().getCurrentSession();
		session.beginTransaction();
		SQLQuery query = session.createSQLQuery(sqlquery);
		query.addEntity(TblEmpAttendanceNew.class);
		List<TblEmpAttendanceNew> emps = query.list();
		session.getTransaction().commit();
		return emps;
	}

	@Override
	public String updatetransManager(int id, String transmanid) {
		Session session = GetSession.buildSession().getSessionFactory().getCurrentSession();
		session.beginTransaction();
		TblEmpAttendanceNew employe = session.load(TblEmpAttendanceNew.class, id);
		employe.setMantrans(transmanid);
		try {
			session.update(employe);session.getTransaction().commit();return "Updated";
		}catch(Exception e){			session.getTransaction().rollback();
		return "error occured while updating";}

	}

	@Override
	public String ChangeManager(int id) {
		Session session = GetSession.buildSession().getSessionFactory().getCurrentSession();
		session.beginTransaction();
		TblEmpAttendanceNew employe = session.load(TblEmpAttendanceNew.class, id);
		employe.setMantrans(null);
		try {
			session.update(employe);session.getTransaction().commit();return "Updated";
		}catch(Exception e){			session.getTransaction().rollback();
		return "error occured while updating";}

	}

	@Override
	public int countEmployee(String managerid) {
		Session session = GetSession.buildSession().getSessionFactory().getCurrentSession();
		session.beginTransaction();
		Criteria crit = session.createCriteria(TblEmpAttendanceNew.class);
		crit.add( Restrictions.isNull("statas"));
		crit.add(Restrictions.ilike("managerid", managerid));
		List<TblEmpAttendanceNew> emps = crit.list();		
		session.getTransaction().commit();
		return emps.size();
	}

	@Override
	public List<TblEmpAttendanceNew> viewSearch(String firstname, String lastname, String month, String status) {
		System.out.print(firstname+lastname+month+status);
		Session session = GetSession.buildSession().getSessionFactory().getCurrentSession();
		session.beginTransaction();
		Query q = null;
		
		if(firstname!="" && lastname!="" && month!="" && status!="") {

			if(status.equals("1")||status.equals("0")) {
				q= session.createQuery("from TblEmpAttendanceNew where empid IN (select eid from EmpDetails where fname='"+firstname+"' AND lname='"+lastname+"') AND month LIKE '"+month+"' AND statas="+status);
			}else {
				q=session.createQuery("from TblEmpAttendanceNew where empid IN (select eid from EmpDetails where fname='"+firstname+"' AND lname='"+lastname+"') AND month='"+month+"' AND statas is null");
			}
		}
		else if(firstname!="" && lastname!="" && month!="") {

				q=session.createQuery("from TblEmpAttendanceNew where empid IN (select eid from EmpDetails where fname='"+firstname+"' AND lname='"+lastname+"') AND month='"+month+"'");
			}
		
		else if(firstname!="" && lastname!="" && status!="") {

			if(status.equals("1")||status.equals("0")) {
				q= session.createQuery("from TblEmpAttendanceNew where empid IN (select eid from EmpDetails where fname='"+firstname+"' AND lname='"+lastname+"') AND statas="+status);
			}else {
				q=session.createQuery("from TblEmpAttendanceNew where empid IN (select eid from EmpDetails where fname='"+firstname+"' AND lname='"+lastname+"') AND statas is null");
			}
		}
		else if(firstname!="" && month!="" && status!="") {

			if(status.equals("1")||status.equals("0")) {
				q= session.createQuery("from TblEmpAttendanceNew where  empid IN (select eid from EmpDetails where fname='"+firstname+"') AND month='"+month+"' AND statas="+status);
			}else {
				q=session.createQuery("from TblEmpAttendanceNew where empid IN (select eid from EmpDetails where fname='"+firstname+"') AND month='"+month+"' AND statas is null");
			}
		}
		else if(lastname!="" && month!="" && status!="") {

			if(status.equals("1")||status.equals("0")) {
				q= session.createQuery("from TblEmpAttendanceNew where  empid IN (select eid from EmpDetails where lname='"+lastname+"') AND month='"+month+"' AND statas="+status);
			}else {
				q=session.createQuery("from TblEmpAttendanceNew where empid IN (select eid from EmpDetails where lname='"+lastname+"') AND month='"+month+"' AND statas is null");
			}
		}
		else if(firstname!="" && month!="") {
			System.out.println(month);
			q=session.createQuery("from TblEmpAttendanceNew where empid IN (select eid from EmpDetails where fname='"+firstname+"') AND month='"+month+"'");
		}
		else if(firstname!="" && lastname!="") {
			q=session.createQuery("from TblEmpAttendanceNew where empid IN (select eid from EmpDetails where fname='"+firstname+"' AND lname='"+lastname+"')");
		}
		else if(firstname!="" && status!="") {
			if(status.equals("1")||status.equals("0")) {
				q= session.createQuery("from TblEmpAttendanceNew where empid IN (select eid from EmpDetails where fname='"+firstname+"') AND statas="+status);
			}else {
				q=session.createQuery("from TblEmpAttendanceNew where empid IN (select eid from EmpDetails where fname='"+firstname+"') AND statas is null");
			}
		}
		else if(lastname!="" && month!="") {
			q=session.createQuery("from TblEmpAttendanceNew where empid IN (select eid from EmpDetails where lname='"+lastname+"') AND month='"+month+"'");
		}
		else if(lastname!="" && status!="") {
			if(status.equals("1")||status.equals("0")) {
				q= session.createQuery("from TblEmpAttendanceNew where empid IN (select eid from EmpDetails where lname='"+lastname+"') AND statas="+status);
			}else {
				q=session.createQuery("from TblEmpAttendanceNew where empid IN (select eid from EmpDetails where lname='"+lastname+"') AND statas is null");
			}
		}
		else if(month!="" && status!="") {
			if(status.equals("1")||status.equals("0")) {
				q= session.createQuery("from TblEmpAttendanceNew where month='"+month+"' AND statas="+status);
			}else {
				q=session.createQuery("from TblEmpAttendanceNew where month='"+month+"' AND statas is null");
			}

		}
		else if(firstname!="")
		{

			q = session.createQuery("from TblEmpAttendanceNew where empid IN (select eid from EmpDetails where fname='"+firstname+"')");	
		}
		else if(lastname!="")
		{

			q = session.createQuery("from TblEmpAttendanceNew where empid IN (select eid from EmpDetails where lname='"+lastname+"')");	
		}
		else if(month!="")
		{
			System.out.println(month+"hi");

			q = session.createQuery("from TblEmpAttendanceNew where month ='"+month+"'");
			System.out.println(q);
		}
		else if(status!="")
		{
			if(status.equals("1")||status.equals("0")) {
				q= session.createQuery("from TblEmpAttendanceNew where statas="+status);
			}else {
				q=session.createQuery("from TblEmpAttendanceNew where statas is null");
			}
		}
		List<TblEmpAttendanceNew> attendance = q.list();
		System.out.println(attendance);
		session.getTransaction().commit();
		return (attendance);
	}

	@Override
	public List<TblEmpAttendanceNew> Search(String month, String status, String id) {
		Session session = GetSession.buildSession().getSessionFactory().getCurrentSession();
		  session.beginTransaction();
		  Query q = null;
		  if( month!="" && status!="") {

		   if(status.equals("1")||status.equals("0")) {
		    q= session.createQuery("from TblEmpAttendanceNew where empid='"+id+"'AND month='"+month+"' AND statas="+status);
		   }else {
		    q=session.createQuery("from TblEmpAttendanceNew where empid='"+id+"'AND month='"+month+"' AND statas is null");
		   }
		  }
		  else if(month!="")
		  {

		   q = session.createQuery("from TblEmpAttendanceNew where empid='"+id+"'AND month='"+month+"'"); 
		  }
		  else if(status!="")
		  {
		   if(status.equals("1")||status.equals("0")) {
		    q= session.createQuery("from TblEmpAttendanceNew where empid='"+id+"'AND statas="+status);
		   }else {
		    q=session.createQuery("from TblEmpAttendanceNew where empid='"+id+"'AND statas is null");
		   }
		  }
		  List<TblEmpAttendanceNew> attendence = q.list();
		  System.out.println(attendence+"....hi");
		  session.getTransaction().commit();
		  return (attendence); 
	}


}
*/}