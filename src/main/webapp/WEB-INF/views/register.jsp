<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<spring:url var="css" value="/resources/css" />
<spring:url var="js" value="/resources/js" />
<spring:url var="images" value="/resources/images" />
<spring:url var="plugins" value="/resources/plugins" />
<c:set var="contextRoot" value="${pageContext.request.contextPath}" /> 
<!DOCTYPE html>
<html>

<head>
<meta charset="UTF-8">
<meta
	content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no"
	name="viewport">
<title>Register | Material Design Admin - Eagle</title>
<!-- Favicon-->
<link rel="icon" href="${images}favicon.png" type="image/x-icon">

<!-- Bootstrap Core Css -->
<link href="${plugins}/bootstrap/css/bootstrap.css" rel="stylesheet">

<!-- Waves Effect Css -->
<link href="${plugins}/node-waves/waves.css" rel="stylesheet" />

<!-- Animation Css -->
<link href="${plugins}/animate-css/animate.css" rel="stylesheet" />

<!-- Custom Css -->
<link href="${css}/style.css" rel="stylesheet">
</head>

<body class="register-ac">
	<div class="register-box">
		<div class="card">
			<div class="body">
				<div class="row">
					<div class="col-lg-12">
						<div class="login-logo">
							<img src="${images}/logo-circle.png" alt=""
								class="img-responsive img-circle align-center">
							<p>Material Design Admin Template</p>
						</div>
					</div>
				</div>

				
				<form id="register" method="POST" onsubmit="return Validate()">

				<p style="color: red;">${regmessage}</p>
				<form id="register" method="POST">
					<div class="msg">Create an account</div>
					<div class="input-group addon-line">
						<span class="input-group-addon"> <i class="material-icons">person</i>
						</span>
						<div class="form-line">
							<input type="text" class="form-control" name="username"
								placeholder="Username" required autofocus size="50" maxlength="50">
						</div>
					</div>
					<div class="input-group addon-line">
						<span class="input-group-addon"> <i class="material-icons">email</i>
						</span>
						<div class="form-line">
							<input type="email" class="form-control" name="email"
								placeholder="Email Address" required size="50" maxlength="50">
						</div>
					</div>
					<div class="input-group addon-line">
						<span class="input-group-addon"> <i class="material-icons">person</i>
						</span>
						<div class="form-line">
							<input type="text" class="form-control" name="empid"
								placeholder="Admin ID" required size="50" maxlength="50">
						</div>
					</div>
					<div class="input-group addon-line">
						<span class="input-group-addon"> <i class="material-icons">lock</i>
						</span>
						<div class="form-line">
							<input type="password" class="form-control" name="password" id="password"
								minlength="8" placeholder="Password" pattern="(?=.*\d)(?=.*[a-z])(?=.*[A-Z]).{8,}" title="Must contain at least one number and one uppercase and lowercase letter, and at least 8 or more characters" required>
						</div>
					</div>
					<div class="input-group addon-line">
						<span class="input-group-addon"> <i class="material-icons">lock</i>
						</span>
						<div class="form-line">
							<input type="password" class="form-control" name="confirm" id="confirm"
								minlength="8" placeholder="Confirm Password" required>
						</div>
					</div>
					<div class="form-group">
						<input type="checkbox" name="terms" id="terms"
							class="filled-in chk-col-blue"> <label for="terms">I
							agree with the <a href="javascript:void(0);">terms and policy</a>.
						</label>
					</div>

					<button class="btn btn-block btn-primary waves-effect"
						type="submit">REGISTER NOW</button>

					<div class="m-t-25 m-b--5 align-center">
						<a href="${contextRoot}/login">You already have an account?</a>
					</div>
				</form>
			</div>
		</div>
	</div>

	<!-- CORE PLUGIN JS -->
	<script src="${plugins}/jquery/jquery.min.js"></script>
	<script src="${plugins}/bootstrap/js/bootstrap.js"></script>
	<script src="${plugins}/node-waves/waves.js"></script>
	<script src="${plugins}/jquery-slimscroll/jquery.slimscroll.js"></script>

	<!--THIS PAGE LEVEL JS-->
	<script src="${plugins}/jquery-validation/jquery.validate.js"></script>
	<script src="${js}/pages/examples/register.js"></script>

	<!-- LAYOUT JS -->
	<script src="${js}/demo.js"></script>
<script type="text/javascript">

function Validate() {
    var password = document.getElementById("password").value;
    var confirmPassword = document.getElementById("confirm").value;
    if (password != confirmPassword) {
        alert("Passwords do not match.");
        return false;
    }
   
    var myInput = document.getElementById("password");
    var letter = document.getElementById("letter");
    var capital = document.getElementById("capital");
    var number = document.getElementById("number");
    var length = document.getElementById("length");


    // Validate lowercase letters
    var lowerCaseLetters = /[a-z]/g;
    if(myInput.value.match(lowerCaseLetters)) { 
     letter.classList.remove("invalid");
     letter.classList.add("valid");
    } else {
     letter.classList.remove("valid");
     letter.classList.add("invalid");
    }
    //Validate capital letters
    var upperCaseLetters = /[A-Z]/g;
    if(myInput.value.match(upperCaseLetters)) { 
      capital.classList.remove("invalid");
      capital.classList.add("valid");
    } else {
      capital.classList.remove("valid");
      capital.classList.add("invalid");
    }

    // Validate numbers
    var numbers = /[0-9]/g;
    if(myInput.value.match(numbers)) { 
      number.classList.remove("invalid");
      number.classList.add("valid");
    } else {
      number.classList.remove("valid");
      number.classList.add("invalid");
    }

    // Validate length
    if(myInput.value.length >= 8) {
      length.classList.remove("invalid");
      length.classList.add("valid");
    } else {
      length.classList.remove("valid");
      length.classList.add("invalid");
    }

}

</script>
</body>

</html>