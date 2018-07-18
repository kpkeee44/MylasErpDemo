
<style>
.fontname{
color :	#fff;
padding : 12px;
}
</style>


<c:if test="${Role.equals('ADMIN_ROLE')}">
	<c:set var="role" value="admin" />
</c:if>

<c:if test="${Role.equals('MANAGER_ROLE')}">
	<c:set var="role" value="manager" />
</c:if>

<c:if test="${Role.equals('EMPLOYEE_ROLE')}">
	<c:set var="role" value="employee" />
</c:if>
<nav role="navigation" class="navbar topnavbar">
	<!-- START navbar header-->
	<div class="navbar-header">
		<c:if test="${userClickForgotPassword==true }">
			<%@include file="../adminindex.jsp"%>
		</c:if>
		<a href="${contextRoot}/" class="navbar-brand">
			<div class="brand-logo">
				<img src="${images}/logo.png" alt="Admin Logo"
					class="img-responsive">
			</div>
			<div class="brand-logo-collapsed">
				<img src="${images}/logo-single.png" alt="Admin Logo"
					class="img-responsive">
			</div>
		</a>
	</div>
	<!-- END navbar header-->
	<!-- START Nav wrapper-->
	<div class="nav-wrapper">
		<!-- START Left navbar-->
		<ul class="nav navbar-nav">
			<li><a href="#" data-trigger-resize=""
				data-toggle-state="aside-collapsed" class="hidden-xs"> <em
					class="material-icons">menu</em>
			</a> <a href="#" data-toggle-state="aside-toggled" data-no-persist="true"
				class="visible-xs sidebar-toggle"> <em class="material-icons">menu</em>
			</a></li>
		</ul>
		<%@page import="java.text.DateFormat"%>
<%@page import="java.util.*"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.util.Calendar" %>  
<%@page import="mylas.com.erp.demo.daoimpl.*"%> 
<%@page import="mylas.com.erp.demo.*"%> 
<%@page import="java.time.LocalDate"%>
<%@page import="java.time.temporal.ChronoUnit" %>
<%@page import="java.time.*" %>
<%-- <% 
EmpLeaveRequestService tbllev=new EmpLeaveRequestService();
List<TblEmpLeavereq> allempleave = tbllev.view();
for(TblEmpLeavereq tbl:allempleave){
	String date2 =tbl.getFromdate(); 
String date1 =date2.replace('-','/');
   SimpleDateFormat dateformat = new SimpleDateFormat ("E    yyyy.MM.dd"); //SDF to display output with day of week
Date displaydate1=new Date(date1); //Turning the inputed date from string
Date displaydate2=new Date();
  int differenceInDays =(int) ((displaydate2.getTime() - displaydate1.getTime())/(1000*60*60*24));//common method to calculate number of days
   if((differenceInDays<=3) && (differenceInDays>0)){
	   if(tbl.getDaycount()!=null)
	    if(differenceInDays==tbl.getDaycount()){ 
	  tbllev.EmailSend(tbl.getId(),differenceInDays);
	   } 
  } 
//out.println("Between " +dateformat.format(displaydate1)+ " and " +dateformat.format(displaydate2)+ " there are " +differenceInDays+ " days");
 // tbllev.EmailSend();
  
}
%> --%>
		
		
		
		
		<!-- END Left navbar-->
		<!-- START Right Navbar-->
		<ul class="nav navbar-nav navbar-right">
		<li><h4 class="fontname">Welcome: ${User.getEmplfirstname()} ${User.getEmpllastname()}</h4></li>
			<li class="visible-lg"><a href="#" data-toggle-fullscreen="">
					<em class="material-icons">fullscreen</em>
			</a></li>
			

			<!-- Notifications -->
			<li class="dropdown"><a href="javascript:void(0);"
				class="dropdown-toggle" data-toggle="dropdown" role="button"> <i
					class="material-icons">notifications</i> 
				<c:if test="${User.getRole().equals('MANAGER_ROLE') || User.getRole().equals('ADMIN_ROLE') }">	
				<c:set var="user" value="${User.getEid()}" /><c:if test="${empleaveslist.getEmployeeid() != user}">
					<span
					class="badge badge-success">${count}</span></c:if></c:if>
			</a>
				<ul class="dropdown-menu">
					<li class="header">NOTIFICATIONS</li>
					<li class="body">
						<ul class="menu">
							<c:forEach items="${allempleave}" var="empleaveslist">
								<c:if test="${User.getRole().equals('MANAGER_ROLE') || User.getRole().equals('ADMIN_ROLE') }">
									<c:set var="user" value="${User.getEid()}" />
									<c:if test="${empleaveslist.getEmployeeid() != user}">
										<c:if
											test="${empleaveslist.getManagerid()==user || empleaveslist.getMantrans() == user}">
											<c:if test="${empleaveslist.getStatus() == null}">
												<li class="media"><a
													href="${contextRoot}/${role}/leaverequests/register">
														<div class="media-left">
															<div class="icon-circle bg-blue">
																<i class="material-icons">alarm</i>
															</div>
														</div>
														<div class="media-body menu-note">
															<p class="pull-right">20 mins</p>
															<h4>Pending Leave Request for
																${empleaveslist.getEmployeeid()}</h4>
														</div>

												</a></li>
											</c:if>
											<%-- <c:if test="${empleaveslist.getStatus() == 1}">
												<li class="media"><a
													href="${contextRoot}/${role}/leaverequests/register">
														<div class="media-left">
															<div class="icon-circle bg-green">
																<i class="material-icons">alarm</i>
															</div>
														</div>
														<div class="media-body menu-note">
															<p class="pull-right">20 mins</p>
															<h4>Approved Leave Request for
																${empleaveslist.getEmployeeid()}</h4>
														</div>

												</a></li>
											</c:if> --%>
										</c:if>
									</c:if>
								</c:if>
							</c:forEach>
							<c:forEach var="attlist" items="${empattendances}">
								<c:if test="${User.getRole().equals('MANAGER_ROLE') || User.getRole().equals('ADMIN_ROLE')}">
									<c:set var="user" value="${User.getEid()}" />
									<c:if
										test="${attlist.getManagerid() == user || attlist.getMantrans() == user}">
										
										<c:if test="${attlist.getEmpid() != user}">
											<c:if test="${attlist.getStatas() == null}">
												<li class="media"><a
													href="${contextRoot}/${role}/employeetimesheets/register">
														<div class="media-left">
															<div class="icon-circle bg-orange">
																<i class="material-icons">thumb_up</i>
															</div>
														</div>
														<div class="media-body menu-note">
															<p class="pull-right">2 hrs</p>
															<h4>Pending Time Sheet for ${attlist.getEmpid()}
																Month/Year ${attlist.getMonth()}/${attlist.getYear()}</h4>
														</div>
												</a></li>
											</c:if>
										</c:if>
									</c:if>
								</c:if>
							</c:forEach>
							
						</ul>
					</li>
					
				</ul></li>
			<!-- #END# Notifications -->
			<!-- Task -->
			<li class="dropdown"><a href="javascript:void(0);"
				class="dropdown-toggle" data-toggle="dropdown" role="button"> <i
					class="material-icons">mail</i> <span class="badge badge-danger">7</span>
			</a>
				<ul class="dropdown-menu">
					<li class="header">You have <span class="font-bold">7
							New</span> Messages
					</li>
					<li class="body">
						<ul class="menu media-list">
							<li class="media unread"><a href="#">
									<div class="media-left">
										<img class="media-object img-circle" width="32"
											src="${images}/mail/nine.jpg" alt="user">
									</div>
									<div class="media-body">
										<p class="pull-right">
											<small>Just Now</small>
										</p>
										<h4 class="media-heading body-text">Lisa Headon</h4>
										<p class="font-12">Cras justo odio, dapibus ac facilisis
											in.</p>
									</div>
							</a></li>
							<li class="media"><a href="#">
									<div class="media-left">
										<img class="media-object img-circle" width="32"
											src="${images}/mail/2.jpg" alt="user">
									</div>
									<div class="media-body">
										<p class="pull-right">
											<small>2 hour ago</small>
										</p>
										<h4 class="media-heading body-text">Lucy Doe</h4>
										<p class="font-12">Duis mollis, est non commodo luctus,
											nisi erat</p>
									</div>
							</a></li>
							<li class="media"><a href="#">
									<div class="media-left">
										<img class="media-object img-circle" width="32"
											src="${images}/mail/five.jpg" alt="user">
									</div>
									<div class="media-body">
										<p class="pull-right">
											<small>12 hour ago</small>
										</p>
										<h4 class="media-heading body-text">Jhon Doe</h4>
										<p class="font-12">Aenean lacinia bibendum nulla sed
											consectetur.</p>
									</div>
							</a></li>
							<li class="media"><a href="#">
									<div class="media-left">
										<img class="media-object img-circle" width="32"
											src="${images}/mail/1.jpg" alt="user">
									</div>
									<div class="media-body">
										<p class="pull-right">
											<small>2 days ago</small>
										</p>
										<h4 class="media-heading body-text">Daniel Richard</h4>
										<p class="font-12">Donec id elit non mi porta gravida at
											eget metus.</p>
									</div>
							</a></li>
							<li class="media"><a href="#">
									<div class="media-left">
										<img class="media-object img-circle" width="32"
											src="${images}/mail/seven.jpg" alt="user">
									</div>
									<div class="media-body">
										<p class="pull-right">
											<small>3 days ago</small>
										</p>
										<h4 class="media-heading body-text">Kelly Brook</h4>
										<p class="font-12">Maecenas sed diam eget risus varius
											blandit sit amet non magna.</p>
									</div>
							</a></li>
							<li class="media unread"><a href="#">
									<div class="media-left">
										<img class="media-object img-circle" width="32"
											src="${images}/mail/three.jpg" alt="user">
									</div>
									<div class="media-body">
										<p class="pull-right">
											<small>4 days ago</small>
										</p>
										<h4 class="media-heading body-text">Olivia Wild</h4>
										<p class="font-12">Fusce dapibus, tellus ac cursus
											commodo, tortor mauris condimentum nibh</p>
									</div>
							</a></li>
							<li class="media"><a href="#">
									<div class="media-left">
										<img class="media-object img-circle" width="32"
											src="${images}/mail/two.jpg" alt="user">
									</div>
									<div class="media-body">
										<p class="pull-right">
											<small>2 weeks ago</small>
										</p>
										<h4 class="media-heading body-text">Andrew Smith</h4>
										<p class="font-12">Nulla vitae elit libero, a pharetra
											augue.</p>
									</div>
							</a></li>
						</ul>
					</li>
					<li class="footer"><a href="javascript:void(0);">View All
							Messages</a></li>
				</ul></li>
			<!-- #END# Task -->

			<!-- login -->
			<li class="dropdown"><a href="javascript:void(0);"
				class="dropdown-toggle" data-toggle="dropdown" role="button"> <i
					class="material-icons">person</i>

			</a>
				<ul class="dropdown-menu">

					<!--  li class="header">NOTIFICATIONS</li>-->
					<li class="header"><a href="${contextRoot}/logout">Log Out</a></li>
					<%-- <li class="header"><a href="${contextRoot}/signout">Sign
							Out</a></li> --%>

				</ul></li>
			<!-- #END# Notifications -->

			<li><a href="#" data-toggle-state="offsidebar-open"
				data-no-persist="true"> <em class="material-icons">more_vert</em>
			</a></li>
		</ul>
		<!-- #END# Right Navbar-->
	</div>
	<!-- #END# Nav wrapper-->
	<!-- START Search form-->
	<form role="search" action="#" class="navbar-form">
		<div class="form-group has-feedback">
			<input type="text" placeholder="Type and search ..."
				class="form-control"> <em data-search-dismiss=""
				class="form-control-feedback material-icons">close</em>
		</div>
		<button type="submit" class="hidden btn btn-info">Submit</button>
	</form>
	<!-- #END# Search form-->

</nav>
<!-- END Top Navbar-->




