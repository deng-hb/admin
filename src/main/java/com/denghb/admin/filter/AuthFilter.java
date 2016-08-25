package com.denghb.admin.filter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.denghb.admin.base.Consts.AA;
import com.denghb.admin.base.Consts.BOOL;
import com.denghb.admin.base.Consts.Session;
import com.denghb.admin.base.Consts.User;
import com.denghb.admin.base.CurrentUser;
import com.denghb.admin.base.JsonResponse;
import com.denghb.admin.domain.AccountAccess;
import com.denghb.admin.domain.SysResource;
import com.denghb.admin.service.AccountAccessService;
import com.denghb.admin.service.AccountService;
import com.denghb.admin.service.SysResourceService;
import com.denghb.admin.utils.SpringUtils;
import com.denghb.admin.utils.WebUtils;
import com.google.gson.Gson;

public class AuthFilter implements Filter {

	private final Logger log = LoggerFactory.getLogger(AuthFilter.class);

	private static SysResourceService sysResourceService;

	private static AccountService accountService;

	private static AccountAccessService accountAccessService;

	private static Map<String, SysResource> sysResources = new HashMap<String, SysResource>();

	@Override
	public void init(FilterConfig config) throws ServletException {
		// TODO Auto-generated method stub

	}

	@Override
	public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)
			throws IOException, ServletException {
		// 初始化service
		if (null == sysResourceService || null == accountAccessService) {
			sysResourceService = SpringUtils.getBean(SysResourceService.class);
			accountAccessService = SpringUtils.getBean(AccountAccessService.class);
			accountService = SpringUtils.getBean(AccountService.class);
		}
		HttpServletRequest request = (HttpServletRequest) servletRequest;
		HttpServletResponse response = (HttpServletResponse) servletResponse;

		String ipAddr = WebUtils.getIpAddr(request);
		// 请求路径
		String uri = request.getRequestURI();
		// 作用域
		String ctx = request.getContextPath();
		if (StringUtils.isNotBlank(uri)) {
			// 去除作用域
			uri = uri.substring(ctx.length(), uri.length());
		}
		log.info("=>[{}] =>[{}]", ipAddr, uri);
		// TODO 静态资源直接过
		if (null != uri && 0 == uri.indexOf("/assets/")) {
			filterChain.doFilter(new XssHttpServletRequestWrapper(request), response);
			return;
		}

		// TODO 当前ContentPath
		request.setAttribute("ctx", ctx);

		request.setAttribute("uri", uri);
		
		// 初始化系统资源
		initSysResources();
		SysResource resource = sysResources.get(uri);

		if (null != resource) {
			String userAgent = request.getHeader("User-Agent");
			String token = WebUtils.getCookieValue(request, Session.TOKEN);
			if(StringUtils.isBlank(token)){
				filterChain.doFilter(new XssHttpServletRequestWrapper(request), response);
				return;
			}
			AccountAccess aa = accountAccessService.query(token);
			if (null == aa || new Date().after(aa.getExpiryTime())) {
				// 会话过期
				jump(request, response, Session.TIMEOUT);
				return;
			} else if (!ipAddr.equals(aa.getIpAddr()) || !userAgent.equals(aa.getUserAgent())) {
				// 登录信息发生变化
				jump(request, response, Session.ERROR_SECURITY);
				return;
			} else if (AA.Status.STATUS_1 == aa.getStatus()) {
				// 强制踢出
				jump(request, response, Session.FORCE_OUT);
				return;
			}

			HttpSession session = request.getSession();

			CurrentUser currentUser = WebUtils.getCurrentUser(request);
			if (null == currentUser) {
				// 恢复会话
				currentUser = accountService.restore(aa.getAccountId());
				session.setAttribute(Session.CURRENT_USER, currentUser);
			}

			@SuppressWarnings("unchecked")
			List<String> urlsList = (List<String>) session.getAttribute(Session.CURRENT_USER_RESOURCE_URLS);

			if (null == urlsList) {

				List<SysResource> myResources = null;
				// TODO 管理员
				if (User.SuperUsername.equals(currentUser.getUsername())) {
					myResources = sysResourceService.listAll(currentUser);
				} else {
					myResources = sysResourceService.querySysResourceByAccountId(currentUser,
							currentUser.getAccountId());

				}
				List<SysResource> menuList = new ArrayList<SysResource>();
				urlsList = new ArrayList<String>();
				for (SysResource res : myResources) {
					// 全部链接
					String url = res.getUrl();
					if (StringUtils.isNotBlank(url)) {
						urlsList.add(url);
					}
					// 菜单
					if (1 == res.getType()) {
						menuList.add(res);
					}
				}

				// 用于比较权限
				session.setAttribute(Session.CURRENT_USER_RESOURCE_URLS, urlsList);
				// 用于前台页面菜单呈现
				session.setAttribute(Session.CURRENT_USER_RESOURCE_MENU, menuList);
			}

			if (null == urlsList || !urlsList.contains(uri)) {
				// 权限不足
				jump(request, response, Session.ERROR_403);
				return;
			}
			// TODO 当前访问页面的名称
			request.setAttribute("page_name", resource.getName());
			
			// 最后在线时间
			accountService.updateOnlineTime(currentUser, currentUser.getAccountId());

		}

		filterChain.doFilter(new XssHttpServletRequestWrapper(request), response);

	}

	@Override
	public void destroy() {
		// TODO Auto-generated method stub

	}

	private void initSysResources() {
		// 控制用户访问权限
		List<SysResource> listAll = sysResourceService.listAll(CurrentUser.sysUser());
		sysResources = new HashMap<String, SysResource>();
		// url->res
		for (SysResource res : listAll) {
			String url = res.getUrl();
			// 非公开的
			if (StringUtils.isNotBlank(url) && BOOL.FLASE == res.getIsPublic()) {
				sysResources.put(url, res);
			}
		}
	}

	private void jump(HttpServletRequest request, HttpServletResponse response, String to) throws IOException {
		// 会话失效
		request.getSession().invalidate();
		// ajax
		String requestType = request.getHeader("X-Requested-With");
		if (null != requestType) {
			response.setCharacterEncoding("UTF-8");
			response.setContentType("application/json;charset=UTF-8");
			String json = new Gson().toJson(JsonResponse.build2(to));
			response.getWriter().write(json);
			log.debug("ajax error ...............{}  ", to);
		} else {
			response.sendRedirect(to);
			log.debug("request error ...............{}  ", to);
		}
	}

}
