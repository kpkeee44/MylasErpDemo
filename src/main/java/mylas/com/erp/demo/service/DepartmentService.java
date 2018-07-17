package mylas.com.erp.demo.service;

import java.util.Date;
import java.util.List;

import javax.persistence.ParameterMode;
import javax.persistence.StoredProcedureQuery;

import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import mylas.com.erp.demo.TblDepartment;
import mylas.com.erp.demo.appservices.GetSession;
import mylas.com.erp.demo.appservices.HibernateUtil;
import mylas.com.erp.demo.dao.DepartmentDao;

@Repository("deptdao")
public class DepartmentService implements DepartmentDao {


	
	
	
	@Override
	public String saveDepartment(int id,String dname,boolean active,String cby,String uby) {
		
				try(Session  s=HibernateUtil.getSessionFactory().openSession())
				{StoredProcedureQuery query=s.createStoredProcedureQuery("sp_insup_tbl_department");
				query.registerStoredProcedureParameter(1,Integer.class, ParameterMode.IN);
				query.registerStoredProcedureParameter(2,String.class, ParameterMode.IN);
				query.registerStoredProcedureParameter(3,Boolean.class, ParameterMode.IN);
				query.registerStoredProcedureParameter(4,String.class, ParameterMode.IN);
				query.registerStoredProcedureParameter(5,Date.class, ParameterMode.IN);
				query.registerStoredProcedureParameter(6,String.class, ParameterMode.IN);
				query.registerStoredProcedureParameter(7,Date.class, ParameterMode.IN);
				query.registerStoredProcedureParameter(8,Integer.class, ParameterMode.OUT);
			
				
				query.setParameter(1,id);
				query.setParameter(2,dname);
				query.setParameter(3,active);
				query.setParameter(4,cby);
				query.setParameter(5,new Date());
				query.setParameter(6,uby);
				query.setParameter(7,new Date());
		
				int a=(int) query.getOutputParameterValue(8);
				query.execute();
				System.out.println(a);
				
				}catch(Exception e)
				{
					
					System.out.println(e);
				return "Department already exists";
				}
				
				return "Department Updated Successfully";	
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
	public List<TblDepartment> getDetails() {
		Session session = GetSession.buildSession().getSessionFactory().getCurrentSession();
		session.beginTransaction();

		Query q = session.createQuery("from TblDepartment");
		System.out.println("dept");
		List<TblDepartment> emp1 = q.list();
		session.getTransaction().commit();
		return (emp1);
	}
}
	
	 /*@Override
	  public String updateDetails(int id,String newDep,String toDate) {
	  Session session = GetSession.buildSession().getSessionFactory().getCurrentSession();
	   try {
	   session.beginTransaction();
	   TblDepartment deptdel = session.load(TblDepartment.class, id);
	     deptdel.setDepartmentname(newDep);
	     if(toDate!="") {
	      deptdel.setCreateddate(toDate);
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
	          }*/
	
	
