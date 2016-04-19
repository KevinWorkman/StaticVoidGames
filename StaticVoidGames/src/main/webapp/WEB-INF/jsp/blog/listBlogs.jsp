<!DOCTYPE html>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
	<title>Blog - Static Void Games</title>
	<link rel="shortcut icon" href="<c:url value="/images/favicon.png"/>" />
	
	<!-- Bootstrap core CSS -->
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/css/bootstrap.min.css">
    <link rel="stylesheet" href="https://bootswatch.com/cyborg/bootstrap.min.css">
    
    <link rel="stylesheet" type="text/css" href="<c:url value="/css/bs.css"/>">
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>

	<script>
		
	function updateThumbnails(){
		
		function updateThumbnail(index){
			if(index >= $( ".blogThumbnail" ).length){
				return;
			}
			else{
				
				var element = $( ".blogThumbnail" ).eq(index);
				var blogUrl = element.attr("class").split(' ')[1];
				
				$.ajax({url: "${baseUrl}blog/" + blogUrl + "/thumbnail", success: function(result){
					if(result){
						element.attr("src", result);
					}
					updateThumbnail(index+1);
	    		}});
			}
		}
		
		updateThumbnail(0);
		}
	
	$(updateThumbnails);
	</script>

</head>

<body style="background-image:url(<c:url value="${backgroundImage}"/>);">
<%@ include file="../include/analytics.jsp"%>
<%@ include file="../include/navigation3.jsp" %>

<div class="container">
	

	<div style="width:300px; margin-left:auto; margin-right:auto;">
		<div class="panel panel-default" style="margin-top:25px;">
			<div class="panel-heading"><h1>Blogs</h1></div>
			<div class="panel-body">Create your own blog <a href="<c:url value="/blog/new" />">here</a>.</div>
		</div>
	</div>
	
		<div class="panel panel-default">
		<div class="panel-heading">Every Blog Ever</div>
			<div class="panel-body">
	
				<div class="row">
				<c:forEach items="${blogViews}" var="blogView" varStatus="loop">
				
				<c:if test="${not loop.first and loop.index % 4 == 0}">
                	</div><div class="row">
            	</c:if>
				
					<a href="<c:url value="/blog/${blogView.getBlog().getUrlEscapedUrlName()}" />">
						<div class="col-xs-3">
							
							<div class="panel panel-primary">
								<div class="panel-heading">${blogView.getBlog().getEscapedTitle()}<br/>${blogView.getFormattedDate()}</div>
								<div class="panel-body"><img class="blogThumbnail ${blogView.getBlog().getUrlEscapedUrlName()}" src="${blogView.getMemberPictureUrl()}" style="width:100px;"/></div>
								<div class="panel-footer">by ${blogView.getMember().getEscapedMemberName()}</div>
							</div>
						</div>
					</a>
			
				</c:forEach>
			</div>
				
		</div>
	</div>
	
	<%@ include file="../include/advertisement.jsp" %>
	<%@ include file="../include/openSource.jsp" %>
</div>
</body>
</html>