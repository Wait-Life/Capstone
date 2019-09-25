"use strict";

function checkPassword(form) {
	var passwordOne = form.password.value;
	var passwordTwo = form.verifyPassword.value;

	if (passwordOne == "") {
		alert("Please enter a password");
	} else if (passwordTwo == "") {
		alert("Please confirm password");
	} else if (passwordOne != passwordTwo) {
		alert("Passwords do not match");
		return false;
	} else {
		alert("passwords match!")
		return true;
	}
}


