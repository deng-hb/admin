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
import com.denghb.admin.service.SysRoleResourceService;

/**
 * 
 * @author denghb
 *
 */
@Controller
@RequestMapping("/sys/role-resource")
public class SysRoleResourceController {

	private final static Logger log = LoggerFactory.getLogger(SysRoleResourceController.class);

	@Autowired
	private SysRoleResourceService sysRoleResourceService;

	@RequestMapping("/update")
	@ResponseBody
	public JsonResponse update(HttpServletRequest request) {
		String roleId = request.getParameter("roleId");
		String resourceIdsString = request.getParameter("resourceIds");
		String[] resourceIdsStringArray = resourceIdsString.split(",");

		int[] resourceIds = new int[resourceIdsStringArray.length];
		for (int i = 0; i < resourceIds.length; i++) {
			resourceIds[i] = Integer.parseInt(resourceIdsStringArray[i]);
		}
		try {
			sysRoleResourceService.update(CurrentUser.sysUser(), Integer.parseInt(roleId), resourceIds);
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			return JsonResponse.buildFailure("操作失败");
		}
		return JsonResponse.buildSuccess("操作成功");
	}
	
	@RequestMapping("/query")
	@ResponseBody
	public JsonResponse queryResourceIdsByRoleId(HttpServletRequest request){
		String roleId = request.getParameter("roleId");
		List<Integer> resourceIds = sysRoleResourceService.queryResourceIdsByRoleId(CurrentUser.sysUser(),Integer.parseInt( roleId));
		
		return JsonResponse.buildObject(resourceIds);
	}
}
