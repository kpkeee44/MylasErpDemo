package mylas.com.erp.demo.dto;

public class Services {

	String ServiceRole;
	String ServiceID;
	String ServiceName;
	String ServiceDescription;
	private boolean active = true;
	public String getServiceRole() {
		return ServiceRole;
	}
	public void setServiceRole(String serviceRole) {
		ServiceRole = serviceRole;
	}
	public String getServiceID() {
		return ServiceID;
	}
	public void setServiceID(String serviceID) {
		ServiceID = serviceID;
	}
	public String getServiceName() {
		return ServiceName;
	}
	public void setServiceName(String serviceName) {
		ServiceName = serviceName;
	}
	public String getServiceDescription() {
		return ServiceDescription;
	}
	public void setServiceDescription(String serviceDescription) {
		ServiceDescription = serviceDescription;
	}
	public boolean isActive() {
		return active;
	}
	public void setActive(boolean active) {
		this.active = active;
	}
	public Services(String serviceRole, String serviceID, String serviceName, String serviceDescription,
			boolean active) {
		super();
		ServiceRole = serviceRole;
		ServiceID = serviceID;
		ServiceName = serviceName;
		ServiceDescription = serviceDescription;
		this.active = active;
	}
	public Services() {
		super();
		// TODO Auto-generated constructor stub
	}
		
}
