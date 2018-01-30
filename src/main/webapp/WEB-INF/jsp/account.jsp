<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>

<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>

<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<title>Add an Account</title>
</head>

<body>
	<h1>Enter email account</h1>
	<form:form commandName="aNewAccount" method="post" action="accountCreated" >
		<table>
			<tr><td>First Name: <form:input path="firstname" type="text" name="firstname"/></td></tr>
			<tr><td>Last Name: <form:input path="lastname" type="text" name="lastname"/></td></tr>
			<tr><td>Age: <form:input path="age" type="text" name="age"/></td></tr>
			<tr><td>Address: <form:input path="address" type="text" name="address"/></td></tr>
			<tr><td>Email: <form:input path="email" type="text" name="email"/></td></tr>
			
			<tr><td><input type="submit" value="Add " /></td></tr>
		</table>
	</form:form>
</body>

</html>
