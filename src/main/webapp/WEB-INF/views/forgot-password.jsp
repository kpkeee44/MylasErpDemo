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
    <title>Forgot Password | Material Design Admin - Eagle</title>
    <!-- Favicon-->
    <link rel="icon" href="${images}favicon.png" type="image/x-icon">

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
</head>

<body class="forgot-pass">
    <div class="forgot-box">
        <div class="card">
            <div class="body">
                <div class="row">
                    <div class="col-lg-12">
                        <div class="login-logo">
                            <img src="../../assets/images/logo-circle.png" alt="" class="img-responsive img-circle align-center">
                            <p>Material Design Admin Template</p>
                        </div>
                    </div>
                </div>
                <form id="forgot_pass" method="POST">
                    <div class="msg">
                        Fill with your mail to receive instructions on how to reset your password.
                    </div>
                    <div class="input-group addon-line">
                        <span class="input-group-addon">
                            <i class="material-icons">email</i>
                        </span>
                        <div class="form-line">
                            <input type="email" class="form-control" name="email" placeholder="Email" required autofocus>
                        </div>
                    </div>

                    <button class="btn btn-block btn-primary waves-effect" type="submit">RESET PASSWORD</button>

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
    <script src="${js}/pages/examples/forgot-password.js"></script>

    <!-- LAYOUT JS -->
    <script src="${js}/demo.js"></script>

</body>

</html>