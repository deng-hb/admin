<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!-- Left side column. contains the logo and sidebar -->
      <aside class="main-sidebar">

        <!-- sidebar: style can be found in sidebar.less -->
        <section class="sidebar">
          <!-- Sidebar Menu -->
          <ul class="sidebar-menu">
            <li class="header">Navigation</li>
            <!-- Optionally, you can add icons to the links -->
            <%-- 菜单 --%><!--             <li class="active"><a href="#"><i class="fa fa-link"></i> <span>Link</span></a></li> -->
            <c:set var="isParentStart" value="true"></c:set>
			<c:forEach var="res" items="${sessionScope.CURRENT_USER_RESOURCE_MENU }">
				<c:set var="isParent" value="${null == res.parentId}"></c:set><%-- 是否是父菜单 --%>
				<c:set var="isLink" value="${null != res.url}"></c:set><%-- 是否是链接 --%>
				<c:choose>
					<c:when test="${isParent}">
						<c:choose>
							<%-- 是链接 --%>
							<c:when test="${isLink}">
								<li><a href="${isLink?res.url:'#' }"><i class="fa fa-${res.icon }"></i> <span>${res.name}</span></a></li>
							</c:when>
							<%-- 是分组 --%>
							<c:otherwise>
								${isParentStart?'':'</ul>' }<%-- 结束之前的子链接 --%>
		            			<c:set var="isParentStart" value="false"></c:set>
								<li class="treeview">
								<a href="${isLink?res.url:'#' }"  res-id="${res.id}" ><i class="fa fa-link"></i> <span>${res.name}</span> <i class="fa fa-angle-left pull-right"></i></a>
								<ul class="treeview-menu">
							</c:otherwise>
						</c:choose>
					</c:when>
					<c:otherwise>
						</li>
						<li><a href="${isLink?res.url:'#' }"  ${uri eq res.url?'style="color:#fff"':'' } class="${isLink?'animsition-link':'' }"><i class="fa fa-link"></i> <span>${res.name}</span></a></li>
					</c:otherwise>
				</c:choose>
			</c:forEach>
        </section>
        <!-- /.sidebar -->
      </aside>
      
      