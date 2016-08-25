package com.denghb.admin.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.denghb.admin.base.AdminException;
import com.denghb.admin.base.JsonResponse;
import com.denghb.admin.criteria.SysResourceCriteria;
import com.denghb.admin.dao.PagingResult;
import com.denghb.admin.domain.SysResource;
import com.denghb.admin.service.SysResourceService;
import com.denghb.admin.utils.DataUtils;
import com.denghb.admin.utils.ParameterUtils;
import com.denghb.admin.utils.WebUtils;

@Controller
@RequestMapping("/sys/resource")
public class SysResourceController {

	private final static Logger log = LoggerFactory.getLogger(SysResourceController.class);

	@Autowired
	private SysResourceService sysResourceService;

	// 主页面
	@RequestMapping("/")
	public String main() {

		return "sys/resource";
	}

	// 列表
	@RequestMapping("/list")
	@ResponseBody
	public JsonResponse list(HttpServletRequest request) {

		SysResourceCriteria criteria = new SysResourceCriteria();
		ParameterUtils.initDataTablesParams(request, criteria);

		PagingResult<SysResource> result = sysResourceService.list(WebUtils.getCurrentUser(request), criteria);
		
		return JsonResponse.buildObject(DataUtils.pagingResult2DataTablesResult(result));
	}
	
	@RequestMapping("/list-all")
	@ResponseBody
	public JsonResponse listAll(HttpServletRequest request){
		List<SysResource> list = sysResourceService.listAll(WebUtils.getCurrentUser(request));
	
		return JsonResponse.buildObject(list);
	}

	@RequestMapping("/create")
	@ResponseBody
	public JsonResponse create(HttpServletRequest request, SysResource res) {
		// TODO 判断
		if (null == res) {

		}
		try {
			sysResourceService.create(WebUtils.getCurrentUser(request), res);
		} catch (AdminException e) {
			log.error(e.getMessage(), e);
			return JsonResponse.buildFailure();
		}

		return JsonResponse.buildSuccess();
	}

	@RequestMapping("/update")
	@ResponseBody
	public JsonResponse update(HttpServletRequest request, SysResource res) {
		try {
			if(!StringUtils.hasText(res.getUrl())){
				res.setUrl(null);
			}
			sysResourceService.update(WebUtils.getCurrentUser(request), res);
		} catch (AdminException e) {
			log.error(e.getMessage(), e);
			return JsonResponse.buildFailure();
		}
		return JsonResponse.buildSuccess();
	}

	@RequestMapping("/delete")
	@ResponseBody
	public JsonResponse delete(HttpServletRequest request) {
		
		try {
			String idsString = request.getParameter("ids");
			String[] idsStringArray = idsString.split(",");
			int[] ids = new int[idsStringArray.length];
			for (int i = 0; i < ids.length; i++) {
				ids[i] = Integer.parseInt(idsStringArray[i]);
			}
			sysResourceService.delete(WebUtils.getCurrentUser(request), ids);
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			return JsonResponse.buildFailure();
		}
		return JsonResponse.buildSuccess();
	}
}
