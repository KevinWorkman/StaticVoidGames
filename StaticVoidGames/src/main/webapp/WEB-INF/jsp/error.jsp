<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
<link rel='stylesheet' type='text/css' href='http://fonts.googleapis.com/css?family=Open+Sans' >
<link rel="stylesheet" type="text/css" href="<c:url value="/css/general.css"/>">
<link rel="stylesheet" type="text/css" href="<c:url value="/css/everyPage.css"/>">
<title>Error - Static Void Games</title>
	<link rel="shortcut icon" href="<c:url value="/images/favicon.png"/>" />
</head>
<body>
<%@ include file="include/analytics.jsp"%>
<%@ include file="include/navigation.jsp" %>
	<div id="contentPane">
		<div class="darkBackground lightText thinGrayBorder textPadding">
			<p>Sorry, something broke.</p>
		</div>
    <%@ include file="include/advertisement.jsp" %>
    </div>
</body>
</html>