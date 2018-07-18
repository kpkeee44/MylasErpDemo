<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
	
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<spring:url var="css" value="/resources/css" />
<spring:url var="js" value="/resources/js" />
<spring:url var="images" value="/resources/images" />
<spring:url var="plugins" value="/resources/plugins" />
<c:set var="contextRoot" value="${pageContext.request.contextPath}" />
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>

<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=Edge">
    <meta content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" name="viewport">
    <title>Amp Demo</title>
    <!-- Favicon-->
    <link rel="icon" href="${images}favicon.png" type="image/x-icon">

    <!--REQUIRED PLUGIN CSS-->
    <link href="${plugins}/font-awesome/css/font-awesome.min.css" rel="stylesheet">
    <link href="${plugins}/bootstrap/css/bootstrap.css" rel="stylesheet">
    <link href="${plugins}/node-waves/waves.css" rel="stylesheet" />
    <link href="${plugins}/animate-css/animate.css" rel="stylesheet" />
    <link href="${plugins}/spinkit/spinkit.css" rel="stylesheet">

    <!--REQUIRED THEME CSS -->
    <link href="${css}/style.css" rel="stylesheet">
    <link href="${css}/layout.css" rel="stylesheet">
    <link href="${css}/themes/main_theme.css" rel="stylesheet" />

    <!--THIS PAGE LEVEL CSS-->
    <link href="${plugins}/unslider/css/unslider.css" rel="stylesheet" />
    <link href="${plugins}/chartist/css/chartist.min.css" rel="stylesheet">
    <!--Chat Css-->
    <link href="${plugins}/wchat/assets/css/style-light.css" rel="stylesheet">
    <link href="${plugins}/wchat/assets/css/mobile.css" rel="stylesheet" id="style">

    <!-- EMOJI ONE JS -->
    <link rel="stylesheet" href="${plugins}/wchat/smiley/assets/sprites/emojione.sprites.css"/>
    <script src="${plugins}/wchat/smiley/js/emojione.min.js"></script>

    <script type="text/javascript">
        // #################################################
        // # Optional

        // default is PNG but you may also use SVG
        emojione.imageType = 'png';
        emojione.sprites = false;

        // default is ignore ASCII smileys like :) but you can easily turn them on
        emojione.ascii = true;

        // if you want to host the images somewhere else
        // you can easily change the default paths
        emojione.imagePathPNG = '${plugins}/wchat/smiley/assets/png/';
        emojione.imagePathSVG = '${plugins}/wchat/smiley/assets/svg/';

        // #################################################
    </script>
    <!--#End# Chat Css-->

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
			<!-- END Top Navbar-->
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
                <div class="page-header">
                    <h2>Employee</h2>
                </div>

<c:out value="${User.getEmplfirstname()} ${User.getEmpllastname()}"></c:out>

   
				
				   <div class="col-md-12">
                        <div class="col-md-4">
                                <div class="user-card-wrapper">
                                    <div class="user-card">
                                        <img class="img-responsive" src="${images}/mail/1.jpg" alt="" />
                                        <div class="user-card-info">
                                            <h6>${User.getEmplfirstname()} ${User.getEmpllastname()}</h6>
                                            <%-- <span>${User.getDesignation()}</span> --%>
                                            <div class="user-card-socials">
                                                <a href="#" title=""><i class="fa fa-twitter"></i></a>
                                                <a href="#" title=""><i class="fa fa-facebook"></i></a>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
							<div class="col-md-8">
							<div class="card">
							<table class="table empdt">
		 <tr>
		 <td>UserName</td>
		 <td>:</td>
		 <td>${User.getUname()}</td>
		 </tr>
		  <tr>
		 <td>Email:</td>
		  <td>:</td>
		 <td>${User.getEmail()}</td>
		 </tr>
		  <tr>
		 <td>Employee ID:</td>
		  <td>:</td>
		 <td>${User.getUname()}</td>
		 </tr>
		  <%--  <tr>
		 <td>Joining Date:</td>
		  <td>:</td>
		 <td>${User.getJdate()}</td>
		 </tr> --%>
		  <tr>
		 <td>Phone:</td>
		  <td>:</td>
		 <td>${User.getPhone()}</td>
		 </tr>
		<%--   <tr>
		 <td>Company:</td>
		  <td>:</td>
		 <td>${User.getCompName()}</td>
		 </tr>
 --%>		  </table>
		  
		  </div>				
		  
							</div>
                            </div>
                        </div>
                        <div class="col-lg-12 col-md-12 col-sm-12 col-xs-12 topbar">

								<div class="col-md-6">
									<div class="row pagetitle">
										<span>Employee Leave Details</span>
									</div>
								</div>

							</div>
                       <%--  <div class="col-md-12">
                        
                         <table class="table tablesaw table-striped table-bordered table-hover">
                        <tr> <th data-tablesaw-sortable-col
												data-tablesaw-sortable-default-col
												data-tablesaw-priority="persist">LeaveType
												</th>
												<th data-tablesaw-sortable-col
												data-tablesaw-sortable-default-col
												data-tablesaw-priority="persist">TotalLeaves
												</th>
												<th data-tablesaw-sortable-col
												data-tablesaw-sortable-default-col
												data-tablesaw-priority="persist">UsedLeaves
												</th>
												<th data-tablesaw-sortable-col
												data-tablesaw-sortable-default-col
												data-tablesaw-priority="persist">PendingLeaves												</th></tr>
									<c:forEach items="${nleave}" var="empleaveslist">
									<thead class="tableheding">
									<tbody>
										<tr>
											<th data-tablesaw-sortable-col
												data-tablesaw-sortable-default-col
												data-tablesaw-priority="persist">${empleaveslist.getLeavetype()}
												</th>
										
									</thead>
									<div class="clearfix"></div>
												
													
													
													<!-- 	<th data-tablesaw-sortable-col
												data-tablesaw-sortable-default-col
												data-tablesaw-priority="persist">Total Leaves
												</th> -->
															<td>${empleaveslist.getNumleavedays()}</td>
															<c:set var="leave" value="${using}"></c:set>
															 <td>${leave.get(empleaveslist.getLeavetype())}</td>
															<c:set var="pleave" value="${pleave}"></c:set>
															<td>${pleave.get(empleaveslist.getLeavetype())}</td> 

														</tr>

													</tbody>
													</c:forEach>
													<tbody>
														<tr>
														<th data-tablesaw-sortable-col
												data-tablesaw-sortable-default-col
												data-tablesaw-priority="persist">Pending Leaves
												</th>
															<td>${psick}</td>
															<td>${pcasual}</td>
															<td>${pmedical}</td>
														</tr>

													</tbody>
													<tbody>
														<tr>
														<th data-tablesaw-sortable-col
												data-tablesaw-sortable-default-col
												data-tablesaw-priority="persist">Used Leaves
												</th>
															<td>${sick}</td>
															<td>${casual}</td>
															<td>${medical}</td>

														</tr>

													</tbody>


								</table>
                   
                   </div> --%>
                   
                    </div>
             
							</diV>
												
							
							</div>
							
								<!--ROW END-->
							
	
		 </div>
		
		
    </section>
    <!-- FOOTER-->
    <footer>
        <span>&copy; 2018 -  <b class="col-blue">Amp</b></span>
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
    <script src="${plugins}/masonry/masonry.pkgd.min.js"></script>
    <script src="${plugins}/jquery-knob/jquery.knob.min.js"></script>
    <script src="${plugins}/jquery-sparkline/jquery.sparkline.js"></script>
    <script src="${plugins}/skycon/skycons.js"></script>
    <script src='${plugins}/chartist/chartist.js'></script>
    <script src="${plugins}/masonry/masonry.min.js"></script>
    <script src="${plugins}/unslider/js/unslider-min.js"></script>
    <!--Chat js-->
    <script src="${plugins}/wchat/assets/js/custom.js"></script>
    <script type="text/javascript" src="${plugins}/wchat/chatjs/lightbox.js"></script>
    <script type="text/javascript" src="${plugins}/wchat/chatjs/dashboard.js"></script>
    <script type="text/javascript" src="${plugins}/wchat/chatjs/custom.js"></script>
    <!-- #End# Chat js-->

    <script src="assets/js/pages/charts/jquery-knob.js"></script>
    <script src="assets/js/pages/index_2.js"></script>

    <!-- LAYOUT JS -->
    <script src="assets/js/demo.js"></script>
    <script src="assets/js/layout.js"></script>

    <script>

    </script>
	<style>
	.empdt td{
border-bottom:1px solid #fff !important;
text-align:left;
padding:21px !important;
}
	</style>
</body>

</html>
