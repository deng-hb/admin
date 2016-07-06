package com.denghb.admin.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.denghb.admin.base.AdminException;
import com.denghb.admin.base.Consts.Deleted;
import com.denghb.admin.base.CurrentUser;
import com.denghb.admin.criteria.SysResourceCriteria;
import com.denghb.admin.domain.SysResource;
import com.denghb.admin.service.SysResourceService;
import com.denghb.dbhelper.DbHelper;
import com.denghb.dbhelper.domain.PagingResult;
import com.denghb.dbhelper.utils.DbHelperUtils;

@Service
public class SysResourceServiceImpl implements SysResourceService {

	@Autowired
	private DbHelper db;

	// 清除缓存
	@CacheEvict(value = "sysResource", keyGenerator = "emptyKeyGenerator")
	@Override
	public void create(CurrentUser currentUser, SysResource res) throws AdminException {
		res.setCreatedBy(currentUser.getAccountId());
		boolean result = db.insert(res);
		if (!result) {
			throw AdminException.buildCreateException();
		}
	}

	// 清除缓存
	@CacheEvict(value = "sysResource", keyGenerator = "emptyKeyGenerator")
	@Override
	public void update(CurrentUser currentUser, SysResource res) throws AdminException {
		res.setUpdatedBy(currentUser.getAccountId());
		boolean result = db.updateById(res);
		if (!result) {
			throw AdminException.buildUpdateException();
		}

	}

	@Override
	public PagingResult<SysResource> list(CurrentUser currentUser, SysResourceCriteria criteria) {

		StringBuffer sql = new StringBuffer();
		sql.append(DbHelperUtils.getSelectSql(SysResource.class));

		PagingResult<SysResource> result = db.list(sql, SysResource.class, criteria);
		return result;
	}

	// 缓存
	@Cacheable(value = "sysResource", keyGenerator = "emptyKeyGenerator")
	@Override
	public List<SysResource> listAll(CurrentUser currentUser) {
		return db.list(DbHelperUtils.getSelectSql(SysResource.class), SysResource.class);
	}

	// 清除缓存
	@CacheEvict(value = "sysResource", keyGenerator = "emptyKeyGenerator")
	@Override
	public void delete(CurrentUser currentUser, int[] ids) throws AdminException {
		if (0 == ids.length) {
			return;
		}
		// 有子资源的不能删除
		// TODO 重复删除
		for (int i = 0; i < ids.length; i++) {

			SysResource res = new SysResource();
			res.setId(ids[i]);
			res.setUpdatedBy(currentUser.getAccountId());
			res.setDeleted(Deleted.TRUE);
			boolean result = db.updateById(res);
			if (!result) {
				throw AdminException.buildDeleteException();
			}
		}
	}

	@Override
	public List<SysResource> querySysResourceByAccountId(CurrentUser currentUser, long accountId) {
		// 查询用户资源
		StringBuffer sql = new StringBuffer(DbHelperUtils.getSelectSql(SysResource.class));
		sql.append(" where id in (select resource_id from sys_role_resource ");
		sql.append(" where role_id in (select role_id from sys_user_role ");
		sql.append(" where account_id = ? and deleted = 0)  and deleted = 0) and deleted = 0");
		return db.list(sql.toString(), SysResource.class, accountId);
	}
}
