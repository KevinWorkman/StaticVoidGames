<div id="aboutNavigation">
	<ul>
		<li><a class="aboutLink ${'index' eq page ? 'current' : 'notCurrent'}" href="<c:url value="/about" />">About</a></li>
		<li><a class="aboutLink ${'contact' eq page ? 'current' : 'notCurrent'}" href="<c:url value="/about/contact" />">Contact</a></li>
		<li><a class="aboutLink ${'faq' eq page ? 'current' : 'notCurrent'}" href="<c:url value="/about/faq" />">FAQ</a></li>
		<li><a class="aboutLink ${'legal' eq page ? 'current' : 'notCurrent'}" href="<c:url value="/about/legal" />">Legal</a></li>
		<li><a class="aboutLink ${'openSource' eq page ? 'current' : 'notCurrent'}" href="<c:url value="/about/openSource" />">Open Source</a></li>
	</ul>
</div>