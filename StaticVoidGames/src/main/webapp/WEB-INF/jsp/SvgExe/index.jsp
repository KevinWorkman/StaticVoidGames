<!DOCTYPE HTML>


<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
	<link rel='stylesheet' type='text/css' href='http://fonts.googleapis.com/css?family=Open+Sans' >
	<link rel="stylesheet" type="text/css" href="<c:url value="/css/general.css"/>">
	<link rel="stylesheet" type="text/css" href="<c:url value="/css/everyPage.css"/>">
	<title>SvgExe - Static Void Games</title>
	<link rel="shortcut icon" href="<c:url value="/images/favicon.png"/>" />
</head>
<body style="background-image:url(<c:url value="${backgroundImage}"/>);">
<%@ include file="../include/navigation.jsp" %>

	<%@ include file="sideBar.inc"%>
	<div id="contentPane">
	
		
	
<h3>The Problem:</h3>
		<ul>
		<li>You have several jars that you want to combine into one jar.</li>
		<li>Your program relies on external files that can't be added to a jar, but you want to deploy as a single runnable file.</li>
		<li>Your program relies on native files that have to be on the file system, but you want to deploy as a single runnable file.</li>
		<li>You have a Processing sketch that you want to deploy as a single runnable file.</li>
		<li>You have some .class files that you want to deploy as a runnable jar.</li>
		</ul>
		
		<h3>The Solution:</h3>
		<p>SvgExe helps you create an automatically extracting runnable
			jar file that handles multiple jars, classes, external files, and
			even native libraries. You can use the GUI to create the jar, and
			that's the only file that you need to send to your users (or upload
			to Static Void Games!). Users of your program just see a single file
			that they double-click to run, and SvgExe handles the rest.</p>
			
			<img src="flowchart.png" style="width:100%;"/>

				<p>SvgExe is a program designed to help alleviate some of the
					stress of Java and Processing deployment. It handles packaging
					multiple jars and classes up into a single program, allows for
					packaging of external files (images, sounds, etc), and native
					libraries- all into a single executable jar that can be sent to end users.</p>

				<p>This is an alternative to running as an applet or webstart, which has been made more difficult in the latest version of Java.</p>

				<p>
					Hopefully the process is relatively straightforward, but don't
					hesitate to contact me via <a target='blank' href=https://twitter.com/StaticVoidGames>twitter</a>,
					<a target='blank' href=https://www.facebook.com/StaticVoidGames>facebook</a>,
					or email (<a href="mailto:kevin@StaticVoidGames.com?Subject=SvgExe" target="_top">kevin@StaticVoidGames.com</a>) if you have any questions.</p>

				
				<h3 style="margin-left:15px"><a href="download.jsp">Download SvgExe here.</a></h3>

				<h3 style="margin-left:15px"><a href="faq.jsp">Want to know more? Check out the FAQ here.</a></h3>
		

	
	
	
	
    <%@ include file="../include/advertisement.jsp" %>
    
    </div>
   
</body>
</html>
