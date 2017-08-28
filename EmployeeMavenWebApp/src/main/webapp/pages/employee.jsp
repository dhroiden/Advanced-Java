<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<table>
		<tr>
			<td>Employee Id:</td>
			<td>${requestScope.employee.empNo}</td>
		</tr>
		<tr>
			<td>Department Id:</td>
			<td>${requestScope.employee.depNo}</td>
		</tr>
		<tr>
			<td>DOJ:</td>
			<td>${requestScope.employee.doj}</td>
		</tr>
		<tr>
			<td>DOB:</td>
			<td>${requestScope.employee.dob}</td>
		</tr>
		<tr>
			<td>Salary:</td>
			<td>${requestScope.employee.salary}</td>
		</tr>
		<tr>
			<td>Salary Grade:</td>
			<td>${requestScope.employee.salaryGrade}</td>
		</tr>
	</table>
</body>
</html>