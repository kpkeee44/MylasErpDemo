package mylas.com.erp.demo.service;

import java.util.Date;
import java.util.List;

import javax.persistence.ParameterMode;
import javax.persistence.PersistenceException;
import javax.persistence.StoredProcedureQuery;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.stereotype.Repository;

import mylas.com.erp.demo.TblDepartment;
import mylas.com.erp.demo.TblDesignation;
import mylas.com.erp.demo.appservices.GetSession;
import mylas.com.erp.demo.appservices.HibernateUtil;
import mylas.com.erp.demo.dao.DesignationDao;

@Repository("designationImpl")
public class DesignationService implements DesignationDao {


	@Override
	 public String saveDetails(int id,String dname,String active,int cby,int uby){
		System.out.println(id+" 1\t"+dname+" 2\t"+active+"4\t "+cby+"5\t "+uby);
		try(Session  s=HibernateUtil.getSessionFactory().openSession())
		{StoredProcedureQuery query=s.createStoredProcedureQuery("sp_insup_tbl_designation");
		query.registerStoredProcedureParameter(1,Integer.class, ParameterMode.IN);
		query.registerStoredProcedureParameter(2,String.class, ParameterMode.IN);
		query.registerStoredProcedureParameter(3,Boolean.class, ParameterMode.IN);
		query.registerStoredProcedureParameter(4,Integer.class, ParameterMode.IN);
		query.registerStoredProcedureParameter(5,Date.class, ParameterMode.IN);
		query.registerStoredProcedureParameter(6,Integer.class, ParameterMode.IN);
		query.registerStoredProcedureParameter(7,Date.class, ParameterMode.IN);
		query.registerStoredProcedureParameter(8,Integer.class, ParameterMode.OUT);
		System.out.println(id);
	query.setParameter(1,id);
	query.setParameter(2,dname);
	if(active.equals("false")) {
	query.setParameter(3,false);}
	else{query.setParameter(3,true);}
	query.setParameter(4,cby);
	query.setParameter(5,new Date());
	query.setParameter(6,uby);
	query.setParameter(7,new Date());
	query.execute();
	
	int a=(int) query.getOutputParameterValue(8	);
	System.out.println(a);
		}
		catch(Exception e)
		{
			System.out.println(e);
			return "Designation name already Exits";
		}
	return "updated successfully";
	}
		
	@Override
	public List<TblDesignation> getDetails() {
		Session session = GetSession.buildSession().getSessionFactory().getCurrentSession();
		session.beginTransaction();	
		System.out.println("5aa");
		Query q = session.createQuery("from TblDesignation where isactive=1");
		System.out.println("12");
		List<TblDesignation> emp1 = q.list();
		System.out.println("14");
		session.getTransaction().commit();
		System.out.println("5a");
		return (emp1);
	}

	/*@Override
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
		session.close();
		fact.close();
		
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
*/
	@Override
	public TblDesignation getById(int id) {
		 Session session = GetSession.buildSession().getSessionFactory().getCurrentSession();
		  session.beginTransaction();

		  TblDesignation deptdel = session.get(TblDesignation.class, id);
		  session.getTransaction().commit();
		  return deptdel;
	}

	@Override
	public List<TblDesignation> getDetailsview() {
		Session session = GetSession.buildSession().getSessionFactory().getCurrentSession();
		session.beginTransaction();	
		Query q = session.createQuery("from TblDesignation");
		List<TblDesignation> emp1 = q.list();
		session.getTransaction().commit();
		return (emp1);
	}





}
