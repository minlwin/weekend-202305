<%@ tag language="java" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<nav class="navbar navbar-expand bg-light shadow-sm">
	
	<div class="container">
		<h4 class="navbar-brand">
			<i class="bi-pie-chart"></i> The Balances
		</h4>
		
		<ul class="navbar-nav">
			<li class="nav-item">
				<c:url value='/member/home' var="memberHome" />
				<a href="${memberHome}" class="nav-link ${pageTitle eq 'Home' ? 'active' : ''}">
					<i class="bi-house"></i> Home
				</a>
			</li>
			
			
			<!-- Credit Management -->
			<li class="nav-item">
				<c:url value="/member/transaction/Credit" var="creditTrans"></c:url>
				<a href="${creditTrans}" class="nav-link ${pageTitle eq 'Credit' ? 'active' : ''}">
					<i class="bi-arrow-up"></i> Credit
				</a>
			</li>
			
			<!-- Debit Management -->
			<li class="nav-item">
				<c:url value="/member/transaction/Debit" var="debitTrans"></c:url>
				<a href="${debitTrans}" class="nav-link ${pageTitle eq 'Debit' ? 'active' : ''}">
					<i class="bi-arrow-down"></i> Debit
				</a>
			</li>
			
			<!-- Balance Report -->
			<li class="nav-item">
				<c:url value="/member/balance" var="balance"></c:url>
				<a href="${balance}" class="nav-link ${pageTitle eq 'Balance' ? 'active' : ''}">
					<i class="bi-arrow-down-up"></i> Balances
				</a>
			</li>
			
			<!-- Ledger Management -->
			<li class="nav-item">
				<c:url value="/member/ledger" var="ledger"></c:url>
				<a href="${ledger}" class="nav-link ${pageTitle eq 'Ledger' ? 'active' : ''}">
					<i class="bi-tags"></i> Ledgers
				</a>
			</li>
			
			<li class="nav-item">
				<a id="signOutMenu" class="nav-link">
					<i class="bi-lock"></i> Sign Out
				</a>
			</li>
		</ul>
	</div>
	
	<c:url value="/signout" var="signOut"></c:url>
	<form id="signOutForm" action="${signOut}" method="post">
		<sec:csrfInput/>
	</form>

	<c:url value="/js/signout.js" var="script"></c:url>
	<script type="text/javascript" src="${script}"></script>
	
</nav>