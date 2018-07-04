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
<link rel="icon" href="${images}/favicon.png" type="image/x-icon">

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
										<span> Leave Request </span>
									</div>
								</div>




								<div class="col-md-6">
									<div class="row pagetitle">
										<span class="pull-right">
											<button type="button" id="addnew"
												class="btn btn-primary btn-rounded waves-effect">Add
												Leave</button>
										</span>
									</div>
								</div>


								<div></div>


							</div>

						</div>
					</div>
				</div>

				<div class="col-md-12 card" id="addnewemp">
					<button type="button" class="close" aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
					<div class="custom_title">
						<h2>Add Leave Request</h2>
					</div>
					<p style="color: green;">${Submitmsg}</p>
					<p style="color: red;">${errmsg}</p>
					<c:if test="${Role.equals('ADMIN_ROLE')}">
						<c:set var="role" value="admin" />
					</c:if>

					<c:if test="${Role.equals('MANAGER_ROLE')}">
						<c:set var="role" value="manager" />
					</c:if>

					<c:if test="${Role.equals('EMPLOYEE_ROLE')}">
						<c:set var="role" value="employee" />
					</c:if>

					<form action="${contextRoot}/${role}/leave/register" method="post"
						onsubmit="return Validate()" name="form">
						<hr class="custom_line">
						<div class="body">

							<div class="col-md-6">
								<div class="form-group">
									<div class="form-line">
										<label>Leave Type</label> <select class="form-control"
											size="1" name="leavetype" id="leavetype" required="required">
											<option value="">Please select</option>
											<option value="Loss of Pay">Loss of Pay</option>
											<option value="Casual Leave">Casual Leave 12 Days</option>
											<option value="Medical Leave">Medical Leave</option>
										</select>
									</div>
								</div>
							</div>
							<div class="col-md-6">
								<div class="form-group">
									<div class="input-group addon-line">
										<div class="form-line">
											<label>From </label> <input type="date" name="fromdate"
												id="fromdate" class="form-control" placeholder="From Date"
												required="required">
										</div>
										<span class="input-group-addon"><i
											class="material-icons">date_range </i></span>
									</div>
								</div>

							</div>

							<div class="col-md-6">
								<div class="form-group">
									<div class="input-group addon-line">
										<div class="form-line">
											<label>To </label> <input type="date" name="todate"
												id="todate" class="form-control" placeholder="To Date"
												required="required">
										</div>
										<span class="input-group-addon"><i
											class="material-icons">date_range </i></span>
									</div>
								</div>

							</div>

							<div class="col-md-6">
								<div class="form-group">
									<div class="form-line">
										<label>Leave Reason </label>
										<textarea name="leavereason" id="leavereason"
											class="form-control" rows="3" required="required"></textarea>
									</div>
								</div>

							</div>
							<div class="clearfix"></div>
							<div style="text-align: center;">
								<button type="submit"
									class="btn btn-primary btn-rounded waves-effect">Send
									Leave Request</button>
							</div>
						</div>
					</form>
				</div>

				<div class="row clearfix">



					<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12 ">



						<!-- Table Kitchen Sink -->
						<div class="card">
							<form action="${contextRoot}/${role}/leave/search" method="post">

								<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12 search_leav">

									<div class="col-md-2 padding_col">
										<div class="form-group">

											<select class="form-control" size="1" name="month" id="month">
												<option value="">Select Month</option>
												<option value="01">January</option>
												<option value="02">February</option>
												<option value="03">March</option>
												<option value="04">April</option>
												<option value="05">May</option>
												<option value="06">June</option>
												<option value="07">July</option>
												<option value="08">August</option>
												<option value="09">September</option>
												<option value="10">October</option>
												<option value="11">November</option>
												<option value="12">December</option>
											</select>
										</div>
									</div>
									<div class="col-md-2 padding_col">
										<div class="form-group">

											<select class="form-control" size="1" id="status"
												name="status">
												<option value="">Select Status</option>
												<option value="2">Pending</option>
												<option value="1">Approved</option>
												<option value="0">Declined</option>
											</select>
										</div>
									</div>
									<div class="col-md-2 ">
										<button type="submit" onclick="return Search()"
											class="btn btn-primary  pull-right waves-effect ">
											Search</button>
									</div>
								</div>
							</form>
							<div class="body">
								<p style="color: green;">${DelMsg}</p>
								<table class="tablesaw table-striped table-bordered table-hover">
									<thead class="tableheding">
										<tr>
											<th data-tablesaw-sortable-col
												data-tablesaw-sortable-default-col
												data-tablesaw-priority="persist">Employee</th>
											<th data-tablesaw-sortable-col data-tablesaw-priority="3">
												Leave Type</th>
											<th data-tablesaw-sortable-col data-tablesaw-priority="2">From</th>
											<th data-tablesaw-sortable-col data-tablesaw-priority="4">To
											</th>

											<th data-tablesaw-sortable-col data-tablesaw-priority="4">No
												of Days</th>
											<th data-tablesaw-sortable-col data-tablesaw-priority="2">
												Reason</th>
											<th data-tablesaw-sortable-col data-tablesaw-priority="4">Status
											</th>
											<th data-tablesaw-sortable-col data-tablesaw-priority="1"
												class="actiontabel">Action</th>

										</tr>
									</thead>
									<div class="clearfix"></div>

									<c:forEach items="${empleave}" var="empleaveslist">
										<tbody>


											<tr>
												<td>
													<div class="chip">
														<img src="${images}/mail/one.jpg" alt="Contact Person">
														<span>${User.getFname()} ${User.getLname()}</span>
														<div style="text-align: center">${User.getEid()}</div>
													</div>
												</td>
												<td>${empleaveslist.getLeavetype()}</td>
												<td>${empleaveslist.getFromdate()}</td>
												<td>${empleaveslist.getTodate()}</td>
												<td>${empleaveslist.getCount()}days</td>
												<td>${empleaveslist.getLeavereason()}</td>
												<td>

													<div class="btn-group">
														<c:if test="${empleaveslist.getStatus() == null}">
															<button type="button"
																class="btn btn-primary btn-outline btn-rounded waves-effect"
																data-toggle="dropdown" aria-haspopup="true"
																aria-expanded="false">Pending</button>
														</c:if>
														<c:if test="${empleaveslist.getStatus() == false}">
															<button type="button"
																class="btn btn-primary  btn-outline btn-rounded waves-effect colorred">Declined
															</button>
														</c:if>
														<c:if test="${empleaveslist.getStatus() == true}">
															<button type="button"
																class="btn btn-primary colorgreen btn-outline btn-rounded waves-effect "
																data-toggle="dropdown" aria-haspopup="true"
																aria-expanded="false">Approved</button>
														</c:if>
													</div>
												</td>
												<td>
													<ul class="tabelaction">
														<li class="dropdown"><a href="javascript:void(0);"
															class="dropdown-toggle" data-toggle="dropdown"
															role="button" aria-haspopup="true" aria-expanded="false">
																<i class="material-icons">more_vert</i>
														</a><c:if test="${empleaveslist.getStatus() == null}">
															<ul class="dropdown-menu pull-right">
																
																	<li><a
																		href="${contextRoot}/${role}/leave/delete/${empleaveslist.getId()}"><i
																			class="material-icons">delete</i>Delete</a></li>
																
															</ul></c:if></li>
													</ul>
												</td>

											</tr>

										</tbody>

									</c:forEach>


								</table>
							</div>
						</div>
						<!-- #END# Kitchen Sink -->


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


	<script type="text/javascript">
function Validate() {
    var startDate = document.getElementById("fromdate").value;
    var endDate = document.getElementById("todate").value;
 
    if (Date.parse(endDate) <= Date.parse(startDate)) {
        alert("To date should be greater than From date");
        fromdate.focus();
        return false;
}
}
</script>

	<script>
		$(document).ready(function() {

			$("#addnewemp").css("display", "none");

			$("#addnew").click(function() {

				$("#addnewemp").css("display", "block");
				$("#addnewemp").addClass("animated bounce");
				$("this").css("display", "none")

			});

			$(".close").click(function() {
				$("#addnewemp").css("display", "none");
				$("#addnewemp").addClass("animated");

			});

		});
	</script>
	<script>
     function Search(){
	 var mon = document.getElementById("month").value;
	 var sta = document.getElementById("status").value;
	 if(name=="" && mon=="" && sta=="")
		 {
		 alert("plese Select any one of these");
		 return false;
		 }

}
  </script>
</body>

</html>