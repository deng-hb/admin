<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
    <!-- REQUIRED JS SCRIPTS -->

    <!-- jQuery 2.1.4 -->
    <script src="/assets/plugins/jQuery/jQuery-2.1.4.min.js"></script>
    <script src="/assets/js/jquery.cookie.js"></script>
	<!-- animsition.js -->
	<script src="/assets/plugins/animsition/js/animsition.min.js"></script>
	<script type="text/javascript" src="/assets/js/app.js"></script>
    <!-- Bootstrap 3.3.5 -->
    <script src="/assets/bootstrap/js/bootstrap.min.js"></script>
    <!-- AdminLTE App -->
    <script src="/assets/AdminLTE/js/app.js"></script>
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
    <script>
    
    	// 记住上次点击的菜单
      	$(function(){
      		$('.treeview,a').click(function(){
      			var $this = $(this);
      			var id = $this.attr('res-id');
      			if(id){
          			console.log(id);
      				$.cookie('menu-open-id',id,{ expires: 7 ,path:"/"});
      			}
      			
      		});
      		
      		var animationSpeed = $.AdminLTE.options.animationSpeed;
      		var id = $.cookie('menu-open-id');
      		var $this = $('[res-id="'+id+'"]');
            var checkElement = $this.next();

            //Get the parent menu
            var parent = $this.parents('ul').first();
            //Close all open menus within the parent
            var ul = parent.find('ul:visible').slideUp(animationSpeed);
            //Remove the menu-open class from the parent
            ul.removeClass('menu-open');
            //Get the parent li
            var parent_li = $this.parent("li");

            //Open the target menu and add the menu-open class
            checkElement.slideDown(animationSpeed, function () {
              //Add the class active to the parent li
              checkElement.addClass('menu-open');
              parent.find('li.active').removeClass('active');
              parent_li.addClass('active');
              //Fix the layout in case the sidebar stretches over the height of the window
              $.AdminLTE.layout.fix();
            });
      	});
      
      </script>
      
    
    