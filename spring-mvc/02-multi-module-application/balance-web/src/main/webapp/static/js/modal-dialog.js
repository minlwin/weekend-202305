document.addEventListener('DOMContentLoaded', () => {
	const addNewBtn = document.getElementById('addNewBtn')
	const editLedgerDialog = document.getElementById('editLedgerDialog')
	
	if(addNewBtn && editLedgerDialog) {
		addNewBtn.addEventListener('click', () => {
			const dialog = new bootstrap.Modal(editLedgerDialog)
			dialog.show()
		})
	}
	
	const statusCheck = document.getElementById('statusCheck')
	const statusCheckLabel = document.getElementById('statusCheckLabel')
	
	if(statusCheck && statusCheckLabel) {
		statusCheck.addEventListener('change', (event) => {
			statusCheckLabel.innerText = event.target.checked ? 'Deleted' : 'Active'
		})
	}
})