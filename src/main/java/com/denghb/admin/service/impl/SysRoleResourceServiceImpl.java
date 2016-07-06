package com.denghb.admin.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.denghb.admin.base.AdminException;
import com.denghb.admin.base.Consts.Deleted;
import com.denghb.admin.base.CurrentUser;
import com.denghb.admin.domain.SysRoleResource;
import com.denghb.admin.service.SysRoleResourceService;
import com.denghb.dbhelper.DbHelper;

@Service
public class SysRoleResourceServiceImpl implements SysRoleResourceService {

	@Autowired
	private DbHelper db;

	@Override
	public void update(CurrentUser currentUser, int roleId, int[] resourceIds) throws AdminException {
		long by = currentUser.getAccountId();
		// 先全部删掉
		db.execute("update sys_role_resource set deleted = 1, updated_by = ? where role_id = ?", by, roleId);
		int len = resourceIds.length;
		if (0 < len) {
			SysRoleResource record;
			for (int i = 0; i < len; i++) {
				record = new SysRoleResource();
				record.setRoleId(roleId);
				record.setResourceId(resourceIds[i]);
				record.setCreatedBy(by);
				record.setDeleted(Deleted.FLASE);
				db.insert(record);
			}
		}

	}

	@Override
	public List<Integer> queryResourceIdsByRoleId(CurrentUser currentUser, int roleId) {
		
		String sql = "select resource_id from sys_role_resource where role_id = ? and deleted = 0";
		return db.list(sql, Integer.class, roleId);
	}

}
