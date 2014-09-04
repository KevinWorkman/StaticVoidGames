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
<%@ include file="include/navigation.jsp" %>
<div id="contentPane">
<h3>Register</h3>
<form action="<c:url value="/register"/>" method='POST'>

<div class="thinGrayBorder darkBackground">
<p style="color:red">${registerError}</p>

    <p>Email:<input type='text' name='email' value=''></p>
    <p>Username:<input type='text' name='username' value=''></p>
   	<p>Password:<input type='password' name='password'/></p>
   	<p><input name="submit" type="submit" value="Register"/></p>
</div>
</form>

	    <%@ include file="include/advertisement.jsp" %>
    	<%@ include file="include/openSource.jsp" %>

</div>
</body>
</html>