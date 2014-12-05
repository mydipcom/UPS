<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<!DOCTYPE html>
<!--[if IE 8]> <html lang="en" class="ie8 no-js"> <![endif]-->
<!--[if IE 9]> <html lang="en" class="ie9 no-js"> <![endif]-->
<!--[if !IE]><!-->
<html lang="en" class="no-js">
<!--<![endif]-->
<!-- BEGIN HEAD -->
<head>
<meta charset="utf-8" />
<title>Interface List</title>
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta content="width=device-width, initial-scale=1.0" name="viewport" />
<meta content="" name="description" />
<meta content="" name="author" />
<!-- BEGIN GLOBAL MANDATORY STYLES -->
<link
	href="../assets/global/plugins/font-awesome/css/font-awesome.min.css"
	rel="stylesheet" type="text/css" />
<link
	href="../assets/global/plugins/simple-line-icons/simple-line-icons.min.css"
	rel="stylesheet" type="text/css" />
<link href="../assets/global/plugins/bootstrap/css/bootstrap.min.css"
	rel="stylesheet" type="text/css" />
<link href="../assets/global/plugins/uniform/css/uniform.default.css"
	rel="stylesheet" type="text/css" />
<link
	href="../assets/global/plugins/bootstrap-switch/css/bootstrap-switch.min.css"
	rel="stylesheet" type="text/css" />
<!-- END GLOBAL MANDATORY STYLES -->
<!-- BEGIN PAGE LEVEL STYLES -->
<link href="../assets/global/plugins/select2/select2.css"
	rel="stylesheet" type="text/css" />
<link
	href="../assets/global/plugins/datatables/plugins/bootstrap/dataTables.bootstrap.css"
	rel="stylesheet" type="text/css" />
<link
	href="../assets/global/plugins/bootstrap-modal/css/bootstrap-modal-bs3patch.css"
	rel="stylesheet" type="text/css" />
<link
	href="../assets/global/plugins/bootstrap-modal/css/bootstrap-modal.css"
	rel="stylesheet" type="text/css" />
<link rel="stylesheet" type="text/css"
	href="../assets/global/plugins/bootstrap-datepicker/css/datepicker.css" />
<!-- END PAGE LEVEL STYLES -->
<!-- BEGIN THEME STYLES -->
<link href="../assets/global/css/components.css" rel="stylesheet"
	type="text/css" />
<link href="../assets/global/css/plugins.css" rel="stylesheet"
	type="text/css" />
<link href="../assets/admin/layout/css/layout.css" rel="stylesheet"
	type="text/css" />
<link id="style_color"
	href="../assets/admin/layout/css/themes/default.css" rel="stylesheet"
	type="text/css" />
<link href="../assets/admin/layout/css/custom.css" rel="stylesheet"
	type="text/css" />
<!-- END THEME STYLES -->
<link rel="shortcut icon" href="../media/image/favicon.ico" />
<style type="text/css">
.api-title {
	background: url("../static/images/api_title_backgroud.png") repeat
		scroll 100% 0 transparent;
	display: block;
	height: 30px;
	line-height: 30px;
	margin-bottom: 15px;
	margin-top: 35px;
	padding-left: 8px;
	font-size: 14px;
	font-family: \5fae\8f6f\96c5\9ed1;
}
</style>
</head>
<!-- END HEAD -->
<!-- BEGIN BODY -->
<!-- DOC: Apply "page-header-fixed-mobile" and "page-footer-fixed-mobile" class to body element to force fixed header or footer in mobile devices -->
<!-- DOC: Apply "page-sidebar-closed" class to the body and "page-sidebar-menu-closed" class to the sidebar menu element to hide the sidebar by default -->
<!-- DOC: Apply "page-sidebar-hide" class to the body to make the sidebar completely hidden on toggle -->
<!-- DOC: Apply "page-sidebar-closed-hide-logo" class to the body element to make the logo hidden on sidebar toggle -->
<!-- DOC: Apply "page-sidebar-hide" class to body element to completely hide the sidebar on sidebar toggle -->
<!-- DOC: Apply "page-sidebar-fixed" class to have fixed sidebar -->
<!-- DOC: Apply "page-footer-fixed" class to the body element to have fixed footer -->
<!-- DOC: Apply "page-sidebar-reversed" class to put the sidebar on the right side -->
<!-- DOC: Apply "page-full-width" class to the body element to have full width page without the sidebar menu -->
<body class="page-header-fixed">
	<!-- BEGIN HEADER -->
	<c:import url="/common/header" />
	<!-- END HEADER -->
	<!-- BEGIN CONTAINER -->
	<div class="page-container">
		<!-- BEGIN SIDEBAR -->
		<c:import url="/common/left" />
		<!-- END SIDEBAR -->
		<!-- BEGIN CONTENT -->
		<div class="page-content-wrapper">
			<div class="page-content">
				<!-- BEGIN PAGE TITLE & BREADCRUMB-->
				<div class="page-bar">
					<ul class="page-breadcrumb">
						<li><i class="fa fa-home"></i> <a
							href="<c:url value="/"/>home"><s:message code="home" /></a> <i
							class="fa fa-angle-right"></i></li>
						<li><a href="<c:url value="/"/>interface"><s:message
									code="interface" /></a></li>
					</ul>
				</div>
				<!-- END PAGE TITLE & BREADCRUMB-->
				<!-- BEGIN PAGE CONTENT-->
				<div class="row">
					<div class="col-md-10">
						<div class="portlet-body">
							<ul class="nav nav-tabs">
								<c:forEach var="map" items="${interfaces}" varStatus="status">
									<c:choose>
										<c:when test="${status.getIndex() == 0}">
											<li class="active"><a href="#tab_${status.getIndex()}"
												data-toggle="tab" aria-expanded="false">${map.value[0]}</a></li>
										</c:when>
										<c:otherwise>
											<li class=""><a href="#tab_${status.getIndex()}"
												data-toggle="tab" aria-expanded="false">${map.value[0]}</a></li>
										</c:otherwise>
									</c:choose>
								</c:forEach>
							</ul>
							<div class="tab-content">
								<c:forEach var="map" items="${interfaces}" varStatus="status">
									<c:set var="key" value="${map.key}" />
									<c:choose>
										<c:when test="${status.getIndex() == 0}">
											<div class="tab-pane fade active in"
												id="tab_${status.getIndex()}">
												<div class="col-md-12">

													<div class="row">
														<div class="col-md-12">
															<div class="panel panel-primary">
																<!-- Default panel contents -->
																<div class="panel-heading">
																	<h3 class="panel-title">Request URL</h3>
																</div>
																<div class="panel-body">
																	<p>${map.value[1]}</p>
																</div>

															</div>
														</div>
													</div>
													<div class="row">
														<div class="col-md-12">
															<div class="panel panel-primary">
																<!-- Default panel contents -->
																<div class="panel-heading">
																	<h3 class="panel-title">API Describe</h3>
																</div>
																<div class="panel-body">
																	<p>${map.value[2]}</p>
																</div>

															</div>
														</div>
													</div>
													<div class="row">
														<div class="col-md-12">
															<div class="panel panel-primary">
																<!-- Default panel contents -->
																<div class="panel-heading">
																	<h3 class="panel-title">System level input
																		parameters</h3>
																</div>

																<!-- Table -->
																<table class="table">
																	<thead>
																		<tr>
																			<th>Name</th>
																			<th>Tpye</th>
																			<th>Required</th>
																			<th>Describe</th>
																		</tr>
																	</thead>
																	<tbody>
																		<tr>
																			<td>api_key</td>
																			<td>String</td>
																			<td>true</td>
																			<td>The application of AppKey</td>
																		</tr>
																	</tbody>
																</table>
															</div>
														</div>
													</div>

													<div class="row">
														<div class="col-md-12">
															<div class="panel panel-primary">
																<!-- Default panel contents -->
																<div class="panel-heading">
																	<h3 class="panel-title">Application level input
																		parameters</h3>
																</div>

																<!-- Table -->
																<table class="table">
																	<thead>
																		<tr>
																			<th>Name</th>
																			<th>Tpye</th>
																			<th>Required</th>
																			<th>Describe</th>
																		</tr>
																	</thead>
																	<tbody>

																		<c:forEach var="list"
																			items="${interfaceInputParam[key]}">
																			<tr>
																				<td>${list[0]}</td>
																				<td>${list[1]}</td>
																				<td>${list[2]}</td>
																				<td>${list[3]}</td>
																			</tr>
																		</c:forEach>
																	</tbody>
																</table>
															</div>
														</div>
													</div>

													<div class="row">
														<div class="col-md-12">
															<div class="panel panel-primary">
																<!-- Default panel contents -->
																<div class="panel-heading">
																	<h3 class="panel-title">Return result</h3>
																</div>

																<!-- Table -->
																<table class="table">
																	<thead>
																		<tr>
																			<th>Name</th>
																			<th>Tpye</th>
																			<th>Required</th>
																			<th>Describe</th>
																		</tr>
																	</thead>
																	<tbody>
																		<c:forEach var="list"
																			items="${interfaceOutParam[key]}">
																			<tr>
																				<td>${list[0]}</td>
																				<td>${list[1]}</td>
																				<td>${list[2]}</td>
																				<td>${list[3]}</td>
																			</tr>
																		</c:forEach>
																	</tbody>
																</table>
															</div>
														</div>
													</div>

												</div>
											</div>
										</c:when>
										<c:otherwise>
											<div class="tab-pane fade in" id="tab_1">
												<div class="col-md-12">

													<div class="row">
														<div class="col-md-12">
															<div class="panel panel-primary">
																<!-- Default panel contents -->
																<div class="panel-heading">
																	<h3 class="panel-title">Request URL</h3>
																</div>
																<div class="panel-body">
																	<p>${map.value[1]}</p>
																</div>

															</div>
														</div>
													</div>
													<div class="row">
														<div class="col-md-12">
															<div class="panel panel-primary">
																<!-- Default panel contents -->
																<div class="panel-heading">
																	<h3 class="panel-title">API Describe</h3>
																</div>
																<div class="panel-body">
																	<p>${map.value[2]}</p>
																</div>

															</div>
														</div>
													</div>
													<div class="row">
														<div class="col-md-12">
															<div class="panel panel-primary">
																<!-- Default panel contents -->
																<div class="panel-heading">
																	<h3 class="panel-title">System level input
																		parameters</h3>
																</div>

																<!-- Table -->
																<table class="table">
																	<thead>
																		<tr>
																			<th>Name</th>
																			<th>Tpye</th>
																			<th>Required</th>
																			<th>Describe</th>
																		</tr>
																	</thead>
																	<tbody>
																		<tr>
																			<td>api_key</td>
																			<td>String</td>
																			<td>true</td>
																			<td>The application of AppKey</td>
																		</tr>
																	</tbody>
																</table>
															</div>
														</div>
													</div>

													<div class="row">
														<div class="col-md-12">
															<div class="panel panel-primary">
																<!-- Default panel contents -->
																<div class="panel-heading">
																	<h3 class="panel-title">Application level input
																		parameters</h3>
																</div>

																<!-- Table -->
																<table class="table">
																	<thead>
																		<tr>
																			<th>Name</th>
																			<th>Tpye</th>
																			<th>Required</th>
																			<th>Describe</th>
																		</tr>
																	</thead>
																	<tbody>
																		<c:forEach var="list"
																			items="${interfaceInputParam[key]}">
																			<tr>
																				<td>${list[0]}</td>
																				<td>${list[1]}</td>
																				<td>${list[2]}</td>
																				<td>${list[3]}</td>
																			</tr>
																		</c:forEach>
																	</tbody>
																</table>
															</div>
														</div>
													</div>

													<div class="row">
														<div class="col-md-12">
															<div class="panel panel-primary">
																<!-- Default panel contents -->
																<div class="panel-heading">
																	<h3 class="panel-title">Return result</h3>
																</div>

																<!-- Table -->
																<table class="table">
																	<thead>
																		<tr>
																			<th>Name</th>
																			<th>Tpye</th>
																			<th>Required</th>
																			<th>Describe</th>
																		</tr>
																	</thead>
																	<tbody>
																		<c:forEach var="list"
																			items="${interfaceOutParam[key]}">
																			<tr>
																				<td>${list[0]}</td>
																				<td>${list[1]}</td>
																				<td>${list[2]}</td>
																				<td>${list[3]}</td>
																			</tr>
																		</c:forEach>
																	</tbody>
																</table>
															</div>
														</div>
													</div>

												</div>
											</div>
										</c:otherwise>
									</c:choose>
								</c:forEach>



							</div>
						</div>


					</div>
				</div>
			</div>
		</div>
	</div>

	<!-- END CONTAINER -->
	<!-- BEGIN FOOTER -->
	<c:import url="/common/footer" />
	<!-- END FOOTER -->
	<!-- BEGIN JAVASCRIPTS(Load javascripts at bottom, this will reduce page load time) -->
	<!-- BEGIN CORE PLUGINS -->
	<!--[if lt IE 9]>
	<script src="../assets/global/plugins/respond.min.js"></script>
	<script src="../assets/global/plugins/excanvas.min.js"></script> 
	<![endif]-->
	<script src="../assets/global/plugins/jquery-1.11.0.min.js"
		type="text/javascript"></script>
	<script src="../assets/global/plugins/jquery-migrate-1.2.1.min.js"
		type="text/javascript"></script>
	<!-- IMPORTANT! Load jquery-ui-1.10.3.custom.min.js before bootstrap.min.js to fix bootstrap tooltip conflict with jquery ui tooltip -->
	<script
		src="../assets/global/plugins/jquery-ui/jquery-ui-1.10.3.custom.min.js"
		type="text/javascript"></script>
	<script src="../assets/global/plugins/bootstrap/js/bootstrap.min.js"
		type="text/javascript"></script>
	<script
		src="../assets/global/plugins/bootstrap-hover-dropdown/bootstrap-hover-dropdown.min.js"
		type="text/javascript"></script>
	<script
		src="../assets/global/plugins/jquery-slimscroll/jquery.slimscroll.min.js"
		type="text/javascript"></script>
	<script src="../assets/global/plugins/jquery.blockui.min.js"
		type="text/javascript"></script>
	<script src="../assets/global/plugins/jquery.cokie.min.js"
		type="text/javascript"></script>
	<script
		src="../assets/global/plugins/jquery-validation/js/jquery.validate.min.js"
		type="text/javascript"></script>
	<script src="../assets/global/plugins/uniform/jquery.uniform.min.js"
		type="text/javascript"></script>
	<script
		src="../assets/global/plugins/bootstrap-switch/js/bootstrap-switch.min.js"
		type="text/javascript"></script>
	<!-- END CORE PLUGINS -->
	<!-- BEGIN PAGE LEVEL PLUGINS -->
	<script src="../assets/global/plugins/select2/select2.min.js"
		type="text/javascript"></script>
	<script
		src="../assets/global/plugins/datatables/media/js/jquery.dataTables.js"
		type="text/javascript"></script>
	<script
		src="../assets/global/plugins/datatables/plugins/bootstrap/dataTables.bootstrap.js"
		type="text/javascript"></script>
	<script
		src="../assets/global/plugins/bootstrap-modal/js/bootstrap-modalmanager.js"
		type="text/javascript"></script>
	<script
		src="../assets/global/plugins/bootstrap-modal/js/bootstrap-modal.js"
		type="text/javascript"></script>
	<script
		src="../assets/global/plugins/bootstrap-datepicker/js/bootstrap-datepicker.js"
		type="text/javascript"></script>
	<!-- END PAGE LEVEL PLUGINS -->
	<!-- BEGIN PAGE LEVEL SCRIPTS -->
	<script src="../assets/global/plugins/json/json2.js"
		type="text/javascript"></script>
	<script src="../assets/global/scripts/metronic.js"
		type="text/javascript"></script>
	<script src="../assets/admin/layout/scripts/layout.js"
		type="text/javascript"></script>
	<script src="../static/js/InterfaceTableData.js"></script>
	<script>
		jQuery(document).ready(function() {
			Metronic.init(); // init metronic core components
			Layout.init(); // init current layout	
			//Demo.init(); // init demo features
			InterfaceTable.init("<c:url value="/"/>");
		});
	</script>
</body>
<!-- END BODY -->

</html>