package mylas.com.erp.demo.appservices;
	import org.hibernate.SessionFactory;
	import org.hibernate.boot.Metadata;
	import org.hibernate.boot.MetadataSources;
	import org.hibernate.boot.registry.StandardServiceRegistry;
	import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

	public class HibernateUtil {
	private static StandardServiceRegistry ssr;
	private static SessionFactory sf;
	static {
		if(sf==null)
		{
			try{
				ssr=new StandardServiceRegistryBuilder().configure().build();
				MetadataSources ms=new MetadataSources(ssr);
				Metadata md=ms.getMetadataBuilder().build();
				sf=md.getSessionFactoryBuilder().build();
				
			
			}catch(Exception e) {
				e.printStackTrace();
				if(ssr!=null) {
					StandardServiceRegistryBuilder.destroy(ssr);
				}
		}
			
	}
	}
	public static SessionFactory getSessionFactory()
	{
		return sf;
	}
	}


