<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>

<head>
	<title>${memberName} - Static Void Games</title>
	
	<link rel='stylesheet' type='text/css' href='http://fonts.googleapis.com/css?family=Open+Sans' >
	
	<link rel="stylesheet" type="text/css" href="<c:url value="/css/general.css"/>">
	<link rel="stylesheet" type="text/css" href="<c:url value="/css/everyPage.css"/>">
	<link rel="stylesheet" type="text/css" href="<c:url value="/css/game.css"/>">
	<link rel="stylesheet" type="text/css" href="<c:url value="/css/comment.css"/>">

	<link rel="stylesheet" type="text/css" href="<c:url value="/css/pageDown.css"/>">

	<script type="text/javascript" src="<c:url value="/js/Markdown.Converter.js"/>"></script>
	<script type="text/javascript" src="<c:url value="/js/Markdown.Sanitizer.js"/>"></script>
	<script type="text/javascript" src="<c:url value="/js/Markdown.Editor.js"/>"></script>
	<link rel="shortcut icon" href="<c:url value="/images/favicon.png"/>" />
</head>

<body style="background-image:url(<c:url value="${backgroundImage}"/>);">
	<%@ include file="../include/navigation.jsp"%>
	
	<div id="contentPane">
	
	<div class="darkBackground thinGrayBorder centeredBlock width75 centered">
		<c:if test="${isOwner}">
			<h1>
				This is your profile. <a href="<c:url value="/members/${member.getMemberName()}/edit" />">Edit it here.</a>
			</h1>
			
			<h2><a href="<c:url value="/logout" />">Logout</a></h2>
		</c:if>
	</div>
	
	
	<div class="darkBackground thinGrayBorder topMargin centeredBlock width75 centered">
		<h1>${member.getMemberName()}</h1>
	
		<img src="${profilePicture}" style="width:200px" />
	
		
		<p>${member.getParsedTag()}</p>
	</div>
	
	
	<div class="lightBackground darkText centeredBlock thinGrayBorder textPadding topMargin"> ${member.getParsedDescription()}</div>
	
	
	<c:forEach items="${publishedGames}" var="game">
		<a href="<c:url value="/games/${game.getGameName()}" />">
				<div class="smallGameLink">
				
					<img src="<c:url value="${s3Endpoint}/games/${game.getGameName()}/${game.getThumbnailUrl()}"/>" />
				
					<div class="smallGameLinkText">
						${game.getTitle()}
						<br />
						${game.getShortDescription()}
					</div>
			
				</div>
			</a>
	</c:forEach>

	<c:if test="${isOwner and not empty unpublishedGames}">
		<p>Your unpublished games (only you can see this)</p>
		<c:forEach items="${unpublishedGames}" var="game">
			<p>
				<a href="<c:url value="/games/${game.getGameName()}" />">${game.getGameName()}</a>
			</p>
		</c:forEach>
	</c:if>

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
				<div class="commentTextDiv">
					${commentView.getComment().getParsedComment()}
				</div>
		
				
				
			</div>
		</c:forEach>
	</div>

	<c:choose>
	<c:when test="${isLoggedIn}">
	<div class="pagedownDiv">
		<form action="<c:url value="/add/AccountComment/${memberName}"/>"
			method="post">

			<div class="wmd-panel">
				<div id="wmd-button-bar"></div>
				<textarea class="wmd-input" id="wmd-input" name="commentInput">Say hi!</textarea>
			</div>
			<p>Preview:</p>
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