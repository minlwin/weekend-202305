<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Scope Demo</title>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-9ndCyUaIbzAi2FUVXJi0CjmCapSmO7SnpJef0486qhLnuZ2cdeRhO02iuK6FUUVM" crossorigin="anonymous">
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js" integrity="sha384-geWF76RCwLtnZ8qwWowPQNguL3RmwHVBC9FhGdlKrxdiJJigb/j/68SIy3Te4Bkz" crossorigin="anonymous"></script>
</head>
<body>

	<div class="container mt-4">
		<h1>Scope Demo</h1>
		
		<div class="row">
			<div class="col-3">
				<table class="table table-striped">
					<tr>
						<td>Request Scope</td>
						<td>${requestScope.counter.count}</td>
					</tr>
					<tr>
						<td>Session Scope</td>
						<td>${sessionScope.counter.count}</td>
					</tr>
					<tr>
						<td>Application Scope</td>
						<td>${applicationScope.counter.count}</td>
					</tr>
				</table>
				
				<a href="/counter" class="btn btn-primary mt-4">Count Up</a>
			</div>
		</div>
	</div>

</body>
</html>