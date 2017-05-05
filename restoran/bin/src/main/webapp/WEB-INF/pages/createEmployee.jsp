<?xml version="1.0" encoding="ISO-8859-1" ?>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Create Employee</title>
</head>
<body>
	<div id="createGuest">
		<c:url var="action" value="/employees/create" />
		<form:form id="formEmployee" action="${action}" method="post"
			modelAttribute="employee">
			<fieldset>
			
				<form:label path="email">Email </form:label>
				<form:input path="email" /><br />
				<form:errors path="email" />
				<form:hidden path="firstName" value="name"  />
				<form:hidden path="lastName" value="lName"  />
				<form:label path="password">Password</form:label>
				<form:input path="password" /> <br />
				<form:label path="password2">Password again</form:label>
				<form:input path="password2" />
				
			</fieldset>
			<p>
				<button type="submit">Submit</button>
			</p>
		</form:form>
	</div>

</body>
</html>