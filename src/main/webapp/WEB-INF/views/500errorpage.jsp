
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
    <title>500 | Material Design Admin - Fusion</title>
    <!-- Favicon-->
    <link rel="icon" href="${images}/favicon.png" type="image/x-icon">

    <!-- Google Fonts -->
    <link href="https://fonts.googleapis.com/css?family=Roboto:400,700&subset=latin,cyrillic-ext" rel="stylesheet" type="text/css">
    <link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet" type="text/css">

    <!-- Bootstrap Core Css -->
    <link href="${plugins}/bootstrap/css/bootstrap.css" rel="stylesheet">

    <!-- Waves Effect Css -->
    <link href="${plugins}/node-waves/waves.css" rel="stylesheet" />

    <!-- Custom Css -->
    <link href="${css}/style.css" rel="stylesheet">
</head>

<body class="error error_five">
    <div class="error-box">
        <img src="${images}/error500.gif" class="img-responsive center-block height150" alt="404">
        <div class="error-message p-t-0">
            OOOPS!!!! 500
            <p>Something went wrong.</p>
        </div>
        <p class="text-muted font-13">Don't worry though, we know and are fixing it. Please try again later.</p>
        <div class="error-bottom">
            <a href="${contextRoot}/index.html" class="btn btn-danger btn-outline">Return To Dashboard</a>
        </div>
    </div>

    <!-- Jquery Core Js -->
    <script src="${plugins}/jquery/jquery.min.js"></script>

    <!-- Bootstrap Core Js -->
    <script src="${plugins}/bootstrap/js/bootstrap.js"></script>

    <!-- Waves Effect Plugin Js -->
    <script src="${plugins}/node-waves/waves.js"></script>

    <link rel="icon" href="${images}/favicon.png" type="image/x-icon">

    <!-- Google Fonts -->
    <link href="https://fonts.googleapis.com/css?family=Roboto:400,700&subset=latin,cyrillic-ext" rel="stylesheet" type="text/css">
    <link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet" type="text/css">

    <!-- Bootstrap Core Css -->
    <link href="${plugins}/bootstrap/css/bootstrap.css" rel="stylesheet">

    <!-- Waves Effect Css -->
    <link href="${plugins}/node-waves/waves.css" rel="stylesheet" />

    <!-- Custom Css -->
    <link href="${css}/style.css" rel="stylesheet">
</head>

<body class="error error_five">
    <div class="error-box">
        <img src="${images}/error500.gif" class="img-responsive center-block height150" alt="404">
        <div class="error-message p-t-0">
            OOOPS!!!! 500
            <p>Something went wrong.</p>
        </div>
        <p class="text-muted font-13">Don't worry though, we know and are fixing it. Please try again later.</p>
        <div class="error-bottom">
            <a href="${contextRoot}/" class="btn btn-danger btn-outline">Return To Dashboard</a>
        </div>
    </div>

    <!-- Jquery Core Js -->
    <script src="${plugins}/jquery/jquery.min.js"></script>

    <!-- Bootstrap Core Js -->
    <script src="${plugins}/bootstrap/js/bootstrap.js"></script>

    <!-- Waves Effect Plugin Js -->
    <script src="${plugins}/node-waves/waves.js"></script>

</body>

</html>