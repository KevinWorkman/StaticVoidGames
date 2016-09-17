<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>

	<title>Login - Static Void Games</title>
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/css/bootstrap.min.css">
    <link rel="stylesheet" href="https://bootswatch.com/cyborg/bootstrap.min.css">
    
    <link rel="stylesheet" type="text/css" href="<c:url value="/css/bs.css"/>">
	<link rel="shortcut icon" href="<c:url value="/images/favicon.png"/>" />
	
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/js/bootstrap.min.js"></script>
</head>

<body style="background-image:url(<c:url value="${backgroundImage}"/>);">
<%@ include file="include/analytics.jsp"%>
<%@ include file="include/navigation3.jsp" %>
<div class="container">



<c:if test="${not empty loginError}">
	<div class="panel panel-default text-center" style="margin-left:auto; margin-right:auto; margin-top:25px;">
		<div class="panel-body">
			<h2 style="color:red">${loginError}</h2>
			</div>
	</div>
</c:if>

<c:if test="${not empty param.error}">
	<div class="panel panel-default text-center" style="margin-left:auto; margin-right:auto; margin-top:25px;">
		<div class="panel-body">
			<c:choose>
				<c:when test="${sessionScope.SPRING_SECURITY_LAST_EXCEPTION.message eq 'Cannot pass null or empty values to constructor'}">
					<h3 style="color:red">Please reset your password.</h3>
					<p>We increased the security of the site since you last logged in, so please reset your password <a href="<c:url value="/forgotPassword" />">here</a>.</p>
				</c:when>
				<c:when test="${sessionScope.SPRING_SECURITY_LAST_EXCEPTION.message eq 'Bad credentials'}">
					<h3 style="color:red">Incorrect username or password.</h3>
					<p>For security reasons, if you haven't logged in since July 2014, please reset your password <a href="<c:url value="/forgotPassword" />">here</a>.</p>
				</c:when>
				<c:when test="${sessionScope.SPRING_SECURITY_LAST_EXCEPTION.message eq 'User is disabled'}">
				 	<h3 style="color:red">That account has not been activated. Click the activation link in the email you received when you registered.</h3>
				</c:when>
				<c:otherwise>
					<h3 style="color:red">${sessionScope.SPRING_SECURITY_LAST_EXCEPTION.message}</h3>
					<p>For security reasons, if you haven't logged in since July 2014, please reset your password <a href="<c:url value="/forgotPassword" />">here</a>.</p>
				</c:otherwise>
			</c:choose>
			
			<p>If you're having an issue, please <a href="<c:url value="/about/contact"/>">contact me</a>.</p>
		</div>
	</div>
</c:if>

	<div class="panel panel-default text-center" style="margin-left:auto; margin-right:auto; margin-top:25px; width:500px">
	
		<div class="panel-heading">Login</div>
		<div class="panel-body">
		
			<form name='f' action="<c:url value="/login"/>" method='POST'>
	
				<div class="form-group">
					<label for="username">Username</label>
					<input type='text' name='username' value='' class="form-control">
				</div>
				
				<div class="form-group">
					<label for="password">Password</label>
					<input type='password' name='password' class="form-control"/>
				</div>
				
				<button type="submit" class="btn btn-primary">Login</button>
	
			</form>
	
			<p style="margin-top:10px;">Forgot your password? Go <a href="<c:url value="/forgotPassword" />">here</a> to reset it.</p>
	  	</div>
	  </div>

    <%@ include file="include/openSource.jsp" %>

</div>
</body>
</html>