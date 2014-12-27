<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>

<head>
<title>Password Reset - Static Void Games</title>

<link rel='stylesheet' type='text/css' href='http://fonts.googleapis.com/css?family=Open+Sans'>

<link rel="stylesheet" type="text/css" href="<c:url value="/css/general.css"/>">
<link rel="stylesheet" type="text/css" href="<c:url value="/css/everyPage.css"/>">

<link rel="shortcut icon" href="<c:url value="/images/favicon.png"/>" />
</head>

<body style="background-image:url(<c:url value="${backgroundImage}"/>);">
<%@ include file="../include/analytics.jsp"%>
	<%@ include file="../include/navigation.jsp"%>

	<div id="contentPane">

		<div class="darkBackground thinGrayBorder topMargin centeredBlock width75 textPadding">

			<c:choose>
				<c:when test="${resetSuccess}">
				<h1>Password Reset</h1>
					<p>Your password has been reset. Your new temporary password is:</p>
					<p>${password}</p>
					
					<p>You can now <a href="<c:url value="/login"/>">login</a> with your new password.</p>
					
				
					<p>If you have any questions, don't hesitate to <a href="<c:url value="/about/contact"/>">contact me</a>.</p>
					

					
				</c:when>
				<c:otherwise>
					<h1>Reset Failed</h1>
					<p style="color:red">${resetMessage}</p>
					<p>If you're having an issue, please <a href="<c:url value="/about/contact"/>">contact me</a>.</p>
				</c:otherwise>
			</c:choose>
			
		</div>

		<%@ include file="../include/advertisement.jsp"%>
		<%@ include file="../include/openSource.jsp"%>

	</div>


</body>
</html>