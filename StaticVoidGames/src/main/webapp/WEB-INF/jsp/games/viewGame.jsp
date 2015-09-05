<!DOCTYPE html>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
	<title>${game.getTitle()} - Static Void Games</title>
		
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/css/bootstrap.min.css">
    <link rel="stylesheet" href="https://bootswatch.com/cyborg/bootstrap.min.css">
    
    <link rel="stylesheet" type="text/css" href="<c:url value="/css/bs.css"/>">
	
	<link rel="stylesheet" type="text/css" href="<c:url value="/css/pageDown.css"/>">
	
	<script type="text/javascript" src="<c:url value="/js/Markdown.Converter.js"/>"></script>
	<script type="text/javascript" src="<c:url value="/js/Markdown.Sanitizer.js"/>"></script>
	<script type="text/javascript" src="<c:url value="/js/Markdown.Editor.js"/>"></script>
	
	<link rel="shortcut icon" href="<c:url value="${faviconImage}"/>" />
	
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
	<script>var baseUrl = '<c:url value="/"/>';</script>
	<script src="<c:url value="/js/PointsGetter.js"/>"></script>
	<script>$(updateAllPoints);</script>
	
	<style>
		.profilePic{
			width:64px;
			height:64px;
		}
	</style>
	
</head>

<body style="background-size:auto; background-image:url(<c:url value="${backgroundImage}"/>);">
<%@ include file="../include/analytics.jsp"%>
<%@ include file="../include/navigation3.jsp" %>
<div class="container">

	<c:if test="${isOwner}">
		
		<div style="width:250px; margin-left:auto; margin-right:auto;">
			<div class="panel panel-default" style="margin-top:25px;">
				<div class="panel-heading">This is your game.</div>
				<div class="panel-body"><a href="<c:url value="/games/${game.getGameName()}/edit/" />">Edit it here.</a></div>
			</div>
		</div>
		
	</c:if>
	
	
		
	
	<div class="panel panel-default text-center" style="width:500px; margin-left:auto; margin-right:auto; margin-top:25px;">
		<div class="panel-heading"><h1>${game.getTitle()}</h1></div>
		<div class="panel-body">
			<img style="width:100px" src="${thumbnailImage}"/>
			<p>${game.getShortDescription()}</p>
			<p>This is a game by <a href="<c:url value="/members/${game.getMember()}" />">${game.getMember()}</a>.</p>
		</div>
	</div>
	
	
			
		<c:if test="${game.isShowLibGdxHtml()}">
		<div class="panel panel-default">
			<div class="panel-heading"><h4>LibGDX</h4></div>
			<div class="panel-body"><h3>Play this libGDX game in your browser <a href="${libGdxHtmlLink}">here</a>.</h3></div>
		</div>
		</c:if>
		
		<c:if test="${game.isShowProcessingJavaScript()}">
		<div class="panel panel-default">
			<div class="panel-heading"><h4>Processing.js</h4></div>
			<div class="panel-body">
				<h3>Play this Processing.js game in your browser <a href="${processingJavaScriptLink}">here</a>.</h3>
			</div>
		</div>
		</c:if>
		
		<c:if test="${game.isShowJavaScript()}">
		<div class="panel panel-default">
			<div class="panel-heading"><h4>JavaScript</h4></div>
			<div class="panel-body">
				<h3>Play this JavaScript game in your browser <a href="${pureJavaScriptLink}">here</a>.</h3>
			</div>
		</div>
		</c:if>
		
		<c:if test="${isAndroid}">
		<div class="panel panel-default">
			<div class="panel-heading"><h4>Android</h4></div>
			<div class="panel-body">
				<c:if test="${hasApk}">
					<h3>Download the APK <a href="${apkUrl}">here</a>.</h3>
				</c:if>
				${androidText}
			</div>
		</div>
		</c:if>
			

		<c:if test="${isJar}">
		<div class="panel panel-default">
			<div class="panel-heading"><h4>Desktop</h4></div>
			<div class="panel-body">
				<p>Play this game offline by downloading the <a href="<c:url value="${jarUrl}"/>" >jar</a>.</p>
			</div>
		</div>
		</c:if>

		<c:if test="${hasExecutables}">
			<div class="panel panel-default">
				<div class="panel-heading"><h4>Executables</h4></div>
				<div class="panel-body">
					<c:forEach items="${gameExecutables}" var="gameExecutable">
						<p><a href="<c:url value="${s3Endpoint}/games/${game.getGameName()}/${gameExecutable.getUrl()}"/>" >${gameExecutable.getLabel()}</a></p>
					</c:forEach>
				</div>
			</div>
		</c:if>
		

	

	<c:if test="${isOpenSource}">
		<div class="panel panel-default">
			<div class="panel-heading"><h4>Open Source</h4></div>
			<div class="panel-body">
				<h5>Download the source <a href="<c:url value="${sourceUrl}"/>">here</a>.</h5>
					${sourceText}
			</div>
		</div>
	</c:if>
	



	<div class="panel panel-default">
	<div class="panel-heading"><h4>About</h4></div>
	<div class="panel-body">${game.getEscapedDescription()}</div>
	</div>



	<c:forEach items="${commentViews}" var="commentView">
		<div class="col-xs-12">
			<div class="panel panel-default">
  				<div class="panel-heading">
	    			<a href="<c:url value="/members/${commentView.getMember().getMemberName()}" />">
	      				<img class="profilePic" src="<c:url value="${commentView.getMemberPictureUrl() != null ? commentView.getMemberPictureUrl() : '/images/defaultProfilePictures/profile1.jpg'}" />"/>
	    				${commentView.getMember().getMemberName()}</a> <span class="points ${commentView.getMember().getMemberName()}">[]</span> - ${commentView.getFormattedDate()}
	    				<%-- TODO: this is stupid, but I can't figure out how to do this in one if statement. Should probably be on the server anyway? --%>
						<c:choose>
							<c:when test="${isOwner}">
								<span style="border:thin solid red; float:right; margin-right:100px;"><a href="<c:url value="/delete/comment/${commentView.getComment().getId()}"/>">Delete</a></span>
							</c:when>
							<c:when test="${loggedInUser eq commentView.getComment().getCommentingMember()}">
								<span style="border:thin solid red; float:right;margin-right:100px;"><a href="<c:url value="/delete/comment/${commentView.getComment().getId()}"/>">Delete</a></span>
							</c:when>
						</c:choose>
						
	    			
  				</div>
  				<div class="panel-body">
  					${commentView.getComment().getParsedComment()}
  				</div>
			</div>
		</div>
	</c:forEach>



	<div class="col-xs-12">
		<c:choose>
		<c:when test="${isLoggedIn}">
			<div class="panel panel-default">
				<div class="panel-heading">Leave a comment!</div>
				<div class="panel-body">
					<form action="<c:url value="/add/GameComment/${game.getGameName()}"/>" method="post">
			
						<div class="wmd-panel">
							<div id="wmd-button-bar"></div>
							<textarea class="wmd-input" id="wmd-input" name="commentInput"></textarea>
						</div>
						<div class="wmd-panel"><p>Preview:</p></div>
						<div id="wmd-preview" class="wmd-panel wmd-preview"></div>
						
						<script type="text/javascript">
							(function() {
								var converter = Markdown.getSanitizingConverter();
								var editor = new Markdown.Editor(converter);
								editor.run();
			
							})();
						</script>
			
						<div class="centered"><input type="submit" value="Comment"></div>
			
					</form>
				</div>
			</div>
		</c:when>
		<c:otherwise>
			<div class="panel panel-default">
				<div class="panel-heading">Leave a comment!</div>
				<div class="panel-body"><a href="<c:url value="/login" />">Login</a> to leave a comment!</div>
			</div>
		</c:otherwise>
		</c:choose>
	</div>



	

			<%@ include file="../include/advertisement.jsp" %>
   			<%@ include file="../include/openSource.jsp" %>

	</div>
	
</body>
</html>