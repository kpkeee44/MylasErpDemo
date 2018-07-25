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
<meta
	content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no"
	name="viewport">
<title>Amp Demo</title>
<!-- Favicon-->
<link rel="icon" href="${images}/resources/images/favicon.png"
	type="image/x-icon">

<!--REQUIRED PLUGIN CSS-->
<link href="${plugins}/font-awesome/css/font-awesome.min.css"
	rel="stylesheet">
<link href="${plugins}/bootstrap/css/bootstrap.css" rel="stylesheet">
<link href="${plugins}/node-waves/waves.css" rel="stylesheet" />
<link href="${plugins}/animate-css/animate.css" rel="stylesheet" />
<link href="${plugins}/spinkit/spinkit.css" rel="stylesheet">

<!--THIS PAGE LEVEL CSS-->
<link href="${plugins}/tablesaw/css/tablesaw.min.css" rel="stylesheet">
<link href="${plugins}/nifty-modal/component.css" rel="stylesheet" />

<link
	href="https://stackpath.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css"
	rel="stylesheet" />
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

<style>
.icn {
	font-size: 20px;
	color: #428bca;
	border-color: #357ebd;
	margin-top: 44px;
}
</style>
</head>

<body class="theme-indigo light layout-fixed">
	<div class="wrapper">
		<!-- top navbar-->
		<!-- top navbar-->
		<header class="topnavbar-wrapper">

			<%@include file="shared/navbar.jsp"%>
		</header>
		<!-- sidebar-->
		<%@include file="shared/slidebar.jsp"%>
		<!-- offsidebar-->
		<%@include file="shared/offslidebar.jsp"%>
		<!-- Main section-->
		<c:if test="${Role.equals('ADMIN_ROLE')}">
			<c:set var="role" value="admin" />
		</c:if>

		<c:if test="${Role.equals('MANAGER_ROLE')}">
			<c:set var="role" value="manager" />
		</c:if>

		<c:if test="${Role.equals('EMPLOYEE_ROLE')}">
			<c:set var="role" value="employee" />
		</c:if>


		<section>
			<!-- Page content-->
			<div class="content-wrapper">
				<div class="container-fluid">

					<div class="row clearfix">

						<div class="">


							<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12 topbar">

								<div class="col-md-6">
									<div class="row pagetitle">
										<span> Department</span>
									</div>
								</div>




							</div>

						</div>
					</div>
				</div>

      <td><c:if test="${msg.equals('Department already exists')}">
         <h4 style="color: red;">${msg}</h4>
        </c:if></td>

				<div class="col-md-12 card">
					<div class="custom_title">
						<h2>Edit Department</h2>
						

					</div>
					<form
						action="${contextRoot}/admin/departments/edit/${depdetailsforedit.getDepartmentid()}" method="post">
						<hr class="custom_line">
						<div class="body">
							<div class="col-md-5">
								<div class="form-group">
									<div class="input-group addon-line">
										<div class="form-line">
											<label>Department Name</label> <input type="text"
												name="departmentname" id="departmentname"
												class="form-control" required="required">
										</div>
									</div>
								</div>
							</div>
								<div class="col-md-8 col-xs-8">
                                            <ul class="list-inline" style="margin-top:10px;">
                                            <li>Status:</li>
                                                <li class="eagle-checkbox">
                                                    <label class="eagle-check custom-radio">
                                                        <input id="active1" name="active" value="true" type="radio" class="eagle-check-input" >
                                                        <span class="eagle-check-indicator"></span>
                                                        <span class="eagle-check-description">YES</span>
                                                    </label>
                                                </li>
                                                <li class="eagle-checkbox">
                                                    <label class="eagle-check custom-radio">
                                                        <input id="active" name="active" value="false" type="radio" class="eagle-check-input">
                                                        <span class="eagle-check-indicator"></span>
                                                        <span class="eagle-check-description">NO</span>
                                                    </label>
                                                </li>
                                               
                                            </ul>
                                        </div>
			<!-- 				<div class="col-md-6">
								<div class="form-group">
									<div class="input-group addon-line">
										<div class="form-line">
											<label>To Date </label> <input type="date" name="todate"
												id="todate" class="form-control" placeholder="To Date">
										</div>
										<span class="input-group-addon"><i
											class="material-icons">date_range </i></span>
									</div>
								</div>

							</div> -->
							<div class="clearfix"></div>
							<div style="text-align: center;">
								<button type="submit"
									class="btn btn-primary btn-rounded waves-effect">Edit
									Department</button>
							</div>
						</div>
					</form>
				</div>






			</div>
	</div>
	</div>
	<div class="md-overlay custom-overlay"></div>
	</section>
	<!-- FOOTER-->
	<footer>
		<span>&copy; 2018 - <b class="col-blue">Amp</b></span>
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


	<%-- <c:set var="DepId" value="${depdetailsforedit.getDepartmentId()}"></c:set>
 <%
  int id = (Integer)pageContext.getAttribute("DepId");
  request.setAttribute("DepId", id);
 
 %> --%>
	<script>
 
  document.getElementById("departmentname").value = "${depdetailsforedit.getDepartmentname()}";
  var active = "${depdetailsforedit.getIsactive()}";
	if(active == "true")
	$('#active1').prop('checked', true);
	else
		$('#active').prop('checked', true);	
	holiday.focus(); 
 </script>


</body>

</html>