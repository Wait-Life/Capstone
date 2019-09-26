"use strict";

function checkPassword(form) {
	var passwordOne = form.password.value;
	var passwordTwo = form.verifyPassword.value;

	if (passwordOne == "") {
		alert("Please enter a password");
		event.preventDefault()
	} else if (passwordTwo == "") {
		alert("Please confirm password");
		event.preventDefault();
	} else if (passwordOne != passwordTwo) {
		alert("Passwords do not match");
		event.preventDefault();
	}
}


