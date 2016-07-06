package com.denghb.admin.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.denghb.admin.base.AdminException;
import com.denghb.admin.base.Consts.Deleted;
import com.denghb.admin.base.CurrentUser;
import com.denghb.admin.base.DataTablesResult;
import com.denghb.admin.criteria.SysRoleCriteria;
import com.denghb.admin.domain.SysRole;
import com.denghb.admin.service.SysRoleService;
import com.denghb.dbhelper.DbHelper;
import com.denghb.dbhelper.domain.PagingResult;
import com.denghb.dbhelper.utils.DbHelperUtils;

@Service
public class SysRoleServiceImpl implements SysRoleService {

	@Autowired
	private DbHelper db;

	@Override
	public void create(CurrentUser currentUser, SysRole role) throws AdminException {
		role.setCreatedBy(currentUser.getAccountId());
		role.setDeleted(Deleted.FLASE);
		boolean result = db.insert(role);
		if (!result) {
			throw AdminException.buildCreateException();
		}
	}

	@Override
	public void update(CurrentUser currentUser, SysRole role) throws AdminException {
		role.setUpdatedBy(currentUser.getAccountId());
		role.setDeleted(Deleted.FLASE);
		boolean result = db.updateById(role);
		if (!result) {
			throw AdminException.buildUpdateException();
		}
	}

	@Override
	public PagingResult<SysRole> list(CurrentUser currentUser, SysRoleCriteria criteria) {
		StringBuffer sql = new StringBuffer(DbHelperUtils.getSelectSql(SysRole.class));
		PagingResult<SysRole> result = db.list(sql , SysRole.class, criteria);
		return result;
	}

	@Override
	public void delete(CurrentUser currentUser, int[] ids) throws AdminException {
		for (int i = 0; i < ids.length; i++) {
			SysRole role = new SysRole();
			role.setId(ids[i]);
			role.setUpdatedBy(currentUser.getAccountId());
			role.setDeleted(Deleted.TRUE);
			boolean result = db.updateById(role);
			if (!result) {
				throw AdminException.buildDeleteException();
			}
		}
	}

	@Override
	public List<SysRole> listAll(CurrentUser currentUser) {
		String sql = DbHelperUtils.getSelectSql(SysRole.class);
		sql += " order by id desc";
		return db.list(sql, SysRole.class);
	}

}
