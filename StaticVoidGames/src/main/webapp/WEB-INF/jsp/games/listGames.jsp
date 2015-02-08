<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
	<title>Games - Static Void Games</title>
	<link rel='stylesheet' type='text/css' href='http://fonts.googleapis.com/css?family=Open+Sans&effect=3d' >
	<link rel="stylesheet" type="text/css" href="<c:url value="/css/general.css"/>">
	<link rel="stylesheet" type="text/css" href="<c:url value="/css/everyPage.css"/>">
	<link rel="stylesheet" type="text/css" href="<c:url value="/css/game.css"/>">
	<link rel="shortcut icon" href="<c:url value="/images/favicon.png"/>" />
</head>
<body style="background-image:url(<c:url value="${backgroundImage}"/>);">


<%@ include file="../include/analytics.jsp"%>
<%@ include file="../include/navigation.jsp" %>
<div id="gameListPane">

	<div class="darkBackground centeredBlock centered textPadding topMargin font-effect-3d" style="border:thin solid gray; font-size: 32px;" >
	<h1>All Games</h1>
	</div>
	
	<c:forEach items="${games}" var="game">
		
		<div class="smallGameLink">
			<%-- Links both the title and description with a link to the game. --%>
			<a href="<c:url value="/games/${game.getGameName()}" />">
				
				<div style="float:left;">
					<%-- Image of game --%>
					<c:choose>
					<c:when test="${game.getThumbnailUrl() == null}"><div><img src="<c:url value="${s3Endpoint}/images/staticVoidProfile3.png"/>" /></div></c:when>
					<c:otherwise><div><img src="<c:url value="${s3Endpoint}/games/${game.getGameName()}/${game.getThumbnailUrl()}"/>" /></div></c:otherwise>
					</c:choose>
					
					by ${game.getMember()}
				</div>
				
				<div style="padding:15px;">
					<div style="font-size:28px;">${game.getTitle()}</div>
					
					<c:choose>
					<c:when test="${game.shortDescription == null || game.shortDescription.isEmpty() || game.shortDescription == \"null\"}"></c:when>
					<c:otherwise>${game.getShortDescription()}</c:otherwise>
					</c:choose>
				</div>
			</a>
		</div>
		
	</c:forEach>
	
			<%@ include file="../include/advertisement.jsp" %>
   			<%@ include file="../include/openSource.jsp" %>
</div>
</body>
</html>