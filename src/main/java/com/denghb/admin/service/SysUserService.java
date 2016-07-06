package com.denghb.admin.service;

import com.denghb.admin.base.AdminException;
import com.denghb.admin.base.CurrentUser;
import com.denghb.admin.criteria.SysUserCriteria;
import com.denghb.admin.domain.SysUser;
import com.denghb.dbhelper.domain.PagingResult;

public interface SysUserService {

	void update(CurrentUser currentUser, SysUser user) throws AdminException;

	PagingResult<SysUser> list(CurrentUser currentUser, SysUserCriteria criteria);

	void delete(CurrentUser currentUser, long[] ids) throws AdminException;

	void create(CurrentUser currentUser, SysUser user) throws AdminException;
}
