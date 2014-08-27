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
	
				<h3 style="margin-left:15px"><a href="http://s3.StaticVoidGames.com/SvgExe/SvgExe.jar">SvgExe.jar</a><h3 style="margin-left:15px"></h3>
				
				<p>This is probably what you came here for. Download the jar, then double-click it to run it. You'll need to have Java installed.</p>
				
				<h3 style="margin-left:15px"><a href="http://s3.StaticVoidGames.com/SvgExe/ProcessingLibrary.jar"">ProcessingLibrary.jar</a></h3>
				
				<p>Processing users need this because of how the newest version of Processing handles the export process.</p>
				
				<h3 style="margin-left:15px"><a href="http://s3.StaticVoidGames.com/SvgExe/SvgExeProcessingTool.zip">SvgExe Processing Tool</a></h3>
				
				<p>Unzip this file to your Processing tools directory to launch SvgExe directly from the Processing PDE.</p>
				
				<h3 style="margin-left:15px"><a href="https://github.com/KevinWorkman/SvgExe">Source on Github</a></h3>
				
				<p>The source for SvgExe is available on Github. There are plenty of ways to get involved! Feel free to poke around if you're curious, but you don't need this to run the program.</p>
				
    <%@ include file="../include/advertisement.jsp" %>
    
    </div>
   
</body>
</html>
