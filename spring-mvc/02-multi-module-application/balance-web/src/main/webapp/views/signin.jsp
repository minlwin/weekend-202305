<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %> 
<%@ taglib prefix="app" tagdir="/WEB-INF/tags" %>       
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Sign In</title>

<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-4bw+/aepP/YC94hEpVNVgiZdgIC5+VKNBQNGCHeKRQN+PtmoHDEXuppvnDJzQIu9" crossorigin="anonymous">
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/js/bootstrap.bundle.min.js" integrity="sha384-HwwvtgBNo3bZJJLYd8oVXjrBZt8cqVSpeBNS5n7C8IVInixGAoxmnlMuBnhbgrkm" crossorigin="anonymous"></script>

<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.10.5/font/bootstrap-icons.css">
<link rel="stylesheet" href="/styles/application.css" />

</head>
<body>

	
	<app:anonymous-layout subTitle="Member Sign In" title="The Balances">
		
		<!-- Login Form -->
		<div class="text-start d-flex justify-content-center">
		
			<div class="main-feature pe-5">
				<i class="bi-unlock"></i>
			</div>
		
			<form action="/member/home" class="sign-form">
				<!-- Email -->
				<div class="mb-3">
					<label class="form-label">Email Address</label>
					<input type="email" placeholder="Enter Email for Login" class="form-control" />
				</div>
				
				<!-- Password -->
				<div class="mb-3">
					<label class="form-label">Password</label>
					<input type="password" placeholder="Enter Password" class="form-control" />
				</div>
				
				<div>
					<!-- Login Button -->
					<button class="btn btn-outline-primary">
						<i class="bi bi-unlock"></i> Sign In
					</button>
					
					<!-- Sign Up Link -->
					<a href="/signup" class="btn btn-outline-primary">
						<i class="bi bi-person-plus"></i> Sign Up
					</a>
				</div>
			</form>
		</div>
	
	</app:anonymous-layout>
		
</body>
</html>