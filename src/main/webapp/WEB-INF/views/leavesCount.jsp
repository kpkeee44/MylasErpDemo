<%-- <%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@page import="java.util.List"%>
<%@page import="mylas.com.erp.demo.*"%>
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
<link rel="icon" href="../../resources/images/favicon.png"
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

<!--REQUIRED THEME CSS -->
<link href="${css}/style.css" rel="stylesheet">
<link href="${css}/layout.css" rel="stylesheet">
<link href="${css}/themes/main_theme.css" rel="stylesheet" />
<link href="${css}/custom_style.css" rel="stylesheet">
<link href="${css}/main.css" rel="stylesheet">

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
						<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12 topbar">

							<div class="col-md-9">
								<div class="row pagetitle">
									<span> Leave Additions </span>
								</div>
							</div>
						</div>

						
						
					</div>

					<div class="row clearfix"></div>
	<div class="card" style="padding: 5px;">
					<form action="${contextRoot}/admin/leavedays/register" method="post">
					
							<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12 search_leav">
								
								
								<div class="col-md-3 padding_col">
									<div class="form-group">
										<select id="department1" name="department1"
											class="form-control" size="1">
											<option value="">Select TypeOfLeave</option>
											<c:forEach items="${departments}" var="departments">
												<option value="${departments.getLeavetype()}">
													${departments.getLeavetype()}</option>
											</c:forEach>
										</select>
									</div>
								</div>
								<div class="col-md-2 padding_col">
									<div class="form-group">
										<input type="number" class="form-control" id="count"
											placeholder="Numbers Of Days" name="count" value=""/>
									</div>
								</div>

								<div class="col-md-2 ">
									<button type="submit" onclick="return Search()"
										class="btn btn-primary  pull-right waves-effect ">Submit</button>
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
												data-tablesaw-priority="persist">LeaveType</th>
											<th data-tablesaw-sortable-col data-tablesaw-priority="4">Number Of Leave
											</th>
											<th data-tablesaw-sortable-col data-tablesaw-priority="1"
												class="actiontabel">Update</th>
										</tr>
									</thead>
									<div class="clearfix"></div>

									<c:forEach items="${empleave}" var="empleaveslist">
										<tbody>


											<tr>
												
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

	<!-- LAYOUT JS -->
	<script src="${js}/demo.js"></script>
	<script src="${js}/layout.js"></script>
	<script src="${js}/jquery.1.10.2.min.js"></script>
	<script src="${js}/jquery.autocomplete.min.js"></script>

	<script>
		$(document).ready(function() {

			showdiv();
			$("#detailview").click(function() {
				$("#detail").css("display", "block");
				$("#list").css("display", "none");
			});

			$("#listview").click(function() {
				$("#detail").css("display", "none");
				$("#list").css("display", "block");
			});

		});

		function showdiv() {
			$("#gi").css("display", "block");
			$("#list").css("display", "none");
		}
		function Validate() {
			var password = document.getElementById("pswd").value;
			var confirmPassword = document.getElementById("cpswd").value;
			var ph = document.getElementById("phone").value;
			if (password != confirmPassword) {
				alert("Passwords do not match.");
				pswd.focus();
				return false;
			}

			if ((ph.length < 10) || ph.length > 10) {
				alert("Invalid Mobile Number");
				phone.focus();
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
	 var fname = document.getElementById("firstname").value;
	 var lname = document.getElementById("lastname").value;
	 var mon = document.getElementById("department1").value;
	 var sta = document.getElementById("designation1").value;
	 if(fname=="" && lname=="" && mon=="" && sta=="")
		 {
		 alert("plese Select any one of these");
		 return false;
		 }

}
     </script>

	<script>
	$(document).ready(function() {

		$('#firstname').autocomplete({
			
			serviceUrl: '${contextRoot}/getTags',
			paramName: "firstname",
			delimiter: ",",
		    transformResult: function(response) {
		    	
		        return {
		        	
		            suggestions: $.map($.parseJSON(response), function(item) {
		            	
		                return { value: item.fname, data: item.id };
		            })
		            
		        };
		        
		    }
		    
		});
		
		
	});
	</script>
	<script>
	$(document).ready(function() {

		$('#lastname').autocomplete({
			
			serviceUrl: '${contextRoot}/lastNames',
			paramName: "lastname",
			delimiter: ",",
		    transformResult: function(response) {
		    	
		        return {
		        	
		            suggestions: $.map($.parseJSON(response), function(item) {
		            	
		                return { value: item.lname, data: item.id };
		            })
		            
		        };
		        
		    }
		    
		});
		
		
	});
	</script>
	
	<script>
$(document).ready(function(){
    $("button").click(function(){
        $("h4").hide();
    });
});
</script>
	
</body>

</html> --%>

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
										<span> Leave Additions  </span>
									</div>
								</div>
									<c:if test="${msgup.equals('HoliDay UpDated Successfully')}">
									<h4 style="color: green;">${msgup}</h4>
								</c:if>
								<c:if test="${msgup.equals('HoliDay is notUpdated.Please try Again')}">
									<h4 style="color: red;">${msgup}</h4>
									</c:if>
							</div>

						</div>
					</div>
				</div>
                      
                      
                        <td ><c:if test="${msg.equals('LeaveType added successfully!....')}">
									<h4 style="color: green;">${msg}</h4>
								</c:if></td>
						<td><c:if test="${msg.equals('LeaveType already exists')}">
									<h4 style="color: red;">${msg}</h4>
								</c:if></td>
								
				

					
					<div class="body">
						<form action="${contextRoot}/admin/leavecount/register"
							method="post">
								<div class="col-md-3 padding_col">
									<div class="form-group">
										<select id="department1" name="department1"
											class="form-control" size="1">
											<option value="">Select TypeOfLeave</option>
											<c:forEach items="${departments}" var="departments">
												<option value="${departments.getLeavetype()}">
													${departments.getLeavetype()}</option>
											</c:forEach>
										</select>
									</div>
								</div>
							
							<div class="col-md-6">
								<div class="form-group">
									<div class="form-line">
									 <input type="number" name="count"
											id="count" class="form-control"  placeholder="NumberOfDays" required="required" />


									</div>
								</div>
							</div>
							
							<div style="text-align: center;">
								<button type="submit"
									class="btn btn-primary btn-rounded waves-effect">Submit
									</button>
							</div>
						</form>
					</div>

				
				<div class="row clearfix">


					<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">



						<!-- Table Kitchen Sink -->
						<div class="card">

							<div class="body">
								<table class="tablesaw table-striped table-bordered table-hover">
									<thead class="tableheding">
										<tr>
											<th data-tablesaw-sortable-col data-tablesaw-priority="3">LeaveType
											</th>
											<th data-tablesaw-sortable-col data-tablesaw-priority="2">TotelDays
												</th>
											
											<th data-tablesaw-sortable-col data-tablesaw-priority="1"
												class="actiontabel">Action</th>

										</tr>
									</thead>
									<div class="clearfix"></div>
									<tbody>
										<c:forEach items="${DaysList}" var="days">
											<tr>
												<td>${days.getLeavetype()}</td>
												<td>${days.getNumleavedays()}</td>
												<td>
													<ul class="tabelaction">
														<li class="dropdown"><a href="javascript:void(0);"
															class="dropdown-toggle" data-toggle="dropdown"
															role="button" aria-haspopup="true" aria-expanded="false">
																<i class="material-icons">more_vert</i>
														</a>
															<ul class="dropdown-menu pull-right">
																<li><a
																	href="${contextRoot}/days/edit/${days.getId()}"><i
																		class="material-icons">edit</i>Update</a></li>
																
													
													
													<!-- <ul class="dropdown-menu pull-right"> -->
																<li><a
																	href="${contextRoot}/admin/days/delete/${days.getId()}"><i
																		class="material-icons">delete</i>Delete</a></li>
																
													</ul></li>
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


	<!-- <script>
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
	</script>  -->

 

 <script>
$(document).ready(function(){
    $("button").click(function(){
        $("h4").hide();
    });
});
</script>



</body>

</html>