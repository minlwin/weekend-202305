<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>        
<%@ taglib prefix="app" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>
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
			<div class="col">
				<!-- Item Information -->
				<c:url value="/member/transaction/${type}/edit/add-item" var="addItemAction"></c:url>
				<sf:form action="${addItemAction}" modelAttribute="itemForm" method="post" cssClass="card">
					<div class="card-header"><i class="bi-plus"></i> Add Item</div>
					<div class="card-body row">
						<div class="col-6">
							<label class="form-label">Item</label>
							<sf:input path="item" placeholder="Item Name" cssClass="form-control"/>
							<sf:errors path="item" cssClass=""></sf:errors>
						</div>
						
						<div class="col">
							<label class="form-label">Price</label>
							<sf:input path="unitPrice" type="number" cssClass="form-control"/>
							<sf:errors path="unitPrice" cssClass="text-secondary"></sf:errors>
						</div>
						
						<div class="col">
							<label class="form-label">Quantity</label>
							<sf:input path="quantity" type="number" cssClass="form-control"/>
							<sf:errors path="quantity" cssClass="text-secondary"></sf:errors>
						</div>
					</div>	
					
					<div class="card-footer text-end">
						<button class="btn btn-outline-secondary">
							<i class="bi-plus"></i> Add Item
						</button>
					</div>
				</sf:form>	
				
				<!-- Item List -->
				<div class="card mt-4">
					<div class="card-header"><i class="bi-list"></i> Item List</div>
					
					<table class="table table-striped card-body">
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
							<c:forEach var="item" varStatus="sts" items="${form.items}">
							<tr>
								<td>${sts.index + 1}</td>
								<td>${item.item}</td>
								<td class="text-end">${item.unitPrice}</td>
								<td class="text-end">${item.quantity}</td>
								<td class="text-end">${item.unitPrice * item.quantity}</td>
							</tr>							
							</c:forEach>
						</tbody>
					</table>
				
				</div>
			</div>
			
			
			<div class="col-4">
				<!-- Transaction Information -->
				<c:url value="/member/transaction/${type}/edit" var="saveAction"></c:url>
				<sf:form action="${saveAction}" modelAttribute="form" method="post" class="card">
					<div class="card-header">
						<i class="bi-file"></i> Transaction
					</div>
					
					<div class="card-body">
					
						<!-- All Total -->
						<div class="mb-3">
							<label class="form-label">Total Amount</label>
							<span class="form-control">${form.allTotal}</span>
						</div>
						
						<!-- Ledgers -->
						<div class="mb-3">
							<label class="form-label">Select Ledger</label>
							<sf:select path="ledgerId" cssClass="form-select">
								<sf:options items="${ledgers}" itemValue="id" itemLabel="name"/>
							</sf:select>
						</div>
						
						<!-- Issue At -->
						<div class="mb-3">
							<label class="form-label">Issue At</label>
							<sf:input path="issueAt" cssClass="form-control" type="date"/>
						</div>
						
						<!-- Remark -->
						<div>
							<label class="form-label">Remark</label>
							<sf:textarea path="remark" cssClass="form-control"/>
						</div>
					
					</div>
					<c:if test="${form.allTotal gt 0}">
						<div class="card-footer text-end">
							<button class="btn btn-outline-secondary">
								<i class="bi-save"></i> Save Transaction
							</button>
						</div>
					</c:if>
					
				</sf:form>
			
			</div>			
		</div>
	</div>

</body>
</html>