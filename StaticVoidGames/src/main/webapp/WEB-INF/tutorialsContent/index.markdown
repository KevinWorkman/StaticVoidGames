<%
	session.setAttribute("loginRedirect", request.getRequestURL());
session.setAttribute("chapter", "index");
session.removeAttribute("section");
%>
<!DOCTYPE HTML>
<html>

<head>
<title>Static Void Games - Tutorials</title>

<%@ include file="../include/meta.inc"%>

<meta name="description" content="Static Void Games- Play indie games, learn how to program by following our game programming tutorials, and upload your own indie games!">
<meta name="keywords" content="java, code, samples, indie, game, static, void, games">
<link rel="stylesheet" type="text/css" href="../styles/tutorialStyles.css" />


<%@ include file="../include/jspCss.inc"%>
<%@ include file="../include/analytics.inc"%>

<link rel="stylesheet" type="text/css"
	href="http://s3.staticvoidgames.com/javascript/prettify/sunburst.css" />
<script type="text/javascript" src="http://s3.staticvoidgames.com/javascript/prettify/prettify.js"></script>

</head>

<body onload="prettyPrint()">


<%@ include file="../include/topBar.inc"%>
<%@ include file="include/tutorialNavigation.jsp"%>

	<div class="wrap">
	
	<%@ include file="../include/announcement.inc"%>


		<h1 class="chapterTitle">Tutorials</h1>
						
		<div id="rightTutorialBody">
		
			<p>
				Welcome to the tutorial section of Static Void Games! This entire
				site has been a huge learning experience for me; I've learned so
				much designing and implementing it, and I appreciate the feedback
				I've received so far! I've always intended for this site to help
				other people learn alongside me. I'm hoping we can learn together
				and grow as a community of up-and-coming indie game developers. With
				that, I'm happy to introduce chapter one of the tutorial section:
				<a href="http://staticvoidgames.com/tutorials/intro">Introduction to Programming</a>. Check it out in the list to the left!
				</p>

				<p>
				So far, the tutorials are geared towards beginners. They use
				Processing to introduce the very basics of programming. So if you've
				ever thought to yourself "how would I start learning how to
				program?", then they might be worth checking out. Even experienced
				users might benefit from retouching the basics- I know I did. I'm
				planning on writing more tutorials as I go, eventually going from
				Hello World to advanced topics like AI. If you have any requests for
				tutorial topics, or if you spot an error or something that isn't
				quite clear in a tutorial, please contact me and let me know!
				Feedback is always appreciated!
				</p>

				<p>Next: <a href="intro/">Introduction to Programming</a></p>


		</div>
			
			<div style="clear:both;"></div>
			
		
		<%@ include file="../include/ad.inc"%>
		<%@ include file="../include/footer.inc"%>

		</div>
</body>

</html>