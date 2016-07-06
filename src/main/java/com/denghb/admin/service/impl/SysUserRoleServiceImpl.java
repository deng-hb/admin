package com.denghb.admin.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.denghb.admin.base.AdminException;
import com.denghb.admin.base.CurrentUser;
import com.denghb.admin.domain.SysUserRole;
import com.denghb.admin.service.SysUserRoleService;
import com.denghb.dbhelper.DbHelper;

@Service
public class SysUserRoleServiceImpl implements SysUserRoleService {

	@Autowired
	private DbHelper db;

	@Override
	public void update(CurrentUser currentUser, long accountId, int[] roleIds) throws AdminException {
		long by = currentUser.getAccountId();
		// 先将之前的角色清理
		db.execute("update sys_user_role set deleted = 1, updated_by = ? where account_id = ?", by, accountId);
		int len = roleIds.length;
		if (0 < len) {
			for (int i = 0; i < roleIds.length; i++) {
				SysUserRole sysUser = new SysUserRole();
				sysUser.setCreatedBy(by);
				sysUser.setAccountId(accountId);
				sysUser.setRoleId(roleIds[i]);
				db.insert(sysUser);
			}
		}
	}

	@Override
	public List<Integer> queryRoleIdsByAccountId(CurrentUser currentUser, long accountId) {
		String sql = "select role_id from sys_user_role where account_id = ? and deleted = 0";
		return db.list(sql, Integer.class, accountId);
	}

}
