package com.denghb.admin.service.impl;

import java.text.MessageFormat;
import java.util.Date;
import java.util.UUID;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.denghb.admin.base.Consts.AA;
import com.denghb.admin.base.CurrentUser;
import com.denghb.admin.domain.AccountAccess;
import com.denghb.admin.service.AccountAccessService;
import com.denghb.dbhelper.DbHelper;
import com.denghb.dbhelper.DbHelperUtils;

@Service
public class AccountAccessServiceImpl implements AccountAccessService {

	@Autowired
	private DbHelper db;

	@Override
	public String create(long accountId, String userAgent, String ipAddr, Date expiryTime) {

		// 销毁之前的
		String sql = MessageFormat.format(
				"update account_access set status = {0},updated_by = ? where account_id = ? and status = {1}",
				AA.Status.STATUS_1, AA.Status.STATUS_0);
		db.execute(sql, accountId, accountId);

		// TODO 生成规则
		String token = UUID.randomUUID().toString();
		token = token.toUpperCase().replaceAll("-", "");

		AccountAccess accountAccess = new AccountAccess();
		accountAccess.setAccountId(accountId);
		accountAccess.setToken(token);
		accountAccess.setExpiryTime(expiryTime);
		accountAccess.setIpAddr(ipAddr);
		accountAccess.setUserAgent(userAgent);
		accountAccess.setStatus(AA.Status.STATUS_0);
		accountAccess.setCreatedBy(accountId);
		db.insert(accountAccess);

		return token;
	}

	@Override
	public void destroy(CurrentUser currentUser, long[] accountIds) {
		int len = accountIds.length;
		if (1 > len) {
			return;
		}

		// 销毁之前的
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < accountIds.length; i++) {
			if (0 != i) {
				sb.append(",");
			}
			sb.append(accountIds[i]);

		}
		String sql = MessageFormat.format(
				"update admin.account_access set status = {0},updated_by = ? where account_id in ({1}) and status = {2}",
				1, sb.toString(), 0);
		db.execute(sql, 0L);
	}

	@Override
	public AccountAccess query(String token) {
		if (StringUtils.isBlank(token)) {
			return null;
		}
		StringBuffer sql = new StringBuffer();
		sql.append(DbHelperUtils.getSelectSql(AccountAccess.class));
		sql.append(" where token = ? and deleted = 0");
		return db.queryForObject(sql.toString(), AccountAccess.class, token);
	}

}
