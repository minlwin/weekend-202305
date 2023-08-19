<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>        
<%@ taglib prefix="app" tagdir="/WEB-INF/tags" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Balance | Admin</title>

<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-4bw+/aepP/YC94hEpVNVgiZdgIC5+VKNBQNGCHeKRQN+PtmoHDEXuppvnDJzQIu9" crossorigin="anonymous">
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/js/bootstrap.bundle.min.js" integrity="sha384-HwwvtgBNo3bZJJLYd8oVXjrBZt8cqVSpeBNS5n7C8IVInixGAoxmnlMuBnhbgrkm" crossorigin="anonymous"></script>

<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.10.5/font/bootstrap-icons.css">

<c:url value="/styles/application.css" var="commonCss"></c:url>
<link rel="stylesheet" href="${commonCss}" />

</head>
<body>

	<nav class="navbar navbar-expand navbar-dark bg-dark">
		<div class="container">
			<span class="navbar-brand">
				<i class="bi bi-arrow-down-up"></i> Balance Home
			</span>
			
			<ul class="navbar-nav">
				<li class="nav-item">
					<a href="/" class="nav-link">
						<i class="bi-lock"></i> Sign Out
					</a>
				</li>
			</ul>
		</div>
	</nav>
	
	<div class="container pt-4">
	
		<h3><i class="bi-people"></i> Member Management</h3>
	
		<!-- Search Form -->
		<form action="${searchAction}" class="row my-3">
		
			<div class="col-auto">
				<label class="form-label">Role</label>
				<select name="role" class="form-select">
					<option value="">All Role</option>
					<option value="Member">Member</option>
					<option value="Admin">Admin</option>
				</select>
			</div>
						
			<div class="col-auto">
				<label class="form-label">Date From</label>
				<input type="date" name="from" class="form-control" />
			</div>

			<div class="col-auto">
				<label class="form-label">Date To</label>
				<input type="date" name="to" class="form-control" />
			</div>
			
			<div class="col-auto">
				<label class="form-label">Name</label>
				<input type="text" name="name" placeholder="Search Name" class="form-control" />
			</div>
			
			<div class="col btn-wrapper">
				<button class="btn btn-outline-dark">
					<i class="bi-search"></i> Search
				</button>
			</div>
		</form>		

 		<!-- Result Table -->
 		<table class="table table-striped">
 			<thead>
 				<tr>
 					<th>Name</th>
 					<th>Role</th>
 					<th>Register At</th>
 					<th>Email</th>
 					<th>Phone</th>
 					<th>Status</th>
 				</tr>
 			</thead>
 			<tbody>
 				<tr>
 					<td>Aung Aung</td>
 					<td>Member</td>
 					<td>2023-06-01</td>
 					<td>aung@gmail.com</td>
 					<td>0923737448</td>
 					<td>Active</td>
 				</tr>
 			</tbody>
 		</table>
		
		<!-- Pagination -->
		<app:pagination></app:pagination>
	
	</div>

</body>
</html>