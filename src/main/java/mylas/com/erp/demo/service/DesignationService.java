package mylas.com.erp.demo.service;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.springframework.stereotype.Repository;

import mylas.com.erp.demo.TblDesignation;
import mylas.com.erp.demo.appservices.GetSession;
import mylas.com.erp.demo.dao.DesignationDao;

@Repository("designationImpl")
public class DesignationService implements DesignationDao {


	@Override
	public void saveDetails(TblDesignation tbldesg) {
		Session session = GetSession.buildSession().getSessionFactory().getCurrentSession();
		session.beginTransaction();
		Integer num = (Integer) session.save(tbldesg);
		session.getTransaction().commit();
		
	}

	@Override
	public List<TblDesignation> getDetails() {
		Session session = GetSession.buildSession().getSessionFactory().getCurrentSession();
		session.beginTransaction();

		Query q = session.createQuery("from TblDesignation");
		List<TblDesignation> emp1 = q.list();
		session.getTransaction().commit();
		return (emp1);
	}

	@Override
	public void updateDetails(TblDesignation tbldesg) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteDetails(int id) {
		Session session = GetSession.buildSession().getSessionFactory().getCurrentSession();
		session.beginTransaction();

		TblDesignation desdel = session.load(TblDesignation.class, id);
		session.delete(desdel);
		System.out.println("Object Deleted successfully.....!!");
		session.getTransaction().commit();
		/*session.close();
		fact.close();*/
		
	}

	@Override
	public void updateDetails(int id, String newDep,String newDep1,String todate) {

		Session session = GetSession.buildSession().getSessionFactory().getCurrentSession();
		session.beginTransaction();
		TblDesignation deptdel = session.load(TblDesignation.class, id);
		
		deptdel.setDesignation(newDep);
		deptdel.setDepartment(newDep1);
		if(todate!="") {
			deptdel.setTodate(todate);
			deptdel.setActivestate(false);
		}
		session.saveOrUpdate(deptdel);
		session.getTransaction().commit();
		
	}

	@Override
	public TblDesignation getById(int id) {
		 Session session = GetSession.buildSession().getSessionFactory().getCurrentSession();
		  session.beginTransaction();

		  TblDesignation deptdel = session.get(TblDesignation.class, id);
		  session.getTransaction().commit();
		  return deptdel;
	}





}
