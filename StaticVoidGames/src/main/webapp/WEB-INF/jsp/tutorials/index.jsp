<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
	<title>Tutorials - Static Void Games</title>
	
	<link rel='stylesheet' type='text/css' href='http://fonts.googleapis.com/css?family=Open+Sans' >
	
	<link rel="stylesheet" type="text/css" href="<c:url value="/css/general.css"/>">
	<link rel="stylesheet" type="text/css" href="<c:url value="/css/everyPage.css"/>">
	<link rel="stylesheet" type="text/css" href="<c:url value="/css/tutorial.css"/>">
	<link rel="shortcut icon" href="<c:url value="/images/favicon.png"/>" />
</head>

<body style="background-image:url(<c:url value="${backgroundImage}"/>);">
<%@ include file="../include/navigation.jsp" %>
<%@ include file="tutorialsNavigation.jsp" %>
<div id="contentPane">

	<h1>Tutorials</h1>

	<p>Pick a tutorial from the left!</p>

    <%@ include file="../include/advertisement.jsp" %>
   </div>
</body>
</html>