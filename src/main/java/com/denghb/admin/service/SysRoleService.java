package com.denghb.admin.service;

import java.util.List;

import com.denghb.admin.base.AdminException;
import com.denghb.admin.base.CurrentUser;
import com.denghb.admin.criteria.SysRoleCriteria;
import com.denghb.admin.domain.SysRole;
import com.denghb.dbhelper.domain.PagingResult;

public interface SysRoleService {

	void create(CurrentUser currentUser, SysRole role) throws AdminException;

	void update(CurrentUser currentUser, SysRole role) throws AdminException;

	PagingResult<SysRole> list(CurrentUser currentUser, SysRoleCriteria criteria);

	void delete(CurrentUser currentUser, int[] ids) throws AdminException;
	
	List<SysRole> listAll(CurrentUser currentUser);
}
