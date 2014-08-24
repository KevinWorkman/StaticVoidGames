<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
<title>New Game</title>
	<link rel='stylesheet' type='text/css' href='http://fonts.googleapis.com/css?family=Open+Sans' >
	
	<link rel="stylesheet" type="text/css" href="<c:url value="/css/general.css"/>">
	<link rel="stylesheet" type="text/css" href="<c:url value="/css/everyPage.css"/>">
</head>
<body style="background-image:url(<c:url value="${backgroundImage}"/>);">
	<%@ include file="../include/navigation.jsp"%>
	<div id="contentPane">
	<h3>New Game</h3>
	
	<div class="darkBackground lightText centered centeredBlock width75" style="color:red;">
	<p>${newError}</p>
	</div>

	<div class="darkBackground lightText centered centeredBlock width75 thinGrayBorder">
	<form action='<c:url value="/games/new" />' method='POST'>
		
		<p>Game URL: http://StaticVoidGames.com/games/<input type="text" name="gameUrlName"></p>

		<input name="submit" type="submit" value="Submit" />
	</form>
	</div>
	</div>
</body>
</html>