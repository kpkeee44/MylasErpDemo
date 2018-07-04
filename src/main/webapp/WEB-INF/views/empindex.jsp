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
    <meta http-equiv="X-UA-Compatible" content="IE=Edge">
    <meta content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" name="viewport">
    <title>Amp Demo</title>
    <!-- Favicon-->
    <link rel="icon" href="../../favicon.png" type="image/x-icon">

    <!--REQUIRED PLUGIN CSS-->
    <link href="${plugins}/font-awesome/css/font-awesome.min.css" rel="stylesheet">
    <link href="${plugins}/bootstrap/css/bootstrap.css" rel="stylesheet">
    <link href="${plugins}/node-waves/waves.css" rel="stylesheet" />
    <link href="${plugins}/animate-css/animate.css" rel="stylesheet" />
    <link href="${plugins}/spinkit/spinkit.css" rel="stylesheet">

    <!--THIS PAGE LEVEL CSS-->
    <link href="${plugins}/tablesaw/css/tablesaw.min.css" rel="stylesheet">
	  <link href="${plugins}/nifty-modal/component.css" rel="stylesheet" />
	

    <!--REQUIRED THEME CSS -->
    <link href="${css}/style.css" rel="stylesheet">
    <link href="${css}/layout.css" rel="stylesheet">
    <link href="${css}/themes/main_theme.css" rel="stylesheet" />
		 <link href="${css}/custom_style.css" rel="stylesheet">

    <!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
    <!--[if lt IE 9]>
    <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
    <script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->
</head>

<body class="theme-indigo light layout-fixed">


<div class="wrapper">

	<!-- top navbar-->
		<header class="topnavbar-wrapper">

			<%@include file="shared/navbar.jsp"%>
		</header>
		<!-- sidebar-->
		<%@include file="shared/slidebar.jsp"%>
		<!-- offsidebar-->
		<%@include file="shared/offslidebar.jsp"%>
			<!-- Main section-->
    <section>
        <!-- Page content-->
        <div class="content-wrapper">
        
	<div class="row clearfix">
                    <div class="col-lg-3">
                        <div class="widget style2 bg-red hover-zoom-effect">
                            <div class="col-xs-4 widget-icon">
                                <i class="material-icons">email</i>
                            </div>
                            <div class="col-xs-8 widget-body text-right">
                                <span> New messages </span>
                                <h2 class="num count-to" data-from="0" data-to="250" data-speed="1000" data-fresh-interval="20">250</h2>
                            </div>
                        </div>
                    </div>
                    <div class="col-lg-3">
                        <div class="widget style2 bg-green">
                            <div class="col-xs-4 widget-icon">
                                <i class="material-icons">face</i>
                            </div>
                            <div class="col-xs-8 widget-body text-right">
                                <span> New users </span>
                                <h2 class="num count-to" data-from="0" data-to="222" data-speed="1000" data-fresh-interval="20">222</h2>
                            </div>
                        </div>
                    </div>
                    <div class="col-lg-3">
                        <div class="widget style2 bg-cyan">
                            <div class="col-xs-4 widget-icon">
                                <i class="material-icons">shopping_cart</i>
                            </div>
                            <div class="col-xs-8 widget-body text-right">
                                <span> New orders </span>
                                <h2 class="num count-to" data-from="0" data-to="90" data-speed="1000" data-fresh-interval="20">90</h2>
                            </div>
                        </div>
                    </div>
                    <div class="col-lg-3">
                        <div class="widget style2 bg-indigo">
                            <div class="col-xs-4 widget-icon">
                                <i class="material-icons">library_music</i>
                            </div>
                            <div class="col-xs-8 widget-body text-right">
                                <span> New albums </span>
                                <h2 class="num count-to" data-from="0" data-to="15" data-speed="1000" data-fresh-interval="20">15</h2>
                            </div>
                        </div>
                    </div>
                </div>
                <!--row-->
        </div>
			<div class="md-overlay custom-overlay"></div>
    </section>
    <!-- FOOTER-->
    <footer>
        <span>&copy; 2018 -  <b class="col-blue">Amp</b></span>
    </footer>
</div>
<!-- CORE PLUGIN JS -->
<script src="${plugins}/jquery/jquery.min.js"></script>
<script src="${plugins}/bootstrap/js/bootstrap.js"></script>
<script src="${plugins}/modernizr/modernizr.custom.js"></script>
<script src="${plugins}/screenfull/dist/screenfull.js"></script>
<script src="${plugins}/jQuery-Storage-API/jquery.storageapi.js"></script>
<script src="${plugins}/jquery-slimscroll/jquery.slimscroll.js"></script>
<script src="${plugins}/node-waves/waves.js"></script>

<!--THIS PAGE LEVEL JS-->
<script src="${plugins}/tablesaw/js/tablesaw.js"></script>
<script src="${plugins}/tablesaw/js/tablesaw-init.js"></script>
<script src="${js}/pages/ui/modals.js"></script>
<script src="${plugins}/bootstrap-notify/bootstrap-notify.js"></script>
<script src="${plugins}/nifty-modal/modalEffects.js"></script>
<script src="${plugins}/nifty-modal/classie.js"></script>

<!-- LAYOUT JS -->
<script src="${js}/demo.js"></script>
<script src="${js}/layout.js"></script>






</body>

</html>