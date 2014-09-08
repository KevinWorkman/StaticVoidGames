<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<div id="topBar">

	<a href="<c:url value="/" />"><img id="siteLogo" src="<c:url value="/images/StaticVoidGamesLogo3.png" />" /></a>
	
	<div id="topBarText">
	
		<c:choose>
			<c:when test="${isLoggedIn}">
				Hello, <a href="<c:url value="/members/${loggedInUser}" />">${loggedInUser}</a>! 
			</c:when>
			<c:otherwise>
					<a href="<c:url value="/login" />">Login</a>
			</c:otherwise>
		</c:choose>
	
		<br/>
	
		<div id="navButtons">
			<a href="<c:url value="/games" />">Play</a>
			<a href="<c:url value="/tutorials" />">Tutorials</a>
			<a href="<c:url value="/games/new" />">Upload</a>
			<a href="<c:url value="/about" />">About</a>
			<a href="<c:url value="/blog" />">Blog</a>
			<a href="<c:url value="/irc" />">IRC</a>
			<a href="<c:url value="/JarMatey" />">JarMatey</a>
			
			<c:choose>
				<c:when test="${isLoggedIn}">
				
					<a id="notificationsLink" href="<c:url value="/notifications" />">Notifications</a>
				
					<script>
					function updateNotifications() {
						var xmlhttp;
						if (window.XMLHttpRequest) {// code for IE7+, Firefox, Chrome, Opera, Safari
  							xmlhttp=new XMLHttpRequest();
  						}
						else{// code for IE6, IE5
  							xmlhttp=new ActiveXObject("Microsoft.XMLHTTP");
 						}
						xmlhttp.onreadystatechange=function() {
 							if (xmlhttp.readyState==4 && xmlhttp.status==200){
 								var notificationsCount = xmlhttp.responseText;
 								if(notificationsCount > 0){
									document.getElementById("notificationsLink").innerHTML= "Notifications <span style=\"display:inline-block; color:red;\">(" + notificationsCount + ")</span>";
 								}
 								else{
 									document.getElementById("notificationsLink").innerHTML= "Notifications";
 								}
 							}
  						}
						
						xmlhttp.open("GET","<c:url value="/ajaxNotificationCount" />",true);
						xmlhttp.send();
					}
					updateNotifications();
					setInterval(updateNotifications, 5000);
					</script>
				
				</c:when>
				<c:otherwise>
					<a href="<c:url value="/events" />">Events</a>
				</c:otherwise>
		</c:choose>
		</div>
	</div>
			
	<a title="Static Void Games on Facebook" href="http://www.facebook.com/StaticVoidGames" target="_blank"><img style="height:50px; float:right" src="http://s3.StaticVoidGames.com/images/FacebookLogo.png" /></a>
	<a title="@StaticVoidGames" href="https://twitter.com/StaticVoidGames" target="_blank"><img style="height:50px; float:right" src="http://s3.StaticVoidGames.com/images/TwitterBird.png" /></a>
			
</div>