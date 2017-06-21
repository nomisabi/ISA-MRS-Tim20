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
<title>Create Manager</title>
</head>
<body>
	<div id="createManager">
		<c:url var="action" value="/managers/createManager" />
		<form:form action="${action}" method="post"
			modelAttribute="manager">
			<fieldset>
				<form:label path="email">Email> </form:label>
				<form:input path="email" />
				<form:errors path="email" />
				<form:hidden path="password" value="pass"  />
				<form:hidden path="firstName" value="name"  />
				<form:hidden path="lastName" value="lName"  />
				<form:label path="restaurant">Restaurant> </form:label>
				<form:select path="restaurant" >
					<c:forEach items="${restaurant}"  var="restaurants">
						<form:option value="${restaurant.name}"></form:option>
					</c:forEach>
				</form:select>
				<form:errors path="restaurant" />
				</>
			</fieldset>
			<p>
				<button type="submit">Submit</button>
			</p>
		</form:form>
	</div>
</body>
</html>