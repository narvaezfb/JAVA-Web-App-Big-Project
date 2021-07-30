/**
 * Author: Fabian Narvaez
 * Name: Deliverable # 4 - UserDA
 * Date: 2021-03-07
 */

package webd4201.NarvaezF;

import java.security.NoSuchAlgorithmException;
import java.sql.*;
//import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Vector;

public class UserDA {
	
	//class attributes
		/**
		 * user object
		 */
		static User aUser;
		
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
		 * id for user
		 */
		static long id;
		/**
		 * password for user
		 */
		static String password;
		/**
		 * first name for user
		 */
		static String firstName;
		/**
		 * last name for user
		 */
		static String lastName;
		/**
		 * email address for user
		 */
		static String emailAddress;
		/**
		 * last access for user
		 */
		static Date lastAccess;
		/**
		 * enrol date for user
		 */
		static Date enrolDate;
		/**
		 * enabled for user
		 */
		static boolean enabled;
		/**
		 * type for user
		 */
		static char type;
		
		
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
		 * Create a new user record in the database
		 * @param aUser
		 * @return
		 * @throws DuplicateException
		 * @throws InvalidPasswordException
		 * @throws InvalidNameException
		 * @throws InvalidUserDataException
		 * @throws SQLException
		 * @throws NotFoundException
		 * @throws NoSuchAlgorithmException
		 */
		public static boolean create(User aUser) throws DuplicateException, InvalidPasswordException, InvalidNameException, InvalidUserDataException, SQLException, NotFoundException, NoSuchAlgorithmException 
		{	
			boolean inserted = false; //insertion success flag
			
			//retrieve the user attribute values
			id = aUser.getId();
			password = aUser.getPassword();
			//encrypts the password
			String encyrptedPassword = User.get_SHA_1_SecurePassword(password);
			
			firstName = aUser.getFirstName();
			lastName = aUser.getLastName();
			emailAddress = aUser.getEmailAddress();
			lastAccess = new java.sql.Date(aUser.getLastAccess().getTime());
			enrolDate = new java.sql.Date(aUser.getEnrolDate().getTime());
			enabled = aUser.isEnabled();
			type = aUser.getType();
			
			//checks if the record already exists in the user table
			try
			{
			retrieve(id);
			throw (new DuplicateException("Problem with creating user record, User Id " + id +" already exists in the system."));
			}//if not the creates a new record
			catch(NotFoundException e) {
			try {
				//prepare statement 
				aPreparedStatement = aConnection.prepareStatement("INSERT INTO Users " +
	        			"(Id, Password, FirstName, LastName, EmailAddress, LastAccess, EnrolDate, Enabled, Type) " +
	        			"VALUES (?,?,?,?,?,?,?,?,?)");

				aPreparedStatement.setInt(1,(int)id);
				aPreparedStatement.setString(2,encyrptedPassword);
				aPreparedStatement.setString(3,firstName);
				aPreparedStatement.setString(4,lastName);
				aPreparedStatement.setString(5,emailAddress);
				aPreparedStatement.setDate(6, (java.sql.Date) lastAccess);
				aPreparedStatement.setDate(7,(java.sql.Date) enrolDate);
				aPreparedStatement.setBoolean(8,enabled);
				aPreparedStatement.setString(9,String.valueOf(type));
				
				//execute prepare statement 
				inserted= (aPreparedStatement.executeUpdate() == 1);
				
				}catch (SQLException ee)
				{ System.out.println(ee);	}		
			}

			//return true or false 
			return inserted;
		}
		
		/**
		 * Retrieves a user from the database
		 * @param userID
		 * @return
		 * @throws NotFoundException
		 * @throws InvalidPasswordException
		 * @throws InvalidNameException
		 * @throws InvalidUserDataException
		 */		
		public static User retrieve(long userID) throws NotFoundException, InvalidPasswordException, InvalidNameException, InvalidUserDataException  {
			
			//declare a new user object 
			 aUser = null;
			 
			 //prepare statement 
			 try {
				
				 aPreparedStatement = aConnection.prepareStatement("SELECT Id, Password, FirstName,"
				 		+ " LastName, EmailAddress, LastAccess, EnrolDate, Enabled, Type"
				 		+ " FROM Users WHERE Id = ?");
				
				 aPreparedStatement.setInt(1, (int) userID );		
				 
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
					 
					 //creates a new user object 
	                 try{
	                     aUser = new User(id, password, firstName, lastName, emailAddress, lastAccess, enrolDate, enabled, type);
	                 }catch (InvalidIdException e){ 
	                	 System.out.println("Record for " + id + " contains an invalid Id number.  Verify and correct.");
	                	 }                
	             } 
				 //not found in the database 
				 else{throw (new NotFoundException("Problem retrieving User Id number " + userID +" does not exist in the system."));}			 
				 rs.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			 
			 //returns the new user object with the values of the record
			return aUser; 	
		}
		
		/**
		 * Update a user from the database
		 * @param myUser
		 * @return the number of records updated
		 * @throws NotFoundException
		 * @throws InvalidPasswordException
		 * @throws InvalidNameException
		 * @throws InvalidUserDataException
		 * @throws SQLException
		 * @throws NoSuchAlgorithmException
		 */
		public static int update(User myUser) throws NotFoundException, InvalidPasswordException, InvalidNameException, InvalidUserDataException, SQLException, NoSuchAlgorithmException{
			
			//variables 
			int records = 0;
			
			//values that will be updated
			id = myUser.getId();
			password = myUser.getPassword();
			//encrypt the password 
			String encyrptedPassword = User.get_SHA_1_SecurePassword(password);
			firstName = myUser.getFirstName();
			lastName = myUser.getLastName();
			emailAddress = myUser.getEmailAddress();
			lastAccess = new java.sql.Date(myUser.getLastAccess().getTime());
			enrolDate = new java.sql.Date(myUser.getEnrolDate().getTime());
			enabled = myUser.isEnabled();
			type = myUser.getType();
			
			
			//statement for update the records
			String Mysql = "UPDATE Users Set Password ='"+ encyrptedPassword +"',FirstName='"+ firstName +"'"
							+ ",LastName='"+ lastName +"',EmailAddress='"+ emailAddress+"',LastAccess='"+lastAccess+"',"
							+ "EnrolDate='"+enrolDate+"',Enabled='"+enabled+"',Type='"+type+"' Where Id = '"+ id +"'";

			//check first if the record exists in the database
			try {
				Student.retrieve(id);
				//execute the statements.
				records= aStatement.executeUpdate(Mysql);
				
			//if record not found then throw an exception
			}catch(NotFoundException e)	{	
					
				throw new NotFoundException("User with Id " + id 
						+ " cannot be updated, does not exist in the system.");
			}
			catch(SQLException e){
				System.out.println(e);
			}
			
			//return the number of records updated
			return records;
			
		}
		
		/**
		 * delete a user from the database
		 * @param myUser
		 * @return the number of records deleted
		 * @throws NotFoundException
		 * @throws InvalidPasswordException
		 * @throws InvalidNameException
		 * @throws InvalidUserDataException
		 */
		public static int delete(User myUser) throws NotFoundException, InvalidPasswordException, InvalidNameException, InvalidUserDataException {
			
			//to store the number of records updated
			int records = 0;
			id = myUser.getId();
			
			//sql statements for execution
			String anotherSQL ="DELETE FROM Users WHERE Id = '"+id+"'";
				
			try {
				
				//check if the student exists in the table students 
				Student.retrieve(id);
				
				//execute the sql statements
				records += aStatement.executeUpdate(anotherSQL);
				
			}catch(NotFoundException e) {
				
				//if the record is not found
				throw new NotFoundException("User with Id " + id 
						+ " cannot be deleted, does not exist in the system.");
			}			
			catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
					
			return records;
		}
		
}
