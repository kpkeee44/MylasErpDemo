package mylas.com.erp.demo.daoimpl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import mylas.com.erp.demo.dao.ManagerServicesDao;
import mylas.com.erp.demo.dto.EmpServices;
import mylas.com.erp.demo.dto.ManServices;
import mylas.com.erp.demo.dto.Services;

@Repository("mandao")
public class ManServiceDaoImpl implements ManagerServicesDao{

private static List<ManServices> services = new ArrayList<ManServices>();
	
	
	static {
		
		ManServices service = new ManServices();
		service.setServiceID("allemp");
		service.setServiceName("All Employees");
		service.setServiceDescription("List Of All the Employee");
		service.setServiceRole("manager");
		services.add(service); 
		
		
		service = new ManServices();
		service.setServiceID("profile");
		service.setServiceName("Profile");
		service.setServiceDescription("Apply for a Leave");
		service.setServiceRole("manager");		
		services.add(service);
		
		service = new ManServices();
		service.setServiceID("leave");
		service.setServiceName("Leave");
		service.setServiceDescription("Apply for a Leave");
		service.setServiceRole("manager");		
		services.add(service);
		
		
		service = new ManServices();
		service.setServiceID("timesheet");
		service.setServiceName("Time Sheet");
		service.setServiceDescription("List Of All the Employee");
		service.setServiceRole("manager");
		services.add(service);
		
		service = new ManServices();
		service.setServiceID("leaverequests");
		service.setServiceName("Leave Requests");
		service.setServiceDescription("Leave requests for Manager");
		service.setServiceRole("manager");
		services.add(service);
		
		/*service = new ManServices();
		service.setServiceID("allemployee");
		service.setServiceName("All Employee");
		service.setServiceDescription("Manager Leave Requests");
		service.setServiceRole("manager");
		services.add(service);*/
		
		service = new ManServices();
		service.setServiceID("employeetimesheets");
		service.setServiceName("Employee Timesheets");
		service.setServiceDescription("List Of Employee TimeSheets");
		service.setServiceRole("manager");
		services.add(service);
		
		
	}
	
	public List<ManServices> list() {
		return services;
	}


}
