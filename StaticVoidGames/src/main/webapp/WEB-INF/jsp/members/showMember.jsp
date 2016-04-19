<!DOCTYPE html>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>

<head>
	<title>${memberName} - Static Void Games</title>
	<link rel="shortcut icon" href="<c:url value="/images/favicon.png"/>" />
	
  	<!-- Bootstrap core CSS -->
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/css/bootstrap.min.css">
    <link rel="stylesheet" href="https://bootswatch.com/cyborg/bootstrap.min.css">
    
    <link rel="stylesheet" type="text/css" href="<c:url value="/css/bs.css"/>">

	<link rel="stylesheet" type="text/css" href="<c:url value="/css/pageDown.css"/>">
	
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>

	<script type="text/javascript" src="<c:url value="/js/Markdown.Converter.js"/>"></script>
	<script type="text/javascript" src="<c:url value="/js/Markdown.Sanitizer.js"/>"></script>
	<script type="text/javascript" src="<c:url value="/js/Markdown.Editor.js"/>"></script>
	
	<script>var baseUrl = '<c:url value="/"/>';</script>
	<script src="<c:url value="/js/PointsGetter.js"/>"></script>
	<script>$(updateAllPoints);</script>
	
	<script>
	var viewedMember = '${member.getMemberName()}';
	
	function updateStatsAndToDo(){
		
		$.ajax({url: baseUrl+"members/" + viewedMember + "/stats", success: function(result){
			var obj = $.parseJSON(result);
			
			$("#memberStats").append("<ul>");
			$("#memberStats").append("<li>" + obj.gameCount + " games * 100 +</li>");
			$("#memberStats").append("<li>" + obj.blogCount + " blogs * 20 +</li>");
			$("#memberStats").append("<li>" + obj.commentCount + " comments * 10 +</li>");
			$("#memberStats").append("<li>" + obj.topics + " forum topics * 10 +</li>");
			$("#memberStats").append("<li>" + obj.replies + " forum replies * 5 +</li>");
			$("#memberStats").append("<li>" + obj.likesReceived + " forum likes received * 2 +</li>");
			$("#memberStats").append("<li>" + obj.likesGiven + " forum likes given * 1</li>");
			$("#memberStats").append("</ul>");
			
		}});
		
		<c:if test="${isOwner}">
			$.ajax({url: baseUrl+"members/" + viewedMember + "/todo", success: function(result){
				
				if(result){
				
					var todo = $.parseJSON(result);
					
					$("#todoList").append("<ul>");
					$.each(todo, function(i, item) {
						$("#todoList").append("<li>" + item + "</li>");
					});
					$("#todoList").append("</ul>");
					
					$("#todo").show();
				}
			}});
		</c:if>
		
		}
	
	$(updateStatsAndToDo);
	</script>
	
	<style>
		.profilePic{
			width:64px;
			height:64px;
		}
	</style>
	
</head>

<body style="background-image:url(<c:url value="${backgroundImage}"/>);">
<%@ include file="../include/analytics.jsp"%>
<%@ include file="../include/navigation3.jsp" %>
	
    <div class="container">
	
		<c:if test="${isOwner}">
		
		<div style="width:250px; margin-left:auto; margin-right:auto;">
			<div class="panel panel-default" style="margin-top:25px;">
				<div class="panel-heading">This is your profile.</div>
				<div class="panel-body">
					<p><a href="<c:url value="/members/${member.getMemberName()}/edit" />">Edit it here.</a></p>	
					<p>Change your password <a href="<c:url value="/members/${member.getMemberName()}/changePassword" />">here</a>.</p>		
					<p><a href="<c:url value="/logout" />">Logout</a></p>
				</div>
			</div>
		</div>
		
		<div id="todo" style="width:500px; margin-left:auto; margin-right:auto; display:none;">
			<div class="panel panel-default" style="margin-top:25px;">
				<div class="panel-heading">What next?</div>
				<div class="panel-body">
					<p>Here are some things you might try doing:</p>
					<div id="todoList"></div>
				</div>
			</div>
		</div>
		
		
		
		
		
		</c:if>

	
	
	<div class="panel panel-default" style="margin-top:25px;">
		<div class="panel-heading"><img src="${profilePicture}" style="width:200px" /><h1 style="display:inline-block">${member.getMemberName()}</h1></div>
		<div class="panel-body">${member.getParsedDescription()}</div>
	</div>
	
	<div class="panel panel-default">
		<div class="panel-heading">${member.getMemberName()}'s Points</div>
		<div class="panel-body">
			<span class="points ${member.getMemberName()}">[]</span>	
			<span id="memberStats"></span>
		</div>
	</div>
	
	<c:if test="${not empty publishedGames}">
		<div class="panel panel-default">
			<div class="panel-heading">${member.getMemberName()}'s Games</div>
				<div class="panel-body">
		
					<div class="row">
					<c:forEach items="${publishedGames}" var="game" varStatus="loop">
					
					
					<c:if test="${not loop.first and loop.index % 4 == 0}">
	                	</div><div class="row">
	            	</c:if>
					
						<a href="<c:url value="/games/${game.getGameName()}" />">
							<div class="col-xs-3">
								
								<div class="panel panel-primary">
									<div class="panel-heading">${game.getTitle()}</div>
									<div class="panel-body"><img src="<c:url value="${s3Endpoint}/games/${game.getGameName()}/${game.getThumbnailUrl()}"/>" style="max-width:100%;"/></div>
									<div class="panel-footer">${game.getShortDescription()}</div>
								</div>
							</div>
						</a>
						
						
						
					</c:forEach>
				</div>
					
			</div>
		</div>
	</c:if>


	<c:if test="${isOwner and not empty unpublishedGames}">
		<div class="panel panel-default">
			<div class="panel-heading">Your Unpublished Games - Only you can see this!</div>
				<div class="panel-body">
		
					<div class="row">
					<c:forEach items="${unpublishedGames}" var="game" varStatus="loop">
					
					
					<c:if test="${not loop.first and loop.index % 4 == 0}">
	                	</div><div class="row">
	            	</c:if>
					
						<a href="<c:url value="/games/${game.getGameName()}" />">
							<div class="col-xs-3">
								
								<div class="panel panel-info">
									<div class="panel-heading">${game.getTitle()}</div>
									<div class="panel-body"><img src="<c:url value="${s3Endpoint}/games/${game.getGameName()}/${game.getThumbnailUrl()}"/>" style="max-width:100%;"/></div>
									<div class="panel-footer">${game.getShortDescription()}</div>
								</div>
							</div>
						</a>
						
						
						
					</c:forEach>
					</div>
					
			</div>
		</div>
	</c:if>





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
  					${commentView.getParsedCommentText()}
  				</div>
			</div>
		</div>
	</c:forEach>



<div class="col-xs-12">
		<c:choose>
		<c:when test="${isLoggedIn}">
			<div class="panel panel-default">
				<div class="panel-heading">Say hello!</div>
				<div class="panel-body">
					<form action="<c:url value="/add/AccountComment/${memberName}"/>" method="post">
			
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

<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/js/bootstrap.min.js"></script>
</body>
</html>