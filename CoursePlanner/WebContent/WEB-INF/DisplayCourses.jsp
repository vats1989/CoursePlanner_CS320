<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Course List</title>
</head>
<body bgcolor="#E6E6FA">

<c:set var="user" scope="page" value="${login_user}" />
<c:if test="${not empty user}">
	<font size="3" color="blue">Welcome <c:out value="${user}"/>, </font> <br/>
	<a href="/JDBC_CoursePlanner/Logout">Logout</a>
</c:if>
<c:if test="${empty user}">
	<a href="/JDBC_CoursePlanner/Login">Login</a> | <a href="/JDBC_CoursePlanner/Register">Register</a> <br/>
</c:if>
<br/>
<a href="/JDBC_CoursePlanner/CoursePlan">CoursePlanner</a>

<div align="center"  style="top:200px height=20px">
	<table align="center">
		<tr><td><h4><b>COURSE &nbsp;&nbsp;LIST</b></h4></td></tr>
	</table>
</div>
<div>
<table align="center" border="1">
<tr>
	<th>Code</th>
	<th>Title</th>
	<th>Prerequisites</th>
	<th>Operation</th>
</tr>
<c:forEach items="${courses}" var="entry">
<tr>
	<td>${entry.courseCode}</td>
	<td>${entry.courseDesc}</td>
<!--<td>${entry.preReq}</td>-->
	<td><c:forEach items="${entry.preReqCodes}" var="code"> ${code} </c:forEach> </td>		
		
	<td><a href="/JDBC_CoursePlanner/EditCourse?courseID=${entry.courseCode}">Edit</a></td>
</tr>
</c:forEach>
</table></div>
<br/><br/> 
<div><table align="center"><tr><td>
<a href="/JDBC_CoursePlanner/AddCourse">Add Courses</a>
</td></tr>
</table></div></head></html>