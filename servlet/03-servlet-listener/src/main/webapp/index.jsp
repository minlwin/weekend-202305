<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>    
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Product List</title>

<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-4bw+/aepP/YC94hEpVNVgiZdgIC5+VKNBQNGCHeKRQN+PtmoHDEXuppvnDJzQIu9" crossorigin="anonymous">
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/js/bootstrap.bundle.min.js" integrity="sha384-HwwvtgBNo3bZJJLYd8oVXjrBZt8cqVSpeBNS5n7C8IVInixGAoxmnlMuBnhbgrkm" crossorigin="anonymous"></script>
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.10.5/font/bootstrap-icons.css">
</head>
<body>

	<div class="container mt-4">
		
		<h1>Products</h1>
		
		<div class="row row-cols-4 g-4 mt-2">
		
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
								<a href="#" class="btn-link">
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
	
	</div>

</body>
</html>