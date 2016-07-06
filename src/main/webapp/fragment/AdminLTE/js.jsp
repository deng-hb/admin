<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
    <!-- REQUIRED JS SCRIPTS -->

    <!-- jQuery 2.1.4 -->
    <script src="/assets/plugins/jQuery/jQuery-2.1.4.min.js"></script>
	<!-- animsition.js -->
	<script src="/assets/plugins/animsition/js/animsition.min.js"></script>
	<script type="text/javascript" src="/assets/js/app.js"></script>
    <!-- Bootstrap 3.3.5 -->
    <script src="/assets/bootstrap/js/bootstrap.min.js"></script>
    <!-- AdminLTE App -->
    <script src="/assets/AdminLTE/js/app.min.js"></script>
	<c:if test="${-1 < fn:indexOf(param.js, 'iCheck')}">
    <script src="/assets/plugins/iCheck/icheck.min.js"></script>
	</c:if>
	<c:if test="${-1 < fn:indexOf(param.js, 'md5')}">
	<script type="text/javascript" src="/assets/js/md5.js"></script>
	</c:if>
	<c:if test="${-1 < fn:indexOf(param.js, 'jq.utils')}">
	<script type="text/javascript" src="/assets/js/jquery.form.min.js"></script>
	<script type="text/javascript" src="/assets/js/jq.utils.js"></script>
	</c:if>
	<c:if test="${-1 < fn:indexOf(param.js, 'dataTables')}">
	<!-- DataTables -->
    <script src="/assets/dataTables/jquery.dataTables.min.js"></script>
    <script src="/assets/dataTables/dataTables.bootstrap.min.js"></script>
	<script src="/assets/dataTables/dataTables.select.min.js"></script>
    <script src="/assets/AdminLTE/js/jq.table.utils.js"></script>
    </c:if>
    <c:if test="${-1 < fn:indexOf(param.js, 'zTree')}">
    <script type="text/javascript" src="/assets/ztree/js/jquery.ztree.core.min.js"></script>
    <script type="text/javascript" src="/assets/ztree/js/jquery.ztree.excheck.min.js"></script>
    </c:if>
    <c:if test="${-1 < fn:indexOf(param.js, 'drawer')}">
    <script type="text/javascript" src="/assets/plugins/bootstrap-drawer/js/drawer.min.js"></script>
    </c:if>
    <%-- <c:if test="${-1 < fn:indexOf(param.js, 'notify') }">
    </c:if> --%>
    <script type="text/javascript" src="/assets/plugins/bootstrap-notify/bootstrap-notify.min.js"></script>
   
    <script src="/assets/plugins/slimScroll/jquery.slimscroll.min.js"></script>
    <!-- FastClick -->
    <script src="/assets/plugins/fastclick/fastclick.min.js"></script>
    
    
    