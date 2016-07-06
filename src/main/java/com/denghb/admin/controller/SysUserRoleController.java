package com.denghb.admin.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.denghb.admin.base.CurrentUser;
import com.denghb.admin.base.JsonResponse;
import com.denghb.admin.service.SysUserRoleService;

/**
 * 
 * @author denghb
 *
 */
@Controller
@RequestMapping("/sys/user-role")
public class SysUserRoleController {

	private final static Logger log = LoggerFactory.getLogger(SysUserRoleController.class);

	@Autowired
	private SysUserRoleService sysUserRoleService;

	@RequestMapping("/update")
	@ResponseBody
	public JsonResponse update(HttpServletRequest request) {
		String accountId = request.getParameter("accountId");
		String roleIdsString = request.getParameter("roleIds");
		String[] roleIdsStringArray = roleIdsString.split(",");

		int[] roleIds = new int[roleIdsStringArray.length];
		for (int i = 0; i < roleIds.length; i++) {
			roleIds[i] = Integer.parseInt(roleIdsStringArray[i]);
		}
		try {
			sysUserRoleService.update(CurrentUser.sysUser(), Long.parseLong(accountId), roleIds);
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			return JsonResponse.buildFailure("操作失败");
		}
		return JsonResponse.buildSuccess("操作成功");
	}

	@RequestMapping("/query")
	@ResponseBody
	public JsonResponse queryResourceIdsByRoleId(HttpServletRequest request) {
		String accountId = request.getParameter("accountId");
		List<Integer> roleIds = sysUserRoleService.queryRoleIdsByAccountId(CurrentUser.sysUser(),
				Long.parseLong(accountId));

		return JsonResponse.buildObject(roleIds);
	}
}
