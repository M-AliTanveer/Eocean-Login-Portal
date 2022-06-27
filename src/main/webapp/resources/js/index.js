function listeners(){
    const signUpButton = document.getElementById('signUp');
	const signInButton = document.getElementById('signIn');
	const container = document.getElementById('container');

	signUpButton.addEventListener('click', () => {
		container.classList.add("right-panel-active");
	});
	
	signInButton.addEventListener('click', () => {
		container.classList.remove("right-panel-active");
	});
}

function check_pass() {
   	var req = "^(?=.*?[A-Z])(?=.*?[a-z])(?=.*?[0-9])(?=.*?[#?!_@$%^&*-]).{8,}$";
   	var password = document.getElementById('signup-password').value;
   	var pass_label = document.getElementById('signup_error_label');
   	var button = document.getElementById("signupbutton");
   	
   	if(password.match(req))
	   	{pass_label.style.display = "none";
	   	console.log("matched");
	   	return true;
		}
   	else
	   	{pass_label.style.display = "inline-block";
	   	console.log("didnt match")
	   	pass_label.innerHTML = "The Password should contain at least 1 digit, 1 capital letter and be 8 characters long!";
	   	return false;
	   	}  
}


