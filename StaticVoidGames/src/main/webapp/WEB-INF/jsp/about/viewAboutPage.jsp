<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
	<title>About - Static Void Games</title>
	
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/css/bootstrap.min.css">
    <link rel="stylesheet" href="https://bootswatch.com/cyborg/bootstrap.min.css">
    <link rel="stylesheet" type="text/css" href="<c:url value="/css/bs.css"/>">

	<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
	<link rel="shortcut icon" href="<c:url value="/images/favicon.png"/>" />
	
	<style>
		.list-group-item{
			padding: 0px;
		}
		
		ul {
			list-style-position: inside;
			padding-left:0;
		}
		
		h3{
			font-size: 20px;
		}
	</style>
	
	
	<script type="text/javascript" src="<c:url value="/js/Markdown.Converter.js"/>"></script>
	<script type="text/javascript" src="<c:url value="/js/Markdown.Sanitizer.js"/>"></script>
	<script type="text/javascript" src="<c:url value="/js/MarkdownParser.js"/>"></script>
	
</head>
<body style="background-image:url(<c:url value="${backgroundImage}"/>);">

	<%@ include file="../include/analytics.jsp"%>
	<%@ include file="../include/navigation3.jsp" %>
	
<div class="container" style="margin-top:10px;">

		<div class="col-xs-2">
			<div class="panel panel-default">
				<div class="panel-heading">About</div>
				<div class="panel-body">
					<%@ include file="aboutNavigation.jsp" %>
				</div>
			</div>
		</div>
		
		<div class="col-xs-10" >
			<div class="panel panel-default">
				<div class="panel-heading">${aboutTitle}</div>
		
				<div class="panel-body markdown" >${aboutMarkdown}</div>
			</div>
		</div>


    <%@ include file="../include/advertisement.jsp" %>
    <%@ include file="../include/openSource.jsp" %>
</div>

</body>
</html>