<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
	<title>Blog - Static Void Games</title>
	
	<link rel='stylesheet' type='text/css' href='http://fonts.googleapis.com/css?family=Open+Sans' >
	
	<link rel="stylesheet" type="text/css" href="<c:url value="/css/general.css"/>">
	<link rel="stylesheet" type="text/css" href="<c:url value="/css/everyPage.css"/>">
	<link rel="stylesheet" type="text/css" href="<c:url value="/css/blog.css"/>">
	
	<link rel="shortcut icon" href="<c:url value="/images/favicon.png"/>" />

</head>

<body style="background-image:url(<c:url value="${backgroundImage}"/>);">

<%@ include file="../include/navigation.jsp" %>

<div id="contentPane">
	
	<div class="darkBackground textPadding centeredBlock centered width75 topMargin">
		<h1>Blogs</h1>
		<h3>Create your own blog <a href="<c:url value="/blog/new" />">here</a>.</h3>
		<p>This page needs to be redone. For now, here is every blog ever:</p>
	</div>

	<div class="textPadding centeredBlock centered width75" >
		<c:forEach items="${blogViews}" var="blogView">
			<div class="thinGrayBorder topMargin darkBackground" style="display:inline-block; max-width:200px;">
				<p>${blogView.getFormattedDate()}</p>
				<hr/>
				<img src="${blogView.getMemberPictureUrl()}" style="width:100px;"/>
				<hr/>
				<p><a href="<c:url value="/blog/${blogView.getBlog().getUrlName()}" />">${blogView.getBlog().getTitle()}</a></p>
			</div>
		</c:forEach>
	</div>

	<%@ include file="../include/advertisement.jsp" %>
</div>
</body>
</html>