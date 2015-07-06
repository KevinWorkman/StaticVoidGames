 <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
 
<nav class="navbar navbar-inverse navbar-fixed-top" style="height:50px; min-width:720px; overflow:hidden;">
	<div>
		<a class="logo" href="#"><img style="height:50px; vertical-align:top;" src="http://staticvoidgames.com/images/StaticVoidGamesLogo3.png"/></a>
       
        
        <div style="display:inline-block; vertical-align:top;">
        
	        <div class="userNavigationBar">        
	        
				<c:choose>
				<c:when test="${isLoggedIn}">
					Hello, <a href="<c:url value="/members/${loggedInUser}" />">${loggedInUser}</a>!
					<a id="notificationsLink" style="float:right;" href="<c:url value="/notifications" />">Notifications</a>
					<script>
					function updateNotifications() {
						$.ajax({url: "<c:url value="/ajaxNotificationCount" />", success: function(result){
								console.log("result: " + result);
								if(result > 0){
									$("#notificationsLink").html("Notifications <span style=\"display:inline-block; color:red;\">(" + result + ")</span>");
								}
								else{
									$("#notificationsLink").html("Notifications");
								}
					    }});
					}
					updateNotifications();
					setInterval(updateNotifications, 5000);
					</script>
				</c:when>
				<c:otherwise>
					<a href="<c:url value="/login" />">Login</a>
				</c:otherwise>
			</c:choose>
	        
	        
	        
	        </div>
	        
	        
	        
	        
			<hr style="margin-top:0px; margin-bottom:0px;"  />
			<div class="navigationBar" style="margin-top:5px;">
				<a href="<c:url value="/games" />">Play</a>
				<a href="<c:url value="/tutorials" />">Tutorials</a>
				<a href="<c:url value="/upload" />">Upload</a>
				<a href="<c:url value="/about" />">About</a>
				<a href="<c:url value="/blog" />">Blog</a>
				<a href="<c:url value="http://forum.StaticVoidGames.com" />">Forum</a>
				<a href="<c:url value="/notifications" />">Events</a>
			</div>
        </div>
        
		<div style="display:inline-block; float:right">
			<a title="Static Void Games on Facebook" href="http://www.facebook.com/StaticVoidGames" target="_blank"><img style="height:50px;" src="http://s3.StaticVoidGames.com/images/FacebookLogo.png" /></a>
			<a title="@StaticVoidGames" href="https://twitter.com/StaticVoidGames" target="_blank"><img style="height:50px;" src="http://s3.StaticVoidGames.com/images/TwitterBird.png" /></a>
		</div>
        
  </div>
    </nav>