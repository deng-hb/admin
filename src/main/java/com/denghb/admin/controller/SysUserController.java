package com.denghb.admin.controller;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.denghb.admin.base.AdminException;
import com.denghb.admin.base.CurrentUser;
import com.denghb.admin.base.JsonResponse;
import com.denghb.admin.base.ParameterException;
import com.denghb.admin.criteria.SysUserCriteria;
import com.denghb.admin.dao.PagingResult;
import com.denghb.admin.domain.SysUser;
import com.denghb.admin.service.SysUserService;
import com.denghb.admin.utils.DataUtils;
import com.denghb.admin.utils.DateUtils;
import com.denghb.admin.utils.ParameterUtils;
import com.denghb.admin.utils.WebUtils;

@Controller
@RequestMapping("/sys/user")
public class SysUserController {

	private static final Logger log = LoggerFactory.getLogger(SysUserController.class);

	@Autowired
	private SysUserService sysUserService;

	@RequestMapping("/")
	public String main() {

		return "sys/user";
	}

	@RequestMapping("/list")
	@ResponseBody
	public JsonResponse list(HttpServletRequest request) {

		SysUserCriteria criteria = new SysUserCriteria();
		ParameterUtils.initDataTablesParams(request, criteria);
		PagingResult<SysUser> result = sysUserService.list(CurrentUser.sysUser(), criteria);
		return JsonResponse.buildObject(DataUtils.pagingResult2DataTablesResult(result));
	}

	@RequestMapping("/update")
	@ResponseBody
	public JsonResponse update(HttpServletRequest request) {
		try {
			String id = request.getParameter("id");
			String name = request.getParameter("name");
			String email = request.getParameter("email");
			String mobile = request.getParameter("mobile");
			String birthday = request.getParameter("birthday");

			SysUser user = new SysUser();
			user.setId(Long.parseLong(id));
			user.setEmail(email);
			user.setMobile(mobile);
			user.setName(name);
			user.setBirthday(DateUtils.getDate(birthday, "yyyy-MM-dd"));

			sysUserService.update(WebUtils.getCurrentUser(request), user);
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			return JsonResponse.buildFailure("操作失败");
		}
		return JsonResponse.buildSuccess("操作成功");
	}

	@RequestMapping("/create")
	@ResponseBody
	public JsonResponse create(HttpServletRequest request) {
		try {
			String name = ParameterUtils.getNotBlankString(request, "name", "姓名");
			String email = ParameterUtils.getNotBlankString(request, "email", "邮箱");
			String mobile = ParameterUtils.getNotBlankString(request, "mobile", "手机号");
			Date birthday = ParameterUtils.getNotNullDate(request, "birthday", "yyyy-MM-dd", "生日");

			SysUser user = new SysUser();
			user.setEmail(email);
			user.setMobile(mobile);
			user.setName(name);
			user.setBirthday(birthday);

			sysUserService.create(WebUtils.getCurrentUser(request), user);
		} catch (AdminException e) {
			log.error(e.getMessage(), e);
			return JsonResponse.buildFailure("操作失败");
		} catch (ParameterException e) {
			return JsonResponse.buildFailure(e.getMessage());
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			return JsonResponse.buildFailure("操作失败");
		}
		return JsonResponse.buildSuccess("操作成功");
	}

	@RequestMapping("/delete")
	@ResponseBody
	public JsonResponse delete(HttpServletRequest request) {

		try {
			long[] ids = ParameterUtils.getLongArray(request, "ids", null);
			sysUserService.delete(WebUtils.getCurrentUser(request), ids);
			return JsonResponse.buildSuccess("操作成功");
		} catch (AdminException e) {
			log.error(e.getMessage(), e);
			return JsonResponse.buildFailure(e.getMessage());
		} catch (ParameterException e) {
			log.error(e.getMessage(), e);
			return JsonResponse.buildFailure(e.getMessage());
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			return JsonResponse.buildFailure("操作失败");
		}
	}
}
