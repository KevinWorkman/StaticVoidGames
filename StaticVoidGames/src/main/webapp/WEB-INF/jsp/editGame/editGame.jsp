<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
<head>
<title>Edit ${gameObj.getEscapedGameName()}</title>

	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/css/bootstrap.min.css">
    <link rel="stylesheet" href="https://bootswatch.com/cyborg/bootstrap.min.css">
    <link rel="stylesheet" type="text/css" href="<c:url value="/css/bs.css"/>">

	<link rel="stylesheet" type="text/css" href="<c:url value="/css/pageDown.css"/>">
	<script type="text/javascript" src="<c:url value="/js/Markdown.Converter.js" />"></script>
	<script type="text/javascript" src="<c:url value="/js/Markdown.Sanitizer.js" />"></script>
	<script type="text/javascript" src="<c:url value="/js/Markdown.Editor.js" />"></script>
	
	<link rel="shortcut icon" href="<c:url value="${faviconImage}"/>" />
	
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/js/bootstrap.min.js"></script>
	
</head>
<body style="background-size:auto; background-image:url(<c:url value="${backgroundImage}"/>);">
<%@ include file="../include/analytics.jsp"%>
<%@ include file="../include/navigation3.jsp" %>
	<div id="container">
	
		<div class="panel panel-default text-center" style="margin-left:auto; margin-right:auto; margin-top:25px; width:500px">
	
	    	<div class="panel-heading">Edit Game</div>
	               
	        <form:form name='f' action='../edit/' method='POST' commandName="gameForm" enctype="multipart/form-data">
	               
	        	<c:forEach items="${formFields}" var="formField">
	 			
	                <c:choose>
	                	<c:when test="${formField.getType() == 'input'}">
	                		<div class="darkBackground textPadding centered topMargin thinGrayBorder">
	                		<h2>${formField.getLabel()}</h2>
	                		<p>${formField.getDescription()}</p>
	                		<form:input cssClass="form-control" path="${formField.getCommand()}" />
	                		</div>
	                    </c:when>
	                    <c:when test="${formField.getType() == 'file'}">
		                    <div class="darkBackground textPadding centered topMargin thinGrayBorder">
		                			<h2>${formField.getLabel()}</h2>
		                    	<p>${formField.getDescription()}</p> 
		                    	<input class="form-control" type="file" name="${formField.getCommand()}"  />
		                    </div>
	                    </c:when>
	                    <c:when test="${formField.getType() == 'htmlModeRadio'}">
	                    <div class="darkBackground textPadding centered topMargin thinGrayBorder">
	                			<h2>${formField.getLabel()}</h2>
	                    	<p>
	                    	<form:radiobutton cssClass="form-control" path="${formField.getCommand()}" value="StaticVoidGames" />Link to a nice-looking page that contains my playable game.
							</p>
							<p>
	                    	<form:radiobutton cssClass="form-control" path="${formField.getCommand()}" value="minimal" />Link to a minimal page that only contains my playable game.
							</p>
							<p>
	                    	<form:radiobutton cssClass="form-control" path="${formField.getCommand()}" value="mine" />Link to the page I'm providing in dist/html/index.html
							</p>
	
	                    	</div>
	                    </c:when>
	                    <c:when test="${formField.getType() == 'textarea'}">
	                    <div class="darkBackground textPadding centered topMargin thinGrayBorder">
	                			<h2>${formField.getLabel()}</h2>
	                			<p>${formField.getDescription()}</p> 
	                    	<div class="wmd-panel">
								<div id="wmd-button-bar"></div>
								<form:textarea cssClass="form-control" path="${formField.getCommand()}" class="wmd-input" id="wmd-input" />
							</div>
							<div id="wmd-preview" class="wmd-panel wmd-preview"></div>
				
							<script type="text/javascript">
								(function() {
									var converter = Markdown.getSanitizingConverter();
									var editor = new Markdown.Editor(converter);
									editor.run();
				
								})();
							</script>
	                    </div>                                
						</c:when>
						<c:when test="${formField.getType() == 'checkbox'}">
						<div class="darkBackground textPadding centered topMargin thinGrayBorder">
	                			<h2>${formField.getLabel()}</h2>
							${formField.getDescription()}: <form:checkbox cssClass="form-control" path="${formField.getCommand()}" />
							</div>
						</c:when>
					</c:choose>
	 
	 			</c:forEach>
	               
	            <button name="submit" type="submit" class="btn btn-primary">Submit</button>
	               	
				
			</form:form>
		
		
		</div>
   			<%@ include file="../include/openSource.jsp" %>
	</div>
</body>
</html>