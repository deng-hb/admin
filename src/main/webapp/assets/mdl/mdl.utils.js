/**!
 * 扩展mdl
 */
// TODO
window.el = function(s) {
	return document.querySelector(s);
}

window.width = window.innerWidth || document.documentElement.clientWidth
		|| document.body.clientWidth;
window.height = window.innerHeight || document.documentElement.clientHeight
		|| document.body.clientHeight;

if (window.mdl) {
	throw new Error('window.mdl is define');
}
window.mdl = {
	progress : {
		show : function() {
		},
		hide : function() {
		}
	},
	messager : {
		show : function() {
		},
		hide : function() {
		}
	}
};

function init_base_dialog() {
	var mdl_progress_bar_dialog_html = '<div id="mdl-progress-bar-dialog" class="mdl-dialog" style="padding:10px;display: none;">\
		<div class="mdl-progress mdl-js-progress mdl-progress__indeterminate is-upgraded" data-upgraded=",MaterialProgress"><div class="progressbar bar bar1" style="width: 0%;"></div><div class="bufferbar bar bar2" style="width: 100%;"></div><div class="auxbar bar bar3" style="width: 0%;"></div></div>\
	</div>';
	var mdl_alert_dialog_html = '<div id="mdl-alert-dialog" class="mdl-dialog" style="display: none;">\
			<div id="mdl-alert-dialog-title" class="mdl-dialog__title"></div>\
		    <div id="mdl-alert-dialog-content" class="mdl-dialog__content"></div>\
		    <div class="mdl-dialog__actions">\
		      <button id="mdl-alert-dialog-close" type="button" class="mdl-button">确认</button>\
		    </div>\
		</div>';
	var mdl_confirm_dialog_html = '<div id="mdl-confirm-dialog" class="mdl-dialog" style="display: none;">\
		<div id="mdl-confirm-dialog-title" class="mdl-dialog__title"></div>\
	    <div id="mdl-confirm-dialog-content" class="mdl-dialog__content"></div>\
	    <div class="mdl-dialog__actions">\
	      <button id="mdl-confirm-dialog-close" type="button" class="mdl-button">取消</button>\
	      <button id="mdl-confirm-dialog-action" type="button" class="mdl-button mdl-js-button mdl-button--accent">确认</button>\
	    </div>\
	</div>';

	var container = document.createElement('div');
	container.innerHTML = mdl_progress_bar_dialog_html + mdl_alert_dialog_html + mdl_confirm_dialog_html;
	// container.style.display = 'none';
	el('body').appendChild(container);

	// 初始化dialog
	mdl.progress.dialog = fulldialog(el('#mdl-progress-bar-dialog'));
	// 显示
	mdl.progress.show = function() {
		mdl.progress.dialog.showModal();
	};
	// 隐藏
	mdl.progress.hide = function() {
		mdl.progress.dialog.close();
	};

	// 消息弹框
	mdl.messager._alert = fulldialog(el('#mdl-alert-dialog'));

	// 显示
	mdl.messager.alert = function(title, content) {

		el('#mdl-alert-dialog-title').innerHTML = title;
		el('#mdl-alert-dialog-content').innerHTML = content;

		mdl.messager._alert.showModal();
	}

	el('#mdl-alert-dialog-close').addEventListener('click', function() {
		mdl.messager._alert.close();
	});
	
	// 确认弹框
	mdl.messager._confirm = fulldialog(el('#mdl-confirm-dialog'));
	// 显示
	mdl.messager.confirm = function(title, content, confirm) {

		el('#mdl-confirm-dialog-title').innerHTML = title;
		el('#mdl-confirm-dialog-content').innerHTML = content;

		mdl.messager._confirm.showModal();
		// 执行
		el('#mdl-confirm-dialog-action').addEventListener('click', function() {
			if(confirm){
				confirm();
			}
		});
	}

	mdl.messager.confirm.close = function(){
		mdl.messager._confirm.close();
	};
	
	el('#mdl-confirm-dialog-close').addEventListener('click', function() {
		mdl.messager.confirm.close();
	});
};

window.isElement = function(o) {
	return (typeof HTMLElement === "object" ? o instanceof HTMLElement : // DOM2
	o && typeof o === "object" && o !== null && o.nodeType === 1
			&& typeof o.nodeName === "string");
};

var _fulldialog = function() {
};
if (window.fulldialog) {
	throw new Error('window.fulldialog is define');
}

window['fulldialog'] = function(el) {
	var dialog = new _fulldialog();
	dialog.init(el);
	return dialog;
};
_fulldialog.prototype = {
	_el : null,
	_el_film : null,
	init : function(el) {
		if (!isElement(el)) {
			throw new Error('fulldialog(el) el is HTMLElement');
		}
		this._el = el;
		
		// init 
		var el_film_id = 'film-' + el.id;
		var el_film = window.el('#'+el_film_id);
		if (!el_film) {
			el_film = document.createElement('div');
			el_film.id = el_film_id;
			var filmStyle = el_film.style;
			filmStyle.opacity = '0.3';
			filmStyle.backgroundColor = '#000000';
			filmStyle.display = 'none';
			filmStyle.top = '0';
			filmStyle.left = '0';
			filmStyle.width = '100%';
			filmStyle.height = '100%';
			filmStyle.position = 'absolute';
			window.el('body').appendChild(el_film);

		}
		this._el_film = el_film;

	},
	showModal : function() {

		// 提升zIndex
		var max_zIndex = 1000;
		var dialogs = document.querySelectorAll('.mdl-dialog');
		for (var i = 0,len = dialogs.length; i < len; i++) {
			var dialog = dialogs[i];
			var zIndex = dialog.style.zIndex;
			
			if(this._el.id != dialog.id && 'block' == dialog.style.display && max_zIndex < zIndex){
				max_zIndex = zIndex;
			}
			
		}
		
		// +2 
		max_zIndex += 2;
		
		this._el.style.display = 'block';
		this._el.style.zIndex = max_zIndex;
		
		var filmStyle = this._el_film.style;
		
		//top递增
		var top = 0,z=3;
		var elStyle = this._el.style;
		var topInterval = setInterval(function(){
			elStyle.top = (top+=4+z) +'%';
			--z;
			if(20 <= top){
				clearInterval(topInterval);

			}
		}, 50);
		
		// TODO default 280
		var width = elStyle.width;
		if (!width) {
			width = 280;
		} else {
			// px
			width = width.substr(0, width.length - 2);
		}
		elStyle.left = (window.width - width) / 2 + 'px';
		elStyle.position = 'absolute';
		elStyle.background = '#ffffff';

		// 透明层
		filmStyle.zIndex = --max_zIndex;
		filmStyle.display = 'block';
		
	},
	close : function() {
		this._el.style.display = 'none';
		this._el_film.style.display = 'none';
	}
};

var _editor = function(){
	
}
/**
 * 编辑弹框
 * 
 */
window.mdl.editor = function(options){
	var edit = new _editor();
	edit.init(options);
	return edit;
}

_editor.prototype = {
		id:null,
		dialog:null,
		/**
		 * {alias:'data',width:'400px',fields:[{label:'名称..',name:'name',type:'text',value:'123',values:[{label:'男',value:'1'}]},{custom:'<html/>'}],compile:function}
		 */
		init:function(options){
			var alias = options.alias?options.alias:'data';
			
			var width = options.width?options.width:'280px';
			
			var action = options.action?option.action:'';
			
			var fields = options.fields?options.fields:[];
			
			var el_id = 'editor-'+alias;
			this.id = el_id;
			
			var getFieldId = function(name){
				return el_id+'-form-'+name;
			}
			// 字段拼接
			var editor_form_fields_html = '';
			for(var i = 0;i< fields.length;i++){
				var field = fields[i];
				if(field.custom){
					editor_form_fields_html += field.custom;
				}else{
					if(field.type == 'hidden'){
						editor_form_fields_html += 
							'<input name="'+field.name+'" type="hidden" id="'+getFieldId(field.name)+'" />';
					}else if(field.type == 'text'){
						editor_form_fields_html += 
						  '<div class="mdl-textfield mdl-js-textfield mdl-textfield--floating-label">\
						    <input class="mdl-textfield__input" name="'+field.name+'" type="text" id="'+getFieldId(field.name)+'"/>\
						    <label class="mdl-textfield__label" for="'+getFieldId(field.name)+'">'+field.label+'..</label>\
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
			
			var editor_dialog_html = '<div id="'+el_id+'-dialog" class="mdl-dialog" style="width:'+width+';display: none;">';

			var editor_title_html = '<div id="'+el_id+'-title" class="mdl-dialog__title"></div>';

			var editor_form_html = '<div class="mdl-dialog__content"><form id="'+el_id+'-form" action="'+action+'" method="post">';
						
			var editor_dialog_button_html = 
			'<div class="mdl-dialog__actions ">\
		      <button id="'+el_id+'-hide" type="button" class="mdl-button">取消</button>\
		      <button id="'+el_id+'-submit" type="button" class="mdl-button mdl-js-button mdl-button--accent">确认</button>\
		    </div>';
			
			var html = editor_dialog_html + editor_title_html + editor_form_html + editor_form_fields_html +'</form></div>' +editor_dialog_button_html+'</div>';

			var container = document.createElement('div');
			container.innerHTML = html;
			el('body').appendChild(container);
			
			// 初始化dialog
			var dialog = fulldialog(el('#'+el_id+'-dialog'));
			this.dialog = dialog;
			el('#'+el_id+'-hide').onclick = function(){
				dialog.close();
			};
			
			if(!options.ajaxForm){
				new Error('ajax form is not def');
			}
			options.ajaxForm(el_id+'-form');

			el('#'+el_id+'-submit').onclick = function(){
				if(options.ajaxSubmit){
					options.ajaxSubmit();
				}
			};
 			
		},
		/**
		 * {action:'/x/x/x',value:{},title:'标题'}
		 * @param conf
		 */
		showModal:function(conf){
			if(conf.title){
				el('#'+this.id+'-title').innerHTML = conf.title;
			}
			if(conf.action){
				el('#'+this.id+'-form').action = conf.action;
			}
			if(conf.value){
				for(var key in conf.value){
					var _el = el('#'+this.id+'-form-'+key);
					if(_el)
						_el.value = conf.value[key];
				}
			}
			this.dialog.showModal();
		},
		close:function(){
			this.dialog.close();
		}
}


window.onload = function() {
	init_base_dialog();
};

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
