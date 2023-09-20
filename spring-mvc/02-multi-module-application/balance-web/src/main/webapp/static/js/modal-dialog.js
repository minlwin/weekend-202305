document.addEventListener('DOMContentLoaded', () => {
	const addNewBtn = document.getElementById('addNewBtn')
	const editLedgerDialog = document.getElementById('editLedgerDialog')
	
	if(addNewBtn && editLedgerDialog) {
		addNewBtn.addEventListener('click', () => {
			const dialog = new bootstrap.Modal(editLedgerDialog)
			dialog.show()
		})
	}
	
	if(editLedgerDialog.dataset.error) {
		const dialog = new bootstrap.Modal(editLedgerDialog)
		dialog.show()
	}
	
	const statusCheck = document.getElementById('statusCheck')
	const statusCheckLabel = document.getElementById('statusCheckLabel')
	
	if(statusCheck && statusCheckLabel) {
		statusCheck.addEventListener('change', (event) => {
			statusCheckLabel.innerText = event.target.checked ? 'Deleted' : 'Active'
		})
	}
	
	const editButtons = Array.from(document.getElementsByClassName('editBtn'))
	editButtons.forEach(btn => btn.addEventListener('click', () => {
		document.getElementById('ledgerId').value = btn.dataset.id
		document.getElementById('ledgerName').value = btn.dataset.name
		document.getElementById('ledgerType').value = btn.dataset.type
		
		const dialog = new bootstrap.Modal(editLedgerDialog)
		dialog.show()
	}))
})