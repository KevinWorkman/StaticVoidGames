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
	
		
				<p>SvgExe is a program that helps you create a standalone,
					self-extracting jar file. It handles multiple jar files, external
					files, and native libraries. It is also designed to deploy a Processing
					sketch as a single file that will run on any system!</p>

				<p>SvgExe can be launched directly from the Processing PDE.</p>
			
			
			<p>To add the SvgExe option to the Tools menu of the Processing PDE, do the following:</p>
		<ul>
		<li>Download the SvgExe Processing tool zip from the <a href="download.jsp">download page</a>.</li>
		<li>Unzip that directory to your Processing tools directory.</li>
		<li>Choose the "Export Application" option from the File menu to generate the necessary jar files.</li>
		<li>Choose the "SvgExe" option from the Tools menu to open up SvgExe.</li>
		<li>Go through the SvgExe tabs to export a standalone self-extracting jar!</li>
		</ul>
		

	
	
	
	
    <%@ include file="../include/advertisement.jsp" %>
    
    </div>
   
</body>
</html>
