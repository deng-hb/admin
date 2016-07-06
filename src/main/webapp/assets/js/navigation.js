$(function() {
	
	$('.manage-navigation-link').click(function() {
		var $this = $(this);
		if ($this.hasClass('manage-navigation-parent')) {
			var res_id = $this.attr('res-id');
			var sub = $('[res-pid=' + res_id + ']');
			if ($this.hasClass('manage-navigation-unexpand')) {
				$this.removeClass('manage-navigation-unexpand');
				$this.addClass('manage-navigation-expand');
				sub.slideDown();// 展开

				// 记录展开的到cookie
				var expend = $.cookie(nav_expand_cookie);
				if(!expend){
					expend = res_id;
				}else{
					var expends = expend.split(',');
					// 不存在才添加
					if(-1 == expends.indexOf(res_id)){
						expends.push(res_id);
					}
					expend = expends.toString();
				}
				console.log(expend);
				$.cookie(nav_expand_cookie, expend, { path: '/' });
			} else {
				$this.removeClass('manage-navigation-expand');
				$this.addClass('manage-navigation-unexpand');
				sub.slideUp();// 收缩
				// 移除
				var expend = $.cookie(nav_expand_cookie);
				if(!expend){
					return;
				}
				var expends = expend.split(',');
				expends.remove(res_id);
				expend = expends.toString();
				console.log(expend);
				$.cookie(nav_expand_cookie, expend, { path: '/' });
			}

		}
	});
	// 初始化 默认是收缩的
	var nav_expand_cookie = "nav_expand_cookie";
	var init_nav = function(){
		var expend = $.cookie(nav_expand_cookie);
		if(!expend){
			return;
		}
		var expends = expend.split(',');
		if(expends){
			for(var i = 0;i < expends.length;i++){
				var res_id = expends[i];
				if('' != res_id){
					$('[res-id=' + res_id + ']').click();// 展开
				}
			}
		}
	}
	init_nav();
});