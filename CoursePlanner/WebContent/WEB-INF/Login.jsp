<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
	
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Course Planner Login Page</title>
<script type="text/javascript">
function resetall()	{
	document.login.usernamelogin.value="";
	document.login.txtpasslogin.value="";
}
</script>
</head>
<body bgcolor="#E6E6FA" onload="resetall()">
<a href="/JDBC_CoursePlanner/Back">Back</a>
<div  align="center" style="top:150px; height:10px">

<table style="padding-top: 10px;">
  <tr><th><h3>Course Planner Login Page</h3></th></tr>
</table>
</div>

<form name="login" id="login" action="/JDBC_CoursePlanner/Login" method="POST">
<div align="center" style="padding-top: 80px">
<table>
<tr>
	<td align="left"> Username: </td> 
	<td> <input type="text" name="usernamelogin" size="15"> </td>
</tr> 
<tr> 
	<td align="left"> Password: </td>
	<td> <input type="password" name="txtpasslogin" size="15"> </td>
</tr>
<tr>
	<td></td>
	<td align="center" style="padding-top: 30px; padding-right: 30px"> <input type="submit" value="Submit"> </td>
</tr> 
</table>
</div>
<div  align="center" style="padding-top:20px; height:10px">	
	<font size="+1" color="red">
   		<c:out value="${errorMessage}"/> 
   		<c:out value="${login_error}" />
   	</font>
</div>
<c:remove var="login_error" />   		
<c:remove var="errorMessage" />
</form>
</body>
</html>