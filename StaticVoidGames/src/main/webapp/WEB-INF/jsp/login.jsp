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
<%@ include file="include/navigation.jsp" %>
<div id="contentPane">
<h3>Login</h3>
<form name='f' action="<c:url value="/login"/>" method='POST'>

<div class="thinGrayBorder darkBackground">
<p>Don't have an account yet? Register <a href="<c:url value="/register"/>">here</a>!</p>
</div>

<c:if test="${param.error == 'true'}">
	<div class="thinGrayBorder darkBackground">
    	<span><spring:message code="loginPage.authenticationFailure" /></span>
    </div>
</c:if>

<div class="thinGrayBorder darkBackground">
<p>If you haven't logged in recently, you have to login <a href="<c:url value="/oldLogin" />">here</a> first.</p>
</div>

<div class="thinGrayBorder darkBackground">
 <table>
    <tr><td>Username:</td><td><input type='text' name='username' value=''></td></tr>
    <tr><td>Password:</td><td><input type='password' name='password'/></td></tr>
    <tr><td colspan='2'><input name="submit" type="submit" value="Login"/></td></tr>
  </table>
  </div>
</form>
</div>
</body>
</html>