<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Security Demo</title>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-4bw+/aepP/YC94hEpVNVgiZdgIC5+VKNBQNGCHeKRQN+PtmoHDEXuppvnDJzQIu9" crossorigin="anonymous">
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/js/bootstrap.bundle.min.js" integrity="sha384-HwwvtgBNo3bZJJLYd8oVXjrBZt8cqVSpeBNS5n7C8IVInixGAoxmnlMuBnhbgrkm" crossorigin="anonymous"></script>
</head>
<body>

	<div class="container pt-4">
		<h1>Security Demo</h1>
		
		<div class="w-25">
			
			<sf:form action="/signin" method="post">
			
				<c:if test="${param.error ne null}">
					<div class="alert alert-info">
						Login Error
					</div>
				</c:if>	
				
				<div class="mb-3">
					<label class="form-label">Email</label>
					<input type="email" name="email" class="form-control" placeholder="Enter Login Email" />
				</div>
				
				<div class="mb-3">
					<label class="form-label">Password</label>
					<input type="password" name="password" class="form-control" placeholder="Enter Password" />
				</div>
				
				<div>
					<button class="btn btn-primary">
						Sign In
					</button>
					
					<a href="/signup" class="btn btn-outline-primary">
						Sign Up
					</a>
				</div>
			
			</sf:form>
			
		</div>
	
	</div>


</body>
</html>