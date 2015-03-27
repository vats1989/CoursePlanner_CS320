<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
	
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Registration Page</title>
<script type="text/javascript">
function resetall()	{
	document.register.username.value="";
	document.register.password.value="";
	document.register.repassword.value="";
	document.register.firstname.value="";
	document.register.lastname.value="";
}
</script>

</head>
<body bgcolor="#E6E6FA" onload="resetall()">
<a href="/CS320CoursePlanner/BackMVC">Back</a>
<div align="center" style="top:50px">
	<table style="padding-top: 1px;">
	<tr><th><h2>Registration Form</h2></th></tr>
	</table>
</div>
<!-- <form name="register" onsubmit="return validateRegister()" id="register" action="RegisterValidation.jsp" method="post"> -->
<form name="register" id="register" action="/JDBC_CoursePlanner/Register" method="POST">	
	<div>
		<table align="center" style="padding-top: 10px">
			<tr>
				<td>Username:</td>
				<td style="color: red;"><input type="text" name="username" size="20"/>*</td>
			</tr>
			<tr>
				<td>Password:</td>
				<td style="color: red;"><input type="password" name="password" size="20"/>*</td>
			</tr>
			<tr>
				<td>Re-type Password:</td>
				<td style="color: red;"><input type="password" name="repassword" size="20" />*</td>
			</tr>
			<tr>
				<td>First Name:</td>
				<td><input type="text" name="firstname" size="20" /> </td>
			</tr>
			<tr>
				<td>Last Name:</td>
				<td><input type="text" name="lastname" size="20" /> </td>
			</tr>
			<tr>
				<td>Email:</td>
				<td><input type="text" name="email" /></td>
			</tr>
			<tr>
				<td colspan="2" align="center" style="padding-top: 30px"><input type="submit" name="submit" value="Submit"/></td>
			</tr>
		</table>
	</div>
	<div  align="center" style="padding-top:20px; height:10px">
	<font size="+1" color="red"> <c:out value="${errorMessage}"/> </font>
	</div>
	<c:remove var="errorMessage" />
</form>
</body>
</html>