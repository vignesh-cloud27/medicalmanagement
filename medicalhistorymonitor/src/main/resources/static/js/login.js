
console.clear();

//Elements to get login and signup titles
const loginHdr = document.getElementById('login');
const signupHdr = document.getElementById('signup');

//Elements to get login and signup buttons
const signupBtn = document.getElementById('btnsignup');
const loginBtn = document.getElementById('btnlogin');

//To store sign up form details
let signupContainer = getElementsByXPath("//div[@class='signup']//input");
let signupDictionary = new Object();

//To validate field with regular expression
let regex = "";

//Boolean flag to verify the input field has value
let isValid = true;

let loginContainer = getElementsByXPath("//div[@class='center']//input");
let loginDictionary = new Object();

//Function to load sign up image
$(document).ready(function(){                
	const imgbg = document.getElementsByClassName("form-structor")[0].style.backgroundImage = "url(/img/stethoscope-blue-background-cardiology-concept_711966-751.avif)";                
});

//Function to find element by Xpath
function getElementByXpath(path) {
	return document.evaluate(path, document, null, XPathResult.FIRST_ORDERED_NODE_TYPE, null).singleNodeValue;
}

//Function to find elements by Xpath
function getElementsByXPath(xpath, parent) {
	let results = [];
	let query = document.evaluate(xpath, parent || document,
		null, XPathResult.ORDERED_NODE_SNAPSHOT_TYPE, null);
	for (let i = 0, length = query.snapshotLength; i < length; ++i) {
		results.push(query.snapshotItem(i));
	}
	return results;
}

//Click event to slide and show login form  
loginHdr.addEventListener('click', (e) => {
	let parent = e.target.parentNode.parentNode;
	if (parent.className != 'login') {
		Array.from(e.target.parentNode.parentNode.classList).find((element) => {
			if (element !== "slide-up") {
				parent.classList.add('slide-up')
			} else {
				signupHdr.parentNode.classList.add('slide-up')
				parent.classList.remove('slide-up')
				document.getElementById("sign-message").textContent = "";
				signupContainer.forEach(function (input) {
					input.nextElementSibling.textContent = "";
					input.nextElementSibling.style.display = "none";

				});
			}
		});
	}
});

//Click event to slide and show signup form
signupHdr.addEventListener('click', (e) => {
	let parent = e.target.parentNode;
	if (parent.className != 'signup') {
		Array.from(e.target.parentNode.classList).find((element) => {
			if (element !== "slide-up") {
				parent.classList.add('slide-up')
			} else {
				loginHdr.parentNode.parentNode.classList.add('slide-up')
				parent.classList.remove('slide-up')
			}
		});
	}
});

//Sign Up button click event
signupBtn.addEventListener('click', function () {

	SignUpUser();

});

//Login button click event
loginBtn.addEventListener('click', function () {
	LoginUser();

});

//Enter button click event
document.addEventListener("keyup", function (event) {
	if (event.key === 'Enter') {
		if (event.target.parentNode.parentNode.className.toString() == "signup") {
			SignUpUser();
		}
	}
});

//Function to sign up user
function SignUpUser() {
	signupContainer.forEach(function (input) {
		switch (input.placeholder) {
			case 'Name':
				regex = new RegExp('^[a-zA-Z ]{5,29}$');
				if (input.value === "" || !regex.test(input.value)) {
					input.nextElementSibling.style.display = "";
					input.nextElementSibling.textContent = "Please enter a valid Name.";
					isValid = false;
				}
				break;
			case 'Phone':
				regex = new RegExp('^(0|91)?[6-9][0-9]{9}$');
				if (input.value === "" || !regex.test(input.value)) {
					input.nextElementSibling.style.display = "";
					input.nextElementSibling.textContent = "Please enter a valid Phone Number.";
					isValid = false;
				}
				break;
			case 'Password':
				regex = new RegExp('^(?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[^a-zA-Z0-9])(?!.*\\s).{8,15}$');

				if (input.value === "" || !regex.test(input.value)) {
					input.nextElementSibling.style.display = "";
					input.nextElementSibling.textContent = "Password needed atleast 8 char, 1 symbol, upper & lower case letter and number.";
					isValid = false;
				}
		}
		if (isValid) {
			input.nextElementSibling.textContent = "";
			signupDictionary[input.name] = input.value;
		}

	});
	if (Object.keys(signupDictionary).length == 3) {
		$.ajax({
			type: "POST",
			contentType: 'application/json; charset=utf-8',
			accept: 'text/plain',
			async: false,
			url: "/SignUpUser",
			data: JSON.stringify(signupDictionary),
			success: function (result) {				
				let signupMsgEle = document.getElementById("sign-message");
				signupMsgEle.style.display = "";
				if (JSON.stringify(result).includes("already")) {
					signupMsgEle.style.color = "red";
				}
				else {
					signupMsgEle.style.color = "green";
				}
				signupMsgEle.textContent = JSON.stringify(result).replace(/['"]+/g, '');
				signupContainer.forEach(function (input) {
					input.value = "";
				});
			},
			error: function (result) {
			}
		});
	}
}

//Function to login user
function LoginUser() {
	loginContainer.forEach(function (input) {
		switch (input.placeholder) {
			case 'Phone':
				regex = new RegExp('^(0|91)?[6-9][0-9]{9}$');
				if (input.value === "" || !regex.test(input.value)) {
					input.nextElementSibling.style.display = "";
					input.nextElementSibling.textContent = "Please enter a valid Phone Number.";
					isValid = false;
				}
				break;
			case 'Password':
				regex = new RegExp('^(?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[^a-zA-Z0-9])(?!.*\\s).{8,15}$');
				if (input.value === "" || !regex.test(input.value)) {
					input.nextElementSibling.style.display = "";
					input.nextElementSibling.textContent = "Password needed atleast 8 char, 1 symbol, upper & lower case letter and number.";
					isValid = false;
				}
		}
		if (isValid) {
			input.nextElementSibling.textContent = "";
			loginDictionary[input.name] = input.value;
		}

	});
	if (Object.keys(loginDictionary).length == 2) {
		$.ajax({
			type: "POST",
			contentType: 'application/json; charset=utf-8',
			accept: 'text/plain',
			async: false,
			url: "/LoginUser",
			data: JSON.stringify(loginDictionary),
			success: function (result) {
				console.log("result");
				window.location='/home';
			},
			error: function (result) {
				console.log("error");
			}

		});
	}
}
