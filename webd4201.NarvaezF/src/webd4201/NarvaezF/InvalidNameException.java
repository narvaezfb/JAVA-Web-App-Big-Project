package webd4201.NarvaezF;
/**
 * @author Fabian Narvaez
 * @name Deliverable 1 - InvalidNameException Class
 * @date 2021-01-12
 */


public class InvalidNameException extends Exception {
	//attributes
	private static final long serialVersionUID = 1L;
	private String message;
	
	/**
	 * Parametirized Constructor
	 * @param message
	 */
	public InvalidNameException(String message) {
		super();
		this.message = message;
	}

	/**
	 * accessor for message
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
