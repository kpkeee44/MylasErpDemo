package mylas.com.erp.demo.daoimpl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import mylas.com.erp.demo.dao.ServicesDao;
import mylas.com.erp.demo.dto.Services;

@Repository("servicesdao")
public class ServicesDaoImpl implements ServicesDao{

	private static List<Services> services = new ArrayList<Services>();
	
	
	static {
		
		Services service = new Services();
		service.setServiceID("allemp");
		service.setServiceName("All Employees");
		service.setServiceDescription("List Of All the Employee");
		service.setServiceRole("admin");
		services.add(service);
		
		service = new Services();
		service.setServiceID("empholidays");
		service.setServiceName("Holidays");
		service.setServiceDescription("Holidays for Employee");
		service.setServiceRole("admin");
		services.add(service);
		
		service = new Services();
		service.setServiceID("leaverequests");
		service.setServiceName("Leave Requests");
		service.setServiceDescription("List Of Employee Leave Requests");
		service.setServiceRole("admin");
		services.add(service);
		
		service = new Services();
		service.setServiceID("employeetimesheets");
		service.setServiceName("Employee Timesheets");
		service.setServiceDescription("List Of Employee Attenedance");
		service.setServiceRole("admin");
		services.add(service);
		
		service = new Services();
		service.setServiceID("empdep");
		service.setServiceName("Department");
		service.setServiceDescription("List Of Departments");
		service.setServiceRole("admin");
		services.add(service);
		
		service = new Services();
		service.setServiceID("empdesig");
		service.setServiceName("Designation");
		service.setServiceDescription("Employee Designation List");
		service.setServiceRole("admin");
		services.add(service);
		
	}
	@Override
	public List<Services> list() {
		// TODO Auto-generated method stub
		return services;
	}

}
