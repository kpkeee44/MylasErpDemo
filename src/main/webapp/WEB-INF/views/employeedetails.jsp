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
				<div class="page-header">
					<h2>Employee</h2>
				</div>
			

				<div class="col-md-12">
					<div class="col-md-4">
						<div class="user-card-wrapper">
							<div class="user-card">
								<img class="img-responsive" src="${images}/mail/1.jpg" alt="" />
								<div class="user-card-info">
									<h6>${employee.getFname()}${employee.getLname()}</h6>
									<span>${employee.getDesignation()}</span>
									<div class="user-card-socials">
										<a href="#" title=""><i class="fa fa-twitter"></i></a> <a
											href="#" title=""><i class="fa fa-facebook"></i></a>
									</div>
								</div>
							</div>
						</div>
					</div>
					<div class="col-md-8">
						<div class="card">
							<table class="table empdt">
								<tr>
									<td>Username</td>
									<td>:</td>
									<td>${employee.getUname()}</td>
								</tr>
								<tr>
									<td>Email:</td>
									<td>:</td>
									<td>${employee.getEmail()}</td>
								</tr>
								<tr>
									<td>Employee ID:</td>
									<td>:</td>
									<td>${employee.getEid()}</td>
								</tr>
								<tr>
									<td>Joining Date:</td>
									<td>:</td>
									<td>${employee.getJdate()}</td>
								</tr>
								<tr>
									<td>Phone:</td>
									<td>:</td>
									<td>${employee.getPhone()}</td>
								</tr>
								<tr>
									<td>Company:</td>
									<td>:</td>
									<td>${employee.getCompName()}</td>
								</tr>
							</table>
						</div>
					</div>
				</div>
			</div>
		</div>
	</section>
	<!-- FOOTER-->
	<footer>
		<span>&copy; 2018 - <b class="col-blue">Amp</b></span>
	</footer>

	<!-- CORE PLUGIN JS -->
	<script src="${plugins}/jquery/jquery.min.js"></script>
	<script src="${plugins}/bootstrap/js/bootstrap.js"></script>
	<script src="${plugins}/modernizr/modernizr.custom.js"></script>
	<script src="${plugins}/screenfull/dist/screenfull.js"></script>
	<script src="${plugins}/jQuery-Storage-API/jquery.storageapi.js"></script>
	<script src="${plugins}/jquery-slimscroll/jquery.slimscroll.js"></script>
	<script src="${plugins}/node-waves/waves.js"></script>

	<!--THIS PAGE LEVEL JS-->
	<script src="${plugins}/masonry/masonry.pkgd.min.js"></script>
	<script src="${plugins}/jquery-knob/jquery.knob.min.js"></script>
	<script src="${plugins}/jquery-sparkline/jquery.sparkline.js"></script>
	<script src="${plugins}/skycon/skycons.js"></script>
	<script src='${plugins}/chartist/chartist.js'></script>
	<script src="${plugins}/masonry/masonry.min.js"></script>
	<script src="${plugins}/unslider/js/unslider-min.js"></script>
	<!--Chat js-->
	<script src="${plugins}/wchat/assets/js/custom.js"></script>
	<script type="text/javascript"
		src="${plugins}/wchat/chatjs/lightbox.js"></script>
	<script type="text/javascript"
		src="${plugins}/wchat/chatjs/dashboard.js"></script>
	<script type="text/javascript" src="${plugins}/wchat/chatjs/custom.js"></script>
	<!-- #End# Chat js-->



	<!-- LAYOUT JS -->

<!-- LAYOUT JS -->
<script src="${js}/demo.js"></script>
<script src="${js}/layout.js"></script>


	<style>
.empdt td {
	border-bottom: 1px solid #fff !important;
	text-align: left;
	padding: 21px !important;
}
</style>

</body>

</html>