package com.denghb.admin.service;

import java.util.List;

import com.denghb.admin.base.AdminException;
import com.denghb.admin.base.CurrentUser;
import com.denghb.admin.criteria.SysResourceCriteria;
import com.denghb.admin.domain.SysResource;
import com.denghb.dbhelper.domain.PagingResult;

public interface SysResourceService {
	/**
	 * 创建
	 * 
	 * @param currentUser
	 * @param res
	 * @throws AdminException
	 */
	void create(CurrentUser currentUser, SysResource res) throws AdminException;

	/**
	 * 修改（主键）
	 * 
	 * @param currentUser
	 * @param res
	 * @throws AdminException
	 */
	void update(CurrentUser currentUser, SysResource res) throws AdminException;

	/**
	 * 列表
	 * 
	 * @param currentUser
	 * @param criteria
	 * @return
	 */
	PagingResult<SysResource> list(CurrentUser currentUser, SysResourceCriteria criteria);

	List<SysResource> listAll(CurrentUser currentUser);

	/**
	 * 逻辑删除
	 * 
	 * @param currentUser
	 * @param id
	 * @throws AdminException
	 */
	void delete(CurrentUser currentUser, int[] ids) throws AdminException;

	/**
	 * 按用户ID查询资源ID
	 * 
	 * @param currentUser
	 * @param accountId
	 * @return
	 */
	List<SysResource> querySysResourceByAccountId(CurrentUser currentUser, long accountId);

}
