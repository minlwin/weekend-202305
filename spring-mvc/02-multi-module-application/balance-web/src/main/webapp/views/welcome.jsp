<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>   
<%@ taglib prefix="app" tagdir="/WEB-INF/tags" %>     
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Balance Home</title>

<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-4bw+/aepP/YC94hEpVNVgiZdgIC5+VKNBQNGCHeKRQN+PtmoHDEXuppvnDJzQIu9" crossorigin="anonymous">
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/js/bootstrap.bundle.min.js" integrity="sha384-HwwvtgBNo3bZJJLYd8oVXjrBZt8cqVSpeBNS5n7C8IVInixGAoxmnlMuBnhbgrkm" crossorigin="anonymous"></script>

<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.10.5/font/bootstrap-icons.css">

<c:url value="/styles/application.css" var="appCss"></c:url>
<link rel="stylesheet" href="${appCss}" />

</head>
<body>
	
	<app:anonymous-layout title="The Balances" subTitle="Personal Accounting System" >
	
		<!-- Main Features -->
		<section class="row main-feature">
			
			<!-- Register as Member -->
			<div class="col">
				<div>
					<i class="bi-person-plus"></i>
				</div>
				<h5>Member Registration</h5>
				<p>You can sign up and sign in to the system. And you can use balance management system.</p>
			</div>
			
			<!-- Create your daily Transactions -->
			<div class="col">
				<div>
					<i class="bi-pencil"></i>
				</div>
				<h5>Transaction Management</h5>
				<p>
					You can enter your daily transaction by separate ledgers. And also you can manage your credit and debit on Transaction Management Features.
				</p>
			</div>
			
			<!-- Check Balance Reports -->
			<div class="col">
				<div>
					<i class="bi-bar-chart"></i>
				</div>
				<h5>Balance Reports</h5>
				<p>
					Balance Reports feature support your transaction as a trial balance aspects. You can manage your transactions via monthly or yearly views.
				</p>
			</div>

		</section>
		
		<!-- Buttons -->
		<section class="main-controls mt-5">
			<c:url value="/signup" var="signUp"></c:url>
			<a href="${signUp}" class="btn btn-outline-primary"><i class="bi-person-plus"></i> Sign Up</a>

			<c:url value="/signin" var="signIn"></c:url>
			<a href="${signIn}" class="btn btn-outline-primary"><i class="bi-person-check"></i>Sign In</a>
		</section>
	
	</app:anonymous-layout>

</body>
</html>