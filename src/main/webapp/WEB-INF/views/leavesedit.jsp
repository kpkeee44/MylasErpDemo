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
				<p style="color: red;" align="center">${msg}</p>
				<div class="col-md-12 card" id="addnewemp">

					<div class="body">
						<form action="${contextRoot}/days/edit/${editdep.getId()}"
							method="post">
							<!-- <div class="col-md-6">
								<div class="form-group">
									<div class="form-line">
									<label>LeaveType</label> <input type="text" name="type"
											id="type" class="form-control"  required="required" />


									</div>
								</div>
							</div>
							 -->
							<%--  <div class="col-md-3 padding_col">
									<div class="form-group">
										<select id="leavetype" name="leavetype"
											class="form-control" size="1">
											<option value="">Select TypeOfLeave</option>
											<c:forEach items="${tblday}" var="leavetype">
												<option value="${leavetype.getId()}">
													${leavetype.getLeavetype()}${leavetype.getId()}</option>
											</c:forEach>
										</select>
									</div>
								</div> --%>
								<div class="col-md-6"><div class="form-group">
                                                <div class="form-line">
                                                    <label>Leave Types</label>
                                                   <select id="leavetype" name="leavetype"
											class="form-control" size="1">
                                                        <option value="">Select LeaveType</option>
                                                       <c:forEach items="${tblday}" var="leavetype">
												<option value="${leavetype.getId()}">
													${leavetype.getLeavetype()}</option>
											</c:forEach>
                                                    </select>
                                                </div>
                                            </div></div>
							<div class="col-md-6">
								<div class="form-group">
									<div class="form-line">
									<label>NumberOfDays</label> <input type="number" name="count"
											id="count" class="form-control"  required="required" />


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
						
							<!-- <div class="col-md-6">
								<div class="form-group">
									<div class="form-line">
										<label>LeaveType </label> <input type="text" name="ltype"
											id="ltype" required="required" class="form-control" />

									</div>
								</div>
							</div>
							<div class="col-md-6">
								<div class="form-group">
									<div class="input-group addon-line">
										<div class="form-line">
											<label>NumberofDays</label> <input type="date" name="hdate"
												id="hdate" class="form-control" placeholder="Holiday Date"
												required="required">
										</div>
										<span class="input-group-addon"><i
											class="material-icons">date_range </i></span>
									</div>
								</div>

							</div> -->

							<div class="col-md-12 col-sm-12 col-xs-12" style="text-align: center;">
								<button type="submit"
									class="btn btn-primary btn-rounded waves-effect">Update
									</button>
							</div>
						</form>
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


	<!-- 	<script>
		$(document).ready(function() {

			$("#addnewemp").css("display", "none");

			$("#addnew").click(function() {

				$("#addnewemp").css("display", "block");
				$("#addnewemp").addClass("animated bounce");
				$("this").css("display", "none")

			});

			$(".close").click(function() {
				alert("close!");
				$("#addnewemp").css("display", "none");
				$("#addnewemp").addClass("animated");

			});

		});
	</script>

 -->
	<script>
	$("#leavetype").val("${editdep.getLeavetypeid()}");
	document.getElementById("count").value = "${editdep.getNumleavedays()}";
	var active = "${editdep.isIsactive()}";
	if(active == "true")
	$('#active1').prop('checked', true);
	else
		$('#active').prop('checked', true);	
	holiday.focus(); 
	
	</script>


</body>

</html>