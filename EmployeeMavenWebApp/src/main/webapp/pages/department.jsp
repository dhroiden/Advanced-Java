<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<table cellspacing="10" border="1">
		<tr>
			<td>Employee Id</td>
			<td>Department Id</td>
			<td>DOJ</td>
			<td>DOB</td>
			<td>Salary</td>
			<td>Salary Grade</td>
		</tr>
		<c:forEach items="${requestScope.employeeList}" var="employee">
			<tr>
				<td>${employee.getEmpNo()}</td>
				<td>${employee.getDepNo()}</td>
				<td>${employee.getDoj()}</td>
				<td>${employee.getDob()}</td>
				<td>${employee.getSalary()}</td>
				<td>${employee.getSalaryGrade()}</td>
			</tr>
		</c:forEach>
	</table>
</body>
</html>