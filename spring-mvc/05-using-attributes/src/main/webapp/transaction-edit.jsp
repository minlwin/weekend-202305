<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>    
<%@ taglib prefix="s" uri="http://www.springframework.org/tags/form" %>    
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Edit Transaction</title>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN" crossorigin="anonymous">
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-C6RzsynM9kWDrMNeT87bh95OGNyZPhcTNXj1NW7RuBCsyN/o0jlpcV8Qyq46cDfL" crossorigin="anonymous"></script>
</head>
<body>

	<div class="container mt-4">
		<h1>Edit Transaction</h1>
		
		<div class="mt-4">
			<div class="row">
				<!-- Item Form -->
				<div class="col-3">
					<div class="card">
						<div class="card-header">
							<h4>Add Item</h4>
						</div>
						
						<c:url value="/transaction/edit/add-item" var="addItemAction"></c:url>
						<s:form action="${addItemAction}" modelAttribute="itemForm" cssClass="card-body" method="post">
							<div class="mb-3">
								<label class="form-label">Item Name</label>
								<s:input path="itemName" cssClass="form-control" placeholder="Enter Item Name" />
							</div>
							
							<div class="mb-3">
								<label class="form-label">Unit Price</label>
								<s:input path="unitPrice" class="form-control" type="number"></s:input>
							</div>

							<div class="mb-3">
								<label class="form-label">Quantity</label>
								<s:input path="quantity" class="form-control" type="number"></s:input>
							</div>
							
							<button class="btn btn-outline-primary">Add Item</button>
						</s:form>
					</div>
				</div>
				
				<!-- Item Table -->
				<div class="col">
					<div class="card">
						<div class="card-header">
							<h4>Items</h4>
						</div>
						
						<table class="card-body table table-striped">
							<thead>
								<tr>
									<th>Name</th>
									<th>Unit Price</th>
									<th>Quantity</th>
									<th>Total</th>
								</tr>
							</thead>
							
							<tbody>
								<c:forEach var="item" items="${transactionForm.items}">
									<tr>
										<td>${item.itemName}</td>
										<td>${item.unitPrice}</td>
										<td>${item.quantity}</td>
										<td>${item.total}</td>
									</tr>
								</c:forEach>
							</tbody>
						</table>
					</div>
				
				</div>
				
				<!-- Transaction Form -->
				<div class="col-3">
					<div class="card">
						<div class="card-header">
							<h4>Transaction</h4>
						</div>
						
						<s:form modelAttribute="transactionForm" method="post" cssClass="card-body">
							<div class="mb-3">
								<label class="form-label">Total Amount</label>
								<span class="form-control">${transactionForm.allTotal}</span>
							</div>
							
							<div class="mb-3">
								<label class="form-label">Remark</label>
								<s:textarea path="title" cssClass="form-control"/>
							</div>
							
							<button class="btn btn-outline-primary">Save Transaction</button>
						</s:form>
					</div>
				</div>
			</div>
		</div>
	</div>

</body>
</html>