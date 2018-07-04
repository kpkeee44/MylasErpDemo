package mylas.com.erp.demo.service;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.springframework.stereotype.Repository;

import mylas.com.erp.demo.EmpDetails;
import mylas.com.erp.demo.TblDepartment;
import mylas.com.erp.demo.appservices.GetSession;
import mylas.com.erp.demo.dao.DepartmentDao;

@Repository("deptdao")
public class DepartmentService implements DepartmentDao {


	
	
	@Override
	public void saveDepartment(TblDepartment tbl) {
		Session session = GetSession.buildSession().getSessionFactory().getCurrentSession();
		session.beginTransaction();

		Integer num = (Integer) session.save(tbl);
		session.getTransaction().commit();
		
	}

	@Override
	public List<TblDepartment> getDetails() {
		Session session = GetSession.buildSession().getSessionFactory().getCurrentSession();
		session.beginTransaction();

		Query q = session.createQuery("from TblDepartment");
		List<TblDepartment> emp1 = q.list();
		session.getTransaction().commit();
		return (emp1);
	}

	@Override
	public void updateDetails(TblDepartment tbl) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteDetails(int id) {
		Session session = GetSession.buildSession().getSessionFactory().getCurrentSession();
		session.beginTransaction();

		TblDepartment deptdel = session.load(TblDepartment.class, id);
		session.delete(deptdel);
		System.out.println("Object Deleted successfully.....!!");
		session.getTransaction().commit();
	
		
	}
	
	@Override
	 public TblDepartment getById(int id) {
	  Session session = GetSession.buildSession().getSessionFactory().getCurrentSession();
	  session.beginTransaction();

	  TblDepartment deptdel = session.get(TblDepartment.class, id);
	  session.getTransaction().commit();
	  return deptdel;
	 }
	
	@Override
	 public void updateDetails(int id,String newDep,String toDate) {
	  Session session = GetSession.buildSession().getSessionFactory().getCurrentSession();
	  session.beginTransaction();
	  TblDepartment deptdel = session.load(TblDepartment.class, id);
	  deptdel.setDepartment(newDep);
	  if(toDate!="") {
		  deptdel.setTodate(toDate);
		  deptdel.setActivestate(false);

	  }
	  
	  session.saveOrUpdate(deptdel);
	  session.getTransaction().commit();
	 }






}
