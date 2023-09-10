document.addEventListener('DOMContentLoaded', () => {
	
	const signOutForm = document.getElementById('signOutForm')
	const signOutMenu = document.getElementById('signOutMenu')
	
	if(signOutForm && signOutMenu) {
		signOutMenu.addEventListener('click', () => signOutForm.submit())
	} 
})