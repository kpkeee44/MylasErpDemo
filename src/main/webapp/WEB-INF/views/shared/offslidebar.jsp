<%@ page import="java.util.*"%>
<%@ page import="java.text.SimpleDateFormat"%>
<%@ page import="mylas.com.erp.demo.daoimpl.RoletransfoeDaoImpl"%>
<%
	response.setIntHeader("Refresh", 1800);
%>
<c:forEach items="${TransferRoleList}" var="transferRoleList">
	<c:if test="${transferRoleList.getStatus() == null}">
		<c:if
			test="${transferRoleList.getFrommanid() == User.getEid() || transferRoleList.getTomanid() == User.getEid()}">
			<c:set var="toDate" value="${transferRoleList.getTodate()}" />
			<c:set var="id" value="${transferRoleList.getId()}" />
			<c:set var="managerid" value="${transferRoleList.getFrommanid()}" />
			<%
				String ToDate = (String) pageContext.getAttribute("toDate");
							String manid = (String) pageContext.getAttribute("managerid");
							Integer id = (Integer) pageContext.getAttribute("id");
							SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
							String today = sdf.format(new Date());
							Date Today = sdf.parse(today);
							out.println(ToDate);
							Date tDate = sdf.parse(ToDate);
							if (Today.after(tDate) || Today.equals(tDate)) {
								RoletransfoeDaoImpl rolemethod = new RoletransfoeDaoImpl();
								rolemethod.returnToMainManager(manid, id);
							}
			%>
		</c:if>
	</c:if>
</c:forEach>




<aside class="offsidebar hide">
	<!-- START Off Sidebar (right)-->
	<nav>
		<div role="tabpanel">
			<!-- Nav tabs-->
			<ul role="tablist" class="nav nav-tabs nav-tabs-inline nav-justified">

				<li role="presentation"><a href="#app-settings"
					aria-controls="app-chat" role="tab" data-toggle="tab"> <em
						class="material-icons">settings</em>
				</a></li>
			</ul>
			<!-- Tab panes-->
			<div class="tab-content">

				<div id="app-settings" role="tabpanel"
					class="tab-pane fade active in">
					<div class="demo-settings ">
						<ul class="setting-list">
							<h2>Employee Settings</h2>
							<c:if test="${Role.equals('MANAGER_ROLE')}">
								<li><span>Manager On Leave</span>
									<div class="switch">
										<button type="button" id="switchbutton"
											class="btn btn-primary btn-circle  sidebar-btn-circle waves-effect waves-circle waves-float">
											<i class="material-icons">person</i>
										</button>

										<button type="button" id="switchbutton1"
											class="btn btn-primary btn-circle  sidebar-btn-circle waves-effect waves-circle waves-float">
											<i class="material-icons fa fa-close"></i>
										</button>

									</div>
									<form action="${contextRoot}/manager/roletransfer"
										method="post" onsubmit="return validate1()" name="form">
										<div style="padding: 10px;" id="sidebarform">
											<div class="form-group">
												<div class="input-group addon-line">
													<div class="form-line">
														<label>Manager Name</label> <select class="form-control"
															size="1" name="managerId" id="managerid">
															<option value="0">Please select</option>
															<c:forEach items="${employees}" var="deper">
																<c:if test="${deper.getRole().equals('MANAGER_ROLE')}">
																	<c:if test="${deper.getEid() != User.getEid()}">
																		<option value="${deper.getEid()}">${deper.getFname()}
																			${deper.getLname()}</option>
																	</c:if>
																</c:if>
															</c:forEach>
														</select>
													</div>
												</div>
											</div>

											<div class="form-group">
												<div class="input-group addon-line">
													<div class="form-line">
														<label>From Date </label> <input type="date"
															class="form-control" placeholder="From Date"
															name="fromdate" id="manfromdate">
													</div>
													<span class="input-group-addon"><i
														class="material-icons">date_range </i></span>
												</div>
											</div>

											<div class="form-group">
												<div class="input-group addon-line">
													<div class="form-line">
														<label>To Date </label> <input type="date"
															class="form-control" placeholder="To Date" name="todate"
															id="mantodate">
													</div>
													<span class="input-group-addon"><i
														class="material-icons">date_range </i></span>
												</div>
											</div>

											<div>
												<button type="submit"
													class="btn btn-primary  pull-right waves-effect ">Change</button>
											</div>

										</div>
									</form></li>
							</c:if>
						</ul>
					</div>
				</div>
			</div>
		</div>
	</nav>
<%-- <c:forEach items="${TransferRoleList}" var="transferRoleList">
	<c:if test="${transferRoleList.getStatus() == null}">
${transferRoleList.getFrommanid()}
	</c:if>
		
	
	</c:forEach> --%>
	<!-- #END# Off Sidebar (right)-->
</aside>
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script>
	$(document).ready(function() {
		$("#sidebarform").css("display", "none");
		$("#switchbutton1").css("display", "none");

		$("#switchbutton").click(function(event) {
			$("#switchbutton1").css("display", "block");
			$("#sidebarform").css("display", "block");
			$("#switchbutton").css("display", "none");

		});

		$("#switchbutton1").click(function(event) {

			$("#sidebarform").css("display", "none");
			$("#switchbutton").css("display", "block");
			$("#switchbutton1").css("display", "none");
		});

	});

	function validate1() {
		var startDate = document.getElementById("manfromdate").value;
		var endDate = document.getElementById("mantodate").value;
		var opt = document.getElementById("managerid").value;

		if ((Date.parse(endDate) <= Date.parse(startDate))) {
			alert("To date should be greater than From date");
			fromdate.focus();
			return false;
		}
		if (opt == 0) {
			alert("please select manager name")
			managerid.focus();
			return false;

		}

	}
</script>