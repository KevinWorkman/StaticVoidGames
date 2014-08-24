<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
	<link rel='stylesheet' type='text/css' href='http://fonts.googleapis.com/css?family=Open+Sans' >
	<link rel="stylesheet" type="text/css" href="<c:url value="/css/general.css"/>">
	<link rel="stylesheet" type="text/css" href="<c:url value="/css/everyPage.css"/>">
	<title>Static Void Games</title>
	<link rel="shortcut icon" href="<c:url value="/images/favicon.png"/>" />
</head>
<body style="background-image:url(<c:url value="${backgroundImage}"/>);">
<%@ include file="include/navigation.jsp" %>

	<div id="contentPane">
	
		<div class="darkBackground lightText thinGrayBorder textPadding" style="float:left; width:300px; height:600px; overflow:hidden;">
			<p>Here's what's been going on lately:</p>
			<hr/>
			<c:forEach items="${events}" var="event">
				<p><a href="<c:url value="${event.getRelativeUrl() }"/>">${event.getEventText()}</a></p>
			</c:forEach>
		</div>

		<div style="margin-left:400px;">
	
	
	<div class="darkBackground thinGrayBorder textPadding topMargin" style="height:600px;">
		
			<h1>Welcome to Static Void Games!</h1>
		
			<p>Who is this site for?</p>
			<p>I'm building this site with the following people in mind:</p>
			
		    <ul>
			
			<li>Game Jammers:
				<ul>
					<li>
						You don't have time to worry about hosting and deployment.
					</li>
					<li>
						Use our easy <a href="<c:url value="/games/new" />">uploader</a> to get applet, webstart, and runnable jar versions of your game on a customizable webpage!
					</li>
					<li>
						Specify your own ad code and keep 100% of the revenue your game generates!
					</li>
				</ul>
			 
			</li>
			<li>
				Students:
				<ul>
					<li>
						Have you started learning Processing or Java and want to learn more about game development?
					</li>
					<li>
						Check out our growing list of <a href="<c:url value="/tutorials" />">tutorials</a> and the source code to real games here!
					</li>
					
				</ul>				
			</li>
			<li>
				Players:
				<ul>
				
					<li>
						Want to play some indie games and help support up-and-coming developers?
					</li>
					
					<li >
						<a href="<c:url value="/games" />">Play</a> some games and drop the developers a comment!
					</li>
				
				</ul>
				
			</li>
			
			</ul>
			
		</div>
		

		
		<div class="lightBackground darkText centeredBlock thinGrayBorder textPadding topMargin">
			<h2>Kevin's latest blog: ${latestBlog.getTitle()}</h2>
			<hr/>
			${latestBlog.getParsedText().substring(0, latestBlog.getParsedText().indexOf(".") +1)}
			
			<hr/>
			
			<p>Read more <a href="<c:url value="/blog/${latestBlog.getUrlName()}" />">here</a>.</p>
			
		</div>
	
	</div>
	
    <%@ include file="include/advertisement.jsp" %>
    
    </div>
   
</body>
</html>