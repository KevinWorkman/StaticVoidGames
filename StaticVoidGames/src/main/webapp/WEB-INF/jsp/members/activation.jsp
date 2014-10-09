<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>

<head>
<title>Account Activation - Static Void Games</title>

<link rel='stylesheet' type='text/css' href='http://fonts.googleapis.com/css?family=Open+Sans'>

<link rel="stylesheet" type="text/css" href="<c:url value="/css/general.css"/>">
<link rel="stylesheet" type="text/css" href="<c:url value="/css/everyPage.css"/>">

<link rel="shortcut icon" href="<c:url value="/images/favicon.png"/>" />
</head>

<body style="background-image:url(<c:url value="${backgroundImage}"/>);">
	<%@ include file="../include/navigation.jsp"%>

	<div id="contentPane">

		<div class="darkBackground thinGrayBorder topMargin centeredBlock width75 textPadding">

			<c:choose>
				<c:when test="${activationSuccess}">
				<h1>Activation Successful</h1>
					<p>Your account is activated! You can now <a href="<c:url value="/login"/>">login</a>!</p>
					
					<div class="lightBackground darkText centeredBlock thinGrayBorder textPadding topMargin">
					<p>Now that you have an account, you can do all sorts of things on Static Void Games:</p>
					<ul>
					<li>Comment on games, blogs, and member profiles.</li>
					<li>Upload your own game!</li>
					<li>Write a blog!</li>
					<li>Get involved in our open-source project!</li>
					</ul>
					
					<p>If you have any questions, don't hesitate to <a href="<c:url value="/about/contact"/>">contact me</a>.</p>
					
		</div>
					
				</c:when>
				<c:otherwise>
					<h1>Activation Failed</h1>
					<p style="color:red">${activationMessage}</p>
					<p>If you're having an issue, please <a href="<c:url value="/about/contact"/>">contact me</a>.</p>
				</c:otherwise>
			</c:choose>



		</div>


		



		<%@ include file="../include/advertisement.jsp"%>
		<%@ include file="../include/openSource.jsp"%>

	</div>


</body>
</html>