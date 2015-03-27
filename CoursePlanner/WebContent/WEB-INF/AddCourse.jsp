<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Add Course</title>
</head>
<body bgcolor="#E6E6FA">

<c:set var="user" scope="page" value="${login_user}" />
<font size="3" color="blue">Welcome <c:out value="${user}"/>, </font> <br/>
<a href="/CS320CoursePlanner/LogoutMVC">Logout</a>
 
<div align="center" style="top:200px height=20px">
<table align="center"> 
	<tr> <td><b>ADD COURSE</b></td> </tr> 
</table>
</div>
<form name="addcourse" action="/JDBC_CoursePlanner/AddCourse" method="POST">
<div><table align="center" border="1">
<tr> <td>Code:</td> <td><input type="text" name="AddCode" size="15" /></td> </tr>
<tr> <td>Title:</td><td><input type="text" name="AddTitle" size="40" /></td> </tr>
<tr>
	<td>Prerequisite(s):</td>
	<td>
		<c:forEach items="${course_codes}" var="addCode">
		<input type="checkbox" name="AddPreReqCode" value="${addCode}">${addCode}<br/>
		</c:forEach>
	</td>
</tr>
<tr><td colspan="2" align="center"> <input type="submit" name="submit" value="Add" size="20"></td></tr>
</table>
</div>
</form>
</body>
</html>