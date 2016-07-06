package com.denghb.admin.service;

import java.util.Date;

import com.denghb.admin.base.CurrentUser;
import com.denghb.admin.domain.AccountAccess;

public interface AccountAccessService {

	/**
	 * 创建授权
	 * 
	 * @param currentUser
	 * @param aa
	 */
	String create(long accountId, String userAgent, String ipAddr, Date expiryTime);

	/**
	 * 查询token
	 * 
	 * @param currentUser
	 * @param token
	 * @return
	 */
	AccountAccess query(String token);

	/**
	 * 销毁授权
	 * 
	 * @param currentUser
	 * @param aa
	 */
	void destroy(CurrentUser currentUser, long[] accountIds);
}
