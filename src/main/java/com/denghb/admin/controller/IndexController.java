package com.denghb.admin.controller;

import java.util.Calendar;
import java.util.Date;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.denghb.admin.base.AdminException;
import com.denghb.admin.base.Consts.AA;
import com.denghb.admin.base.Consts.Session;
import com.denghb.admin.base.CurrentUser;
import com.denghb.admin.base.JsonResponse;
import com.denghb.admin.domain.AccountAccess;
import com.denghb.admin.service.AccountAccessService;
import com.denghb.admin.service.AccountService;
import com.denghb.admin.utils.WebUtils;
import com.geetest.sdk.java.GeetestLib;

@Controller
public class IndexController {

	private static final Logger log = LoggerFactory.getLogger(IndexController.class);

	@Autowired
	private AccountService accountService;

	@Autowired
	private AccountAccessService accountAccessService;

	@RequestMapping("/")
	public String index(HttpServletRequest request, HttpServletResponse response) {
		CurrentUser currentUser = WebUtils.getCurrentUser(request);
		if (null != currentUser) {
			// 登陆过就去主页
			return "redirect:/home/";
		}

		return "index";
	}

	@RequestMapping(value = "/signin")
	public String signin(HttpServletRequest request, HttpServletResponse response, Model model) {
		String challenge = request.getParameter(GeetestLib.fn_geetest_challenge);
		String validate = request.getParameter(GeetestLib.fn_geetest_validate);
		String seccode = request.getParameter(GeetestLib.fn_geetest_seccode);

		String username = request.getParameter("username");
		model.addAttribute("username", username);
		String password = request.getParameter("password");
		String remember = request.getParameter("remember");

		GeetestLib gtSdk = new GeetestLib();

		int gtResult = gtSdk.enhencedValidateRequest(challenge, validate, seccode);
		if (1 != gtResult) {
			log.error("sign in error:验证失败");
			model.addAttribute("msg", "滑动验证失败");
			return "redirect:/";
		}

		CurrentUser currentUser = null;
		try {
			currentUser = accountService.signIn(username, password);
		} catch (AdminException e) {
			log.warn(e.getMessage(), e);
			model.addAttribute("msg", e.getMessage());
			return "redirect:/";
		}
		model.asMap().remove("username");
		// 用户信息保存至新的session里面
		HttpSession session = request.getSession();
		session.setAttribute(Session.CURRENT_USER, currentUser);
		String userAgent = request.getHeader("User-Agent");
		String ipAddr = WebUtils.getIpAddr(request);
		// 日期
		Calendar calendar = Calendar.getInstance();

		int expiry = -1;// 10分钟
		if (null != remember && "1".endsWith(remember)) {
			// 5d 单位为秒
			expiry = 5 * 24 * 60 * 60;
		}
		calendar.add(Calendar.SECOND, -1 == expiry ? 10 * 60 : expiry);// 分钟
		String token = accountAccessService.create(currentUser.getAccountId(), userAgent, ipAddr, calendar.getTime());

		//
		Cookie cookie = new Cookie(Session.TOKEN, token);
		cookie.setMaxAge(expiry);
		cookie.setHttpOnly(true);
		response.addCookie(cookie);

		return "redirect:/home/";
	}

	@RequestMapping("/signout")
	public String signout(HttpServletRequest request, HttpServletResponse response) {
		CurrentUser currentUser = WebUtils.getCurrentUser(request);
		if (null != currentUser) {
			accountAccessService.destroy(currentUser, new long[] { currentUser.getAccountId() });
		}
		request.getSession().invalidate();
		return "redirect:/";
	}

	@RequestMapping("/help")
	public String help() {

		return "help";
	}

	@RequestMapping("/validate-remember")
	@ResponseBody
	public JsonResponse validateRemeber(HttpServletRequest request, HttpServletResponse response) {
		String token = WebUtils.getCookieValue(request, Session.TOKEN);
		if (StringUtils.isNotBlank(token)) {
			AccountAccess aa = accountAccessService.query(token);
			String ipAddr = WebUtils.getIpAddr(request);
			String userAgent = request.getHeader("User-Agent");

			if (null == aa || new Date().after(aa.getExpiryTime())) {
				// 会话过期

			} else if (!ipAddr.equals(aa.getIpAddr()) || !userAgent.equals(aa.getUserAgent())) {
				// 登录信息发生变化

			} else if (AA.Status.STATUS_1 == aa.getStatus()) {
				// 强制踢出

			} else {
				return JsonResponse.build2("/home/");
			}
		}
		return JsonResponse.buildFailure();

	}

	@RequestMapping("/error/{code}")
	public String error(@PathVariable("code") String code, Model model) {
		String message = "";
		switch (code) {
		case "403":
			message = "Sorry ！！ 您没有相应的权限，请联系管理员！";
			break;
		case "404":
			message = "Sorry ！！ 您访问的页面找不到了，请联系管理员！";
			break;
		case "timeout":
			message = "Sorry ！！ 登录失效或未登录！";
			break;
		case "forceout":
			message = "Sorry ！！ 请重新登录！";
			break;
		case "security":
			message = "Sorry ！！ 您的登录信息发生变化，请重新登录！";
			break;
		default:
			message = "服务器去玩了，找管理员帮忙找回来";
			break;
		}
		model.addAttribute("message", message);
		return "error/index";
	}

}
