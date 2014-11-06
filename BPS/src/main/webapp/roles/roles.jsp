<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%><%@ taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core"%><%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%><%@ taglib prefix="s" uri="http://www.springframework.org/tags"%><!DOCTYPE html>
<!--[if IE 8]> <html lang="en" class="ie8"> <![endif]-->
<!--[if IE 9]> <html lang="en" class="ie9"> <![endif]-->
<!--[if !IE]><!--> <html lang="en"> <!--<![endif]-->
<!-- BEGIN HEAD -->
<head>
	<meta charset="utf-8" />
	<title>Metronic | Form Stuff - Advance Form Samples</title>
	<meta content="width=device-width, initial-scale=1.0" name="viewport" />
	<meta content="" name="description" />
	<meta content="" name="author" />
	<!-- BEGIN GLOBAL MANDATORY STYLES -->
	<link href="../media/css/bootstrap.min.css" rel="stylesheet" type="text/css"/>
	<link href="../media/css/bootstrap-responsive.min.css" rel="stylesheet" type="text/css"/>
	<link href="../media/css/font-awesome.min.css" rel="stylesheet" type="text/css"/>
	<link href="../media/css/style-metro.css" rel="stylesheet" type="text/css"/>
	<link href="../media/css/style.css" rel="stylesheet" type="text/css"/>
	<link href="../media/css/style-responsive.css" rel="stylesheet" type="text/css"/>
	<link href="../media/css/default.css" rel="stylesheet" type="text/css" id="style_color"/>
	<link href="../media/css/uniform.default.css" rel="stylesheet" type="text/css"/>
	<!-- END GLOBAL MANDATORY STYLES -->
	<!-- BEGIN PAGE LEVEL STYLES --> 
	<link rel="stylesheet" type="text/css" href="../media/css/select2_metro.css" />
	<!-- END PAGE LEVEL SCRIPTS -->
	<link rel="shortcut icon" href="../media/image/favicon.ico" />
</head>
<!-- END HEAD -->
<!-- BEGIN BODY -->
<body class="page-header-fixed">
	<!-- BEGIN HEADER -->	    <c:import url="/common/header"/>
	<!-- END HEADER -->
	<!-- BEGIN CONTAINER -->
	<div class="page-container row-fluid">
		<!-- BEGIN SIDEBAR -->
		<c:import url="/common/left"/>
		<!-- END SIDEBAR -->
		<!-- BEGIN PAGE -->  
		<div class="page-content">
			<!-- BEGIN SAMPLE PORTLET CONFIGURATION MODAL FORM-->
			<div id="portlet-config" class="modal hide">
				<div class="modal-header">
					<button data-dismiss="modal" class="close" type="button"></button>
					<h3>portlet Settings</h3>
				</div>
				<div class="modal-body">
					<p>Here will be a configuration form</p>
				</div>
			</div>
			<!-- END SAMPLE PORTLET CONFIGURATION MODAL FORM-->
			<!-- BEGIN PAGE CONTAINER-->
			<div class="container-fluid">
				<!-- BEGIN PAGE HEADER-->   
				<div class="row-fluid">
					<div class="span12">
						<!-- BEGIN STYLE CUSTOMIZER -->
						<div class="color-panel hidden-phone">
							<div class="color-mode-icons icon-color"></div>
							<div class="color-mode-icons icon-color-close"></div>
							<div class="color-mode">
								<p>THEME COLOR</p>
								<ul class="inline">
									<li class="color-black current color-default" data-style="default"></li>
									<li class="color-blue" data-style="blue"></li>
									<li class="color-brown" data-style="brown"></li>
									<li class="color-purple" data-style="purple"></li>
									<li class="color-grey" data-style="grey"></li>
									<li class="color-white color-light" data-style="light"></li>
								</ul>
								<label>
									<span>Layout</span>
									<select class="layout-option m-wrap small">
										<option value="fluid" selected>Fluid</option>
										<option value="boxed">Boxed</option>
									</select>
								</label>
								<label>
									<span>Header</span>
									<select class="header-option m-wrap small">
										<option value="fixed" selected>Fixed</option>
										<option value="default">Default</option>
									</select>
								</label>
								<label>
									<span>Sidebar</span>
									<select class="sidebar-option m-wrap small">
										<option value="fixed">Fixed</option>
										<option value="default" selected>Default</option>
									</select>
								</label>
								<label>
									<span>Footer</span>
									<select class="footer-option m-wrap small">
										<option value="fixed">Fixed</option>
										<option value="default" selected>Default</option>
									</select>
								</label>
							</div>
						</div>
						<!-- END BEGIN STYLE CUSTOMIZER -->  
						<h3 class="page-title">
							Advance Form Samples
							 <small>advance form layout samples</small>
						</h3>
						<ul class="breadcrumb">
							<li>
								<i class="icon-home"></i>
								<a href="index.html">Home</a> 
								<span class="icon-angle-right"></span>
							</li>
							<li>
								<a href="#">Form Stuff</a>
								<span class="icon-angle-right"></span>
							</li>
							<li><a href="#">Form Layouts</a></li>
						</ul>
					</div>
				</div>
				<!-- END PAGE HEADER-->
				<!-- BEGIN PAGE CONTENT-->
				<div class="row-fluid">
					<div class="span12">
						<div class="tabbable tabbable-custom boxless">
							<ul class="nav nav-tabs">
								<li class="active"><a href="#tab_1" data-toggle="tab">2 Columns</a></li>
								<li><a class="" href="#tab_2" data-toggle="tab">2 Columns Horizontal</a></li>
								<li><a href="#tab_3" data-toggle="tab">2 Columns View Only</a></li>
								<li><a class="" href="#tab_4" data-toggle="tab">Row Seperated</a></li>
								<li><a class="" href="#tab_5" data-toggle="tab">Bordered</a></li>
								<li><a class="" href="#tab_6" data-toggle="tab">Bordered & Row Stripped</a></li>
								<li><a class="" href="#tab_7" data-toggle="tab">Bordered & Label Stripped</a></li>
							</ul>
							<div class="tab-content">
								<div class="tab-pane active" id="tab_1">
									<div class="portlet box blue">
										<div class="portlet-title">
											<div class="caption"><i class="icon-reorder"></i>Form Sample</div>
											<div class="tools">
												<a href="javascript:;" class="collapse"></a>
												<a href="#portlet-config" data-toggle="modal" class="config"></a>
												<a href="javascript:;" class="reload"></a>
												<a href="javascript:;" class="remove"></a>
											</div>
										</div>
										<div class="portlet-body form">
											<!-- BEGIN FORM-->
											<form action="#" class="horizontal-form">
												<h3 class="form-section">Person Info</h3>
												<div class="row-fluid">
													<div class="span6 ">
														<div class="control-group">
															<label class="control-label" for="firstName">First Name</label>
															<div class="controls">
																<input type="text" id="firstName" class="m-wrap span12" placeholder="Chee Kin">
																<span class="help-block">This is inline help</span>
															</div>
														</div>
													</div>
													<!--/span-->
													<div class="span6 ">
														<div class="control-group error">
															<label class="control-label" for="lastName">Last Name</label>
															<div class="controls">
																<input type="text" id="lastName" class="m-wrap span12" placeholder="Lim">
																<span class="help-block">This field has error.</span>
															</div>
														</div>
													</div>
													<!--/span-->
												</div>
												<!--/row-->
												<div class="row-fluid">
													<div class="span6 ">
														<div class="control-group">
															<label class="control-label" >Gender</label>
															<div class="controls">
																<select  class="m-wrap span12">
																	<option value="">Male</option>
																	<option value="">Female</option>
																</select>
																<span class="help-block">Select your gender.</span>
															</div>
														</div>
													</div>
													<!--/span-->
													<div class="span6 ">
														<div class="control-group">
															<label class="control-label" >Date of Birth</label>
															<div class="controls">
																<input type="text" class="m-wrap span12"  placeholder="dd/mm/yyyy">
															</div>
														</div>
													</div>
													<!--/span-->
												</div>
												<!--/row-->        
												<div class="row-fluid">
													<div class="span6 ">
														<div class="control-group">
															<label class="control-label" >Category</label>
															<div class="controls">
																<select class="span12 select2_category" data-placeholder="Choose a Category" tabindex="1">
																	<option value=""></option>
																	<option value="Category 1">Category 1</option>
																	<option value="Category 2">Category 2</option>
																	<option value="Category 3">Category 5</option>
																	<option value="Category 4">Category 4</option>
																</select>
															</div>
														</div>
													</div>
													<!--/span-->
													<div class="span6 ">
														<div class="control-group">
															<label class="control-label" >Membership</label>
															<div class="controls">                                                
																<label class="radio">
																<input type="radio" name="optionsRadios2" value="option1" />
																Free
																</label>
																<label class="radio">
																<input type="radio" name="optionsRadios2" value="option2" checked />
																Professional
																</label>  
															</div>
														</div>
													</div>
													<!--/span-->
												</div>
												<!--/row--> 
												<h3 class="form-section">Address</h3>
												<div class="row-fluid">
													<div class="span12 ">
														<div class="control-group">
															<label class="control-label" >Street</label>
															<div class="controls">
																<input type="text" class="m-wrap span12" >
															</div>
														</div>
													</div>
												</div>
												<div class="row-fluid">
													<div class="span6 ">
														<div class="control-group">
															<label class="control-label" >City</label>
															<div class="controls">
																<input type="text"  class="m-wrap span12"> 
															</div>
														</div>
													</div>
													<!--/span-->
													<div class="span6 ">
														<div class="control-group">
															<label class="control-label" >State</label>
															<div class="controls">
																<input type="text"  class="m-wrap span12"> 
															</div>
														</div>
													</div>
													<!--/span-->
												</div>
												<!--/row-->           
												<div class="row-fluid">
													<div class="span6 ">
														<div class="control-group">
															<label class="control-label" >Post Code</label>
															<div class="controls">
																<input type="text" class="m-wrap span12"> 
															</div>
														</div>
													</div>
													<!--/span-->
													<div class="span6 ">
														<div class="control-group">
															<label class="control-label" >Country</label>
															<div class="controls">
																<select  class="m-wrap span12"></select>
															</div>
														</div>
													</div>
													<!--/span-->
												</div>
												<div class="form-actions">
													<button type="submit" class="btn blue"><i class="icon-ok"></i> Save</button>
													<button type="button" class="btn">Cancel</button>
												</div>
											</form>
											<!-- END FORM--> 
										</div>
									</div>
								</div>
								<div class="tab-pane " id="tab_2">
									<div class="portlet box green">
										<div class="portlet-title">
											<div class="caption"><i class="icon-reorder"></i>Form Sample</div>
											<div class="tools">
												<a href="javascript:;" class="collapse"></a>
												<a href="#portlet-config" data-toggle="modal" class="config"></a>
												<a href="javascript:;" class="reload"></a>
												<a href="javascript:;" class="remove"></a>
											</div>
										</div>
										<div class="portlet-body form">
											<!-- BEGIN FORM-->
											<form action="#" class="form-horizontal">
												<h3 class="form-section">Person Info</h3>
												<div class="row-fluid">
													<div class="span6 ">
														<div class="control-group">
															<label class="control-label">First Name</label>
															<div class="controls">
																<input type="text" class="m-wrap span12" placeholder="Chee Kin">
																<span class="help-block">This is inline help</span>
															</div>
														</div>
													</div>
													<!--/span-->
													<div class="span6 ">
														<div class="control-group error">
															<label class="control-label">Last Name</label>
															<div class="controls">
																<input type="text" class="m-wrap span12" placeholder="Lim">
																<span class="help-block">This field has error.</span>
															</div>
														</div>
													</div>
													<!--/span-->
												</div>
												<!--/row-->
												<div class="row-fluid">
													<div class="span6 ">
														<div class="control-group">
															<label class="control-label">Gender</label>
															<div class="controls">
																<select class="m-wrap span12">
																	<option value="">Male</option>
																	<option value="">Female</option>
																</select>
																<span class="help-block">Select your gender.</span>
															</div>
														</div>
													</div>
													<!--/span-->
													<div class="span6 ">
														<div class="control-group">
															<label class="control-label" >Date of Birth</label>
															<div class="controls">
																<input type="text" class="m-wrap span12" placeholder="dd/mm/yyyy">
															</div>
														</div>
													</div>
													<!--/span-->
												</div>
												<!--/row-->        
												<div class="row-fluid">
													<div class="span6 ">
														<div class="control-group">
															<label class="control-label">Category</label>
															<div class="controls">
																<select class="span12 select2_category"  data-placeholder="Choose a Category" tabindex="1">
																	<option value=""></option>
																	<option value="Category 1">Category 1</option>
																	<option value="Category 2">Category 2</option>
																	<option value="Category 3">Category 5</option>
																	<option value="Category 4">Category 4</option>
																</select>
															</div>
														</div>
													</div>
													<!--/span-->
													<div class="span6 ">
														<div class="control-group">
															<label class="control-label">Membership</label>
															<div class="controls">                                                
																<label class="radio">
																<input type="radio" name="optionsRadios2" value="option1" />
																Free
																</label>
																<label class="radio">
																<input type="radio" name="optionsRadios2" value="option2" checked />
																Professional
																</label>  
															</div>
														</div>
													</div>
													<!--/span-->
												</div>
												<h3 class="form-section">Address</h3>
												<!--/row-->                   
												<div class="row-fluid">
													<div class="span12 ">
														<div class="control-group">
															<label class="control-label">Street</label>
															<div class="controls">
																<input type="text" class="m-wrap span12" >
															</div>
														</div>
													</div>
												</div>
												<div class="row-fluid">
													<div class="span6 ">
														<div class="control-group">
															<label class="control-label">City</label>
															<div class="controls">
																<input type="text" class="m-wrap span12"> 
															</div>
														</div>
													</div>
													<!--/span-->
													<div class="span6 ">
														<div class="control-group">
															<label class="control-label" >State</label>
															<div class="controls">
																<input type="text"  class="m-wrap span12"> 
															</div>
														</div>
													</div>
													<!--/span-->
												</div>
												<!--/row-->           
												<div class="row-fluid">
													<div class="span6 ">
														<div class="control-group">
															<label class="control-label">Post Code</label>
															<div class="controls">
																<input type="text" class="m-wrap span12"> 
															</div>
														</div>
													</div>
													<!--/span-->
													<div class="span6 ">
														<div class="control-group">
															<label class="control-label">Country</label>
															<div class="controls">
																<select class="m-wrap span12">
																	<option>Country 1</option>
																	<option>Country 2</option>
																</select>
															</div>
														</div>
													</div>
													<!--/span-->
												</div>
												<!--/row-->
												<div class="form-actions">
													<button type="submit" class="btn blue"><i class="icon-ok"></i> Save</button>
													<button type="button" class="btn">Cancel</button>
												</div>
											</form>
											<!-- END FORM-->                
										</div>
									</div>
								</div>
								<div class="tab-pane " id="tab_3">
									<div class="portlet box blue">
										<div class="portlet-title">
											<div class="caption"><i class="icon-reorder"></i>Form Sample</div>
											<div class="tools">
												<a href="javascript:;" class="collapse"></a>
												<a href="#portlet-config" data-toggle="modal" class="config"></a>
												<a href="javascript:;" class="reload"></a>
												<a href="javascript:;" class="remove"></a>
											</div>
										</div>
										<div class="portlet-body form">
											<!-- BEGIN FORM-->
											<div class="form-horizontal form-view">
												<h3> View User Info - Bob Nilson </h3>
												<h3 class="form-section">Person Info</h3>
												<div class="row-fluid">
													<div class="span6 ">
														<div class="control-group">
															<label class="control-label" for="firstName">First Name:</label>
															<div class="controls">
																<span class="text">Bob</span>
															</div>
														</div>
													</div>
													<!--/span-->
													<div class="span6 ">
														<div class="control-group">
															<label class="control-label" for="lastName">Last Name:</label>
															<div class="controls">
																<span class="text">Nilson</span>
															</div>
														</div>
													</div>
													<!--/span-->
												</div>
												<!--/row-->
												<div class="row-fluid">
													<div class="span6 ">
														<div class="control-group">
															<label class="control-label" >Gender:</label>
															<div class="controls">
																<span class="text">Male</span> 
															</div>
														</div>
													</div>
													<!--/span-->
													<div class="span6 ">
														<div class="control-group">
															<label class="control-label" >Date of Birth:</label>
															<div class="controls">
																<span class="text bold">20.01.1984</span>
															</div>
														</div>
													</div>
													<!--/span-->
												</div>
												<!--/row-->        
												<div class="row-fluid">
													<div class="span6 ">
														<div class="control-group">
															<label class="control-label" >Category:</label>
															<div class="controls">
																<span class="text bold">Category1</span>
															</div>
														</div>
													</div>
													<!--/span-->
													<div class="span6 ">
														<div class="control-group">
															<label class="control-label" >Membership:</label>
															<div class="controls">                                                
																<span class="text bold">Free</span>
															</div>
														</div>
													</div>
													<!--/span-->
												</div>
												<!--/row-->                
												<h3 class="form-section">Address</h3>
												<div class="row-fluid">
													<div class="span12 ">
														<div class="control-group">
															<label class="control-label" >Street:</label>
															<div class="controls">
																<span class="text">#24 Sun Park Avenue, Rolton Str</span>
															</div>
														</div>
													</div>
												</div>
												<div class="row-fluid">
													<div class="span6 ">
														<div class="control-group">
															<label class="control-label" >City:</label>
															<div class="controls">
																<span class="text">New York</span>
															</div>
														</div>
													</div>
													<!--/span-->
													<div class="span6">
														<div class="control-group">
															<label class="control-label" >State:</label>
															<div class="controls">
																<span class="text">New York</span>
															</div>
														</div>
													</div>
													<!--/span-->
												</div>
												<!--/row-->           
												<div class="row-fluid">
													<div class="span6 ">
														<div class="control-group">
															<label class="control-label" >Post Code:</label>
															<div class="controls">
																<span class="text">457890</span>
															</div>
														</div>
													</div>
													<!--/span-->
													<div class="span6 ">
														<div class="control-group">
															<label class="control-label" >Country:</label>
															<div class="controls">
																<span class="text">USA</span>
															</div>
														</div>
													</div>
													<!--/span-->
												</div>
												<div class="form-actions">
													<button type="submit" class="btn blue"><i class="icon-pencil"></i> Edit</button>
													<button type="button" class="btn">Back</button>
												</div>
											</div>
											<!-- END FORM-->  
										</div>
									</div>
								</div>
								<div class="tab-pane"  id="tab_4">
									<div class="portlet box blue">
										<div class="portlet-title">
											<div class="caption"><i class="icon-reorder"></i>Form Sample</div>
											<div class="tools">
												<a href="javascript:;" class="collapse"></a>
												<a href="#portlet-config" data-toggle="modal" class="config"></a>
												<a href="javascript:;" class="reload"></a>
												<a href="javascript:;" class="remove"></a>
											</div>
										</div>
										<div class="portlet-body form">
											<!-- BEGIN FORM-->
											<form action="#" class="form-horizontal form-row-seperated">
												<div class="control-group">
													<label class="control-label">First Name</label>
													<div class="controls">
														<input type="text" placeholder="small" class="m-wrap span12" />
														<span class="help-inline">This is inline help</span>
													</div>
												</div>
												<div class="control-group">
													<label class="control-label">Last Name</label>
													<div class="controls">
														<input type="text" placeholder="medium" class="m-wrap span12" />
														<span class="help-inline">This is inline help</span>
													</div>
												</div>
												<div class="control-group">
													<label class="control-label">Gender</label>
													<div class="controls">
														<select  class="m-wrap span12">
															<option value="">Male</option>
															<option value="">Female</option>
														</select>
														<span class="help-block">Select your gender.</span>
													</div>
												</div>
												<div class="control-group">
													<label class="control-label" >Date of Birth</label>
													<div class="controls">
														<input type="text" class="m-wrap span12"  placeholder="dd/mm/yyyy">
													</div>
												</div>
												<div class="control-group">
													<label class="control-label" >Category</label>
													<div class="controls">
														<div class="select2-wrapper">
															<select class="span12 select2_category">
																<option value=""></option>
																<option value="Category 1">Category 1</option>
																<option value="Category 2">Category 2</option>
																<option value="Category 3">Category 5</option>
																<option value="Category 4">Category 4</option>
															</select>
														</div>
													</div>
												</div>
												<div class="control-group">
													<label class="control-label">Multi-Value Select</label>
													<div class="controls">
														<select class="span12 select2_sample1" multiple>
															<option value=""></option>
															<optgroup label="NFC EAST">
																<option>Dallas Cowboys</option>
																<option>New York Giants</option>
																<option>Philadelphia Eagles</option>
																<option>Washington Redskins</option>
															</optgroup>
															<optgroup label="NFC NORTH">
																<option>Chicago Bears</option>
																<option>Detroit Lions</option>
																<option>Green Bay Packers</option>
																<option>Minnesota Vikings</option>
															</optgroup>
															<optgroup label="NFC SOUTH">
																<option>Atlanta Falcons</option>
																<option>Carolina Panthers</option>
																<option>New Orleans Saints</option>
																<option>Tampa Bay Buccaneers</option>
															</optgroup>
															<optgroup label="NFC WEST">
																<option>Arizona Cardinals</option>
																<option>St. Louis Rams</option>
																<option>San Francisco 49ers</option>
																<option>Seattle Seahawks</option>
															</optgroup>
															<optgroup label="AFC EAST">
																<option>Buffalo Bills</option>
																<option>Miami Dolphins</option>
																<option>New England Patriots</option>
																<option>New York Jets</option>
															</optgroup>
															<optgroup label="AFC NORTH">
																<option>Baltimore Ravens</option>
																<option>Cincinnati Bengals</option>
																<option>Cleveland Browns</option>
																<option>Pittsburgh Steelers</option>
															</optgroup>
															<optgroup label="AFC SOUTH">
																<option>Houston Texans</option>
																<option>Indianapolis Colts</option>
																<option>Jacksonville Jaguars</option>
																<option>Tennessee Titans</option>
															</optgroup>
															<optgroup label="AFC WEST">
																<option>Denver Broncos</option>
																<option>Kansas City Chiefs</option>
																<option>Oakland Raiders</option>
																<option>San Diego Chargers</option>
															</optgroup>
														</select>
													</div>
												</div>
												<div class="control-group">
													<label class="control-label">Loading Data</label>
													<div class="controls">
														<input type="hidden" class="span12 select2_sample2">
													</div>
												</div>
												<div class="control-group">
													<label class="control-label">Tags Support List</label>
													<div class="controls">
														<input type="hidden" class="span12 select2_sample3" value="red, blue">
													</div>
												</div>
												<div class="control-group">
													<label class="control-label" >Membership</label>
													<div class="controls">                                                
														<label class="radio">
														<input type="radio" name="optionsRadios2" value="option1" />
														Free
														</label>
														<label class="radio">
														<input type="radio" name="optionsRadios2" value="option2" checked />
														Professional
														</label>  
													</div>
												</div>
												<div class="control-group">
													<label class="control-label" >Street</label>
													<div class="controls">
														<input type="text" class="m-wrap span12" >
													</div>
												</div>
												<div class="control-group">
													<label class="control-label" >City</label>
													<div class="controls">
														<input type="text"  class="m-wrap span12"> 
													</div>
												</div>
												<div class="control-group">
													<label class="control-label" >State</label>
													<div class="controls">
														<input type="text"  class="m-wrap span12"> 
													</div>
												</div>
												<div class="control-group">
													<label class="control-label" >Post Code</label>
													<div class="controls">
														<input type="text" class="m-wrap span12"> 
													</div>
												</div>
												<div class="control-group last">
													<label class="control-label" >Country</label>
													<div class="controls">
														<select  class="m-wrap span12"></select>
													</div>
												</div>
												<div class="form-actions">
													<button type="submit" class="btn blue"><i class="icon-ok"></i> Save</button>
													<button type="button" class="btn">Cancel</button>
												</div>
											</form>
											<!-- END FORM-->  
										</div>
									</div>
								</div>
								<div class="tab-pane"  id="tab_5">
									<div class="portlet box blue ">
										<div class="portlet-title">
											<div class="caption"><i class="icon-reorder"></i>Form Sample</div>
											<div class="tools">
												<a href="javascript:;" class="collapse"></a>
												<a href="#portlet-config" data-toggle="modal" class="config"></a>
												<a href="javascript:;" class="reload"></a>
												<a href="javascript:;" class="remove"></a>
											</div>
										</div>
										<div class="portlet-body form">
											<!-- BEGIN FORM-->
											<form action="#" class="form-horizontal form-bordered">
												<div class="control-group">
													<label class="control-label">First Name</label>
													<div class="controls">
														<input type="text" placeholder="small" class="m-wrap span12" />
														<span class="help-inline">This is inline help</span>
													</div>
												</div>
												<div class="control-group">
													<label class="control-label">Last Name</label>
													<div class="controls">
														<input type="text" placeholder="medium" class="m-wrap span12" />
														<span class="help-inline">This is inline help</span>
													</div>
												</div>
												<div class="control-group">
													<label class="control-label">Gender</label>
													<div class="controls">
														<select  class="m-wrap span12">
															<option value="">Male</option>
															<option value="">Female</option>
														</select>
														<span class="help-block">Select your gender.</span>
													</div>
												</div>
												<div class="control-group">
													<label class="control-label" >Date of Birth</label>
													<div class="controls">
														<input type="text" class="m-wrap span12"  placeholder="dd/mm/yyyy">
													</div>
												</div>
												<div class="control-group">
													<label class="control-label" >Category</label>
													<div class="controls">
														<div class="select2-wrapper">
															<select class="span12 select2_category">
																<option value=""></option>
																<option value="Category 1">Category 1</option>
																<option value="Category 2">Category 2</option>
																<option value="Category 3">Category 5</option>
																<option value="Category 4">Category 4</option>
															</select>
														</div>
													</div>
												</div>
												<div class="control-group">
													<label class="control-label">Multi-Value Select</label>
													<div class="controls">
														<div class="select2-wrapper">
															<select class="span12 select2_sample1" multiple>
																<option value=""></option>
																<optgroup label="NFC EAST">
																	<option>Dallas Cowboys</option>
																	<option>New York Giants</option>
																	<option>Philadelphia Eagles</option>
																	<option>Washington Redskins</option>
																</optgroup>
																<optgroup label="NFC NORTH">
																	<option>Chicago Bears</option>
																	<option>Detroit Lions</option>
																	<option>Green Bay Packers</option>
																	<option>Minnesota Vikings</option>
																</optgroup>
																<optgroup label="NFC SOUTH">
																	<option>Atlanta Falcons</option>
																	<option>Carolina Panthers</option>
																	<option>New Orleans Saints</option>
																	<option>Tampa Bay Buccaneers</option>
																</optgroup>
																<optgroup label="NFC WEST">
																	<option>Arizona Cardinals</option>
																	<option>St. Louis Rams</option>
																	<option>San Francisco 49ers</option>
																	<option>Seattle Seahawks</option>
																</optgroup>
																<optgroup label="AFC EAST">
																	<option>Buffalo Bills</option>
																	<option>Miami Dolphins</option>
																	<option>New England Patriots</option>
																	<option>New York Jets</option>
																</optgroup>
																<optgroup label="AFC NORTH">
																	<option>Baltimore Ravens</option>
																	<option>Cincinnati Bengals</option>
																	<option>Cleveland Browns</option>
																	<option>Pittsburgh Steelers</option>
																</optgroup>
																<optgroup label="AFC SOUTH">
																	<option>Houston Texans</option>
																	<option>Indianapolis Colts</option>
																	<option>Jacksonville Jaguars</option>
																	<option>Tennessee Titans</option>
																</optgroup>
																<optgroup label="AFC WEST">
																	<option>Denver Broncos</option>
																	<option>Kansas City Chiefs</option>
																	<option>Oakland Raiders</option>
																	<option>San Diego Chargers</option>
																</optgroup>
															</select>
														</div>
													</div>
												</div>
												<div class="control-group">
													<label class="control-label">Loading Data</label>
													<div class="controls">
														<div class="select2-wrapper">
															<input type="hidden" class="span12 select2_sample2">
														</div>
													</div>
												</div>
												<div class="control-group">
													<label class="control-label">Tags Support List</label>
													<div class="controls">
														<div class="select2-wrapper">
															<input type="hidden" class="span12 select2_sample3" value="red, blue">
														</div>
													</div>
												</div>
												<div class="control-group">
													<label class="control-label" >Membership</label>
													<div class="controls">                                                
														<label class="radio">
														<input type="radio" name="optionsRadios2" value="option1" />
														Free
														</label>
														<label class="radio">
														<input type="radio" name="optionsRadios2" value="option2" checked />
														Professional
														</label>  
													</div>
												</div>
												<div class="control-group">
													<label class="control-label" >Street</label>
													<div class="controls">
														<input type="text" class="m-wrap span12" >
													</div>
												</div>
												<div class="control-group">
													<label class="control-label" >City</label>
													<div class="controls">
														<input type="text"  class="m-wrap span12"> 
													</div>
												</div>
												<div class="control-group">
													<label class="control-label" >State</label>
													<div class="controls">
														<input type="text"  class="m-wrap span12"> 
													</div>
												</div>
												<div class="control-group">
													<label class="control-label" >Post Code</label>
													<div class="controls">
														<input type="text" class="m-wrap span12"> 
													</div>
												</div>
												<div class="control-group last">
													<label class="control-label" >Country</label>
													<div class="controls">
														<select  class="m-wrap span12"></select>
													</div>
												</div>
												<div class="form-actions">
													<button type="submit" class="btn blue"><i class="icon-ok"></i> Save</button>
													<button type="button" class="btn">Cancel</button>
												</div>
											</form>
											<!-- END FORM-->  
										</div>
									</div>
								</div>
								<div class="tab-pane"  id="tab_6">
									<div class="portlet box blue ">
										<div class="portlet-title">
											<div class="caption"><i class="icon-reorder"></i>Form Sample</div>
											<div class="tools">
												<a href="javascript:;" class="collapse"></a>
												<a href="#portlet-config" data-toggle="modal" class="config"></a>
												<a href="javascript:;" class="reload"></a>
												<a href="javascript:;" class="remove"></a>
											</div>
										</div>
										<div class="portlet-body form">
											<!-- BEGIN FORM-->
											<form action="#" class="form-horizontal form-bordered form-row-stripped">
												<div class="control-group">
													<label class="control-label">First Name</label>
													<div class="controls">
														<input type="text" placeholder="small" class="m-wrap span12" />
														<span class="help-inline">This is inline help</span>
													</div>
												</div>
												<div class="control-group">
													<label class="control-label">Last Name</label>
													<div class="controls">
														<input type="text" placeholder="medium" class="m-wrap span12" />
														<span class="help-inline">This is inline help</span>
													</div>
												</div>
												<div class="control-group">
													<label class="control-label">Gender</label>
													<div class="controls">
														<select  class="m-wrap span12">
															<option value="">Male</option>
															<option value="">Female</option>
														</select>
														<span class="help-block">Select your gender.</span>
													</div>
												</div>
												<div class="control-group">
													<label class="control-label" >Date of Birth</label>
													<div class="controls">
														<input type="text" class="m-wrap span12"  placeholder="dd/mm/yyyy">
													</div>
												</div>
												<div class="control-group">
													<label class="control-label" >Category</label>
													<div class="controls">
														<div class="select2-wrapper">
															<select class="span12 select2_category">
																<option value=""></option>
																<option value="Category 1">Category 1</option>
																<option value="Category 2">Category 2</option>
																<option value="Category 3">Category 5</option>
																<option value="Category 4">Category 4</option>
															</select>
														</div>
													</div>
												</div>
												<div class="control-group">
													<label class="control-label">Multi-Value Select</label>
													<div class="controls">
														<div class="select2-wrapper">
															<select class="span12 select2_sample1" multiple>
																<option value=""></option>
																<optgroup label="NFC EAST">
																	<option>Dallas Cowboys</option>
																	<option>New York Giants</option>
																	<option>Philadelphia Eagles</option>
																	<option>Washington Redskins</option>
																</optgroup>
																<optgroup label="NFC NORTH">
																	<option>Chicago Bears</option>
																	<option>Detroit Lions</option>
																	<option>Green Bay Packers</option>
																	<option>Minnesota Vikings</option>
																</optgroup>
																<optgroup label="NFC SOUTH">
																	<option>Atlanta Falcons</option>
																	<option>Carolina Panthers</option>
																	<option>New Orleans Saints</option>
																	<option>Tampa Bay Buccaneers</option>
																</optgroup>
																<optgroup label="NFC WEST">
																	<option>Arizona Cardinals</option>
																	<option>St. Louis Rams</option>
																	<option>San Francisco 49ers</option>
																	<option>Seattle Seahawks</option>
																</optgroup>
																<optgroup label="AFC EAST">
																	<option>Buffalo Bills</option>
																	<option>Miami Dolphins</option>
																	<option>New England Patriots</option>
																	<option>New York Jets</option>
																</optgroup>
																<optgroup label="AFC NORTH">
																	<option>Baltimore Ravens</option>
																	<option>Cincinnati Bengals</option>
																	<option>Cleveland Browns</option>
																	<option>Pittsburgh Steelers</option>
																</optgroup>
																<optgroup label="AFC SOUTH">
																	<option>Houston Texans</option>
																	<option>Indianapolis Colts</option>
																	<option>Jacksonville Jaguars</option>
																	<option>Tennessee Titans</option>
																</optgroup>
																<optgroup label="AFC WEST">
																	<option>Denver Broncos</option>
																	<option>Kansas City Chiefs</option>
																	<option>Oakland Raiders</option>
																	<option>San Diego Chargers</option>
																</optgroup>
															</select>
														</div>
													</div>
												</div>
												<div class="control-group">
													<label class="control-label">Loading Data</label>
													<div class="controls">
														<div class="select2-wrapper">
															<input type="hidden" class="span12 select2_sample2">
														</div>
													</div>
												</div>
												<div class="control-group">
													<label class="control-label">Tags Support List</label>
													<div class="controls">
														<div class="select2-wrapper">
															<input type="hidden" class="span12 select2_sample3" value="red, blue">
														</div>
													</div>
												</div>
												<div class="control-group">
													<label class="control-label" >Membership</label>
													<div class="controls">                                                
														<label class="radio">
														<input type="radio" name="optionsRadios2" value="option1" />
														Free
														</label>
														<label class="radio">
														<input type="radio" name="optionsRadios2" value="option2" checked />
														Professional
														</label>  
													</div>
												</div>
												<div class="control-group">
													<label class="control-label" >Street</label>
													<div class="controls">
														<input type="text" class="m-wrap span12" >
													</div>
												</div>
												<div class="control-group">
													<label class="control-label" >City</label>
													<div class="controls">
														<input type="text"  class="m-wrap span12"> 
													</div>
												</div>
												<div class="control-group">
													<label class="control-label" >State</label>
													<div class="controls">
														<input type="text"  class="m-wrap span12"> 
													</div>
												</div>
												<div class="control-group">
													<label class="control-label" >Post Code</label>
													<div class="controls">
														<input type="text" class="m-wrap span12"> 
													</div>
												</div>
												<div class="control-group last">
													<label class="control-label" >Country</label>
													<div class="controls">
														<select  class="m-wrap span12"></select>
													</div>
												</div>
												<div class="form-actions">
													<button type="submit" class="btn blue"><i class="icon-ok"></i> Save</button>
													<button type="button" class="btn">Cancel</button>
												</div>
											</form>
											<!-- END FORM-->  
										</div>
									</div>
								</div>
								<div class="tab-pane"  id="tab_7">
									<div class="portlet box blue ">
										<div class="portlet-title">
											<div class="caption"><i class="icon-reorder"></i>Form Sample</div>
											<div class="tools">
												<a href="javascript:;" class="collapse"></a>
												<a href="#portlet-config" data-toggle="modal" class="config"></a>
												<a href="javascript:;" class="reload"></a>
												<a href="javascript:;" class="remove"></a>
											</div>
										</div>
										<div class="portlet-body form">
											<!-- BEGIN FORM-->
											<form action="#" class="form-horizontal form-bordered form-label-stripped">
												<div class="control-group">
													<label class="control-label">First Name</label>
													<div class="controls">
														<input type="text" placeholder="small" class="m-wrap span12" />
														<span class="help-inline">This is inline help</span>
													</div>
												</div>
												<div class="control-group">
													<label class="control-label">Last Name</label>
													<div class="controls">
														<input type="text" placeholder="medium" class="m-wrap span12" />
														<span class="help-inline">This is inline help</span>
													</div>
												</div>
												<div class="control-group">
													<label class="control-label">Gender</label>
													<div class="controls">
														<select  class="m-wrap span12">
															<option value="">Male</option>
															<option value="">Female</option>
														</select>
														<span class="help-block">Select your gender.</span>
													</div>
												</div>
												<div class="control-group">
													<label class="control-label" >Date of Birth</label>
													<div class="controls">
														<input type="text" class="m-wrap span12"  placeholder="dd/mm/yyyy">
													</div>
												</div>
												<div class="control-group">
													<label class="control-label" >Category</label>
													<div class="controls">
														<div class="select2-wrapper">
															<select class="span12 select2_category">
																<option value=""></option>
																<option value="Category 1">Category 1</option>
																<option value="Category 2">Category 2</option>
																<option value="Category 3">Category 5</option>
																<option value="Category 4">Category 4</option>
															</select>
														</div>
													</div>
												</div>
												<div class="control-group">
													<label class="control-label">Multi-Value Select</label>
													<div class="controls">
														<div class="select2-wrapper">
															<select class="span12 select2_sample1" multiple>
																<option value=""></option>
																<optgroup label="NFC EAST">
																	<option>Dallas Cowboys</option>
																	<option>New York Giants</option>
																	<option>Philadelphia Eagles</option>
																	<option>Washington Redskins</option>
																</optgroup>
																<optgroup label="NFC NORTH">
																	<option>Chicago Bears</option>
																	<option>Detroit Lions</option>
																	<option>Green Bay Packers</option>
																	<option>Minnesota Vikings</option>
																</optgroup>
																<optgroup label="NFC SOUTH">
																	<option>Atlanta Falcons</option>
																	<option>Carolina Panthers</option>
																	<option>New Orleans Saints</option>
																	<option>Tampa Bay Buccaneers</option>
																</optgroup>
																<optgroup label="NFC WEST">
																	<option>Arizona Cardinals</option>
																	<option>St. Louis Rams</option>
																	<option>San Francisco 49ers</option>
																	<option>Seattle Seahawks</option>
																</optgroup>
																<optgroup label="AFC EAST">
																	<option>Buffalo Bills</option>
																	<option>Miami Dolphins</option>
																	<option>New England Patriots</option>
																	<option>New York Jets</option>
																</optgroup>
																<optgroup label="AFC NORTH">
																	<option>Baltimore Ravens</option>
																	<option>Cincinnati Bengals</option>
																	<option>Cleveland Browns</option>
																	<option>Pittsburgh Steelers</option>
																</optgroup>
																<optgroup label="AFC SOUTH">
																	<option>Houston Texans</option>
																	<option>Indianapolis Colts</option>
																	<option>Jacksonville Jaguars</option>
																	<option>Tennessee Titans</option>
																</optgroup>
																<optgroup label="AFC WEST">
																	<option>Denver Broncos</option>
																	<option>Kansas City Chiefs</option>
																	<option>Oakland Raiders</option>
																	<option>San Diego Chargers</option>
																</optgroup>
															</select>
														</div>
													</div>
												</div>
												<div class="control-group">
													<label class="control-label">Loading Data</label>
													<div class="controls">
														<div class="select2-wrapper">
															<input type="hidden" class="span12 select2_sample2">
														</div>
													</div>
												</div>
												<div class="control-group">
													<label class="control-label">Tags Support List</label>
													<div class="controls">
														<div class="select2-wrapper">
															<input type="hidden" class="span12 select2_sample3" value="red, blue">
														</div>
													</div>
												</div>
												<div class="control-group">
													<label class="control-label" >Membership</label>
													<div class="controls">                                                
														<label class="radio">
														<input type="radio" name="optionsRadios2" value="option1" />
														Free
														</label>
														<label class="radio">
														<input type="radio" name="optionsRadios2" value="option2" checked />
														Professional
														</label>  
													</div>
												</div>
												<div class="control-group">
													<label class="control-label" >Street</label>
													<div class="controls">
														<input type="text" class="m-wrap span12" >
													</div>
												</div>
												<div class="control-group">
													<label class="control-label" >City</label>
													<div class="controls">
														<input type="text"  class="m-wrap span12"> 
													</div>
												</div>
												<div class="control-group">
													<label class="control-label" >State</label>
													<div class="controls">
														<input type="text"  class="m-wrap span12"> 
													</div>
												</div>
												<div class="control-group">
													<label class="control-label" >Post Code</label>
													<div class="controls">
														<input type="text" class="m-wrap span12"> 
													</div>
												</div>
												<div class="control-group last">
													<label class="control-label" >Country</label>
													<div class="controls">
														<select  class="m-wrap span12"></select>
													</div>
												</div>
												<div class="form-actions">
													<button type="submit" class="btn blue"><i class="icon-ok"></i> Save</button>
													<button type="button" class="btn">Cancel</button>
												</div>
											</form>
											<!-- END FORM-->  
										</div>
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>
				<!-- END PAGE CONTENT-->         
			</div>
			<!-- END PAGE CONTAINER-->
		</div>
		<!-- END PAGE -->  
	</div>
	<!-- END CONTAINER -->
	<!-- BEGIN FOOTER -->    <c:import url="/common/footer"/>
	<!-- END FOOTER -->
	<!-- BEGIN JAVASCRIPTS(Load javascripts at bottom, this will reduce page load time) -->
	<!-- BEGIN CORE PLUGINS -->
	<script src="../media/js/jquery-1.10.1.min.js" type="text/javascript"></script>
	<script src="../media/js/jquery-migrate-1.2.1.min.js" type="text/javascript"></script>
	<!-- IMPORTANT! Load jquery-ui-1.10.1.custom.min.js before bootstrap.min.js to fix bootstrap tooltip conflict with jquery ui tooltip -->
	<script src="../media/js/jquery-ui-1.10.1.custom.min.js" type="text/javascript"></script>      
	<script src="../media/js/bootstrap.min.js" type="text/javascript"></script>
	<!--[if lt IE 9]>
	<script src="../media/js/excanvas.min.js"></script>
	<script src="../media/js/respond.min.js"></script>  
	<![endif]-->   
	<script src="../media/js/jquery.slimscroll.min.js" type="text/javascript"></script>
	<script src="../media/js/jquery.blockui.min.js" type="text/javascript"></script>  
	<script src="../media/js/jquery.cookie.min.js" type="text/javascript"></script>
	<script src="../media/js/jquery.uniform.min.js" type="text/javascript" ></script>
	<!-- END CORE PLUGINS -->
	<!-- BEGIN PAGE LEVEL PLUGINS -->
	<script type="text/javascript" src="../media/js/select2.min.js"></script>
	<!-- END PAGE LEVEL PLUGINS -->
	<!-- BEGIN PAGE LEVEL SCRIPTS -->
	<script src="../media/js/app.js"></script>
	<script src="../media/js/form-samples.js"></script>   
	<!-- END PAGE LEVEL SCRIPTS -->
	<script>
		jQuery(document).ready(function() {    
		   // initiate layout and plugins
		   App.init();
		   FormSamples.init();
		});
	</script>
	<!-- END JAVASCRIPTS -->   
<script type="text/javascript">  var _gaq = _gaq || [];  _gaq.push(['_setAccount', 'UA-37564768-1']);  _gaq.push(['_setDomainName', 'keenthemes.com']);  _gaq.push(['_setAllowLinker', true]);  _gaq.push(['_trackPageview']);  (function() {    var ga = document.createElement('script'); ga.type = 'text/javascript'; ga.async = true;    ga.src = ('https:' == document.location.protocol ? 'https://' : 'http://') + 'stats.g.doubleclick.net/dc.js';    var s = document.getElementsByTagName('script')[0]; s.parentNode.insertBefore(ga, s);  })();</script></body>
<!-- END BODY -->
</html>