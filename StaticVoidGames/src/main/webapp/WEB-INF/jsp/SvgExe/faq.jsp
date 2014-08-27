
<!DOCTYPE HTML>


<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
	<link rel='stylesheet' type='text/css' href='http://fonts.googleapis.com/css?family=Open+Sans' >
	<link rel="stylesheet" type="text/css" href="<c:url value="/css/general.css"/>">
	<link rel="stylesheet" type="text/css" href="<c:url value="/css/everyPage.css"/>">
	<title>SvgExe - Static Void Games</title>
	<link rel="shortcut icon" href="<c:url value="/images/favicon.png"/>" />
	
	<style type="text/css">
dt {
	font-weight: bold;
	margin-top: 25px;
	margin-left: 25px;
}

dd {
	margin-top: 10px;
	margin-left: 50px;
}
</style>
</head>
<body style="background-image:url(<c:url value="${backgroundImage}"/>);">
<%@ include file="../include/navigation.jsp" %>

	<%@ include file="sideBar.inc"%>
	<div id="contentPane">


				<dl>

					<dt>What is SvgExe?</dt>
					<dd>SvgExe is a program that builds self-extracting jars. These self-extracting jars can handle external files and native resources.</dd>

					<dt>Isn't this just another fatjar creator?</dt>
					<dd>A fatjar is a jar that contains the contents of other jars, and yes, that's part of SvgExe. However, SvgExe programs can also contain external files and OS-specific natives, which SvgExe takes care of automatically.</dd>

					<dt>Can I package the JRE with an SvgExe? Can I create .exe files using SvgExe?</dt>
					<dd>No. End users will have to have their own JRE installed, and SvgExe only exports .jar files.
						This helps keep the download size of your program down, and in my humble opinion, packaging the JRE with your program is overkill for most
						situations. If you really want to package the JRE with your program, check out <a target='blank' href="http://www.jwrapper.com/">JWrapper</a>.</dd>

					<dt>Can I charge for games and applications I create using SvgExe?</dt>
					<dd>Sure, if you want. The only thing I wouldn't like is if you took the code from SvgExe, created your own version of SvgExe, and then charged for that.</dd>


					<dt>Isn't this just like JarSplice?</dt>
					<dd>SvgExe is based on JarSplice, but I've added the ability to
						include external files so it can be used for Processing sketches
						as well as Java programs.</dd>
						
				
					<dt>Something isn't working or I have a feature request!</dt>
					<dd>I'd love to hear from you. Don't
					hesitate to contact me via <a target='blank' href=https://twitter.com/StaticVoidGames>twitter</a>,
					<a target='blank' href=https://www.facebook.com/StaticVoidGames>facebook</a>,
					or email (<a href="mailto:kevin@StaticVoidGames.com?Subject=SvgExe" target="_top">kevin@StaticVoidGames.com</a>) if something isn't working or if you have a feature request.</dd>

					<dt>Did anybody actually ask any of these questions?</dt>
					<dd>Nope. But this is a better format than a wall-o-text explaining everything!</dd>

					<dt>How exactly does SvgExe work?</dt>
					<dd>SvgExe consists of two parts. The first part is the GUI that is run by the creator of the game or application. The gui does a few things:
					
					<ul>
					<li>Uses the <a target="_blank" href="http://docs.oracle.com/javase/7/docs/api/java/util/zip/ZipFile.html">ZipFile</a> class to read the files from any input jars.</li>
					<li>Uses the <a target="_blank" href="http://docs.oracle.com/javase/7/docs/api/java/util/jar/JarOutputStream.html">JarOutputStream</a> class to output those files, as well as any natives and external files, to a destination jar.</li>
					<li>Includes a few other classes required for the other half of the process.</li>
					</ul>
					
					The second part of SvgExe is the actual self-extracting jar that is output by the SvgExe gui. When the jar is run by the user (by double-clicking it), the following happens:
					<ul>
					<li>A splash screen is (optionally) displayed.</li>
					<li>The main SvgExeLauncher class is run.</li>
					<li>This class extracts all of the natives and external files to a temp directory that the user doesn't have to worry about.</li>
					<li>The class then uses the  <a target="_blank" href="http://docs.oracle.com/javase/7/docs/api/java/lang/ProcessBuilder.html">ProcessBuilder</a> class to run the actual main program. This guarantees that all of the natives and external files are in place before the program is run.</li>
					<li>When the user exits the main program, SvgExe deletes the external files so the user only ever has to deal with a single file: the self-extracting jar.</li>
					</ul>
					
					<dt>Can I use SvgExe to run an applet?</dt>
					<dd>No. SvgExe is my attempt at getting <i>away</i> from applets after Java 7 update 51 made them harder for users. But if there's a lot of interest, I could think about adding applet functionality. That wouldn't make it easier on users though, so it's not currently in the works.</dd>
					

				</dl>

	
	
    <%@ include file="../include/advertisement.jsp" %>
    
    </div>
   
</body>
</html>
