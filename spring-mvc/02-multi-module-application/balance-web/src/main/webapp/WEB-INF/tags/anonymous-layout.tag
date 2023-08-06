<%@ tag language="java" pageEncoding="ISO-8859-1"%>
<%@ attribute name="title" required="true" %>
<%@ attribute name="subTitle" required="true" %>


<div class="container text-center pt-4">
	<!-- Title -->
	<h1>${title}</h1>
	<!-- Sub Title -->
	<h4>${subTitle}</h4>
	
	<div class="anonymous-content">
		<!-- Main Features -->
		<jsp:doBody></jsp:doBody>
	</div>
	
</div>