<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
<title>Edit ${gameObj.getEscapedGameName()}</title>
	<link rel='stylesheet' type='text/css' href='http://fonts.googleapis.com/css?family=Open+Sans' >
	
	<link rel="stylesheet" type="text/css" href="<c:url value="/css/general.css"/>">
	<link rel="stylesheet" type="text/css" href="<c:url value="/css/everyPage.css"/>">
	<link rel="stylesheet" type="text/css" href="<c:url value="/css/editGame.css"/>">
	<link rel="shortcut icon" href="<c:url value="${faviconImage}"/>" />
</head>
<body style="background-size:auto; background-image:url(<c:url value="${backgroundImage}"/>);">
<%@ include file="../include/analytics.jsp"%>
	<%@ include file="../include/navigation.jsp"%>
	<div id="contentPane">
	
		<div class="editSectionDiv topMargin">
			<h3>Edit ${gameObj.getEscapedGameName()} by clicking a link below.</h3>
			<p>Done editing? Go back to your game <a href="<c:url value="/games/${gameObj.getUrlEscapedGameName()}" />">here</a>.</p>
		</div>
	                         
		<div class="editSectionDiv">
			<h3>Step 1: Edit the title and description of your game:</h3>
			<div class="editLinkDiv"><a href="<c:url value="/games/${gameObj.getUrlEscapedGameName()}/edit/title" />">Title</a></div>
			<div class="editLinkDiv"><a href="<c:url value="/games/${gameObj.getUrlEscapedGameName()}/edit/description" />">Description</a></div>
		</div>
	               
		<div class="editSectionDiv">
			<h3>Step 2: Specify how people can play your game:</h3>
			<div class="editLinkDiv"><a href="<c:url value="/games/${gameObj.getUrlEscapedGameName()}/edit/jar" />">Jar</a></div>
			<div class="editLinkDiv"><a href="<c:url value="/games/${gameObj.getUrlEscapedGameName()}/edit/android" />">Android</a></div>
			<div class="editLinkDiv"><a href="<c:url value="/games/${gameObj.getUrlEscapedGameName()}/edit/libGdxHtml" />">LibGDX HTML</a></div>
			<div class="editLinkDiv"><a href="<c:url value="/games/${gameObj.getUrlEscapedGameName()}/edit/processingJavaScript" />">Processing.js</a></div>
			<div class="editLinkDiv"><a href="<c:url value="/games/${gameObj.getUrlEscapedGameName()}/edit/javaScript" />">Pure JavaScript</a></div>
		</div>
	               
		<div class="editSectionDiv">
			<h3>Step 3: Is your game open-source?</h3>
			<div class="editLinkDiv"><a href="<c:url value="/games/${gameObj.getUrlEscapedGameName()}/edit/source" />">Source</a></div>
		</div>
	
		<div class="editSectionDiv">
			<h3>Step 4: Add background, thumbnail, and favicon images:</h3>
			<div class="editLinkDiv"><a href="<c:url value="/games/${gameObj.getUrlEscapedGameName()}/edit/images" />">Images</a></div>
		</div>
		
		<div class="editSectionDiv">
			<h3>Step 5: Publish your game!</h3>
			<div class="editLinkDiv"><a href="<c:url value="/games/${gameObj.getUrlEscapedGameName()}/edit/publish" />">Publish</a></div>
		</div>

			<%@ include file="../include/advertisement.jsp" %>
   			<%@ include file="../include/openSource.jsp" %>
		
	</div>      
</body>
</html>