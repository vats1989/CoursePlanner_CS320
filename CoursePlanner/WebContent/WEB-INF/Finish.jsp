<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Final Course Planning List</title>
</head>
<body bgcolor="#E6E6FA">
<div align="center"  style="top:200px height=20px">
	<table align="center">
		<tr><td><b>Here is your course plan:</b></td></tr>
	</table>
</div>
<br/>

<c:set var="quart1" scope="page" value="Winter"/>
<c:set var="quart2" scope="page" value="Spring"/>
<c:set var="quart3" scope="page" value="Summer"/>
<c:set var="quart4" scope="page" value="Fall"/>

<c:set var="winterFlag" scope="page" value=""/>
<c:set var="springFlag" scope="page" value=""/>
<c:set var="summerFlag" scope="page" value=""/>
<c:set var="fallFlag" scope="page" value=""/>

<c:forEach items="${QuarterCourseList}" var="list">
	<c:if test="${list.quarter.quarter == quart1 }">
		<c:set var="winterFlag" scope="page" value="Winter"/>	
	</c:if>
	<c:if test="${list.quarter.quarter == quart2 }">
		<c:set var="springFlag" scope="page" value="Spring"/>	
	</c:if>
	<c:if test="${list.quarter.quarter == quart3 }">
		<c:set var="summerFlag" scope="page" value="Summer"/>
	</c:if>
	<c:if test="${list.quarter.quarter == quart4 }">
		<c:set var="fallFlag" scope="page" value="Fall"/>
	</c:if>
</c:forEach>

<c:if test="${not empty winterFlag}">
<div>
<center><u>Winter ${year}</u></center>
<table align="center" border="1">
<tr><th>Code</th><th>Title</th><th>Prerequisites</th></tr>
<c:forEach items="${QuarterCourseList}" var="entry">
<c:if test="${entry.quarter.quarter == quart1 }" >
	<tr>	
		<td>${entry.course.courseCode}</td>
		<td>${entry.course.courseDesc}</td>
		<td>${entry.course.preReq}</td>
	</tr>
</c:if>
</c:forEach>
</table></div>
</c:if>
<br/><br/>

<c:if test="${not empty springFlag }">
<div>
<center><u>Spring ${year}</u></center>
<table align="center" border="1">
<tr><th>Code</th><th>Title</th><th>Prerequisites</th>
</tr>
<c:forEach items="${QuarterCourseList}" var="entry">
<c:if test="${entry.quarter.quarter == quart2 }" >
	<tr>	
		<td>${entry.course.courseCode}</td>
		<td>${entry.course.courseDesc}</td>
		<td>${entry.course.preReq}</td>
	</tr>
</c:if>
</c:forEach>
</table></div>
</c:if>
<br/><br/>

<c:if test="${not empty summerFlag}">
<div>
<center><u>Summer ${year}</u></center>
<table align="center" border="1">
<tr><th>Code</th><th>Title</th><th>Prerequisites</th>
</tr>
<c:forEach items="${QuarterCourseList}" var="entry">
<c:if test="${entry.quarter.quarter == quart3 }" >
	<tr>	
		<td>${entry.course.courseCode}</td>
		<td>${entry.course.courseDesc}</td>
		<td>${entry.course.preReq}</td>
	</tr>
</c:if>
</c:forEach>
</table></div>
</c:if>
<br/><br/>

<c:if test="${not empty fallFlag}">
<div>
<center><u>Fall ${year}</u></center>
<table align="center" border="1">
<tr><th>Code</th><th>Title</th><th>Prerequisites</th>
</tr>
<c:forEach items="${QuarterCourseList}" var="entry">
<c:if test="${entry.quarter.quarter == quart4 }" >
	<tr>	
		<td>${entry.course.courseCode}</td>
		<td>${entry.course.courseDesc}</td>
		<td>${entry.course.preReq}</td>
	</tr>
</c:if>
</c:forEach>
</table></div>
</c:if>
<br/><br/>
<center><a href="/JDBC_CoursePlanner/Done">Done</a></center>

</body>
</html>