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

<form action="<c:url value="/register"/>" method='POST'>

<div class="thinGrayBorder darkBackground textPadding topMargin">

<h3>Register</h3>

<p style="color:red">${registrationError}</p>

    <p>Email:<input type='text' name='email' value='${email}'></p>
    
    <p style="font-size:small;">An activation link will be emailed to you. <b>You must click the link in the email before you can login</b>.
    <br/><span style="font-size:x-small;">This is annoying, but it's better than dealing with a bunch of spam bots!</span></p>
   	
    
    <p>Username:<input type='text' name='username' value='${username}'></p>
   	<p>Password:<input type='password' name='password'/></p>
   	
   	${recaptchaHtml}
   	
   
   	<p><input name="submit" type="submit" value="Register"/></p>
</div>
</form>

	    <%@ include file="include/advertisement.jsp" %>
    	<%@ include file="include/openSource.jsp" %>

</div>
</body>
</html>