<!DOCTYPE html>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
	<title>Games - Static Void Games</title>
	<link rel="shortcut icon" href="<c:url value="/images/favicon.png"/>" />
	
	<!-- Bootstrap core CSS -->
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/css/bootstrap.min.css">
    <link rel="stylesheet" href="https://bootswatch.com/cyborg/bootstrap.min.css">
    <link rel="stylesheet" type="text/css" href="<c:url value="/css/bs.css"/>">
    
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
    
    <style>
	.thumbnailx imgx{
			width:64px;
			height:64px;
		}
	</style>
	
</head>
<body style="background-image:url(<c:url value="${backgroundImage}"/>);">


<%@ include file="../include/analytics.jsp"%>
<%@ include file="../include/navigation3.jsp" %>
    <div class="container">
    
    
    <div class="panel panel-default" style="margin-top: 50px">
		<div class="panel-heading">All Games</div>
		<div class="panel-body">
		
		<div class="row">
		<c:forEach items="${games}" var="game" varStatus="loop">
					
					
					<c:if test="${not loop.first and loop.index % 6 == 0}">
	                	</div><div class="row">
	            	</c:if>
					
						<a href="<c:url value="/games/${game.getGameName()}" />">
							<div class="col-xs-2">
								
								<div class="panel panel-primary">
									<div class="panel-heading">${game.getTitle()}</div>
									<div class="panel-body">
										<c:choose>
										<c:when test="${game.getThumbnailUrl() == null}"><img src="<c:url value="${s3Endpoint}/images/staticVoidProfile3.png"/>" style="max-width:100%;" /></c:when>
										<c:otherwise><img src="<c:url value="${s3Endpoint}/games/${game.getGameName()}/${game.getThumbnailUrl()}"/>" style="max-width:100%;" /></c:otherwise>
										</c:choose>
									</div>
								
									<div class="panel-footer">
										<c:choose>
										<c:when test="${game.shortDescription == null || game.shortDescription.isEmpty() || game.shortDescription == \"null\"}"></c:when>
										<c:otherwise><p>${game.getShortDescription()}</p></c:otherwise>
										</c:choose>
									</div>
								</div>
							</div>
							</a>
						
					</c:forEach>
		</div>
		</div>
		<div class="panel-footer"><a href="<c:url value="/upload" />">Upload your game here!</a></div>
	</div>


	<%@ include file="../include/advertisement.jsp" %>
   	<%@ include file="../include/openSource.jsp" %>
	</div>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/js/bootstrap.min.js"></script>
</body>
</html>