package mylas.com.erp.demo.operations;


public class LoginOperations {

	private String LoginName;
	private String LoginPassword;
	public String getLoginName() {
		return LoginName;
	}
	public void setLoginName(String loginName) {
		LoginName = loginName;
	}
	public String getLoginPassword() {
		return LoginPassword;
	}
	public void setLoginPassword(String loginPassword) {
		LoginPassword = loginPassword;
	}
	public LoginOperations(String loginName, String loginPassword) {
		super();
		LoginName = loginName;
		LoginPassword = loginPassword;
	}
	public LoginOperations() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
}
