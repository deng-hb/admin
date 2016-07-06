<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html>
  	<jsp:include page="/fragment/AdminLTE/head.jsp">
  		<jsp:param value="dataTables,zTree,drawer" name="css"/>
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
                  <!-- <h3 class="box-title">Hover Data Table</h3> -->
                  <c:if test="${fn:contains(sessionScope.CURRENT_USER_RESOURCE_URLS,'/sys/role-resource/update')}">
			    <button id="show-settings" type="button" class="btn btn-default btn-flat" disabled="disabled">
			    <i class="fa fa-cog"></i>设置资源
			    </button>
				</c:if>
				<c:if test="${fn:contains(sessionScope.CURRENT_USER_RESOURCE_URLS,'/sys/role/create')}">
				<button id="show-create" class="btn btn-default btn-flat">
				<i class="fa fa-plus"></i>创建
				</button>
				</c:if>
				
				<c:if test="${fn:contains(sessionScope.CURRENT_USER_RESOURCE_URLS,'/sys/role/update')}">
			    <button id="show-update" type="button" class="btn btn-default btn-flat" disabled="disabled">
			    <i class="fa fa-edit"></i>修改
			    </button>
				</c:if>
				
				<c:if test="${fn:contains(sessionScope.CURRENT_USER_RESOURCE_URLS,'/sys/role/delete')}">
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
		                <th>描述</th>
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
	<aside class="control-sidebar control-sidebar-dark">
	        <div class="tab-content">
	
        <div>TO：<span id="ztree-cell-title" style="color:#ff4081;"></span></div>
	  	<div>
	  		<button id="ztree-cell-reset" class="btn btn-default btn-flat btn-sm">
			 <i class="fa fa-refresh"></i>重置
			</button>
	  		<button id="ztree-cell-update" class="btn btn-primary btn-flat btn-sm">
			  <i class="fa fa-gears"></i>更新
			</button>
	  	</div>
	  	<br/>
	  	<div >
	        <ul id="ztree" class="ztree"></ul>
	    </div>
	    </div>
    </aside>
    <div class="control-sidebar-bg"></div>
    
	<jsp:include page="/fragment/AdminLTE/js.jsp">
		<jsp:param value="dataTables,jq.utils,zTree,notify" name="js"/>
	</jsp:include>
	
	<script type="text/javascript">
	$(function() {
		var $data_table = jq.table({
			url:"/sys/role/list",
		 fields:["id","name","description","updatedTime"]
			});
		

	    var $show_create = $('#show-create');
	    var $show_update = $('#show-update');
	    var $show_delete = $('#show-delete');
	    var $show_settings = $('#show-settings');
		$data_table.on( 'select', function ( e, dt, type, indexes ) {
	    	
	    	// button style
	    	var count = $data_table.rows( { selected: true } ).count();
	    	if(1 == count){
	    		$show_create.removeAttr('disabled');
	    		$show_update.removeAttr('disabled');
	    		$show_settings.removeAttr('disabled');
	    	}else{
	    		$show_create.attr('disabled','disabled');
	    		$show_update.attr('disabled','disabled');
	    		$show_settings.attr('disabled','disabled');
	    	}
	    	if(1 <= count){
	    		$show_delete.removeAttr('disabled');
	    	}
	    	
        })
        .on( 'deselect', function ( e, dt, type, indexes ) {
	    	// button style
	    	var count = $data_table.rows( { selected: true } ).count();
	    	if(0 == count){
	    		$show_update.attr('disabled','disabled');
	    		$show_delete.attr('disabled','disabled');
	    		$show_settings.attr('disabled','disabled');
	    	}
	    	
        } );

		// 初始化zTree数据	    
	    jq.ajax("/sys/resource/list-all", {}, function(data){
			data = data.object;
			// console.log(data);
			// 初始化数据
			var zNodes = [];
			for(var i = 0,len = data.length;i<len;i++){
				var obj = data[i];
				var z = {};
				z.id = obj.id;
				z.pId = obj.parentId;
				//if(!obj.parentId){
					z.open = true;
				//}
				z.name = obj.name;
				zNodes.push(z);
			}
	    	var setting = {
	    			check: {
	    				enable: true
	    			},
	    			data: {
	    				simpleData: {
	    					enable: true
	    				}
	    			}
	            };
	    	
            $.fn.zTree.init($("#ztree"), setting, zNodes);
    	});
		
	
		var $refresh_resource = function(){
			var role = $data_table.rows( { selected: true } ).data().toArray()[0];
			// TODO 查询已有资源
   			jq.ajax("/sys/role-resource/query", {'roleId':role.id}, function(data){
 		  		var data = data.object;
   				var treeObj = $.fn.zTree.getZTreeObj("ztree");
 		  		var nodes = treeObj.getNodes();

 		  		// 获取所有节点 
 		  		var allNodes = [];
 		  		// 获取子节点函数
 		  		var getChildren = function(node){
 		  			if(node.children){
 		  				var cNodes = node.children;
 		  				for(var j = 0,len = cNodes.length;j<len;j++){
 		  					node = cNodes[j];
	 		  				allNodes.push(node);
	 		  				getChildren(node);// 递归
 		  				}
 		  			}
 		  		}
 		  		for (var i=0, l=nodes.length; i < l; i++) {
 		  			var node = nodes[i];
 		  			allNodes.push(node);
 		  			getChildren(node);
 		  		}
				// 循环所有节点
 		  		for (var i=0, l=allNodes.length; i < l; i++) {
 		  			var node = allNodes[i];
		  				// 清空选择
 		  			treeObj.checkNode(node, false, true);
 		  			if(data){
						// 选中已分配的
	 		  			for(var j = 0,len = data.length;j<len;j++){
		 		  			if(node.id === data[j]){
			 		  			treeObj.checkNode(node, true, true);
		 		  			}
	 		  			}
						
 		  			}
 		  		}
 		  		
 		  		
	  	    });
		}
		// 重置
		$('#ztree-cell-reset').click(function(){
			$refresh_resource();
		});
		
		$show_settings.click(function(){
			// 样式
			showAside();
			
			// 只会有一个选中
	    	var selected = $data_table.rows( { selected: true } );
	    	if(1 == selected.count()){
		    	var array = selected.data().toArray();
		    	var res = array[0];
	  		  	$('#ztree-cell-title').text(res.name);
	    	}
		
	    	$refresh_resource();

		});

		// 更新
		$('#ztree-cell-update').click(function(){
			// 获取选中的tree
	  		var treeObj = $.fn.zTree.getZTreeObj("ztree");
	  		var nodes = treeObj.getCheckedNodes(true);
	  		var resourceIds = '';
	  		for (var i=0, l=nodes.length; i < l; i++) {
				resourceIds += nodes[i].id+',';// ','区分
			}
			var role = $data_table.rows( { selected: true } ).data().toArray()[0];
			jq.ajax("/sys/role-resource/update", {'roleId':role.id,'resourceIds':resourceIds}, function(data){
				mdl.messager.alert('提示',data.msg);
				hideAside();
			});
			
		});
		
		var data_table_reload = function(){
			$data_table.ajax.reload();
			// 按钮禁用
    		$show_update.attr('disabled','disabled');
    		$show_delete.attr('disabled','disabled');
    		$show_settings.attr('disabled','disabled');
		}
		
		var $data_form;
		var edit = mdl.editor({
			alias:'data',
			fields:[
			    {name:'id',type:'hidden'},
			    {name:'name',type:'text',label:'名称'},
			    {name:'description',type:'text',label:'描述'}
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
			edit.showModal({action:'/sys/role/create',title:'创建',value:false});
		});
		
		$show_update.click(function(){
	    	var selected = $data_table.rows( { selected: true } ).data().toArray()[0];
			edit.showModal({action:'/sys/role/update',title:'修改',value:selected});
		});
		
		$show_delete.click(function(){
	    	var selected = $data_table.rows( { selected: true } );
	    	mdl.messager.confirm('提示','确认要删除选中的'+selected.count()+'项？',function(){
	    		var array = selected.data().toArray();
	    		var ids = [];
	    		for(var i = 0,len = array.length;i<len;i++){
	    			ids.push(array[i].id);
	    		}
	    		jq.ajax('/sys/role/delete',{'ids':ids.toString()},function(data){
	    			if(data.code){
	    				mdl.messager.confirm.close();
	    				data_table_reload();
	    	    	}
	    			$.notify(data.msg);
	    		});
	    	});
	    });
		
	});// end $
	
	
	</script>
  </body>
</html>
