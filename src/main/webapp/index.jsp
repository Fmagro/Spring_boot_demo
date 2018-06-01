<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<h1>Welcome!</h1>


	<a href=<c:url value = "/Allemp"/>> View all employees</a> |  <a href=<c:url value = "/EmployeeForm"/>> Search for an employee </a> | <a href=<c:url value = "/EmployeeInsertForm"/>> Enter a new employee </a>
</body>
</html>