<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<!DOCTYPE html>
<!--[if IE 8]> <html lang="en" class="ie8 no-js"> <![endif]-->
<!--[if IE 9]> <html lang="en" class="ie9 no-js"> <![endif]-->
<!--[if !IE]><!--> <html lang="en" class="no-js"> <!--<![endif]-->
<!-- BEGIN HEAD -->
<head>
<meta charset="utf-8"/>
<title>AdminUser Log List</title>
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta content="width=device-width, initial-scale=1.0" name="viewport"/>
<meta content="" name="description"/>
<meta content="" name="author"/>
<!-- BEGIN GLOBAL MANDATORY STYLES -->
<link href="../assets/global/plugins/font-awesome/css/font-awesome.min.css" rel="stylesheet" type="text/css"/>
<link href="../assets/global/plugins/simple-line-icons/simple-line-icons.min.css" rel="stylesheet" type="text/css"/>
<link href="../assets/global/plugins/bootstrap/css/bootstrap.min.css" rel="stylesheet" type="text/css"/>
<link href="../assets/global/plugins/uniform/css/uniform.default.css" rel="stylesheet" type="text/css"/>
<link href="../assets/global/plugins/bootstrap-switch/css/bootstrap-switch.min.css" rel="stylesheet" type="text/css"/>
<!-- END GLOBAL MANDATORY STYLES -->
<!-- BEGIN PAGE LEVEL STYLES -->
<link href="../assets/global/plugins/select2/select2.css" rel="stylesheet" type="text/css"/>
<link href="../assets/global/plugins/datatables/plugins/bootstrap/dataTables.bootstrap.css" rel="stylesheet" type="text/css"/>
<link href="../assets/global/plugins/bootstrap-modal/css/bootstrap-modal-bs3patch.css" rel="stylesheet" type="text/css"/>
<link href="../assets/global/plugins/bootstrap-modal/css/bootstrap-modal.css" rel="stylesheet" type="text/css"/>
<!-- END PAGE LEVEL STYLES -->
<!-- BEGIN THEME STYLES -->
<link href="../assets/global/css/components.css" rel="stylesheet" type="text/css"/>
<link href="../assets/global/css/plugins.css" rel="stylesheet" type="text/css"/>
<link href="../assets/admin/layout/css/layout.css" rel="stylesheet" type="text/css"/>
<link id="style_color" href="../assets/admin/layout/css/themes/default.css" rel="stylesheet" type="text/css"/>
<link href="../assets/admin/layout/css/custom.css" rel="stylesheet" type="text/css"/>
<!-- END THEME STYLES -->
<link rel="shortcut icon" href="../media/image/favicon.ico"/>
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
	<c:import url="/common/header"/>
	<!-- END HEADER -->
	<!-- BEGIN CONTAINER -->
	<div class="page-container">
		<!-- BEGIN SIDEBAR -->
		<c:import url="/common/left"/>
		<!-- END SIDEBAR -->
		<!-- BEGIN CONTENT -->
		<div class="page-content-wrapper">	
			<div class="page-content">								
				<!-- BEGIN PAGE TITLE & BREADCRUMB-->
				<div class="page-bar">
					<ul class="page-breadcrumb">
						<li>
							<i class="fa fa-home"></i>
							<a href="<c:url value="/"/>home">Home</a>
							<i class="fa fa-angle-right"></i>
						</li>
						<li>
							<a href="<c:url value="/"/>pointlog">Log List</a>
							<i class="fa fa-angle-right"></i>
						</li>
						<li>
							<a href="<c:url value="/"/>managerlog">Admin User Log</a>
						</li>
					</ul>					
				</div>
				<!-- END PAGE TITLE & BREADCRUMB-->
				
				<!-- BEGIN PAGE CONTENT-->
				<div class="row">
					<div class="col-md-12">
						<!-- BEGIN EXAMPLE TABLE PORTLET-->
						<div class="portlet box blue-hoki">
							<div class="portlet-title">
								<div class="caption">
									<i class="fa fa-edit"></i>Admin Logs Table
								</div>
								<div class="actions">									
								    <a class="btn btn-default btn-sm" data-toggle="modal" href="#delete_adminslog"><i class="fa fa-trash-o"></i> Delete</a>
								    <div class="btn-group">
										<a class="btn default" href="#" data-toggle="dropdown">
										Columns <i class="fa fa-angle-down"></i>
										</a>
										<div id="column_toggler" class="dropdown-menu hold-on-click dropdown-checkboxes pull-right">
											<label><input type="checkbox" checked data-column="0">Checkbox</label>
											<label><input type="checkbox" checked data-column="1">ID</label>
											<label><input type="checkbox" checked data-column="2">Admin Name</label>
											<label><input type="checkbox" checked data-column="3">Content</label>
											<label><input type="checkbox" checked data-column="4">Level</label>
											<label><input type="checkbox" checked data-column="5">Create Time</label>
										</div>
									</div>								    																
								</div>
							</div>							
							<div class="portlet-body">																
								<table class="table table-striped table-hover table-bordered" id="managerlog_table">
									<thead>
										<tr>
											<th class="table-checkbox">
												<input type="checkbox" class="group-checkable" data-set="#managerlog_table .checkboxes"/>
											</th>
											<th>ID</th>
											<th>Admin Name</th>
											<th>Content</th>
											<th>Level</th>
											<th>Create Time</th>
											<th>Action</th>
										</tr>
									</thead>
																						
								</table>
							</div>
						</div>
						<!-- END EXAMPLE TABLE PORTLET-->
					</div>
				</div>
				<!-- END PAGE CONTENT -->
						
								<!-- BEGIN Edit MODAL FORM-->
				<div class="modal" id="view_log" tabindex="-1" data-width="760">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal" aria-hidden="true"></button>
						<h4 class="modal-title">View Admin User Log</h4>
					</div>
					<div id="editFormMsg"></div>
					<!-- <div class="modal-body"> -->
					<div class="portlet-body form">
							<!-- BEGIN FORM	-->					
						<form id="viewAdminlogForm" action="" method="post" name="viewAdminlogForm" class="form-horizontal form-bordered">
							<div class="form-body">
								<div class="alert alert-danger display-hide">
									<button class="close" data-close="alert"></button>
									You have some form errors. Please check below.
								</div>
								<div class="form-group">
									<label class="control-label col-md-3">Id</label>
									<div class="col-md-9">										
										<input name="id" class="form-control" readonly="true"/>										
									</div>
								</div>						
								<div class="form-group">
									<label class="control-label col-md-3">Admin Name<span class="required"> * </span></label>
									<div class="col-md-9">										
										<input name="adminId" class="form-control" readonly="true"/>										
									</div>
								</div>
								<div class="form-group">
									<label class="control-label col-md-3">Content<span class="required">* </span></label>
									<div class="col-md-9">																				
										<textarea name="content" class="form-control"  readonly="true"></textarea>
									</div>		
								</div>
								<div class="form-group">
									<label class="control-label col-md-3">Level<span class="required">* </span></label>
									<div class="col-md-9">																				
										<input name="level" class="form-control" readonly="true"/>
									</div>		
								</div>									
								<div class="form-group">
									<label class="control-label col-md-3">Create Time<span class="required">* </span></label>
									<div class="col-md-9">
										<input name="createdTime" class="form-control" readonly="true"/>									
									</div>
								</div>	
										
							</div>
						</form>
						<!-- END FORM-->
				</div>	
				<div class="modal-footer"></div>
							
			</div>	
						
				<!-- BEGIN DELETE MODAL FORM-->
				<div class="modal" id="delete_adminslog" tabindex="-1" data-backdrop="static" data-keyboard="false">
					<div class="modal-body">
						<p>
							 Are you sure to delete these selected rows ?
						</p>
					</div>
					<div class="modal-footer">
						<button type="button" data-dismiss="modal" class="btn btn-default">Cancel</button>
						<button id="deleteBtn" type="button" data-dismiss="modal" class="btn blue">Confirm</button>
					</div>					
				</div>				
				<!-- END DELETE MODAL FORM-->
			</div>		
		</div>
	</div>	
	<!-- END CONTAINER -->
	<!-- BEGIN FOOTER -->
	<c:import url="/common/footer"/>
	<!-- END FOOTER -->
	<!-- BEGIN JAVASCRIPTS(Load javascripts at bottom, this will reduce page load time) -->
	<!-- BEGIN CORE PLUGINS -->
	<!--[if lt IE 9]>
	<script src="../assets/global/plugins/respond.min.js"></script>
	<script src="../assets/global/plugins/excanvas.min.js"></script> 
	<![endif]-->
	<script src="../assets/global/plugins/jquery-1.11.0.min.js" type="text/javascript"></script>
	<script src="../assets/global/plugins/jquery-migrate-1.2.1.min.js" type="text/javascript"></script>
	<!-- IMPORTANT! Load jquery-ui-1.10.3.custom.min.js before bootstrap.min.js to fix bootstrap tooltip conflict with jquery ui tooltip -->
	<script src="../assets/global/plugins/jquery-ui/jquery-ui-1.10.3.custom.min.js" type="text/javascript"></script>
	<script src="../assets/global/plugins/bootstrap/js/bootstrap.min.js" type="text/javascript"></script>
	<script src="../assets/global/plugins/bootstrap-hover-dropdown/bootstrap-hover-dropdown.min.js" type="text/javascript"></script>
	<script src="../assets/global/plugins/jquery-slimscroll/jquery.slimscroll.min.js" type="text/javascript"></script>
	<script src="../assets/global/plugins/jquery.blockui.min.js" type="text/javascript"></script>
	<script src="../assets/global/plugins/jquery.cokie.min.js" type="text/javascript"></script>
	<script src="../assets/global/plugins/jquery-validation/js/jquery.validate.min.js" type="text/javascript"></script>
	<script src="../assets/global/plugins/uniform/jquery.uniform.min.js" type="text/javascript"></script>
	<script src="../assets/global/plugins/bootstrap-switch/js/bootstrap-switch.min.js" type="text/javascript"></script>
	<!-- END CORE PLUGINS -->
	<!-- BEGIN PAGE LEVEL PLUGINS -->
	<script src="../assets/global/plugins/select2/select2.min.js" type="text/javascript"></script>
	<script src="../assets/global/plugins/datatables/media/js/jquery.dataTables.js" type="text/javascript"></script>
	<script src="../assets/global/plugins/datatables/plugins/bootstrap/dataTables.bootstrap.js" type="text/javascript"></script>
	<script src="../assets/global/plugins/bootstrap-modal/js/bootstrap-modalmanager.js" type="text/javascript"></script>
    <script src="../assets/global/plugins/bootstrap-modal/js/bootstrap-modal.js" type="text/javascript"></script>
	<!-- END PAGE LEVEL PLUGINS -->
	<!-- BEGIN PAGE LEVEL SCRIPTS -->
	<script src="../assets/global/plugins/json/json2.js" type="text/javascript"></script>
	<script src="../assets/global/scripts/metronic.js" type="text/javascript"></script>
	<script src="../assets/admin/layout/scripts/layout.js" type="text/javascript"></script>	
	<script src="../static/js/managerlogTableData.js"></script>
	<script>
	jQuery(document).ready(function() {       
	   Metronic.init(); // init metronic core components
	   Layout.init(); // init current layout	
	   //Demo.init(); // init demo features
	   ManagerLogTable.init("<c:url value="/"/>");	   
	});
	</script>
</body>
<!-- END BODY -->

</html>