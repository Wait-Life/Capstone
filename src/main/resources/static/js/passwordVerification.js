"use strict";

function CustomValidation() {
	this.invalidities = [];
	this.validityChecks = [];
}

CustomValidation.prototype = {
	addInvalidity: function (message) {
		this.invalidities.push(message);
	},
	getInvalidities: function () {
		return this.invalidities.join('.\n');
	},
	checkValidity: function (input) {
		for (var i = 0; i < this.validityChecks.length; i++) {
			var isInvalid = this.validityChecks[i].isInvalid(input);
			if (isInvalid) {
				this.addInvalidity(this.validityChecks[i].invalidityMessage);
			}

			var requireElement = this.validityChecks[i].element;
			if (requireElement) {
				if (isInvalid) {
					requireElement.classList.add('invalid');
					requireElement.classList.remove('valid');
				} else {
					requireElement.classList.remove('invalid');
					requireElement.classList.add('valid');
				}
			}
		}
	}
};

var passwordValidityChecks = [
	{
		isInvalid: function (input) {
			return input.value.length < 3;
		},
		invalidityMessage: 'Password needs to be at least 3 characters',
		element: document.querySelector('label[for="password"].input-requirements li:nth-child(1)')
	}
];

var repeatPasswordValidityCheck = [
	{
		isInvalid: function () {
			return verifyPassword.value != password.value;
		},
		invalidityMessage: 'The passwords do not match'
	}
];

function checkInput(input) {
	input.CustomValidation.invalidities = [];
	input.CustomValidation.checkValidity(input);

	if (input.CustomValidation.invalidities.length == 0 && input.value != "") {
		input.setCustomValidity('');
	} else {
		var message = input.CustomValidation.getInvalidities();
		input.setCustomValidity(message);
	}
};

var password = document.getElementById('password');
var verifyPassword = document.getElementById('verifyPassword');

passwordInput.CustomValidation = new CustomValidation();
passwordInput.CustomValidation.validityChecks = passwordValidityChecks;

verifyPasswordInput.CustomValidation = new CustomValidation();
verifyPasswordInput.CustomValidation.validityChecks = verifyPasswordChecks;

var inputs = document.querySelectorAll('input:not([type="submit"])');
var submit = document.querySelector('input[type="submit"]');

for (var i=0; i<inputs.length; i++) {
	inputs[i].addEventListener('keyup', function () {
		checkInput(this);
	});
}

submit.addEventListener('click', function () {
	for (var i=0; i<inputs.length; i++) {
		checkInput(inputs[i]);
	}
});


