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

<h3>Forgot Password</h3>

<form name='f' action="<c:url value="/forgotPassword"/>" method='POST'>

<p>If you forgot your password, simply fill in your username and press the "Reset Password" button.</p>

<p>An email will be sent to you containing a password reset link.</p>

<p>If you're having an issue, please <a href="<c:url value="/about/contact"/>">contact me</a>.</p>

<h2 style="color:red">${forgotPasswordError}</h2>

 <table>
    <tr><td>Username:</td><td><input type='text' name='username' value='${username}'></td></tr>
    <tr><td colspan='2'><input name="submit" type="submit" value="Reset Password"/></td></tr>
  </table>
</form>
  </div>

    <%@ include file="include/advertisement.jsp" %>
    <%@ include file="include/openSource.jsp" %>

</div>
</body>
</html>