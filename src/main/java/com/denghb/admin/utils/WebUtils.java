package com.denghb.admin.utils;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

import com.denghb.admin.base.Consts.Session;
import com.denghb.admin.base.CurrentUser;

public class WebUtils {
	/**
	 * 获取client IP地址
	 * 
	 * @param request
	 * @return
	 */
	public static String getIpAddr(HttpServletRequest request) {
		String ip = request.getHeader("x-forwarded-for");
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("WL-Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getRemoteAddr();
		}
		return ip;
	}

	/**
	 * 获取用户信息
	 * 
	 * @param request
	 * @return
	 */
	public static CurrentUser getCurrentUser(HttpServletRequest request) {

		return (CurrentUser) request.getSession().getAttribute(Session.CURRENT_USER);
	}

	/**
	 * 获取Cookie值
	 * 
	 * @param request
	 * @param name
	 * @return
	 */
	public static String getCookieValue(HttpServletRequest request, String name) {
		Cookie[] cookies = request.getCookies();
		if (null != cookies) {
			for (Cookie cookie : cookies) {
				if (null != name && name.equals(cookie.getName())) {
					return cookie.getValue();
				}
			}
		}
		return null;
	}

	/**
	 * http://denghb.com/images/favicon.ico
	 *
	 * @param url
	 * @return ico
	 */
	public static String getUrlExt(String url) {
		if (null != url) {
			int leng = url.length();
			if (0 < leng) {
				int i = url.lastIndexOf(".");
				if (-1 < i && i < leng - 1) {
					return url.substring(i + 1, leng);
				}
			}

		}
		return null;
	}
}
