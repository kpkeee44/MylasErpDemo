package mylas.com.erp.demo.daoimpl;

import java.util.Date;
import java.util.List;

import javax.persistence.ParameterMode;
import javax.persistence.PersistenceException;
import javax.persistence.StoredProcedureQuery;

import org.hibernate.Session;
import org.hibernate.exception.ConstraintViolationException;
import org.hibernate.query.Query;


import mylas.com.erp.demo.TblDepartment;
import mylas.com.erp.demo.Tblleavestype;
import mylas.com.erp.demo.appservices.GetSession;
import mylas.com.erp.demo.appservices.HibernateUtil;
import mylas.com.erp.demo.dao.LeavesTypeDao;

	public class LeavesTypeDaoImpl implements LeavesTypeDao {

		@Override
		public String saveLeaveType(Tblleavestype lt) {
			
				Session session = GetSession.buildSession().getSessionFactory().getCurrentSession();
				try {
					session.beginTransaction();
					 int num = (Integer)session.save(lt);
					 if(num!=0) {
						System.out.println(" added leavetype successfully!....");
						session.getTransaction().commit();
						return "leavetype added successfully!....";
						}else {
							/*			session.getTransaction().commit();
							 */			return "Entered Leavetype already exists";
						}

					}catch(ConstraintViolationException e) {
						System.out.println("Duplicate Entry");
						session.getTransaction().rollback();
						return "Entered Leavetype already exists";
					}
		}

		@Override
		 public Tblleavestype getById(int id) {
		  Session session = GetSession.buildSession().getSessionFactory().getCurrentSession();
		  session.beginTransaction();

		  Tblleavestype getlt = session.get(Tblleavestype.class, id);
		  session.getTransaction().commit();
		  return getlt;
		 }
		
		@Override
		public List<Tblleavestype> viewAll() {
			Session session = GetSession.buildSession().getSessionFactory().getCurrentSession();
			session.beginTransaction();
			Query q = session.createQuery("from Tblleavestype");
			List<Tblleavestype> tlt = q.list();
			session.getTransaction().commit();
			return tlt;

		}

		@Override
		public void deleteLeaveType(int id) {
			
				Session session = GetSession.buildSession().getSessionFactory().getCurrentSession();
				session.beginTransaction();

				Tblleavestype ltdel = session.load(Tblleavestype.class, id);
				session.delete(ltdel);
				System.out.println("Object Deleted successfully.....!!");
				session.getTransaction().commit(); 	
			}


/*
		@Override
		public String updateLeaveType(int id,String leavetype,String eid,String active) throws org.hibernate.exception.ConstraintViolationException{
		System.out.println();
			Session session = GetSession.buildSession().getSessionFactory().getCurrentSession();
			try {
			session.beginTransaction();
			Tblleavestype tlt = session.load(Tblleavestype.class, id);
			tlt.setLeavetype(leavetype);
			tlt.setUpdatedby(eid);
			System.out.println(active);
			if(active.equals("true")) {
				tlt.setIsactive(true);
			}else {tlt.setIsactive(false);}
			
			tlt.setUpdateddate(new Date());
			
			session.saveOrUpdate(tlt);
			System.out.println("saveor updated");
			session.getTransaction().commit();
			System.out.println("commited");
			return "LeaveType UpDated Successfully";
			}catch(ConstraintViolationException e) {
				System.out.println("exception");
				session.getTransaction().rollback();
				return "LeaveType is Already Exists.Please try Again";
			}catch(PersistenceException e){                                                       
				System.out.println("this is PersistenceException exception throw");   
				session.getTransaction().rollback();
				return "LeaveType is Already Exists.Please try Again";
	         }
		
			
		}
*/
		@Override
		public Tblleavestype getLeaveTypeById(int id) {

				Session session = GetSession.buildSession().getSessionFactory().getCurrentSession();
				session.beginTransaction();
				Tblleavestype tlt = session.get(Tblleavestype.class, id);
				session.getTransaction().commit();
				return tlt;
			}

@Override
public String updateLeaveType(int id, String leavetype, String eid, String active) {
	// TODO Auto-generated method stub
	return null;
}

@Override
public String saveLeave(int id, String ltype, String active, int cby, int uby) {

	try(Session  s=HibernateUtil.getSessionFactory().openSession())
	{StoredProcedureQuery query=s.createStoredProcedureQuery("sp_insupdLeavetype");
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
query.setParameter(2,ltype);
if(active.equals("false")) {query.setParameter(3,false);}
else {query.setParameter(3,true);}
query.setParameter(4,cby);
query.setParameter(5,new Date());
query.setParameter(6,uby);
query.setParameter(7,new Date());
query.execute();

int a=(int) query.getOutputParameterValue(8);
System.out.println(a);
	}
	catch(Exception e)
	{
		return "Leavetype already exists";
	}
return "updated successfully";

}
			
		}
