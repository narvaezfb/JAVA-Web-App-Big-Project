package webd4201.NarvaezF;
/**
 * @author Fabian Narvaez
 * @name Deliverable 1 - Faculty Class
 * @date 2021-01-12
 */
import java.util.Date;
//this class inherits from the User class parent
public class Faculty extends User {
	
	//Class Attributes
	/**
	 * It stores the default school code
	 */
	static final String DEFAULT_SCHOOL_CODE = "SET";
	/**
	 * It stores the default school description
	 */
	static final String DEFAULT_SCHOOL_DESCRIPTION = "School of Engineering & Technology";
	/**
	 * It stores the default office
	 */
	static final String DEFAULT_OFFICE = "H-140";
	/**
	 * It stores the default phone extension
	 */
	static final int DEFAULT_PHONE_EXTENSION = 1234;
	
	
	
	//Instance attributes
	/**
	 * Instance for school code attribute
	 */
	private String schoolCode;
	/**
	 * Instance for school description attribute
	 */
	private String schoolDescription;
	/**
	 * Instance for office attribute
	 */
	private String office;
	/**
	 * Instance for extension attribute
	 */
	private int extension;
	
	
	/**
	 * Parameterized Constructor for faculty
	 * @param id
	 * @param password
	 * @param firstName
	 * @param lastName
	 * @param emailAddress
	 * @param lastAccess
	 * @param enrolDate
	 * @param enabled
	 * @param type
	 * @param schoolCode
	 * @param schoolDescription
	 * @param office
	 * @param extension
	 * @throws InvalidPasswordException 
	 * @throws InvalidIdException 
	 * @throws InvalidNameException 
	 * @throws InvalidUserDataException 
	 */
	public Faculty(long id, String password, String firstName, String lastName, String emailAddress, Date lastAccess,
			Date enrolDate, boolean enabled, char type, String schoolCode, String schoolDescription, String office,
			int extension) throws InvalidIdException, InvalidPasswordException, InvalidNameException, InvalidUserDataException {
		//calling tyhe parent class constructor
		super(id, password, firstName, lastName, emailAddress, lastAccess, enrolDate, enabled, type);
		//set the faculty attributes using the set Methods
		setSchoolCode(schoolCode);
		setSchoolDescription(schoolDescription);
		setOffice(office);
		setExtension(extension);
	}
	/**
	 * Default Constructor for faculty
	 * @throws InvalidNameException 
	 * @throws InvalidPasswordException 
	 * @throws InvalidIdException 
	 * @throws InvalidUserDataException 
	 */
	public Faculty () throws InvalidIdException, InvalidPasswordException, InvalidNameException, InvalidUserDataException {
		//calling the parent default constructor
		super();
		////set the faculty default attributes using the set Methods
		setSchoolCode(DEFAULT_SCHOOL_CODE);
		setSchoolDescription(DEFAULT_SCHOOL_DESCRIPTION);
		setOffice(DEFAULT_OFFICE);
		setExtension(DEFAULT_PHONE_EXTENSION);		
	}
	/**
	 * Implement the abstract method from the College Interface
	 * @return "Faculty"
	 */
	@Override
	public String getTypeForDisplay() {
		//return Faculty as a string
		return "Faculty";
	}
	
	/**
	 * Overide the toString() method to display the faculty info
	 */
	@Override
	public String toString(){
		
		//declare a string variable
		String firstPart = super.toString();
		//replace the word user for faculty by using the replaceAll method
		firstPart = firstPart.replaceAll("User", getTypeForDisplay() );
		
	     //return the data as a one string  
		 return firstPart + "\n" + schoolDescription + " (" + schoolCode + " )" 
				+ "\nOffice: " + office 
				+ "\n" + PHONE_NUMBER + " x"+ extension;
	 }
	/**
	 * Accessor for school Code
	 * @return the schoolCode
	 */
	public String getSchoolCode() {
		return schoolCode;
	}
	/**
	 * mutator for school Code
	 * @param schoolCode the schoolCode to set
	 */
	public void setSchoolCode(String schoolCode) {
		this.schoolCode = schoolCode;
	}
	/**
	 * Accessor for school Description
	 * @return the schoolDescription
	 */
	public String getSchoolDescription() {
		return schoolDescription;
	}
	/**
	 * mutator for school description
	 * @param schoolDescription the schoolDescription to set
	 */
	public void setSchoolDescription(String schoolDescription) {
		this.schoolDescription = schoolDescription;
	}
	/**
	 * accessor for office
	 * @return the office
	 */
	public String getOffice() {
		return office;
	}
	/**
	 * mutator for office
	 * @param office the office to set
	 */
	public void setOffice(String office) {
		this.office = office;
	}
	/**
	 * accessor for extension
	 * @return the extension
	 */
	public int getExtension() {
		return extension;
	}
	/**
	 * mutator for extension
	 * @param extension the extension to set
	 */
	public void setExtension(int extension) {
		this.extension = extension;
	}
	
	
	

}
