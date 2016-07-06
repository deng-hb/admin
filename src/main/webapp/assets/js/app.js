

window.D = {
		progress : {
			show : function() {
			},
			hide : function() {
			}
		},
		messager : {
			alert : function() {
			},
			confirm : function() {
			}
		}
	};
var _editor = function(){
	
}
window.D.editor = function(options){
	var edit = new _editor();
	edit.init(options);
	return edit;
}

_editor.prototype = {
		id:null,
		dialog:null,
		fields:null,
		/**
		 * {alias:'data',width:'400px',fields:[{label:'名称..',name:'name',type:'text',value:'123',values:[{label:'男',value:'1'}]},{custom:'<html/>'}],compile:function}
		 */
		init:function(options){
			var alias = options.alias?options.alias:'data';
						
			var action = options.action?option.action:'';
			
			var fields = options.fields?options.fields:[];
			this.fields = fields;
			
			var $_id = 'editor-'+alias;
			this.id = $_id;
			
			var getFieldId = function(name){
				return $_id+'-form-'+name;
			}
			// 字段拼接
			var editor_form_fields_html = '';
			for(var i = 0;i< fields.length;i++){
				var field = fields[i];
				if(field.custom){
					editor_form_fields_html += field.custom;
				}else{
					
					var placeholder = field.label;
					if(field.placeholder){
						placeholder = field.placeholder;
					}
					
					if(field.type == 'hidden'){
						editor_form_fields_html += 
							'<input name="'+field.name+'" type="hidden" id="'+getFieldId(field.name)+'" />';
					}else if(field.type == 'text'){
						editor_form_fields_html += '<div class="form-group">\
								                      <label for="'+getFieldId(field.name)+'" class="col-sm-2 control-label">'+field.label+'</label>\
								                      <div class="col-sm-10">\
								                        <input type="email" class="form-control"  name="'+field.name+'"  id="'+getFieldId(field.name)+'" placeholder="'+placeholder+'">\
								                      </div>\
								                    </div>';
					}else if(field.type == 'radio' || field.type == 'checkbox'){
						var values = field.values; 
						if(values){
							new Error('values is null');
						}
						var len = values.length;
						if(0 == len){
							new Error('values is null');
						}
						
						// 选项拼接
						var editor_form_fields_sub_html = '';
						for(var j = 0;j<len;j++){
							var field_sub = values[j];
							editor_form_fields_sub_html +=
								 '<label class="mdl-'+field.type+' mdl-js-'+field.type+' mdl-js-ripple-effect" for="'+getFieldId(field.name)+'-'+field_sub.value+'">\
								  <input type="'+field.type+'" id="'+getFieldId(field.name)+'-'+field_sub.value+'" class="mdl-'+field.type+'__button" name="'+field.name+'" value="0" '+j==0?'checked':''+'>\
								  <span class="mdl-'+field.type+'__label">'+field_sub.label+'</span>\
								</label>';
						}
						
						editor_form_fields_html += 
						'<div class="mdl-textfield">\
							'+field.label+'\
						    '+editor_form_fields_sub_html+'\
						</div>';
					}
					
					
				}
			}
			
			var editor_dialog_html = '<div id="'+$_id+'-dialog" class="modal" style="display: none;" tabindex="-1" role="dialog">';
			var editor_title_html = '<div class="modal-dialog ">\
										<div class="modal-content"> \
											<div class="modal-header">\
												<button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">×</span></button>\
												<h4 id="'+$_id+'-title" class="modal-title"></h4> \
											</div>';

			var editor_form_html = '<div class="modal-body"><form id="'+$_id+'-form" class="form-horizontal" action="'+action+'" method="post">';
						
			var editor_dialog_button_html = 
			'<div class="modal-footer ">\
		      <button id="'+$_id+'-hide" type="button" class="btn btn-default btn-flat">取消</button>\
		      <button id="'+$_id+'-submit" type="button" class="btn btn-primary btn-flat">确认</button>\
		    </div></div></div>';
			
			var html = editor_dialog_html + editor_title_html + editor_form_html + editor_form_fields_html +'</form></div>' +editor_dialog_button_html+'</div>';

			$('body').append(html);
			
			// 初始化dialog
			var dialog = $('#'+$_id+'-dialog');
			this.dialog = dialog;
			$('#'+$_id+'-hide').unbind('click').bind('click',function(){
				dialog.modal('hide');
			});
			
			if(!options.ajaxForm){
				new Error('ajax form is not def');
			}
			options.ajaxForm($_id+'-form');

			$('#'+$_id+'-submit').unbind('click').bind('click',function(){
				if(options.ajaxSubmit){
					options.ajaxSubmit();
				}
			});
 			
		},
		/**
		 * {action:'/x/x/x',value:{},title:'标题'}
		 * @param conf
		 */
		showModal:function(conf){
			if(conf.title){
				$('#'+this.id+'-title').html(conf.title);
			}
			if(conf.action){
				$('#'+this.id+'-form').attr('action',conf.action);
			}

			// 清空。。
			var fields = this.fields;
			for(var i = 0,len = fields.length;i < len;i++){
				$('#'+this.id+'-form-'+fields[i].name).val('');
			}
			// 赋值
			if(conf.value){
				for(var key in conf.value){
					var _$ = $('#'+this.id+'-form-'+key);
					if(_$)
						_$.val(conf.value[key]);
				}
			}
			this.dialog.modal('show');
		},
		close:function(){
			this.dialog.modal('hide');
		}
}

function init_D() {
	var D_progress_bar_dialog_html = '<div id="D-progress-bar-dialog" class="modal " tabindex="-1" role="dialog">\
								<div class="modal-dialog modal-sm">\
									<div class="modal-content">\
										<div class="progress">\
										  <div class="progress-bar progress-bar-striped active" role="progressbar" style="width: 100%"></div>\
										</div>\
									</div>\
								</div>\
							</div>';
	
	var D_alert_dialog_html = '<div id="D-alert-dialog" class="modal " tabindex="-1" role="dialog">\
								<div class="modal-dialog modal-sm">\
								<div class="modal-content"> \
									<div class="modal-header">\
										<button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">×</span></button>\
										<h4 id="D-alert-dialog-title" class="modal-title"></h4> \
									</div> \
									<div id="D-alert-dialog-content" class="modal-body">\
									</div> \
									<div class="modal-footer"> \
										<button id="D-alert-dialog-close" type="button" type="button" class="btn btn-primary btn-flat">确定</button> \
									</div>\
								</div> \
							</div>\
						</div>';
	var D_confirm_dialog_html = '<div id="D-confirm-dialog" class="modal " tabindex="-1" role="dialog">\
								<div class="modal-dialog modal-sm">\
									<div class="modal-content"> \
										<div class="modal-header">\
											<button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">×</span></button>\
											<h4 id="D-confirm-dialog-title" class="modal-title"></h4> \
										</div> \
										<div id="D-confirm-dialog-content" class="modal-body">\
										</div> \
										<div class="modal-footer"> \
											<button id="D-confirm-dialog-close" type="button" class="btn btn-default btn-flat" data-dismiss="modal">取消</button> \
											<button id="D-confirm-dialog-action" type="button" class="btn btn-primary btn-flat">确定</button> \
										</div>\
									</div> \
								</div>\
							</div>';

	var container = document.createElement('div');//  D_progress_bar_dialog_html +
	container.innerHTML = D_alert_dialog_html + D_confirm_dialog_html;
//	 container.style.display = 'none';
	$('body').append(container);
	$(D_progress_bar_dialog_html).appendTo("body");
	// 显示
	D.progress.show = function() {
		$('#D-progress-bar-dialog').modal('show');

//		$('body').append(D_progress_bar_dialog_html);
//		$('#D-progress-bar-dialog').modal('show');
	};
	// 隐藏
	D.progress.hide = function() {
		$('#D-progress-bar-dialog').modal('hide');
//		$('body').remove(D_progress_bar_dialog_html);
	};

	// 显示
	D.messager.alert = function(title, content) {

		$('#D-alert-dialog-title').html(title);
		$('#D-alert-dialog-content').html(content);
		$('#D-alert-dialog').modal('show');
	}

	$('#D-alert-dialog-close').on('click', function() {
		$('#D-alert-dialog').modal('hide');
	});
	
	// 显示
	D.messager.confirm = function(title, content, confirm) {

		$('#D-confirm-dialog-title').html(title);
		$('#D-confirm-dialog-content').html(content);
		$('#D-confirm-dialog').modal('show');
		// 执行
		$('#D-confirm-dialog-action').unbind('click').bind('click', function() {
			if(confirm){
				confirm();
			}
		});

		$('#D-confirm-dialog-close').unbind('click').bind('click', function() {
			$('#D-confirm-dialog').modal('hide');
		});
	}
	D.messager.confirm.close = function(){
		$('#D-confirm-dialog').modal('hide');
	};
};

var showAside = function(){

	$('.drawer-overlay').on('click',function(){
		hideAside();
	});
	

	$('.control-sidebar').addClass('control-sidebar-open');
	$('.drawer-overlay').show();
}

var hideAside = function(){
	$('.drawer-overlay').hide();
	$('.control-sidebar').removeClass('control-sidebar-open');
}

$(document).ready(function() {
	// init animsition
	$('a').addClass('animsition-link');
	$(".animsition").animsition({
	    inClass: 'fade-in',
	    outClass: 'fade-out',
	    inDuration: 1500,
	    outDuration: 800,
	    linkElement: '.animsition-link',
	    linkElement: 'a:not([target="_blank"]):not([href^="#"])',
	    loading: true,
	    loadingParentElement: 'body', //animsition wrapper element
	    loadingClass: 'animsition-loading',
	    loadingInner: '', // e.g '<img src="loading.svg" />'
	    timeout: false,
	    timeoutCountdown: 5000,
	    onLoadEvent: true,
	    browser: [ 'animation-duration', '-webkit-animation-duration'],
	    // "browser" option allows you to disable the "animsition" in case the css property in the array is not supported by your browser.
	    // The default setting is to disable the "animsition" in a browser that does not support "animation-duration".
	    overlay : false,
	    overlayClass : 'animsition-overlay-slide',
	    overlayParentElement : 'body',
	    transition: function(url){ window.location.href = url; }
	  });

	$.notifyDefaults({
		placement: {
			from: "top",
			align:"center"
		},
		delay: 1500,
		timer: 1000,
		animate:{
			enter: 'animated fadeInDown',
			exit: 'animated fadeOutUp'
		}
	});

	
	init_D();
});

//数组优化
Array.prototype.indexOf = function(val) {
	for (var i = 0; i < this.length; i++) {
		if (this[i] == val)
			return i;
	}
	return -1;
};
Array.prototype.remove = function(val) {
	var index = this.indexOf(val);
	if (index > -1) {
		this.splice(index, 1);
	}
};