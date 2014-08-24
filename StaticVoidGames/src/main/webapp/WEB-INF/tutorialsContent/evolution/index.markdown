<%
session.setAttribute("loginRedirect", request.getRequestURL());
session.setAttribute("chapter", "evolution");
session.removeAttribute("section");
%>
<!DOCTYPE HTML>
<html>

<head>
<title>Static Void Games - Learn</title>

<%@ include file="../../include/meta.inc"%>

<meta name="description" content="Static Void Games- Play indie games, learn how to program by following our game programming tutorials, and upload your own indie games!">
<meta name="keywords" content="java, code, samples, indie, game, static, void, games">
<link rel="stylesheet" type="text/css" href="../../styles/tutorialStyles.css" />


<%@ include file="../../include/jspCss.inc"%>
<%@ include file="../../include/analytics.inc"%>

<link rel="stylesheet" type="text/css"
	href="http://s3.staticvoidgames.com/javascript/prettify/sunburst.css" />
<script type="text/javascript" src="http://s3.staticvoidgames.com/javascript/prettify/prettify.js"></script>

</head>

<body onload="prettyPrint()">


<%@ include file="../../include/topBar.inc"%>
<%@ include file="../include/tutorialNavigation.jsp"%>

	<div class="wrap">
	
	<%@ include file="../../include/announcement.inc"%>


			<h1 class="chapterTitle">Evolution Framework</h1>
						
		<div id="rightTutorialBody">
		
			<p>This is a temporary placeholder for the evolutionary framework tutorial.</p>
			
			<img src="http://s3.StaticVoidGames.com/learn/evolution/longMaze1.png" />
			
			<p>Soon I will have a real tutorial here, but for now, feel free to:</p>
			
			<ul>
				<li>Read the <a href="http://s3.StaticVoidGames.com/learn/evolution/UsingEvolutionaryAlgorithmsToPrototypeGameContent.pdf">paper</a></li>
				<li>View the slides (as <a href="http://s3.StaticVoidGames.com/learn/evolution/EvolutionFrameworkSlides.pptx">PowerPoint</a> or <a href="http://s3.StaticVoidGames.com/learn/evolution/EvolutionFrameworkSlides.pdf">PDF</a>)</li>
				<li>Download the <a href="http://s3.StaticVoidGames.com/learn/evolution/EvolutionFramework.java">source</a> to the framework. It's contained in a single .java file.</li>
			</ul>
			
			<p>You can also play an example <a href="http://staticvoidgames.com/play/?game=MazeGame">game</a> that uses the framework. The game is available as:</p>
			<ul>
			<li><a href="http://staticvoidgames.com/play/?game=MazeGame&view=applet">Applet</a></li>
			<li><a href="http://s3.staticvoidgames.com/games/MazeGame/webStart.jnlp">Webstart</a></li>
			<li><a href="http://s3.staticvoidgames.com/games/MazeGame/MazeEvolution.jar">Runnable Jar</a></li>
			<li><a href="http://s3.staticvoidgames.com/games/MazeGame/MazeGameSource.zip">Source</a></li>
			</ul>
			


		</div>
			
			<div style="clear:both;"></div>
			
		
		<!--<%@ include file="../../include/ad.inc"%>-->
		<%@ include file="../../include/footer.inc"%>

		</div>
</body>

</html>