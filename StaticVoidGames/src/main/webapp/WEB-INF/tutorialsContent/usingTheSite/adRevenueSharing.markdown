
<%
	String loginRedirect = request.getParameter("r");
	if (loginRedirect != null) {
		session.setAttribute("loginRedirect", loginRedirect);
	}
%>

<!DOCTYPE HTML>
<html>

<head>
<title>Static Void Games - Login</title>
<%@ include file="../../include/meta.inc"%>
<%@ include file="../../include/jspCss.inc"%>
<%@ include file="../../include/analytics.inc" %>
</head>

<body>
	<div class="wrap">

		<%@ include file="../../include/navigation.inc"%>

		<div id="lonePage">

			<h1 id="pageTitle">Ad Revenue Sharing</h1>

			<div class="programDescription">
				<p>I'm still figuring everything out, but the next big thing I'm going to add to the site is
					ad revenue sharing.</p>

				<p>What this means is that developers will be able to make money off the revenue generated
					by ads on their game pages. I was planning on using Google's host api, but that only works for
					sites with over 100,000 visitors a day. That's not an option for a startup website. So if
					anybody has any suggestions, I'm open to other options. I'd love to get this implemented as soon as possible.</p>

				<p>
					If you have any suggestions or questions, feel free to post them in the <a
						href="http://forum.staticvoidgames.com">forum</a> or email me at <a href="mailto:kevin@StaticVoidGames.com">kevin@StaticVoidGames.com</a>.
				</p>



			</div>



		</div>

		<%@ include file="../../include/footer.inc"%>

	</div>

</body>

</html>