package mylas.com.erp.demo.daoimpl;



import org.hibernate.Query;
import org.hibernate.Session;

import mylas.com.erp.demo.Email;
import mylas.com.erp.demo.EmpDetails;
import mylas.com.erp.demo.appservices.GetSession;
import mylas.com.erp.demo.dao.EmailDao;

public class EmailDaoImpl implements EmailDao {

	@Override
	public Email getMail() {
		System.out.println("asdfsdfdsf");
		
		Session session = GetSession.buildSession().getSessionFactory().getCurrentSession();
		session.beginTransaction();
		System.out.println("asdfdsfdsfdsfdsfdsfdsf");
		Query q = session.createQuery("from Email");
		System.out.println(q);
		System.out.println(q.list());
		Email user =  (Email) q.list().get(0);
		session.getTransaction().commit();
		System.out.println(user);
		return user;
	}

}
