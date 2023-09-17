<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>  
<%@ taglib prefix="fmt" uri="jakarta.tags.fmt" %>      
<%@ taglib prefix="app" tagdir="/WEB-INF/tags" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Member | ${pageTitle}</title>

<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-4bw+/aepP/YC94hEpVNVgiZdgIC5+VKNBQNGCHeKRQN+PtmoHDEXuppvnDJzQIu9" crossorigin="anonymous">
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/js/bootstrap.bundle.min.js" integrity="sha384-HwwvtgBNo3bZJJLYd8oVXjrBZt8cqVSpeBNS5n7C8IVInixGAoxmnlMuBnhbgrkm" crossorigin="anonymous"></script>

<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.10.5/font/bootstrap-icons.css">

</head>
<body>

	<!-- Navigation Bar -->
	<app:member-menu />
	
	<div class="container mt-4">
		<div class="row">
			
			<div class="col-8">
			
				<div class="card">
					<div class="card-header">
						<i class="bi-list"></i> Transaction Items
					</div>
					
					<table class="card-body table table-striped">
						<thead>
							<tr>
								<th>No.</th>
								<th>Item</th>
								<th class="text-end">Price</th>
								<th class="text-end">Quantity</th>
								<th class="text-end">Total</th>
							</tr>
						</thead>
						
						<tbody>
							<c:forEach var="item" varStatus="sts" items="${data.items()}">
								<tr>
									<td>${sts.index + 1}</td>
									<td>${item.item()}</td>
									<td class="text-end">
										<fmt:formatNumber value="${item.unitPrice()}" />
									</td>
									<td class="text-end">
										<fmt:formatNumber value="${item.quantity()}" />
									</td>
									<td class="text-end">
										<fmt:formatNumber value="${item.unitPrice() * item.quantity()}" />
									</td>
								</tr>
							</c:forEach>
						</tbody>
					</table>
				</div>
			</div>

			<div class="col">
				<div class="card">
					<div class="card-header">
						<i class="bi-file"></i> ${type} Transaction
					</div>
					
					<div class="card-body">
						<!-- Ledger -->
						<div class="mb-3">
							<label class="form-label">Ledger</label>
							<span class="form-control">${data.ledgerName}</span>
						</div>
						
						<!-- Remark -->
						<div class="mb-3">
							<label class="form-label">Remark</label>
							<span class="form-control">${data.remark}</span>
						</div>

						<!-- Issue At -->
						<div class="mb-3">
							<label class="form-label">Issue At</label>
							<span class="form-control">${data.issueAt}</span>
						</div>
						
						<!-- Amount -->
						<div class="mb-3">
							<label class="form-label">Total Amount</label>
							<span class="form-control">
								<fmt:formatNumber value="${data.allTotal}" />
							</span>
						</div>
						
					</div>
				</div>			
			</div>			
		</div>
	</div>

</body>
</html>