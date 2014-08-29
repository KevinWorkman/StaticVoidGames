<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
	<title>${game.getTitle()} - Static Void Games</title>
	
	<link rel='stylesheet' type='text/css' href='http://fonts.googleapis.com/css?family=Open+Sans' >
	
	<link rel="stylesheet" type="text/css" href="<c:url value="/css/general.css"/>">
	<link rel="stylesheet" type="text/css" href="<c:url value="/css/everyPage.css"/>">
	<link rel="stylesheet" type="text/css" href="<c:url value="/css/game.css"/>">
	<link rel="stylesheet" type="text/css" href="<c:url value="/css/comment.css"/>">
	
	<link rel="stylesheet" type="text/css" href="<c:url value="/css/pageDown.css"/>">
	
	<script type="text/javascript" src="<c:url value="/js/Markdown.Converter.js"/>"></script>
	<script type="text/javascript" src="<c:url value="/js/Markdown.Sanitizer.js"/>"></script>
	<script type="text/javascript" src="<c:url value="/js/Markdown.Editor.js"/>"></script>
	
	<link rel="shortcut icon" href="<c:url value="${faviconImage}"/>" />
</head>

<body style="background-size:auto; background-image:url(<c:url value="${backgroundImage}"/>);">
	
<%@ include file="../include/navigation.jsp" %>
<div id="contentPane">

	<c:if test="${isOwner}">
		<div class="darkBackground thinGrayBorder centeredBlock width75 centered">
			<h3>This is your game. <a href="<c:url value="/games/${game.getGameName()}/edit/" />">Edit it here.</a></h3>
		</div>
	</c:if>
	
	<div class="darkBackground centeredBlock thinGrayBorder topMargin width75 centered">
		<h1>${game.getTitle()}</h1>
		<img style="width:100px" src="${thumbnailImage}"/>
		<p>${game.getShortDescription()}</p>
	</div>
	
	<div class="darkBackground centeredBlock thinGrayBorder topMargin width75 centered">
			<c:choose>
				<c:when test="${isPlayable}">
				<h3>Play this game!</h3>
				</c:when>
				<c:otherwise>
				<h3>This game isn't playable yet.</h3>
				</c:otherwise>
			</c:choose>

		<c:if test="${isJar}">
			<p><a href="<c:url value="${jarUrl}"/>" >Jar</a></p>
		</c:if>

		<c:if test="${hasExecutables}">
			<c:forEach items="${gameExecutables}" var="gameExecutable">
				<p><a href="<c:url value="${s3Endpoint}/games/${game.getGameName()}/${gameExecutable.getUrl()}"/>" >${gameExecutable.getLabel()}</a></p>
			</c:forEach>
		</c:if>
		
		<c:if test="${isApplet}">
			<p><a href="<c:url value="${appletJnlpUrl}"/>" >Applet JNLP</a></p>
		</c:if>
		
		<c:if test="${isWebstart}">
			<p><a href="<c:url value="${webstartJnlpUrl}"/>" >WebStart JNLP</a></p>
		</c:if>
	</div>
	
	<c:if test="${isAndroid}">
		<div class="darkBackground centeredBlock thinGrayBorder topMargin width75 centered">
			<h3>Android</h3>
			<c:if test="${hasApk}">
				<h3>Download the APK <a href="${apkUrl}">here</a>.</h3>
			</c:if>
			${androidText}
		</div>
	
		<p><a href="<c:url value="${jarUrl}"/>" >Jar</a></p>
	</c:if>


	<c:if test="${isOpenSource}">
		<div class="darkBackground centeredBlock thinGrayBorder topMargin width75 centered">
			<h3>This game is open source. Download the source <a href="<c:url value="${sourceUrl}"/>">here</a>.</h3>
				${sourceText}
		</div>
	</c:if>


	<div class="lightBackground darkText centeredBlock thinGrayBorder textPadding topMargin">
		${game.getGameDescription()}
	</div>

	<div class="commentScrollPaneDiv">
		<c:forEach items="${commentViews}" var="commentView">
	
			<div class="singleCommentDiv" style="overflow: auto;">
				
				<div class="profilePreviewDiv">
					
					<img src="<c:url value="${commentView.getMemberPictureUrl() != null ? commentView.getMemberPictureUrl() : '/images/defaultProfilePictures/profile1.jpg'}" />"/>
					
					<p><a href="<c:url value="/members/${commentView.getMember().getMemberName()}" />">${commentView.getMember().getMemberName()}</a></p>
					<hr/>
					<p>${commentView.getMember().getParsedTag()}</p>
					
				</div>
		
				<hr />
				<div class="centered">
					<p>${commentView.getFormattedDate()}
					
					<%-- TODO: this is stupid, but I can't figure out how to do this in one if statement. Should probably be on the server anyway? --%>
					<c:choose>
						<c:when test="${isOwner}">
							<span style="border:thin solid red; float:right; margin-right:100px;"><a href="<c:url value="/delete/comment/${commentView.getComment().getId()}"/>">Delete</a></span>
						</c:when>
						<c:when test="${loggedInUser eq commentView.getComment().getCommentingMember()}">
							<span style="border:thin solid red; float:right;margin-right:100px;"><a href="<c:url value="/delete/comment/${commentView.getComment().getId()}"/>">Delete</a></span>
						</c:when>
					</c:choose>
					
					</p>
					
				</div>
				<hr />
				<div class="commentTextDiv">${commentView.getComment().getParsedComment()}</div>
			</div>
		</c:forEach>
	</div>
	
	<c:choose>
	<c:when test="${isLoggedIn}">
	<div class="pagedownDiv">
		<form action="<c:url value="/add/GameComment/${game.getGameName()}"/>" method="post">

			<div class="wmd-panel">
				<div id="wmd-button-bar"></div>
				<textarea class="wmd-input" id="wmd-input" name="commentInput">Comment on this game!</textarea>
			</div>
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
	</c:when>
	<c:otherwise>
		<div class="centeredBlock centered darkBackground"><a href="<c:url value="/login" />">Login</a> to leave a comment!</div>
	</c:otherwise>
	</c:choose>

    <%@ include file="../include/advertisement.jsp" %>

	</div>
	
</body>
</html>