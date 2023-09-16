<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>        
<%@ taglib prefix="app" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
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
	
	<div class="container pt-4">
		<!-- Title -->
		<h3 class="d-flex justify-content-between">
			<span>
				<i class="bi-tags"></i> Ledger Management
			</span>
			
			<!-- Controls -->
			<div>
				<!-- Add New -->
				<a id="addNewBtn" class="btn btn-outline-dark">
					<i class="bi bi-plus"></i> Add New
				</a>
				
				<!-- Credit -->
				<c:url value="/member/ledger" var="creditLink">
					<c:param name="type" value="Credit"></c:param>
				</c:url>
				<a href="${creditLink}" class="btn ${ active eq 'Credit' ? 'btn-dark' : 'btn-outline-dark' }">
					<i class="bi bi-arrow-up"></i> Credit
				</a>
				
				<!-- Debit -->
				<c:url value="/member/ledger" var="debitLink">
					<c:param name="type" value="Debit"></c:param>
				</c:url>
				<a href="${debitLink}" class="btn ${ active eq 'Debit' ? 'btn-dark' : 'btn-outline-dark' }">
					<i class="bi bi-arrow-down"></i> Debit
				</a>
				
				<!-- All Ledgers -->
				<c:url value="/member/ledger" var="allLedgers"></c:url>
				<a href="${allLedgers}" class="btn ${ active eq 'All' ? 'btn-dark' : 'btn-outline-dark' }">
					<i class="bi bi-arrow-down-up"></i> All Ledgers
				</a>
			
			</div>
		</h3>
		
		<!-- Data Table -->
		<c:choose>
			<c:when test="${not empty list}">
			<table class="table table-striped">
				<thead>
					<tr>
						<th>Id</th>
						<th>Type</th>
						<th>Ledger Name</th>
						<th class="text-end">Transactions</th>
						<th class="text-end">Amount</th>
						<th></th>
					</tr>
				</thead>
				
				<tbody>
					<c:forEach var="item" items="${list}">
					<tr>
						<td>${item.id()}</td>
						<td>${item.type()}</td>
						<td>${item.name()}</td>
						<td class="text-end">${item.transactionCount()}</td>
						<td class="text-end">${item.transactionAmount()}</td>
						<td class="text-center">
							<a href="#" data-id="${item.id()}" data-name="${item.name()}" data-type="${item.type()}" class="btn-link editBtn">
								<i class="bi-pencil"></i>
							</a>
						</td>
					</tr>
					</c:forEach>
				</tbody>
			</table>
			</c:when>
			
			<c:otherwise>
				<div class="alert alert-info">There is no category.</div>
			</c:otherwise>
		</c:choose>

	
	</div>
	
	<!-- Edit Dialog -->
	<div class="modal" id="editLedgerDialog" data-error="${errors}" tabindex="-1">
		<c:url value="/member/ledger" var="saveAction"></c:url>
		<sf:form method="post" id="ledgerEditForm" modelAttribute="form" action="${saveAction}" class="modal-dialog">
		
			<sec:authentication var="username" property="name" />
			<input type="hidden" name="username" value="${username}" />
			
			<input type="hidden" id="ledgerId" name="id" />
			<input type="hidden" name="searchType" value="${param.type}" />
			
			<div class="modal-content">
				<div class="modal-header">
					<h5><i class="bi-pencil"></i> Edit Ledger</h5>
				</div>
				
				<div class="modal-body">
					<!-- Type Select -->
					<div class="mb-3">
						<label class="form-label">Ledger Type</label>
						<sf:select id="ledgerType" path="type" cssClass="form-select">
							<option value="">Select One</option>
							<option value="Credit" ${param.type eq 'Credit' ? 'selected' : ''}>Credit</option>
							<option value="Debit" ${param.type eq 'Debit' ? 'selected' : ''}>Debit</option>
						</sf:select>
						<sf:errors path="type" cssClass="text-secondary"></sf:errors>
					</div>
					
					<!-- Name Input -->
					<div class="mb-3">
						<label class="form-label">Ledger Name</label>
						<sf:input id="ledgerName" path="name" placeholder="Enter Ledger Name" cssClass="form-control" />
						<sf:errors path="name" cssClass="text-secondary"></sf:errors>
					</div>
					
					<!-- Status Check Box -->
					<div class="form-check">
						<sf:checkbox path="deleted" cssClass="form-check-input" id="statusCheck" />
						<label id="statusCheckLabel" for="statusCheck" class="form-check-label">Active</label>
					</div>
				</div>
				
				<div class="modal-footer">
					<button class="btn btn-outline-primary"><i class="bi-save"></i> Save</button>
				</div>
			</div>		
		</sf:form>
	</div>
	
	<c:url value="/js/modal-dialog.js" var="modalScript" />
	<script src="${modalScript}"></script>

</body>
</html>