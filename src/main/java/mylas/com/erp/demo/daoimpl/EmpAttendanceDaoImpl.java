package mylas.com.erp.demo.daoimpl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

/*import mylas.com.erp.demo.TblEmpAttendanceNew;*/
import mylas.com.erp.demo.TblEmpLeavereq;
import mylas.com.erp.demo.appservices.GetSession;
import mylas.com.erp.demo.dao.EmpAttendenceDao;

@Repository("attimpl")
public class EmpAttendanceDaoImpl implements EmpAttendenceDao {




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
*/


}
