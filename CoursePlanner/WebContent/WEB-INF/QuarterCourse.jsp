<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Quarter Courses</title>
</head>
<body bgcolor="#E6E6FA">

<div align="center"  style="top:200px height=20px">
	<table align="center">
		<tr><td><h4><b>COURSE &nbsp;&nbsp;LIST</b></h4></td></tr>
	</table>
</div>
<p align="center" style="padding-right: 210px">You may take the following courses in <u>${nextQuarter} ${year}:</u></p>
<form name="quarterCourse" action="/JDBC_CoursePlanner/CoursePlan" method="POST">
<div>
<table align="center" border="1">
<tr>
	<th></th>
	<th>Code</th>
	<th>Title</th>
	<th>Prerequisites</th>
</tr>
<c:forEach items="${QuarterCourseList}" var="entry">
<tr>
	<c:if test="${entry.quarter.quarter ==  nextQuarter}" >
	<td><input type="checkbox" name="plan_subjects" value="${entry.course.courseCode}"/></td>
	<td>${entry.course.courseCode}</td>
	<td>${entry.course.courseDesc}</td>
	<td>${entry.course.preReq}</td>
	</c:if>
</tr>
</c:forEach>
<tr>
	<td colspan="4" align="center"> 
	<input type="submit" name="submit" value="Next"> </td>
</tr> 
</table></div>
</form>
<center>
<a href="/JDBC_CoursePlanner/Finish">Finish</a>
</center>
</head>
</html>