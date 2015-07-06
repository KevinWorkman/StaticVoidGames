<!DOCTYPE html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html lang="en">
  <head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->
    <meta name="description" content="">
    <meta name="author" content="">
    <link rel="icon" href="../../favicon.ico">

    <title>Static Void Games</title>

    <!-- Bootstrap core CSS -->
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/css/bootstrap.min.css">
    <link rel="stylesheet" href="https://bootswatch.com/cyborg/bootstrap.min.css">


    <!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
    <!--[if lt IE 9]>
      <script src="https://oss.maxcdn.com/html5shiv/3.7.2/html5shiv.min.js"></script>
      <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
    <![endif]-->


	<link rel="shortcut icon" href="<c:url value="/images/favicon.png"/>" />
	
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
	
<style>
body {
	padding-top: 50px;
}

.starter-template {
 	padding: 40px 15px;
	text-align: center;
}

.blog-post{
	width:75%;
	margin-left:auto;
	margin-right:auto;
}

.thumbnail{
	text-align:center;
}

h2{
	font-size:initial;
}

.container{
min-width:720px;
}

.navigationBar a{
margin-left:5px;
margin-right:5px;
}

a:hover{
color:orange;
}

	</style>
	
  </head>

<body style="background-image:url(<c:url value="${backgroundImage}"/>);">

	<%@ include file="include/navigation3.jsp" %>

    <div class="container">

		<div class="panel panel-default" style="margin-top:25px; background-color:black;">
		<div class="panel-body">
		<div>
			<img style="margin-left: auto; margin-right: auto; display: block;" src="https://pbs.twimg.com/media/CGHtKEhUYAAfmo4.png" />
		</div>

		<div class="row marketing">
        <div class="col-lg-6">
          <h4><a href="<c:url value="/games" />">Play</a></h4>
          <p>Support up-and-coming indie game developers by playing their games. Leave a comment to tell them what you like or what can be improved.</p>

          <h4><a href="<c:url value="/tutorials" />">Learn</a></h4>
          <p>Our tutorials go from basic Processing through advanced libGDX.</p>

          <h4><a href="<c:url value="/upload" />">Code</a></h4>
          <p>Free hosting for Processing, Java, libGDX, and JavaScript games.</p>
        </div>

        <div class="col-lg-6">
          <h4><a href="<c:url value="/blog" />">Blog</a></h4>
          <p>Read about what we're up to, or write your own blog to keep us updated on your latest projects.</p>

          <h4><a href="<c:url value="http://forum.StaticVoidGames.com" />">Forum</a></h4>
          <p>Stuck on a code problem? Just want to talk? Come say hi!</p>

          <h4><a href="https://github.com/KevinWorkman/StaticVoidGames">Open Source</a></h4>
          <p>Static Void Games is completely open-source. Want to practice your web development skill? Contribute on GitHub!</p>
        </div>
      </div>
      </div>
      </div>


		<div class="panel panel-default">
			<div class="panel-heading">
				<h3>Newest games:</h3>
			</div>
			<div class="panel-body">
				<div class="row">
					<c:forEach items="${recentGames}" var="game">
						<div class="col-xs-2">

							<div class="thumbnail">
								<a href="<c:url value="/games/${game.getGameName()}" />"> 
									<c:choose>
										<c:when test="${game.getThumbnailUrl() == null}">
											<img src="<c:url value="${s3Endpoint}/images/staticVoidProfile3.png"/>" />
										</c:when>
										<c:otherwise>
											<img src="<c:url value="${s3Endpoint}/games/${game.getGameName()}/${game.getThumbnailUrl()}"/>" />
										</c:otherwise>
									</c:choose> ${game.getTitle()}
								</a> 
								<br /> by <a href="<c:url value="/members/${game.getMember()}" />">${game.getMember()}</a>
								
							</div>
						</div>

					</c:forEach>

				</div>
			</div>
			<div class="panel-footer"><a href="<c:url value="/games" />">View more games here!</a></div>
		</div>

		
      
	<div class="panel panel-default">
		<div class="panel-heading"><h3>Recent happenings:</h3></div>
		<div class="panel-body">
			<c:forEach items="${events}" var="event">
				<p><a href="<c:url value="${event.getRelativeUrl() }"/>">${event.getEventText()}</a></p>
			</c:forEach>
		</div>
	</div>



		<div class="panel panel-default">
			<div class="panel-heading"><h3>Kevin's latest blog: ${latestBlog.getTitle()}</h3></div>
			<div class="panel-body">
           		${latestBlog.getParsedText()}
          </div>
          <div class="panel-footer">Comment on this blog <a href="<c:url value="/blog/${latestBlog.getUrlName()}" />">here</a>.</div>
      	</div>
      
		<div class="panel panel-default" style="width:800px; margin-left:auto; margin-right:auto;">
			<div class="panel-heading">Advertisement</div>
			<div class="panel-body">
      			<%@ include file="include/advertisement.jsp" %>
      		</div>
   	   </div>
      
      
      <%@ include file="include/openSource.jsp" %>

    </div><!-- /.container -->
    


    <!-- Bootstrap core JavaScript
    ================================================== -->
    <!-- Placed at the end of the document so the pages load faster -->
    
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/js/bootstrap.min.js"></script>
    <!-- Just to make our placeholder images work. Don't actually copy the next line! -->
    <script src="../../assets/js/vendor/holder.js"></script>
    <!-- IE10 viewport hack for Surface/desktop Windows 8 bug -->
    <script src="../../assets/js/ie10-viewport-bug-workaround.js"></script>
  </body>
</html>
