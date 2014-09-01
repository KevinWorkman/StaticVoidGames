<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
	<title>IRC - Static Void Games</title>
	
	<link rel='stylesheet' type='text/css' href='http://fonts.googleapis.com/css?family=Open+Sans' >
	
	<link rel="stylesheet" type="text/css" href="<c:url value="/css/general.css"/>">
	<link rel="stylesheet" type="text/css" href="<c:url value="/css/everyPage.css"/>">
	<link rel="stylesheet" type="text/css" href="<c:url value="/css/irc.css"/>">
	
	<link rel="shortcut icon" href="<c:url value="/images/favicon.png"/>" />
</head>

<body style="background-image:url(<c:url value="${backgroundImage}"/>);">
<%@ include file="../include/navigation.jsp" %>
<div id="contentPane">
	<div class="lightBackground darkText textPadding topMargin">
		<iframe src="https://kiwiirc.com/client/irc.esper.net/?nick=nickname?&theme=cli#StaticVoidGames" style="border:0; width:100%; height:100%;"></iframe>
	</div>

    <%@ include file="../include/advertisement.jsp" %>
   </div>
</body>
</html>