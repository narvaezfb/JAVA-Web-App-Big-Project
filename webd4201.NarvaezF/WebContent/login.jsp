<!-- 
Author:Fabian Narvaez
Name: login.jsp
Date: 2021-03-21
Last Date modified: 2021-03-21
Description: This is the login page that will take student id and password and send the information to the server 
and validate the information.
 -->
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ include file="./header.jsp" %>
<%	String errorMessage = (String)session.getAttribute("errors"); 
	String id = (String)session.getAttribute("id");
	String password = new String();
	if(errorMessage == null)
	    errorMessage = "";
	if(id == null)
		id = "";
	
	
%>


<form class="form-signin" method="post" action="./Login">

    <h2>Please log in</h2>
	<p>Enter your login information below.</p>
       
    <label for="login" class="sr-only">Student Id</label>
    
    <input type="text" name="Login" value="<%= id %>" class="form-control" placeholder="Student ID" required autofocus>
       
    <label for="inputPassword" class="sr-only">Password</label>

    <input type="password" name="Password" value="<%= password %>" class="form-control" placeholder="Password" required>
	
	<div class="d-flex justify-content-center">
   <button type="submit" class="btn btn-primary btn-lg">Submit</button>
   	</div>
   	<p><%= errorMessage %></p>
	
</form>

<%@ include file="./footer.jsp" %>