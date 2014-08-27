<div id="leftBar">
	<h3><a class="bar <%="index".equals(session.getAttribute("current")) ? "current" : "notCurrent"%>" href="index.jsp" title="SvgExe">SvgExe</a></h3>
	<h3><a class="bar <%="processing".equals(session.getAttribute("current")) ? "current" : "notCurrent"%>" href="processing.jsp" title="Processing Tool">Processing Tool</a></h3>
	<a class="bar <%="download".equals(session.getAttribute("current")) ? "current" : "notCurrent"%>" href="download.jsp" title="Download">Download</a>
	<a class="bar <%="faq".equals(session.getAttribute("current")) ? "current" : "notCurrent"%>" href="faq.jsp" title="Frequently Asked Questions">FAQ</a>
	
	<a class="bar notCurrent" href="http://StaticVoidGames.com/tutorials/deployment/BasicProcessingExample.jsp">Example: Basic Processing Sketch</a>
	<a class="bar notCurrent" href="http://StaticVoidGames.com/tutorials/deployment/AdvancedProcessingExample.jsp">Example: Advanced Processing Sketch</a>
	<a class="bar notCurrent" href="http://StaticVoidGames.com/tutorials/deployment/LwjglExample.jsp">Example: LWJGL Game</a>
	<a class="bar notCurrent" href="http://StaticVoidGames.com/tutorials/deployment/JoglExample.jsp">Example: JOGL Game</a>
				
			
	
	
</div>