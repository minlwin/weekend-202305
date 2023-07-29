<%@ tag language="java" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<div class="card bg-primary text-white">
	<div class="card-body">
		<h4 class="card-title d-flex justify-content-between">
			<span>
				<i class="bi bi-cart"></i> My Cart
			</span>
			
			<span>
				${cart.itemCount}
			</span>
		</h4>
		<hr />
		<c:if test="${cart.itemCount gt 0}">
			<!-- Cart Items -->
			<c:forEach var="item" items="${cart.items}">
				<div class="d-flex flex-column mb-3">
					<span>${item.product.name}</span>
					<div class="d-flex justify-content-between">
						<span> 
							${item.product.price} × ${item.quantity}
						</span>
						<span>${item.total}</span>
					</div>
				</div>
			</c:forEach>
			
			
			<!-- Total -->
			<hr />
			<div class="text-end">
				<span>${cart.total}</span>		
			</div>
			
			<!-- Buttons -->
			<div class="mt-4">
				<a href="/cart/clear" class="btn btn-outline-info d-block w-100">
					Clear Cart
				</a>
			</div>
		</c:if>
	</div>
</div>