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
		<c:forEach var="menu" items="${menus}" varStatus="status">
			<c:forEach var="tempmenu" items="${menu.value}">
				<c:set var="tempMenuUri" value="${tempmenu.uri}"/>
				<c:if test="${tempMenuUri.startsWith('/')}">
					<c:set var="reqUri" value="${contextPath}${tempMenuUri}/"/>
				</c:if>
				<c:if test="${!tempMenuUri.startsWith('/')}">
					<c:set var="reqUri" value="${contextPath}/${tempMenuUri}/"/>
				</c:if>				
				<c:if test="${cureReqUri.startsWith(reqUri)}">					
					<c:set var="selectedNode" value="${tempmenu}"/>			
				</c:if>
			</c:forEach>						
			<c:if test="${status.first}">
				<c:if test="${selectedNode!=null&&selectedNode.nodeId==menu.key.nodeId}">		
					<c:set var="classStr" value="start active"/>									
				</c:if>
				<c:if test="${selectedNode!=null&&selectedNode.nodeId!=menu.key.nodeId&&selectedNode.pid==menu.key.nodeId}">		
					<c:set var="classStr" value="start active open"/>			
				</c:if>
				<c:if test="${selectedNode!=null&&selectedNode.nodeId!=menu.key.nodeId&&selectedNode.pid!=menu.key.nodeId}">		
					<c:set var="classStr" value="start"/>			
				</c:if>
			</c:if>
			<c:if test="!${status.first}">		
				<c:if test="${selectedNode!=null&&selectedNode.nodeId==menu.key.nodeId}">
					<c:set var="classStr" value="active"/>					
				</c:if>
				<c:if test="${selectedNode!=null&&selectedNode.nodeId!=menu.key.nodeId&&selectedNode.pid==menu.key.nodeId}">		
					<c:set var="classStr" value="active open"/>			
				</c:if>
				<c:if test="${selectedNode!=null&&selectedNode.nodeId!=menu.key.nodeId&&selectedNode.pid!=menu.key.nodeId}">		
					<c:set var="classStr" value=""/>			
				</c:if>
			</c:if>	
			<jsp:useBean id="curBit" class="com.bps.dto.TadminNodes"></jsp:useBean>	
			<jsp:setProperty name="curBit" property="bitFlag" value="${menu.key.bitFlag}"></jsp:setProperty>	
			<%
			boolean isAdmin=((com.bps.dto.TadminUser)session.getAttribute("Logined")).getAdminRole().getRoleId()==1;
			long userRights=0;
			if(session.getAttribute("rights")!=null){
				userRights=(long)session.getAttribute("rights");
			}			
			if(isAdmin||(userRights&curBit.getBitFlag())>0){
				
			%>	
			<li class="${classStr}">
				<c:choose>
					<c:when test="${menu.key.uri eq '/home'}">
						<c:set var="menuIcon" value="icon-home"/>						
					</c:when>
					<c:when test="${menu.key.uri eq '/rights'}">
						<c:set var="menuIcon" value="icon-wrench"/>
					</c:when>
					<c:when test="${menu.key.uri eq '/setting'}">
						<c:set var="menuIcon" value="icon-settings"/>
					</c:when>
					<c:when test="${menu.key.uri eq '/logs'}">
						<c:set var="menuIcon" value="icon-feed"/>
					</c:when>
					<c:when test="${menu.key.uri eq '/points'}">
						<c:set var="menuIcon" value="icon-note"/>
					</c:when>
					<c:otherwise>
						<c:set var="menuIcon" value="icon-home"/>
					</c:otherwise>
				</c:choose>
				
			
				<c:set var="menuUri" value="${pageContext.request.contextPath}${menu.key.uri}"/>
				<a href="${menu.value!=null?'javascript;':menu.key.uri}">
				<i class="<c:out value="${menuIcon}"/>"></i> 
				<span class="title"><c:out value="${menu.key.name}"/></span>
				<c:if test="${selectedNode!=null&&selectedNode.nodeId==menu.key.nodeId}">				
					<span class="selected"></span>								
				</c:if>	
				<c:if test="${selectedNode!=null&&selectedNode.pid!=0&&selectedNode.pid==menu.key.nodeId}">
					<span class="arrow open"></span>
				</c:if>
				<c:if test="${selectedNode!=null&&selectedNode.pid!=0&&selectedNode.pid!=menu.key.nodeId}">
					<span class="arrow"></span>
				</c:if>		
				</a>
				<c:if test="${menu.value!=null}">
					<ul class="sub-menu">
					<c:forEach var="subMenu" items="${menu.value}" varStatus="substatus">						
						<jsp:useBean id="menuBean" class="com.bps.dto.TadminNodes"></jsp:useBean>	
						<jsp:setProperty name="menuBean" property="bitFlag" value="${subMenu.bitFlag}"></jsp:setProperty>
						<%if(isAdmin||(userRights&menuBean.getBitFlag())>0){%>
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
			
		</ul>
		<!-- END SIDEBAR MENU -->
	   </div>
    </div>    
   <!-- END SIDEBAR -->