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


						<c:if test="${Role.equals('ADMIN_ROLE')}">
							<c:set var="role" value="admin" />
						</c:if>

						<c:if test="${Role.equals('MANAGER_ROLE')}">
							<c:set var="role" value="manager" />
						</c:if>
						<div class="col-md-12 card">
							<div class="custom_title">
								<h2>Edit Employee</h2>
								<c:if test="${dupmsg.equals('Employee Saved')}">
									<h4 style="color: green;">${dupmsg}</h4>
								</c:if>
								<c:if test="${dupmsg.equals('This is a Duplicate Entry')}">
									<h4 style="color: red;">${dupmsg}</h4>
								</c:if>
								<c:if test="${empty dupmsg}">
									<h4 style="color: red;">Employee Save Failed</h4>
								</c:if>
							</div>

							<hr class="custom_line">
							<div class="body">



								<form action="${contextRoot}/admin/empdetais/edit/${edetais.id}"
									method="post" onsubmit="return Validate()" name="form">
									
									
																	<div class="col-md-6">
										<div class="form-group">
											<div class="input-group addon-line">
												<div class="form-line">
													First Name:<input type="text"
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
													Last Name:<input type="text"
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
													Employee ID:<input type="text" name="empid"
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
													Email:<input type="email" name="email"
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
													Username:<input type="text" name="uname"
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
													Password:<input type="password" name="pswd"
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
												Confirm Password:<input type="password"
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
													Aadhar Number:<input type="text"
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
													Phone:<input type="text" name="phone"
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
												Address:<input type="text" name="address"
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
														size="1" name="designation" id="designation"
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
									<div class="clearfix"></div>
									<div class="form-actions">
										<div style="text-align: center;">
											<button type="submit"
												class="btn btn-primary btn-rounded waves-effect">Update
												Employee</button>
										</div>
									</div>
								</form>
							</div>

						</div>

					</div>

					<div class="row clearfix"></div>


							
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

	<script>
$(document).ready(function(){

showdiv();
$("#detailview").click(function (){
$("#detail").css("display","block");
$("#list").css("display","none");
});

$("#listview").click(function(){
$("#detail").css("display","none");
$("#list").css("display","block");
});

});

function showdiv()
{
$("#gi").css("display","block");
$("#list").css("display","none");
}
function Validate() {
    var password = document.getElementById("pswd").value;
    var confirmPassword = document.getElementById("cpswd").value;
  	     var ph=document.getElementById("phone").value;
    if (password != confirmPassword) {
        alert("Passwords do not match.");
       pswd.focus();
        return false; 
    }

  
   
    if((ph.length <10) || ph.length >10 )
    {
    alert("Invalid Mobile Number");
    phone.focus();
    return false;
    }      
   
}
</script>
<script>
		document.getElementById("firstname1").value = "${edetais.getEmplfirstname()}"
		document.getElementById("lastname1").value = "${edetais.getEmpllastname()}";
		document.getElementById("empid").value = "${edetais.getEid()}";
		document.getElementById("email").value = "${edetais.getEmail()}";
		document.getElementById("uname").value = "${edetais.getUname()}";
		document.getElementById("aadhar").value = "${edetais.getAadharno()}";
		document.getElementById("phone").value = "${edetais.getPhone()}";
		document.getElementById("address").value = "${edetais.getAdderss()}";
		$("#department").val("${edetais.getDepartmentnameid()}");
		$("#designation").val("${edetais.getDesignationid()}");
		
		var active = "${edetais.isIsactive()}";
		if(active == "true")
		$('#active1').prop('checked', true);
		else
			$('#active').prop('checked', true);	
		firstname1.focus(); 
		
		
	</script> 


</body>

</html>
