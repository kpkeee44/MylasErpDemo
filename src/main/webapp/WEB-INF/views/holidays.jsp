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
										<span> Holidays </span>
									</div>
								</div>
									<c:if test="${msgup.equals('HoliDay UpDated Successfully')}">
									<h4 style="color: green;">${msgup}</h4>
								</c:if>
								<c:if test="${msgup.equals('HoliDay is notUpdated.Please try Again')}">
									<h4 style="color: red;">${msgup}</h4>
									</c:if>

                                 


								<div class="col-md-6">
									<div class="row pagetitle">
										<span class="pull-right">
											<button type="button" id="addnew"
												class="btn btn-primary btn-rounded waves-effect">Add
												New Holiday</button>
										</span>
									</div>
								</div>


								<div></div>


							</div>

						</div>
					</div>
				</div>
                      
                      
                        <td ><c:if test="${msg.equals('Holiday added successfully!....')}">
									<h4 style="color: green;">${msg}</h4>
								</c:if></td>
						<td><c:if test="${msg.equals('Holiday already exists')}">
									<h4 style="color: red;">${msg}</h4>
								</c:if></td>
								
				<div class="col-md-12 card" id="addnewemp">

					<button type="button" class="close" aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
					<div class="body">
						<form action="${contextRoot}/admin/empholidays/register"
							method="post">
							<div class="col-md-6">
								<div class="form-group">
									<div class="form-line">
										<label>Holiday Name </label> <input type="text" name="holiday"
											id="holiday" class="form-control" required="required" />


									</div>
								</div>
							</div>
							<div class="col-md-6">
								<div class="form-group">
									<div class="input-group addon-line">
										<div class="form-line">
											<label>Holiday Date</label> <input type="date" name="hdate"
												id="hdate" class="form-control" placeholder="Holiday Date"
												required="required">
										</div>
										<span class="input-group-addon"><i
											class="material-icons">date_range </i></span>
									</div>
								</div>

							</div>

							<div style="text-align: center;">
								<button type="submit"
									class="btn btn-primary btn-rounded waves-effect">Create
									Holiday</button>
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
											<th data-tablesaw-sortable-col data-tablesaw-priority="3">Title
											</th>
											<th data-tablesaw-sortable-col data-tablesaw-priority="2">Holiday
												Date</th>
											<th data-tablesaw-sortable-col data-tablesaw-priority="4">Day
											</th>
											<th data-tablesaw-sortable-col data-tablesaw-priority="1"
												class="actiontabel">Action</th>

										</tr>
									</thead>
									<div class="clearfix"></div>
									<tbody>
										<c:forEach items="${HolidaysList}" var="Holiday">
											<tr>
												<td>${Holiday.getHolidayname()}</td>
												<td>${Holiday.getHolidaydate()}</td>
												<td id="day${Holiday.getHolidayid()}"></td>
												<td>
													<ul class="tabelaction">
														<li class="dropdown"><a href="javascript:void(0);"
															class="dropdown-toggle" data-toggle="dropdown"
															role="button" aria-haspopup="true" aria-expanded="false">
																<i class="material-icons">more_vert</i>
														</a>
															<ul class="dropdown-menu pull-right">
																<li><a
																	href="${contextRoot}/holidays/edit/${Holiday.getHolidayid()}"><i
																		class="material-icons">edit</i>Edit</a></li>
																<%-- <li><a Onclick="return ConfirmDelete()"
																href="${contextRoot}/holidays/delete/${Holiday.getHolidayid()}"><i
																		class="material-icons">delete</i>Delete</a></li> --%>

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

	<c:forEach items="${HolidaysList}" var="Holiday">
		<script type="text/javascript">
		var input = '${Holiday.getHolidaydate()}';
var fields = input.split('-');
		
var date = fields[2];
var month1 = fields[1];
var year = fields[0];

switch (month1) {
case "01":
	month = "0";
    break;
case "02":
	month = "1";
    break;
case "03":
	month = 2;
    break;
case "04":
	month = "3";
    break;
case "05":
	month = "4";
    break;
case "06":
	month = 5;
	break;
case "07":
	month = "6";
	break;
case "08":
	month = "7";
	break;
case "09":
	month = "8";
	break;
case "10":
	month = "9";
	break;
case "11":
	month = "10";
	break;
case "12":
	month = "11";
}

var date = new Date(year, month, date);

var day1 = date.getDay();

switch (day1) {
case 0:
    day = "Sunday";
    break;
case 1:
    day = "Monday";
    break;
case 2:
    day = "Tuesday";
    break;
case 3:
    day = "Wednesday";
    break;
case 4:
    day = "Thursday";
    break;
case 5:
    day = "Friday";
    break;
case 6:
    day = "Saturday";
}

 document.getElementById('day'+'${Holiday.getHolidayid()}').innerHTML = day; 

</script>

	</c:forEach> 

 <script>
$(document).ready(function(){
    $("button").click(function(){
        $("h4").hide();
    });
});
</script>
<script type="text/javascript">
function ConfirmDelete()
{
  var x = confirm("Are you sure you want to delete?");
  if (x)
      return true;
  else
    return false;
}
</script>

 
</body>

</html>