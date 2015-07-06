<div class="panel panel-default" style="font-size:small; width:275px; margin-left:auto; margin-right:auto;">
	<div class="panel-heading">Static Void Games is open-source!</div>
		<div class="panel-body">
			<ul style="list-style-position: inside;  padding:0px; margin:0px; ">
			<c:if test="${openSourceLinks != null}">
				<c:forEach items="${openSourceLinks}" var="osLink">
					<li ><a href="<c:url value="${osLink.getUrl() }"/>" target="_blank">${osLink.getTitle()}</a></li>
				</c:forEach>
			</c:if>
				<li><a href="https://github.com/KevinWorkman/StaticVoidGames" target="_blank" >Static Void Games on GitHub</a></li>
				<li><a href="https://github.com/KevinWorkman/StaticVoidGames/issues" target="_blank" >Report an Issue / Request a Feature</a></li>
			</ul>
		</div>
</div>