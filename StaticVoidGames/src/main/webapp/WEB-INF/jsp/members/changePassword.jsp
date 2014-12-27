<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
<link rel='stylesheet' type='text/css' href='http://fonts.googleapis.com/css?family=Open+Sans' >
<link rel="stylesheet" type="text/css" href="<c:url value="/css/general.css"/>">
<link rel="stylesheet" type="text/css" href="<c:url value="/css/everyPage.css"/>">
<title>Change Password - Static Void Games</title>
	<link rel="shortcut icon" href="<c:url value="/images/favicon.png"/>" />
</head>

<body style="background-image:url(<c:url value="${backgroundImage}"/>);">
<%@ include file="../include/analytics.jsp"%>
<%@ include file="../include/navigation.jsp" %>
<div id="contentPane">

<div class="thinGrayBorder darkBackground textPadding topMargin">

			<c:choose>
				<c:when test="${success}">
				<h3>Password Changed</h3>
					<p>Your password has been changed.</p>
					<p><a href="<c:url value="/members/${memberName}"/>">Go back to your profile.</a></p>
					
				</c:when>
				<c:otherwise>
					<h1>Change Password</h1>
				
					
					<form name='f' action="" method='POST'>

						<p>To change your password, please enter your current password followed by your new password:</p>
						
						<h2 style="color:red">${error}</h2>
						
						 <table>
						    <tr><td>Current Password:</td><td><input type='password' name='oldPassword' value=''></td></tr>
						    <tr><td>New Password:</td><td><input type='password' name='newPassword1' value=''></td></tr>
						    <tr><td>Retype New Password:</td><td><input type='password' name='newPassword2' value=''></td></tr>
						    <tr><td colspan='2'><input name="submit" type="submit" value="Change Password"/></td></tr>
						  </table>
						  
						  
						  <p>If you don't want to change your password, you can <a href="<c:url value="/members/${memberName}"/>">go back to your profile.</a></p>
						  <p>If you're having an issue, please <a href="<c:url value="/about/contact"/>">contact me</a>.</p>
					</form>
					
				</c:otherwise>
			</c:choose>
</div>

	    <%@ include file="../include/advertisement.jsp" %>
    	<%@ include file="../include/openSource.jsp" %>

</div>
</body>
</html>