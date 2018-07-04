package mylas.com.erp.demo.daoimpl;

import java.util.List;

import javax.persistence.PersistenceException;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.stereotype.Repository;

import mylas.com.erp.demo.Holidays;
import mylas.com.erp.demo.appservices.GetSession;
import mylas.com.erp.demo.dao.HolidayDao;

@Repository("himpl")
public class HolidayDaoImpl implements HolidayDao {

	@Override
	public String saveHoliday(Holidays hday) {
		Session session = GetSession.buildSession().getSessionFactory().getCurrentSession();
		try {
			session.beginTransaction();
			int num = (Integer) session.save(hday);

			if(num!=0) {
				System.out.println("Holiday added successfully!....");
				session.getTransaction().commit();
				return "Holiday added successfully!....";
			}else {
			
				/*			session.getTransaction().commit();
				 */			return "Holiday already exists";
			}

		}catch(ConstraintViolationException e) {
			System.out.println("Duplicate Entry");
			session.getTransaction().rollback();
			return "Holiday already exists";
		}
	}

	@Override
	public void deleteHoliday(int id) {
		Session session = GetSession.buildSession().getSessionFactory().getCurrentSession();
		session.beginTransaction();

		Holidays deptdel = session.load(Holidays.class, id);
		session.delete(deptdel);
		System.out.println("Object Deleted successfully.....!!");
		session.getTransaction().commit(); 	
	}

	@Override
	public List<Holidays> viewAll() {
		Session session = GetSession.buildSession().getSessionFactory().getCurrentSession();
		session.beginTransaction();
		Query q = session.createQuery("from Holidays");
		List<Holidays> empatt = q.list();
		session.getTransaction().commit();
		return empatt;

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
	
		
	}

	@Override
	public Holidays getHolidayById(int id) {
		Session session = GetSession.buildSession().getSessionFactory().getCurrentSession();
		session.beginTransaction();
		Holidays deptdel = session.get(Holidays.class, id);
		session.getTransaction().commit();
		return deptdel;
	}





}
