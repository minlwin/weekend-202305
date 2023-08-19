<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>        
<%@ taglib prefix="app" tagdir="/WEB-INF/tags" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Member | ${pageTitle}</title>

<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-4bw+/aepP/YC94hEpVNVgiZdgIC5+VKNBQNGCHeKRQN+PtmoHDEXuppvnDJzQIu9" crossorigin="anonymous">
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/js/bootstrap.bundle.min.js" integrity="sha384-HwwvtgBNo3bZJJLYd8oVXjrBZt8cqVSpeBNS5n7C8IVInixGAoxmnlMuBnhbgrkm" crossorigin="anonymous"></script>

<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.10.5/font/bootstrap-icons.css">

<c:url value="/styles/application.css" var="commonCss"></c:url>
<link rel="stylesheet" href="${commonCss}" />
</head>
<body>

	<!-- Navigation Bar -->
	<app:member-menu />

	<div class="container pt-4">
		<h3>
			<i class="${pageTitle eq 'Credit' ? 'bi-arrow-up' : 'bi-arrow-down'}"></i> ${pageTitle} Management
		</h3>
		
		<!-- Search Form -->
		<c:url value="/member/transaction/${pageTitle}" var="searchAction"></c:url>
		<form action="${searchAction}" class="row my-3">
						
			<div class="col-auto">
				<label class="form-label">Ledger Name</label>
				<input type="text" name="ledgerName" class="form-control" placeholder="Search Name" />
			</div>

			<div class="col-auto">
				<label class="form-label">Date From</label>
				<input type="date" name="from" class="form-control" />
			</div>

			<div class="col-auto">
				<label class="form-label">Date To</label>
				<input type="date" name="to" class="form-control" />
			</div>
			
			<div class="col btn-wrapper">
				<button class="btn btn-outline-dark">
					<i class="bi-search"></i> Search
				</button>
				
				<c:url value="/member/transaction/${pageTitle}/edit" var="addNew"></c:url>
				<a href="${addNew}" class="btn btn-dark">
					<i class="bi-plus"></i> Add New
				</a>
			</div>
		</form>
		
		<!-- Result Table -->
		<table class="table table-strpied">
			<thead>
				<tr>
					<th>Ledger</th>
					<th>Issue Date</th>
					<th>References</th>
					<th class="text-end">Items</th>
					<th class="text-end">Amount</th>
					<th class="text-center"></th>
				</tr>
			</thead>
			
			<tbody>
				<tr>
					<td>Some Ledger</td>
					<td>2028-10-10</td>
					<td>Any Other Remarks</td>
					<td class="text-end">5</td>
					<td class="text-end">120,000</td>
					<td class="text-center">
						<c:url value="/member/transaction/${pageTitle}/1" var="detailsLink" />
						<a href="${detailsLink}" class="btn-link">
							<i class="bi-send"></i>
						</a>
					</td>
				</tr>
			</tbody>
		</table>
		
		<app:pagination></app:pagination>
	</div>
</body>
</html>