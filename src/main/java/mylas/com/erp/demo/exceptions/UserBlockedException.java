package mylas.com.erp.demo.exceptions;

public class UserBlockedException extends Exception{

	/**
	 * 
	 * Creates user object with out error description
	 */
	public UserBlockedException() {

	}

	public UserBlockedException(String errDisc) {
		
		super(errDisc);

	}
}
