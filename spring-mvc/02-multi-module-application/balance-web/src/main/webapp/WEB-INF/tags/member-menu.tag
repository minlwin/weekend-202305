<%@ tag language="java" pageEncoding="ISO-8859-1"%>

<nav class="navbar navbar-expand bg-light shadow-sm">
	
	<div class="container">
		<h4 class="navbar-brand">
			<i class="bi-pie-chart"></i> The Balances
		</h4>
		
		<ul class="navbar-nav">
			<li class="nav-item">
				<a href="/member/home" class="nav-link ${pageTitle eq 'Home' ? 'active' : ''}">
					<i class="bi-house"></i> Home
				</a>
			</li>
			
			
			<!-- Credit Management -->
			<li class="nav-item">
				<a href="/member/transaction/Credit" class="nav-link ${pageTitle eq 'Credit' ? 'active' : ''}">
					<i class="bi-arrow-up"></i> Credit
				</a>
			</li>
			
			<!-- Debit Management -->
			<li class="nav-item">
				<a href="/member/transaction/Debit" class="nav-link ${pageTitle eq 'Debit' ? 'active' : ''}">
					<i class="bi-arrow-down"></i> Debit
				</a>
			</li>
			
			<!-- Balance Report -->
			<li class="nav-item">
				<a href="/member/balance" class="nav-link ${pageTitle eq 'Balance' ? 'active' : ''}">
					<i class="bi-arrow-down-up"></i> Balances
				</a>
			</li>
			
			<!-- Ledger Management -->
			<li class="nav-item">
				<a href="/member/ledger" class="nav-link ${pageTitle eq 'Ledger' ? 'active' : ''}">
					<i class="bi-tags"></i> Ledgers
				</a>
			</li>
			
			<li class="nav-item">
				<a href="/" class="nav-link">
					<i class="bi-lock"></i> Sign Out
				</a>
			</li>
		</ul>
	</div>
</nav>