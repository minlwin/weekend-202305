<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %> 
<%@ taglib prefix="app" tagdir="/WEB-INF/tags" %>   
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>    
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Sign Up</title>

<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-4bw+/aepP/YC94hEpVNVgiZdgIC5+VKNBQNGCHeKRQN+PtmoHDEXuppvnDJzQIu9" crossorigin="anonymous">
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/js/bootstrap.bundle.min.js" integrity="sha384-HwwvtgBNo3bZJJLYd8oVXjrBZt8cqVSpeBNS5n7C8IVInixGAoxmnlMuBnhbgrkm" crossorigin="anonymous"></script>

<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.10.5/font/bootstrap-icons.css">

<c:url value="/styles/application.css" var="appCss"></c:url>
<link rel="stylesheet" href="${appCss}" />

</head>
<body>

	<app:anonymous-layout subTitle="Member Sign Up" title="The Balances">

		<div class="text-start d-flex justify-content-center">
		
			<div class="main-feature pe-5">
				<i class="bi-person-plus"></i>
			</div>
			
			<c:url value="/signup" var="signUpAction"></c:url>
			<sf:form action="${signUpAction}" method="post" modelAttribute="form" cssClass="sign-form">
				<!-- Email -->
				<div class="mb-3">
					<label class="form-label">Member Name</label>
					<sf:input path="name" placeholder="Enter Your Name" cssClass="form-control"/>
					<sf:errors path="name" cssClass="text-secondary" />
				</div>

				<div class="mb-3">
					<label class="form-label">Email Address</label>
					<sf:input path="email" type="email" placeholder="Enter Email" cssClass="form-control"/>
					<sf:errors path="email" cssClass="text-secondary" />
				</div>
				
				<!-- Password -->
				<div class="mb-3">
					<label class="form-label">Password</label>
					<sf:input path="password" type="password" placeholder="Enter Password" cssClass="form-control"/>
					<sf:errors path="password" cssClass="text-secondary" />
				</div>
				
				<div>
					<!-- Login Button -->
					<button class="btn btn-outline-primary">
						<i class="bi bi-person-plus"></i> Sign Up
					</button>
					
					<!-- Sign Up Link -->
					<c:url value="/signin" var="signIn"></c:url>
					<a href="${signIn}" class="btn btn-outline-primary">
						<i class="bi bi-unlock"></i> Sign In
					</a>
				</div>
			</sf:form>
		</div>	
	</app:anonymous-layout>

</body>
</html>