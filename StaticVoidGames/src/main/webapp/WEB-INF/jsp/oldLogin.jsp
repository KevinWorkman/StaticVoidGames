<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
<link rel='stylesheet' type='text/css' href='http://fonts.googleapis.com/css?family=Open+Sans' >
<link rel="stylesheet" type="text/css" href="<c:url value="/css/general.css"/>">
<link rel="stylesheet" type="text/css" href="<c:url value="/css/everyPage.css"/>">
<title>Old Login - Static Void Games</title>
	<link rel="shortcut icon" href="<c:url value="/images/favicon.png"/>" />
</head>

<body style="background-image:url(<c:url value="${backgroundImage}"/>);">
<%@ include file="include/analytics.jsp"%>
<%@ include file="include/navigation.jsp" %>
<div id="contentPane">
<h3>Old Login</h3>

<div class="thinGrayBorder darkBackground">
<p>This site was completely overhauled in August of 2014, including improving the password hashing function. The old site used my own hash function, whereas the new one uses bcrypt. This means that if you haven't logged in since July 15th, 2014, the site needs to generate a bcrypt hash for your password.</p>
<p>You can do so by entering your username and password below. This will create a bcrypt hash for you and take you to the new login page, where your login will now work.</p>
<p>Either way, you should know that your password is NEVER stored in the database.</p>
</div>


<form action="<c:url value="/oldLogin"/>" method='POST'>

<div class="thinGrayBorder darkBackground">
<p style="color:red">${oldLoginError}</p>
    <p>Username:<input type='text' name='username' value=''></p>
   	<p>Password:<input type='password' name='password'/></p>
   	<p><input name="submit" type="submit" value="Okay"/></p>
</div>
</form>

    <%@ include file="include/advertisement.jsp" %>
    <%@ include file="include/openSource.jsp" %>

</div>
</body>
</html>