package mylas.com.erp.demo.service;

import java.util.List;

import javax.persistence.PersistenceException;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.stereotype.Repository;

import mylas.com.erp.demo.TblDepartment;
import mylas.com.erp.demo.appservices.GetSession;
import mylas.com.erp.demo.dao.DepartmentDao;

@Repository("deptdao")
public class DepartmentService implements DepartmentDao {


	
	
	@Override
	 public String saveDepartment(TblDepartment tbl) {
	  Session session = GetSession.buildSession().getSessionFactory().getCurrentSession();
	  try {
	   session.beginTransaction();
	   int num = (Integer) session.save(tbl);

	   if(num!=0) {
	    System.out.println("Department added successfully!....");
	    session.getTransaction().commit();
	    return "Department added successfully!....";
	   }else {
	   
	    /*   session.getTransaction().commit();
	     */   return "Department already exists";
	   }

	  }catch(ConstraintViolationException e) {
	   System.out.println("Duplicate Entry");
	   session.getTransaction().rollback();
	   return "Department already exists";
	  }
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
	  public String updateDetails(int id,String newDep,String toDate) {
	   Session session = GetSession.buildSession().getSessionFactory().getCurrentSession();
	   try {
	   session.beginTransaction();
	   TblDepartment deptdel = session.load(TblDepartment.class, id);
	     deptdel.setDepartment(newDep);
	     if(toDate!="") {
	      deptdel.setTodate(toDate);
	      deptdel.setActivestate(false);}
	   session.saveOrUpdate(deptdel);
	   System.out.println("saveor updated");
	   session.getTransaction().commit();
	   System.out.println("commited");
	   return "Department UpDated Successfully";
	   }catch(ConstraintViolationException e) {
	    System.out.println("exception");
	    session.getTransaction().rollback();
	    return "Department is Already Exists.Please try Again";
	   }catch(PersistenceException e){                                                       
	    System.out.println("this is PersistenceException exception throw");   
	    session.getTransaction().rollback();
	    return "Department is Already Exists.Please try Again";
	          }
	}
}
