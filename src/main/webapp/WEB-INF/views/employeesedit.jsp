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



								<form action="${contextRoot}/admin/updatedetails/edit/${edetais.id}"
									method="post" onsubmit="return Validate()" name="form">
									<div class="col-md-6">
										<div class="form-group">
											<div class="input-group addon-line">
												<div class="form-line">
													<label>First Name</label> <input type="text"
														name="firstname" id="firstname" class="form-control"
														placeholder="First Name" required="required">
												</div>
											</div>
										</div>
									</div>
									<div class="col-md-6">
										<div class="form-group">
											<div class="input-group addon-line">
												<div class="form-line">
													<label>Last Name </label> <input type="text"
														name="lastname" id="lastname" class="form-control"
														placeholder="Last Name " required="required">
												</div>
											</div>
										</div>
									</div>
									<div class="col-md-6">
										<div class="form-group">
											<div class="input-group addon-line">
												<div class="form-line">
													<label>Username</label> <input type="text" name="uname"
														id="uname" class="form-control" placeholder="Username"
														required="required">
												</div>
											</div>
										</div>
									</div>
									<div class="col-md-6">
										<div class="form-group">
											<div class="input-group addon-line">
												<div class="form-line">
													<label>Email</label> <input type="email" name="email"
														id="email" class="form-control" placeholder="Email"
														required="required">
												</div>
											</div>
										</div>
									</div>
									<div class="col-md-6">
										<div class="form-group">
											<div class="input-group addon-line">
												<div class="form-line">
													<label>Password </label> <input type="password" name="pswd"
														id="pswd" class="form-control" placeholder="Password"
														pattern="(?=.*\d)(?=.*[a-z])(?=.*[A-Z]).{8,}" title="Must contain at least one number and one uppercase and lowercase letter, and at least 8 or more characters" required="required">
												</div>
											</div>
										</div>
									</div>
									<div class="col-md-6">
										<div class="form-group">
											<div class="input-group addon-line">
												<div class="form-line">
													<label>Confirm Password </label> <input type="password"
														name="cpswd" id="cpswd" class="form-control"
														placeholder="Confirm Password" required="required">
												</div>
											</div>
										</div>
									</div>
									<div class="col-md-6">
										<div class="form-group">
											<div class="input-group addon-line">
												<div class="form-line">
													<label>Employee ID </label> <input type="text" name="empid"
														id="empid" class="form-control" placeholder="Employee ID"
														required="required">
												</div>
											</div>
										</div>
									</div>
									<div class="col-md-6">
										<div class="form-group">
											<div class="input-group addon-line">
												<div class="form-line">
													<label>Joining Date </label> <input type="date" name="joindate" id="joindate"
														class="form-control" placeholder="Joining Date">
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
													<label>Phone </label> <input type="number" name="phone"
														id="phone" class="form-control" placeholder="Phone"
														required="required">
												</div>
											</div>
										</div>
									</div>
									<c:if test="${Role.equals('ADMIN_ROLE')}">
									<div class="col-md-6">
										<div class="form-group">
											<div class="form-line">
												<label>Company</label> <input type="text" name="company"
														id="company" class="form-control" placeholder="Company"
														required="required">
											</div>
										</div>
									</div>
									<div class="col-md-6">
										<div class="form-group">
											<div class="form-line">
												<label>Department</label> <select class="form-control"
													size="1" name="department" id="department" required="required">
													<option value="">Please select</option>
													<c:forEach items="${departments}" var="deper">
													<option value="${deper.getDepartment()}">${deper.getDepartment()}</option>
													</c:forEach>
												</select>
											</div>
										</div>
									</div>
									<div class="col-md-6">
										<div class="form-group">
											<div class="input-group addon-line">
												<div class="form-line">
													<label>Relieving Date </label> <input type="date" name="relievingdate" id="relievingdate"
														class="form-control" placeholder="Relieving Date">
												</div>
												<span class="input-group-addon"><i
													class="material-icons">date_range </i></span>
											</div>
										</div>
									</div>
									</c:if>
									<c:if test="${Role.equals('MANAGER_ROLE')}">									
									<div class="col-md-6">
										<div class="form-group">
											<div class="form-line">
												<label>Designation</label> <select class="form-control"
													size="1" name="designation" id="designation" required="required">
													<option value="0">Please select</option>
													<c:forEach items="${designations}" var="desig">
													<c:if test="${desig.getDesignation()!='Manager'}">
													<option value="${desig.getDesignation()}">${desig.getDesignation()}</option></c:if>
													</c:forEach>
												</select>
											</div>
										</div>
									</div>
									</c:if>
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
    alert(" Your Mobile Number must ");
    phone.focus();
    return false;
    }      
   
}
</script>
<script>
		document.getElementById("firstname").value = "${edetais.getFname()}";
		document.getElementById("lastname").value = "${edetais.getLname()}";
		document.getElementById("uname").value = "${edetais.getUname()}";
		document.getElementById("email").value = "${edetais.getEmail()}";
		document.getElementById("empid").value = "${edetais.getEid()}";
		document.getElementById("joindate").value = "${edetais.getJdate()}";
		document.getElementById("phone").value = "${edetais.getPhone()}";
		document.getElementById("pswd").value = "${edetais.getCpswd()}";
		document.getElementById("cpswd").value = "${edetais.getPswd()}";
		document.getElementById("company").value = "${edetais.getCompName()}";
		document.getElementById("departments").value = "${edetais.getDepartment()}";
		document.getElementById("designations").value = "${edetais.getDesignation()}";
		firstname.focus();
		
		/* document.getElementById("joindate").value = "${edetais.getJdate()}"; */
	</script>

</body>

</html>
