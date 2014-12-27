<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<body style="background-image:url(<c:url value="${backgroundImage}"/>);">
<%@ include file="../include/analytics.jsp"%>
<%@ include file="../include/navigation.jsp" %>
	<h1>Members</h1>

	<c:forEach items="${members}" var="member">
		<p><a href="<c:url value="/members/${member.getMemberName()}" />">${member.getMemberName()}</a></p>
	</c:forEach>

			<%@ include file="../include/advertisement.jsp" %>
   			<%@ include file="../include/openSource.jsp" %>

</body>
</html>