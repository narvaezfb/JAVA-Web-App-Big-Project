<!-- 
Author:Fabian Narvaez
Name: header.jsp
Date: 2021-03-21
Last Date modified: 2021-03-21
Description: this is the header of everypage on my web application.
 -->
<%@ page import="webd4201.NarvaezF.*" %>
<%@ page import = "java.util.*" %>
<%	
String title = "";
String href1 = "login.jsp";
String link1 = "Login";
String href2 = "register.jsp";
String link2 = "Register";
String dashboard = "";
String href3 = "./dashboard.jsp";

if((Student) session.getAttribute("student") != null){
	href1 = "./Logout";
	link1 = "Sign out";
	href2 = "./update";
	link2 = "Update";
	dashboard = "Dashboard";
}

%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN"
 "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en" lang="en">
  <head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->
    <title><%= title %></title>

    <!-- Bootstrap -->
    <link href="css/bootstrap.min.css" rel="stylesheet">
    <link href="./css/styles.css" rel="stylesheet">

    <!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]>
      <script src="https://oss.maxcdn.com/html5shiv/3.7.3/html5shiv.min.js"></script>
      <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
    <![endif]-->
  </head>
  <body>
  
  <!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
    <!-- Include all compiled plugins (below), or include individual files as needed -->
    <script src="js/bootstrap.min.js"></script>
    
  <nav class="navbar navbar-expand-lg navbar-dark bg-light bg-dark">
  <div class="container">
  <a class="navbar-brand" href="#">WEBD4201</a>
  <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
    <span class="navbar-toggler-icon"></span>
  </button>

  <div class="col-auto">
  <div class="collapse navbar-collapse" id="navbarSupportedContent">
    <ul class="navbar-nav mr-auto">
      <li class="nav-item ">
        <a class="nav-link" href="<%= href3 %>"><%= dashboard %> </a>
      </li>
      <li class="nav-item">
        <a class="nav-link " href="<%= href1 %>"><%= link1 %></a>
      </li>
      <li class="nav-item">
        <a class="nav-link " href="<%= href2 %>"><%= link2 %></a>
      </li>
    </ul>
    </div>
  </div>
  </div>
</nav>
 

     
 


