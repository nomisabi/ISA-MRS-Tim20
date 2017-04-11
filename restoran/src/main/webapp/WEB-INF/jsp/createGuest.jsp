<?xml version="1.0" encoding="ISO-8859-1" ?>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1" />
<title>Sign up</title>
</head>
<body>
	<div id="createGuest">
		<c:url var="action" value="/guests/create" />
		
		<form:form id="formGuest" action="${action}" method="post"
			modelAttribute="guest">
			<fieldset>
				<form:label path="email">Email </form:label>
				<form:input path="email" /><br /><br />
				<form:label path="password">Password</form:label>
				<form:input path="password" /> <br /><br />
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