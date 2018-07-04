package mylas.com.erp.demo.appservices;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;



public class GetSession {
	
	private static GetSession instance = new GetSession();
	
	static Session session=null;
	private SessionFactory sessionFactory;
	private GetSession() {
		this.sessionFactory = buildSessionFactory();
	}
	private synchronized static SessionFactory buildSessionFactory() {
		
	    return new Configuration().configure().buildSessionFactory();
	}
	public static GetSession buildSession() {
	    if(instance == null){
	        return new GetSession();
	    }
	    return instance;
	}
	public SessionFactory getSessionFactory() {
		
	    return sessionFactory;
	}


	
}
