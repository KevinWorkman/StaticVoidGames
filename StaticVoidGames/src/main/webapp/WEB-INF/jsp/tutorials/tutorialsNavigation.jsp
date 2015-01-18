
<div id="leftTutorialNav">
	<ul>
		<li><a class="chapterTitle ${'index' eq chapter ? 'current' : 'notCurrent'}" href="<c:url value="/tutorials" />">Tutorials</a>
			</li>
	
			<li><a class="chapterTitle ${'hourOfCode' eq chapter ? 'current' : 'notCurrent'}" href="<c:url value="/tutorials/hourOfCode/index" />">Hour of Code</a>
			<ul class="${'hourOfCode' eq chapter ? 'show' : 'noShow'}">
				<li><a class="sectionTitle ${'setup' eq section ? 'current' : 'notCurrent'}" href="<c:url value="/tutorials/hourOfCode/setup" />">Setup</a></li>
				<li><a class="sectionTitle ${'callingFunctions' eq section ? 'current' : 'notCurrent'}" href="<c:url value="/tutorials/hourOfCode/callingFunctions" />">Calling Functions</a></li>
				<li><a class="sectionTitle ${'variables' eq section ? 'current' : 'notCurrent'}" href="<c:url value="/tutorials/hourOfCode/variables" />">Variables</a></li>
				<li><a class="sectionTitle ${'writingFunctions' eq section ? 'current' : 'notCurrent'}" href="<c:url value="/tutorials/hourOfCode/writingFunctions" />">Writing Functions</a></li>
				<li><a class="sectionTitle ${'animation' eq section ? 'current' : 'notCurrent'}" href="<c:url value="/tutorials/hourOfCode/animation" />">Animation</a></li>
				<li><a class="sectionTitle ${'ifStatements' eq section ? 'current' : 'notCurrent'}" href="<c:url value="/tutorials/hourOfCode/ifStatements" />">If Statements</a></li>
				<li><a class="sectionTitle ${'input' eq section ? 'current' : 'notCurrent'}" href="<c:url value="/tutorials/hourOfCode/input" />">Input</a></li>
				<li><a class="sectionTitle ${'pong' eq section ? 'current' : 'notCurrent'}" href="<c:url value="/tutorials/hourOfCode/pong" />" >Pong</a></li>
				<li><a class="sectionTitle ${'next' eq section ? 'current' : 'notCurrent'}" href="<c:url value="/tutorials/hourOfCode/next" />">What's Next?</a></li>
			</ul>
		</li>
	
		<li><a class="chapterTitle ${'intro' eq chapter ? 'current' : 'notCurrent'}" href="<c:url value="/tutorials/intro/index" />">Intro to Programming</a>
			<ul class="${'intro' eq chapter ? 'show' : 'noShow'}">
				<li><a class="sectionTitle ${'whatIsProgramming' eq section ? 'current' : 'notCurrent'}" href="<c:url value="/tutorials/intro/whatIsProgramming" />">What is Programming?</a></li>
				<li><a class="sectionTitle ${'whyJava' eq section ? 'current' : 'notCurrent'}" href="<c:url value="/tutorials/intro/whyJava" />">Why Java?</a></li>
				<li><a class="sectionTitle ${'whyProcessing' eq section ? 'current' : 'notCurrent'}" href="<c:url value="/tutorials/intro/whyProcessing" />">Why Processing?</a></li>
				<li><a class="sectionTitle ${'startingProcessing' eq section ? 'current' : 'notCurrent'}" href="<c:url value="/tutorials/intro/startingProcessing" />">Starting Processing</a></li>
				<li><a class="sectionTitle ${'startingProgramming' eq section ? 'current' : 'notCurrent'}" href="<c:url value="/tutorials/intro/startingProgramming" />">Starting Programming</a></li>
				<li><a class="sectionTitle ${'processingApiExploring' eq section ? 'current' : 'notCurrent'}" href="<c:url value="/tutorials/intro/processingApiExploring" />">Exploring the API</a></li>
				<li><a class="sectionTitle ${'debuggingProcessing' eq section ? 'current' : 'notCurrent'}" href="<c:url value="/tutorials/intro/debuggingProcessing" />" id='current'>Debugging</a></li>
				<li><a class="sectionTitle ${'processingDeployment' eq section ? 'current' : 'notCurrent'}" href="<c:url value="/tutorials/intro/processingDeployment" />">Deployment</a></li>
			</ul>
		</li>
		
		
		
		<li><a class="chapterTitle ${'basicConcepts' eq chapter ? 'current' : 'notCurrent'}" href="<c:url value="/tutorials/basicConcepts/index" />">Basic Concepts</a>
			<ul class="${'basicConcepts' eq chapter ? 'show' : 'noShow'}">
				<li><a class="sectionTitle ${'callingFunctions' eq section ? 'current' : 'notCurrent'}" href="<c:url value="/tutorials/basicConcepts/callingFunctions" />">Calling Functions</a></li>
				<li><a class="sectionTitle ${'variables' eq section ? 'current' : 'notCurrent'}" href="<c:url value="/tutorials/basicConcepts/variables" />">Variables</a></li>
				<li><a class="sectionTitle ${'castingPrimitives' eq section ? 'current' : 'notCurrent'}" href="<c:url value="/tutorials/basicConcepts/castingPrimitives" />">Casting</a></li>
				<li><a class="sectionTitle ${'booleans' eq section ? 'current' : 'notCurrent'}" href="<c:url value="/tutorials/basicConcepts/booleans" />">Booleans</a></li>
				<li><a class="sectionTitle ${'strings' eq section ? 'current' : 'notCurrent'}" href="<c:url value="/tutorials/basicConcepts/strings" />">Strings</a></li>
				<li><a class="sectionTitle ${'colors' eq section ? 'current' : 'notCurrent'}" href="<c:url value="/tutorials/basicConcepts/colors" />">Colors</a></li>
				<li><a class="sectionTitle ${'writingYourOwnFunctions' eq section ? 'current' : 'notCurrent'}" href="<c:url value="/tutorials/basicConcepts/writingYourOwnFunctions" />">Writing Your Own Functions</a></li>
				<li><a class="sectionTitle ${'ifStatements' eq section ? 'current' : 'notCurrent'}" href="<c:url value="/tutorials/basicConcepts/ifStatements" />">If Statements</a></li>
				<li><a class="sectionTitle ${'loops' eq section ? 'current' : 'notCurrent'}" href="<c:url value="/tutorials/basicConcepts/loops" />">Loops</a></li>
				<li><a class="sectionTitle ${'scope' eq section ? 'current' : 'notCurrent'}" href="<c:url value="/tutorials/basicConcepts/scope" />">Scope</a></li>
				<li><a class="sectionTitle ${'animation' eq section ? 'current' : 'notCurrent'}" href="<c:url value="/tutorials/basicConcepts/animation" />">Animation</a></li>
				<li><a class="sectionTitle ${'arrays' eq section ? 'current' : 'notCurrent'}" href="<c:url value="/tutorials/basicConcepts/arrays" />">Arrays</a></li>
			
			</ul>
		</li>
		
		<li><a class="chapterTitle ${'intermediateConcepts' eq chapter ? 'current' : 'notCurrent'}" href="<c:url value="/tutorials/intermediateConcepts/index" />">Intermediate Concepts</a>
			<ul class="${'intermediateConcepts' eq chapter ? 'show' : 'noShow'}">
				<li><a class="sectionTitle ${'mouseInput' eq section ? 'current' : 'notCurrent'}" href="<c:url value="/tutorials/intermediateConcepts/mouseInput" />">Mouse Input</a></li>
				<li><a class="sectionTitle ${'keyboardInput' eq section ? 'current' : 'notCurrent'}" href="<c:url value="/tutorials/intermediateConcepts/keyboardInput" />">Keyboard Input</a></li>
				<li><a class="sectionTitle ${'collisionDetection' eq section ? 'current' : 'notCurrent'}" href="<c:url value="/tutorials/intermediateConcepts/collisionDetection" />">Collision Detection</a></li>
				<li><a class="sectionTitle ${'pong' eq section ? 'current' : 'notCurrent'}" href="<c:url value="/tutorials/intermediateConcepts/pong" />">Pong</a></li>
			
			</ul>
		</li>
		
		<li><a class="chapterTitle ${'objects' eq chapter ? 'current' : 'notCurrent'}" href="<c:url value="/tutorials/objects/index" />">Object Oriented Programming</a>
			<ul class="${'objects' eq chapter ? 'show' : 'noShow'}">
				<li><a class="sectionTitle ${'objects' eq section ? 'current' : 'notCurrent'}" href="<c:url value="/tutorials/objects/objects" />">Objects</a></li>
				<li><a class="sectionTitle ${'fireworks' eq section ? 'current' : 'notCurrent'}" href="<c:url value="/tutorials/objects/fireworks" />">Fireworks</a></li>
				<li><a class="sectionTitle ${'inheritance' eq section ? 'current' : 'notCurrent'}" href="<c:url value="/tutorials/objects/inheritance" />">Inheritance</a></li>
				<li><a class="sectionTitle ${'advancedInheritance' eq section ? 'current' : 'notCurrent'}" href="<c:url value="/tutorials/objects/advancedInheritance" />">Advanced Inheritance</a></li>
			</ul>
		</li>
		
		<li><a class="chapterTitle ${'basicJava' eq chapter ? 'current' : 'notCurrent'}" href="<c:url value="/tutorials/basicJava/index" />">Basic Java</a>
			<ul class="${'basicJava' eq chapter ? 'show' : 'noShow'}">
				<li><a class="sectionTitle ${'fromProcessingToJava' eq section ? 'current' : 'notCurrent'}" href="<c:url value="/tutorials/basicJava/fromProcessingToJava" />">From Processing to Java</a></li>
				<li><a class="sectionTitle ${'javaSetup' eq section ? 'current' : 'notCurrent'}" href="<c:url value="/tutorials/basicJava/javaSetup" />">Java Setup</a></li>
				<li><a class="sectionTitle ${'helloWorld' eq section ? 'current' : 'notCurrent'}" href="<c:url value="/tutorials/basicJava/helloWorld" />">Hello World</a></li>
				<li><a class="sectionTitle ${'classSetup' eq section ? 'current' : 'notCurrent'}" href="<c:url value="/tutorials/basicJava/classSetup" />">Class Setup</a></li>
				<li><a class="sectionTitle ${'strings' eq section ? 'current' : 'notCurrent'}" href="<c:url value="/tutorials/basicJava/strings" />">Strings</a></li>
				<li><a class="sectionTitle ${'userInput' eq section ? 'current' : 'notCurrent'}" href="<c:url value="/tutorials/basicJava/userInput" />">User Input</a></li>
				<li><a class="sectionTitle ${'higherLower' eq section ? 'current' : 'notCurrent'}" href="<c:url value="/tutorials/basicJava/higherLower" />">Higher/Lower</a></li>
				<li><a class="sectionTitle ${'rockPaperScissors' eq section ? 'current' : 'notCurrent'}" href="<c:url value="/tutorials/basicJava/rockPaperScissors" />">Rock Paper Scissors</a></li>
			
			</ul>
		</li>
		
		<li><a class="chapterTitle ${'swing' eq chapter ? 'current' : 'notCurrent'}" href="<c:url value="/tutorials/swing/index" />">Swing</a>
			<ul class="${'swing' eq chapter ? 'show' : 'noShow'}">
				<li><a class="sectionTitle ${'startingSwing' eq section ? 'current' : 'notCurrent'}" href="<c:url value="/tutorials/swing/startingSwing" />">Starting Swing</a></li>
				<li><a class="sectionTitle ${'javaColors' eq section ? 'current' : 'notCurrent'}" href="<c:url value="/tutorials/swing/javaColors" />">Java Colors</a></li>
				<li><a class="sectionTitle ${'layouts' eq section ? 'current' : 'notCurrent'}" href="<c:url value="/tutorials/swing/layouts" />">Layouts</a></li>
				<li><a class="sectionTitle ${'actionListeners' eq section ? 'current' : 'notCurrent'}" href="<c:url value="/tutorials/swing/actionListeners" />">Action Listeners</a></li>
				<li><a class="sectionTitle ${'rockPaperScissorsSwing' eq section ? 'current' : 'notCurrent'}" href="<c:url value="/tutorials/swing/rockPaperScissors" />">Rock Paper Scissors</a></li>
				<li><a class="sectionTitle ${'customPainting' eq section ? 'current' : 'notCurrent'}" href="<c:url value="/tutorials/swing/customPainting" />">Custom Painting</a></li>
				<li><a class="sectionTitle ${'listeners' eq section ? 'current' : 'notCurrent'}" href="<c:url value="/tutorials/swing/listeners" />">Mouse and Keyboard Listeners</a></li>
			<li><a class="sectionTitle ${'swingTimers' eq section ? 'current' : 'notCurrent'}" href="<c:url value="/tutorials/swing/swingTimers" />">Swing Timers</a></li>
			<li><a class="sectionTitle ${'selfListening' eq section ? 'current' : 'notCurrent'}" href="<c:url value="/tutorials/swing/selfListening" />">Self Listening</a></li>
			<li><a class="sectionTitle ${'swingPong' eq section ? 'current' : 'notCurrent'}" href="<c:url value="/tutorials/swing/swingPong" />">Pong</a></li>
			</ul>
		</li>
		
		<li><a class="chapterTitle ${'deployment' eq chapter ? 'current' : 'notCurrent'}" href="<c:url value="/tutorials/deployment/index" />">Deployment</a>
			<ul class="${'objects' eq chapter ? 'show' : 'noShow'}">
				<li><a class="sectionTitle ${'history' eq section ? 'current' : 'notCurrent'}" href="<c:url value="/tutorials/deployment/history" />">A Brief History</a></li>
				<li><a class="sectionTitle ${'javaSecuritySettings' eq section ? 'current' : 'notCurrent'}" href="<c:url value="/tutorials/deployment/javaSecuritySettings" />">Java Security Settings</a></li>
			
				<li><a class="sectionTitle ${'basicProcessingExample' eq section ? 'current' : 'notCurrent'}" href="<c:url value="/tutorials/deployment/basicProcessingExample" />">Example: Basic Processing Sketch</a></li>
				<li><a class="sectionTitle ${'advancedProcessingExample' eq section ? 'current' : 'notCurrent'}" href="<c:url value="/tutorials/deployment/advancedProcessingExample" />">Example: Advanced Processing Sketch</a></li>
				<li><a class="sectionTitle ${'lwjglExample' eq section ? 'current' : 'notCurrent'}" href="<c:url value="/tutorials/deployment/lwjglExample" />">Example: LWJGL Game</a></li>
				<li><a class="sectionTitle ${'joglExample' eq section ? 'current' : 'notCurrent'}" href="<c:url value="/tutorials/deployment/joglExample" />">Example: JOGL Game</a></li>
				<li><a class="sectionTitle ${'libGdxHtml' eq section ? 'current' : 'notCurrent'}" href="<c:url value="/tutorials/deployment/libGdxHtml" />">LibGDX HTML</a></li>
				
			</ul>
		</li>

	</ul>

</div>