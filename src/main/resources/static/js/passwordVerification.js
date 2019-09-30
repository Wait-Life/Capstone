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

var nameValidityChecks = [
	{
		isInvalid: function (input) {
			return input.value.length < 3;
		},
		invalidityMessage: 'Name needs to be at least 3 characters',
		element: document.querySelector('label[for="username"].input-requirements li:nth-child(1)')
	}
];

var emailValidityChecks = [
	{
		isInvalid: function (input) {
			return !input.value.match(/[.\@]/g);
		},
		invalidityMessage: 'Email must be in email address format',
		element: document.querySelector('label[for="email"].input-requirements li:nth-child(1)')
	}
];

var phoneNumValidityChecks = [
	{
		isInvalid: function (input) {
			return input.value.length < 10 || input.value.length > 10;
		},
		invalidityMessage: 'Phone number must be exactly 10 digits long',
		element: document.querySelector('label[for="phoneNum"].input-requirements li:nth-child(2)')
	},
	{
		isInvalid: function (input) {
			return input.value.match(/[a-z]/g);
		},
		invalidityMessage: 'Phone number must only contain digits.',
		element: document.querySelector('label[for="phoneNum"].input-requirements li:nth-child(1)')
	}
]

var tabcValidityChecks = [
	{
		isInvalid: function (input) {
			return input.value.match(/[a-z]/g);
		},
		invalidityMessage: 'TABC must only contain digits.',
		element: document.querySelector('label[for="tabc"].input-requirements li:nth-child(1)')
	}
]

var foodHandlersValidityChecks = [
	{
		isInvalid: function (input) {
			return input.value.match(/[a-z]/g);
		},
		invalidityMessage: 'Food Handlers must only contain digits.',
		element: document.querySelector('label[for="foodhandler"].input-requirements li:nth-child(1)')
	}
]

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

var nameInput = document.getElementById('username');
var emailInput = document.getElementById('email');
var phoneNumInput = document.getElementById('phoneNum');
var tabcInput = document.getElementById('tabc');
var foodHandlersInput = document.getElementById('foodhandler');
var passwordInput = document.getElementById('password');
var verifyPasswordInput = document.getElementById('verifyPassword');

nameInput.CustomValidation = new CustomValidation();
nameInput.CustomValidation.validityChecks = nameValidityChecks;

emailInput.CustomValidation = new CustomValidation();
emailInput.CustomValidation.validityChecks = emailValidityChecks;

phoneNumInput.CustomValidation = new CustomValidation();
phoneNumInput.CustomValidation.validityChecks = phoneNumValidityChecks;

tabcInput.CustomValidation = new CustomValidation();
tabcInput.CustomValidation.validityChecks = tabcValidityChecks;

foodHandlersInput.CustomValidation = new CustomValidation();
foodHandlersInput.CustomValidation.validityChecks = foodHandlersValidityChecks;

passwordInput.CustomValidation = new CustomValidation();
passwordInput.CustomValidation.validityChecks = passwordValidityChecks;

verifyPasswordInput.CustomValidation = new CustomValidation();
verifyPasswordInput.CustomValidation.validityChecks = repeatPasswordValidityCheck;

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


