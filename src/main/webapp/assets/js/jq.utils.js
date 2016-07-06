/**
 * <pre>
 * jQuery 简单封装，更方便使用
 * author : denghb.com 
 * date   : 2016-03-16 PM
 * version: 1.0
 * </pre>
 */
window.jq = {};
if(!window.mdl){
	window.mdl = window.D;
}
/**
 * @param selecter jQuery form 对象
 * @param pre 提交前执行的函数
 * @param success 成功后回调函数
 */
jq.form = function(selecter,pre,succes){
	selecter.ajaxForm({ 
		dataType:'json',
        beforeSubmit:function(formData, jqForm, options){
        	if(pre()){
        		mdl.progress.show();
        	}else{
            	return false;
        	}
        },
        success:function(data){
        	mdl.progress.hide();
			if(!jq.error(data)){
				succes(data);
			}
		},
		error:function(xhr, textStatus, errorThrown){
			mdl.progress.hide();
			mdl.messager.alert('Sorry','服务器遇到错误，请稍候重试！<br/><br/> Error code：'+xhr.status);
		}
    });
};
/**
 * @param url 请求路径
 * @param params 参数
 * @param success 成功后回调函数
 */
jq.ajax = function(url,params,succes){
	// 参数拼接随机数 防止缓存
	if(!params){
		params = {};
	}
	if(params._r){
		alert('r is internal paramter');
		return;
	}
	params._r = new Date().getTime();
	mdl.progress.show();
	
	// ajax 执行
	$.ajax({
		type: "POST",
		url: url,
		data: params,
		dataType : 'json',
		success: function(data){
			mdl.progress.hide();
			if(!jq.error(data)){
				succes(data);
			}
			
		},
		error: function(xhr, textStatus, errorThrown) {
			mdl.progress.hide();
			mdl.messager.alert('Sorry','服务器遇到错误，请稍候重试！<br/><br/> Error code：'+xhr.status);
		}
	});
};

// 错了返回true
jq.error = function(data){

	// 2 超时、3 没有权限
	if(2 == data.code || 3 == data.code){
		window.location.href = data.object;
		return true;
	}
	return false;
}
