<%@ tag language="java" pageEncoding="UTF-8"%>

<div class="row">
	<div class="col-auto d-flex">
	
		<!-- Page Select -->
		<select id="pageSelect" class="form-select me-2">
			<option value="10">10</option>
			<option value="25">25</option>
			<option value="50">50</option>
		</select>
		
		<!-- Top -->
		<a href="#" class="btn btn-outline-secondary me-1">
			<i class="bi-skip-start"></i>
		</a>
		
		<!-- Previous -->
		<a href="#" class="btn btn-outline-secondary me-1">
			<i class="bi-skip-backward"></i>
		</a>
		
		<!-- Pages -->
		<a href="#" class="btn btn-dark me-1">
			1
		</a>
		
		<!-- Next -->
		<a href="#" class="btn btn-outline-secondary me-1">
			<i class="bi-skip-end"></i>
		</a>
		
		
		<!-- Last -->
		<a href="#" class="btn btn-outline-secondary">
			<i class="bi-skip-forward"></i>
		</a>
		
	</div>
	
	<div class="col row gx-2">
	
		<!-- Total Page -->
		<div class="col-auto">
			<div class="input-group">
				<span class="form-control">Pages</span>
				<span class="input-group-text">
					9
				</span>
			</div>
		</div>
		
		<!-- Total Count -->
		<div class="col-auto">
			<div class="input-group">
				<span class="form-control">Records</span>
				<span class="input-group-text">
					89
				</span>
			</div>
		</div>
	</div>
</div>