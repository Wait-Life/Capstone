"use strict";

window.fbAsyncInit = function () {
	FB.init({
		appId: fBKey,
		autoLogAppEvents: true,
		xfbml: true,
		version: 'v4.0'
	});
	FB.login(function (response) {
		if (response.authResponse) {
			console.log("Welcome! Fetching your information...");
			FB.api('/me', function (response) {
				console.log("Great to see you, " + response.name + ".")
			});
		} else {
			console.log("Login cancelled by user or not fully authorized.")
		}
	});

};