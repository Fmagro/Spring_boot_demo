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
	<h1>Listing Employees</h1>
	<table>
		<tr>
			<th>Name</th>
			<th>Surname</th>
			<th>Job</th>
			<th colspan="1" />
		</tr>
		<c:forEach var="emp" items="${list}">
			<tr>
				<td><a href=<c:url value = "/ShowEmployee/${emp.employeeId}"/>>${emp.firstName}</a></td>
				<td>${emp.lastName}</td>
				<td>${emp.jobId}</td>
				<td><a href=<c:url value = "/EmployeeUpdateForm/${emp.employeeId}"/>>Update Employee data</a></td>
		</c:forEach>

	</table>
</body>
</html>