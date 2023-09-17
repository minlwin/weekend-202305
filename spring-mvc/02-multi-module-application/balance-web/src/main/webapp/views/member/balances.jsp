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
			<i class="bi-arrow-down-up"></i> Balance Report
		</h3>

		<c:url value="/member/balance" var="searchAction"></c:url>
		
		<div class="d-flex justify-content-between my-3">
			<form action="${searchAction}" class="row">
							
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
				</div>
			</form>
			
			<div class="row gx-2">
				<div class="col-auto btn-wrapper">
					<div class="input-group">
						<labe class="form-control">Start</labe>
						<span class="input-group-text">123,112</span>
					</div>	
				</div>
				
				<div class="col-auto btn-wrapper">
					<div class="input-group">
						<labe class="form-control">Last</labe>
						<span class="input-group-text">123,112</span>
					</div>	
				</div>
			</div>
		
		</div>
		
		<table class="table table-strpied">
			<thead>
				<tr>
					<th>Issue At</th>
					<th>Type</th>
					<th>Ledger</th>
					<th>Remark</th>
					<th class="text-end">Credit</th>
					<th class="text-end">Debit</th>
					<th class="text-end">Balance</th>
					<th></th>
				</tr>
			</thead>
			
			<tbody>
				<c:forEach var="item" items="${list}">
				<tr>
					<td>${item.issueAt}</td>
					<td>${item.type}</td>
					<td>${item.ledgerName}</td>
					<td>${item.remark}</td>
					<td class="text-end">${item.credit}</td>
					<td class="text-end">${item.debit}</td>
					<td class="text-end">${item.balance()}</td>
					<td class="text-center">
						<c:url value="/member/transaction/${item.type}/${item.id}" var="detailsLink" />
						<a href="${detailsLink}" class="btn-link">
							<i class="bi-send"></i>
						</a>
					</td>
				</tr>
				</c:forEach>
			</tbody>
		</table>
		
		<app:pagination></app:pagination>

	</div>

</body>
</html>