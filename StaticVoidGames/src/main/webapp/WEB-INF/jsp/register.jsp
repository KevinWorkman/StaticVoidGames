<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
<link rel='stylesheet' type='text/css' href='http://fonts.googleapis.com/css?family=Open+Sans' >
<link rel="stylesheet" type="text/css" href="<c:url value="/css/general.css"/>">
<link rel="stylesheet" type="text/css" href="<c:url value="/css/everyPage.css"/>">
<title>Register - Static Void Games</title>
	<link rel="shortcut icon" href="<c:url value="/images/favicon.png"/>" />
</head>

<body style="background-image:url(<c:url value="${backgroundImage}"/>);">
<%@ include file="include/analytics.jsp"%>
<%@ include file="include/navigation.jsp" %>
<div id="contentPane">

	<h3>Static Void Games is no longer accepting new users!</h3>
	<h3>Check out my new project at [HappyCoding.io](http://HappyCoding.io)!</h3>

	<%@ include file="include/openSource.jsp" %>

</div>
</body>
</html>