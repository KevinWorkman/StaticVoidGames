<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<title>Edit ${gameObj.getEscapedGameName()}</title>
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/css/bootstrap.min.css">
    <link rel="stylesheet" href="https://bootswatch.com/cyborg/bootstrap.min.css">
    <link rel="stylesheet" type="text/css" href="<c:url value="/css/bs.css"/>">
	<link rel="shortcut icon" href="<c:url value="${faviconImage}"/>" />
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/js/bootstrap.min.js"></script>
	
	<style>
		.editStep{
			width:400px;
			margin-left:auto;
			margin-right:auto; 
		}
	</style>
</head>
<body style="background-size:auto; background-image:url(<c:url value="${backgroundImage}"/>);">
<%@ include file="../include/analytics.jsp"%>
<%@ include file="../include/navigation3.jsp"%>
	<div id="container">
	
		<div class="panel panel-default text-center" style="margin-left:auto; margin-right:auto; margin-top:25px; width:500px">
	
				<div class="panel-heading">Edit ${gameObj.getEscapedGameName()} </div>
				<div class="panel-body">
					<p>Click a link below.</p>
					<p>Done editing? Go back to your game <a href="<c:url value="/games/${gameObj.getUrlEscapedGameName()}" />">here</a>.</p>
				</div>
		</div>
	                         
		<div class="panel panel-default text-center editStep">
			<div class="panel-heading">Step 1: Edit the title and description of your game:</div>
			<div class="panel-body">
				<a class="btn btn-default" href="<c:url value="/games/${gameObj.getUrlEscapedGameName()}/edit/title" />">Title</a>
				<a class="btn btn-default" href="<c:url value="/games/${gameObj.getUrlEscapedGameName()}/edit/description" />">Description</a>
			</div>
		</div>
	               
		<div class="panel panel-default text-center editStep">
			<div class="panel-heading">Step 2: Specify how people can play your game:</div>
			<div class="panel-body">
				<a class="btn btn-default" href="<c:url value="/games/${gameObj.getUrlEscapedGameName()}/edit/jar" />">Jar</a>
				<a class="btn btn-default" href="<c:url value="/games/${gameObj.getUrlEscapedGameName()}/edit/android" />">Android</a>
				<a class="btn btn-default" href="<c:url value="/games/${gameObj.getUrlEscapedGameName()}/edit/libGdxHtml" />">LibGDX HTML</a>
				<a class="btn btn-default" href="<c:url value="/games/${gameObj.getUrlEscapedGameName()}/edit/processingJavaScript" />">Processing.js</a>
				<a class="btn btn-default" href="<c:url value="/games/${gameObj.getUrlEscapedGameName()}/edit/javaScript" />">Pure JavaScript</a>
			</div>
		</div>
	               
		<div class="panel panel-default text-center editStep">
			<div class="panel-heading">Step 3: Is your game open-source?</div>
			<div class="panel-body">
				<a class="btn btn-default" href="<c:url value="/games/${gameObj.getUrlEscapedGameName()}/edit/source" />">Source</a>
			</div>
		</div>
	
		<div class="panel panel-default text-center editStep">
			<div class="panel-heading">Step 4: Add background, thumbnail, and favicon images:</div>
			<div class="panel-body">
				<a class="btn btn-default" href="<c:url value="/games/${gameObj.getUrlEscapedGameName()}/edit/images" />">Images</a>
			</div>
		</div>
		
		<div class="panel panel-default text-center editStep">
			<div class="panel-heading">Step 5: Publish your game!</div>
			<div class="panel-body">
				<a class="btn btn-default" href="<c:url value="/games/${gameObj.getUrlEscapedGameName()}/edit/publish" />">Publish</a>
			</div>
		</div>

			<%@ include file="../include/advertisement.jsp" %>
   			<%@ include file="../include/openSource.jsp" %>
		
	</div>      
</body>
</html>