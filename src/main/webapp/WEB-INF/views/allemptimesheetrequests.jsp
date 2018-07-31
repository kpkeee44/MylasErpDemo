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

						<div class="">


							<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12 topbar">

								<div class="col-md-6">
									<div class="row pagetitle">
										<span>All TimeSheet Request </span>
									</div>
								</div>

							</div>

						</div>
					</div>
				</div>



				<div class="row clearfix">



					<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12 ">



						<!-- Table Kitchen Sink -->
						<form action="${contextRoot}/${role}/employee/timesheetSearch"
							method="post">
							<div class="card">
								<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12 search_leav">
									<div class="col-md-2 padding_col">
										<div class="form-group">

											<input type="text" class="form-control"
												placeholder="First Name" name="firstname" id="firstname" />
										</div>
									</div>
									<div class="col-md-2 padding_col">
										<div class="form-group">

											<input type="text" class="form-control"
												placeholder="Last Name" name="lastname" id="lastname" />
										</div>
									</div>
									<div class="col-md-2 padding_col">
										<div class="form-group">

											<select name="month" id="month" class="form-control" size="1">
												<option value="">Select Month</option>
												<option value="January">January</option>
												<option value="February">February</option>
												<option value="March">March</option>
												<option value="April">April</option>
												<option value="May">May</option>
												<option value="June">June</option>
												<option value="July">July</option>
												<option value="August">August</option>
												<option value="September">September</option>
												<option value="October">October</option>
												<option value="November">November</option>
												<option value="December">December</option>
											</select>
										</div>
									</div>
									<div class="col-md-2 padding_col">
										<div class="form-group">

											<select name="status" id="status" class="form-control"
												size="1">
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

							</div>
						</form>
						<div class="card">

							<div class="body" style="overflow-y: visible; overflow-x: scroll;height: 600px; ">
								<table class="tablesaw table-striped table-hover table-bordered">
										<thead class="tableheding">
														<tr>
															<th data-tablesaw-sortable-col
																data-tablesaw-sortable-default-col
																data-tablesaw-priority="persist">Month</th>
																<th data-tablesaw-sortable-col
																data-tablesaw-sortable-default-col
																data-tablesaw-priority="persist">Name</th>
															<th data-tablesaw-sortable-col data-tablesaw-priority="3">1</th>
															<th data-tablesaw-sortable-col data-tablesaw-priority="2">2</th>
															<th data-tablesaw-sortable-col data-tablesaw-priority="4">3
															</th>

															<th data-tablesaw-sortable-col data-tablesaw-priority="4">4</th>
															<th data-tablesaw-sortable-col data-tablesaw-priority="2">5</th>
															<th data-tablesaw-sortable-col data-tablesaw-priority="4">6</th>
															<th data-tablesaw-sortable-col data-tablesaw-priority="1">7
															</th>
															<th data-tablesaw-sortable-col data-tablesaw-priority="1">8
															</th>
															<th data-tablesaw-sortable-col data-tablesaw-priority="1">9
															</th>
															<th data-tablesaw-sortable-col data-tablesaw-priority="1">10
															</th>
															<th data-tablesaw-sortable-col data-tablesaw-priority="3">11</th>
															<th data-tablesaw-sortable-col data-tablesaw-priority="2">12</th>
															<th data-tablesaw-sortable-col data-tablesaw-priority="4">13
															</th>

															<th data-tablesaw-sortable-col data-tablesaw-priority="4">14</th>
															<th data-tablesaw-sortable-col data-tablesaw-priority="2">15</th>
															<th data-tablesaw-sortable-col data-tablesaw-priority="4">16</th>
															<th data-tablesaw-sortable-col data-tablesaw-priority="1">17
															</th>
															<th data-tablesaw-sortable-col data-tablesaw-priority="1">18
															</th>
															<th data-tablesaw-sortable-col data-tablesaw-priority="1">19
															</th>
															<th data-tablesaw-sortable-col data-tablesaw-priority="1">20
															</th>
															<th data-tablesaw-sortable-col data-tablesaw-priority="3">21</th>
															<th data-tablesaw-sortable-col data-tablesaw-priority="2">22</th>
															<th data-tablesaw-sortable-col data-tablesaw-priority="4">23
															</th>

															<th data-tablesaw-sortable-col data-tablesaw-priority="4">24</th>
															<th data-tablesaw-sortable-col data-tablesaw-priority="2">25</th>
															<th data-tablesaw-sortable-col data-tablesaw-priority="4">26</th>
															<th data-tablesaw-sortable-col data-tablesaw-priority="1">27
															</th>
															<th data-tablesaw-sortable-col data-tablesaw-priority="1">28
															</th>
															<th data-tablesaw-sortable-col data-tablesaw-priority="1">29
															</th>
															<th data-tablesaw-sortable-col data-tablesaw-priority="1">30
															</th>
															<th data-tablesaw-sortable-col data-tablesaw-priority="1">31
															</th>
															<th data-tablesaw-sortable-col
																data-tablesaw-sortable-default-col
																data-tablesaw-priority="persist" style="width: 11%;">STATUS
															</th>
															<th data-tablesaw-sortable-col
																data-tablesaw-sortable-default-col
																data-tablesaw-priority="persist">Edit</th>
														</tr>
													</thead>
									
										<c:if test="${User.getRole().equals('MANAGER_ROLE')}">
											<c:set var="user" value="${User.getEid()}" />
											<c:if
												test="${attlist.getManagerid() == user || attlist.getMantrans() == user}">
												<c:if test="${attlist.getEmpid() != user}">
													<c:if test="${attlist.getStatas() == null}">
													<tbody>
														<c:set var="green"
															value="icon-display  fa fa-check attenedance_check_green" />
														<c:set var="red"
															value="icon-display  fa fa-close  attenedance_check_red" />
														<c:set var="gray"
															value="icon-display  fa fa-bullseye attenedance_check_darkgrey" />



														<tr>
															<th><a class="hideOverflow"
																href="javascript:void(0)">${attlist.getEmpid()}</a></th>
															<th><a class="hideOverflow"
																href="javascript:void(0)">${attlist.getMonth()}
																	${attlist.getYear()}</a></th>
															<c:if test="${attlist.getDay1() == 1}">
																<td><i class="${green}"></i></td>
															</c:if>
															<c:if test="${attlist.getDay1() == 2}">
																<td><i class="${red}"></i></td>
															</c:if>
															<c:if test="${attlist.getDay1() == 3}">
																<td><i class="${gray}"></i></td>
															</c:if>
															<c:if test="${attlist.getDay2() == 1}">
																<td><i class="${green}"></i></td>
															</c:if>
															<c:if test="${attlist.getDay2() == 2}">
																<td><i class="${red}"></i></td>
															</c:if>
															<c:if test="${attlist.getDay2() == 3}">
																<td><i class="${gray}"></i></td>
															</c:if>
															<c:if test="${attlist.getDay3() == 1}">
																<td><i class="${green}"></i></td>
															</c:if>
															<c:if test="${attlist.getDay3() == 2}">
																<td><i class="${red}"></i></td>
															</c:if>
															<c:if test="${attlist.getDay3() == 3}">
																<td><i class="${gray}"></i></td>
															</c:if>

															<c:if test="${attlist.getDay4() == 1}">
																<td><i class="${green}"></i></td>
															</c:if>
															<c:if test="${attlist.getDay4() == 2}">
																<td><i class="${red}"></i></td>
															</c:if>
															<c:if test="${attlist.getDay4() == 3}">
																<td><i class="${gray}"></i></td>
															</c:if>

															<c:if test="${attlist.getDay5() == 1}">
																<td><i class="${green}"></i></td>
															</c:if>
															<c:if test="${attlist.getDay5() == 2}">
																<td><i class="${red}"></i></td>
															</c:if>
															<c:if test="${attlist.getDay5() == 3}">
																<td><i class="${gray}"></i></td>
															</c:if>
															<c:if test="${attlist.getDay6() == 1}">
																<td><i class="${green}"></i></td>
															</c:if>
															<c:if test="${attlist.getDay6() == 2}">
																<td><i class="${red}"></i></td>
															</c:if>
															<c:if test="${attlist.getDay6() == 3}">
																<td><i class="${gray}"></i></td>
															</c:if>
															<c:if test="${attlist.getDay7() == 1}">
																<td><i class="${green}"></i></td>
															</c:if>
															<c:if test="${attlist.getDay7() == 2}">
																<td><i class="${red}"></i></td>
															</c:if>
															<c:if test="${attlist.getDay7() == 3}">
																<td><i class="${gray}"></i></td>
															</c:if>
															<c:if test="${attlist.getDay8() == 1}">
																<td><i class="${green}"></i></td>
															</c:if>
															<c:if test="${attlist.getDay8() == 2}">
																<td><i class="${red}"></i></td>
															</c:if>
															<c:if test="${attlist.getDay8() == 3}">
																<td><i class="${gray}"></i></td>
															</c:if>
															<c:if test="${attlist.getDay9() == 1}">
																<td><i class="${green}"></i></td>
															</c:if>
															<c:if test="${attlist.getDay9() == 2}">
																<td><i class="${red}"></i></td>
															</c:if>
															<c:if test="${attlist.getDay9() == 3}">
																<td><i class="${gray}"></i></td>
															</c:if>
															<c:if test="${attlist.getDay10() == 1}">
																<td><i class="${green}"></i></td>
															</c:if>
															<c:if test="${attlist.getDay10() == 2}">
																<td><i class="${red}"></i></td>
															</c:if>
															<c:if test="${attlist.getDay10() == 3}">
																<td><i class="${gray}"></i></td>
															</c:if>
															<c:if test="${attlist.getDay11() == 1}">
																<td><i class="${green}"></i></td>
															</c:if>
															<c:if test="${attlist.getDay11() == 2}">
																<td><i class="${red}"></i></td>
															</c:if>
															<c:if test="${attlist.getDay11() == 3}">
																<td><i class="${gray}"></i></td>
															</c:if>
															<c:if test="${attlist.getDay12() == 1}">
																<td><i class="${green}"></i></td>
															</c:if>
															<c:if test="${attlist.getDay12() == 2}">
																<td><i class="${red}"></i></td>
															</c:if>
															<c:if test="${attlist.getDay12() == 3}">
																<td><i class="${gray}"></i></td>
															</c:if>
															<c:if test="${attlist.getDay13() == 1}">
																<td><i class="${green}"></i></td>
															</c:if>
															<c:if test="${attlist.getDay13() == 2}">
																<td><i class="${red}"></i></td>
															</c:if>
															<c:if test="${attlist.getDay13() == 3}">
																<td><i class="${gray}"></i></td>
															</c:if>
															<c:if test="${attlist.getDay14() == 1}">
																<td><i class="${green}"></i></td>
															</c:if>
															<c:if test="${attlist.getDay14() == 2}">
																<td><i class="${red}"></i></td>
															</c:if>
															<c:if test="${attlist.getDay14() == 3}">
																<td><i class="${gray}"></i></td>
															</c:if>
															<c:if test="${attlist.getDay15() == 1}">
																<td><i class="${green}"></i></td>
															</c:if>
															<c:if test="${attlist.getDay15() == 2}">
																<td><i class="${red}"></i></td>
															</c:if>
															<c:if test="${attlist.getDay15() == 3}">
																<td><i class="${gray}"></i></td>
															</c:if>
															<c:if test="${attlist.getDay16() == 1}">
																<td><i class="${green}"></i></td>
															</c:if>
															<c:if test="${attlist.getDay16() == 2}">
																<td><i class="${red}"></i></td>
															</c:if>
															<c:if test="${attlist.getDay16() == 3}">
																<td><i class="${gray}"></i></td>
															</c:if>
															<c:if test="${attlist.getDay17() == 1}">
																<td><i class="${green}"></i></td>
															</c:if>
															<c:if test="${attlist.getDay17() == 2}">
																<td><i class="${red}"></i></td>
															</c:if>
															<c:if test="${attlist.getDay17() == 3}">
																<td><i class="${gray}"></i></td>
															</c:if>
															<c:if test="${attlist.getDay18() == 1}">
																<td><i class="${green}"></i></td>
															</c:if>
															<c:if test="${attlist.getDay18() == 2}">
																<td><i class="${red}"></i></td>
															</c:if>
															<c:if test="${attlist.getDay18() == 3}">
																<td><i class="${gray}"></i></td>
															</c:if>
															<c:if test="${attlist.getDay19() == 1}">
																<td><i class="${green}"></i></td>
															</c:if>
															<c:if test="${attlist.getDay19() == 2}">
																<td><i class="${red}"></i></td>
															</c:if>
															<c:if test="${attlist.getDay19() == 3}">
																<td><i class="${gray}"></i></td>
															</c:if>
															<c:if test="${attlist.getDay20() == 1}">
																<td><i class="${green}"></i></td>
															</c:if>
															<c:if test="${attlist.getDay20() == 2}">
																<td><i class="${red}"></i></td>
															</c:if>
															<c:if test="${attlist.getDay20() == 3}">
																<td><i class="${gray}"></i></td>
															</c:if>
															<c:if test="${attlist.getDay21() == 1}">
																<td><i class="${green}"></i></td>
															</c:if>
															<c:if test="${attlist.getDay21() == 2}">
																<td><i class="${red}"></i></td>
															</c:if>
															<c:if test="${attlist.getDay21() == 3}">
																<td><i class="${gray}"></i></td>
															</c:if>
															<c:if test="${attlist.getDay22() == 1}">
																<td><i class="${green}"></i></td>
															</c:if>
															<c:if test="${attlist.getDay22() == 2}">
																<td><i class="${red}"></i></td>
															</c:if>
															<c:if test="${attlist.getDay22() == 3}">
																<td><i class="${gray}"></i></td>
															</c:if>
															<c:if test="${attlist.getDay23() == 1}">
																<td><i class="${green}"></i></td>
															</c:if>
															<c:if test="${attlist.getDay23() == 2}">
																<td><i class="${red}"></i></td>
															</c:if>
															<c:if test="${attlist.getDay23() == 3}">
																<td><i class="${gray}"></i></td>
															</c:if>
															<c:if test="${attlist.getDay24() == 1}">
																<td><i class="${green}"></i></td>
															</c:if>
															<c:if test="${attlist.getDay24() == 2}">
																<td><i class="${red}"></i></td>
															</c:if>
															<c:if test="${attlist.getDay24() == 3}">
																<td><i class="${gray}"></i></td>
															</c:if>
															<c:if test="${attlist.getDay25() == 1}">
																<td><i class="${green}"></i></td>
															</c:if>
															<c:if test="${attlist.getDay25() == 2}">
																<td><i class="${red}"></i></td>
															</c:if>
															<c:if test="${attlist.getDay25() == 3}">
																<td><i class="${gray}"></i></td>
															</c:if>
															<c:if test="${attlist.getDay26() == 1}">
																<td><i class="${green}"></i></td>
															</c:if>
															<c:if test="${attlist.getDay26() == 2}">
																<td><i class="${red}"></i></td>
															</c:if>
															<c:if test="${attlist.getDay26() == 3}">
																<td><i class="${gray}"></i></td>
															</c:if>
															<c:if test="${attlist.getDay27() == 1}">
																<td><i class="${green}"></i></td>
															</c:if>
															<c:if test="${attlist.getDay27() == 2}">
																<td><i class="${red}"></i></td>
															</c:if>
															<c:if test="${attlist.getDay27() == 3}">
																<td><i class="${gray}"></i></td>
															</c:if>
															<c:if test="${attlist.getDay28() == 1}">
																<td><i class="${green}"></i></td>
															</c:if>
															<c:if test="${attlist.getDay28() == 2}">
																<td><i class="${red}"></i></td>
															</c:if>
															<c:if test="${attlist.getDay28() == 3}">
																<td><i class="${gray}"></i></td>
															</c:if>
															<c:if test="${attlist.getDay29() == 1}">
																<td><i class="${green}"></i></td>
															</c:if>
															<c:if test="${attlist.getDay29() == 2}">
																<td><i class="${red}"></i></td>
															</c:if>
															<c:if test="${attlist.getDay29() == 3}">
																<td><i class="${gray}"></i></td>
															</c:if>
															<c:if test="${attlist.getDay29() == null}">
																<td><i class="${gray}"></i></td>
															</c:if>
															<c:if test="${attlist.getDay30() == 1}">
																<td><i class="${green}"></i></td>
															</c:if>
															<c:if test="${attlist.getDay30() == 2}">
																<td><i class="${red}"></i></td>
															</c:if>
															<c:if test="${attlist.getDay30() == 3}">
																<td><i class="${gray}"></i></td>
															</c:if>
															<c:if test="${attlist.getDay30() == null}">
																<td><i class="${gray}"></i></td>
															</c:if>
															<c:if test="${attlist.getDay31() == 1}">
																<td><i class="${green}"></i></td>
															</c:if>
															<c:if test="${attlist.getDay31() == 2}">
																<td><i class="${red}"></i></td>
															</c:if>
															<c:if test="${attlist.getDay31() == 3}">
																<td><i class="${gray}"></i></td>
															</c:if>
															<c:if test="${attlist.getDay31() == null}">
																<td><i class="${gray}"></i></td>
															</c:if>
															<td>

																<div class="btn-group">

																	<c:if test="${attlist.getStatas() == null}">
																		<button type="button"
																			class="btn btn-primary btn-outline btn-rounded waves-effect"
																			data-toggle="dropdown" aria-haspopup="true"
																			aria-expanded="false">Pending</button>
																	</c:if>
																	<c:if test="${attlist.getStatas() == false}">
																		<button type="button"
																			class="btn btn-primary  btn-outline btn-rounded waves-effect colorred">Declined
																		</button>
																	</c:if>
																	<c:if test="${attlist.getStatas() == true}">
																		<button type="button"
																			class="btn btn-primary colorgreen btn-outline btn-rounded waves-effect "
																			aria-haspopup="true" aria-expanded="false">Approved
																		</button>
																	</c:if>
																	<c:set var="eid" value="${User.getEid()}"></c:set>
																	<c:if
																		test="${attlist.getManagerid() == eid || attlist.getMantrans() == user}">
																		<c:if test="${attlist.getStatas() == null}">
																			<ul class="dropdown-menu bullet">
																				<c:if test="${Role.equals('ADMIN_ROLE')}">
																					<c:set var="role" value="admin" />
																				</c:if>

																				<c:if test="${Role.equals('MANAGER_ROLE')}">
																					<c:set var="role" value="manager" />
																				</c:if>

																				<c:if test="${Role.equals('EMPLOYEE_ROLE')}">
																					<c:set var="role" value="employee" />
																				</c:if>
																				<li>
																					<button type="button"
																						class="btn btn-primary  btn-outline btn-rounded waves-effect colorred"
																						data-toggle="modal" data-target="#longmodal1"
																						id="${attlist.getId()}" onclick="display(this.id)">Declined</button>

																				</li>
																				<li><button type="button"
																						class="btn btn-primary colorgreen btn-outline btn-rounded waves-effect"
																						data-toggle="modal" data-target="#longmodal"
																						id="${attlist.getId()}" onclick="display(this.id)">Approved</button>
																				</li>
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
										<div class="clearfix"></div>

									<c:forEach var="attlist" items="${attendancelist}">
									
											 <tbody id="attend">
															

	<c:forEach var="attlist" items="${attendancelist}">
															<tr>
																<td><a href="javascript:void(0)" class="green">${attlist.getMonth()}-
																		${attlist.getYear()}${attlist.getDay1()} <i ></i></a></td>
																		<th><a href="javascript:void(0)" class="green">${attlist.getLeavestatus()}${attlist.getEmpfname()}${attlist.getEmplname()}
																		</a></th>
																<c:if test="${attlist.getDay1() == 2}">
																	<td><i class="${attlist.getDay1()}"></i></td>
																	
																	 <td><a href="javascript:void(0)" class="${attlist.getDay1()}"><i class=""></i></a></td> 
																</c:if>
																<c:if test="${attlist.getDay1() == 2}">
																	<td><i class="${red}"></i></td>
																</c:if>
																<c:if test="${attlist.getDay1() == 3}">
																	<td><i class="${gray}"></i></td>
																</c:if>
																<c:if test="${attlist.getDay2() == 1}">
																	<td><i class="${green}"></i></td>
																</c:if>
																<c:if test="${attlist.getDay2() == 2}">
																	<td><i class="${red}"></i></td>
																</c:if>
																<c:if test="${attlist.getDay2() == 3}">
																	 <td><a href="javascript:void(0)" class="${attlist.getDay2()}"><i class=""></i></a></td> 
																</c:if>
																<c:if test="${attlist.getDay3() == 1}">
																	<td><i class="${green}"></i></td>
																</c:if>
																<c:if test="${attlist.getDay3() == 2}">
																	<td><i class="${red}"></i></td>
																</c:if>
																<c:if test="${attlist.getDay3() == 3}">
																		 <td><a href="javascript:void(0)" class="${attlist.getDay3()}"><i class=""></i></a></td> 
																</c:if>

																<c:if test="${attlist.getDay4() == 1}">
																	<td><i class="${green}"></i></td>
																</c:if>
																<c:if test="${attlist.getDay4() == 2}">
																	<td><i class="${red}"></i></td>
																</c:if>
																<c:if test="${attlist.getDay4() == 3}">
																	 <td><a href="javascript:void(0)" class="${attlist.getDay4()}"><i class=""></i></a></td> 
																</c:if>

																<c:if test="${attlist.getDay5() == 1}">
																	<td><i class="${green}"></i></td>
																</c:if>
																<c:if test="${attlist.getDay5() == 2}">
																	<td><i class="${red}"></i></td>
																</c:if>
																<c:if test="${attlist.getDay5() == 3}">
																	 <td><a href="javascript:void(0)" class="${attlist.getDay5()}"><i class=""></i></a></td> 
																</c:if>
																<c:if test="${attlist.getDay6() == 1}">
																	<td><i class="${green}"></i></td>
																</c:if>
																<c:if test="${attlist.getDay6() == 2}">
																	<td><i class="${red}"></i></td>
																</c:if>
																<c:if test="${attlist.getDay6() == 3}">
																 <td><a href="javascript:void(0)" class="${attlist.getDay6()}"><i class=""></i></a></td> 
																</c:if>
																<c:if test="${attlist.getDay7() == 1}">
																	<td><i class="${green}"></i></td>
																</c:if>
																<c:if test="${attlist.getDay7() == 2}">
																	<td><i class="${red}"></i></td>
																</c:if>
																<c:if test="${attlist.getDay7() == 3}">
																	 <td><a href="javascript:void(0)" class="${attlist.getDay7()}"><i class=""></i></a></td> 
																</c:if>
																<c:if test="${attlist.getDay8() == 1}">
																	<td><i class="${green}"></i></td>
																</c:if>
																<c:if test="${attlist.getDay8() == 2}">
																	<td><i class="${red}"></i></td>
																</c:if>
																<c:if test="${attlist.getDay8() == 3}">
																	 <td><a href="javascript:void(0)" class="${attlist.getDay8()}"><i class=""></i></a></td> 
																</c:if>
																<c:if test="${attlist.getDay9() == 1}">
																	<td><i class="${green}"></i></td>
																</c:if>
																<c:if test="${attlist.getDay9() == 2}">
																	<td><i class="${red}"></i></td>
																</c:if>
																<c:if test="${attlist.getDay9() == 3}">
																	 <td><a href="javascript:void(0)" class="${attlist.getDay9()}"><i class=""></i></a></td> 
																</c:if>
																<c:if test="${attlist.getDay10() == 1}">
																	<td><i class="${green}"></i></td>
																</c:if>
																<c:if test="${attlist.getDay10() == 2}">
																	<td><i class="${red}"></i></td>
																</c:if>
																<c:if test="${attlist.getDay10() == 3}">
																		 <td><a href="javascript:void(0)" class="${attlist.getDay10()}"><i class=""></i></a></td> 
																</c:if>
																<c:if test="${attlist.getDay11() == 1}">
																	<td><i class="${green}"></i></td>
																</c:if>
																<c:if test="${attlist.getDay11() == 2}">
																	<td><i class="${red}"></i></td>
																</c:if>
																<c:if test="${attlist.getDay11() == 3}">
																		 <td><a href="javascript:void(0)" class="${attlist.getDay11()}"><i class=""></i></a></td> 
																</c:if>
																<c:if test="${attlist.getDay12() == 1}">
																	<td><i class="${green}"></i></td>
																</c:if>
																<c:if test="${attlist.getDay12() == 2}">
																	<td><i class="${red}"></i></td>
																</c:if>
																<c:if test="${attlist.getDay12() == 3}">
																		 <td><a href="javascript:void(0)" class="${attlist.getDay12()}"><i class=""></i></a></td> 
																</c:if>
																<c:if test="${attlist.getDay13() == 1}">
																	<td><i class="${green}"></i></td>
																</c:if>
																<c:if test="${attlist.getDay13() == 2}">
																	<td><i class="${red}"></i></td>
																</c:if>
																<c:if test="${attlist.getDay13() == 3}">
																		 <td><a href="javascript:void(0)" class="${attlist.getDay13()}"><i class=""></i></a></td> 
																</c:if>
																<c:if test="${attlist.getDay14() == 1}">
																	<td><i class="${green}"></i></td>
																</c:if>
																<c:if test="${attlist.getDay14() == 2}">
																	<td><i class="${red}"></i></td>
																</c:if>
																<c:if test="${attlist.getDay14() == 3}">
																		 <td><a href="javascript:void(0)" class="${attlist.getDay14()}"><i class=""></i></a></td> 
																</c:if>
																<c:if test="${attlist.getDay15() == 1}">
																	<td><i class="${green}"></i></td>
																</c:if>
																<c:if test="${attlist.getDay15() == 2}">
																	<td><i class="${red}"></i></td>
																</c:if>
																<c:if test="${attlist.getDay15() == 3}">
																	 <td><a href="javascript:void(0)" class="${attlist.getDay15()}"><i class=""></i></a></td> 
																</c:if>
																<c:if test="${attlist.getDay16() == 1}">
																	<td><i class="${green}"></i></td>
																</c:if>
																<c:if test="${attlist.getDay16() == 2}">
																	<td><i class="${red}"></i></td>
																</c:if>
																<c:if test="${attlist.getDay16() == 3}">
																	 <td><a href="javascript:void(0)" class="${attlist.getDay16()}"><i class=""></i></a></td> 
																</c:if>
																<c:if test="${attlist.getDay17() == 1}">
																	<td><i class="${green}"></i></td>
																</c:if>
																<c:if test="${attlist.getDay17() == 2}">
																	<td><i class="${red}"></i></td>
																</c:if>
																<c:if test="${attlist.getDay17() == 3}">
																		 <td><a href="javascript:void(0)" class="${attlist.getDay17()}"><i class=""></i></a></td> 
																</c:if>
																<c:if test="${attlist.getDay18() == 1}">
																	<td><i class="${green}"></i></td>
																</c:if>
																<c:if test="${attlist.getDay18() == 2}">
																	<td><i class="${red}"></i></td>
																</c:if>
																<c:if test="${attlist.getDay18() == 3}">
																		 <td><a href="javascript:void(0)" class="${attlist.getDay18()}"><i class=""></i></a></td> 
																</c:if>
																<c:if test="${attlist.getDay19() == 1}">
																	<td><i class="${green}"></i></td>
																</c:if>
																<c:if test="${attlist.getDay19() == 2}">
																	<td><i class="${red}"></i></td>
																</c:if>
																<c:if test="${attlist.getDay19() == 3}">
																	 <td><a href="javascript:void(0)" class="${attlist.getDay19()}"><i class=""></i></a></td> 
																</c:if>
																<c:if test="${attlist.getDay20() == 1}">
																	<td><i class="${green}"></i></td>
																</c:if>
																<c:if test="${attlist.getDay20() == 2}">
																	<td><i class="${red}"></i></td>
																</c:if>
																<c:if test="${attlist.getDay20() == 3}">
																	 <td><a href="javascript:void(0)" class="${attlist.getDay20()}"><i class=""></i></a></td> 
																</c:if>
																<c:if test="${attlist.getDay21() == 1}">
																	<td><i class="${green}"></i></td>
																</c:if>
																<c:if test="${attlist.getDay21() == 2}">
																	<td><i class="${red}"></i></td>
																</c:if>
																<c:if test="${attlist.getDay21() == 3}">
																		 <td><a href="javascript:void(0)" class="${attlist.getDay21()}"><i class=""></i></a></td> 
																</c:if>
																<c:if test="${attlist.getDay22() == 1}">
																	<td><i class="${green}"></i></td>
																</c:if>
																<c:if test="${attlist.getDay22() == 2}">
																	<td><i class="${red}"></i></td>
																</c:if>
																<c:if test="${attlist.getDay22() == 3}">
																		 <td><a href="javascript:void(0)" class="${attlist.getDay22()}"><i class=""></i></a></td> 
																</c:if>
																<c:if test="${attlist.getDay23() == 1}">
																	<td><i class="${green}"></i></td>
																</c:if>
																<c:if test="${attlist.getDay23() == 2}">
																	<td><i class="${red}"></i></td>
																</c:if>
																<c:if test="${attlist.getDay23() == 3}">
																		 <td><a href="javascript:void(0)" class="${attlist.getDay23()}"><i class=""></i></a></td> 
																</c:if>
																<c:if test="${attlist.getDay24() == 1}">
																	<td><i class="${green}"></i></td>
																</c:if>
																<c:if test="${attlist.getDay24() == 2}">
																	<td><i class="${red}"></i></td>
																</c:if>
																<c:if test="${attlist.getDay24() == 3}">
																		 <td><a href="javascript:void(0)" class="${attlist.getDay24()}"><i class=""></i></a></td> 
																</c:if>
																<c:if test="${attlist.getDay25() == 1}">
																	<td><i class="${green}"></i></td>
																</c:if>
																<c:if test="${attlist.getDay25() == 2}">
																	<td><i class="${red}"></i></td>
																</c:if>
																<c:if test="${attlist.getDay25() == 3}">
																		 <td><a href="javascript:void(0)" class="${attlist.getDay25()}"><i class=""></i></a></td> 
																</c:if>
																<c:if test="${attlist.getDay26() == 1}">
																	<td><i class="${green}"></i></td>
																</c:if>
																<c:if test="${attlist.getDay26() == 2}">
																	<td><i class="${red}"></i></td>
																</c:if>
																<c:if test="${attlist.getDay26() == 3}">
																	 <td><a href="javascript:void(0)" class="${attlist.getDay26()}"><i class=""></i></a></td> 
																</c:if>
																<c:if test="${attlist.getDay27() == 1}">
																	<td><i class="${green}"></i></td>
																</c:if>
																<c:if test="${attlist.getDay27() == 2}">
																	<td><i class="${red}"></i></td>
																</c:if>
																<c:if test="${attlist.getDay27() == 3}">
																		 <td><a href="javascript:void(0)" class="${attlist.getDay27()}"><i class=""></i></a></td> 
																</c:if>
																<c:if test="${attlist.getDay28() == 1}">
																	<td><i class="${green}"></i></td>
																</c:if>
																<c:if test="${attlist.getDay28() == 2}">
																	<td><i class="${red}"></i></td>
																</c:if>
																<c:if test="${attlist.getDay28() == 3}">
																		 <td><a href="javascript:void(0)" class="${attlist.getDay28()}"><i class=""></i></a></td> 
																</c:if>
																<c:if test="${attlist.getDay29() == 1}">
																	<td><i class="${green}"></i></td>
																</c:if>
																<c:if test="${attlist.getDay29() == 2}">
																	<td><i class="${red}"></i></td>
																</c:if>
																<c:if test="${attlist.getDay29() == 3}">
																	<td><i class="${gray}"></i></td>
																</c:if>
																<c:if test="${attlist.getDay29() == null}">
																	 <td><a href="javascript:void(0)" class="${attlist.getDay29()}"><i class=""></i></a></td> 
																</c:if>
																<c:if test="${attlist.getDay30() == 1}">
																	<td><i class="${green}"></i></td>
																</c:if>
																<c:if test="${attlist.getDay30() == 2}">
																	<td><i class="${red}"></i></td>
																</c:if>
																<c:if test="${attlist.getDay30() == 3}">
																	<td><i class="${gray}"></i></td>
																</c:if>
																<c:if test="${attlist.getDay30() == null}">
																		 <td><a href="javascript:void(0)" class="${attlist.getDay30()}"><i class=""></i></a></td> 
																</c:if>
																<c:if test="${attlist.getDay31() == 1}">
																	<td><i class="${green}"></i></td>
																</c:if>
																<c:if test="${attlist.getDay31() == 2}">
																	<td><i class="${red}"></i></td>
																</c:if>
																<c:if test="${attlist.getDay31() == 3}">
																	<td><i class="${gray}"></i></td>
																</c:if>
																<c:if test="${attlist.getDay31() == null}">
																		 <td><a href="javascript:void(0)" class="${attlist.getDay31()}"><i class=""></i></a></td> 
																</c:if>

																<td>
																<div class="col-md-12 padding_col">
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
												</div>
																	<div class="btn-group">
																		<c:if test="${attlist.getLeavestatus() == null}">
																			<button type="button"
																				class="btn btn-primary btn-outline btn-rounded waves-effect"
																				data-toggle="dropdown" aria-haspopup="true"
																				aria-expanded="false">Pending</button>
																		</c:if>
																		<c:if test="${attlist.getLeavestatus() == 'Approved'}">
																			<button type="button"
																				class="btn btn-primary  btn-outline btn-rounded waves-effect colorred">Declined
																			</button>
																		</c:if>
																		<c:if test="${attlist.getLeavestatus() == 'Denied'}">
																			<button type="button"
																				class="btn btn-primary colorgreen btn-outline btn-rounded waves-effect "
																				data-toggle="dropdown" aria-haspopup="true"
																				aria-expanded="false">Approved</button>
																		</c:if>
																	</div>
																</td>
																<td>
																	<ul class="tabelaction">
																		<li class="dropdown"><a
																			href="javascript:void(0);" class="dropdown-toggle"
																			data-toggle="dropdown" role="button"
																			aria-haspopup="true" aria-expanded="false"> <i
																				class="material-icons">more_vert</i>
																		</a><c:if test="${attlist.getLeavestatus() == null}">
																			<ul class="dropdown-menu pull-right">
																				
																					<li><a
																						href="${contextRoot}/${role}/timesheet/delete/${attlist.getId()}"><i
																							class="material-icons">delete</i>Delete</a></li>
																				
																			</ul></c:if></li>
																	</ul>
																</td>
															</tr>
</c:forEach>
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
			<div class="modal fade" id="longmodal" tabindex="-1" role="dialog"
				aria-labelledby="myModalLabel" aria-hidden="true">
				<div class="modal-dialog">
					<div class="modal-content">
						<div class="modal-header">
							<button type="button" class="close" data-dismiss="modal"
								aria-label="Close">
								<span aria-hidden="true">x</span>
							</button>
							<h4 class="modal-title" id="myModalLabel4">Long Modal</h4>
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
											class="btn btn-primary btn-rounded waves-effect">Approve
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
							<h4 class="modal-title" id="myModalLabel4">Long Modal</h4>
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



	<!-- 	<script type="text/javascript">
	
	$(document).ready(function(){
    $("#de").click(function(){

    	var abc = $("#reason").val();
    	  
    	 alert(abc);
    	 
    	var word = document.getElementById("reason").value;
       alert(word);
        
    });
});
	
	</script>
 -->
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

	<script type="text/javascript">
		function display(id) {
			document.getElementById('form_d').action = "${contextRoot}/${role}/attendance/decline/"
					+ id;
			document.getElementById('form_a').action = "${contextRoot}/${role}/attendance/approve/"
					+ id;
		}
		</script>
		<script>		  function geticons()
	     {
	    	

	    	 $("#attend").children().each(function(){
	    		 $(this).children().each(function(){
	       			 if($(this).children('a').hasClass("green"))
	    				 {
	    				  $(this).children('a').children('i').addClass("icon-display  fa fa-check attenedance_check_green");
	    				 }
	    			 else if($(this).children('a').hasClass("red"))
					 {
	    				  $(this).children('a').children('i').addClass("icon-display  fa fa-close  attenedance_check_red");
					 }
	    			 else
	    				 {
	    				  $(this).children('a').children('i').addClass("icon-display  fa fa-bullseye attenedance_check_darkgrey");
	    				 }
	    		 });
	    	 });
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
		</body>

		</html>
	 --%>
	 
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
<style>
.wdth td {
	min-width: 80px;
}

.selected {
	background: red;
}

</style>
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
										<span> Attendance Sheet </span>
									</div>
								</div>
							<!-- 	<div class="col-md-6">
									<div class="row pagetitle">
										<span class="pull-right">
											<button type="button" id="addnew"
												class="btn btn-primary btn-rounded waves-effect">Submit
												New Time Sheet</button>
									</div>

								</div> -->
							</div>
						</div>


						<div class="row clearfix">


<%-- 
							<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12 ">
								<div class="col-md-12 card" id="addnewemp">
									<button type="button" class="close" aria-label="Close">
										<span aria-hidden="true">&times;</span>
									</button>
									<div class="custom_title">
										<h2>Time Sheet</h2>
									</div>


									<!-- Table Kitchen Sink -->
									<c:if test="${Role.equals('ADMIN_ROLE')}">
										<c:set var="role" value="admin" />
									</c:if>

									<c:if test="${Role.equals('MANAGER_ROLE')}">
										<c:set var="role" value="manager" />
									</c:if>

									<c:if test="${Role.equals('EMPLOYEE_ROLE')}">
										<c:set var="role" value="employee" />
									</c:if>




									<form action="${contextRoot}/${role}/timesheet/register"
										method="post">
										<div class="card">

											<div class="body">
												<table
													class="tablesaw table-striped table-hover table-bordered wdth"
													data-tablesaw-mode="swipe" data-tablesaw-minimap>
													<thead class="tableheding ">
														<tr>
															<th data-tablesaw-sortable-col
																data-tablesaw-sortable-default-col
																data-tablesaw-priority="persist">Employee</th>
															<th data-tablesaw-sortable-col
																data-tablesaw-sortable-default-col
																data-tablesaw-priority="persist">Year</th>
															<th data-tablesaw-sortable-col
																data-tablesaw-sortable-default-col
																data-tablesaw-priority="persist">Month</th>
															<c:if test="${root!=null}">
																<c:forEach begin="1" end="${root}" varStatus="loop">
																	<th data-tablesaw-sortable-col
																		data-tablesaw-priority="3">${loop.index}</th>

																</c:forEach>
															</c:if>
														</tr>
													</thead>



													<tbody>



														<tr>
															<th><a
																href="${contextRoot}/${role}/profile/register">${User.getEmplfirstname()}
																	${User.getEmpllastname()}</a></th>

															<td><select class="form-control" size="1"
																name="year" id="year" required>
																	<option value="">Select Year</option>
																	<option value="2018">2018</option>
																	<option value="2019">2019</option>
																	<option value="2020">2020</option>
															</select></td>
															<td><select class="form-control" size="1"
																name="month" id="month" onchange="return validate()"
																required>
																	<option value="">Select Month</option>
																	<option value="January">January</option>
																	<option value="February">February</option>
																	<option value="March">March</option>
																	<option value="April">April</option>
																	<option value="May">May</option>
																	<option value="June">June</option>
																	<option value="July">July</option>
																	<option value="August">August</option>
																	<option value="September">September</option>
																	<option value="October">October</option>
																	<option value="November">November</option>
																	<option value="December">December</option>
															</select></td>
															<c:forEach begin="1" end="${root}" varStatus="loop">


																<td><select class="form-control" size="1"
																	name="day${loop.index}" id="day${loop.index}" required>
																		<option value="1">Present</option>
																		<option value="2">Absent Or Leave</option>
																		<option value="3">WeekEnd Or Holiday</option>
																</select></td>
															</c:forEach>

														</tr>


													</tbody>
												</table>
												<div class="pull-right"
													style="margin-bottom: 10px; margin-top: 10px;">
													<button type="submit" class="btn btn-primary">Submit</button>
												</div>
											</div>
											<div class="clearfix"></div>




										</div>
										<!-- #END# Kitchen Sink -->
									</form>

								</div>
							</div> --%>


							<div class="row clearfix">



								<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12 ">



									<!-- Table Kitchen Sink -->
									<div class="card">
										<div
											class="col-lg-12 col-md-12 col-sm-12 col-xs-12 search_leav">

											<form action="${contextRoot}/${role}/timesheet/search" method="post">
												<div class="col-md-2 padding_col">
													<div class="form-group">

														<select class="form-control" id="months" name="month"
															size="1">
															<option value="">Select Month</option>
															<option value="January">January</option>
															<option value="February">February</option>
															<option value="March">March</option>
															<option value="April">April</option>
															<option value="May">May</option>
															<option value="June">June</option>
															<option value="July">July</option>
															<option value="August">August</option>
															<option value="September">September</option>
															<option value="October">October</option>
															<option value="November">November</option>
															<option value="December">December</option>
														</select>
													</div>
												</div>
												<div class="col-md-2 padding_col">
													<div class="form-group">

														<select class="form-control" id="status" name="status"
															size="1">
															<option value="">Select Status</option>
															<c:forEach items="${ltypelist}" var="ltypelist">
												<option value="${ltypelist.getLeavestatusid()}">
													${ltypelist.getLeavestatus()}</option>
											</c:forEach>
														</select>
													</div>
												</div>
												<div class="col-md-2 ">
													<button type="submit" onclick="return Search()"
														class="btn btn-primary  pull-right waves-effect ">
														Search</button>
												</div>


											</form>
</div>
											<div class="body">
												<table
													class="tablesaw table-striped table-hover table-bordered"
													data-tablesaw-mode="swipe" data-tablesaw-minimap>
													<thead class="tableheding">
														<tr>
															<th data-tablesaw-sortable-col
																data-tablesaw-sortable-default-col
																data-tablesaw-priority="persist">Month</th>
																<th data-tablesaw-sortable-col
																data-tablesaw-sortable-default-col
																data-tablesaw-priority="persist">Name</th>
															<th data-tablesaw-sortable-col data-tablesaw-priority="3">1</th>
															<th data-tablesaw-sortable-col data-tablesaw-priority="2">2</th>
															<th data-tablesaw-sortable-col data-tablesaw-priority="4">3
															</th>

															<th data-tablesaw-sortable-col data-tablesaw-priority="4">4</th>
															<th data-tablesaw-sortable-col data-tablesaw-priority="2">5</th>
															<th data-tablesaw-sortable-col data-tablesaw-priority="4">6</th>
															<th data-tablesaw-sortable-col data-tablesaw-priority="1">7
															</th>
															<th data-tablesaw-sortable-col data-tablesaw-priority="1">8
															</th>
															<th data-tablesaw-sortable-col data-tablesaw-priority="1">9
															</th>
															<th data-tablesaw-sortable-col data-tablesaw-priority="1">10
															</th>
															<th data-tablesaw-sortable-col data-tablesaw-priority="3">11</th>
															<th data-tablesaw-sortable-col data-tablesaw-priority="2">12</th>
															<th data-tablesaw-sortable-col data-tablesaw-priority="4">13
															</th>

															<th data-tablesaw-sortable-col data-tablesaw-priority="4">14</th>
															<th data-tablesaw-sortable-col data-tablesaw-priority="2">15</th>
															<th data-tablesaw-sortable-col data-tablesaw-priority="4">16</th>
															<th data-tablesaw-sortable-col data-tablesaw-priority="1">17
															</th>
															<th data-tablesaw-sortable-col data-tablesaw-priority="1">18
															</th>
															<th data-tablesaw-sortable-col data-tablesaw-priority="1">19
															</th>
															<th data-tablesaw-sortable-col data-tablesaw-priority="1">20
															</th>
															<th data-tablesaw-sortable-col data-tablesaw-priority="3">21</th>
															<th data-tablesaw-sortable-col data-tablesaw-priority="2">22</th>
															<th data-tablesaw-sortable-col data-tablesaw-priority="4">23
															</th>

															<th data-tablesaw-sortable-col data-tablesaw-priority="4">24</th>
															<th data-tablesaw-sortable-col data-tablesaw-priority="2">25</th>
															<th data-tablesaw-sortable-col data-tablesaw-priority="4">26</th>
															<th data-tablesaw-sortable-col data-tablesaw-priority="1">27
															</th>
															<th data-tablesaw-sortable-col data-tablesaw-priority="1">28
															</th>
															<th data-tablesaw-sortable-col data-tablesaw-priority="1">29
															</th>
															<th data-tablesaw-sortable-col data-tablesaw-priority="1">30
															</th>
															<th data-tablesaw-sortable-col data-tablesaw-priority="1">31
															</th>
															<th data-tablesaw-sortable-col
																data-tablesaw-sortable-default-col
																data-tablesaw-priority="persist" style="width: 11%;">STATUS
															</th>
														<!-- 	<th data-tablesaw-sortable-col
																data-tablesaw-sortable-default-col
																data-tablesaw-priority="persist">Edit</th> -->
														</tr>
													</thead>
													<div class="clearfix"></div>

												
                                    <tbody id="attend">
															

	<c:forEach var="attlist" items="${attendancelist}">
															<tr>
																<td><a href="javascript:void(0)" class="green">${attlist.getMonth()}-${attlist.getYear()}</a></td>
																		<th><a href="javascript:void(0)" class="green">${attlist.getEmpfname()}${attlist.getEmplname()}
																		</a></th>
																<%-- <c:if test="${attlist.getDay1() == 2}"> --%>
																	<%-- <td><i class="${attlist.getDay1()}"></i></td> --%>
																	
																	 <td><a href="javascript:void(0)" class="${attlist.getDay1()}"><i class=""></i></a></td> 
																<%-- </c:if>
																<c:if test="${attlist.getDay1() == 2}">
																	<td><i class="${red}"></i></td>
																</c:if>
																<c:if test="${attlist.getDay1() == 3}">
																	<td><i class="${gray}"></i></td>
																</c:if> --%>
																<%-- <c:if test="${attlist.getDay2() == 1}">
																	<td><i class="${green}"></i></td>
																</c:if>
																<c:if test="${attlist.getDay2() == 2}">
																	<td><i class="${red}"></i></td>
																</c:if>
																<c:if test="${attlist.getDay2() == 3}"> --%>
																	 <td><a href="javascript:void(0)" class="${attlist.getDay2()}"><i class=""></i></a></td> 
																<%-- </c:if>
																<c:if test="${attlist.getDay3() == 1}">
																	<td><i class="${green}"></i></td>
																</c:if>
																<c:if test="${attlist.getDay3() == 2}">
																	<td><i class="${red}"></i></td>
																</c:if>
																<c:if test="${attlist.getDay3() == 3}"> --%>
																		 <td><a href="javascript:void(0)" class="${attlist.getDay3()}"><i class=""></i></a></td> 
																<%-- </c:if>

																<c:if test="${attlist.getDay4() == 1}">
																	<td><i class="${green}"></i></td>
																</c:if>
																<c:if test="${attlist.getDay4() == 2}">
																	<td><i class="${red}"></i></td>
																</c:if>
																<c:if test="${attlist.getDay4() == 3}"> --%>
																	 <td><a href="javascript:void(0)" class="${attlist.getDay4()}"><i class=""></i></a></td> 
																<%-- </c:if>

																<c:if test="${attlist.getDay5() == 1}">
																	<td><i class="${green}"></i></td>
																</c:if>
																<c:if test="${attlist.getDay5() == 2}">
																	<td><i class="${red}"></i></td>
																</c:if>
																<c:if test="${attlist.getDay5() == 3}"> --%>
																	 <td><a href="javascript:void(0)" class="${attlist.getDay5()}"><i class=""></i></a></td> 
																<%-- </c:if>
																<c:if test="${attlist.getDay6() == 1}">
																	<td><i class="${green}"></i></td>
																</c:if>
																<c:if test="${attlist.getDay6() == 2}">
																	<td><i class="${red}"></i></td>
																</c:if>
																<c:if test="${attlist.getDay6() == 3}"> --%>
																 <td><a href="javascript:void(0)" class="${attlist.getDay6()}"><i class=""></i></a></td> 
																<%-- </c:if>
																<c:if test="${attlist.getDay7() == 1}">
																	<td><i class="${green}"></i></td>
																</c:if>
																<c:if test="${attlist.getDay7() == 2}">
																	<td><i class="${red}"></i></td>
																</c:if>
																<c:if test="${attlist.getDay7() == 3}"> --%>
																	 <td><a href="javascript:void(0)" class="${attlist.getDay7()}"><i class=""></i></a></td> 
																<%-- </c:if>
																<c:if test="${attlist.getDay8() == 1}">
																	<td><i class="${green}"></i></td>
																</c:if>
																<c:if test="${attlist.getDay8() == 2}">
																	<td><i class="${red}"></i></td>
																</c:if>
																<c:if test="${attlist.getDay8() == 3}"> --%>
																	 <td><a href="javascript:void(0)" class="${attlist.getDay8()}"><i class=""></i></a></td> 
																<%-- </c:if>
																<c:if test="${attlist.getDay9() == 1}">
																	<td><i class="${green}"></i></td>
																</c:if>
																<c:if test="${attlist.getDay9() == 2}">
																	<td><i class="${red}"></i></td>
																</c:if>
																<c:if test="${attlist.getDay9() == 3}"> --%>
																	 <td><a href="javascript:void(0)" class="${attlist.getDay9()}"><i class=""></i></a></td> 
																<%-- </c:if>
																<c:if test="${attlist.getDay10() == 1}">
																	<td><i class="${green}"></i></td>
																</c:if>
																<c:if test="${attlist.getDay10() == 2}">
																	<td><i class="${red}"></i></td>
																</c:if>
																<c:if test="${attlist.getDay10() == 3}"> --%>
																		 <td><a href="javascript:void(0)" class="${attlist.getDay10()}"><i class=""></i></a></td> 
																<%-- </c:if>
																<c:if test="${attlist.getDay11() == 1}">
																	<td><i class="${green}"></i></td>
																</c:if>
																<c:if test="${attlist.getDay11() == 2}">
																	<td><i class="${red}"></i></td>
																</c:if>
																<c:if test="${attlist.getDay11() == 3}"> --%>
																		 <td><a href="javascript:void(0)" class="${attlist.getDay11()}"><i class=""></i></a></td> 
																<%-- </c:if>
																<c:if test="${attlist.getDay12() == 1}">
																	<td><i class="${green}"></i></td>
																</c:if>
																<c:if test="${attlist.getDay12() == 2}">
																	<td><i class="${red}"></i></td>
																</c:if>
																<c:if test="${attlist.getDay12() == 3}"> --%>
																		 <td><a href="javascript:void(0)" class="${attlist.getDay12()}"><i class=""></i></a></td> 
																<%-- </c:if>
																<c:if test="${attlist.getDay13() == 1}">
																	<td><i class="${green}"></i></td>
																</c:if>
																<c:if test="${attlist.getDay13() == 2}">
																	<td><i class="${red}"></i></td>
																</c:if>
																<c:if test="${attlist.getDay13() == 3}"> --%>
																		 <td><a href="javascript:void(0)" class="${attlist.getDay13()}"><i class=""></i></a></td> 
																<%-- </c:if>
																<c:if test="${attlist.getDay14() == 1}">
																	<td><i class="${green}"></i></td>
																</c:if>
																<c:if test="${attlist.getDay14() == 2}">
																	<td><i class="${red}"></i></td>
																</c:if>
																<c:if test="${attlist.getDay14() == 3}"> --%>
																		 <td><a href="javascript:void(0)" class="${attlist.getDay14()}"><i class=""></i></a></td> 
																<%-- </c:if>
																<c:if test="${attlist.getDay15() == 1}">
																	<td><i class="${green}"></i></td>
																</c:if>
																<c:if test="${attlist.getDay15() == 2}">
																	<td><i class="${red}"></i></td>
																</c:if>
																<c:if test="${attlist.getDay15() == 3}"> --%>
																	 <td><a href="javascript:void(0)" class="${attlist.getDay15()}"><i class=""></i></a></td> 
																<%-- </c:if>
																<c:if test="${attlist.getDay16() == 1}">
																	<td><i class="${green}"></i></td>
																</c:if>
																<c:if test="${attlist.getDay16() == 2}">
																	<td><i class="${red}"></i></td>
																</c:if>
																<c:if test="${attlist.getDay16() == 3}"> --%>
																	 <td><a href="javascript:void(0)" class="${attlist.getDay16()}"><i class=""></i></a></td> 
																<%-- </c:if>
																<c:if test="${attlist.getDay17() == 1}">
																	<td><i class="${green}"></i></td>
																</c:if>
																<c:if test="${attlist.getDay17() == 2}">
																	<td><i class="${red}"></i></td>
																</c:if>
																<c:if test="${attlist.getDay17() == 3}"> --%>
																		 <td><a href="javascript:void(0)" class="${attlist.getDay17()}"><i class=""></i></a></td> 
																<%-- </c:if>
																<c:if test="${attlist.getDay18() == 1}">
																	<td><i class="${green}"></i></td>
																</c:if>
																<c:if test="${attlist.getDay18() == 2}">
																	<td><i class="${red}"></i></td>
																</c:if>
																<c:if test="${attlist.getDay18() == 3}"> --%>
																		 <td><a href="javascript:void(0)" class="${attlist.getDay18()}"><i class=""></i></a></td> 
																<%-- </c:if>
																<c:if test="${attlist.getDay19() == 1}">
																	<td><i class="${green}"></i></td>
																</c:if>
																<c:if test="${attlist.getDay19() == 2}">
																	<td><i class="${red}"></i></td>
																</c:if>
																<c:if test="${attlist.getDay19() == 3}"> --%>
																	 <td><a href="javascript:void(0)" class="${attlist.getDay19()}"><i class=""></i></a></td> 
																<%-- </c:if>
																<c:if test="${attlist.getDay20() == 1}">
																	<td><i class="${green}"></i></td>
																</c:if>
																<c:if test="${attlist.getDay20() == 2}">
																	<td><i class="${red}"></i></td>
																</c:if>
																<c:if test="${attlist.getDay20() == 3}"> --%>
																	 <td><a href="javascript:void(0)" class="${attlist.getDay20()}"><i class=""></i></a></td> 
															<%-- 	</c:if>
																<c:if test="${attlist.getDay21() == 1}">
																	<td><i class="${green}"></i></td>
																</c:if>
																<c:if test="${attlist.getDay21() == 2}">
																	<td><i class="${red}"></i></td>
																</c:if>
																<c:if test="${attlist.getDay21() == 3}"> --%>
																		 <td><a href="javascript:void(0)" class="${attlist.getDay21()}"><i class=""></i></a></td> 
																<%-- </c:if>
																<c:if test="${attlist.getDay22() == 1}">
																	<td><i class="${green}"></i></td>
																</c:if>
																<c:if test="${attlist.getDay22() == 2}">
																	<td><i class="${red}"></i></td>
																</c:if>
																<c:if test="${attlist.getDay22() == 3}"> --%>
																		 <td><a href="javascript:void(0)" class="${attlist.getDay22()}"><i class=""></i></a></td> 
																<%-- </c:if>
																<c:if test="${attlist.getDay23() == 1}">
																	<td><i class="${green}"></i></td>
																</c:if>
																<c:if test="${attlist.getDay23() == 2}">
																	<td><i class="${red}"></i></td>
																</c:if>
																<c:if test="${attlist.getDay23() == 3}"> --%>
																		 <td><a href="javascript:void(0)" class="${attlist.getDay23()}"><i class=""></i></a></td> 
																<%-- </c:if>
																<c:if test="${attlist.getDay24() == 1}">
																	<td><i class="${green}"></i></td>
																</c:if>
																<c:if test="${attlist.getDay24() == 2}">
																	<td><i class="${red}"></i></td>
																</c:if>
																<c:if test="${attlist.getDay24() == 3}"> --%>
																		 <td><a href="javascript:void(0)" class="${attlist.getDay24()}"><i class=""></i></a></td> 
																<%-- </c:if>
																<c:if test="${attlist.getDay25() == 1}">
																	<td><i class="${green}"></i></td>
																</c:if>
																<c:if test="${attlist.getDay25() == 2}">
																	<td><i class="${red}"></i></td>
																</c:if>
																<c:if test="${attlist.getDay25() == 3}"> --%>
																		 <td><a href="javascript:void(0)" class="${attlist.getDay25()}"><i class=""></i></a></td> 
																<%-- </c:if>
																<c:if test="${attlist.getDay26() == 1}">
																	<td><i class="${green}"></i></td>
																</c:if>
																<c:if test="${attlist.getDay26() == 2}">
																	<td><i class="${red}"></i></td>
																</c:if>
																<c:if test="${attlist.getDay26() == 3}"> --%>
																	 <td><a href="javascript:void(0)" class="${attlist.getDay26()}"><i class=""></i></a></td> 
																<%-- </c:if>
																<c:if test="${attlist.getDay27() == 1}">
																	<td><i class="${green}"></i></td>
																</c:if>
																<c:if test="${attlist.getDay27() == 2}">
																	<td><i class="${red}"></i></td>
																</c:if>
																<c:if test="${attlist.getDay27() == 3}"> --%>
																		 <td><a href="javascript:void(0)" class="${attlist.getDay27()}"><i class=""></i></a></td> 
																<%-- </c:if>
																<c:if test="${attlist.getDay28() == 1}">
																	<td><i class="${green}"></i></td>
																</c:if>
																<c:if test="${attlist.getDay28() == 2}">
																	<td><i class="${red}"></i></td>
																</c:if>
																<c:if test="${attlist.getDay28() == 3}"> --%>
																		 <td><a href="javascript:void(0)" class="${attlist.getDay28()}"><i class=""></i></a></td> 
																<%-- </c:if>
																<c:if test="${attlist.getDay29() == 1}">
																	<td><i class="${green}"></i></td>
																</c:if>
																<c:if test="${attlist.getDay29() == 2}">
																	<td><i class="${red}"></i></td>
																</c:if>
																<c:if test="${attlist.getDay29() == 3}">
																	<td><i class="${gray}"></i></td>
																</c:if>
																<c:if test="${attlist.getDay29() == null}"> --%>
																	 <td><a href="javascript:void(0)" class="${attlist.getDay29()}"><i class=""></i></a></td> 
																<%-- </c:if>
																<c:if test="${attlist.getDay30() == 1}">
																	<td><i class="${green}"></i></td>
																</c:if>
																<c:if test="${attlist.getDay30() == 2}">
																	<td><i class="${red}"></i></td>
																</c:if>
																<c:if test="${attlist.getDay30() == 3}">
																	<td><i class="${gray}"></i></td>
																</c:if>
																<c:if test="${attlist.getDay30() == null}"> --%>
																		 <td><a href="javascript:void(0)" class="${attlist.getDay30()}"><i class=""></i></a></td> 
																<%-- </c:if>
																<c:if test="${attlist.getDay31() == 1}">
																	<td><i class="${green}"></i></td>
																</c:if>
																<c:if test="${attlist.getDay31() == 2}">
																	<td><i class="${red}"></i></td>
																</c:if>
																<c:if test="${attlist.getDay31() == 3}">
																	<td><i class="${gray}"></i></td>
																</c:if>
																<c:if test="${attlist.getDay31() == null}"> --%>
																		 <td><a href="javascript:void(0)" class="${attlist.getDay31()}"><i class=""></i></a></td> 
															<%-- 	</c:if> --%>

																<td>
																<div class="col-md-12 padding_col">
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
												</div>
																	<%-- <div class="btn-group">
																		<c:if test="${attlist.getLeavestatus() == null}">
																			<button type="button"
																				class="btn btn-primary btn-outline btn-rounded waves-effect"
																				data-toggle="dropdown" aria-haspopup="true"
																				aria-expanded="false">Pending</button>
																		</c:if>
																		<c:if test="${attlist.getLeavestatus() == 'Approved'}">
																			<button type="button"
																				class="btn btn-primary  btn-outline btn-rounded waves-effect colorred">Declined
																			</button>
																		</c:if>
																		<c:if test="${attlist.getLeavestatus() == 'Denied'}">
																			<button type="button"
																				class="btn btn-primary colorgreen btn-outline btn-rounded waves-effect "
																				data-toggle="dropdown" aria-haspopup="true"
																				aria-expanded="false">Approved</button>
																		</c:if>
																	</div> --%>
																</td>
																<td>
																	<ul class="tabelaction">
																		<li class="dropdown"><a
																			href="javascript:void(0);" class="dropdown-toggle"
																			data-toggle="dropdown" role="button"
																			aria-haspopup="true" aria-expanded="false"> <i
																				class="material-icons">more_vert</i>
																		</a><c:if test="${attlist.getLeavestatus() == null}">
																			<ul class="dropdown-menu pull-right">
																				
																					<li><a
																						href="${contextRoot}/${role}/timesheet/delete/${attlist.getId()}"><i
																							class="material-icons">delete</i>Delete</a></li>
																				
																			</ul></c:if></li>
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
		function validate() {

			var opt1 = document.getElementById("month").value;
			var opt2 = document.getElementById("year").value;

			if (opt1 == "January" || opt1 == "March" || opt1 == "May"
					|| opt1 == "July" || opt1 == "August" || opt1 == "October"
					|| opt1 == "December") {
				var name1 = "31";
				var name2 = name1.concat(opt1);
				var name = name2.concat(opt2);
				window.location
						.replace("${contextRoot}/${role}/timesheet/register/"
								+ name);

				return false;

			}
			if (opt1 == "April" || opt1 == "June" || opt1 == "September"
					|| opt1 == "November") {
				var name1 = "30";
				var name2 = name1.concat(opt1);
				var name = name2.concat(opt2);
				window.location
						.replace("${contextRoot}/${role}/timesheet/register/"
								+ name);

				return false;
			}
			if (opt1 == "February" && opt2 % 4 == 0) {
				var name1 = "29";
				var name2 = name1.concat(opt1);
				var name = name2.concat(opt2);
				window.location
						.replace("${contextRoot}/${role}/timesheet/register/"
								+ name);

			}

			if (opt1 == "February" && opt2 % 4 != 0) {
				var name1 = "28";
				var name2 = name1.concat(opt1);
				var name = name2.concat(opt2);
				window.location
						.replace("${contextRoot}/${role}/timesheet/register/"
								+ name);
			}

		}
	</script>
	<c:if test="${root != null }">
		<script>
			alert("${month}" + "${year}");

			document.getElementById("month").value = "${month}";
			document.getElementById("year").value = "${year}";

			var opt1 = document.getElementById("month").value;
			var opt2 = document.getElementById("year").value;
			if (opt1 == "January") {
				var month = 0;
				var days = 31;
			}
			if (opt1 == "February" && opt2 % 4 == 0) {
				var month = 1;
				var days = 29;
			}
			if (opt1 == "February" && opt2 % 4 != 0) {
				var month = 1;
				var days = 28;
			}
			if (opt1 == "March") {
				var month = 2;
				var days = 31;
			}
			if (opt1 == "April") {
				var month = 3;
				var days = 30;
			}
			if (opt1 == "May") {
				var month = 4;
				var days = 31;
			}
			if (opt1 == "June") {
				var month = 5;
				var days = 30;
			}
			if (opt1 == "July") {
				var month = 6;
				var days = 31;
			}
			if (opt1 == "August") {
				var month = 7;
				var days = 31;
			}
			if (opt1 == "September") {
				var month = 8;
				var days = 30;
			}
			if (opt1 == "October") {
				var month = 9;
				var days = 31;
			}
			if (opt1 == "November") {
				var month = 10;
				var days = 30;
			}
			if (opt1 == "December") {
				var month = 11;
				var days = 31;
			}

			for (i = 1; i <= days; i++) {
				var date = new Date(opt2, month, i);
				switch (date.getDay()) {
				case 6:
					document.getElementById("day" + i).value = "3";

					break;
				case 0:
					document.getElementById("day" + i).value = "3";
					break;
				default:
					text = "Looking forward to the Weekend";
				}
			}
		</script>

	</c:if>

	<c:if test="${root == null }">
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
			geticons();
		});
	</script>
	</c:if>
<script>
     function Search(){

	 var mon = document.getElementById("months").value;
	 var sta = document.getElementById("status").value;
	 if(mon=="" && sta=="")
		 {
		 alert("plese Select any one of these");
		 return false;
		 }

}
     function geticons()
     {
    	

    	 $("#attend").children().each(function(){
    		 $(this).children().each(function(){
       			 if($(this).children('a').hasClass("green"))
    				 {
    				  $(this).children('a').children('i').addClass("icon-display  fa fa-check attenedance_check_green");
    				 }
    			 else if($(this).children('a').hasClass("red"))
				 {
    				  $(this).children('a').children('i').addClass("icon-display  fa fa-close  attenedance_check_red");
				 }
    			 else
    				 {
    				  $(this).children('a').children('i').addClass("icon-display  fa fa-bullseye attenedance_check_darkgrey");
    				 }
    		 });
    	 });
     }
</script>
<script>

/* document.getElementById("status1").value = 1; */
 $("#status1").val(4);

</script>

</body>

</html>