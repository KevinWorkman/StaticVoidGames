<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<html>
<head>
<title>Edit ${gameObj.getGameName()}</title>
	<link rel='stylesheet' type='text/css' href='http://fonts.googleapis.com/css?family=Open+Sans' >
	
	<link rel="stylesheet" type="text/css" href="<c:url value="/css/general.css"/>">
	<link rel="stylesheet" type="text/css" href="<c:url value="/css/everyPage.css"/>">
	<link rel="stylesheet" type="text/css" href="<c:url value="/css/editGame.css"/>">
	
	<link rel="stylesheet" type="text/css" href="<c:url value="/css/pageDown.css"/>">

	<script type="text/javascript" src="<c:url value="/js/Markdown.Converter.js" />"></script>
	<script type="text/javascript" src="<c:url value="/js/Markdown.Sanitizer.js" />"></script>
	<script type="text/javascript" src="<c:url value="/js/Markdown.Editor.js" />"></script>
	
</head>
<body style="background-size:auto; background-image:url(<c:url value="${backgroundImage}"/>);">
<%@ include file="../include/navigation.jsp" %>
	<div id="contentPane">
    	<h3>Edit Game</h3>
               
        <form:form name='f' action='../edit/' method='POST' commandName="gameForm" enctype="multipart/form-data">
               
        	<c:forEach items="${formFields}" var="formField">
 			
                <c:choose>
                	<c:when test="${formField.getType() == 'input'}">
                		<div class="darkBackground textPadding centered topMargin thinGrayBorder">
                			<h2>${formField.getLabel()}</h2>
                		${formField.getDescription()}: <form:input path="${formField.getCommand()}" />
                		</div>
                    </c:when>
                    <c:when test="${formField.getType() == 'file'}">
                    <div class="darkBackground textPadding centered topMargin thinGrayBorder">
                			<h2>${formField.getLabel()}</h2>
                    	${formField.getDescription()}: <input type="file" name="${formField.getCommand()}"  />
                    	</div>
                    </c:when>
                    <c:when test="${formField.getType() == 'textarea'}">
                    <div class="darkBackground textPadding centered topMargin thinGrayBorder">
                			<h2>${formField.getLabel()}</h2>
                    	<div class="wmd-panel">
							<div id="wmd-button-bar"></div>
							<form:textarea path="${formField.getCommand()}" class="wmd-input" id="wmd-input" />
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
						${formField.getDescription()}: <form:checkbox path="${formField.getCommand()}" />
						</div>
					</c:when>
				</c:choose>
 
 			</c:forEach>
               
               	<div class="darkBackground textPadding centered topMargin thinGrayBorder">
					<input class="editGameSubmitButton" name="submit" type="submit" value="Submit" />
				</div>
		</form:form>
		
		    <%@ include file="../include/advertisement.jsp" %>
   			<%@ include file="../include/openSource.jsp" %>
    
    
	</div>
</body>
</html>