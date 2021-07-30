package webd4201.NarvaezF;
/**
 * @author Fabian Narvaez
 * @name Deliverable 1 - User Class
 * @date 2021-01-12
 */
import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

//Implements College Interface
public class User implements CollegeInterface{
	//class attributes
	/**
	 * Default ID for User
	 */
	static final long DEFAULT_ID = 100123456L;
	/**
	 * Default password for User
	 */
	static final String DEFAULT_PASSWORD = "password";
	/**
	 * Minimum password length for User
	 */
	static final byte MINIMUM_PASSWORD_LENGTH  = 8;
	/**
	 *  Maximum password length for User
	 */
	static final byte MAXIMUM_PASSWORD_LENGTH  = 40;
	/**
	 * Default first name for User
	 */
	static final String DEFAULT_FIRST_NAME ="John";
	/**
	 * Default last Name for User
	 */
	static final String DEFAULT_LAST_NAME = "Doe";
	/**
	 * Default email address for User
	 */
	static final String DEFAULT_EMAIL_ADDRESS = " john.doe@dcmail.com";
	/**
	 * Default enabled status for User
	 */
	static final boolean DEFAULT_ENABLED_STATUS = true;
	/**
	 * Default type for User
	 */
	static final char DEFAULT_TYPE = 's';
	/**
	 * Id number length 
	 */
	static final byte ID_NUMBER_LENGTH  = 9;
	/**
	 * Date Format attribute using DateFormat 
	 */
	static final DateFormat DF = DateFormat.getDateInstance(DateFormat. MEDIUM, Locale.CANADA);
	
	//instance attributes
	/**
	 * Id instance attribute for User
	 */
	private long id;
	/**
	 * password instance attribute
	 */
	private String password;
	/**
	 * first Name instance attribute for User 
	 */
	private String firstName;
	/**
	 * last Name instance attribute for User
	 */
	private String lastName;
	/**
	 * email Address instance attribute for User
	 */
	private String emailAddress;
	/**
	 * last Access instance attribute for User
	 */
	private Date lastAccess;
	/**
	 * enroll Date instance attribute for User
	 */
	private Date enrolDate;
	/**
	 * enabled instance attribute for User
	 */
	private boolean enabled;
	/**
	 * type instance attribute for User
	 */
	private char type;
	
	
	/**
	 * User Parameterized Constructor
	 * @param id
	 * @param password
	 * @param firstName
	 * @param lastName
	 * @param emailAddress
	 * @param lastAccess
	 * @param enrolDate
	 * @param enabled
	 * @param type
	 * @throws InvalidIdException 
	 * @throws InvalidPasswordException 
	 * @throws InvalidNameException 
	 * @throws InvalidUserDataException 
	 */
	public User(long id, String password, String firstName, String lastName, String emailAddress, Date lastAccess,
			Date enrolDate, boolean enabled, char type) throws InvalidIdException, InvalidPasswordException, InvalidNameException, InvalidUserDataException {
		super();
		//using the setters methods for the attributes
		//validate ID
		if(verifyId(id) == false)
		{
			throw new InvalidUserDataException("ID Incorrect, the value must between " + MINIMUM_ID_NUMBER + " and " + MAXIMUM_ID_NUMBER);
		}
		//Validate password
		else if(password.length() < MINIMUM_PASSWORD_LENGTH || password.length() > MAXIMUM_PASSWORD_LENGTH)
		{
			throw new InvalidUserDataException("Password Incorrect, the value must be between " + MINIMUM_PASSWORD_LENGTH + " and " + MAXIMUM_PASSWORD_LENGTH + " characters" );
		}
		//validate first Name
		else if( firstName.isEmpty() || lastName.isEmpty())
		{
			throw new InvalidUserDataException("Name Incorrect, the value cannot be empty");
		}
		//Validate Last Name
		else if( isNumeric(firstName) || isNumeric(lastName))
		{
			throw new InvalidUserDataException("Name Incorrect, the value cannot be numeric");
		}
		//set the values with the setter of each attribute 
		else
		{
			setId(id);//set Id
			setPassword(password);//set password
			setFirstName(firstName);//set first name
			setLastName(lastName);// set last name
			setEmailAddress(emailAddress);//set email address
			setLastAccess(lastAccess);//set last access
			setEnrolDate(enrolDate);// set enrolldate
			setEnabled(enabled);//set enabled
			setType(type);//set type
		}

	}

	/**
	 * User Default Constructor
	 * @throws InvalidNameException 
	 * @throws InvalidPasswordException 
	 * @throws InvalidIdException 
	 * @throws InvalidUserDataException 
	 */
	public User() throws InvalidIdException, InvalidPasswordException, InvalidNameException, InvalidUserDataException {
		//Calling the above constructor using this keyword
		this(DEFAULT_ID, DEFAULT_PASSWORD, DEFAULT_FIRST_NAME, DEFAULT_LAST_NAME, DEFAULT_EMAIL_ADDRESS,  new Date(),  new Date(), DEFAULT_ENABLED_STATUS,  DEFAULT_TYPE );
		
		
	}
	/**
	 * Overload the toString method
	 */
	@Override
	 public String toString(){
	      //return the attributes as a string 
		 return "User Info for: " + id 
	        		+ "\n Name " + firstName + " " + lastName + " (" + emailAddress + " )"
	        		+ "\n Created on: " + enrolDate
	        		+ "\n Last Access: " + lastAccess;
	 }
	 /**
	  * dump method that prints the toString method
	  */
	 public void dump() {
		 //print the attributes by calling the toString method
		 System.out.println(toString());		 
	 }
	
	 /**
	  * verify Id Method for User to validate the identification number 
	  */	 
	 static boolean verifyId(long identificationNumber) {
		 //boolean variable
		 boolean isValid = true;
		 //check if the value is between the valid range
		 if(identificationNumber < MINIMUM_ID_NUMBER || identificationNumber > MAXIMUM_ID_NUMBER)
		 {
			 //if not then return false
			 isValid = false;
		 }
		 //else return true
		 else
		 {
			 isValid = true;
		 }
		 
		 //return the boolean
		 return isValid;
		
	 }
	 
	/**
	 * accessor for ID
	 * @return the id of User
	 */
	public long getId() {
		return id;
	}
	/**
	 * mutator for ID 
	 * @param id the id to set
	 * @throws InvalidIdException 
	 */
	public void setId(long id) throws InvalidIdException {
		
		if(verifyId(id) == false)
			throw new InvalidIdException("The Id must be between " + MINIMUM_ID_NUMBER + " and " + MAXIMUM_ID_NUMBER);
		this.id = id;
	}
	/**
	 * accesor for password
	 * @return the password of User
	 */
	public String getPassword() {
		return password;
	}
	/**
	 * mutator for password
	 * @param password the password to set
	 * @throws InvalidPasswordException 
	 */
	public void setPassword(String password) throws InvalidPasswordException {
		
		if(password.length() < MINIMUM_PASSWORD_LENGTH || password.length() > MAXIMUM_PASSWORD_LENGTH )
			throw new InvalidPasswordException("The password must be between " + MINIMUM_PASSWORD_LENGTH + " and " + MAXIMUM_PASSWORD_LENGTH);
		this.password = password;
	}
	/**
	 * accessor for first name
	 * @return the firstName of the User
	 */
	public String getFirstName() {
		return firstName;
	}
	/**
	 * mutator for firstName
	 * This method validates the first name of User by not letting empty values or numeric values
	 * @param firstName the firstName to set
	 * @throws InvalidNameException 
	 */
	public void setFirstName(String firstName) throws InvalidNameException {
		//checks if the values is empty 
		if(firstName.isEmpty())
		{
			//if the value is empty the throw an exception
			throw new InvalidNameException("The value can not be empty");
		}
			//if the value is numeric it also throws an exception
			else if (isNumeric(firstName))
			{
				throw new InvalidNameException("The value can't be numeric");
				
			}
		else
		{
			//set the value to the instance attribute
			this.firstName = firstName;
		}
		
	}
	/**
	 * accessor for last Name
	 * @return the lastName
	 */
	public String getLastName() {
		return lastName;
	}
	/**
	 * mutator for lastName
	 * This method validates the last name of User by not letting empty values or numeric values
	 * @param lastName the lastName to set
	 * @throws InvalidNameException 
	 */
	public void setLastName(String lastName) throws InvalidNameException {
		//checks if the values is empty 
		if(lastName.isEmpty())
		{
			//if the value is empty the throw an exception
			throw new InvalidNameException("The value can not be empty");
		}
		//if the value is numeric it also throws an exception
			else if (isNumeric(lastName))
			{
				throw new InvalidNameException("The value can't be numeric");
				
			}
		else
		{
			//set the value to the instance attribute
			this.lastName = lastName;
		}
	}
	/**
	 * accessor for email Address
	 * @return the emailAddress
	 * 
	 */
	public String getEmailAddress() {
		return emailAddress;
	}
	/**
	 * mutator for email Address
	 * @param emailAddress the emailAddress to set
	 */
	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}
	/**
	 * accessor for lastAccess
	 * @return the lastAccess
	 */
	public Date getLastAccess() {
		return lastAccess;
	}
	
	/**
	 * mutator for lastAccess
	 * @param lastAccess the lastAccess to set
	 */
	public void setLastAccess(Date lastAccess) {
		this.lastAccess = lastAccess;
	}
	/**
	 * accessor for enrolDate
	 * @return the enrolDate
	 */
	public Date getEnrolDate() {
		return enrolDate;
	}
	/**
	 * mutator for enrolDate
	 * @param enrolDate the enrolDate to set
	 */
	public void setEnrolDate(Date enrolDate) {
		this.enrolDate = enrolDate;
	}
	/**
	 * accessor for enabled
	 * @return the enabled
	 */
	public boolean isEnabled() {
		return enabled;
	}
	/**
	 * mutator for enabled
	 * @param enabled the enabled to set
	 */
	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}
	/**
	 * accessor for type
	 * @return the type
	 */
	public char getType() {
		return type;
	}
	/**
	 * mutator for type
	 * @param type the type to set
	 */
	public void setType(char type) {
		this.type = type;
	}
	
	/**
	 * This method will validate the string to not accept numeric values
	 * @param value
	 * @return
	 */
	public static boolean isNumeric(String value) {
		
		//create a boolean variable 
		boolean isValid = false;
		//if the value is not empty we can proceed
		if(value != null && !value.equals(""))
		{
			isValid = true;
			//create an array of characters
			char chars[] = value.toCharArray();
			//pass the array through a loop 
			for (int index = 0; index < chars.length; index ++)
			{
				//check if the value is a digit 
				isValid &= Character.isDigit(chars[index]);
				 if(!isValid)
					 break;
				
			}
		}
		//return the boolean
		return isValid;
		
	}
	/**
	 * implementying the abstract method from the interface
	 */

	@Override
	public String getTypeForDisplay() {
		// TODO Auto-generated method stub
		return null;
	}
	
	/**
	 * Method to convert the password into a SHA-1 encrypted password
	 * @param passwordToHash
	 * @return the generated password 
	 * @throws NoSuchAlgorithmException
	 */
	public static String get_SHA_1_SecurePassword(String passwordToHash) throws NoSuchAlgorithmException{
		
		String generatedPassword = null;
		
		MessageDigest md = MessageDigest.getInstance("SHA-1");
		md.update(passwordToHash.getBytes());
		byte[] bytesOfGeneratedPassword = md.digest();
		generatedPassword = decToHex(bytesOfGeneratedPassword);
		
		//return the generated password
		return generatedPassword;
		
	}
	
	/**
	 * This method will convert the dec to Hex for my password generated method 
	 * @param bytes
	 * @return dec to Hex 
	 */
	public static String decToHex (byte[] bytes) {
		
		String hex ="";
		StringBuilder sb = new StringBuilder();
		
		for(int index = 0; index < bytes.length; index++) {
			
			sb.append(String.format("%02x", bytes[index]));
		}
		hex = sb.toString();
		
		//return the hex
		return hex;
	}
	
}
