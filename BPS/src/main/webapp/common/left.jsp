<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
    <!-- BEGIN SIDEBAR -->
	<div class="page-sidebar-wrapper">
		<!-- DOC: Set data-auto-scroll="false" to disable the sidebar from auto scrolling/focusing -->
		<!-- DOC: Change data-auto-speed="200" to adjust the sub menu slide up/down speed -->
		<div class="page-sidebar navbar-collapse collapse">
		<!-- BEGIN SIDEBAR MENU -->        
		<ul class="page-sidebar-menu" data-auto-scroll="true" data-slide-speed="200">
			<!-- DOC: To remove the sidebar toggler from the sidebar you just need to completely remove the below "sidebar-toggler-wrapper" LI element -->
			<li class="sidebar-toggler-wrapper">
				<!-- BEGIN SIDEBAR TOGGLER BUTTON -->
				<div class="sidebar-toggler">
				</div>
				<!-- END SIDEBAR TOGGLER BUTTON -->
			</li>
			<!-- DOC: To remove the search box from the sidebar you just need to completely remove the below "sidebar-search-wrapper" LI element -->
			<li class="sidebar-search-wrapper">
				<!-- BEGIN RESPONSIVE QUICK SEARCH FORM -->
				<!-- DOC: Apply "sidebar-search-bordered" class the below search form to have bordered search box -->
				<!-- DOC: Apply "sidebar-search-bordered sidebar-search-solid" class the below search form to have bordered & solid search box -->
				<form class="sidebar-search " action="extra_search.html" method="POST">
					<a href="javascript:;" class="remove">
					<i class="icon-close"></i>
					</a>
					<div class="input-group">
						<input type="text" class="form-control" placeholder="Search...">
						<span class="input-group-btn">
						<a href="javascript:;" class="btn submit"><i class="icon-magnifier"></i></a>
						</span>
					</div>
				</form>
				<!-- END RESPONSIVE QUICK SEARCH FORM -->
			</li>
			<li class="start active open">
				<a href="<c:url value="/"/>home">
				<i class="icon-home"></i> 
				<span class="title">Dashboard</span>
				<span class="selected"></span>
				</a>
			</li>
			
			<li>
				<a href="javascript:;">
				<i class="icon-user"></i> 
				<span class="title">User Profile</span>
				<span class="arrow "></span>
				</a>                    
				<ul class="sub-menu">
				   <li >
						<a href="<c:url value="/"/>userprofile">
						Profile</a>
					</li>
					<li >
						<a href="extra_profile.html#tab_1_3">
						Account</a>
					</li>
					<li >
						<a href="extra_profile.html#tab_2-2">
						Change Avatar</a>
					</li>
					<li >
						<a href="extra_profile.html#tab_3-3">
						Change Password</a>
					</li>
					
				</ul>
			</li>
            <li class="">
				<a href="javascript:;">
				<i class="icon-wrench"></i> 
				<span class="title">System Management</span>
				<span class="selected"></span>
				<span class="arrow"></span>				
				</a>
				<ul class="sub-menu">
				    <li >
						<a href="<c:url value="/"/>manager">Manager List</a>
					</li>
					<li class="active">
						<a href="<c:url value="/"/>rights">Rights List</a>
					</li>
					<li>
						<a href="<c:url value="/"/>roles">Roles List</a>
					</li>						
				</ul>
			</li>
			<li class="">
				<a href="<c:url value="/"/>point">
				<i class="icon-users"></i> 
				<span class="title">Point Users List</span>					
				</a>					
			</li>				
			<li class="">
				<a href="<c:url value="/"/>manager">
				<i class="icon-users"></i> 
				<span class="title">Admin User</span>					
				</a>					
			</li>			
			<li class="">
				<a href="javascript:;">
				<i class="icon-note"></i> 
				<span class="title">Bonus Rule </span>
				<span class="arrow "></span>
				</a>
				<ul class="sub-menu">
					<li >
						<a href="<c:url value="/"/>rules">
						Bonus Rule Group</a>
					</li>
					<li >
						<a href="<c:url value="/"/>rules">
						Bonus Rule Configuration</a>
					</li>						
				</ul>
			</li>			
			<li>
				<a href="javascript:;">
				<i class="icon-notebook"></i> 
				<span class="title">Job List</span>				
				</a>				
			</li>
			<li class="">
				<a href="javascript:;">
				<i class="icon-calendar"></i> 
				<span class="title">Log List</span>
				<span class="arrow "></span>
				</a>
				<ul class="sub-menu">
					<li >
						<a href="login.html">
						System Log</a>
					</li>
					<li >
						<a href="<c:url value="/"/>ruleslog">
						Bonus Rule Change Log</a>
					</li>
					<li >
						<a href="<c:url value="/"/>pointlog">
						Bonus Point Change Log</a>
					</li>
					<li >
						<a href="login_soft.html">
						My Log</a>
					</li>
					<li >
						<a href="<c:url value="/"/>managerlog">
						Admin User Log</a>
					</li>
					<li >
						<a href="login_soft.html">
						Interface Access Log</a>
					</li>
				</ul>
			</li>
			<li class="">
				<a href="javascript:;">
				<i class="icon-feed"></i> 
				<span class="title">Interfaces List</span>
				<span class="arrow "></span>
				</a>
				<ul class="sub-menu">
					<li >
						<a href="table_basic.html">
						Basic Tables</a>
					</li>
					<li >
						<a href="table_responsive.html">
						Responsive Tables</a>
					</li>					
				</ul>
			</li>
			<li class="">
				<a href="javascript:;">
				<i class="icon-settings"></i> 
				<span class="title">System Setting</span>
				<span class="arrow "></span>
				</a>
				<ul class="sub-menu">
					<li >
						<a href="<c:url value="/"/>settings">
						General Portlets</a>
					</li>
					<li >
						<a href="portlet_draggable.html">
						Draggable Portlets</a>
					</li>
				</ul>
			</li>
			
		</ul>
		<!-- END SIDEBAR MENU -->
	   </div>
    </div>
   <!-- END SIDEBAR -->