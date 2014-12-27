<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
	<link rel='stylesheet' type='text/css' href='http://fonts.googleapis.com/css?family=Open+Sans' >
	<link rel="stylesheet" type="text/css" href="<c:url value="/css/general.css"/>">
	<link rel="stylesheet" type="text/css" href="<c:url value="/css/everyPage.css"/>">
	<title>Login - Static Void Games</title>
	<link rel="shortcut icon" href="<c:url value="/images/favicon.png"/>" />
</head>

<body style="background-image:url(<c:url value="${backgroundImage}"/>);">
<%@ include file="include/analytics.jsp"%>
<%@ include file="include/navigation.jsp" %>
<div id="contentPane">


<div class="thinGrayBorder darkBackground textPadding">

<h3>Login</h3>

<form name='f' action="<c:url value="/login"/>" method='POST'>

<p>Don't have an account yet? Register <a href="<c:url value="/register"/>">here</a>!</p>

<h2 style="color:red">${loginError}</h2>
<c:if test="${not empty param.error}">
	<c:choose>
		<c:when test="${sessionScope.SPRING_SECURITY_LAST_EXCEPTION.message eq 'Bad credentials'}">
			<h3 style="color:red">Incorrect username or password.</h3>
			<p>For security reasons, if you haven't logged in since July 2014, please reset your password <a href="<c:url value="/forgotPassword" />">here</a>.</p>
		</c:when>
		<c:when test="${sessionScope.SPRING_SECURITY_LAST_EXCEPTION.message eq 'User is disabled'}">
		 	<h3 style="color:red">That account has not been activated. Click the activation link in the email you received when you registered.</h3>
		</c:when>
		<c:otherwise>
			<h3 style="color:red">${sessionScope.SPRING_SECURITY_LAST_EXCEPTION.message}</h3>
		</c:otherwise>
	</c:choose>
	
	<p>If you're having an issue, please <a href="<c:url value="/about/contact"/>">contact me</a>.</p>
	
</c:if>


 <table>
    <tr><td>Username:</td><td><input type='text' name='username' value=''></td></tr>
    <tr><td>Password:</td><td><input type='password' name='password'/></td></tr>
    <tr><td colspan='2'><input name="submit" type="submit" value="Login"/></td></tr>
  </table>
</form>

		<p>Forgot your password? Go <a href="<c:url value="/forgotPassword" />">here</a> to reset it.</p>
  </div>

    <%@ include file="include/advertisement.jsp" %>
    <%@ include file="include/openSource.jsp" %>

</div>
</body>
</html>