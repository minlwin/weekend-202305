<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>    
<%@ taglib prefix="custom" tagdir="/WEB-INF/tags" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>

<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-4bw+/aepP/YC94hEpVNVgiZdgIC5+VKNBQNGCHeKRQN+PtmoHDEXuppvnDJzQIu9" crossorigin="anonymous">
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/js/bootstrap.bundle.min.js" integrity="sha384-HwwvtgBNo3bZJJLYd8oVXjrBZt8cqVSpeBNS5n7C8IVInixGAoxmnlMuBnhbgrkm" crossorigin="anonymous"></script>

</head>
<body>

	<div class="container mt-4">
		<h3 class="d-flex justify-content-between">
			<span>${title}</span>
			
			<div>
				<a href="/home" class="btn btn-outline-primary">Home</a>
				
				<c:forEach items="${weekDays}" var="item">
					
					<c:url var="daySearchLink" value="/home/${title.month}">
						<c:param name="day" value="${item}"></c:param>
					</c:url>
				
					<a href="${daySearchLink}" class="btn ${param.day eq item ? 'btn-primary' : 'btn-outline-primary'}">${item}</a>
				</c:forEach>
			</div>
		</h3>
		
		<div class="row row-cols-4">
			<c:forEach items="${list}" var="item">
				<div class="col gy-3">
					<custom:simple-card item="${item}" />
				</div>
			</c:forEach>
		</div>
	</div>
	


</body>
</html>