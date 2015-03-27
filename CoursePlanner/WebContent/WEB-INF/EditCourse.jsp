<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Edit Course</title>
</head>
<body bgcolor="#E6E6FA">

<c:set var="user" scope="page" value="${login_user}" />
<font size="3" color="blue">Welcome <c:out value="${user}"/>, </font> <br/>
<a href="/CS320CoursePlanner/LogoutMVC">Logout</a>

<div align="center" style="top:200px; height=20px">
<table align="center"><tr><td><b>EDIT COURSE</b></td></tr></table>
</div>
<form name="editcourse" action="/JDBC_CoursePlanner/EditCourse?id=${id}" method="POST">
<div><table align="center" border="1">
<tr><td>Code:</td><td><input type="text" name="EditCode" size="15" value="${courseCode}" /> </td></tr>
<tr><td>Title:</td><td><input type="text" name="EditTitle" size="40" value="${title}"  /> </td></tr>
<tr><td>Prerequisite(s):</td>
<td>
<c:forEach items="${courses}" var="subCode">
	<c:set var="flag" scope="page" value="0" />
	<c:forEach items="${codes}" var="preReqCode">
		<c:if test="${subCode.courseCode == preReqCode}" >
			<input type="checkbox" name="EditPreReqCode" value="${subCode.courseCode}" checked>${subCode.courseCode}<br/>
			<c:set var="flag" scope="page" value="1" />
		</c:if>
	</c:forEach>
	<c:if test="${subCode.courseCode == courseCode}">
		<c:set var="flag" scope="page" value="1" />
	</c:if>
	<c:if test="${flag == 0}">
		<input type="checkbox" name="EditPreReqCode" value="${subCode.courseCode}">${subCode.courseCode}<br/>
	</c:if>
</c:forEach>
</td>
</tr>
<tr><td colspan="2" align="center"><input type="submit" name="submit" value="Save Changes" size="20"></td></tr>
</table></div></form></body></html>