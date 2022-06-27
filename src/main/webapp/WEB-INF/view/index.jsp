<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
<link rel="stylesheet" href="resources/css/index.css">
<script>
window.onload=function(){
	var isError="${isError}";
	var success = "${success}";
	const container = document.getElementById('container');
	console.log(isError);
	if(isError=="signup")
	{
		console.log("inside");
	container.classList.add("right-panel-active");
	}
	if(success=="true")
	{
		document.getElementById('login_error_label').style.color = "green";
	}
	else
		document.getElementById('login_error_label').style.color = "red";

	listeners();
	
}

</script>
<script src="resources/js/index.js"></script>

</head>
<body>
<h2>EOcean Student Portal</h2>
<div class="container" id="container">
	<div class="form-container sign-up-container">
		<form onsubmit = "return check_pass()" action="register" method="POST" enctype="multipart/form-data">
			<h1>Create Account</h1>
			<p id="signup_error_label" style="color:red;">${error_signup}</p>
			<input type="text" name="fullname" placeholder="Name" required/>
			<input type="email" name="email" placeholder="Email" required/>
			<input type="password" id="signup-password" name="password" placeholder="Password" required/>
			<button id="signupbutton" >Sign Up</button>
		</form>
	</div>
	<div class="form-container sign-in-container">
		<form action="login" method="POST">
			<h1>Sign in</h1>
			<p id="login_error_label" style="color:red;">${error_login}</p>
			<input type="email" name="email" placeholder="Email" required/>
			<input type="password" name="password" placeholder="Password" required/>
			<a href="forgot_pass">Forgot your password?</a>
			<button>Sign In</button>
		</form>
	</div>
	<div class="overlay-container">
		<div class="overlay">
			<div class="overlay-panel overlay-left">
				<h1>Welcome Back!</h1>
				<p>To keep connected with us please login with your personal info</p>
				<button class="ghost" id="signIn">Sign In</button>
			</div>
			<div class="overlay-panel overlay-right">
				<h1>Hello, Friend!</h1>
				<p>Enter your personal details and start journey with us</p>
				<button class="ghost" id="signUp">Sign Up</button>
			</div>
		</div>
	</div>
</div>

<footer>
	<p>Created by Muhammad Ali</p>
</footer>
</body>
</html>