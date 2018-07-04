package mylas.com.erp.demo.daoimpl;

import java.util.List;
import java.util.Map;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.criterion.Restrictions;
import org.hibernate.service.ServiceRegistry;
import org.springframework.stereotype.Repository;

import mylas.com.erp.demo.EmpDetails;
import mylas.com.erp.demo.TblEmpLeavereq;
import mylas.com.erp.demo.appservices.GetSession;
import mylas.com.erp.demo.dao.EmpLeaveRequestDao;


@Repository("ers")
public class EmpLeaveRequestService implements EmpLeaveRequestDao {




	@Override
	public void save(TblEmpLeavereq empleave) {
		Session session = GetSession.buildSession().getSessionFactory().getCurrentSession();
		session.beginTransaction();
		session.save(empleave);
		System.out.println("Request Saved");
		session.getTransaction().commit();
	}

	@Override
	public List<TblEmpLeavereq> view() {
		Session session = GetSession.buildSession().getSessionFactory().getCurrentSession();
		session.beginTransaction();
		Query q = session.createQuery("from TblEmpLeavereq");
		List<TblEmpLeavereq> empleave = q.list();
		System.out.println("view Called");
		session.getTransaction().commit();
		return (empleave);
	}

	@Override
	public List<TblEmpLeavereq> viewbyid(String empid) {
		String sqlquery = "FROM TblEmpLeavereq WHERE employeeid ='"+empid+"'";
		Session session = GetSession.buildSession().getSessionFactory().getCurrentSession();
		session.beginTransaction();
		List<TblEmpLeavereq> map = null;
		Query query = session.createQuery(sqlquery);
		List<TblEmpLeavereq> leaves = query.list();
		session.getTransaction().commit();
		return leaves;

	}
	public List<TblEmpLeavereq> viewbyManagerid(String mgrid){
		String sqlquery = "SELECT * FROM tbl_emp_leavereq WHERE managerid ='"+mgrid+"'";
		Session session = GetSession.buildSession().getSessionFactory().getCurrentSession();
		session.beginTransaction();
		List<TblEmpLeavereq> map = null;

		SQLQuery query = session.createSQLQuery(sqlquery);
		query.addEntity(TblEmpLeavereq.class);
		List<TblEmpLeavereq> leaves = query.list();
		session.getTransaction().commit();
		return leaves;

	}
	public List<TblEmpLeavereq> viewbyStatusid(Boolean statusid){
		String sqlquery = "SELECT * FROM tbl_emp_leavereq WHERE status ='"+statusid+"'";
		Session session = GetSession.buildSession().getSessionFactory().getCurrentSession();
		session.beginTransaction();
		List<TblEmpLeavereq> map = null;

		SQLQuery query = session.createSQLQuery(sqlquery);
		query.addEntity(TblEmpLeavereq.class);
		List<TblEmpLeavereq> leaves = query.list();
		System.out.println("list according to status");
		session.getTransaction().commit();
		return leaves;

	}

	@Override
	public void edit() {
		// TODO Auto-generated method stub

	}

	@Override
	public TblEmpLeavereq view(int id) {
		Session session = GetSession.buildSession().getSessionFactory().getCurrentSession();
		session.beginTransaction();
		TblEmpLeavereq empleave = session.load(TblEmpLeavereq.class, id);
		session.getTransaction().commit();
		return empleave;
	}

	@Override
	public String delete(int id) {
		Session session = GetSession.buildSession().getSessionFactory().getCurrentSession();
		session.beginTransaction();
		TblEmpLeavereq empleave = session.load(TblEmpLeavereq.class, id);
		session.delete(empleave);
		System.out.println("Object Deleted successfully.....!!");
		session.getTransaction().commit();
		return "Deleted_Entry!";
	}

	@Override
	public String update(int id, String reason, boolean status) {
		Session session = GetSession.buildSession().getSessionFactory().getCurrentSession();
		session.beginTransaction();
		TblEmpLeavereq empleave = session.load(TblEmpLeavereq.class, id);
		empleave.setReason(reason);
		empleave.setStatus(status);
		session.update(empleave);
		session.getTransaction().commit();
		System.out.println("updated table");
		return "Request";
	}

	@Override
	public String updatetransManager(int id,String transmanid) {
		Session session = GetSession.buildSession().getSessionFactory().getCurrentSession();
		session.beginTransaction();
		TblEmpLeavereq employe = session.load(TblEmpLeavereq.class, id);
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
		TblEmpLeavereq employe = session.load(TblEmpLeavereq.class, id);
		employe.setMantrans(null);
		try {
			session.update(employe);session.getTransaction().commit();return "Updated";
		}catch(Exception e){			session.getTransaction().rollback();
return "error occured while updating";}

	}

	@Override
	public int countEmployee(String manid) {
		Session session = GetSession.buildSession().getSessionFactory().getCurrentSession();
		  session.beginTransaction();
		  Criteria crit = session.createCriteria(TblEmpLeavereq.class);
		  crit.add( Restrictions.isNull("status"));
		  crit.add(Restrictions.ilike("managerid", manid));
		  List<TblEmpLeavereq> emps = crit.list();
		  session.getTransaction().commit();
		return emps.size();
	}

	@Override
	public List<TblEmpLeavereq> viewSearch(String username, String month, String status) {
		Session session = GetSession.buildSession().getSessionFactory().getCurrentSession();
		session.beginTransaction();
		Query q = null;
		if(username!="" && month!="" && status!="") {

			if(status.equals("1")||status.equals("0")) {
				q= session.createQuery("from TblEmpLeavereq where employeeid='"+username+"'AND fromdate LIKE '%"+month+"%' AND status="+status);
			}else {
				q=session.createQuery("from TblEmpLeavereq where employeeid='"+username+"'AND fromdate LIKE '%"+month+"%' AND status is null");
			}
		}
		else if(username!="" && month!="") {
			q=session.createQuery("from TblEmpLeavereq where employeeid='"+username+"'AND fromdate LIKE '%"+month+"%' AND status is null");
		}
		else if(username!="" && status!="") {
			if(status.equals("1")||status.equals("0")) {
				q= session.createQuery("from TblEmpLeavereq where employeeid='"+username+"'AND status="+status);
			}else {
				q=session.createQuery("from TblEmpLeavereq where employeeid='"+username+"'AND status is null");
			}
		}
		else if(month!="" && status!="") {
			if(status.equals("1")||status.equals("0")) {
				q= session.createQuery("from TblEmpLeavereq where fromdate LIKE '%"+month+"%' AND status="+status);
			}else {
				q=session.createQuery("from TblEmpLeavereq where fromdate LIKE '%"+month+"%' AND status is null");
			}

		}
		else if(username!="")
		{

			q = session.createQuery("from TblEmpLeavereq where employeeid='"+username+"'");	
		}
		else if(month!="")
		{

			q = session.createQuery("from TblEmpLeavereq where fromdate LIKE '%"+month+"%'");	
		}
		else if(status!="")
		{
			if(status.equals("1")||status.equals("0")) {
				q= session.createQuery("from TblEmpLeavereq where status="+status);
			}else {
				q=session.createQuery("from TblEmpLeavereq where status is null");
			}
		}
		List<TblEmpLeavereq> empleave = q.list();
		session.getTransaction().commit();
		return (empleave);
	}

	@Override
	public List<TblEmpLeavereq> empLeaveSearch(String uname, String month, String status) {
		Session session = GetSession.buildSession().getSessionFactory().getCurrentSession();
		session.beginTransaction();
		System.out.println("start");
		Query q = null;
		if(uname!="" && month!="" && status!="") {

			if(status.equals("1")||status.equals("0")) {
				q= session.createQuery("from TblEmpLeavereq where employeeid='"+uname+"'AND fromdate LIKE '%"+month+"%' AND status="+status);
			}else {
				q=session.createQuery("from TblEmpLeavereq where employeeid='"+uname+"'AND fromdate LIKE '%"+month+"%' AND status is null");
			}
		}
		else if(uname!="" && month!="") {
			System.out.println("inside");
			System.out.println(month	);
			q=session.createQuery("from TblEmpLeavereq where employeeid='"+uname+"'AND fromdate LIKE '%"+month+"%'");
		}
		else if(uname!="" && status!="") {
			if(status.equals("1")||status.equals("0")) {
				q= session.createQuery("from TblEmpLeavereq where employeeid='"+uname+"'AND status="+status);
			}else {
				q=session.createQuery("from TblEmpLeavereq where employeeid='"+uname+"'AND status is null");
			}
		}
		else if(month!="" && status!="") {
			if(status.equals("1")||status.equals("0")) {
				q= session.createQuery("from TblEmpLeavereq where fromdate LIKE '%"+month+"%' AND status="+status);
			}else {
				q=session.createQuery("from TblEmpLeavereq where fromdate LIKE '%"+month+"%' AND status is null");
			}

		}
		else if(uname!="")
		{

			q = session.createQuery("from TblEmpLeavereq where employeeid='"+uname+"'");	
		}
		else if(month!="")
		{

			q = session.createQuery("from TblEmpLeavereq where fromdate LIKE '%"+month+"%'");	
		}
		else if(status!="")
		{
			if(status.equals("1")||status.equals("0")) {
				q= session.createQuery("from TblEmpLeavereq where status="+status);
			}else {
				q=session.createQuery("from TblEmpLeavereq where status is null");
			}
		}
		List<TblEmpLeavereq> empleave = q.list();
		System.out.println(empleave);
		session.getTransaction().commit();
		return (empleave);	
	}

}
