<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Transactions</title>

<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN" crossorigin="anonymous">
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-C6RzsynM9kWDrMNeT87bh95OGNyZPhcTNXj1NW7RuBCsyN/o0jlpcV8Qyq46cDfL" crossorigin="anonymous"></script>

</head>
<body>

	<div class="container mt-4">
		
		<div class="d-flex justify-content-between align-items-center">
			<h1>Using Session Attributes</h1>
			
			<c:url value="/transaction/edit" var="editLink"></c:url>
			<a href="${editLink}" class="btn btn-outline-primary">
				Add New
			</a>
		</div>
		
		<div class="mt-4">
			
			<c:choose>
				
				<c:when test="${not empty list}">
					
					<table class="table table-striped">
						<thead>
							<tr>
								<th>ID</th>
								<th>Title</th>
								<th class="text-end">Items</th>
								<th class="text-end">All Total</th>
							</tr>
						</thead>
						
						<tbody>
							<c:forEach var="item" items="${list}">
								<tr>
									<td>${item.id}</td>
									<td>
										<c:url value="/transactions/${item.id}" var="details"></c:url>
										<a href="${details}">${item.title}</a>
									</td>
									<td class="text-end">${item.items.size()}</td>
									<td class="text-end">${item.allTotal}</td>
								</tr>
							</c:forEach>
						</tbody>
					</table>
				
				</c:when>
				
				<c:otherwise>
					<div class="alert alert-info">There is no transaction data.</div>
				</c:otherwise>
			
			</c:choose>
		
		</div>
	
	</div>

</body>
</html>