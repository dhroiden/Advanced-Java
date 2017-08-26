function check() {
	if(isValid()){
		window.location.href="index.html";
	}
}

function isValid() {
	var userName = document.getElementById("userName").value;
	var userPassword = document.getElementById("userPasw").value;
	var userConfirmPassword = document.getElementById("userConfPasw").value;
	var userEmail = document.getElementById("userConfPasw").value;
	var userFirstName = document.getElementById("userFirstName").value;
	var userLastName = document.getElementById("userLastName").value;
	
	if(!isPwdValid(userPassword)) {
		alert("Password needs to be:" +
				"\na. Minimum 10 char" +
				"\nb. Should contain at least one special char, one digit.");
		return false;
	}	
	
	if (userPassword != userConfirmPassword) {
		alert("Passwords don't match!");
		return false;
	}
	
	if (userFirstName.length < 8) {
		alert("First name needs to larger that 8 charecters.");
		return false;
	}
	
	if(userLastName.length<=0) {
		return false;
	}
	
	userFullName = document.getElementById("userFullName").value= userFirstName+" "+userLastName;
	
	return true;
}

function isPwdValid(userPassword) {
	var flag = true;
	var regularExpression = /^(?=.*[0-9])(?=.*[!@#$%^&*])[a-zA-Z0-9!@#$%^&*]{6,16}$/;
	if (!regularExpression.test(userPassword)) {
		flag = false;
		console.log(flag);
		return false;
	} else {
		console.log(flag);
		return true;
	}
}
