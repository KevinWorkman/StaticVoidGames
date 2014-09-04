<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
	<link rel='stylesheet' type='text/css' href='http://fonts.googleapis.com/css?family=Open+Sans' >
	<link rel="stylesheet" type="text/css" href="<c:url value="/css/general.css"/>">
	<link rel="stylesheet" type="text/css" href="<c:url value="/css/everyPage.css"/>">
	<title>Notifications - Static Void Games</title>
	<link rel="shortcut icon" href="<c:url value="/images/favicon.png"/>" />
</head>
<body style="background-image:url(<c:url value="${backgroundImage}"/>);">
	<%@ include file="../include/navigation.jsp"%>
	<div id="contentPane">

<div class="darkBackground centeredBlock centered thinGrayBorder textPadding topMargin">
	<p>The page below shows your notifications. To view the site events, go <a href="<c:url value="/events" />">here</a>.</p>
	</div>
	
<div class="darkBackground centeredBlock centered thinGrayBorder textPadding topMargin">
<h2>New Notifications: </h2>

		<c:forEach items="${unviewedEventViews}" var="eventView">
			<div class="thinGrayBorder" style="height:100px;">
				<img src="${eventView.getMemberPictureUrl()}" style="width:100px; float:left;"/>
			
				<p>${eventView.getFormattedDate()} - <a href="<c:url value="${eventView.getEvent().getRelativeUrl() }"/>">${eventView.getEvent().getEventText()}</a></p>
			</div>
		</c:forEach>

</div>

	<div class="darkBackground centeredBlock centered thinGrayBorder topMargin">
	<h2>All Notifications:</h2>
	
		<c:forEach items="${allEventViews}" var="eventView">
			<div class="thinGrayBorder" style="height:100px;">
				<img src="${eventView.getMemberPictureUrl()}" style="width:100px; float:left;"/>
			
				<p>${eventView.getFormattedDate()} - <a href="<c:url value="${eventView.getEvent().getRelativeUrl() }"/>">${eventView.getEvent().getEventText()}</a></p>
			</div>
		</c:forEach>
	</div>
	
			<%@ include file="../include/advertisement.jsp" %>
   			<%@ include file="../include/openSource.jsp" %>
	
	</div>

</body>
</html>