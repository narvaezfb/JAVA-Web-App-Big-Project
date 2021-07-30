package webd4201.NarvaezF;
/**
 * @author Fabian Narvaez
 * @name Deliverable 1 - InvalidUserDataException Class
 * @date 2021-01-12
 */

public class InvalidUserDataException extends Exception {
	//attributes
	private static final long serialVersionUID = 1L;
	private String message;
	

	/**
	 * Parameterized Constructor
	 * @param message
	 */
	public InvalidUserDataException(String message) {
		super();
		this.message = message;
	}

	/**
	 * accessor for messgae
	 * @return the message
	 */
	public String getMessage() {
		return message;
	}

	/**
	 * mutator for message
	 * @param message the message to set
	 */
	public void setMessage(String message) {
		this.message = message;
	}
	
}
