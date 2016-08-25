package com.denghb.admin.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.denghb.admin.base.AdminException;
import com.denghb.admin.base.Consts.Deleted;
import com.denghb.admin.base.CurrentUser;
import com.denghb.admin.criteria.SysRoleCriteria;
import com.denghb.admin.dao.DbDao;
import com.denghb.admin.dao.PagingResult;
import com.denghb.admin.domain.SysRole;
import com.denghb.admin.service.SysRoleService;
import com.denghb.dbhelper.DbHelperUtils;

@Service
public class SysRoleServiceImpl implements SysRoleService {

	@Autowired
	private DbDao db;

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
		sql.append(" where deleted = 0 ");
		PagingResult<SysRole> result = db.list(sql , SysRole.class, criteria);
		return result;
	}

	@Override
	public void delete(CurrentUser currentUser, int[] ids) throws AdminException {
		for (int i = 0; i < ids.length; i++) {
			int id = ids[i];
			
			// 判断是否有用户正在使用
			String sql = "select count(*) from sys_user_role where role_id = ? and deleted = 0";
			Integer count = db.queryForObject(sql, Integer.class, id);
			if (null != count && 0 < count) {
				throw AdminException.buildException(String.format("ID:%d 有用户正在使用", id));
			}
			
			SysRole role = new SysRole();
			role.setId(id);
			role.setUpdatedBy(currentUser.getAccountId());
			role.setDeleted(Deleted.TRUE);
			boolean result = db.updateById(role);
			if (!result) {
				throw AdminException.buildDeleteException();
			}
			// 删除角色下对应的资源
			sql = "update sys_role_resource set deleted = 1 where role_id = ?";
			db.execute(sql, id);
		}
	}

	@Override
	public List<SysRole> listAll(CurrentUser currentUser) {
		String sql = DbHelperUtils.getSelectSql(SysRole.class);
		sql += " where deleted = 0 order by id desc";
		return db.list(sql, SysRole.class);
	}

}
