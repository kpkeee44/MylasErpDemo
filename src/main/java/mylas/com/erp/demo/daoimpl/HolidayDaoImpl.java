package mylas.com.erp.demo.daoimpl;

import java.util.Date;
import java.util.List;

import javax.persistence.ParameterMode;
import javax.persistence.StoredProcedureQuery;

import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import mylas.com.erp.demo.TblHolidays;
import mylas.com.erp.demo.appservices.GetSession;
import mylas.com.erp.demo.appservices.HibernateUtil;
import mylas.com.erp.demo.dao.HolidayDao;

@Repository("himpl")
public class HolidayDaoImpl implements HolidayDao {

	@Override
	public String saveHoliday(int id,String name,String dt,String active,int eid,Date cdt,int upby,Date update) {
		System.out.println(id+" 1"+name+" 2"+dt+"3 "+active+"4 "+eid+"5 "+cdt+"5 "+upby+"6 "+update);
			try(Session  s=HibernateUtil.getSessionFactory().openSession())
			{StoredProcedureQuery query=s.createStoredProcedureQuery("sp_insup_tbl_holidays");
			query.registerStoredProcedureParameter(1,Integer.class, ParameterMode.IN);
			query.registerStoredProcedureParameter(2,String.class, ParameterMode.IN);
			query.registerStoredProcedureParameter(3,String.class, ParameterMode.IN);
			query.registerStoredProcedureParameter(4,Boolean.class, ParameterMode.IN);
			query.registerStoredProcedureParameter(5,Integer.class, ParameterMode.IN);
			query.registerStoredProcedureParameter(6,Date.class, ParameterMode.IN);
			query.registerStoredProcedureParameter(7,Integer.class, ParameterMode.IN);
			query.registerStoredProcedureParameter(8,Date.class, ParameterMode.IN);
			query.registerStoredProcedureParameter(9,Integer.class, ParameterMode.OUT);
			System.out.println(id);
		query.setParameter(1,id);
		query.setParameter(2,name);
		query.setParameter(3,dt);
		if(active.equals("false")) {
			query.setParameter(4,false);}
			else{query.setParameter(4,true);}
		query.setParameter(5,eid);
		query.setParameter(6,new Date());
		query.setParameter(7,upby);
		query.setParameter(8,new Date());
		query.execute();
		
		int a=(int) query.getOutputParameterValue(9);
		System.out.println(a);
			}
			catch(Exception e)
			{
				return "Holiday already exists";
			}
		return "updated successfully";
		}
			

		/*}catch(ConstraintViolationException e) {
			System.out.println("Duplicate Entry");
			session.getTransaction().rollback();
			return "Holiday already exists";
		}*/
	
/*	@Override
	public void deleteHoliday(int id) {
		Session session = GetSession.buildSession().getSessionFactory().getCurrentSession();
		session.beginTransaction();

		Holidays deptdel = session.load(Holidays.class, id);
		session.delete(deptdel);
		System.out.println("Object Deleted successfully.....!!");
		session.getTransaction().commit(); 	
	}

	
	@Override
	public String updateHOliday(int id,Holidays holiday) throws org.hibernate.exception.ConstraintViolationException {
		Session session = GetSession.buildSession().getSessionFactory().getCurrentSession();
		try {
		session.beginTransaction();
		Holidays deptdel = session.load(Holidays.class, id);
		deptdel.setName(holiday.getName());
		deptdel.setHdate(holiday.getHdate());
		session.saveOrUpdate(deptdel);
		System.out.println("saveor updated");
		session.getTransaction().commit();
		System.out.println("commited");
		return "HoliDay UpDated Successfully";
		}catch(ConstraintViolationException e) {
			System.out.println("exception");
			session.getTransaction().rollback();
			return "HoliDay is Already Exists.Please try Again";
		}catch(PersistenceException e){                                                       
			System.out.println("this is PersistenceException exception throw");   
			session.getTransaction().rollback();
			return "HoliDay is Already Exists.Please try Again";
         }
	
		
	}*/

	@Override
	public TblHolidays getHolidayById(int id) {
		Session session = GetSession.buildSession().getSessionFactory().getCurrentSession();
		session.beginTransaction();
		TblHolidays deptdel = session.get(TblHolidays.class, id);
		session.getTransaction().commit();
		return deptdel;
	}



	@Override
	public List<TblHolidays> viewAll() {
		Session session = GetSession.buildSession().getSessionFactory().getCurrentSession();
		session.beginTransaction();
		Query q = session.createQuery("from TblHolidays");
		List<TblHolidays> empatt = q.list();
		session.getTransaction().commit();
		
		return empatt;

	}


}
