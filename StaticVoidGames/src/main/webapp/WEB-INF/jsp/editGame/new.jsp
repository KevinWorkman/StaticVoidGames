<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
	<title>New Game</title>
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/css/bootstrap.min.css">
    <link rel="stylesheet" href="https://bootswatch.com/cyborg/bootstrap.min.css">
    
    <link rel="stylesheet" type="text/css" href="<c:url value="/css/bs.css"/>">
	<link rel="shortcut icon" href="<c:url value="/images/favicon.png"/>" />
	
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/js/bootstrap.min.js"></script>
</head>
<body style="background-image:url(<c:url value="${backgroundImage}"/>);">
	<%@ include file="../include/analytics.jsp"%>
	<%@ include file="../include/navigation3.jsp" %>
	<div class="container">
	
	<c:if test="${not empty newError}">
		<div class="panel panel-default text-center" style="margin-left:auto; margin-right:auto; margin-top:25px;">
			<div class="panel-heading">Error</div>
			<div class="panel-body" style="color:red;">${newError}</div>
		</div>
	</c:if>

	
	<div class="panel panel-default" style="margin-left:auto; margin-right:auto; margin-top:25px;">
		<div class="panel-heading">Static Void Games is no longer accepting new uploads!</div>
		<div class="panel-body">
			<p>For more info, see <a href="http://staticvoidgames.com/blog/HappyCoding">this blog post</a>.
			<p>Check out my new project at <a href="http://HappyCoding.io">HappyCoding.io</a>.</p>
			
			<p>If you're looking for a place to upload your game, check out <a href="https://itch.io/">itch.io</a> and <a href="http://gamejolt.com/">Game Jolt</a>!</p>

		</div>
	</div>
	

   	<%@ include file="../include/openSource.jsp" %>
	
	</div>
</body>
</html>