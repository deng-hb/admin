package com.denghb.admin.service;

import java.util.List;

import com.denghb.admin.base.AdminException;
import com.denghb.admin.base.CurrentUser;

/**
 * 角色资源分配服务
 * 
 * @author denghb
 *
 */
public interface SysRoleResourceService {

	/**
	 * 按角色ID分配资源
	 * 
	 * @param currentUser
	 * @param roleId
	 * @param resourceIds
	 * @throws AdminException
	 */
	void update(CurrentUser currentUser, int roleId, int[] resourceIds) throws AdminException;

	/**
	 * 按角色ID查询已分配资源
	 * 
	 * @param currentUser
	 * @param roleId
	 * @return
	 */
	List<Integer> queryResourceIdsByRoleId(CurrentUser currentUser, int roleId);

}
