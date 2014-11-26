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
			<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
       		<c:set var="cureReqUri" value="${pageContext.request.requestURI}"/>	
			<%
			boolean isAdmin=((com.bps.dto.TadminUser)session.getAttribute("Logined")).getAdminRole().getRoleId()==1;
			Long userRights=null;
			if(session.getAttribute("rights")!=null){
				userRights=(Long)session.getAttribute("rights");
			}	
			if(!isAdmin){
			%>	
			<c:forEach var="menu" items="${menus}" varStatus="status">
				<jsp:useBean id="menuBean" class="com.bps.dto.TadminNodes"></jsp:useBean>	
				<jsp:setProperty name="menuBean" property="name" value="${menu.key.name}"></jsp:setProperty>
				<jsp:setProperty name="menuBean" property="bitFlag" value="${menu.key.bitFlag}"></jsp:setProperty>
			<%if(menuBean.getName().equals("Dashboard")&&(userRights&menuBean.getBitFlag())>0){ %>
			<li class="start active open">
				<a href="<c:url value="/"/>home">
				<i class="icon-home"></i> 
				<span class="title">Dashboard</span>
				<span class="selected"></span>
				</a>
			</li>
			<%} %>
			</c:forEach>
			<c:forEach var="menu" items="${menus}" varStatus="status">
				<jsp:useBean id="mangmenuBean" class="com.bps.dto.TadminNodes"></jsp:useBean>	
				<jsp:setProperty name="mangmenuBean" property="name" value="${menu.key.name}"></jsp:setProperty>
				<jsp:setProperty name="mangmenuBean" property="bitFlag" value="${menu.key.bitFlag}"></jsp:setProperty>
			<% 
			if(mangmenuBean.getName().equals("System Management")&&(userRights&mangmenuBean.getBitFlag())>0){
			%>	
            <li class="">
				<a href="javascript:;">
				<i class="icon-wrench"></i> 
				<span class="title">System Management</span>
				<span class="selected"></span>
				<span class="arrow"></span>				
				</a>
				<c:if test="${menu.value!=null}">
					<ul class="sub-menu">
					<c:forEach var="subMenu" items="${menu.value}" varStatus="substatus">						
						<jsp:useBean id="smenuBean" class="com.bps.dto.TadminNodes"></jsp:useBean>	
						<jsp:setProperty name="smenuBean" property="bitFlag" value="${subMenu.bitFlag}"></jsp:setProperty>
						<%if(isAdmin||(userRights&smenuBean.getBitFlag())>0){%>
					   <li class="${(selectedNode!=null&&subMenu.nodeId==selectedNode.nodeId)?'active':''}">
							<a href="${contextPath}${subMenu.uri}">
							${subMenu.name}
							</a>
						</li>
						<%} %>
					</c:forEach>											
					</ul>
				</c:if>	
			</li>
			<%} %>
			</c:forEach>
			<c:forEach var="menu" items="${menus}" varStatus="status">
				<jsp:useBean id="pusermenuBean" class="com.bps.dto.TadminNodes"></jsp:useBean>	
				<jsp:setProperty name="pusermenuBean" property="name" value="${menu.key.name}"></jsp:setProperty>
				<jsp:setProperty name="pusermenuBean" property="bitFlag" value="${menu.key.bitFlag}"></jsp:setProperty>
			<% 
			if(pusermenuBean.getName().equals("Point Users List")&&(userRights&pusermenuBean.getBitFlag())>0){
			%>	
            	<li class="">
				<a href="<c:url value="/"/>point">
				<i class="icon-users"></i> 
				<span class="title">Point Users List</span>					
				</a>					
			</li>
			<%} %>
			</c:forEach>
				<c:forEach var="menu" items="${menus}" varStatus="status">
				<jsp:useBean id="PrulemenuBean" class="com.bps.dto.TadminNodes"></jsp:useBean>	
				<jsp:setProperty name="PrulemenuBean" property="name" value="${menu.key.name}"></jsp:setProperty>
				<jsp:setProperty name="PrulemenuBean" property="bitFlag" value="${menu.key.bitFlag}"></jsp:setProperty>
			<% 
			if(PrulemenuBean.getName().equals("Bonus Rule")&&(userRights&PrulemenuBean.getBitFlag())>0){
			%>	
            <li class="">
				<a href="javascript:;">
				<i class="icon-wrench"></i> 
				<span class="title">Bonus Rule</span>
				<span class="selected"></span>
				<span class="arrow"></span>				
				</a>
				<c:if test="${menu.value!=null}">
					<ul class="sub-menu">
					<c:forEach var="subMenu" items="${menu.value}" varStatus="substatus">						
						<jsp:useBean id="rulemenuBean" class="com.bps.dto.TadminNodes"></jsp:useBean>	
						<jsp:setProperty name="rulemenuBean" property="bitFlag" value="${subMenu.bitFlag}"></jsp:setProperty>
						<%if(isAdmin||(userRights&rulemenuBean.getBitFlag())>0){%>
					   <li class="${(selectedNode!=null&&subMenu.nodeId==selectedNode.nodeId)?'active':''}">
							<a href="${contextPath}${subMenu.uri}">
							${subMenu.name}
							</a>
						</li>
						<%} %>
					</c:forEach>											
					</ul>
				</c:if>	
			</li>
			<%} %>
			</c:forEach>
				<c:forEach var="menu" items="${menus}" varStatus="status">
				<jsp:useBean id="logmenuBean" class="com.bps.dto.TadminNodes"></jsp:useBean>	
				<jsp:setProperty name="logmenuBean" property="name" value="${menu.key.name}"></jsp:setProperty>
				<jsp:setProperty name="logmenuBean" property="bitFlag" value="${menu.key.bitFlag}"></jsp:setProperty>
			<% 
			if(logmenuBean.getName().equals("Log List")&&(userRights&logmenuBean.getBitFlag())>0){
			%>	
            <li class="">
				<a href="javascript:;">
				<i class="icon-calendar"></i> 
				<span class="title">Log List</span>
				<span class="selected"></span>
				<span class="arrow"></span>				
				</a>
				<c:if test="${menu.value!=null}">
					<ul class="sub-menu">
					<c:forEach var="subMenu" items="${menu.value}" varStatus="substatus">						
						<jsp:useBean id="slogmenuBean" class="com.bps.dto.TadminNodes"></jsp:useBean>	
						<jsp:setProperty name="slogmenuBean" property="bitFlag" value="${subMenu.bitFlag}"></jsp:setProperty>
						<%if(isAdmin||(userRights&slogmenuBean.getBitFlag())>0){%>
					   <li class="${(selectedNode!=null&&subMenu.nodeId==selectedNode.nodeId)?'active':''}">
							<a href="${contextPath}${subMenu.uri}">
							${subMenu.name}
							</a>
						</li>
						<%} %>
					</c:forEach>											
					</ul>
				</c:if>	
			</li>
			<%} %>
			</c:forEach>
			<c:forEach var="menu" items="${menus}" varStatus="status">
				<jsp:useBean id="intermenuBean" class="com.bps.dto.TadminNodes"></jsp:useBean>	
				<jsp:setProperty name="intermenuBean" property="name" value="${menu.key.name}"></jsp:setProperty>
				<jsp:setProperty name="intermenuBean" property="bitFlag" value="${menu.key.bitFlag}"></jsp:setProperty>
			<% 
			if(intermenuBean.getName().equals("Interfaces List")&&(userRights&intermenuBean.getBitFlag())>0){
			%>	
            <li class="">
				<a href="<c:url value="/"/>home">
				<i class="icon-calendar"></i> 
				<span class="title">Interfaces List</span>
				<span class="selected"></span>
				<span class="arrow"></span>				
				</a>
			</li>
			<%} %>
			</c:forEach>
			<c:forEach var="menu" items="${menus}" varStatus="status">
				<jsp:useBean id="settingmenuBean" class="com.bps.dto.TadminNodes"></jsp:useBean>	
				<jsp:setProperty name="settingmenuBean" property="name" value="${menu.key.name}"></jsp:setProperty>
				<jsp:setProperty name="settingmenuBean" property="bitFlag" value="${menu.key.bitFlag}"></jsp:setProperty>
			<% 
			if(settingmenuBean.getName().equals("System Setting")&&(userRights&settingmenuBean.getBitFlag())>0){
			%>	
            <li class="">
				<a href="<c:url value="/"/>settings">
				<i class="icon-calendar"></i> 
				<span class="title">System Setting</span>
				<span class="selected"></span>
				<span class="arrow"></span>				
				</a>
			</li>
			<%} %>
			</c:forEach>
		<%} else{%>	
			<li class="start active open">
				<a href="<c:url value="/"/>home">
				<i class="icon-home"></i> 
				<span class="title">Dashboard</span>
				<span class="selected"></span>
				</a>
			</li>
			<c:forEach var="menu" items="${menus}" varStatus="status">
				<jsp:useBean id="mangsmenuBean" class="com.bps.dto.TadminNodes"></jsp:useBean>	
				<jsp:setProperty name="mangsmenuBean" property="name" value="${menu.key.name}"></jsp:setProperty>
			<% 
			if(mangsmenuBean.getName().equals("System Management")){
			%>	
            <li class="">
				<a href="javascript:;">
				<i class="icon-wrench"></i> 
				<span class="title">System Management</span>
				<span class="selected"></span>
				<span class="arrow"></span>				
				</a>
				<c:if test="${menu.value!=null}">
					<ul class="sub-menu">
					<c:forEach var="subMenu" items="${menu.value}" varStatus="substatus">							
					   <li class="${(selectedNode!=null&&subMenu.nodeId==selectedNode.nodeId)?'active':''}">
							<a href="${contextPath}${subMenu.uri}">
							${subMenu.name}
							</a>
						</li>	
					</c:forEach>											
					</ul>
				</c:if>	
			</li>
			<%} %>
			</c:forEach>
            	<li class="">
				<a href="<c:url value="/"/>point">
				<i class="icon-users"></i> 
				<span class="title">Point Users List</span>					
				</a>					
				</li>
				<c:forEach var="menu" items="${menus}" varStatus="status">
				<jsp:useBean id="PrulesmenuBean" class="com.bps.dto.TadminNodes"></jsp:useBean>	
				<jsp:setProperty name="PrulesmenuBean" property="name" value="${menu.key.name}"></jsp:setProperty>	
			<% 
			if(PrulesmenuBean.getName().equals("Bonus Rule")){
			%>	
            <li class="">
				<a href="javascript:;">
				<i class="icon-wrench"></i> 
				<span class="title">Bonus Rule</span>
				<span class="selected"></span>
				<span class="arrow"></span>				
				</a>
				<c:if test="${menu.value!=null}">
					<ul class="sub-menu">
					<c:forEach var="subMenu" items="${menu.value}" varStatus="substatus">												
					   <li class="${(selectedNode!=null&&subMenu.nodeId==selectedNode.nodeId)?'active':''}">
							<a href="${contextPath}${subMenu.uri}">
							${subMenu.name}
							</a>
						</li>
					</c:forEach>											
					</ul>
				</c:if>	
			</li>
			<%} %>
			</c:forEach>
				<c:forEach var="menu" items="${menus}" varStatus="status">
				<jsp:useBean id="logsmenuBean" class="com.bps.dto.TadminNodes"></jsp:useBean>	
				<jsp:setProperty name="logsmenuBean" property="name" value="${menu.key.name}"></jsp:setProperty>
			<% 
			if(logsmenuBean.getName().equals("Log List")){
			%>	
            <li class="">
				<a href="javascript:;">
				<i class="icon-calendar"></i> 
				<span class="title">Log List</span>
				<span class="selected"></span>
				<span class="arrow"></span>				
				</a>
				<c:if test="${menu.value!=null}">
					<ul class="sub-menu">
					<c:forEach var="subMenu" items="${menu.value}" varStatus="substatus">						
					   <li class="${(selectedNode!=null&&subMenu.nodeId==selectedNode.nodeId)?'active':''}">
							<a href="${contextPath}${subMenu.uri}">
							${subMenu.name}
							</a>
						</li>
					</c:forEach>											
					</ul>
				</c:if>	
			</li>
			<%} %>
			</c:forEach>		
            <li class="">
				<a href="<c:url value="/"/>interface">
				<i class="icon-calendar"></i> 
				<span class="title">Interfaces List</span>
				<span class="selected"></span>
				<span class="arrow"></span>				
				</a>
			</li>
            <li class="">
				<a href="<c:url value="/"/>settings">
				<i class="icon-calendar"></i> 
				<span class="title">System Setting</span>
				<span class="selected"></span>
				<span class="arrow"></span>				
				</a>
			</li>
		
		<%} %>
		</ul>
		<!-- END SIDEBAR MENU -->
	   </div>
    </div>    
   <!-- END SIDEBAR -->