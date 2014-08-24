
<%
	session.setAttribute("loginRedirect", request.getRequestURL());
	session.setAttribute("chapter", "SvgExe");
	session.setAttribute("section", "BasicProcessingExample");
%>
<!DOCTYPE HTML>
<html>

<head>
<title>Basic Processing Export - Tutorials - Static Void Games</title>

<%@ include file="../../include/meta.inc"%>

<meta name="description"
	content="Static Void Games- Play indie games, learn how to program by following our game programming tutorials, and upload your own indie games!">
<meta name="keywords"
	content="java, code, samples, indie, game, static, void, games">
<link rel="stylesheet" type="text/css"
	href="../../styles/tutorialStyles.css" />


<%@ include file="../../include/jspCss.inc"%>
<%@ include file="../../include/analytics.inc"%>

<link rel="stylesheet" type="text/css"
	href="http://s3.staticvoidgames.com/javascript/prettify/sunburst.css" />
<script type="text/javascript"
	src="http://s3.staticvoidgames.com/javascript/prettify/prettify.js"></script>

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
			<h2 style="text-align: center;">Basic Processing Deployment</h2>


			<p>This tutorial walks through the process of taking a basic Processing sketch and using SvgExe to turn it into a jar that other people can just double-click to run.</p>

			<h3>The Problems We're Fixing</h3>

			<p>In the latest version of Processing, a few restrictions have
				been added:</p>
				
				<ul>
				<li>You can now only export your Processing sketch for the
				type of computer you're on. So if you're on a Windows computer,
				people with Mac or Linux won't be able to run your sketch.</li>
				<li>And even the export that Processing does create is not a single
					file, but a directory that you have to zip up to send people. This
					can be pretty annoying for end-users who just want to double-click
					something to run.</li>
				<li>Processing also relies on an external data folder (if you're using images or sounds), which can be difficult to deal with.</li>
				</ul>

			<p>Luckily, SvgExe deals with all of the above!</p>

			<h3>Download ProcessingLibrary.jar</h3>



			<p>To make a cross-platform program, we need to collect all of
				the files that the different systems need. You can go searching
				through your Processing directory structure, but that sounds
				tedious. Instead, I've collected everything you need in a handy library jar.</p>

			<p>You can download the library jar here: <a href="http://s3.StaticVoidGames.com/SvgExe/ProcessingLibrary.jar">ProcessingLibrary.jar</a><p>
			
			<p>Note that this jar doesn't actually do anything if you double-click it or try to run it; it's just a collection of all of the files you need to export your Processing sketch.</p>
				
		
			
			<h3>An Example Sketch</h3>

			<p>Let's start out with something simple: a Processing sketch that doesn't use any libraries or external files.</p> 
				
<pre>
<code class="prettyprint">
void setup() {
  size(200, 200); 
}

void draw() {
 ellipse(100, 100, 50, 50); 
}
</code>
</pre>

			<p>We'll use this code to </p>
			
			<p>Or so we thought.</p>

			<h3>Valid Reasons to Escape the Sandbox</h3>

			<p>By default, applets and webstarts are not allowed to access
				things like the hard drive. However, there are many valid reasons
				for an applet to need to work "outside the sandbox"- you might want
				an applet to save or load a file, or you might want your game to use
				your graphics card to run faster.</p>

			<p>
				A programmer can ask a user for extended permissions by <span
					class="important">signing</span> the applet. This causes the applet
				to show a dialog to the user asking for permission to run outside
				the sandbox. The user can still say no, so they are still in
				control. An applet running outside the sandbox is called a <span class="important">privileged</span>
				applet, since the user has to grant privileges to access system
				resources.
			</p>

			<p>This actually worked pretty well for quite a long time. In
				fact, one of the reasons the next section was such big news was
				because of how good Java's security was, especially compared to
				languages like flash.</p>

			<h3>Escaping the Sandbox</h3>
			
			<p>Then during the spring of 2012, an exploit was found that gave
				malicious programmers (the media might hype them up as "hackers")
				the ability to run privileged applets without first prompting the
				user for permission.</p>

			<p>This was a big deal, because any website could do things like
				modify the hard drive or lock down the system, and the user would
				not have a good way to track down the cause (compared to the
				security prompt that should have displayed). Even advertisements
				could contain a malicious applet, making it even more difficult.
				Several viruses spread through this applet exploit- one popular
				virus locked down the user's system and displayed a scary-looking
				warning from the FBI telling users to enter their credit card
				information to get rid of the warning. The message wasn't actually
				from the FBI, and entering your credit card information did not get
				rid of the message, it only gave thiefs access to your credit card.</p>
				
				<h3>What the Exploit Wasn't</h3>

			<p>This exploit was bad news. However, many people who didn't
				understand the exploit reacted by declaring Java as a whole
				insecure, which simply isn't true. This exploit was bad because Java
				can be embedded in a website, and websites make it hard to be sure
				where code is coming from (picture a website with 4 advertisements
				all running code from different places).</p>

			<p>However, only a few Java applications are deployed as applets
				(stuff like code examples, academic stuff, and games work well as
				applets). Most applications are deployed either as runnable jars
				(mentioned above) or as packaged executables (mentioned later), and
				this exploit did not make those applications any less secure.
				Similarly, a lot of Java code is run on servers (including this
				website!), and this exploit did not affect that code at all.</p>
				
				<p>So, yes, the exploit was bad. But it doesn't mean that Java itself is insecure, just that applets can be abused to do bad things.</p>

			<h3>Oracle's Reaction</h3>

			<p>After these exploits were made public, Oracle released a patch
				fixing the problem. But as soon as Oracle could fix one problem,
				malicious programmers would find another exploit, starting a back-and-forth that continued for almost two years.</p>

			<p>Over the course of those 2 years, Oracle has made significant
				changes to how applets and webstarts work. For example, Java now
				prompts the user when ANY applet is run, including applets that are
				completely sandboxed, and other settings can completely disable Java in
				internet browsers if the user so chooses.</p>

			<p>These changes have thwarted most of the malicious uses of
				applets, but they've also added a bunch of annoying and scary
				warnings to perfectly innocent applets.</p>

			<h3>Update 51</h3>

			<p>In January of 2014, Oracle released update 51 for Java, which
				contained another major change to how Java works on the internet. As
				of this update, by default, applets will only work if the programmer
				has paid for a certificate. These certificates can be pretty
				expensive and can be revoked if they are abused. This makes it even
				harder for malicious programmers to abuse Java, but it also makes it
				almost impossible for novices and students to deploy their programs as applets.</p>

			<p>The rest of the deployment tutorial contains other options for
				deploying Java (and Processing).</p>

		
			<h3>
				Next: <a href="ProcessingDeployment.jsp">Processing Deployment</a>
			</h3>



		</div>


		<%@ include file="../../include/ad.inc"%>
		<%@ include file="../../include/footer.inc"%>

	</div>
</body>

</html>