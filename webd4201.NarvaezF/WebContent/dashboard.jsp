<!-- 
Author:Fabian Narvaez
Name: dashboard.jsp
Date: 2021-03-21
Last Date modified: 2021-03-21
Description: This is the dashboard page that will display the data of the user that is logged in
 -->
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ include file="./header.jsp" %>
<%
    Student aStudent = (Student)session.getAttribute("student");
	String studentFirstName = aStudent.getFirstName();
    String studentLastName = aStudent.getLastName();
	String studentEmail = aStudent.getEmailAddress();
	String studentProgram = aStudent.getProgramDescription();
	Date studentLastAccess = aStudent.getLastAccess();

	
%>

<h1>DashBoard </h1>
<ul>
<li>Student Name: <%= studentFirstName + " " + studentLastName  %></li>
<li>Email: <%= studentEmail %></li>
<li>Program: <%= studentProgram %></li>
<li>Last Access: <%= studentLastAccess %></li>
</ul>
<%@ include file="./footer.jsp" %>