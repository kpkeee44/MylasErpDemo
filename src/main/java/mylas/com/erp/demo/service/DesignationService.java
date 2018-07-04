package mylas.com.erp.demo.service;

import java.util.List;

import javax.persistence.PersistenceException;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.stereotype.Repository;

import mylas.com.erp.demo.TblDepartment;
import mylas.com.erp.demo.TblDesignation;
import mylas.com.erp.demo.appservices.GetSession;
import mylas.com.erp.demo.dao.DesignationDao;

@Repository("designationImpl")
public class DesignationService implements DesignationDao {


	@Override
	 public String saveDetails(TblDesignation tbldesg) {
	  Session session = GetSession.buildSession().getSessionFactory().getCurrentSession();
	  try {
	  session.beginTransaction();
	  int num = (Integer) session.save(tbldesg);
	    if(num!=0) {
	   System.out.println("Designation added successfully!...");
	   session.getTransaction().commit();
	   return "Designation added successfully!...";
	  }else {
		  
	       return "Designation already exists";
	  }
	  }catch(ConstraintViolationException e) {
		  System.out.println("Duplicate Entry");
		  session.getTransaction().rollback();
		  return "Designation already exists";
		 }
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
	 public String updateDetails(int id, String newDep,String newDep1,String todate) {

	  Session session = GetSession.buildSession().getSessionFactory().getCurrentSession();
	  try {
	  session.beginTransaction();
	  TblDesignation deptdel = session.load(TblDesignation.class, id);
	  deptdel.setDesignation(newDep);
	  deptdel.setDepartment(newDep1);
	  if(todate!="") {
	   deptdel.setTodate(todate);
	   deptdel.setActivestate(false);
	  }
	  session.saveOrUpdate(deptdel);
	  System.out.println("saveor updated");
	  session.getTransaction().commit();
	  System.out.println("commited");
	  return "Designation UpDated Successfully";
	  }catch(ConstraintViolationException e) {
	   System.out.println("exception");
	   session.getTransaction().rollback();
	   return "Designation already exists.Please try Again";
	  }catch(PersistenceException e){                                                       
	   System.out.println("this is PersistenceException exception throw");   
	   session.getTransaction().rollback();
	   return "Designation already exists.Please try Again";
	         }

	  
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
