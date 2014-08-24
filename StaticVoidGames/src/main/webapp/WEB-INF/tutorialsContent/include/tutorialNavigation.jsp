
<div id="leftTutorialNav">
	<ul>
		<li><a class="chapterTitle <%="index".equals(session.getAttribute("chapter")) ? "current" : "notCurrent"%>" href="http://StaticVoidGames.com/tutorials/index.jsp">Tutorials</a>
			</li>
	
		<li><a class="chapterTitle <%="intro".equals(session.getAttribute("chapter")) ? "current" : "notCurrent"%>" href="http://StaticVoidGames.com/tutorials/intro/index.jsp">Intro to Programming</a>
			<ul class="<%="intro".equals(session.getAttribute("chapter")) ? "show" : "noShow"%>">
				<li><a class="sectionTitle <%="WhatIsProgramming".equals(session.getAttribute("section")) ? "current" : "notCurrent"%>" href="http://StaticVoidGames.com/tutorials/intro/WhatIsProgramming.jsp">What is Programming?</a></li>
				<li><a class="sectionTitle <%="WhyJava".equals(session.getAttribute("section")) ? "current" : "notCurrent"%>" href="http://StaticVoidGames.com/tutorials/intro/WhyJava.jsp">Why Java?</a></li>
				<li><a class="sectionTitle <%="WhyProcessing".equals(session.getAttribute("section")) ? "current" : "notCurrent"%>" href="http://StaticVoidGames.com/tutorials/intro/WhyProcessing.jsp">Why Processing?</a></li>
				<li><a class="sectionTitle <%="StartingProcessing".equals(session.getAttribute("section")) ? "current" : "notCurrent"%>" href="http://StaticVoidGames.com/tutorials/intro/StartingProcessing.jsp">Starting Processing</a></li>
				<li><a class="sectionTitle <%="StartingProgramming".equals(session.getAttribute("section")) ? "current" : "notCurrent"%>" href="http://StaticVoidGames.com/tutorials/intro/StartingProgramming.jsp">Starting Programming</a></li>
				<li><a class="sectionTitle <%="ProcessingApiExploring".equals(session.getAttribute("section")) ? "current" : "notCurrent"%>" href="http://StaticVoidGames.com/tutorials/intro/ProcessingApiExploring.jsp">Exploring the API</a></li>
				<li><a class="sectionTitle <%="DebuggingProcessing".equals(session.getAttribute("section")) ? "current" : "notCurrent"%>" href="http://StaticVoidGames.com/tutorials/intro/DebuggingProcessing.jsp" id="current">Debugging</a></li>
				<li><a class="sectionTitle <%="ProcessingDeployment".equals(session.getAttribute("section")) ? "current" : "notCurrent"%>" href="http://StaticVoidGames.com/tutorials/intro/ProcessingDeployment.jsp">Deployment</a></li>
			</ul>
		</li>
		
		
		
		<li><a class="chapterTitle <%="basicConcepts".equals(session.getAttribute("chapter")) ? "current" : "notCurrent"%>" href="http://StaticVoidGames.com/tutorials/basicConcepts/index.jsp">Basic Concepts</a>
			<ul class="<%="basicConcepts".equals(session.getAttribute("chapter")) ? "show" : "noShow"%>">
				<li><a class="sectionTitle <%="CallingFunctions".equals(session.getAttribute("section")) ? "current" : "notCurrent"%>" href="http://StaticVoidGames.com/tutorials/basicConcepts/CallingFunctions.jsp">Calling Functions</a></li>
				<li><a class="sectionTitle <%="Variables".equals(session.getAttribute("section")) ? "current" : "notCurrent"%>" href="http://StaticVoidGames.com/tutorials/basicConcepts/Variables.jsp">Variables</a></li>
				<li><a class="sectionTitle <%="CastingPrimitives".equals(session.getAttribute("section")) ? "current" : "notCurrent"%>" href="http://StaticVoidGames.com/tutorials/basicConcepts/CastingPrimitives.jsp">Casting</a></li>
				<li><a class="sectionTitle <%="Booleans".equals(session.getAttribute("section")) ? "current" : "notCurrent"%>" href="http://StaticVoidGames.com/tutorials/basicConcepts/Booleans.jsp">Booleans</a></li>
				<li><a class="sectionTitle <%="Strings".equals(session.getAttribute("section")) ? "current" : "notCurrent"%>" href="http://StaticVoidGames.com/tutorials/basicConcepts/Strings.jsp">Strings</a></li>
				<li><a class="sectionTitle <%="Colors".equals(session.getAttribute("section")) ? "current" : "notCurrent"%>" href="http://StaticVoidGames.com/tutorials/basicConcepts/Colors.jsp">Colors</a></li>
				<li><a class="sectionTitle <%="WritingYourOwnFunctions".equals(session.getAttribute("section")) ? "current" : "notCurrent"%>" href="http://StaticVoidGames.com/tutorials/basicConcepts/WritingYourOwnFunctions.jsp">Writing Your Own Functions</a></li>
				<li><a class="sectionTitle <%="IfStatements".equals(session.getAttribute("section")) ? "current" : "notCurrent"%>" href="http://StaticVoidGames.com/tutorials/basicConcepts/IfStatements.jsp">If Statements</a></li>
				<li><a class="sectionTitle <%="Loops".equals(session.getAttribute("section")) ? "current" : "notCurrent"%>" href="http://StaticVoidGames.com/tutorials/basicConcepts/Loops.jsp">Loops</a></li>
				<li><a class="sectionTitle <%="Scope".equals(session.getAttribute("section")) ? "current" : "notCurrent"%>" href="http://StaticVoidGames.com/tutorials/basicConcepts/Scope.jsp">Scope</a></li>
				<li><a class="sectionTitle <%="Animation".equals(session.getAttribute("section")) ? "current" : "notCurrent"%>" href="http://StaticVoidGames.com/tutorials/basicConcepts/Animation.jsp">Animation</a></li>
				<li><a class="sectionTitle <%="Arrays".equals(session.getAttribute("section")) ? "current" : "notCurrent"%>" href="http://StaticVoidGames.com/tutorials/basicConcepts/Arrays.jsp">Arrays</a></li>
			
			</ul>
		</li>
		
		<li><a class="chapterTitle <%="intermediateConcepts".equals(session.getAttribute("chapter")) ? "current" : "notCurrent"%>" href="http://StaticVoidGames.com/tutorials/intermediateConcepts/index.jsp">Intermediate Concepts</a>
			<ul class="<%="intermediateConcepts".equals(session.getAttribute("chapter")) ? "show" : "noShow"%>">
				<li><a class="sectionTitle <%="MouseInput".equals(session.getAttribute("section")) ? "current" : "notCurrent"%>" href="http://StaticVoidGames.com/tutorials/intermediateConcepts/MouseInput.jsp">Mouse Input</a></li>
				<li><a class="sectionTitle <%="KeyboardInput".equals(session.getAttribute("section")) ? "current" : "notCurrent"%>" href="http://StaticVoidGames.com/tutorials/intermediateConcepts/KeyboardInput.jsp">Keyboard Input</a></li>
				<li><a class="sectionTitle <%="CollisionDetection".equals(session.getAttribute("section")) ? "current" : "notCurrent"%>" href="http://StaticVoidGames.com/tutorials/intermediateConcepts/CollisionDetection.jsp">Collision Detection</a></li>
				<li><a class="sectionTitle <%="Pong".equals(session.getAttribute("section")) ? "current" : "notCurrent"%>" href="http://StaticVoidGames.com/tutorials/intermediateConcepts/Pong.jsp">Pong</a></li>
			
			</ul>
		</li>
		
		<li><a class="chapterTitle <%="objects".equals(session.getAttribute("chapter")) ? "current" : "notCurrent"%>" href="http://StaticVoidGames.com/tutorials/objects/index.jsp">Object Oriented Programming</a>
			<ul class="<%="objects".equals(session.getAttribute("chapter")) ? "show" : "noShow"%>">
				<li><a class="sectionTitle <%="Objects".equals(session.getAttribute("section")) ? "current" : "notCurrent"%>" href="http://StaticVoidGames.com/tutorials/objects/Objects.jsp">Objects</a></li>
				<li><a class="sectionTitle <%="Fireworks".equals(session.getAttribute("section")) ? "current" : "notCurrent"%>" href="http://StaticVoidGames.com/tutorials/objects/Fireworks.jsp">Fireworks</a></li>
				<li><a class="sectionTitle <%="Inheritance".equals(session.getAttribute("section")) ? "current" : "notCurrent"%>" href="http://StaticVoidGames.com/tutorials/objects/Inheritance.jsp">Inheritance</a></li>
				<li><a class="sectionTitle <%="AdvancedInheritance".equals(session.getAttribute("section")) ? "current" : "notCurrent"%>" href="http://StaticVoidGames.com/tutorials/objects/AdvancedInheritance.jsp">Advanced Inheritance</a></li>
			
			</ul>
		</li>
		
		<li><a class="chapterTitle <%="basicJava".equals(session.getAttribute("chapter")) ? "current" : "notCurrent"%>" href="http://StaticVoidGames.com/tutorials/basicJava/index.jsp">Basic Java</a>
			<ul class="<%="basicJava".equals(session.getAttribute("chapter")) ? "show" : "noShow"%>">
				<li><a class="sectionTitle <%="FromProcessingToJava".equals(session.getAttribute("section")) ? "current" : "notCurrent"%>" href="http://StaticVoidGames.com/tutorials/basicJava/FromProcessingToJava.jsp">From Processing to Java</a></li>
				<li><a class="sectionTitle <%="JavaSetup".equals(session.getAttribute("section")) ? "current" : "notCurrent"%>" href="http://StaticVoidGames.com/tutorials/basicJava/JavaSetup.jsp">Java Setup</a></li>
				<li><a class="sectionTitle <%="HelloWorld".equals(session.getAttribute("section")) ? "current" : "notCurrent"%>" href="http://StaticVoidGames.com/tutorials/basicJava/HelloWorld.jsp">Hello World</a></li>
				<li><a class="sectionTitle <%="ClassSetup".equals(session.getAttribute("section")) ? "current" : "notCurrent"%>" href="http://StaticVoidGames.com/tutorials/basicJava/ClassSetup.jsp">Class Setup</a></li>
				<li><a class="sectionTitle <%="Strings".equals(session.getAttribute("section")) ? "current" : "notCurrent"%>" href="http://StaticVoidGames.com/tutorials/basicJava/Strings.jsp">Strings</a></li>
				<li><a class="sectionTitle <%="UserInput".equals(session.getAttribute("section")) ? "current" : "notCurrent"%>" href="http://StaticVoidGames.com/tutorials/basicJava/UserInput.jsp">User Input</a></li>
				<li><a class="sectionTitle <%="HigherLower".equals(session.getAttribute("section")) ? "current" : "notCurrent"%>" href="http://StaticVoidGames.com/tutorials/basicJava/HigherLower.jsp">Higher/Lower</a></li>
				<li><a class="sectionTitle <%="RockPaperScissors".equals(session.getAttribute("section")) ? "current" : "notCurrent"%>" href="http://StaticVoidGames.com/tutorials/basicJava/RockPaperScissors.jsp">Rock Paper Scissors</a></li>
			
			</ul>
		</li>
		
		<li><a class="chapterTitle <%="swing".equals(session.getAttribute("chapter")) ? "current" : "notCurrent"%>" href="http://StaticVoidGames.com/tutorials/swing/index.jsp">Swing</a>
			<ul class="<%="swing".equals(session.getAttribute("chapter")) ? "show" : "noShow"%>">
				<li><a class="sectionTitle <%="StartingSwing".equals(session.getAttribute("section")) ? "current" : "notCurrent"%>" href="http://StaticVoidGames.com/tutorials/swing/StartingSwing.jsp">Starting Swing</a></li>
				<li><a class="sectionTitle <%="JavaColors".equals(session.getAttribute("section")) ? "current" : "notCurrent"%>" href="http://StaticVoidGames.com/tutorials/swing/JavaColors.jsp">Java Colors</a></li>
				<li><a class="sectionTitle <%="Layouts".equals(session.getAttribute("section")) ? "current" : "notCurrent"%>" href="http://StaticVoidGames.com/tutorials/swing/Layouts.jsp">Layouts</a></li>
				<li><a class="sectionTitle <%="ActionListeners".equals(session.getAttribute("section")) ? "current" : "notCurrent"%>" href="http://StaticVoidGames.com/tutorials/swing/ActionListeners.jsp">Action Listeners</a></li>
				<li><a class="sectionTitle <%="RockPaperScissorsSwing".equals(session.getAttribute("section")) ? "current" : "notCurrent"%>" href="http://StaticVoidGames.com/tutorials/swing/RockPaperScissors.jsp">Rock Paper Scissors</a></li>
				<li><a class="sectionTitle <%="CustomPainting".equals(session.getAttribute("section")) ? "current" : "notCurrent"%>" href="http://StaticVoidGames.com/tutorials/swing/CustomPainting.jsp">Custom Painting</a></li>
				<li><a class="sectionTitle <%="Listeners".equals(session.getAttribute("section")) ? "current" : "notCurrent"%>" href="http://StaticVoidGames.com/tutorials/swing/Listeners.jsp">Mouse and Keyboard Listeners</a></li>
			<li><a class="sectionTitle <%="SwingTimers".equals(session.getAttribute("section")) ? "current" : "notCurrent"%>" href="http://StaticVoidGames.com/tutorials/swing/SwingTimers.jsp">Swing Timers</a></li>
			<li><a class="sectionTitle <%="SelfListening".equals(session.getAttribute("section")) ? "current" : "notCurrent"%>" href="http://StaticVoidGames.com/tutorials/swing/SelfListening.jsp">Self Listening</a></li>
			<li><a class="sectionTitle <%="SwingPong".equals(session.getAttribute("section")) ? "current" : "notCurrent"%>" href="http://StaticVoidGames.com/tutorials/swing/SwingPong.jsp">Pong</a></li>
			</ul>
		</li>
		
		<li><a class="chapterTitle <%="deployment".equals(session.getAttribute("chapter")) ? "current" : "notCurrent"%>" href="http://StaticVoidGames.com/tutorials/deployment/index.jsp">Deployment</a>
			<ul class="<%="objects".equals(session.getAttribute("chapter")) ? "show" : "noShow"%>">
				<li><a class="sectionTitle <%="History".equals(session.getAttribute("section")) ? "current" : "notCurrent"%>" href="http://StaticVoidGames.com/tutorials/deployment/History.jsp">A Brief History</a></li>
				<li><a class="sectionTitle <%="JavaSecuritySettings".equals(session.getAttribute("section")) ? "current" : "notCurrent"%>" href="http://StaticVoidGames.com/tutorials/deployment/JavaSecuritySettings.jsp">Java Security Settings</a></li>
			
				<li><a class="sectionTitle <%="BasicProcessingExample".equals(session.getAttribute("section")) ? "current" : "notCurrent"%>" href="http://StaticVoidGames.com/tutorials/deployment/BasicProcessingExample.jsp">Example: Basic Processing Sketch</a></li>
				<li><a class="sectionTitle <%="AdvancedProcessingExample".equals(session.getAttribute("section")) ? "current" : "notCurrent"%>" href="http://StaticVoidGames.com/tutorials/deployment/AdvancedProcessingExample.jsp">Example: Advanced Processing Sketch</a></li>
				<li><a class="sectionTitle <%="LwjglExample".equals(session.getAttribute("section")) ? "current" : "notCurrent"%>" href="http://StaticVoidGames.com/tutorials/deployment/LwjglExample.jsp">Example: LWJGL Game</a></li>
				<li><a class="sectionTitle <%="JoglExample".equals(session.getAttribute("section")) ? "current" : "notCurrent"%>" href="http://StaticVoidGames.com/tutorials/deployment/JoglExample.jsp">Example: JOGL Game</a></li>
				
			</ul>
		</li>
		
		<!--
		<li>
			<a class="chapterTitle <%="deployingJava".equals(session.getAttribute("chapter")) ? "current" : "notCurrent"%>" href="http://StaticVoidGames.com/tutorials/deploying/index.jsp">Deploying Java</a>
			<ul class="<%="deploying".equals(session.getAttribute("chapter")) ? "show" : "noShow"%>">
				<li><a class="sectionTitle <%="AppletAndApplication".equals(session.getAttribute("section")) ? "current" : "notCurrent"%>" href="http://StaticVoidGames.com/tutorials/deploying/AppletAndApplication">Applet and Application</a></li>
				<li><a class="sectionTitle <%="PackagingResourcesIntoJars".equals(session.getAttribute("section")) ? "current" : "notCurrent"%>" href="http://StaticVoidGames.com/tutorials/deploying/PackagingResourcesIntoJars">Packaging Resources into Jars</a></li>
			</ul>
		
		</li>
		-->
		
	</ul>

</div>