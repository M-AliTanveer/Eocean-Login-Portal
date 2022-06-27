<%@ page language="java" contentType="text/html; charset=ISO-8859-1" isELIgnored="false"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<!-- Created By CodingLab - www.codinglabweb.com -->
<html lang="en" dir="ltr">
  <head>
    <meta charset="UTF-8">
    <!---<title> Responsive Registration Form | CodingLab </title>--->
    <style>
    @charset "ISO-8859-1";

    @import url('https://fonts.googleapis.com/css2?family=Poppins:wght@200;300;400;500;600;700&display=swap');
    *{
      margin: 0;
      padding: 0;
      box-sizing: border-box;
      font-family: 'Poppins',sans-serif;
    }
    body{
      height: 120vh;
      display: flex;
      justify-content: center;
      align-items: center;
      padding: 10px;
      background: linear-gradient(135deg, #71b7e6, #9b59b6);
    }
    .container{
      max-width: 700px;
      width: 100%;
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
      width: calc(100% / 2 - 20px);
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
    .user-details .input-box select{
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
    form .user-details .input-file{
      margin-bottom: 15px;
      width: calc(100% / 2 - 20px);
    }
    form .input-file span.details{
      display: block;
      font-weight: 500;
      margin-bottom: 5px;
    }
    form .gender-details{
    display: inline-block;
    width: 50%;
    margin: 0px;
    }
    
    form .rules{
    display: inline-block;
    width: 47%;
    margin-left: 10px;
    }
    
   	form .gender-details .gender-title{
      font-size: 20px;
      font-weight: 500;
     }
     form .category{
       display: flex;
       width: 50%;
       margin: 14px 0 ;
       justify-content: flex-start;
     }
     form .category label{
       display: flex;
       align-items: center;
       cursor: pointer;
       margin-right: 20px;
     }
     form .category label .dot{
      height: 18px;
      width: 18px;
      border-radius: 50%;
      margin-right: 10px;
      background: #d9d9d9;
      border: 5px solid transparent;
      transition: all 0.3s ease;
    }
     #dot-1:checked ~ .category label .one,
     #dot-2:checked ~ .category label .two,
     #dot-3:checked ~ .category label .three{
       background: #9b59b6;
       border-color: #d9d9d9;
     }
     form input[type="radio"]{
       display: none;
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
      form .category{
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
     <meta name="viewport" content="width=device-width, initial-scale=1.0">
   <script>
   window.onload=function(){
	   datePickerId = document.getElementById('dob');
	   datePickerId.max = new Date().toLocaleDateString('en-ca');
	   
   }
   
   function check_pass() {
	   	var req = "^(?=.*?[A-Z])(?=.*?[a-z])(?=.*?[0-9])(?=.*?[#?!_@$%^&*-]).{8,}$";
	   	var password = document.getElementById('password').value;
	   	var pass_label = document.getElementById('pass_label');
	   	
	   	if(password.match(req))
		   	{pass_label.style.display = "none";
		   	console.log("matched");
			}
	   	else
		   	{pass_label.style.display = "inline-block";
		   	console.log("no");
		   	document.getElementById('Submit').disabled = true;}  
	}

	function check_same()
	{
		var password = document.getElementById('password');
		var conf_pass = document.getElementById('conf_pass');
		var label = document.getElementById('pass_label');
		if(password.value == conf_pass.value)
			{
			document.getElementById('Submit').disabled = false;
			label.style.display = "none";
			}
		else
			{
			document.getElementById('Submit').disabled = true;
			label.style.display = "inline-block";
			label.innerHTML = "The passwords do not match!"
			}
	}
	
   </script>
   </head>
<body>
  <div class="container">
    <div class="title">Registration</div>
    <div class="content">
      <form action="registerUser" method="POST" modelAttribute="form" enctype="multipart/form-data">
        <div class="user-details">
          <div class="input-box">
            <span class="details">Full Name*</span>
            <input type="text" name="fullname" value="${fullname}" pattern="[a-zA-Z'-'\s]*\S" required>
          </div>
          
          <div class="input-box">
            <span class="details">Email*</span>
            <input type="email" name="email" value="${email}" required>
          </div>
          <div class="input-box">
            <span class="details">Date Of Birth*</span>
            <input type="date" id="dob" name="dob" placeholder="Enter your number" required>
          </div>
          <div class="input-box">
            <span class="details">Password*</span>
            <input id="password" type="password" name="password" value="${password}" onchange='check_pass();' required>
          </div>
          <div class="input-box">
            <span class="details">Enrolled Program*</span>
            <select id="programchooser" name="program" required>
			    <option value="BSCS">BSCS</option>
			    <option value="BESE">BESE</option>
			    <option value="BCYS">BCYS</option>
			    <option value="BAI">BAI</option>
		  	</select>
          </div>
          <div class="input-box">
            <span class="details">Confirm Password*</span>
            <input id = "conf_pass" type="password" name="conf_pass" placeholder="Confirm your password" onchange='check_same();' required>
          </div>
          <div class="input-box">
            <span class="details">Batch Code*</span>
          	<select id="batchchooser" name="batch" required>
			    <option value="19K">19K</option>
			    <option value="20K">20K</option>
			    <option value="21K">21K</option>
			    <option value="22K">22K</option>
		  	</select>
          </div>
          <div class="input-file">
            <span class="details">Profile Picture</span>
            <input type="file" name="profile" accept="image/*">
          </div>
          
        </div>
        <div class="gender-details">
          <input type="radio" name="gender" value="Male" id="dot-1" required>
          <input type="radio" name="gender" value="Female" id="dot-2">
          <span class="gender-title">Gender*</span>
          <div class="category">
            <label for="dot-1">
            <span class="dot one"></span>
            <span class="gender">Male</span>
          </label>
          <label for="dot-2">
            <span class="dot two"></span>
            <span class="gender">Female</span>
          </label>
          </div>
          
          
        </div>
        <div class="rules">
          <p id="pass_label" style="font-size: 12px; color: red;">The Entered password must meet the requirements:<br>
          * Should be minimum 8 characters<br>
          * Should be alphanumeric <br>
          * Should contain at least 1 number<br>
          * Should contain at least 1 capital alphabet<br>
          * Should contain at least 1 symbol<br>
          </p>
          </div>
        <div class="button">
          <input id = "Submit" type="submit" value="Register" disabled>
        </div>
      </form>
    </div>
  </div>

</body>
</html>
