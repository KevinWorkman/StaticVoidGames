<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
	<title>Games - Static Void Games</title>
	<link rel='stylesheet' type='text/css' href='http://fonts.googleapis.com/css?family=Open+Sans' >
	<link rel="stylesheet" type="text/css" href="<c:url value="/css/general.css"/>">
	<link rel="stylesheet" type="text/css" href="<c:url value="/css/everyPage.css"/>">
	<link rel="stylesheet" type="text/css" href="<c:url value="/css/game.css"/>">
	<link rel="shortcut icon" href="<c:url value="/images/favicon.png"/>" />
</head>
<body style="background-image:url(<c:url value="${backgroundImage}"/>);">



<%@ include file="../include/navigation.jsp" %>
<div id="contentPane">

	<div class="darkBackground centeredBlock centered textPadding topMargin" style="border:thin solid gray;">
	<h1>All Games</h1>
	<p>This page needs some work, maybe with more information about each game or some kind of search system?</p>
	<p>For now, here is every (published) game on the site:</p>
	</div>
	
	<div style="text-align:center;">
	<c:forEach items="${games}" var="game">
		<a href="<c:url value="/games/${game.getGameName()}" />">
			<div class="smallGameLink">
			
				<img src="<c:url value="${s3Endpoint}/games/${game.getGameName()}/${game.getThumbnailUrl()}"/>" />
			
				<div class="smallGameLinkText">
					${game.getTitle()}
					<hr/>
					${game.getShortDescription()}
					
				</div>
			</div>
		</a>
		
	</c:forEach>
	</div>
	
	<%@ include file="../include/advertisement.jsp" %>
</div>
</body>
</html>