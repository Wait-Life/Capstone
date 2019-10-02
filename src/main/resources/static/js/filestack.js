"use strict";

// Set up the picker
const client = filestack.init(fsKey);
const options = {
    onUploadDone: (res) =>
        document.getElementById("photo").value = res.filesUploaded[0].url,
    maxSize: 10 * 1024 * 1024,
    accept: 'image/*',
    uploadInBackground: false,
    }:
const picker = client.picker(options);


// Get references to the DOM elements
const fileInput = document.getElementById('fileupload');
const btn = document.getElementById('picker');
const nameBox = document.getElementById('nameBox');


// Add our event listeners
btn.addEventListener('click', function (e) {
    e.preventDefault();
    picker.open();
});


// Helper to overwrite the field input value
function updateForm (result) {
    const fileData = result.filesUploaded[0];
    fileInput.value = fileData.url;


    // Some ugly DOM code to show some data.
    const name = document.createTextNode('Selected: ' + fileData.filename);
    nameBox.appendChild(name);
}