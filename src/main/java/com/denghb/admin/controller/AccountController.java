package com.denghb.admin.controller;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.denghb.admin.base.DataTablesResult;
import com.denghb.admin.base.JsonResponse;
import com.denghb.admin.criteria.AccountCriteria;
import com.denghb.admin.dao.PagingResult;
import com.denghb.admin.domain.Account;
import com.denghb.admin.service.AccountAccessService;
import com.denghb.admin.service.AccountService;
import com.denghb.admin.utils.DataUtils;
import com.denghb.admin.utils.ParameterUtils;
import com.denghb.admin.utils.WebUtils;

@Controller
@RequestMapping("/account")
public class AccountController {

	private static final Logger log = LoggerFactory.getLogger(AccountController.class);

	@Autowired
	private AccountService accountService;

	@Autowired
	private AccountAccessService accountAccessService;
	
	// 账户首页
	@RequestMapping("/")
	public String index() {
		return "account/main";
	}

	@RequestMapping("/list")
	@ResponseBody
	public JsonResponse list(HttpServletRequest request) {

		AccountCriteria criteria = new AccountCriteria();
		ParameterUtils.initDataTablesParams(request, criteria);

		PagingResult<Account> result = accountService.list(WebUtils.getCurrentUser(request), criteria);
		return JsonResponse.buildObject(DataUtils.pagingResult2DataTablesResult(result));
	}

	@RequestMapping("/close")
	@ResponseBody
	public JsonResponse close(HttpServletRequest request) {
		String id = request.getParameter("id");
		try {
			accountService.closeAccount(WebUtils.getCurrentUser(request), Long.parseLong(id));
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			return JsonResponse.buildFailure();
		}
		return JsonResponse.buildSuccess();
	}

	@RequestMapping("/open")
	@ResponseBody
	public JsonResponse open(HttpServletRequest request) {
		String id = request.getParameter("id");
		try {
			accountService.openAccount(WebUtils.getCurrentUser(request), Long.parseLong(id));
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			return JsonResponse.buildFailure();
		}
		return JsonResponse.buildSuccess();
	}

	@RequestMapping("/force-signout")
	@ResponseBody
	public JsonResponse forceSignout(HttpServletRequest request) {
		try {
			String idsString = request.getParameter("ids");
			String[] idsStringArray = idsString.split(",");
			long[] accountIds = new long[idsStringArray.length];
			for (int i = 0; i < accountIds.length; i++) {
				accountIds[i] = Long.parseLong(idsStringArray[i]);
			}
			accountAccessService.destroy(WebUtils.getCurrentUser(request), accountIds);
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			return JsonResponse.buildFailure();
		}
		return JsonResponse.buildSuccess();
	}
}
