"use strict";

//
// var openBartender = UIkit.modal('.bartender-class').show();
// var openClient = UIkit.modal('.client-class').show();

function checkPassword(form) {
	var passwordOne = form.password.value;
	var passwordTwo = form.verifyPassword.value;

	if (passwordOne == "") {
		UIkit.modal.alert("Please enter a password");
		event.preventDefault()
	} else if (passwordTwo == "") {
		UIkit.modal.alert("Please confirm password");
		event.preventDefault();
	} else if (passwordOne != passwordTwo) {
		UIkit.modal.alert("Passwords do not match");
		event.preventDefault();
	}
}

UIkit.util.on('#bartender-button', 'click', function (e) {
	;
	e.preventDefault();
});
UIkit.util.on('#client-button', 'click', function (e) {
	UIkit.show();
	e.preventDefault();
})


