<!-- 
Author:Fabian Narvaez
Name: Register.jsp
Date: 2021-03-21
Last Date modified: 2021-03-21
Description: This is the view for my registration page. It contains a form that will recieve the information of the user
after that it will send the information to the server and the data will be procesed
 -->
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ include file="./header.jsp" %>
<%
	String firstName = (String)session.getAttribute("firstName");
	String lastName = (String)session.getAttribute("lastName");
	String email = (String)session.getAttribute("emailAddress");
	String newID = (String)session.getAttribute("ID");
	String newPassword = (String)session.getAttribute("password");
	String programCode = (String)session.getAttribute("programCode");
	String programDescription = (String)session.getAttribute("programDescription");
	String year = (String)session.getAttribute("year");
	
	String errorMessage = (String)session.getAttribute("errors");
	if(errorMessage == null)
		errorMessage="";
	
	if(firstName == null)
		firstName = "";
	
	if(lastName == null)
		lastName = "";
	
	if(email == null)
		email = "";
	
	if(newID == null)
		newID = "";
	
	if(newPassword == null)
		newPassword = "";
	
	if(programCode == null)
		programCode = "";
	
	if(programDescription == null)
		programDescription = "";
	
	if(year == null)
		year = "";
	
	
%>

<form class="form-register" method="post" action="./Register" >

    <h2>Registration</h2>
    <label for="input_user_first_name" class="sr-only">First Name</label>    
    <input type="text" name="user_first_name" value ="<%= firstName %>"  class="form-control" placeholder="First Name" required autofocus>
    
    <label for="input_user_last_name" class="sr-only">Last Name</label>    
    <input type="text" name="user_last_name" value ="<%= lastName %>" class="form-control" placeholder="Last Name" required autofocus>
    
    <label for="input_user_email_address" class="sr-only">Email</label>    
    <input type="text" name="user_email_address" value ="<%= email %>"  class="form-control" placeholder="Email" required autofocus>
    
    <label for="input_user_ID" class="sr-only">User ID</label>  
    <input type="text" name="user_ID" value ="<%= newID %>"  class="form-control" placeholder="User ID" required autofocus>
      
    <label for="input_password" class="sr-only">Password</label>
    <input type="password" name="user_password" value ="<%= newPassword %>" class="form-control" placeholder="Password" required>
    
    <label for="input_student_program_code" class="sr-only">Program Code</label>  
    <input type="text" name="student_program_code" value ="<%= programCode %>"  class="form-control" placeholder="Program Code" required autofocus>
    
    <label for="input_student_program_description" class="sr-only">Program Description</label>  
    <input type="text" name="student_program_description" value ="<%= programDescription %>"  class="form-control" placeholder="Program Description" required autofocus>
    
    <label for="input_student_year" class="sr-only">Year</label>   
    <input type="text" name="student_year" value ="<%= year %>"  class="form-control" placeholder="Year" required autofocus>
	
	<div class="d-flex justify-content-center">
   <button type="submit" class="btn btn-primary btn-lg">Register</button>
   	</div>
   	<p><%= errorMessage %></p>
   	
</form>


<%@ include file="./footer.jsp" %>

