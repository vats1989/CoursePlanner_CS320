<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Course Planner</title>
</head>
<body bgcolor="#E6E6FA">
<a href="/JDBC_CoursePlanner/DisplayCourses">Courses</a>
<div align="center">
	<table align="center">
		<tr><td><font size="5"><b>COURSE PLANNER</b></font></td></tr>
	</table>
</div>
<p align="center" style="padding-right: 210px">Please select the courses you have already taken:</p>
<form name="courseplanner" action="/JDBC_CoursePlanner/CoursePlan" method="POST">
<div>
<table align="center" border="1">
<tr><th></th><th>Code</th><th>Title</th><th>Prerequisites</th></tr>
<c:forEach items="${courseplan}" var="entry">
<tr>
	<td><input type="checkbox" name="plan_subjects" value="${entry.courseCode}"/></td>
	<td>${entry.courseCode}</td>
	<td>${entry.courseDesc}</td>
	<td>${entry.preReq}</td>
</tr>
</c:forEach>
<tr>
	<td colspan="4" align="center"> 
	<input type="submit" name="submit" value="Next"> </td>
</tr> 
</table></div>
</form>
</head>
</html>