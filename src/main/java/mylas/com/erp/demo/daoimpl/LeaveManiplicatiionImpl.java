package mylas.com.erp.demo.daoimpl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.PersistenceException;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.stereotype.Repository;

import mylas.com.erp.demo.Holidays;
import mylas.com.erp.demo.TblEmpLeavereq;
import mylas.com.erp.demo.Tblleaves;
import mylas.com.erp.demo.Tblleavestype;
import mylas.com.erp.demo.appservices.GetSession;
import mylas.com.erp.demo.dao.LeaveManiplication;

@Repository("leave")
public class LeaveManiplicatiionImpl implements LeaveManiplication {

	@Override
	public List<Tblleavestype> getDetails() {
		Session session = GetSession.buildSession().getSessionFactory().getCurrentSession();
		session.beginTransaction();
		Query q = session.createQuery("from Tblleavestype");
		List<Tblleavestype> leavededails = q.list();
		session.getTransaction().commit();
		return leavededails;
	}

	@Override
	public String save(Tblleaves empleave) {
		Session session = GetSession.buildSession().getSessionFactory().getCurrentSession();
		try {
			session.beginTransaction();
			int num = (Integer) session.save(empleave);

			if(num!=0) {
				System.out.println("Holiday added successfully!....");
				session.getTransaction().commit();
				return "LeaveType added successfully!....";
			}else {
			
				/*			session.getTransaction().commit();
				 */			return "LeaveType already exists";
			}

		}catch(ConstraintViolationException e) {
			System.out.println("Duplicate Entry");
			session.getTransaction().rollback();
			return "LeaveType already exists";
		}
			
		}

	@Override
	public List<Tblleaves> getDetailsofleavetye() {
		Session session = GetSession.buildSession().getSessionFactory().getCurrentSession();
		session.beginTransaction();
		Query q = session.createQuery("from Tblleaves");
		List<Tblleaves> leavenum = q.list();
		session.getTransaction().commit();
		return leavenum;
	}

	@Override
	public Tblleaves getDetaisById(int id) {
		Session session = GetSession.buildSession().getSessionFactory().getCurrentSession();
		session.beginTransaction();
		Tblleaves tbldays = session.get(Tblleaves.class, id);
		session.getTransaction().commit();
		return tbldays;
		
	}
	@Override
	public String updateLeave(int id,Tblleaves leave) throws org.hibernate.exception.ConstraintViolationException {
		Session session = GetSession.buildSession().getSessionFactory().getCurrentSession();
		try {
		session.beginTransaction();
		Tblleaves deptdel = session.load(Tblleaves.class, id);
		deptdel.setLeavetype(leave.getLeavetype());
		deptdel.setNumleavedays(leave.getNumleavedays());
		session.saveOrUpdate(deptdel);
		System.out.println("saveor updated");
		session.getTransaction().commit();
		System.out.println("commited");
		return "UpDated Successfully";
		}catch(ConstraintViolationException e) {
			System.out.println("exception");
			session.getTransaction().rollback();
			return "LeaveType is Already Exists.Please try Again";
		}catch(PersistenceException e){                                                       
			System.out.println("this is PersistenceException exception throw");   
			session.getTransaction().rollback();
			return "Leave is Already Exists.Please try Again";
         }
	
		
	}

	@Override
	public void deleteByid(int id) {
		// TODO Auto-generated method stub
		Session session = GetSession.buildSession().getSessionFactory().getCurrentSession();
		session.beginTransaction();

		Tblleaves ltdel = session.load(Tblleaves.class, id);
		session.delete(ltdel);
		System.out.println("Object Deleted successfully.....!!");
		session.getTransaction().commit(); 	
		
	}
}
