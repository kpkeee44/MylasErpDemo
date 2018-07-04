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
    <meta content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" name="viewport">
    <title>Amp Demo</title>
    <!-- Favicon-->
    <link rel="icon" href="${images}/favicon.png" type="image/x-icon">

    <!--REQUIRED PLUGIN CSS-->
    <link href="${plugins}/font-awesome/css/font-awesome.min.css" rel="stylesheet">
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
                
			
				
			
				 <div class="col-lg-12 col-md-12 col-sm-12 col-xs-12 topbar">
				 
	             <div class="col-md-9">
				 <div class="row pagetitle">
				<span > Employee </span>
				 </div>
				  </div>
				  

		
	         
	             <div class="col-md-3">
				 <div class="row pagetitle pull-right">
				 <div style="display: inline-flex; float: left;">
				 <span class=""><button id="detailview"  type="button" class="waves-effect emp_top_btn"><i class="icon-display material-icons"> view_module</i> </button>  </span>
				 <span class="" style="padding-right: 12px;"><button type="button" id="listview" class="waves-effect emp_top_btn"><i class="icon-display material-icons"> view_list</i> </button>  </span>
					 </div>
				 <span > 	 <button type="button" class="btn btn-primary btn-rounded waves-effect">Add Employee </button> </span>
			
				  </div>

                </div>
			
				</div>
				
							<div class="col-md-12 card">
				<div class="custom_title">	
				<h2> Form Elements</h2>
				</div>
				<hr class="custom_line">	
                            <div class="body">
                        
			 		 <div class="col-md-3">
						      <div class="form-group">
                                                                <div class="input-group addon-line">
                                                                    <div class="form-line">
																	   <label>CustomField1</label>
                                                                        <input type="text" class="form-control" placeholder="field1">
                                                                    </div>
                                                                    </div>
                                                            </div>
					
						 </div>
						  		 <div class="col-md-3">
						      <div class="form-group">
                                                                <div class="input-group addon-line">
                                                                    <div class="form-line">
																	   <label>CustomField2</label>
                                                                        <input type="text" class="form-control" placeholder="field2">
                                                                    </div>
                                                                    </div>
                                                            </div>
					
						 </div>
						  		 <div class="col-md-3">
						      <div class="form-group">
                                                                <div class="input-group addon-line">
                                                                    <div class="form-line">
																	   <label>CustomField3</label>
                                                                        <input type="text" class="form-control" placeholder="field3">
                                                                    </div>
                                                                    </div>
                                                            </div>
					
						 </div>
						   		 <div class="col-md-3">
						      <div class="form-group">
                                                                <div class="input-group addon-line">
                                                                    <div class="form-line">
																	   <label>CustomField4</label>
                                                                        <input type="text" class="form-control" placeholder="field4 ">
                                                                    </div>
                                                                    </div>
                                                            </div>
					
						 </div>
						  <div class="col-md-3">
						    <div class="form-group">
                                                <div class="form-line">
                                                    <label>CustomDropdown1</label>
                                                    <select class="form-control" size="1">
                                                        <option value="0">Please select</option>
                                                        <option value="1">Option1</option>
                                                        <option value="2">Option2</option>
                                                        <option value="3">Option3</option>
                                                    </select>
                                                </div>
                                            </div>
						 </div>
						 
						  <div class="col-md-3">
						    <div class="form-group">
                                                <div class="form-line">
                                                    <label>CustomDropdown2</label>
                                                    <select class="form-control" size="1">
                                                        <option value="0">Please select</option>
                                                        <option value="1">Option1</option>
                                                        <option value="2">Option2</option>
                                                        <option value="3">Option3</option>
                                                    </select>
                                                </div>
                                            </div>
						 </div>
						 
						 
						  <div class="col-md-3">
						    <div class="form-group">
                                                <div class="form-line">
                                                    <label>CustomDropdown3</label>
                                                    <select class="form-control" size="1">
                                                        <option value="0">Please select</option>
                                                        <option value="1">Option1</option>
                                                        <option value="2">Option2</option>
                                                        <option value="3">Option3</option>
                                                    </select>
                                                </div>
                                            </div>
						 </div>
						  <div class="col-md-3">
						    <div class="form-group">
                                                <div class="form-line">
                                                    <label>CustomDropdown4</label>
                                                    <select class="form-control" size="1">
                                                        <option value="0">Please select</option>
                                                        <option value="1">Option1</option>
                                                        <option value="2">Option2</option>
                                                        <option value="3">Option3</option>
                                                    </select>
                                                </div>
                                            </div>
						 </div>
						 
						 
						 
						 
						 
						 
						 
						 <div class="col-md-3">
						 <div class="form-group">
                                                <div class="form-line">
                                                    <label>CustomText Area1</label>
                                                    <textarea class="form-control" rows="3"></textarea>
                                                </div>
                                            </div>
											</div>
						 <div class="col-md-3">
						 <div class="form-group">
                                                <div class="form-line">
                                                    <label>CustomText Area2</label>
                                                    <textarea class="form-control" rows="3"></textarea>
                                                </div>
                                            </div>
											</div>
						 <div class="col-md-3">
						 <div class="form-group">
                                                <div class="form-line">
                                                    <label>CustomText Area3</label>
                                                    <textarea class="form-control" rows="3"></textarea>
                                                </div>
                                            </div>
											</div>
						 <div class="col-md-3">
						 <div class="form-group">
                                                <div class="form-line">
                                                    <label>CustomText Area4</label>
                                                    <textarea class="form-control" rows="3"></textarea>
                                                </div>
                                            </div>
											</div>
											<div class="col-md-3">
											<div class="form-group">
											<div class="form-inline">
						 <input type="checkbox" id="md_checkbox_26" class="filled-in chk-col-blue" checked="">
						 <label for="md_checkbox_26">Custom CheckBox1</label>
						 </div>
						 </div>
						 </div>
						<div class="col-md-3">
											<div class="form-group">
											<div class="form-inline">
						 <input type="checkbox" id="md_checkbox_26" class="filled-in chk-col-blue" checked="">
						 <label for="md_checkbox_26">Custom CheckBox1</label>
						 </div>
						 </div>
						 </div>
						<div class="col-md-3">
											<div class="form-group">
											<div class="form-inline">
						 <input type="checkbox" id="md_checkbox_26" class="filled-in chk-col-blue" checked="">
						 <label for="md_checkbox_26">Custom CheckBox1</label>
						 </div>
						 </div>
						 </div>
						<div class="col-md-3">
											<div class="form-group">
											<div class="form-inline">
						 <input type="checkbox" id="md_checkbox_26" class="filled-in chk-col-blue" checked="">
						 <label for="md_checkbox_26">Custom CheckBox1</label>
						 </div>
						 </div>
						 </div>
						 
						 <div class="col-md-3">
											<div class="form-group">
											<div class="form-inline">
						<input name="group5" type="radio" id="radio_35" class="with-gap radio-col-blue">
						<label for="radio_35">CustomRadio1</label>
						 </div>
						 </div>
						 </div>
						  <div class="col-md-3">
											<div class="form-group">
											<div class="form-inline">
						<input name="group5" type="radio" id="radio_36" class="with-gap radio-col-blue">
						<label for="radio_36">CustomRadio2</label>
						 </div>
						 </div>
						 </div>
						  <div class="col-md-3">
											<div class="form-group">
											<div class="form-inline">
						<input name="group5" type="radio" id="radio_37" class="with-gap radio-col-blue">
						<label for="radio_37">CustomRadio3</label>
						 </div>
						 </div>
						 </div>
						  <div class="col-md-3">
											<div class="form-group">
											<div class="form-inline">
						<input name="group5" type="radio" id="radio_38" class="with-gap radio-col-blue">
						<label for="radio_38">CustomRadio4</label>
						 </div>
						 </div>
						 </div>
						 
						 
						   <div class="col-md-3">
											<div class="form-group">
											<div class="form-inline">
						 <button type="button" class="btn btn-primary btn-rounded waves-effect">CustomButton1</button>
						 
						  </div>
						 </div>
						 </div>
						 
						  <div class="col-md-3">
											<div class="form-group">
											<div class="form-inline">
						<button type="button" class="btn btn-primary waves-effect">CustomButton2</button>
						 
						  </div>
						 </div>
						 </div>
						 
						   <div class="col-md-3">
											<div class="form-group">
											<div class="form-inline">
						<button type="button" class="btn btn-primary btn-outline btn-rounded waves-effect">CustomButton3</button>
						 
						  </div>
						 </div>
						 </div>
						   <div class="col-md-3">
											<div class="form-group">
											<div class="form-inline">
						<button type="button" class="btn btn-primary waves-effect">CustomButton4</button>
						 
						  </div>
						 </div>
						 </div>
						 
						 
						
					</div>
					
					</div>
               
		


</div>			
            <div class="container-fluid" id="list">
            
                <div class="row clearfix">
                
                    <div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
                    

                        <!-- Table Kitchen Sink -->
                        <div class="card">
                        
                            <div class="body">
                                <table class="tablesaw table-striped table-bordered table-hover" data-tablesaw-mode="swipe"
                                       data-tablesaw-sortable data-tablesaw-sortable-switch data-tablesaw-minimap
                                       data-tablesaw-mode-switch>
                                    <thead class="tableheding">
                                    <tr>
                                        <th data-tablesaw-sortable-col data-tablesaw-sortable-default-col data-tablesaw-priority="persist">KEY ID</th>
                                        <th data-tablesaw-sortable-col data-tablesaw-priority="3">ACTIVE</th>
                                        <th data-tablesaw-sortable-col data-tablesaw-priority="2">ASSIGNED TO</th>
                                        <th data-tablesaw-sortable-col data-tablesaw-priority="1">ASSIGNED NO </th>
                                      
                                    </tr>
                                    </thead>
                                    <tbody>
                                    <tr>
                                        <td><a href="javascript:void(0)">001</a></td>
                                        <td>1</td>
                                        <td>2015</td>
                                        <td>97%</td>
                                   
                                    </tr>
                                    <tr>
                                        <td><a href="javascript:void(0)">002</a></td>
                                        <td>2</td>
                                        <td>2014</td>
                                        <td>24%</td>
                                  
                                    </tr>
                                    <tr>
                                        <td><a href="javascript:void(0)">003</a></td>
                                        <td>3</td>
                                        <td>2014</td>
                                        <td>67%</td>
                                 
                                    </tr>
                                    <tr>
                                        <td><a href="javascript:void(0)">004</a></td>
                                        <td>4</td>
                                        <td>2015</td>
                                        <td>16%</td>
                                  
                                    </tr>
                                    <tr>
                                        <td><a href="javascript:void(0)">005</a></td>
                                        <td>5</td>
                                        <td>2015</td>
                                        <td>59%</td>
                                   
                                    </tr>
                                    <tr>
                                        <td><a href="javascript:void(0)">006</a></td>
                                        <td>6</td>
                                        <td>2014</td>
                                        <td>82%</td>
                                   
                                    </tr>
                                    <tr>
                                        <td><a href="javascript:void(0)">007</a></td>
                                        <td>7</td>
                                        <td>2015</td>
                                        <td>28%</td>
                                    
                                    </tr>
                                    <tr>
                                        <td><a href="javascript:void(0)">008</a></td>
                                        <td>8</td>
                                        <td>2015</td>
                                        <td>34%</td>
                                    
                                    </tr>
                                    <tr>
                                        <td><a href="javascript:void(0)">009</a></td>
                                        <td>9</td>
                                        <td>2014</td>
                                        <td>81%</td>
                                       
                                    </tr>
                                    <tr>
                                        <td><a href="javascript:void(0)">010</a></td>
                                        <td>10</td>
                                        <td>2015</td>
                                        <td>29%</td>
                                 
                                    </tr>
                                    <tr>
                                        <td><a href="javascript:void(0)">011</a></td>
                                        <td>11</td>
                                        <td>2014</td>
                                        <td>80%</td>
                                   
                                    </tr>
                                    <tr>
                                        <td><a href="javascript:void(0)">012</a></td>
                                        <td>12</td>
                                        <td>2015</td>
                                        <td>23%</td>
                                
                                    </tr>
                                    <tr>
                                        <td><a href="javascript:void(0)">013</a></td>
                                        <td>13</td>
                                        <td>2014</td>
                                        <td>38%</td>
                                     
                                    </tr>
                                    </tbody>
                                </table>
                            </div>
                        </div>
                        <!-- #END# Kitchen Sink -->

                   
                </div>
            </div>
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

</script>
</body>

</html>