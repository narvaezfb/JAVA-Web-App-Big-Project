package webd4201.NarvaezF;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


import java.sql.*;

/**
 * Servlet implementation class LoginServlet
 */
public class LoginServlet extends HttpServlet {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		try {
			
			//set up database connection
			 Connection c = DatabaseConnect.initialize();
			 Student.initialize(c);
			 //create session
			 HttpSession session = request.getSession(true);
			 
			 //variables to store the values passed in the text boxes
			 long studentId = 0L;
			 String id = new String();
			 String password = new String();
			 
			 try {
				 //Get values from text boxes
				 id = request.getParameter("Login");
				 studentId = Long.parseLong(id);
				 password = request.getParameter( "Password" );
				 
				 //authenticate user
				 Student aStudent = Student.authenticate(studentId, password);
						 
				 session.setAttribute("student", aStudent);
				 //empty out any errors if there were some
	             session.setAttribute("errors", "");
	             //redirect the page to dashboard page
	             response.sendRedirect("./dashboard.jsp");
			 }
			 catch( NotFoundException nfe)
	            {
	                //new code == way better, if I do say so myself
	                //sending errors to the page thru the session
	                StringBuffer errorBuffer = new StringBuffer();
	                errorBuffer.append("<strong>Your sign in information is not valid.<br/>");
	                errorBuffer.append("Please try again.</strong>");
	                if(Student.isExistingLogin(studentId)) {
	                	session.setAttribute("id", id);
	                }else {
	                	errorBuffer.append(" Invalid User Id .");
	                	session.setAttribute("id", id);
	                }
	                	
	                	
	                //check if password was incorrect
	               if(!Student.findPassword(password))
	            	   errorBuffer.append(" \nInvalid Password.");
	              
	               
	               
	                session.setAttribute("errors", errorBuffer.toString());
	                response.sendRedirect("./login.jsp");
	            
	            //for the first deliverable you will have to check if there was a problem
	            //with just the password, or login id and password
	            //this will require a special method e.g. public static boolean isExistingLogin(String arg);
	            }
		} catch (Exception e) //not connected
        {
            System.out.println(e);
            String line1="<h2>A network error has occurred!</h2>";
            String line2="<p>Please notify your system " +
                                                    "administrator and check log. "+e.toString()+"</p>";
            formatErrorPage(line1, line2,response);
        }
		
	}
	
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		 doPost(request, response);
	}

	public void formatErrorPage( String first, String second,
            HttpServletResponse response) throws IOException
    {
        PrintWriter output = response.getWriter();
        response.setContentType( "text/html" );
        output.println(first);
        output.println(second);
        output.close();
    }

}
