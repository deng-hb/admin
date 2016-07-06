<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html>
  	<jsp:include page="/fragment/AdminLTE/head.jsp">
  		<jsp:param value="dataTables,zTree" name="css"/>
  		<jsp:param value="${page_name }" name="title"/>
  	</jsp:include>
  
  <body class="hold-transition skin-blue sidebar-mini animsition ">
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
				<c:if test="${fn:contains(sessionScope.CURRENT_USER_RESOURCE_URLS,'/sys/resource/create')}">
				<button id="show-create" class="btn btn-default btn-flat">
				<i class="fa fa-plus"></i>创建
				</button>
				</c:if>
				
				<c:if test="${fn:contains(sessionScope.CURRENT_USER_RESOURCE_URLS,'/sys/resource/update')}">
			    <button id="show-update" type="button" class="btn btn-default btn-flat" disabled="disabled">
			    <i class="fa fa-edit"></i>修改
			    </button>
				</c:if>
				
				<c:if test="${fn:contains(sessionScope.CURRENT_USER_RESOURCE_URLS,'/sys/resource/delete')}">
			    <button id="show-delete" type="button" class="btn btn-default btn-flat" disabled="disabled">
			    <i class="fa fa-minus"></i>删除
			    </button>
				</c:if>
                </div><!-- /.box-header -->
                <div class="box-body">
          		<table id="data-table" class="table table-bordered table-striped table-hover" cellspacing="0" width="100%">
				<thead>
			            <tr>
			                <th>ID</th>
			                <th>名称</th>
			                <th>链接</th>
			                <th>类型</th>
			                <th>上级ID</th>
			                <th>排序</th>
			                <th>更新时间</th>
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
			url:"/sys/resource/list",
		 fields:["id","name","url","type","parentId","sort","updatedTime"],
         "columnDefs": [ {
             "targets": 3,
             "createdCell": function (td, cellData, rowData, row, col) {
                 cellData = (0 == cellData?'链接':'菜单');
                 $(td).text(cellData);
             }
         }]
			});
	    

	    var $show_create = $('#show-create');
	    var $show_update = $('#show-update');
	    var $show_delete = $('#show-delete');
	    
	    
	    $data_table.on( 'select', function ( e, dt, type, indexes ) {
	    	
	    	// button style
	    	var count = $data_table.rows( { selected: true } ).count();
	    	if(1 == count){
	    		$show_create.removeAttr('disabled');
	    		$show_update.removeAttr('disabled');
	    	}else{
	    		$show_create.attr('disabled','disabled');
	    		$show_update.attr('disabled','disabled');
	    	}
	    	if(1 <= count){
	    		$show_delete.removeAttr('disabled');
	    	}
	    	
        } )
        .on( 'deselect', function ( e, dt, type, indexes ) {
	    	// button style
	    	var count = $data_table.rows( { selected: true } ).count();
	    	if(0 == count){
	    		$show_update.attr('disabled','disabled');
	    		$show_delete.attr('disabled','disabled');
	    	}
	    	
        } );
	    
	    var data_table_reload = function(){
	    	$data_table.ajax.reload();
			// 按钮禁用
    		$show_update.attr('disabled','disabled');
    		$show_delete.attr('disabled','disabled');
	    }
	    
	    $show_delete.click(function(){
	    	var selected = $data_table.rows( { selected: true } );
	    	mdl.messager.confirm('提示','确认要删除选中的'+selected.count()+'项？',function(){
	    		var array = selected.data().toArray();
	    		var ids = [];
	    		for(var i = 0,len = array.length;i<len;i++){
	    			ids.push(array[i].id);
	    		}
	    		jq.ajax('/sys/resource/delete',{'ids':ids.toString()},function(data){
	    			if(data.code){
	    				mdl.messager.confirm.close();
	    				data_table_reload();
	    	    	}
	    			$.notify(data.msg);			    		    	
	    		});
	    	});
	    });

	    var $data_form = $('#data-dialog-form');
	    jq.form($data_form, function(){return true}, function(data){
	    	if(data.code){
	    		data_dialog.close();
				data_table_reload();
	    	}
			$.notify(data.msg);			    		    	
	    });
	    var $data_form;
		var edit = mdl.editor({
			alias:'data',
			fields:[
			    {name:'id',type:'hidden'},
			    {name:'name',type:'text',label:'名称'},
			    {name:'url',type:'text',label:'链接'},
			    {name:'type',type:'text',label:'类型'},
			    {name:'parentId',type:'text',label:'上级ID'},
			    {name:'sort',type:'text',label:'排序'}
			    ]
			,ajaxForm:function(id){
				$data_form = $('#'+id);
			    jq.form($data_form, function(){return true}, function(data){
			    	if(data.code){
			    		edit.close();
						data_table_reload();
			    	}
	    			$.notify(data.msg);			    	
			    });
			},ajaxSubmit:function(){
				$data_form.submit();
			}
		});

		$show_create.click(function(){
			edit.showModal({action:'/sys/resource/create',title:'创建',value:false});
		});
		
		$show_update.click(function(){
	    	var selected = $data_table.rows( { selected: true } ).data().toArray()[0];
			edit.showModal({action:'/sys/resource/update',title:'修改',value:selected});
		});
		
	} );
	
	</script>
  </body>
</html>
