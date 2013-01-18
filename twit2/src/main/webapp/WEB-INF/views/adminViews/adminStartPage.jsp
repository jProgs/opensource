<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<html>
<head>
	<title>Admin Start Page</title>
</head>
<body>
<h1>
	Admin Start Page  
</h1>

<P>This is the administrator start page </P>

<form:form method="POST" action="checkCredentials.html">
	<table>
		<tr>
			<td><form:label path="userName">USER NAME: </form:label></td>
			<td><form:input path="userName" /> </td>
		</tr>
		<tr>
			<td><form:label path="password">PASSWORD: </form:label></td>
			<td><form:input path="password" /></td>
		</tr>
		<tr><td><input type="submit" value="Enter" /></td></tr>
	
	</table>

</form:form>

</body>
</html>