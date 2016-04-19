<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>

	<meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">

	<title>${title}</title>
	
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/css/bootstrap.min.css">
    <link rel="stylesheet" href="https://bootswatch.com/cyborg/bootstrap.min.css">
    <link rel="stylesheet" type="text/css" href="<c:url value="/css/bs.css"/>">
    
    <script type="text/javascript" src="<c:url value="/js/prettify.js"/>"></script>
    <link rel="stylesheet" type="text/css" href="<c:url value="/css/sunburst.css"/>">
    
	<link rel="shortcut icon" href="<c:url value="/images/favicon.png"/>" />
	<script type="text/javascript" src="<c:url value="/js/Markdown.Converter.js"/>"></script>
	<script type="text/javascript" src="<c:url value="/js/Markdown.Sanitizer.js"/>"></script>
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
	
	<script>var baseUrl = '<c:url value="/"/>';</script>
	<script>
	    function parseTutorialMarkdown(){
	    	var converter =  new Markdown.getSanitizingConverter();
    		converter.hooks.chain("postConversion", function (text) {
    		    return text.replace(/<pre>/gi, "<pre class=prettyprint>");
    		});
    		var tutorialHtml = converter.makeHtml($("#tutorialText").html());
    		
    		$("#tutorialText").html(tutorialHtml);
    		prettyPrint();
	    }
	    $(parseTutorialMarkdown);
    
    </script>
	
	<style>
		.list-group-item{
			padding: 0px;
		}
		
		ul {
			list-style-position: inside;
			padding-left:0;
		}
		
	</style>
	
	<script>
		function setTutorialNavigationTitle(){
			var r = Math.random();
			if(r < .2){
				$("#tutorialNavigationTitle").html("Pick a tutorial:")
			}
			else if(r < .4){
				$("#tutorialNavigationTitle").html("So many tutorials!")
			}
			else if(r < .6){
				$("#tutorialNavigationTitle").html("Pick a Gibson to hack:")
			}
			else if(r < .8){
				$("#tutorialNavigationTitle").html("You're smarter already!")
			}
			else{
				$("#tutorialNavigationTitle").html("What do you want to learn today?")
			}
		}
		
		$(setTutorialNavigationTitle);
	</script>
	
</head>
<body style="background-image:url(<c:url value="${backgroundImage}"/>);">
	<%@ include file="../include/analytics.jsp"%>
	<%@ include file="../include/navigation3.jsp" %>
	
	<div class="container-fluid" style="margin-top:10px;">
	
	<div class="col-xs-3">
		<div class="panel panel-default">
			<div class="panel-heading" id="tutorialNavigationTitle"></div>
			<div class="panel-body">
				<%@ include file="tutorialsNavigation.jsp" %>
			</div>
			
		</div>
	</div>
	
	<div class="col-xs-9" >
		<div class="panel panel-default">
			<div class="panel-heading">Have a question about this tutorial? Come talk about it on <a target="_blank" href="http://forum.staticvoidgames.com/">the forum</a>!</div>
		
			<div class="panel-body" id="tutorialText2">${tutorialHtml}</div>
		</div>
	</div>
	
			<%@ include file="../include/advertisement.jsp" %>
   			<%@ include file="../include/openSource.jsp" %>
	</div>

</body>
</html>
