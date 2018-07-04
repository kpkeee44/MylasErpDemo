package mylas.com.erp.demo.daoimpl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import mylas.com.erp.demo.TblEmpAttendanceNew;
import mylas.com.erp.demo.appservices.GetSession;
import mylas.com.erp.demo.dao.EmpAttendenceDao;

@Repository("attimpl")
public class EmpAttendanceDaoImpl implements EmpAttendenceDao {




	@Override
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
	public List<TblEmpAttendanceNew> viewSearch(String username, String month, String status) {
		Session session = GetSession.buildSession().getSessionFactory().getCurrentSession();
		session.beginTransaction();
		System.out.println("start");
		Query q = null;
		if(username!="" && month!="" && status!="") {

			if(status.equals("1")||status.equals("0")) {
				q= session.createQuery("from TblEmpAttendanceNew where empid='"+username+"'AND month='"+month+"' AND statas="+status);
			}else {
				q=session.createQuery("from TblEmpAttendanceNew where empid='"+username+"'AND month='"+month+"' AND statas is null");
			}
		}
		else if(username!="" && month!="") {
			q=session.createQuery("from TblEmpAttendanceNew where empid='"+username+"'AND month='"+month+"'");
		}
		else if(username!="" && status!="") {
			if(status.equals("1")||status.equals("0")) {
				q= session.createQuery("from TblEmpAttendanceNew where empid='"+username+"'AND statas="+status);
			}else {
				q=session.createQuery("from TblEmpAttendanceNew where empid='"+username+"'AND statas is null");
			}
		}
		else if(month!="" && status!="") {
			if(status.equals("1")||status.equals("0")) {
				q= session.createQuery("from TblEmpAttendanceNew where month='"+month+"' AND statas="+status);
			}else {
				q=session.createQuery("from TblEmpAttendanceNew where month='"+month+"' AND status is null");
			}

		}
		else if(username!="")
		{

			q = session.createQuery("from TblEmpAttendanceNew where empid='"+username+"'");	
		}
		else if(month!="")
		{

			q = session.createQuery("from TblEmpAttendanceNew where month='"+month+"'");	
		}
		else if(status!="")
		{
			if(status.equals("1")||status.equals("0")) {
				q= session.createQuery("from TblEmpAttendanceNew where status="+status);
			}else {
				q=session.createQuery("from TblEmpAttendanceNew where status is null");
			}
		}
		List<TblEmpAttendanceNew> empleave = q.list();
		System.out.println(empleave);
		session.getTransaction().commit();
		return (empleave);		

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
		  List<TblEmpAttendanceNew> empleave = q.list();
		  session.getTransaction().commit();
		  return (empleave); 
	}



}
