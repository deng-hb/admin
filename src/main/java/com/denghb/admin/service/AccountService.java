package com.denghb.admin.service;

import com.denghb.admin.base.AdminException;
import com.denghb.admin.base.CurrentUser;
import com.denghb.admin.criteria.AccountCriteria;
import com.denghb.admin.dao.PagingResult;
import com.denghb.admin.domain.Account;

public interface AccountService {

	/**
	 * 登录
	 * 
	 * @param username
	 * @param password
	 * @return
	 * @throws AdminException
	 */
	CurrentUser signIn(String username, String password) throws AdminException;

	/**
	 * 注册
	 * 
	 * @param email
	 * @param password
	 * @return
	 * @throws AdminException
	 */
	CurrentUser signUp(String username, String password) throws AdminException;

	/**
	 * 恢复
	 * 
	 * @param accountId
	 * @return
	 */
	CurrentUser restore(long accountId);

	/**
	 * 列表
	 * 
	 * @param currentUser
	 * @param criteria
	 * @return
	 */
	PagingResult<Account> list(CurrentUser currentUser, AccountCriteria criteria);

	/**
	 * 关闭账户
	 * 
	 * @param currentUser
	 * @param accountId
	 * @throws AdminException
	 */
	void closeAccount(CurrentUser currentUser, long accountId) throws AdminException;

	/**
	 * 打开账户
	 * 
	 * @param currentUser
	 * @param accountId
	 * @throws AdminException
	 */
	void openAccount(CurrentUser currentUser, long accountId) throws AdminException;

	/**
	 * 更新最后在线时间
	 * 
	 * @param currentUser
	 * @param accountId
	 */
	void updateOnlineTime(CurrentUser currentUser, long accountId);
}
