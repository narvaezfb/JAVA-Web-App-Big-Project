package webd4201.NarvaezF;
/**
 * @author Fabian Narvaez
 * @name Deliverable - 2 Student Data Access Class
 * @date 2021-01-27
 */

import java.security.NoSuchAlgorithmException;
import java.sql.*;
//import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Vector;



public class StudentDA {

	//class attributes
	/**
	 * Student object
	 */
	static Student aStudent;
	
	// declare variables for the database connection
	/**
	 * connection object
	 */
	static Connection aConnection;
	/**
	 * statement object
	 */
	static Statement aStatement;
	
	/**
	 * statement object
	 */
	static PreparedStatement aPreparedStatement;
	
	// declare static variables for all Student instance attribute values
	/**
	 * id for student
	 */
	static long id;
	/**
	 * password for student
	 */
	static String password;
	/**
	 * first name for student
	 */
	static String firstName;
	/**
	 * last name for student
	 */
	static String lastName;
	/**
	 * email address for student
	 */
	static String emailAddress;
	/**
	 * last access for student
	 */
	static Date lastAccess;
	/**
	 * enrol date foir student
	 */
	static Date enrolDate;
	/**
	 * enabled for student
	 */
	static boolean enabled;
	/**
	 * type for student
	 */
	static char type;
	/**
	 * program code for student
	 */
	static String programCode;
	/**
	 * program description for student
	 */
	static String programDescription;
	/**
	 * year for student
	 */
	static int year;
	/**
	 * marks for student
	 */
	static Vector<Mark> marks = new Vector<Mark>();
	/**
	 * for database
	 */
	//private static final SimpleDateFormat SQL_DF = new SimpleDateFormat("yyyy-MM-dd");
	
	
	// establish the database connection
	/**
	 * This method initializes the connection between my java program and the database
	 * @param c that is a connection object
	 */
	public static void initialize(Connection c)
	{
		 try {
             aConnection=c;
             aStatement = aConnection.createStatement();
         }
         catch (SQLException e)
         { System.out.println(e);	}
	}
	
	// close the database connection
	/**
	 * close the database connection 
	 */
	public static void terminate()
	{
		try
        { 	// close the statement
			aPreparedStatement.close();
            aStatement.close();
        }
        catch (SQLException e)
        { System.out.println(e);	}
	}
	/**
	 * 
	 * @param id
	 * @return true or false
	 * @throws NotFoundException
	 * @throws InvalidUserDataException 
	 * @throws InvalidNameException 
	 * @throws InvalidPasswordException 
	 */
	public static boolean findPassword(String password) throws NotFoundException, InvalidPasswordException, InvalidNameException, InvalidUserDataException
	{ // retrieve Customer and Boat data
		boolean exists = true;
		try {
			aPreparedStatement = aConnection.prepareStatement("SELECT Users.Id, Password, FirstName,"
			 		+ " LastName, EmailAddress, LastAccess, EnrolDate, Enabled, Type, ProgramCode, "
			 		+ "ProgramDescription, Year FROM Users , Students WHERE Password = ?");
			
			 aPreparedStatement.setString(1, password);
			//execute prepare statement
			 ResultSet rs = aPreparedStatement.executeQuery();
			 
			 //if the query finds a record 
			  exists = rs.next();
		          	 
			 rs.close();   
		} 
		catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return exists;
	}
	
	
	/**
	 * Authenticate method to retrieve student object with correct Id and password
	 * @param studentID
	 * @param password
	 * @return A new student
	 * @throws NotFoundException
	 * @throws InvalidPasswordException
	 * @throws InvalidNameException
	 * @throws InvalidUserDataException
	 * @throws NoSuchAlgorithmException
	 */
	public static Student authenticate (long studentID, String password) throws NotFoundException, InvalidPasswordException, InvalidNameException, InvalidUserDataException, NoSuchAlgorithmException {
		aStudent = null;
		
		try {
			aPreparedStatement = aConnection.prepareStatement("SELECT Users.Id, Password, FirstName,"
			 		+ " LastName, EmailAddress, LastAccess, EnrolDate, Enabled, Type, ProgramCode, "
			 		+ "ProgramDescription, Year FROM Users , Students WHERE Users.Id = Students.Id AND Users.Id = ? AND Password = ?");
			
			 aPreparedStatement.setInt(1, (int) studentID );
			 aPreparedStatement.setString(2, password);
			//execute prepare statement
			 ResultSet rs = aPreparedStatement.executeQuery();
			 
			 //if the query finds a record 
			 boolean gotIt = rs.next();
			 if (gotIt)
             {	// extract the data
				 id = rs.getInt("Id");
				 password = rs.getString("Password");
				 firstName = rs.getString("FirstName");
				 lastName = rs.getString("LastName");
				 emailAddress = rs.getString("EmailAddress");
				 lastAccess = rs.getDate("LastAccess");
				 enrolDate = rs.getDate("EnrolDate");
				 enabled = rs.getBoolean("Enabled");
				 type = rs.getString("Type").charAt(0);
				 programCode = rs.getString("ProgramCode");
				 programDescription = rs.getString("ProgramDescription");
				 year = rs.getInt("Year");
				 
				 //creates a new student object 
                 try{
                     aStudent = new Student(id, password, firstName, lastName, emailAddress, lastAccess, enrolDate, enabled, type, programCode, programDescription, year );
                 }catch (InvalidIdException e){ 
                	 System.out.println("Record for " + id + " contains an invalid Id number.  Verify and correct.");
                	 }
             }
              else{
                	 throw (new NotFoundException("Problem retrieving Student Id number " + studentID +" does not exist in the system or the password is incorrect."));
                 }               	 
			 rs.close();   
		} 
		catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return aStudent;
	}
	/**
	 * Verify if the user exists
	 * @param id
	 * @return
	 */
	public static boolean isExistingLogin(long id)
	{ 
		// retrieve Customer data
		// define the SQL query statement using the id key
		String sqlQuery = "SELECT  * " +
							" FROM Users " +
							" WHERE Id= '" + id +"'";
			//System.out.println(sqlQuery);
		 boolean exists = true;                  
		// execute the SQL query statement
		try
		{
			ResultSet rs = aStatement.executeQuery(sqlQuery);
			exists = rs.next();
		}catch (SQLException e)
		{ 
			System.out.println(e);
		}
		return exists;
	}
	
	/**
	 * This method checks first if the student already exists in the database otherwise inserts a new 
	 * new student record to the database
	 * @param student object
	 * @return return true or false depending if the record was created or not
	 * @throws InvalidUserDataException 
	 * @throws InvalidNameException 
	 * @throws InvalidPasswordException 
	 * @throws NotFoundException 
	 * @throws DuplicateException if the record already exists
	 * @throws SQLException 
	 * @throws NoSuchAlgorithmException 
	 * @throws ParseException 
	 */
	public static boolean create(Student aStudent) throws DuplicateException, InvalidPasswordException, InvalidNameException, InvalidUserDataException, SQLException, NotFoundException, NoSuchAlgorithmException 
	{	
		boolean inserted = false; //insertion success flag
		
		//retrieve the student attribute values
		id = aStudent.getId();
		programCode = aStudent.getProgramCode();
		programDescription = aStudent.getProgramDescription();
		year = aStudent.getYear();
		
		//checks if the record already exists in the student table
		try {	 
			retrieve(id);
			throw (new DuplicateException("Problem with creating Student record, Student Id " + id +" already exists in the system."));
			//if not then creates a new record in the student table
		}catch(NotFoundException e) {		 
			 try {
				 
				 aConnection.setAutoCommit(false);
				 
				 if(UserDA.create(aStudent)) {
					 
					//prepared statement 
					 aPreparedStatement = aConnection.prepareStatement("INSERT INTO Students" + 
					 			   "(Id, ProgramDescription, ProgramCode, Year)" + 
					 			   "VALUES (?,?,?,?)");
			 
					aPreparedStatement.setInt(1,(int)id);
					aPreparedStatement.setString(2,programCode);
				    aPreparedStatement.setString(3,programDescription);
					aPreparedStatement.setInt(4,year);
					
					if(aPreparedStatement.executeUpdate() == 1) {
						inserted = true;
						aConnection.commit();
					}else {
						aConnection.rollback();
					}					
					aConnection.setAutoCommit(true);					 
				 }				
			 }catch(SQLException ee) {
				 System.out.println(ee);
			 }
		 }	
		//return true or false 
		return inserted;
	}
	
	/**
	 * This method retrieves a student record from the database based on the student id provided
	 * @param studentID
	 * @return a student object 
	 * @throws NotFoundException
	 * @throws InvalidPasswordException
	 * @throws InvalidNameException
	 * @throws InvalidUserDataException
	 */
	public static Student retrieve(long studentID) throws NotFoundException, InvalidPasswordException, InvalidNameException, InvalidUserDataException  {
		
		//declare a new student object 
		 aStudent = null;
		 
		 //prepare statement 
		 try {
			
			 aPreparedStatement = aConnection.prepareStatement("SELECT Users.Id, Password, FirstName,"
			 		+ " LastName, EmailAddress, LastAccess, EnrolDate, Enabled, Type, ProgramCode, "
			 		+ "ProgramDescription, Year FROM Users , Students WHERE Users.Id = Students.Id AND Users.Id = ?");
			
			 aPreparedStatement.setInt(1, (int) studentID );		
			 
			 //execute prepare statement
			 ResultSet rs = aPreparedStatement.executeQuery();
			 
			 //if the query finds a record 
			 boolean gotIt = rs.next();
			 if (gotIt)
             {	// extract the data
				 id = rs.getInt("Id");
				 password = rs.getString("Password");
				 firstName = rs.getString("FirstName");
				 lastName = rs.getString("LastName");
				 emailAddress = rs.getString("EmailAddress");
				 lastAccess = rs.getDate("LastAccess");
				 enrolDate = rs.getDate("EnrolDate");
				 enabled = rs.getBoolean("Enabled");
				 type = rs.getString("Type").charAt(0);
				 programCode = rs.getString("ProgramCode");
				 programDescription = rs.getString("ProgramDescription");
				 year = rs.getInt("Year");
				 
				 //creates a new student object 
                 try{
                     aStudent = new Student(id, password, firstName, lastName, emailAddress, lastAccess, enrolDate, enabled, type, programCode, programDescription, year );
                 }catch (InvalidIdException e){ 
                	 System.out.println("Record for " + id + " contains an invalid Id number.  Verify and correct.");
                	 }                
             } 
			 //not found in the database 
			 else{throw (new NotFoundException("Problem retrieving Student Id number " + studentID +" does not exist in the system."));}			 
			 rs.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 
		 //returns the new student object with the values of the record
		return aStudent; 	
	}
	
	/**
	 * This method updates the student record in the database
	 * @param myStudent
	 * @return the number of records updated
	 * @throws NotFoundException
	 * @throws InvalidPasswordException
	 * @throws InvalidNameException
	 * @throws InvalidUserDataException
	 * @throws SQLException
	 * @throws NoSuchAlgorithmException
	 */
	public static int update(Student myStudent) throws NotFoundException, InvalidPasswordException, InvalidNameException, InvalidUserDataException, SQLException, NoSuchAlgorithmException{
		
		//variables 
		int records = 0;
		
		//values tha will be updated
		id = myStudent.getId();
		password = myStudent.getPassword();
		//encrypt the password 
		String encyrptedPassword = User.get_SHA_1_SecurePassword(password);
		firstName = myStudent.getFirstName();
		lastName = myStudent.getLastName();
		emailAddress = myStudent.getEmailAddress();
		lastAccess = new java.sql.Date(myStudent.getLastAccess().getTime());
		enrolDate = new java.sql.Date(myStudent.getEnrolDate().getTime());
		enabled = myStudent.isEnabled();
		type = myStudent.getType();
		programCode = myStudent.getProgramCode();
		programDescription = myStudent.getProgramDescription();
		year = myStudent.getYear();
		
		/*String sql = "UPDATE Users SET Password = ?,"
				+ "FirstName = ?, LastName = ?, EmailAddress = ?, LastAccess = ?, EnrolDate =?,"
				+ "Enabled =?, Type =? WHERE Id = ? ";*/
		
		//statement for update the records
		String Mysql = "UPDATE Users Set Password ='"+ encyrptedPassword +"',FirstName='"+ firstName +"',LastName='"+ lastName +"',EmailAddress='"+ emailAddress+"',LastAccess='"+lastAccess+"',EnrolDate='"+enrolDate+"',Enabled='"+enabled+"',Type='"+type+"' Where Id = '"+ id +"'";
		String myAnotherSQL = "UPDATE Students Set ProgramCode ='"+ programCode +"',ProgramDescription='"+ programDescription +"',Year='"+ year+ "' Where Id = '"+ id +"'";			
		
		//syntax for prepared statement 
		/*aPreparedStatement = aConnection.prepareStatement(sql)
		 aPreparedStatement.setString(1,password);
		 aPreparedStatement.setString(2,firstName);
		 aPreparedStatement.setString(3,lastName);
		 aPreparedStatement.setString(4,emailAddress);
		 aPreparedStatement.setDate(5, (java.sql.Date) lastAccess);
		 aPreparedStatement.setDate(6,(java.sql.Date) enrolDate);
		 aPreparedStatement.setBoolean(7,enabled);
		 aPreparedStatement.setString(8,String.valueOf(type));
		 aPreparedStatement.setInt(9,(int) id);
					
		 records = aPreparedStatement.executeUpdate();
		 aPreparedStatement = aConnection.prepareStatement(myAnotherSQL);
				
		 aPreparedStatement.setString(1,programCode);
		 aPreparedStatement.setString(2,programDescription);
		 aPreparedStatement.setInt(3,year);
		 aPreparedStatement.setInt(4,(int)id);
					
		 records = aPreparedStatement.executeUpdate();	
		 */

		//check first if the record exists in the database
		try {
			Student.retrieve(id);
			//execute the statements.
			records= aStatement.executeUpdate(Mysql);
			records= aStatement.executeUpdate(myAnotherSQL);
			
		//if record not found then throw an exception
		}catch(NotFoundException e)	{	
				
			throw new NotFoundException("Student with Id " + id 
					+ " cannot be updated, does not exist in the system.");
		}
		catch(SQLException e){
			System.out.println(e);
		}
		
		//return the number of records updated
		return records;
		
	}
	
	/**
	 * This method deletes a student record from the database 
	 * @param myStudent
	 * @return the number of records deleted
	 * @throws NotFoundException
	 * @throws InvalidPasswordException
	 * @throws InvalidNameException
	 * @throws InvalidUserDataException
	 */
	public static int delete(Student myStudent) throws NotFoundException, InvalidPasswordException, InvalidNameException, InvalidUserDataException {
		
		//to store the number of records updated
		int records = 0;
		id = myStudent.getId();
		
		//sql statements for execution
		String sql = "DELETE FROM Students WHERE Id = '"+id+"'";
		String anotherSQL ="DELETE FROM Users WHERE Id = '"+id+"'";
		
		//prepared statements
		
		/*aPreparedStatement = aConnection.prepareStatement("DELETE FROM Students WHERE Id = ?");
		
		aPreparedStatement.setInt(1,(int) id);
		
		aPreparedStatement.executeUpdate();
		
		aPreparedStatement = aConnection.prepareStatement("DELETE FROM Users WHERE Id = ?");
			
		aPreparedStatement.setInt(1,(int) id);
			
		aPreparedStatement.executeUpdate();*/
		
		
		try {
			
			//check if the student exists in the table students 
			Student.retrieve(id);
			
			//execute the sql statements
			records = aStatement.executeUpdate(sql);
			records += aStatement.executeUpdate(anotherSQL);
			
		}catch(NotFoundException e) {
			
			//if the record is not found
			throw new NotFoundException("Student with Id " + id 
					+ " cannot be deleted, does not exist in the system.");
		}			
		catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
				
		return records;
	}
	

	
}
