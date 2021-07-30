package webd4201.NarvaezF;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;

import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.GregorianCalendar;
/**
 * Servlet implementation class RegisterServlet
 */
@WebServlet("/RegisterServlet")
public class RegisterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

	/**
	 * This post method will create a new student record and store it at the database
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//set up database connection
		
		try {
			//initialize the database connection for user and student
			Connection c = DatabaseConnect.initialize();
			Student.initialize(c);
			User.initialize(c);
			
			//create session
			HttpSession session = request.getSession(true);
			
			//variables 
			//for catching errors	
			Student  aStudent = null;
			long studentId = 0L;
			int studentYear = 0;
			boolean anyErrors = false;
			StringBuffer errorBuffer = new StringBuffer();
			
			//variables to store data from the form
			String firstName = new String();
			String lastName = new String();
			String email = new String();
			String id = new String();
			String password = new String();
			GregorianCalendar cal = new GregorianCalendar();
			Date lastAccess = cal.getTime();
			Date enrolDate = cal.getTime();
			boolean enabled = true;
			char type = 's';
			String programCode = new String();
			String programDescription = new String();
			String year = new String();
			
			
			//getting the data from the form
			firstName = request.getParameter("user_first_name").trim();
			lastName = request.getParameter("user_last_name");
			email = request.getParameter("user_email_address");
			id = request.getParameter("user_ID");
			password = request.getParameter("user_password");
			programCode = request.getParameter("student_program_code");
			programDescription = request.getParameter("student_program_description");
			year = request.getParameter("student_year");
			
			//data validation
			
			//validate first Name
			if(firstName.length() == 0) {
				anyErrors = true;
				errorBuffer.append("<h3 class=\"error\"> You must enter first name.</h3>\n");
			}
			
			//validate Last Name
			if(lastName.length() == 0) {
				anyErrors = true;
				errorBuffer.append("<h3 class=\"error\"> You must enter last name.</h3>\n");
			}
			
			//validate email address
			if(email.length() == 0) {
				anyErrors = true;
				errorBuffer.append("<h3 class=\"error\"> You must enter email Address.</h3>\n");
			}else if(!isValidEmailAddress(email)) {
				anyErrors = true;
				errorBuffer.append("<h3 class=\"error\"> You must enter a valid email Address.</h3>\n");
				session.setAttribute("emailAddress", email);
			}
				
			//validate user ID		
			try {			
				studentId = Long.parseLong(id);
				
				 if(Student.isExistingLogin(studentId)) {
					 anyErrors = true;
					 errorBuffer.append("<h3 class=\"error\"> This user already exists in the database.</h3>\n");
					 session.setAttribute("ID", id);
				 }
					
			}catch(NumberFormatException nfe) {
				anyErrors = true;
				errorBuffer.append("<h3 class=\"error\"> You must enter a numeric ID.</h3>\n");	
				session.setAttribute("ID", id);
				
			}
			
			//validate password
			if(password.length() == 0) {
				anyErrors = true;
				errorBuffer.append("<h3 class=\"error\"> You must enter a password.</h3>\n");	
			}
			
			//validate program code
			if(programCode.length() == 0) {
				
				anyErrors = true;
				errorBuffer.append("<h3 class=\"error\"> You must enter a program Code.</h3>\n");	
			}
			
			//validate program description
			if(programDescription.length() == 0) {
				
				anyErrors = true;
				errorBuffer.append("<h3 class=\"error\"> You must enter a program Description.</h3>\n");	
			}
			
			//validate student year
			try {
				
				studentYear = Integer.parseInt(year);
				
			}catch(NumberFormatException nfe) {
				
				anyErrors = true;
				errorBuffer.append("<h3 class=\"error\"> You must enter a numeric year.</h3>\n");
				session.setAttribute("year", year);
			}
			
			//if there is not errors then proceed to create a new user record
			if(!anyErrors) {
				
				//create a new student object to set a session attribute
				aStudent = new Student(studentId, password, firstName, lastName, email, lastAccess, enrolDate, 
							enabled, type, programCode, programDescription, studentYear);
					
				//create a new student record a store it in the database
				aStudent.create();
				//set the attribute in session
				session.setAttribute("student", aStudent);
				session.setAttribute("errors", "");
				
				//redirect to dashboard after the studet is created
				response.sendRedirect("./dashboard.jsp");				
			}else {
				//otherwise display the errors found and redirect to register page 
				session.setAttribute("errors", errorBuffer.toString());
                response.sendRedirect("./register.jsp");
			}
			
		//catch any exception	
		}catch(Exception e) {
			System.out.println(e);
            String line1="<h2>A network error has occurred!</h2>";
            String line2="<p>Please notify your system " +
                                                    "administrator and check log. "+e.toString()+"</p>";
            formatErrorPage(line1, line2,response);
		}
		 
	}
	/**
	 * This method will display the format error page
	 * @param first
	 * @param second
	 * @param response
	 * @throws IOException
	 */
	public void formatErrorPage( String first, String second,
            HttpServletResponse response) throws IOException
    {
        PrintWriter output = response.getWriter();
        response.setContentType( "text/html" );
        output.println(first);
        output.println(second);
        output.close();
    }
	
	/**
	 * This function will validate the email address
	 * @param email
	 * @return boolean true or false
	 */
	public static boolean isValidEmailAddress(String email) {
		
		//boolean to return
		boolean isValid = true;
		try {
			//use the java jar and check if the email is valid
			InternetAddress emailAddress = new InternetAddress(email);
			emailAddress.validate();
		}catch(AddressException ex) {
			isValid = false;
		}
		
		//return valid or invalid email
		return isValid;
	}

}
