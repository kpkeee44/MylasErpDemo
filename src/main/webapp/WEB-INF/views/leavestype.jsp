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
<link rel="icon" href="${images}favicon.png" type="image/x-icon">

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
				<div class="container-fluid">

					<div class="row clearfix">

						<div class="">


							<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12 topbar">

								<div class="col-md-6">
									<div class="row pagetitle">
										<span>Leave Type</span>
									</div>
								</div>
								<div class="col-md-6">
									<div class="row pagetitle">
										<span class="pull-right">
											<button type="button" id="addnew1" class="btn btn-primary btn-rounded waves-effect">Add
												Leave Type</button>
										</span>
									</div>
								</div>

							</div>

						</div>
					</div>
				</div>
				
				   <td ><c:if test="${msg.equals('leavetype added successfully!....')}">
									<h4 style="color: green;">${msg}</h4>
								</c:if></td>
						<td><c:if test="${msg.equals('Entered Leavetype already exists')}">
									<h4 style="color: red;">${msg}</h4>
								</c:if></td>
				
				
				<div class="col-md-12 card animated bounce" id="addnewemp">
					<button type="button" class="close" aria-label="Close">
						<span aria-hidden="true">×</span>
					</button>
					
					
					<p style="color: red;">${errmsg}</p>
					<c:if test="${Role.equals('ADMIN_ROLE')}">
						<c:set var="role" value="admin" />
					</c:if>

				<%-- 	<c:if test="${Role.equals('MANAGER_ROLE')}">
						<c:set var="role" value="manager" />
					</c:if>

					<c:if test="${Role.equals('EMPLOYEE_ROLE')}">
						<c:set var="role" value="employee" />
					</c:if> --%>

					<form action="${contextRoot}/${role}/leavetype/register" method="post"
						 name="form">
						
							<div class="col-lg-6 col-md-6 col-sm-6 col-xs-6 search_leav">
								<div class="col-md-6 padding_col">
									<div class="form-group">
										<input type="text" class="form-control" id="leavetype"
											placeholder="Enter leavetype" name="leavetype" value=""/>
									</div>
								</div>
								<button type="submit"
									class="btn btn-primary btn-rounded waves-effect">Submit</button>
												
							</div>
							
							<div  class="col-lg-6 col-md-6 col-sm-6 col-xs-6">
								
							</div>
							</form>
						</div>
					</div>
					
					
					<div class="row clearfix">


					<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">



						<!-- Table Kitchen Sink -->
						<div class="card">

							<div class="body">
								<table class="tablesaw table-striped table-bordered table-hover">
									<thead class="tableheding">
										<tr>
											<th data-tablesaw-sortable-col data-tablesaw-priority="3">Leave Type
											</th>
											
											
											<th data-tablesaw-sortable-col data-tablesaw-priority="1"
												class="actiontabel">Action</th>

										</tr>
									</thead>
									<div class="clearfix"></div>
									<tbody>
										<c:forEach items="${leavetypeslist}" var="leavetypes">
											<tr>
												<td>${leavetypes.getLeavetype()}</td>
											
												<td>
													<ul class="tabelaction">
														<li class="dropdown"><a href="javascript:void(0);"
															class="dropdown-toggle" data-toggle="dropdown"
															role="button" aria-haspopup="true" aria-expanded="false">
																<i class="material-icons">more_vert</i>
														</a>
															<ul class="dropdown-menu pull-right">
																<li><a
																	href="${contextRoot}/admin/leavetype/edit/${leavetypes.getId()}"><i
																		class="material-icons">edit</i>Edit</a></li>
																<li><a
																	href="${contextRoot}/admin/leavetype/delete/${leavetypes.getId()}"><i
																		class="material-icons">delete</i>Delete</a></li>

															</ul></li>
													</ul>
												</td>

											</tr>
										</c:forEach>
									</tbody>
								</table>
							</div>
						</div>
						<!-- #END# Kitchen Sink -->


					</div>
				</div>
			
			

				
				
				
				
				
			
			<div class="md-overlay custom-overlay"></div>
		</section>
		<!-- FOOTER-->
		<footer>
			<span>© 2018 - <b class="col-blue">Amp</b></span>
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


	<!-- <script type="text/javascript">
		/ alert("${Holiday.getName()}"); /

		document.getElementById("holiday").value = "${Holiday.getName()}";
		document.getElementById("hdate").value = "${Holiday.getHdate()}";
	</script> -->

<script>
$(".close").click(function(){
	$("#addnewemp").css("display","none");
	
	
});
$("#addnew1").click(function(){
	$("#addnewemp").css("display","block");
});
</script>

</body>

</html>