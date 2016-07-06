package com.denghb.admin.service;

import java.util.List;

import com.denghb.admin.base.AdminException;
import com.denghb.admin.base.CurrentUser;

public interface SysUserRoleService {

	/**
	 * 更新角色与用户关联（按用户清空缓存）
	 * 
	 * @param currentUser
	 * @param accountId
	 * @param roleIds
	 * @throws AdminException
	 */
	void update(CurrentUser currentUser, long accountId, int[] roleIds) throws AdminException;

	/**
	 * 查询用户所拥有的角色id
	 * 
	 * @param currentUser
	 * @param accountId
	 * @return
	 */
	List<Integer> queryRoleIdsByAccountId(CurrentUser currentUser, long accountId);

}
