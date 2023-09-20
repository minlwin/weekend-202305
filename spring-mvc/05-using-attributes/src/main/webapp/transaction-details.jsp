<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Transaction</title>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN" crossorigin="anonymous">
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-C6RzsynM9kWDrMNeT87bh95OGNyZPhcTNXj1NW7RuBCsyN/o0jlpcV8Qyq46cDfL" crossorigin="anonymous"></script>
</head>
<body>

	<div class="container mt-4">
		<!-- Title -->
		<div class="d-flex justify-content-between align-items-center">
			<h1>ID : ${data.id} ${data.title}</h1>
			<div>
				<c:url value="/" var="home"></c:url>
				<a href="${home}" class="btn btn-outline-primary">All Transactions</a>
			</div>
		</div>
		
		<c:if test="${not empty message}">
			<div class="alert alert-info">${message}</div>
		</c:if>
		
		<!-- Items -->
		<table class="table table-striped">
			<thead>
				<tr>
					<th>No.</th>
					<th>Item</th>
					<th class="text-end">Unit Price</th>
					<th class="text-end">Quantity</th>
					<th class="text-end">Total</th>
				</tr>
			</thead>
			
			<tbody>
				<c:forEach var="item" varStatus="sts" items="${data.items}">
				<tr>
					<td>${sts.index + 1}</td>
					<td>${item.name}</td>
					<td class="text-end">${item.unitPrice}</td>
					<td class="text-end">${item.quantity}</td>
					<td class="text-end">${item.unitPrice * item.quantity}</td>
				</tr>
				</c:forEach>
			</tbody>
		</table>
		
	</div>

</body>
</html>