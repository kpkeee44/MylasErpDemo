package mylas.com.erp.demo.daoimpl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import mylas.com.erp.demo.Holidays;
import mylas.com.erp.demo.appservices.GetSession;
import mylas.com.erp.demo.dao.HolidayDao;

@Repository("himpl")
public class HolidayDaoImpl implements HolidayDao {

	@Override
	public void saveHoliday(Holidays hday) {
		Session session = GetSession.buildSession().getSessionFactory().getCurrentSession();
		session.beginTransaction();
		System.out.println("save start"); 
		session.save(hday);
		session.getTransaction().commit();
		System.out.println("save obj");
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
	public void updateHOliday(int id,Holidays holiday) {
		Session session = GetSession.buildSession().getSessionFactory().getCurrentSession();
		session.beginTransaction();

		Holidays deptdel = session.load(Holidays.class, id);
		deptdel.setName(holiday.getName());
		deptdel.setDate(holiday.getDate());
		deptdel.setMonth(holiday.getMonth());
		deptdel.setYear(holiday.getYear());
		session.saveOrUpdate(deptdel);
		session.getTransaction().commit();
		
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
