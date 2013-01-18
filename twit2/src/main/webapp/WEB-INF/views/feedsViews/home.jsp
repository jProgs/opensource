<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<html>
<head>
	<title>Home</title> 
	<link rel="stylesheet" type="text/css" href="/finalLab/src/main/webapp/resources/styles/style.css" media="screen" />
</head>
<body>
	<article id="divPage">
		<section id="divHeader">
			<jsp:include page="/partials/header.jsp"></jsp:include>
		</section>	
		<section id="divContent">
			<aside id="divNav"><jsp:include page="/partials/nav.jsp"></jsp:include></aside>
			</section>
			<form:form method="GET" action="listFeeds.html">
			
			<form:select path="category">
			<form:option value="soccer" label="soccer"/>
			<form:option value="news" label="news"/>
			<form:option value="comedy" label="comedy"/>
			<form:option value="games" label="games"/>
			<form:option value="technology" label="technology"/>
			</form:select>
			<br>

			<input type="submit" value="Submit"/>
			</form:form>
			
			<br /><br />
			
		  <a href="/twit2/allStar">All Stars</a> 
			
		
		<section id="divFooter">
			<jsp:include page="/partials/footer.jsp"></jsp:include>
		</section>
	</article>
	
	</body>
</html>



 

   