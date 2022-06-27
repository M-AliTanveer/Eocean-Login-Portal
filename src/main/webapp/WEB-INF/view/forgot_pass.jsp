<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
<style>
    @charset "ISO-8859-1";

    @import url('https://fonts.googleapis.com/css2?family=Poppins:wght@200;300;400;500;600;700&display=swap');
    *{
      margin: 0;
      padding: 0;
      box-sizing: border-box;
      font-family: 'Poppins',sans-serif;
    }
    #resend{
    display: none;
    }
    body{
      height: 100vh;
      display: flex;
      justify-content: center;
      align-items: center;
      padding: 10px;
      background: linear-gradient(135deg, #71b7e6, #9b59b6);
    }
    .container{
      max-width: 700px;
      width: 30%;
      background-color: #fff;
      padding: 25px 30px;
      border-radius: 5px;
      box-shadow: 0 5px 10px rgba(0,0,0,0.15);
    }
    .container .title{
      font-size: 25px;
      font-weight: 500;
      position: relative;
    }
    .container .title::before{
      content: "";
      position: absolute;
      left: 0;
      bottom: 0;
      top: 5px;
      height: 3px;
      width: 30px;
      border-radius: 5px;
      background: linear-gradient(135deg, #71b7e6, #9b59b6);
    }
    .content form .user-details{
      display: flex;
      flex-wrap: wrap;
      justify-content: space-between;
      margin: 20px 0 12px 0;
    }
    form .user-details .input-box{
      margin-bottom: 15px;
      width: 100%;
    }
    form .input-box span.details{
      display: block;
      font-weight: 500;
      margin-bottom: 5px;
    }
    .user-details .input-box input{
      height: 45px;
      width: 100%;
      outline: none;
      font-size: 16px;
      border-radius: 5px;
      padding-left: 15px;
      border: 1px solid #ccc;
      border-bottom-width: 2px;
      transition: all 0.3s ease;
    }
    .user-details .input-box input:focus,
    .user-details .input-box input:valid{
      border-color: #9b59b6;
    }
    
     form .button{
       height: 45px;
       margin: 35px 0;
     }
     form .button input{
       height: 100%;
       width: 100%;
       border-radius: 5px;
       border: none;
       color: #fff;
       font-size: 18px;
       font-weight: 500;
       letter-spacing: 1px;
       cursor: pointer;
       transition: all 0.3s ease;
       background: linear-gradient(135deg, #71b7e6, #9b59b6);
     }
     form .button input:hover{
      /* transform: scale(0.99); */
      background: linear-gradient(-135deg, #71b7e6, #9b59b6);
      }
      form .button input:disabled{
      /* transform: scale(0.99); */
      background: linear-gradient(90deg, rgba(128,140,127,1) 35%, rgba(25,28,28,1) 100%);
      }
     @media(max-width: 584px){
     .container{
      max-width: 100%;
    }
    
    
    form .user-details .input-box{
        margin-bottom: 15px;
        width: 100%;
      }

      .content form .user-details{
        max-height: 300px;
        overflow-y: scroll;
      }
      .user-details::-webkit-scrollbar{
        width: 5px;
      }
      }
      @media(max-width: 459px){
      .container .content .category{
        flex-direction: column;
      }
    }
        
    </style>
     
<script>
window.onload=function(){
	var checker = ${sent};
	var emailchec = ${isemail};
	if(checker==true)
		{
		console.log("hi");
		document.getElementById('OTP').disabled = false;
		document.getElementById('pass').disabled =false;
		}
	if(emailchec==true)
		{
		document.getElementById('email').disabled = true;
		document.getElementById('email').value = "${email}";
		document.getElementById('Submit').value = "Update Password";
		document.getElementById('resend').style.display = "block";
		document.getElementById('resend').value = "Resend";
		
		}
}
function check_pass() {
   	var req = "^(?=.*?[A-Z])(?=.*?[a-z])(?=.*?[0-9])(?=.*?[#?!_@$%^&*-]).{8,}$";
   	var password = document.getElementById('pass').value;
   	var pass_label = document.getElementById('error_label');
   	var button = document.getElementById("Submit");
   	
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
</script>
</head>
<body>
<div class="container">
    <div class="title">Forgot Your Password?</div>
    <div class="content">
      <form onsubmit = "return check_pass()" action="passreset" method="POST" modelattribute="form" enctype="multipart/form-data">
        <div class="user-details">
          <div class="input-box">
            <span class="details">Enter your registered email</span>
      		<p style="font-size:75%; color: blue;">Please enter the Email of your registered account to recieve your OTP code</p>
            <input id = "email" type="email" name="email" placeholder="Enter Your Email" required>
            
            <p id = "OTP-label" style="font-size:75%; color: blue;">Please enter the OTP sent to your email</p>
            <input id="OTP" type="number" name="OTP" placeholder="Enter OTP" disabled value="">
            
            <p id = "pass-label" style="font-size:75%; color: blue;">Please enter the OTP sent to your email</p>
            <input id="pass" type="password" name="password" placeholder="Enter new pass" disabled value="">
          	<p id="error_label" style="color:red;"><br>${error}</p>
          <input type = "button" id="resend" value="Resend OTP" onclick="location.href='resendOTP'" >
          </div>
        </div>
        <div class="button">
          <input id="Submit" type="submit" value="Send OTP" >
        </div>
      </form>
    </div>
  </div>
</body>
</html>