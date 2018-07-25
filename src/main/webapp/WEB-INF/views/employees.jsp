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
									<span> Employee </span>
								</div>
							</div>
							<div class="col-md-3">
								<div class="row pagetitle pull-right">
									<span>
										<button type="button" id="addnew"
											class="btn btn-primary btn-rounded waves-effect">Add
											Employee</button>
									</span>

								</div>

							</div>

						</div>

						<c:if test="${Role.equals('ADMIN_ROLE')}">
							<c:set var="role" value="admin" />
						</c:if>

						<c:if test="${Role.equals('MANAGER_ROLE')}">
							<c:set var="role" value="manager" />
						</c:if>
						<c:if test="${mesg.equals('Employee already Exits')}">
									<h4 style="color: red;">${mesg}</h4>
								</c:if>
						<div class="col-md-12 card" id="addnewemp">
								<button type="button" class="close" aria-label="Close">
								<span aria-hidden="true">&times;</span>
							</button>
							<div class="custom_title">
								<h2>Add Employee</h2>
								<c:if test="${mesg.equals('Employee added successfully')}">
									<h4 style="color: green;">${mesg}</h4>
								</c:if>
								
								<c:if test="${empty mesg}">
									<h4 style="color: red;">Employee Save Failed</h4>
								</c:if>
							</div>

							<hr class="custom_line">
							<div class="body">



								<form action="${contextRoot}/${role}/allemp/register"
									method="post" onsubmit="return Validate()" name="form">
									<div class="col-md-6">
										<div class="form-group">
											<div class="input-group addon-line">
												<div class="form-line">
													<!-- <label>First Name</label> --><input type="text"
														name="firstname1" id="firstname1" class="form-control"
														required="required" size="50" maxlength="50" placeholder="First Name">
												</div>
											</div>
										</div>
									</div>
									<div class="col-md-6">
										<div class="form-group">
											<div class="input-group addon-line">
												<div class="form-line">
													<!-- <label>Last Name </label> --><input type="text"
														name="lastname1" id="lastname1" class="form-control"
														required="required" size="50" maxlength="50" placeholder="Last Name">
												</div>
											</div>
										</div>
									</div>
									<div class="col-md-6">
										<div class="form-group">
											<div class="input-group addon-line">
												<div class="form-line">
													<!-- <label>Employee ID </label> --><input type="text" name="empid"
														id="empid" class="form-control" required="required"
														size="15" maxlength="15" placeholder="Employee ID">
												</div>
											</div>
										</div>
									</div>
									
									<div class="col-md-6">
										<div class="form-group">
											<div class="input-group addon-line">
												<div class="form-line">
													<!-- <label>Email</label> --><input type="email" name="email"
														id="email" class="form-control" required="required"
														size="50" maxlength="50" placeholder="Email">
												</div>
											</div>
										</div>
									</div>
									<div class="col-md-6">
										<div class="form-group">
											<div class="input-group addon-line">
												<div class="form-line">
													<!-- <label>Employee ID </label> --><input type="text" name="uname"
														id="uname" class="form-control" required="required"
														size="15" maxlength="15" placeholder="UserName">
												</div>
											</div>
										</div>
									</div>
									
									<div class="col-md-6">
										<div class="form-group">
											<div class="input-group addon-line">
												<div class="form-line">
													<!-- <label>Password </label> --><input type="password" name="pswd"
														id="pswd" class="form-control"
														pattern="(?=.*\d)(?=.*[a-z])(?=.*[A-Z]).{8,}"
														title="Must contain at least one number and one uppercase and lowercase letter, and at least 8 or more characters"
														required="required" placeholder="Password">
												</div>
											</div>
										</div>
									</div>
									<div class="col-md-6">
										<div class="form-group">
											<div class="input-group addon-line">
												<div class="form-line">
													<!-- <label>Confirm Password </label> --><input type="password"
														name="cpswd" id="cpswd" class="form-control"
														required="required" placeholder="Confirm Password">
												</div>
											</div>
										</div>
									</div>
									<div class="col-md-6">
										<div class="form-group">
											<div class="input-group addon-line">
												<div class="form-line">
													<!-- <label>Username</label> --><input type="text"
													 name="aadhar" id="aadhar" class="form-control"
													  required="required" size="50" maxlength="50" placeholder="Aadhar Number">
												</div>
											</div>
										</div>
									</div>
									
									<div class="col-md-6">
										<div class="form-group">
											<div class="input-group addon-line">
												<div class="form-line">
													<!-- <label>Phone </label> --><input type="text" name="phone"
														id="phone" class="form-control" required="required"
														size="10" maxlength="10" placeholder="Phone" pattern="[0-9]{10}">
												</div>
											</div>
										</div>
									</div>
									<div class="col-md-6">
										<div class="form-group">
											<div class="input-group addon-line">
												<div class="form-line">
													<!-- <label>Phone </label> --><input type="text" name="address"
														id="address" class="form-control" required="required"
														size="50" maxlength="50" placeholder="Address" ">
												</div>
											</div>
										</div>
									</div>
									
									<c:if test="${Role.equals('ADMIN_ROLE')}">
									<!-- 	<div class="col-md-6">
											<div class="form-group">
												<div class="form-line">
													<label>Company</label>
													<input type="text" name="company"
														id="company" class="form-control" 
														required="required" size="50" maxlength="50">
													<select class="form-control" size="1" name="company"
														id="company" required="required">
														<option value="">Select Company</option>
														<option value="mylasit">MylasIT</option>

													</select>
												</div>
											</div>
										</div> -->
										<div class="col-md-6">
											<div class="form-group">
												<div class="form-line">
													<!-- <label>Department</label> --><select class="form-control"
														size="1" name="department" id="department"
														required="required">
														<option value="">Select Department</option>
														<c:forEach items="${departments}" var="deper">
															<option value="${deper.getDepartmentid()}">${deper.getDepartmentname()}</option>
														</c:forEach>
													</select>
												</div>
											</div>
										</div>
									</c:if>
									<%-- <c:if test="${Role.equals('MANAGER_ROLE')}"> --%>
										<div class="col-md-6">
											<div class="form-group">
												<div class="form-line">
													<!-- <label>Designation</label>  --><select class="form-control"
														size="1" name="designation" id="designation" required="required"
														>
														<option value="">Select Designations</option>
														<c:forEach items="${designations}" var="desig">
															<%-- <c:if test="${desig.getDesignation()!='Manager'}"> --%>
																<option value="${desig.getDesignationid()}">${desig.getDesignation()}</option>
														<%-- 	</c:if> --%>
														</c:forEach>
													</select>
												</div>
											</div>
										</div>
								<%-- 	</c:if> --%>
									<div class="clearfix"></div>
									<div class="form-actions">
										<div style="text-align: center;">
											<button type="submit"
												class="btn btn-primary btn-rounded waves-effect">Create
												Employee</button>
										</div>
									</div>
								</form>
							</div>

						</div>

					</div>

					<div class="row clearfix"></div>

					<form action="${contextRoot}/${role}/employee/search" method="post">
						<div class="card" style="padding: 5px;">
							<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12 search_leav">
								<div class="col-md-2 padding_col">
									<div class="form-group">
										<input type="text" class="form-control" id="firstname"
											placeholder="First Name" name="firstname" value=""/>
									</div>
								</div>
								<div class="col-md-2 padding_col">
									<div class="form-group">
										<input type="text" class="form-control" id="lastname"
											placeholder="Last Name" name="lastname" value=""/>
									</div>
								</div>
								<div class="col-md-3 padding_col">
									<div class="form-group">
										<select id="department1" name="department1"
											class="form-control" size="1">
											<option value="">Select Department</option>
											<c:forEach items="${departments}" var="departments">
												<option value="${departments.getDepartmentname()}">
													${departments.getDepartmentname()}</option>
											</c:forEach>
										</select>
									</div>
								</div>
								<div class="col-md-3 padding_col">
									<div class="form-group">
										<select id="designation1" name="designation1"
											class="form-control" size="1">
											<option value="">Select Designation</option>
											<c:forEach items="${designations}" var="designations">
												<option value="${designations.getDesignation()}">
													${designations.getDesignation()}</option>
											</c:forEach>
										</select>
									</div>
								</div>


								<div class="col-md-2 ">
									<button type="submit" onclick="return Search()"
										class="btn btn-primary  pull-right waves-effect ">Search</button>
								</div>
							</div>
					</form>
				 	<div class="body">
						<div class="clearfix"></div>
						<table
							class="tablesaw table-striped table-bordered table-hover one"
							style="width: 100%;">
							<thead class="tableheding">
								<tr>
									<th data-tablesaw-sortable-col=""
										data-tablesaw-sortable-default-col=""
										data-tablesaw-priority="persist">Employee</th>
									<th data-tablesaw-sortable-col="" data-tablesaw-priority="3">
										Employee ID</th>
									<th data-tablesaw-sortable-col="" data-tablesaw-priority="2">Username</th>
									<th data-tablesaw-sortable-col="" data-tablesaw-priority="4">Email
									</th>

									<!-- <th data-tablesaw-sortable-col="" data-tablesaw-priority="4">Joining
										Date</th> -->
									<th data-tablesaw-sortable-col="" data-tablesaw-priority="2">
										Phone</th>
									<!-- <th data-tablesaw-sortable-col="" data-tablesaw-priority="4">Company
									</th> -->
									<c:if test="${Role.equals('ADMIN_ROLE')}">
										<th data-tablesaw-sortable-col="" data-tablesaw-priority="1"
											class="actiontabel">Action</th>
									</c:if>

								</tr>
							</thead>
							<tbody>
							 <c:set var="manager" value="${User.getEid()}" />
								<%-- <c:set var="compname" value="${User.getCompName()}" /> --%>

								<%-- <c:forEach items="${employees}" var="empl">
									<c:if test="${empl.getCompName() == compname}">
									<c:if test="${empl.getManagerid() ==  manager}">





										<tr>
											<td>
												<div class="chip">
													<a
														href="<%=request.getContextPath()%>/${role}/allemp/register/${empl.getId()}/employeedetails">
														<img src="${images}/mail/one.jpg" alt="Contact Person">
														<div class="profiletitlewidth hideOverflow ">${empl.getEmplfirstname()}${empl.getEmpllastname()}</div>
														<div class="userprofile_sub" style="text-align: center">${empl.getDesignation()}</div>
													</a>
												</div>
											</td>
											<td>${empl.getEid()}</td>
											<td>${empl.getUname()}</td>
											<td>${empl.getEmail()}</td>
											<td>${empl.getJdate()}</td>
											<td>${empl.getPhone()}</td>
											<td>${empl.getCompName()}</td>
											<td>

												<ul class="tabelaction">
													<c:if test="${Role.equals('ADMIN_ROLE')}">
														<li class="dropdown"><a href="javascript:void(0);"
															class="dropdown-toggle" data-toggle="dropdown"
															role="button" aria-haspopup="true" aria-expanded="false">
																<i class="material-icons">more_vert</i>
														</a>
															<ul class="dropdown-menu pull-right">
																<li><a
																	href="${contextRoot}/admin/empdetais/edit/${empl.getId()}"
																	class=" waves-effect waves-classic"><i
																		class="material-icons">edit</i>Edit</a></li>
																	<li><a
																	href="${contextRoot}/admin/allemp/delete/${empl.getId()}"
																	class=" waves-effect waves-classic"><i
																		class="material-icons">delete</i>Delete</a></li>

															</ul></li>
													</c:if>
												</ul>
											</td>
										</tr>



									</c:if>
									</c:if>
								</c:forEach> --%>
								<c:if test="${Role.equals('ADMIN_ROLE')}">
									<c:forEach items="${employees}" var="empl">
										<%-- <c:if test="${empl.getManagerid() !=  manager}"> --%>
											<%-- <c:if test="${empl.getManagerid() !=  null}"> --%>
												<tr style="background: #ededed;">
													<td>
														<div class="chip">
															<a
																href="${contextRoot}/admin/profile/register/${empl.getId()}">
																<img src="${images}/mail/one.jpg" alt="Contact Person">
																<div class="profiletitlewidth hideOverflow ">${empl.getEmplfirstname()}
																	${empl.getEmpllastname()}</div>

																<%-- <div class="userprofile_sub" style="text-align: center">${empl.getDesignation()}</div> --%>

															</a>

														</div>
													</td>
													<td>${empl.getEid()}</td>
													<td>${empl.getUname()}</td>
													<td>${empl.getEmail()}</td>
													<%-- <td>${empl.getJdate()}</td> --%>
													<td>${empl.getPhone()}</td>
												<%-- 	<td>${empl.getCompName()}</td> --%>
													<td>
														<ul class="tabelaction">
															<c:if test="${Role.equals('ADMIN_ROLE')}">
																<li class="dropdown"><a href="javascript:void(0);"
																	class="dropdown-toggle" data-toggle="dropdown"
																	role="button" aria-haspopup="true"
																	aria-expanded="false"> <i class="material-icons">more_vert</i>
																</a>
																	<ul class="dropdown-menu pull-right">
																		<li><a
																			href="${contextRoot}/admin/empdetais/edit/${empl.getId()}"
																			class=" waves-effect waves-classic"><i
																				class="material-icons">edit</i>Edit</a></li>
																		<%-- <li><a
																			href="${contextRoot}/admin/allemp/delete/${empl.getId()}"
																			class=" waves-effect waves-classic"><i
																				class="material-icons">delete</i>Delete</a></li>
 --%>
																	</ul></li>
															</c:if>
														</ul>
													</td>
												</tr>
											<%-- </c:if> --%>
										<%-- </c:if> --%>
									</c:forEach>
								</c:if>
							</tbody>
						</table> 
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
	<!-- <script>
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
     </script> -->

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
	
	<script>
$(document).ready(function(){
    $("button").click(function(){
        $("h4").hide();
    });
});
</script>
	
</body>

</html>