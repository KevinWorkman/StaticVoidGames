<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
	<title>IRC - Static Void Games</title>
	
	<link rel='stylesheet' type='text/css' href='http://fonts.googleapis.com/css?family=Open+Sans' >
	
	<link rel="stylesheet" type="text/css" href="<c:url value="/css/general.css"/>">
	<link rel="stylesheet" type="text/css" href="<c:url value="/css/everyPage.css"/>">
	<link rel="stylesheet" type="text/css" href="<c:url value="/css/irc.css"/>">
	
	<link rel="shortcut icon" href="<c:url value="/images/favicon.png"/>" />
	
	<script type="text/javascript">
function showHide(shID) {
   if (document.getElementById(shID)) {
      if (document.getElementById(shID).style.display != 'none') {
         document.getElementById(shID).style.display = 'none';
      }
      else {
         document.getElementById(shID).style.display = 'block';
      }
   }
}
</script>
</head>

<body style="background-image:url(<c:url value="${backgroundImage}"/>);">
<%@ include file="../include/analytics.jsp"%>
<%@ include file="../include/navigation.jsp" %>
<div id="contentPane">

<div class="darkBackground lightText textPadding topMargin thinGrayBorder centeredBlock" style="width:500px;">
<div class="centered"><h1>IRC</h1></div>
<div class="centered"><p id="moreInfoLink" style="display:none;"><a href="#" onclick="showHide('infoDiv'); showHide('moreInfoLink'); return false;">More info...</a></p></div>
<div id="infoDiv" class="darkBackground lightText textPadding topMargin">
	<ul>
	<li>This is a chat service hosted by <a href="https://kiwiirc.com/" target="_blank">KiwiIRC</a>.</li>
	<li>Use this to chat with other Static Void Gamers, get coding help, or talk about anything you want.</li>
	<li>Note: Never give your password out to anybody. The only form that requires your password is the StaticVoidGames login page.</li>
	<li>If you want to write a longer post, consider <a href="<c:url value="/blog" />">creating a blog entry</a>.</li>
	<li>If you want to contact Kevin (the site admin) directly, view the <a href="<c:url value="/about/contact" />">contact page</a>.</li>
	</ul>
	<div class="centered"><p><a href="#" onclick="showHide('infoDiv'); showHide('moreInfoLink'); return false;">Hide this info.</a></p></div>
	</div>
</div>

	<div class="darkBackground lightText textPadding topMargin">
		<iframe src="https://kiwiirc.com/client/irc.esper.net/?nick=nickname?&theme=cli#StaticVoidGames" style="border:0; width:100%; height:100%;"></iframe>
	</div>

			<%@ include file="../include/advertisement.jsp" %>
   			<%@ include file="../include/openSource.jsp" %>
   </div>
</body>
</html>