<%
	session.setAttribute("loginRedirect", request.getRequestURL());
session.setAttribute("chapter", "SvgExe");
session.removeAttribute("section");
%>
<!DOCTYPE HTML>
<html>

<head>
<title>SvgExe - Tutorials - Static Void Games</title>

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



		<h1 class="chapterTitle">
			<a href="index.jsp">SvgExe</a>
			</h1>
						
		<div id="rightTutorialBody">

			<p>
				I created <a href="http://StaticVoidGames.com/SvgExe/">SvgExe</a> to
				help alleviate some of the stress that comes with Java and
				Processing deployment. It takes class files, library jars, native
				and external files, and outputs a self-extracting jar that end-users
				can double-click to run.
			</p>

			<p>This tutorial section is designed to walk you through a couple different ways to use SvgExe.</p>

			

			<p>Choose the scenario that sounds closest to your setup:</p>
			<ul>
			<li>I have a Processing sketch that does not use any libraries.</li>
			<li>I have a Processing sketch that uses a library (for example, a sketch that uses minim for sound).</li>
			<li>I have a Java program that relies on library jars.</li>
			<li>I have a Java program that relies on external files.</li>
			</ul>


		</div>
			
			<div style="clear:both;"></div>
			
		
		<%@ include file="../../include/ad.inc"%>
		<%@ include file="../../include/footer.inc"%>

		</div>
</body>

</html>