<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
<title>Edit ${gameObj.getGameName()}</title>
	<link rel='stylesheet' type='text/css' href='http://fonts.googleapis.com/css?family=Open+Sans' >
	
	<link rel="stylesheet" type="text/css" href="<c:url value="/css/general.css"/>">
	<link rel="stylesheet" type="text/css" href="<c:url value="/css/everyPage.css"/>">
	<link rel="stylesheet" type="text/css" href="<c:url value="/css/editGame.css"/>">
</head>
<body style="background-size:auto; background-image:url(<c:url value="${backgroundImage}"/>);">
<%@ include file="../include/analytics.jsp"%>
	<%@ include file="../include/navigation.jsp"%>
	<div id="contentPane">
	
		<div class="editSectionDiv topMargin">
			<h3>Edit ${gameObj.getGameName()} by clicking a link below.</h3>
			<p>Done editing? Go back to your game <a href="<c:url value="/games/${gameObj.getGameName()}" />">here</a>.</p>
		</div>
	                         
		<div class="editSectionDiv">
			<h3>Step 1: Edit the title and description of your game:</h3>
			<div class="editLinkDiv"><a href="<c:url value="/games/${gameObj.getGameName()}/edit/title" />">Title</a></div>
			<div class="editLinkDiv"><a href="<c:url value="/games/${gameObj.getGameName()}/edit/description" />">Description</a></div>
		</div>
	               
		<div class="editSectionDiv">
			<h3>Step 2: Specify how people can play your game:</h3>
			<div class="editLinkDiv"><a href="<c:url value="/games/${gameObj.getGameName()}/edit/jar" />">Jar</a></div>
			<div class="editLinkDiv"><a href="<c:url value="/games/${gameObj.getGameName()}/edit/android" />">Android</a></div>
		</div>
	               
		<div class="editSectionDiv">
			<h3>Step 3: Is your game open-source?</h3>
			<div class="editLinkDiv"><a href="<c:url value="/games/${gameObj.getGameName()}/edit/source" />">Source</a></div>
		</div>
	
		<div class="editSectionDiv">
			<h3>Step 4: Add background, thumbnail, and favicon images:</h3>
			<div class="editLinkDiv"><a href="<c:url value="/games/${gameObj.getGameName()}/edit/images" />">Images</a></div>
		</div>
		
		<div class="editSectionDiv">
			<h3>Step 5: Publish your game!</h3>
			<div class="editLinkDiv"><a href="<c:url value="/games/${gameObj.getGameName()}/edit/publish" />">Publish</a></div>
		</div>
	
		<div class="editSectionDiv">
			<h3>These settings are deprecated but are available for legacy games:</h3>
			<div class="editLinkDiv">
	 			<a href="<c:url value="/games/${gameObj.getGameName()}/edit/webstart" />">DEPRECATED: Webstart</a>
			</div>
	
			<div class="editLinkDiv">
				<a href="<c:url value="/games/${gameObj.getGameName()}/edit/applet" />">DEPRECATED: Applet</a>
			</div>
	
			<div class="editLinkDiv">
				<a href="<c:url value="/games/${gameObj.getGameName()}/edit/deprecated" />">Other Deprecated Settings</a>
			</div>
		</div>
	
	 	<div class="editLinkDiv">
	 		<a href="<c:url value="/games/${gameObj.getGameName()}/edit/rating" />">Content Rating</a>
		</div>
		
			<%@ include file="../include/advertisement.jsp" %>
   			<%@ include file="../include/openSource.jsp" %>
		
	</div>      
</body>
</html>