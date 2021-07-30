<!-- 
Author:Fabian Narvaez
Name: footer.jsp
Date: 2021-03-21
Last Date modified: 2021-03-21
Description: This is the footer for every page of my web app.
 -->
<%@ page import = "java.util.*" %>
<p>
	<a href="http://validator.w3.org/check?uri=referer">
	<img style="width:88px; height:31px; position:fixed; bottom: 10px; right:50px;"
	src="http://www.w3.org/Icons/valid-xhtml10" alt="Valid XHTML 1.0 Strict" />
	</a>
</p>
<span>&copy;<%= (Calendar.getInstance()).get(Calendar.YEAR) %></span>
</body>
</html>