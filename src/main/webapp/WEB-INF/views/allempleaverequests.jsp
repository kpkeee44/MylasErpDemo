<%-- <%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<spring:url var="css" value="/resources/css" />
<spring:url var="js" value="/resources/js" />
<spring:url var="images" value="/resources/images" />
<spring:url var="plugins" value="/resources/plugins" />
<c:set var="contextRoot" value="${pageContext.request.contextPath}" /> --%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
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

<link href="${css}/main.css" rel="stylesheet">
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
										<span>All Leave Request </span>
									</div>
								</div>

							</div>

						</div>
					</div>
				</div>

				<div class="row clearfix">



					<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12 ">



						<!-- Table Kitchen Sink -->
						<div class="card">
							<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12 search_leav">
								<form action="${contextRoot}/${role}/search/register"
									method="post">
									<div class="col-md-2 padding_col">

										<div class="form-group">
											<input type="text" id="firstname" value=""
												class="form-control" placeholder="First Name"
												name="firstname" />
										</div>
									</div>
									<div class="col-md-2 padding_col">

										<div class="form-group">

											<input type="text" id="lastname" value=""
												class="form-control" placeholder="Last Name" name="lastname" />
										</div>
									</div>
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
									<!-- <div class="col-md-2 padding_col">
										<div class="form-group">

											<select class="form-control" size="1" name="status"
												id="status">
												<option value="">Select Status</option>
												<option value="2">Pending</option>
												<option value="1">Approved</option>
												<option value="0">Declined</option>
											</select>
										</div>
									</div> -->


									<div class="col-md-2 ">
										<button type="submit" onclick="return Search()"
											class="btn btn-primary  pull-right waves-effect ">
											Search</button>
									</div>
								</form>
							</div>
							<div class="body">
								<p style="color: green;">${UMsg}</p>
								<table class="tablesaw table-striped table-bordered table-hover">
									<thead class="tableheding">
										<tr>
											<th data-tablesaw-sortable-col
												data-tablesaw-sortable-default-col
												data-tablesaw-priority="persist">Employee 
												</th>
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


										</tr>
									</thead>
									<div class="clearfix"></div>
									<c:if test="${Role.equals('ADMIN_ROLE')}">
										<c:set var="role" value="admin" />
									</c:if>

									<c:if test="${Role.equals('MANAGER_ROLE')}">
										<c:set var="role" value="manager" />
									</c:if>

									<c:if test="${Role.equals('EMPLOYEE_ROLE')}">
										<c:set var="role" value="employee" />
									</c:if>
									<c:forEach items="${empleaves}" var="empleaves">
										<%-- <c:if test="${User.getRole().equals('MANAGER_ROLE')}">
										<c:if test="${empleaveslist.getReferenceid()!=0}">
											<c:set var="user" value="${User.getEid()}" />
											<c:if test="${empleaveslist.getEmployeeid() != user}">
												<c:if
													test="${empleaveslist.getManagerid()==user || empleaveslist.getMantrans() == user}">
														
													<tbody>
														<tr>
															<td>
																<div class="chip">
																	<img src="${images}/mail/one.jpg" alt="Contact Person">
																	<span>${empleaveslist.getEmployeeid()}</span>
																	<div style="text-align: center"></div>
																	<c:if test="${empleaveslist.getReferenceid()>0}">
																		<button style="border: none; background: none;"
																			id="${empleaveslist.getReferenceid()}"
																			data-target="#leavehistory" data-toggle="modal"
																			onclick="displayhistory(this.id)">
																			<i class="material-icons">bubble_chart</i>
																		</button>
																	</c:if>
																</div>
															 <c:if test="${empleaveslist.getReferenceid()>0}">
																<button style="border: none; background: none;"
																	id="${empleaveslist.getReferenceid()}"
																	data-target="#leavehistory" data-toggle="modal"
																	onclick="displayhistory(this.id)">
																	<i class="material-icons">bubble_chart</i>
																</button>
															</c:if>
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
																			class="btn btn-primary  btn-outline btn-rounded waves-effect colorred"
																			data-toggle="dropdown">Declined</button>
																		<ul class="dropdown-menu pull-right">
																			<li><a
																				href="${contextRoot}/${role}/leavereq/edit/${empleaveslist.getId()}">
																					Edit</a></li>

																		</ul>
																	</c:if>
																	<c:if test="${empleaveslist.getStatus() == true}">
																		<button type="button"
																			class="btn btn-primary colorgreen btn-outline btn-rounded waves-effect "
																			aria-haspopup="true" aria-expanded="false"
																			data-toggle="dropdown">Approved</button>
																		<ul class="dropdown-menu pull-right">
																			<li><a
																				href="${contextRoot}/${role}/leavereq/edit/${empleaveslist.getId()}">
																					Edit</a></li>
																		</ul>
																	</c:if>
																	<c:set var="eid" value="${User.getEid()}"></c:set>
																	<c:if
																		test="${empleaveslist.getManagerid() == eid || empleaveslist.getMantrans() == eid}">
																		<c:if test="${empleaveslist.getStatus() == null}">

																			<ul class="dropdown-menu bullet">

																				<li>
																					<button type="button"
																						class="btn btn-primary  btn-outline btn-rounded waves-effect colorred"
																						data-toggle="modal" data-target="#longmodal1"
																						id="${empleaveslist.getId()}"
																						onclick="display(this.id)">Declined</button>

																				</li>
																				<li><button type="button"
																						class="btn btn-primary colorgreen btn-outline btn-rounded waves-effect"
																						data-toggle="modal" data-target="#longmodal"
																						id="${empleaveslist.getId()}"
																						onclick="display(this.id)">Approved</button></li>

																			</ul>
																		</c:if>
																	</c:if>
																</div>
															</td>


														</tr>

													</tbody>
												</c:if>
												</c:if>
											</c:if>
										</c:if> --%>
										<c:if test="${User.getRole().equals('ADMIN_ROLE')}">
										<%-- 	<c:if test="${empleaveslist.getReferenceid()!=0}"> --%>



												<tbody>
													<tr>
														<td>
															<div class="chip">

																<img src="${images}/mail/one.jpg" alt="Contact Person">
																<span>${empleaves.getEmployeeid()}</span>
																<div style="text-align: center"></div>
															</div> <%-- <c:if test="${empleaveslist.getReferenceid()>0}">
																<button style="border: none; background: none;"
																	id="${empleaveslist.getReferenceid()}"
																	data-target="#leavehistory" data-toggle="modal"
																	onclick="displayhistory(this.id)">
																	<i class="material-icons">bubble_chart</i>
																</button>
															</c:if> --%>
														</td>
														<td>${empleaves.getLeavetype()}</td>
														<td>${empleaves.getFromdate()}</td>
														<td>${empleaves.getTodate()}</td>
														<td>${empleaves.getLeavecount()}days</td>
														<td>${empleaves.getLeavereason()}</td>
<td><div class="col-md-6 padding_col">
													<div class="form-group">

														<select class="form-control" id="status1" name="status"
															size="1">
															<option value="">Select Status</option>
															<c:forEach items="${ltypelist}" var="ltypelist">
												<option value="${ltypelist.getLeavestatusid()}">
													${ltypelist.getLeavestatus()}</option>
											</c:forEach>
														</select>
													</div>
												</div></td>
													<%-- 	<td>

															<div class="btn-group">

																<c:if test="${empleaves.getLeavestatus() == 'Hold'}">
																	<button type="button"
																		class="btn btn-primary btn-outline btn-rounded waves-effect"
																		data-toggle="dropdown" aria-haspopup="true"
																		aria-expanded="false">Pending</button>
																</c:if>
																<c:if test="${empleaves.getLeavestatus() == 'Denied'}">
																	<button type="button"
																		class="btn btn-primary  btn-outline btn-rounded waves-effect colorred"
																		data-toggle="dropdown" aria-haspopup="true"
																		aria-expanded="false">Declined</button>
																	<ul class="dropdown-menu pull-right">
																		<li><a
																			href="${contextRoot}/admin/leavereq/edit/${empleaveslist.getId()}">
																				Edit</a></li>


																	</ul>
																</c:if>
																<c:if test="${empleaveslist.getLeavestatus() == 'Approved'}">
																	<button type="button"
																		class="btn btn-primary colorgreen btn-outline btn-rounded waves-effect "
																		data-toggle="dropdown" aria-haspopup="true"
																		aria-haspopup="true" aria-expanded="false">Approved
																	</button>
																	<ul class="dropdown-menu pull-right">
																		<li><a
																			href="${contextRoot}/admin/leavereq/edit/${empleaveslist.getId()}">
																				Edit</a></li>


																	</ul>
																</c:if>
																<c:set var="eid" value="${User.getEid()}"></c:set>
																<c:if
																	test="${empleaveslist.getManagerid() == eid || empleaveslist.getMantrans() == eid}">
																	<c:if test="${empleaveslist.getLeavestatus() == null}">

																		<ul class="dropdown-menu bullet">


																			<li>
																				<button type="button"
																					class="btn btn-primary  btn-outline btn-rounded waves-effect colorred"
																					data-toggle="modal" data-target="#longmodal1"
																					id="${empleaveslist.getId()}"
																					onclick="display(this.id)">Declined</button>

																			</li>
																			<li><button type="button"
																					class="btn btn-primary colorgreen btn-outline btn-rounded waves-effect"
																					data-toggle="modal" data-target="#longmodal"
																					id="${empleaveslist.getId()}"
																					onclick="display(this.id)">Approved</button>
																				</button></li>

																		</ul>
																	</c:if>
																	
																	</div>
																	</td> --%>
																	</tr>
																	</tbody>
																</c:if>
														</c:forEach>


								</table>
							</div>
						</div>
						<!-- #END# Kitchen Sink -->


					</div>
				</div>
			</div>
			<div class="md-overlay custom-overlay"></div>
			<div class="modal fade" id="longmodal" tabindex="-1" role="dialog"
				aria-labelledby="myModalLabel" aria-hidden="true">
				<div class="modal-dialog">
					<div class="modal-content">
						<div class="modal-header">
							<button type="button" class="close" data-dismiss="modal"
								aria-label="Close">
								<span aria-hidden="true">x</span>
							</button>
							<h4 class="modal-title" id="myModalLabel4">Approve Leave
								Request</h4>
						</div>
						<div class="modal-body">
							<div class="long-modal">
								<form id="form_a" method="post">
									<div class="col-md-6">
										<div class="form-group">
											<div class="form-line">
												<label>Approve Reason* </label>
												<textarea name="reason" id="reason" class="form-control"
													rows="3" required="required"></textarea>
											</div>
										</div>

									</div>
									<div class="clearfix"></div>
									<div style="text-align: center;">
										<button type="submit"
											class="btn btn-primary btn-rounded waves-effect">Edit
										</button>
									</div>


								</form>
							</div>
						</div>

					</div>
				</div>
			</div>

			<div class="md-overlay custom-overlay"></div>
			<div class="modal fade" id="longmodal1" tabindex="-1" role="dialog"
				aria-labelledby="myModalLabel" aria-hidden="true">
				<div class="modal-dialog">
					<div class="modal-content">
						<div class="modal-header">
							<button type="button" class="close" data-dismiss="modal"
								aria-label="Close">
								<span aria-hidden="true">x</span>
							</button>
							<h4 class="modal-title" id="myModalLabel4">Decline Leave
								Request</h4>
						</div>
						<div class="modal-body">
							<div class="long-modal">
								<form id="form_d" method="post">
									<div class="col-md-6">
										<div class="form-group">
											<div class="form-line">
												<label>Decline Reason* </label>
												<textarea name="reason" id="reason" class="form-control"
													rows="3" required="required"></textarea>
											</div>
										</div>

									</div>
									<div class="clearfix"></div>
									<div style="text-align: center;">
										<button type="submit"
											class="btn btn-primary btn-rounded waves-effect">Decline</button>
									</div>


								</form>
							</div>
						</div>

					</div>
				</div>
			</div>
			<div class="modal fade" id="leavehistory" tabindex="-1" role="dialog"
				aria-labelledby="myModalLabel" aria-hidden="true"
				style="display: none">
				<div class="modal-dialog" style="width: 70%;">
					<div class="modal-content">
						<div class="modal-header">
							<button type="button" class="close" data-dismiss="modal"
								aria-label="Close">
								<span aria-hidden="true">x</span>
							</button>
							<h4 class="modal-title" id="myModalLabel4">Employee Leave
								History</h4>
						</div>
						<div class="modal-body">
							<div class="long-modal">

								<div class="body">
									<table
										class="tablesaw table-striped table-bordered table-hover"
										id="result">
										<thead class="tableheding">
											<div class="demo timeline-block">
												<div class="row">
													<div class="col-md-1 col-md-offset-3"></div>
												</div>
												<div class="time-bar"></div>
												<div class="row">
													<div class="col-md-2 col-md-offset-1">
														<div class="timeline-date" id="frommonthandyear"></div>
													</div>
													<div class="col-md-1">
														<div class="timeline2-icon bg-indigo">
															<i class="material-icons">flag</i>
														</div>
													</div>
													<div class="col-md-6">
														<div class="timeline-hover">
															<div class="timeline-heading bg-indigo">
																<div class="timeline-arrow arrow-indigo"></div>
																Peding Leave Request
															</div>
															<div class="timeline-content">

																<div id="leaverequestcount"></div>
																<div id="leaverequestfrom"></div>

																<p id="leaverequestto">
																<p id="leaveststusbyadmin">


																	<!--  <p id="leaverequestinitialststus"></p> -->
															</div>


														</div>
													</div>
												</div>
												<div class="row">
													<div class="col-md-2 col-md-offset-1">
														<!-- <div class="timeline-date">
                                                    14-Mar-2017,
                                                    <br>Sat 16:30
                                                </div> -->
													</div>
													<div class="col-md-1">
														<div class="timeline2-icon bg-red">
															<i class="material-icons">chat</i>
														</div>
													</div>
													<div class="col-md-6">
														<div class="timeline-hover">
															<div class="timeline-heading bg-red" id="leavemanagerid">
																<div class="timeline-arrow arrow-red"></div>

															</div>
															<div class="timeline-comment">
																<p id="leavemanagerststus"></p>
																<p id="leavemanreason"></p>

															</div>
														</div>
													</div>
												</div>
												<c:if test="${empleaveslist.getReferenceid() == 1}"></c:if>
												<div class="row">
													<div class="col-md-2 col-md-offset-1"></div>
													<div class="col-md-1">
														<div class="timeline2-icon bg-green">
															<i class="material-icons">chat</i>
														</div>
													</div>
													<div class="col-md-6">
														<div class="timeline-hover">
															<div class="timeline-heading bg-green">
																<div class="timeline-arrow arrow-red">
																</div>

															</div>
															<div class="timeline-comment">
																<p id="leavemanagerststus"></p>
																<p id="leavemanreason"></p>

															</div>
														</div>
													</div>
												</div>
											</div>

										</thead>
									</table>
								</div>
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
	<script src="${js}/jquery.1.10.2.min.js"></script>
	<script src="${js}/jquery.autocomplete.min.js"></script>


	<!-- <script
		src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script> -->

	<script>
		function Search() {
			var fname = document.getElementById("firstname").value;
			var lname = document.getElementById("lastname").value;
			var mon = document.getElementById("month").value;
			var sta = document.getElementById("status").value;
			if (fname == "" && lname == "" && mon == "" && sta == "") {
				alert("plese Select any one of these");
				return false;
			}

		}

	</script>
<script>
$("#status1").val(4);
</script>



	<script>
		$(document).ready(function() {

			$(".tr").css("display", "none");

			$("#star").click(function() {
				alert("plese Select any one of these");
				$(".tr").css("display", "inline-block");
				alert("plese Select any one of these2");
				$(".tr").addClass("animated bounce");

			});

		});
	</script>


	<script type="text/javascript">
		function display(id) {
			document.getElementById('form_d').action = "${contextRoot}/${role}/leave/decline/"
					+ id;
			document.getElementById('form_a').action = "${contextRoot}/${role}/leave/approve/"
					+ id;
		}
	</script>

	<script type="text/javascript">
		function leveHistory(id) {
			window.location
					.replace("${contextRoot}/${role}/leaverequests/register?leavehistory="
							+ id);

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
		            	
		                return { value: item.emplfirstname, data: item.id };
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
		            	
		                return { value: item.empllastname, data: item.id };
		            })
		            
		        };
		        
		    }
		    
		});
		
		
	});
	</script>
	<script type="text/javascript">
		function displayhistory(id) {

			$
					.ajax({
						type : "POST",
						url : "${contextRoot}/leavehistory",
						data : "id=" + id,
						cache : false,
						success : function(response) {
							//document.getElementById("result").value = response;

							$('#result').html("");
							/*  $('#leaveststusbyadmin').html("Was Sent to :"); */
							$('#frommonthandyear').html("");
							/*  $('#leaverequestinitialststus').html(""); */
							/*  $('#leaverequestfrom').html("From :"); */
							/* $('#leaverequestto').html("To :"); */
							$('#leaverequestcount')
									.html("A Leave Request of :");
							$('#leavemanagerid').html("");
							$('#leavemanagerststus').html(
									"Leave Request Was : ");
							$('#leavemanreason').html("With The Reason : ");

							/* $('#result')
									.html(
											"<tr><th>Employee</th><th data-tablesaw-sortable-col data-tablesaw-priority='3'>Leave Type</th><th data-tablesaw-sortable-col data-tablesaw-priority='2'>From</th><th data-tablesaw-sortable-col data-tablesaw-priority='4'>To</th><th data-tablesaw-sortable-col data-tablesaw-priority='4'>No of Days</th><th data-tablesaw-sortable-col data-tablesaw-priority='2'>Leave Reason</th><th data-tablesaw-sortable-col data-tablesaw-priority='2'>Manager Remark</th><th data-tablesaw-sortable-col data-tablesaw-priority='4'>Status</th></tr>"); */
							//var obj = JSON.parse(response);
							var stat = response.status;

							if (stat == true) {
								var ststus = '<button type="button" class="btn btn-primary colorgreen btn-outline btn-rounded waves-effect " data-toggle="dropdown" aria-haspopup="true" aria-haspopup="true" aria-expanded="false">Approved</button>';
							} else if (stat == false) {
								var ststus = '<button type="button" class="btn btn-primary colorred btn-outline btn-rounded waves-effect " data-toggle="dropdown" aria-haspopup="true" aria-haspopup="true" aria-expanded="false">Declined</button>';
							}

							$('#frommonthandyear').append(response.fromdate);
							/* $('#leaveststusbyadmin').append(response.managerid); */
							/* $('#leaverequestinitialststus').append(ststus); */
							/* $('#leaverequestfrom').append(response.fromdate);
							$('#leaverequestto').append(response.fromdate); */
							$('#leaverequestcount').append(
									+response.count + " Days From : "
											+ response.fromdate + "<br> To :"
											+ response.todate
											+ " Was Sent to : "
											+ response.managerid);
							$('#leavemanagerid').append(response.managerid);
							$('#leavemanagerststus').append(
									ststus + " by " + response.managerid);
							$('#leavemanreason').append(response.reason);

							/* $('#result').append(
									'<td style="border: 1px solid #eee"><div class="chip"><img src="${images}/mail/one.jpg" style="width: 32px;height: 32px;" alt="Contact Person"><span>' + response.employeeid + '</span><div style="text-align: center"></div></div><td style="border: 1px solid #eee">'
											+ response.leavetype + '<td style="border: 1px solid #eee">'
											+ response.fromdate + '<td style="border: 1px solid #eee">'
											+ response.todate + '<td style="border: 1px solid #eee">'
											+ response.count + ' Days<td style="border: 1px solid #eee">'
											+ response.leavereason + '<td style="border: 1px solid #eee">'
											+ response.reason + '<td style="border: 1px solid #eee">'
											+ ststus); */

						},

					});

		};
	</script>


</body>

</html>