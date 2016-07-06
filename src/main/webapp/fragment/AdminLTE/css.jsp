<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

    <link rel="stylesheet" href="/assets/css/app.css">
    <!-- animsition.css -->
	<link rel="stylesheet" href="/assets/plugins/animsition/css/animsition.min.css">
	<!-- Bootstrap 3.3.5 -->
    <link rel="stylesheet" href="/assets/bootstrap/css/bootstrap.min.css">
    <!-- Font Awesome -->
    <link rel="stylesheet" href="/assets/font-awesome-4.6.3/css/font-awesome.min.css">
    <!-- bootstrap-notify ref -->
    <link rel="stylesheet" href="/assets/css/animate.css" >
    <%-- <!-- Ionicons -->
    <link rel="stylesheet" href="https://code.ionicframework.com/ionicons/2.0.1/css/ionicons.min.css">
    --%>
    <!-- Theme style -->
    <link rel="stylesheet" href="/assets/AdminLTE/css/AdminLTE.min.css">
    <!-- AdminLTE Skins. We have chosen the skin-blue for this starter
          page. However, you can choose any other skin. Make sure you
          apply the skin class to the body tag so the changes take effect.
    -->
    <link rel="stylesheet" href="/assets/AdminLTE/css/skins/skin-blue.min.css">
	<c:if test="${-1 < fn:indexOf(param.css, 'iCheck')}">
    <!-- iCheck -->
    <link rel="stylesheet" href="/assets/plugins/iCheck/square/blue.css">
	</c:if>
	<c:if test="${-1 < fn:indexOf(param.css, 'dataTables')}">
	<link rel="stylesheet" href="/assets/dataTables/dataTables.bootstrap.css">
	<link rel="stylesheet" href="/assets/dataTables/select.dataTables.min.css" />
	</c:if>
	<c:if test="${-1 < fn:indexOf(param.css, 'zTree')}">
	<link rel="stylesheet" href="/assets/ztree/css/awesomeStyle/awesome.css" />
	</c:if>
	<c:if test="${-1 < fn:indexOf(param.css, 'drawer') }">
    <link rel="stylesheet" href="/assets/plugins/bootstrap-drawer/css/bootstrap-drawer.min.css">
	</c:if>
    <!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]>
        <script src="https://oss.maxcdn.com/html5shiv/3.7.3/html5shiv.min.js"></script>
        <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
    <![endif]-->
    
    