package com.denghb.admin.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import com.denghb.admin.utils.SpringMvcUtils;

@Controller
@RequestMapping("/config")
public class ConfigController {

	/**
	 * 动态改变输出jsp内容
	 * 
	 * @param theme
	 * @return
	 */
	@RequestMapping("/to-{theme}")
	public String toIndex(@PathVariable("theme") String theme) {

		InternalResourceViewResolver irvr = SpringMvcUtils.getBean(InternalResourceViewResolver.class);
		irvr.clearCache();// 清除之前的缓存
		switch (theme) {
		case "lte":
			irvr.setPrefix("/WEB-INF/AdminLTE/");
			break;
		case "mdl":
			irvr.setPrefix("/WEB-INF/jsp/");
			break;
		}
		return "redirect:/";
	}
}
