<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
<title>${blog.getTitle()} by ${blog.getMember() } - Static Void Games</title>

	<link rel='stylesheet' type='text/css' href='http://fonts.googleapis.com/css?family=Open+Sans' >
	
	<link rel="stylesheet" type="text/css" href="<c:url value="/css/general.css"/>">
	<link rel="stylesheet" type="text/css" href="<c:url value="/css/everyPage.css"/>">
	<link rel="stylesheet" type="text/css" href="<c:url value="/css/blog.css"/>">
	<link rel="stylesheet" type="text/css" href="<c:url value="/css/comment.css"/>">
	<link rel="stylesheet" type="text/css" href="<c:url value="/css/pageDown.css"/>">

	<script type="text/javascript" src="<c:url value="/js/Markdown.Converter.js" />"></script>
	<script type="text/javascript" src="<c:url value="/js/Markdown.Sanitizer.js" />"></script>
	<script type="text/javascript" src="<c:url value="/js/Markdown.Editor.js" />"></script>

	<link rel="shortcut icon" href="<c:url value="/images/favicon.png"/>" />
</head>

<body style="background-image:url(<c:url value="${backgroundImage}"/>);">

<%@ include file="../include/navigation.jsp" %>

<div id="contentPane">

	<div class="darkBackground centered centeredBlock">
	<c:if test="${isOwner}">
		<p>This is your blog post.</p>
		<a href="<c:url value="/blog/${blog.getUrlName()}/edit/" />">Edit</a>
	</c:if>
	
	
	<h1>${blog.getTitle()}</h1>
	<p>Author: <a href="<c:url value="/members/${blog.getMember()}" />">${blog.getMember()}</a></p>
	</div>
	
	<div class="lightBackground darkText textPadding">${blog.getParsedText()}</div>
	
	<div class="centered centeredBlock darkBackground"><h2 style="margin-bottom:0px;">Comments</h2></div>
	<div class="commentScrollPaneDiv">
		<c:forEach items="${commentViews}" var="commentView">
	
			<div class="singleCommentDiv" style="overflow: auto;">
		
				<div class="profilePreviewDiv">
					<img src="<c:url value="${commentView.getMemberPictureUrl() != null ? commentView.getMemberPictureUrl() : '/images/defaultProfilePictures/profile1.jpg'}" />"/>
					<p><a href="<c:url value="/members/${commentView.getMember().getMemberName()}" />">${commentView.getMember().getMemberName()}</a></p>
					<hr/>
					<p>${commentView.getMember().getTag()}</p>
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
		<form action="<c:url value="/add/BlogComment/${blog.getUrlName()}"/>"
			method="post">

			<div class="wmd-panel">
				<div id="wmd-button-bar"></div>
				<textarea class="wmd-input" id="wmd-input" name="commentInput">Comment on this blog!</textarea>
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
	</c:when>
	<c:otherwise>
		<div class="centeredBlock centered darkBackground"><a href="<c:url value="/login" />">Login</a> to leave a comment!</div>
	</c:otherwise>
	</c:choose>
	
		<%@ include file="../include/advertisement.jsp" %>
	</div>
	
</body>
</html>