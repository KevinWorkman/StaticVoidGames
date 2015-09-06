<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
	<title>Tutorials - Static Void Games</title>
	
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/css/bootstrap.min.css">
    <link rel="stylesheet" href="https://bootswatch.com/cyborg/bootstrap.min.css">
    <link rel="stylesheet" type="text/css" href="<c:url value="/css/bs.css"/>">
    
	<link rel="shortcut icon" href="<c:url value="/images/favicon.png"/>" />
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
	
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
	
	<link rel="shortcut icon" href="<c:url value="/images/favicon.png"/>" />
</head>

<body style="background-image:url(<c:url value="${backgroundImage}"/>);">
	<%@ include file="../include/analytics.jsp"%>
	<%@ include file="../include/navigation3.jsp" %>
	<div class="container-fluid" style="margin-top:10px;">


		<div class="col-xs-4">
			<div class="panel panel-default">
				<div class="panel-heading" id="tutorialNavigationTitle"></div>
				<div class="panel-body">
					<%@ include file="tutorialsNavigation.jsp" %>
				</div>
			</div>
		</div>

		<div class="col-xs-8" >
			<div class="panel panel-default">
				<div class="panel-heading">Tutorials</div>
		
				<div class="panel-body">Pick a tutorial from the left!</div>
			</div>
		</div>

			<%@ include file="../include/advertisement.jsp" %>
   			<%@ include file="../include/openSource.jsp" %>
   </div>
</body>
</html>