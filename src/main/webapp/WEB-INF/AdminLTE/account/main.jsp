<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html>
  	<jsp:include page="/fragment/AdminLTE/head.jsp">
  		<jsp:param value="dataTables" name="css"/>
  		<jsp:param value="${page_name }" name="title"/>
  	</jsp:include>
  
  <body class="hold-transition skin-blue sidebar-mini animsition">
    <div class="wrapper">
		<jsp:include page="/fragment/AdminLTE/top.jsp"></jsp:include>
      
      	<jsp:include page="/fragment/AdminLTE/left.jsp"></jsp:include>
      

      <!-- Content Wrapper. Contains page content -->
      <div class="content-wrapper">
        <!-- Content Header (Page header) -->
        <section class="content-header">
          <h1>
            ${page_name }
            <small>Optional description</small>
          </h1>
          <ol class="breadcrumb">
            <li><a href="/home/"><i class="fa fa-dashboard"></i> home</a></li>
            <li class="active">${page_name }</li>
          </ol>
        </section>

        <!-- Main content -->
        <section class="content">

          <div class="row">
            <div class="col-xs-12">
              <div class="box">
                <div class="box-header">
                  <!-- <h3 class="box-title">Hover Data Table</h3> -->
                    <c:if test="${fn:contains(sessionScope.CURRENT_USER_RESOURCE_URLS,'/account/force-signout')}">
					<button id="show-force-signout" disabled="disabled" class="btn btn-danger btn-flat">
						<i class="fa fa-times-circle"></i> 强制登出
					</button>
					</c:if>

                </div><!-- /.box-header -->
                <div class="box-body">
          <table id="data-table" class="table table-bordered table-striped table-hover" cellspacing="0" width="100%">
				<thead>
		            <tr>
		                <th>ID</th>
		                <th>账号</th>
		                <th>系统用户</th>
		                <th>普通用户</th>
		                <th>状态</th>
		                <th>最后在线时间</th>
		                <th>创建时间</th>
		            </tr>
		        </thead>
			</table>
                </div><!-- /.box-body -->
              </div><!-- /.box -->
			</div>
		</div>
        </section><!-- /.content -->
      </div><!-- /.content-wrapper -->

      <jsp:include page="/fragment/AdminLTE/bottom.jsp"></jsp:include>	
      
    </div><!-- ./wrapper -->

	<jsp:include page="/fragment/AdminLTE/js.jsp">
		<jsp:param value="dataTables,jq.utils,notify" name="js"/>
	</jsp:include>
	
	<script type="text/javascript">
	$(function() {
		var $data_table = jq.table({
			url:"/account/list",
		 fields:["id","username","sysFlag","userFlag","status","lastOnlineTime","createdTime"],
		 columnDefs:[ {
             "targets": 2,
             "createdCell": function (td, cellData, rowData, row, col) {
             	var text = '否';
             	if(cellData)
             		text = '是';
                 $(td).text(text);
             }
           },{
               "targets": 3,
               "createdCell": function (td, cellData, rowData, row, col) {
             	  var text = '否';
               	  if(cellData)
               			text = '是';
                   $(td).text(text);
               }
             },{
                 "targets": 4,
                 "createdCell": function (td, cellData, rowData, row, col) {
                 	var text = '异常';
                 	  if(cellData)
                 			text = '正常';
                     $(td).text(text);
                 }
               }]
			});
	    	
	    var $show_force_signout = $('#show-force-signout');
		$data_table.on( 'select', function ( e, dt, type, indexes ) {
	    	
	    	// button style
	    	var count = $data_table.rows( { selected: true } ).count();
	    	
	    	if(1 <= count){
	    		$show_force_signout.removeAttr('disabled');
	    	}
	    	
        })
        .on( 'deselect', function ( e, dt, type, indexes ) {
	    	// button style
	    	var count = $data_table.rows( { selected: true } ).count();
	    	if(0 == count){
	    		$show_force_signout.attr('disabled','disabled');
	    	}
	    	
        } );

		var data_table_reload = function(){
			$data_table.ajax.reload();
			// 按钮禁用
    		$show_force_signout.attr('disabled','disabled');
		}
		
		$show_force_signout.click(function(){
	    	var selected = $data_table.rows( { selected: true } );
	    	mdl.messager.confirm('提示','确认要强制登出选中的'+selected.count()+'项？',function(){
	    		var array = selected.data().toArray();
	    		var ids = [];
	    		for(var i = 0,len = array.length;i<len;i++){
	    			ids.push(array[i].id);
	    		}
	    		jq.ajax('/account/force-signout',{'ids':ids.toString()},function(data){
	    			if(data.code){
	    				mdl.messager.confirm.close();
	    				data_table_reload();
	    	    	}
					$.notify(data.msg);
	    		});
	    	});
	    });
	} );
	
	
	</script>
  </body>
</html>
