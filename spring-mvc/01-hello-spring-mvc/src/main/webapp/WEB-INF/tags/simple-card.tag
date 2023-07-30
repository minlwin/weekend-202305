<%@ tag language="java" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ attribute name="item" required="true" %>
<%@ attribute name="link"  %>
<div class="card">
	<div class="card-body">
		<h5 class="card-title">
			${item}
		</h5>
		
		<c:if test="${not empty link}">
			<div class="mt-3">
				<a href="${link}" class="btn btn-outline-primary">Show Dates</a>
			</div>
		</c:if>
	</div>
</div>
