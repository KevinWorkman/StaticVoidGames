<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
	<link rel='stylesheet' type='text/css' href='http://fonts.googleapis.com/css?family=Open+Sans' >
	<link rel="stylesheet" type="text/css" href="<c:url value="/css/general.css"/>">
	<link rel="stylesheet" type="text/css" href="<c:url value="/css/everyPage.css"/>">
	<title>Forgot Password - Static Void Games</title>
	<link rel="shortcut icon" href="<c:url value="/images/favicon.png"/>" />
</head>

<body style="background-image:url(<c:url value="${backgroundImage}"/>);">
<%@ include file="include/analytics.jsp"%>
<%@ include file="include/navigation.jsp" %>
<div id="contentPane">


<div class="thinGrayBorder darkBackground textPadding">

<h3>Password Reset</h3>

<c:choose>
		<c:when test="${passwordSent}">
			<p>An email has been sent to you containing a password reset link.</p>

			<p>If you're having an issue, please <a href="<c:url value="/about/contact"/>">contact me</a>.</p>
		</c:when>
		<c:otherwise>
			<p>There was a problem sending you a password reset email.</p>
			
			<p>Please <a href="<c:url value="/about/contact"/>">contact me</a>.</p>
		</c:otherwise>
	</c:choose>

  </div>

    <%@ include file="include/advertisement.jsp" %>
    <%@ include file="include/openSource.jsp" %>

</div>
</body>
</html>