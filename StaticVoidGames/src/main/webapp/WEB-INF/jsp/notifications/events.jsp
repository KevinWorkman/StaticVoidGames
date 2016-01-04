<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
	<title>Events - Static Void Games</title>
	
	<link rel='stylesheet' type='text/css' href='http://fonts.googleapis.com/css?family=Open+Sans' >
	
	<link rel="stylesheet" type="text/css" href="<c:url value="/css/general.css"/>">
	<link rel="stylesheet" type="text/css" href="<c:url value="/css/everyPage.css"/>">
	<link rel="stylesheet" type="text/css" href="<c:url value="/css/comment.css"/>">
	
	<link rel="shortcut icon" href="<c:url value="/images/favicon.png"/>" />

</head>
<body style="background-image:url(<c:url value="${backgroundImage}"/>);">
<%@ include file="../include/analytics.jsp"%>
	<%@ include file="../include/navigation.jsp"%>
<div id="contentPane">
	<h1>Events</h1>
	<div>
		<c:forEach items="${eventView}" var="eventView">
			<div class="thinGrayBorder darkBackground" style="overflow: auto; margin-bottom:25px;">
			
				<div class="profilePreviewDiv">
			
					<img src="<c:url value="${eventView.getMemberPictureUrl() != null ? eventView.getMemberPictureUrl() : '/images/defaultProfilePictures/profile1.jpg'}" />"/>
			
					
					<p><a href="<c:url value="/members/${eventView.getMember().getMemberName()}" />">${eventView.getMember().getMemberName()}</a></p>
					<hr/>
					<p>${eventView.getMember().getTag()}</p>
			
				</div>
				
				<div class="centered">
					<p>${eventView.getFormattedDate()}</p>
				</div>
				<hr />
				<div class="centered">
					<p><a href="<c:url value="${eventView.getEvent().getRelativeUrl() }"/>">${eventView.getEvent().getEventText()}</a></p>
				</div>
			</div>
		</c:forEach>
		</div>
		
			<%@ include file="../include/advertisement.jsp" %>
   			<%@ include file="../include/openSource.jsp" %>
		
</div>

</body>
</html>