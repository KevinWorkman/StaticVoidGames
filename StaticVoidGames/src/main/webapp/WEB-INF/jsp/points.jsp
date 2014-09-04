<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
<link rel='stylesheet' type='text/css' href='http://fonts.googleapis.com/css?family=Open+Sans' >
<link rel="stylesheet" type="text/css" href="<c:url value="/css/general.css"/>">
<link rel="stylesheet" type="text/css" href="<c:url value="/css/everyPage.css"/>">
<title>Static Void Games</title>



	<link rel="shortcut icon" href="<c:url value="/images/favicon.png"/>" />
</head>
<body style="background-image:url(<c:url value="${backgroundImage}"/>);">



<%@ include file="include/navigation.jsp" %>
<div id="contentPane">


	<h1>Standings</h1>
	
	

	<c:forEach items="${memberPointsViews}" var="mpv">
		<p>${mpv.getMember().getMemberName()} - ${mpv.getPoints()}</p>
	</c:forEach>
	
	
	    <%@ include file="include/advertisement.jsp" %>
    	<%@ include file="include/openSource.jsp" %>
	
	</div>
	
	

</body>
</html>