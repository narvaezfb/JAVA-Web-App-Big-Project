package webd4201.NarvaezF;
/**
 * @author Fabian Narvaez
 * @name Deliverable 2 - Not Found Exception class
 * @date 2021-01-12
 */

public class NotFoundException extends Exception {

	/**
	 * Default serial Version UID
	 */
	private static final long serialVersionUID = 1L;
	//Instance attribute
	/**
	 * instance attribute for message 
	 */
	private String message;
	
	
	/**
	 * Parameterized constructor
	 * @param message of the exception
	 */
	public NotFoundException(String message) {
		super();
		this.message = message;
	}
	
	/**
	 * Default constructor
	 */
	public NotFoundException() {
		super();
	}

	/**
	 * @return the message
	 */
	public String getMessage() {
		return message;
	}
	/**
	 * @param message the message to set
	 */
	public void setMessage(String message) {
		this.message = message;
	}
	
	
	
	

}
