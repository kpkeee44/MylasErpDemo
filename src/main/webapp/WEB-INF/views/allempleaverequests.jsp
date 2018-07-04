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
								<form action="${contextRoot}/${role}/search/register" method="post">
									<div class="col-md-2 padding_col">

										<div class="form-group">

											<input type="text" class="form-control"
												placeholder="Username" name="uname" id="uname" />
										</div>
									</div>
									<div class="col-md-2 padding_col">
										<div class="form-group">

											<select class="form-control" size="1" name="month" id="month">
												<option value="">Please select</option>
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

											<select class="form-control" size="1" name="status"
												id="status">
												<option value="">Please select</option>
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
								</form>
							</div>
							<div class="body">
								<p style="color: green;">${UMsg}</p>
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


										</tr>
									</thead>
									<div class="clearfix"></div>

									<c:forEach items="${empleave}" var="empleaveslist">
										<c:if test="${User.getRole().equals('MANAGER_ROLE')}">
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
																			aria-haspopup="true" aria-expanded="false">Approved
																		</button>
																	</c:if>
																	<c:set var="eid" value="${User.getEid()}"></c:set>
																	<c:if
																		test="${empleaveslist.getManagerid() == eid || empleaveslist.getMantrans() == eid}">
																		<c:if test="${empleaveslist.getStatus() == null}">

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
																				data-toggle="modal" data-target="#longmodal1" id="${empleaveslist.getId()}" onclick="display(this.id)">Declined</button>
																			
																		</li>
																		<li><button type="button"
																				class="btn btn-primary colorgreen btn-outline btn-rounded waves-effect"
																				data-toggle="modal" data-target="#longmodal" id="${empleaveslist.getId()}" onclick="display(this.id)">Approved</button>
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
										<c:if test="${User.getRole().equals('ADMIN_ROLE')}">
											<tbody>
												<tr>
													<td>
														<div class="chip">
															<img src="${images}/mail/one.jpg" alt="Contact Person">
															<span>${empleaveslist.getEmployeeid()}</span>
															<div style="text-align: center"></div>
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
																	aria-haspopup="true" aria-expanded="false">Approved
																</button>
															</c:if>
															<c:set var="eid" value="${User.getEid()}"></c:set>
															<c:if
																test="${empleaveslist.getManagerid() == eid || empleaveslist.getMantrans() == eid}">
																<c:if test="${empleaveslist.getStatus() == null}">

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
																				data-toggle="modal" data-target="#longmodal1" id="${empleaveslist.getId()}" onclick="display(this.id)">Declined</button>
																			
																		</li>
																		<li><button type="button"
																				class="btn btn-primary colorgreen btn-outline btn-rounded waves-effect"
																				data-toggle="modal" data-target="#longmodal" id="${empleaveslist.getId()}" onclick="display(this.id)">
</button>
																			</li>

																	</ul>
																</c:if>
															</c:if>
														</div>
													</td>


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
								<form id="form_d"  method="post">
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
     function Search(){
	 var name = document.getElementById("uname").value;
	 var mon = document.getElementById("month").value;
	 var sta = document.getElementById("status").value;
	 if(name=="" && mon=="" && sta=="")
		 {
		 alert("plese Select any one of these");
		 return false;
		 }

}
  </script>
  
  <script type="text/javascript">
  function display(id){
	    document.getElementById('form_d').action = "${contextRoot}/${role}/leave/decline/"+id;
	    document.getElementById('form_a').action = "${contextRoot}/${role}/leave/approve/"+id;
	    }


   

  </script>
</body>

</html>