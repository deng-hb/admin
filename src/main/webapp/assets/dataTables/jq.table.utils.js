/**
 *  必须引用jquery.dataTables.js
 *  {sel:'#xx', 'dom': '<lf<t>ip>',url:'/x/x/x',fields:['id','dd'],params:{data:323},columnDefs:[]}
 */
window.jq.table = function(conf){
	
	var sel = conf.sel?conf.sel:'#data-table';

	// mdl-grid length,info  paging
	var defalutDom = '<"mdl-grid"<"mdl-cell mdl-cell--12-col"rt>><"mdl-grid"<"mdl-cell mdl-cell--4-col"li><"mdl-cell mdl-cell--8-col"p>>';

	var dom = conf.dom?conf.dom:defalutDom;
	var url = conf.url;
	if(!url){
		alert('url is not def');
	}
	var params = conf.params;
	var fields = conf.fields;
	if(!fields){
		alert('fields is not def');
	}
	var columns = [];
	for(var i = 0,len=fields.length;i<len;i++){
		columns.push({'data':fields[i]});
	}
	
	var columnDefs = conf.columnDefs;
	
	return $(sel).DataTable( {
		"dom":dom,
		"select": true,
    	//"processing": true,
        "serverSide": true,
        "scrollX": true,
        "scrollCollapse": true,
        "scrollY": '60vh',
        "sAjaxSource": url,
        "fnServerData": function ( sSource, aoData, fnCallback, oSettings ) {
        	// 参数
        	var param = {};
        	for(var i =0,len = aoData.length;i<len;i++){
        		var ao = aoData[i];
        		if('sEcho' == ao.name){
        			param.draw = ao.value;
        		}
        		if('sSearch' == ao.name){
        			// 搜索字段
        			param.search = ao.value;
        		}
        		if('sSortDir_0' == ao.name){
        			// 排序类型
        			param.sort = ao.value;
        		}
        		if('iSortCol_0' == ao.name){
        			// 排序字段
        			param.column = ao.value;
        		}
        		if('iDisplayStart' == ao.name){
        			// start
        			param.start = ao.value;
        		}
        		if('iDisplayLength' == ao.name){
        			// 显示数量
        			param.length = ao.value;
        		}
        		
        	}
        	// 拼加参数
        	if(params){
        		for(var key in params){
        			param[key] = params[key];
        		}
        	}

        	oSettings.jqXHR = jq.ajax(sSource,param,function(data){
 	        	if(data.code){
	        		fnCallback(data.object);
	        	}
	        });
        },
        "order": [[ 0, "desc" ]],
        "language": {
            "url": "/assets/dataTables/zh-cn.json"
        },
        // https://datatables.net/reference/option/columns.data
    	"columns": columns,
    	"columnDefs":columnDefs
    });
}
