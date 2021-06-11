<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="security"
	uri="http://www.springframework.org/security/tags"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Admin Page</title>
</head>
<body>
	Welcome to admin page!
	<a href="logout">logout</a>
	<br>
	<br>
	<security:authorize access="hasRole('ADMIN')">  
	Hello ADMIN  
	</security:authorize>
	<security:authentication property="principal.username" />
	<security:csrfInput />
	<security:accesscontrollist hasPermission="1,2"
		domainObject="${someObject}">  
 If user has all the permissions represented by the values "1" or "2" on the given object.  
</security:accesscontrollist>
</body>
</html>