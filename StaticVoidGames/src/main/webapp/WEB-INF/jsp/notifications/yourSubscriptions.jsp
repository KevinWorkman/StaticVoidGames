<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<body style="background-image:url(<c:url value="${backgroundImage}"/>);">
<%@ include file="../include/analytics.jsp"%>
	<%@ include file="../include/navigation.jsp"%>

	<h1>Your Subscriptions</h1>


	<table>

		<tr>
			<td>ID</td>
			<td>Label</td>
			<td>Last Viewed</td>
			<td>Timestamp</td>
		</tr>

		<c:forEach items="${subscriptions}" var="s">
		

			<tr>

				<td>${s.getEntityId()}</td>
				<td>${s.getLabel()}</td>
				<td>${s.getLastNotificationViewedTimestamp()}</td>
				<td>${s.getTimestamp()}</td>

			</tr>



		</c:forEach>

	</table>
	
			<%@ include file="../include/advertisement.jsp" %>
   			<%@ include file="../include/openSource.jsp" %>

</body>
</html>