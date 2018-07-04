package mylas.com.erp.demo.dto;

public class Department {

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getDepName() {
		return depName;
	}
	public void setDepName(String depName) {
		this.depName = depName;
	}
	public String getDepdescription() {
		return depdescription;
	}
	public void setDepdescription(String depdescription) {
		this.depdescription = depdescription;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public boolean isActive() {
		return active;
	}
	public void setActive(boolean active) {
		this.active = active;
	}
/*
 * Private Fields
 */
private int id;
private String depName;
private String depdescription;
private String url;
private boolean active = true;
}