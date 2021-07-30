package webd4201.NarvaezF;
/**
 * @author Fabian Narvaez
 * @name Deliverable 1 - CollegeInterface Interface
 * @date 2021-01-12
 */
//new interface
public interface CollegeInterface {
	//declarations
	/**
	 * It stores the name of the College
	 */
	final String COLLEGE_NAME = "Durham College";
	/**
	 * It stores the phone number
	 */
	final String PHONE_NUMBER = "(905)742-2000";
	/**
	 * it stores the minimum ID number
	 */
	final long MINIMUM_ID_NUMBER = 100000000;
	/**
	 * it stores the maximum ID number
	 */
	final long MAXIMUM_ID_NUMBER = 999999999;
	/**
	 * it stores the minimum password length
	 */
	final byte MINIMUM_PASSWORD_LENGTH = 8;
	/**
	 * it stores the maximum password length
	 */
	final byte MAXIMUM_PASSWORD_LENGTH = 20;
	
	/**
	 * a method header for getTypeForDisplay()
	 * @return
	 */
	
	public abstract String getTypeForDisplay();

}
