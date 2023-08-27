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
		<h1>Member Sign Up</h1>
		
		<div class="w-25">
			
			<sf:form action="/signup" modelAttribute="form" method="post">
			
				<div class="mb-3">
					<label class="form-label">Name</label>
					<sf:input path="name" cssClass="form-control" placeholder="Enter Your Name"/>
					<sf:errors path="name" cssClass="text-secondary" />
				</div>

				<div class="mb-3">
					<label class="form-label">Email</label>
					<sf:input path="email" type="email" cssClass="form-control" placeholder="Enter Login Email"/>
					<sf:errors path="email" cssClass="text-secondary" />
				</div>
				
				<div class="mb-3">
					<label class="form-label">Password</label>
					<sf:input path="password" type="password" cssClass="form-control" placeholder="Enter Login Password"/>
					<sf:errors path="password" cssClass="text-secondary" />
				</div>
				
				<div>
					<button class="btn btn-primary">
						Sign Up
					</button>
					
					<a href="/signin" class="btn btn-outline-primary">
						Sign In
					</a>				
				</div>
			
			</sf:form>
			
		</div>
	
	</div>


</body>
</html>