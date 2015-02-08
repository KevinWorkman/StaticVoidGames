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
<%@ include file="include/analytics.jsp"%>
<%@ include file="include/navigation.jsp" %>

	<div id="contentPane">
	
		<div class="darkBackground lightText thinGrayBorder textPadding topMargin">
		
			<h1>Thanks for visiting Static Void Games.</h1>
		
		
			<p>Static Void Games offers a couple things:</p>
			
		    <ul>
			
			<li><h2>Tutorials</h2>
				<ul>
					<li>Don't know anything about code? Good! Try our <a href="<c:url value="/tutorials/hourOfCode/index" />">Hour of Code</a> and learn the basics of programming (and code your own Pong game) in an hour!</li>
					<li><a href="<c:url value="/tutorials/basicConcepts/index" />">Processing tutorials</a>: go from knowing nothing about programming to being able to program your own games!</li>
					<li><a href="<c:url value="/tutorials/basicJava/index" />">Java tutorials</a>: we have basic Java tutorials that show you how to make simple games and <a href="<c:url value="/tutorials/swing/index" />">Swing tutorials</a> that show you how to make GUI games.
				</ul>
			</li>
			<li><h2>Free Hosting</h2>
				<ul>
					<li>Free hosting for Processing, Processing.js, Java, and JavaScript games.</li>
					<li>Use our easy <a href="<c:url value="/games/new" />">uploader</a> to create a website for your game, without worrying about html.</li>
				</ul>				
			</li>
			<li><h2>Open-Source Games</h2>
				<ul>
					<li>Want to play some indie games and help support up-and-coming developers?</li>
					<li><a href="<c:url value="/games" />">Play</a> some games and drop the developers a comment!</li>
					<li>Static Void Games is entirely open source: visit us on <a href="https://github.com/KevinWorkman/StaticVoidGames">GitHub!</a></li>
				</ul>
			</li>
			
			</ul>
		</div>

	
	
	<div class="darkBackground thinGrayBorder textPadding topMargin centeredBlock centered">
			<p>Here's what's been going on lately:</p>
			<hr/>
			<c:forEach items="${events}" var="event">
				<p><a href="<c:url value="${event.getRelativeUrl() }"/>">${event.getEventText()}</a></p>
			</c:forEach>
		</div>
		

		
		<div class="lightBackground darkText centeredBlock thinGrayBorder textPadding topMargin">
			<h2>Kevin's latest blog: ${latestBlog.getTitle()}</h2>
			<hr/>
			${latestBlog.getParsedText()}
			
			<hr/>
			
			<p>Comment <a href="<c:url value="/blog/${latestBlog.getUrlName()}" />">here</a>.</p>
			
		</div>
	
	</div>
	
	
    <%@ include file="include/advertisement.jsp" %>
    <%@ include file="include/openSource.jsp" %>
    
    </div>
   
</body>
</html>