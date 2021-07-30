package webd4201.NarvaezF;
/**
 * @author Fabian Narvaez
 * @name Deliverable 2 - Duplicate Exception Class
 * @date 2021-01-27
 */

public class DuplicateException extends Exception {

	/**
	 * Default serial Version UID
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * instance attribute for message
	 */
	private String message;
	
	/**
	 * Parameterized constructor
	 * @param message of the exception
	 */
	public DuplicateException(String message) {
		super();
		this.message = message;
	}
	
	/**
	 * Default constructor
	 */
	public DuplicateException() {
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
