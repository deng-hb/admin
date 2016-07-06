package com.denghb.admin.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.denghb.admin.base.AdminException;
import com.denghb.admin.base.CurrentUser;
import com.denghb.admin.base.DataTablesResult;
import com.denghb.admin.base.JsonResponse;
import com.denghb.admin.criteria.SysRoleCriteria;
import com.denghb.admin.domain.SysRole;
import com.denghb.admin.service.SysRoleService;
import com.denghb.admin.utils.ParameterUtils;
import com.denghb.admin.utils.WebUtils;

@Controller
@RequestMapping("/sys/role")
public class SysRoleController {

	@Autowired
	private SysRoleService sysRoleService;

	// 主页面
	@RequestMapping("/")
	public String main() {

		return "sys/role";
	}

	// 列表
	@RequestMapping("/list")
	@ResponseBody
	public JsonResponse list(HttpServletRequest request) {

		SysRoleCriteria criteria = new SysRoleCriteria();
		ParameterUtils.initDataTablesParams(request, criteria);

		DataTablesResult<SysRole> result = sysRoleService.list(CurrentUser.sysUser(), criteria);
		return JsonResponse.buildObject(result);
	}

	@RequestMapping("/create")
	@ResponseBody
	public JsonResponse create(HttpServletRequest request) {
		String name = request.getParameter("name");
		String description = request.getParameter("description");

		SysRole res = new SysRole();
		res.setName(name);
		res.setDescription(description);
		try {
			sysRoleService.create(CurrentUser.sysUser(), res);
		} catch (AdminException e) {
			return JsonResponse.buildFailure("操作失败");
		}

		return JsonResponse.buildSuccess("操作成功");
	}

	@RequestMapping("/update")
	@ResponseBody
	public JsonResponse update(HttpServletRequest request) {
		String id = request.getParameter("id");
		String name = request.getParameter("name");
		String description = request.getParameter("description");

		SysRole res = new SysRole();
		res.setId(Integer.parseInt(id));
		res.setName(name);
		res.setDescription(description);
		try {
			sysRoleService.update(CurrentUser.sysUser(), res);
		} catch (AdminException e) {
			return JsonResponse.buildFailure("操作失败");
		}

		return JsonResponse.buildSuccess("操作成功");
	}
	
	@RequestMapping("/delete")
	@ResponseBody
	public JsonResponse delete(HttpServletRequest request) {
		String idsString = request.getParameter("ids");
		String[] idsStringArray = idsString.split(",");
		int[] ids = new int[idsStringArray.length];
		for (int i = 0; i < ids.length; i++) {
			ids[i] = Integer.parseInt(idsStringArray[i]);
		}
		try {
			sysRoleService.delete(WebUtils.getCurrentUser(request), ids);
		} catch (AdminException e) {
			return JsonResponse.buildFailure("操作失败");
		}

		return JsonResponse.buildSuccess("操作成功");
	}
	
	// 全部
	@RequestMapping("/list-all")
	@ResponseBody
	public JsonResponse listAll(HttpServletRequest request) {

		List<SysRole> list = sysRoleService.listAll(CurrentUser.sysUser());
		return JsonResponse.buildObject(list);
	}

}
