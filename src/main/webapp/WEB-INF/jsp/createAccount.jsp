<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>

<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	
	<style type="text/css">
		.error{
			color: red;
			font-size: 15px;
		}
	</style>
	
	<title>Create an Account</title>
</head>

<body>
	<h1>Enter email account</h1>
	<form:form commandName="aNewAccount">
		<table>
			<tr>
				<td>First Name: <form:input path="firstname" type="text" name="firstname"/>
				<form:errors path="firstname" cssClass="error"/></td>
			</tr>
			<tr>
				<td>Last Name: <form:input path="lastname" type="text" name="lastname"/>
				<form:errors path="lastname" cssClass="error"/></td>
			</tr>
			<tr>
				<td>Age: <form:input path="age" type="text" name="age"/>
				<form:errors path="age" cssClass="error"/></td>
			</tr>
			<tr>
				<td>Address: <form:input path="address" type="text" name="address"/>
				<form:errors path="address" cssClass="error"/></td>
			</tr>
			<tr>
				<td>Email: <form:input path="email" type="text" name="email"/>
				<form:errors path="email" cssClass="error"/></td>
			</tr>
			
			<tr>
				<td><input type="submit" value="Create" /></td>
			</tr>
		</table>
	</form:form>
</body>

</html>
