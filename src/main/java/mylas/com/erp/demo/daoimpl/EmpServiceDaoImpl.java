package mylas.com.erp.demo.daoimpl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import mylas.com.erp.demo.dao.EmpServicesDao;
import mylas.com.erp.demo.dto.EmpServices;

@Repository("empservicesdao")
public class EmpServiceDaoImpl implements EmpServicesDao{
	
	public static List<EmpServices> services = new ArrayList<EmpServices>();
	static {
		EmpServices empserv = new EmpServices();
		empserv.setActive(true);
		empserv.setServiceID("profile");
		empserv.setServiceName("View Profile");
		empserv.setServiceDescription("Employee Can View Personal Details");
		empserv.setServiceRole("employee");
		services.add(empserv);
		
		empserv = new EmpServices();
		empserv.setActive(true);
		empserv.setServiceID("leave");
		empserv.setServiceName("Leave");
		empserv.setServiceDescription("Employee Can Apply for Leave");
		empserv.setServiceRole("employee");
		services.add(empserv);
		
		empserv = new EmpServices();
		empserv.setActive(true);
		empserv.setServiceID("timesheet");
		empserv.setServiceName("TimeSheet");
		empserv.setServiceDescription("Employee Can Apply for Leave");
		empserv.setServiceRole("employee");
		services.add(empserv);
	}

	@Override
	public List<EmpServices> list() {
		// TODO Auto-generated method stub
		return services;
	}

}
