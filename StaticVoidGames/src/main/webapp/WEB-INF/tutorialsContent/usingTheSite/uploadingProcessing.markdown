
<%
	String loginRedirect = request.getParameter("r");
	if(loginRedirect != null){
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

			<h1 id="pageTitle">Uploading Processing</h1>

			<div class="programDescription">
				<p>To upload a Processing sketch, simply follow these steps:</p>

				<ol>
					<li>Export your sketch as an applet (File > Export Applet). That will create an applet directory containing a jar file named after your sketch.</li>
					<li>Upload that jar file!</li>
					<li>You can deploy your sketch as a web start app by using the name of your sketch as the main class.</li>
					<li>You can deploy your sketch as an applet by using the name of your sketch as the applet class.</li>
					<li>That's it! Add images and a description, and your game is fully deployed!</li>
				
				</ol>

				<p>If you have any questions, feel free to post them in the <a href="http://forum.staticvoidgames.com">forum</a>.</p>



			</div>



		</div>

		<%@ include file="../../include/footer.inc"%>

	</div>

</body>

</html>