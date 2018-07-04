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
    <meta content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" name="viewport">
    <title>Log In | Material Design Admin - Eagle</title>
    <!-- Favicon-->
    <link rel="icon" href="${images}/favicon.png" type="image/x-icon">

    <!-- Google Fonts -->
    <link href="https://fonts.googleapis.com/css?family=Roboto:400,700&subset=latin,cyrillic-ext" rel="stylesheet" type="text/css">
    <link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet" type="text/css">

    <!-- Bootstrap Core Css -->
    <link href="${plugins}/bootstrap/css/bootstrap.css" rel="stylesheet">

    <!-- Waves Effect Css -->
    <link href="${plugins}/node-waves/waves.css" rel="stylesheet" />

    <!-- Animation Css -->
    <link href="${plugins}/animate-css/animate.css" rel="stylesheet" />

    <!-- Custom Css -->
    <link href="${css}/style.css" rel="stylesheet">
	 <link href="${css}/custom_style.css" rel="stylesheet">
</head>

<body class="login-page loginbg">
    <div class="login-box">
	   <div class="row">
                    <div class="col-lg-12">
                        <div class="login-logo">
                            <img src="${images}/demo_logo.png" alt="" class="img-responsive img-circle align-center">
                          
                        </div>
                    </div>
                </div>
        <div class="card" style="margin-top:30px;">
            <div class="body">
             				
             				<c:if test="${not empty error}">
			<div class="error" style="color: red;">${error}</div>
		</c:if>
		<c:if test="${not empty msg}">
			<div class="msg" style="color: green;">${msg}</div>
		</c:if>
             				
             				<form name='loginForm' action="${contextRoot}/login" id="log_in" method="POST">
					<p style="color:red">${loginerrmssg} ${errmsg}</p>
					<p style="color:green;">${regmessage}</p>
					<div class="input-group addon-line">
						<span class="input-group-addon"> <i class="material-icons">person</i>
						</span>
						<div class="form-line">
							<input type="text" class="form-control" name="username"
								placeholder="Username" required autofocus size="50" maxlength="50">
						</div>
					</div>
					<div class="input-group addon-line">
						<span class="input-group-addon"> <i class="material-icons">lock</i>
						</span>
						<div class="form-line">
							<input type="password" class="form-control" name="password"
								placeholder="Password" required size="50" maxlength="50">
						</div>
					</div>
					<div class="row">
						<div class="col-xs-6 p-t-5">
							<input type="checkbox" name="rememberme" id="rememberme"
								class="filled-in chk-col-blue"> <label for="rememberme">Remember
								Me</label>
						</div>
						<div class="col-xs-6 align-right p-t-5">
							<c:if test="${userClickForgotPassword==true }">
								<%@include file="forgot-password.jsp"%>
							</c:if>
							<a href="${contextRoot}/forgot-password">Forgot Password?</a>
						</div>
					</div>

					<button class="btn btn-block btn-primary waves-effect"
						type="submit">LOG IN</button>

					<p class="text-muted text-center p-t-20">
						<small>Do not have an account?</small>
					</p>

					<a class="btn btn-sm btn-default btn-block"
						href="${contextRoot}/register">Create an account</a>
<input type="hidden" name="${_csrf.parameterName}"
				value="${_csrf.token}" />
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
    <script src="${js}/pages/examples/login.js"></script>

    <!-- LAYOUT JS -->
    <script src="${js}/demo.js"></script>
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>

</body>

</html>