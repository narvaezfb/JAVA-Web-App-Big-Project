package webd4201.NarvaezF;
import java.security.NoSuchAlgorithmException;
import java.sql.*;

/**
 * @author Fabian Narvaez
 * @name Deliverable -1 Student Class
 * @date 2021-01-12
 * @last modified: 2021-01-27
 */
//import Date
import java.util.Date;
import java.util.Vector;

public class Student extends User {
	
	//class attributes
	/**
	 * It stores the default program code
	 */
	static final String DEFAULT_PROGRAM_CODE = "UNDC";
	/**
	 * It stores the default program description
	 */
	static final String DEFAULT_PROGRAM_DESCRIPTION = "Undeclared";
	/**
	 * It stores the default year
	 */
	static final int DEFAULT_YEAR = 1;
	//// DA static methods, you DO NOT need to be a Student object to do these
	/**
	 * Initialize method for Initialize the database
	 * @param c
	 */
	public static void initialize(Connection c)
	{StudentDA.initialize(c);}
	
	/**
	 * Terminate the connection
	 */
	public static void terminate()
	{StudentDA.terminate();}
	
	/**
	 * This method return a record from the database 
	 * @param Id
	 * @return a record from the database based on the student id provided
	 * @throws NotFoundException
	 * @throws InvalidPasswordException
	 * @throws InvalidNameException
	 * @throws InvalidUserDataException
	 */
	public static Student retrieve(long Id) throws NotFoundException, InvalidPasswordException, InvalidNameException, InvalidUserDataException
	{return StudentDA.retrieve(Id);}
	
	/**
	 * 
	 * @param Id
	 * @param password
	 * @return
	 * @throws NoSuchAlgorithmException
	 * @throws NotFoundException
	 * @throws InvalidPasswordException
	 * @throws InvalidNameException
	 * @throws InvalidUserDataException
	 */
	public static Student authenticate(long Id, String password) throws NoSuchAlgorithmException, NotFoundException, InvalidPasswordException, InvalidNameException, InvalidUserDataException {
		return StudentDA.authenticate(Id, password);
	}
	
	/**
	 * 
	 * @param password
	 * @return
	 * @throws NotFoundException
	 * @throws InvalidPasswordException
	 * @throws InvalidNameException
	 * @throws InvalidUserDataException
	 */
	public static boolean findPassword(String password) throws NotFoundException, InvalidPasswordException, InvalidNameException, InvalidUserDataException {
		return StudentDA.findPassword(password);
	}
	
	/**
	 * 
	 * @param id
	 * @return
	 */
	public static boolean isExistingLogin(long id)
    { return StudentDA.isExistingLogin(id);}
	//instance attributes
	/**
	 * This method creates a new record of student in the database
	 * @throws DuplicateException
	 * @throws SQLException 
	 * @throws InvalidUserDataException 
	 * @throws InvalidNameException 
	 * @throws InvalidPasswordException 
	 * @throws NotFoundException 
	 * @throws NoSuchAlgorithmException 
	 */
	
	// DA instance method, you need to be a Customer object to do these
	public void create() throws DuplicateException, NotFoundException, InvalidPasswordException, InvalidNameException, InvalidUserDataException, SQLException, NoSuchAlgorithmException
	{StudentDA.create(this);}
	/**
	 * This method deletes a student record from the database
	 * @throws NotFoundException
	 * @throws InvalidUserDataException 
	 * @throws InvalidNameException 
	 * @throws InvalidPasswordException 
	 */
	public void delete() throws NotFoundException, InvalidPasswordException, InvalidNameException, InvalidUserDataException
	{StudentDA.delete(this);}
	/**
	 * This method updates a student record in the database
	 * @throws NotFoundException
	 * @throws SQLException 
	 * @throws InvalidUserDataException 
	 * @throws InvalidNameException 
	 * @throws InvalidPasswordException 
	 * @throws NoSuchAlgorithmException 
	 */
	public void update() throws NotFoundException, InvalidPasswordException, InvalidNameException, InvalidUserDataException, SQLException, NoSuchAlgorithmException
	{StudentDA.update(this);}
	
	
	/**
	 * instance attribute for program code
	 */
	private String programCode;
	/**
	 * instance attribute for program description
	 */
	private String programDescription;
	/**
	 * instance attribute for year
	 */
	private int year;
	/**
	 * a new vector instance attribute for marks
	 */
	private static Vector<Mark> marks = new Vector<Mark>();
	
	
	/**
	 * Parameterized constructor
	 * @param id
	 * @param password
	 * @param firstName
	 * @param lastName
	 * @param emailAddress
	 * @param lastAccess
	 * @param enrolDate
	 * @param enabled
	 * @param type
	 * @param programCode
	 * @param programDescription
	 * @param year
	 * @param marks
	 * @throws InvalidPasswordException 
	 * @throws InvalidIdException 
	 * @throws InvalidNameException 
	 * @throws InvalidUserDataException 
	 */
	public Student(long id, String password, String firstName, String lastName, String emailAddress, Date lastAccess,
			Date enrolDate, boolean enabled, char type, String programCode, String programDescription, int year,
			Vector<Mark> marks) throws InvalidIdException, InvalidPasswordException, InvalidNameException, InvalidUserDataException {
		//calling the parent class constructor
		super(id, password, firstName, lastName, emailAddress, lastAccess, enrolDate, enabled, type);
		//set the student attributes using the set Methods
		setProgramCode(programCode);
		setProgramDescription(programDescription);
		setYear(year);
		setMarks(marks);
	}
	/**
	 * Overloaded Constructor
	 * @param id
	 * @param password
	 * @param firstName
	 * @param lastName
	 * @param emailAddress
	 * @param lastAccess
	 * @param enrolDate
	 * @param enabled
	 * @param type
	 * @param programCode
	 * @param programDescription
	 * @param year
	 * @throws InvalidPasswordException 
	 * @throws InvalidIdException 
	 * @throws InvalidNameException 
	 * @throws InvalidUserDataException 
	 */
	public Student(long id, String password, String firstName, String lastName, String emailAddress, Date lastAccess,
			Date enrolDate, boolean enabled, char type, String programCode, String programDescription, int year) throws InvalidIdException, InvalidPasswordException, InvalidNameException, InvalidUserDataException {
		//calling the above constructor by using the this keyword 
		this(id, password, firstName, lastName, emailAddress, lastAccess, enrolDate, enabled, type, programCode, programDescription, year, marks);
		
	}
	
	/**
	 * Default Constructor
	 * @throws InvalidPasswordException 
	 * @throws InvalidIdException 
	 * @throws InvalidNameException 
	 * @throws InvalidUserDataException 
	 */
	public Student() throws InvalidIdException, InvalidPasswordException, InvalidNameException, InvalidUserDataException {
		//calling the parameterized constructor that does not contain marks vector as a argument
		this(DEFAULT_ID, DEFAULT_PASSWORD, DEFAULT_FIRST_NAME, DEFAULT_LAST_NAME, DEFAULT_EMAIL_ADDRESS, new Date(), new Date(), DEFAULT_ENABLED_STATUS, DEFAULT_TYPE, DEFAULT_PROGRAM_CODE, DEFAULT_PROGRAM_DESCRIPTION, DEFAULT_YEAR );
	}
	/**
	 * override the toString Method to display the data of student
	 */
	@Override
	public String toString(){
       
		//return a string with all the attributes of student
		 return "Student infor for: " 
				 +"\n" + getFirstName() + " " + getLastName()
				 + "\nCurrently in: " + getYear() +" year of " + getProgramDescription() + " (" + getProgramCode() + ")"
				 + "\nEnrolled:  " + getEnrolDate();
	 }
	/**
	 * accessor for program code
	 * @return the programCode
	 */
	public String getProgramCode() {
		return programCode;
	}
	/**
	 * mutator for program code
	 * @param programCode the programCode to set
	 */
	public void setProgramCode(String programCode) {
		this.programCode = programCode;
	}
	/**
	 * accessor for program description
	 * @return the programDescription
	 */
	public String getProgramDescription() {
		return programDescription;
	}
	/**
	 * mutator for program description
	 * @param programDescription the programDescription to set
	 */
	public void setProgramDescription(String programDescription) {
		this.programDescription = programDescription;
	}
	/**
	 * accessor for year
	 * @return the year
	 */
	public int getYear() {
		return year;
	}
	/**
	 * mutator for year
	 * @param year the year to set
	 */
	public void setYear(int year) {
		this.year = year;
	}
	/**
	 * accesor for marks
	 * @return the marks
	 */
	public Vector<Mark> getMarks() {
		return marks;
	}
	/**
	 * mutator for marks
	 * @param marks the marks to set
	 */
	@SuppressWarnings("static-access")
	public void setMarks(Vector<Mark> marks) {
		this.marks = marks;
	}
	
	

}
