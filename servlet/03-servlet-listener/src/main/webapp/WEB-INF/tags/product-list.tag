<%@ tag language="java" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<div class="row row-cols-3 gy-4">

	<c:forEach var="item" items="${products}">
		
		<div class="col">
			<div class="card">
				<div class="card-body d-flex justify-content-between">
					
					<div class="d-flex">
						<h4>${item.id}</h4>
						
						<div class="d-flex flex-column ms-3">
							<h4>${item.name}</h4>
							<span class="text-secondary">${item.price} MMK</span>
						</div>
					</div>
					
					<div>
						<c:url var="addToCart" value="/cart/add">
							<c:param name="id" value="${item.id}"></c:param>
						</c:url>
						<a href="${addToCart}" class="btn-link">
							<h4>
								<i class="bi bi-plus"></i>
							</h4>
						</a>
					</div>
				</div>
			</div>
		</div>
	
	</c:forEach>
	
</div>