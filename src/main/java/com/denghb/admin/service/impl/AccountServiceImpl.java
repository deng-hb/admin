package com.denghb.admin.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.denghb.admin.base.AdminException;
import com.denghb.admin.base.CurrentUser;
import com.denghb.admin.criteria.AccountCriteria;
import com.denghb.admin.domain.Account;
import com.denghb.admin.domain.AccountPassword;
import com.denghb.admin.service.AccountService;
import com.denghb.dbhelper.DbHelper;
import com.denghb.dbhelper.domain.PagingResult;
import com.denghb.dbhelper.utils.DbHelperUtils;

@Service
public class AccountServiceImpl implements AccountService {

	@Autowired
	private DbHelper db;

	@Override
	public CurrentUser signIn(String username, String password) throws AdminException {

		// 查询
		StringBuffer sql = new StringBuffer();
		sql.append(DbHelperUtils.getSelectSql(Account.class));
		sql.append(" a inner join (select account_id from account_password where password = ? and deleted = 0) a");
		sql.append(" on a.id = apt.account_id where a.username = ? and a.deleted = 0");

		Account account = db.queryForObject(sql.toString(), Account.class, password, username);
		if (null == account) {
			throw new AdminException("账户不存在，或密码错误");
		}
		if (1 != account.getStatus()) {
			throw new AdminException("账户异常");
		}
		// TODO
		return CurrentUser.build(account.getId(), username, 0, 0);
	}

	@Override
	public CurrentUser signUp(String username, String password) throws AdminException {
		Account account = new Account();
		account.setUsername(username);
		account.setCreatedBy(0L);

		account.setSysFlag(0);// 系统用户
		account.setUserFlag(0);//
		boolean result = db.insert(account);
		if (!result) {
			throw new AdminException("sign up error");
		}

		AccountPassword ap = new AccountPassword();
		ap.setAccountId(account.getId());
		ap.setPassword(password);
		ap.setCreatedBy(0L);
		result = db.insert(ap);
		if (!result) {
			throw new AdminException("sign up error");
		}

		return CurrentUser.build(account.getId(), username, 0, 0);
	}

	@Override
	public CurrentUser restore(long accountId) {
		Account account = db.queryById(Account.class, accountId);
		return CurrentUser.build(accountId, account.getUsername(), 0, 0);
	}

	@Override
	public PagingResult<Account> list(CurrentUser currentUser, AccountCriteria criteria) {

		StringBuffer sql = new StringBuffer();
		sql.append(DbHelperUtils.getSelectSql(Account.class));

		PagingResult<Account> result = db.list(sql, Account.class, criteria);
		return result;
	}

	@Override
	public void closeAccount(CurrentUser currentUser, long accountId) throws AdminException {
		Account record = new Account();
		record.setId(accountId);
		record.setStatus(0);
		record.setUpdatedBy(currentUser.getAccountId());
		boolean result = db.updateById(record);
		if (!result) {
			throw AdminException.buildUpdateException();
		}
	}

	@Override
	public void openAccount(CurrentUser currentUser, long accountId) throws AdminException {
		Account record = new Account();
		record.setId(accountId);
		record.setStatus(1);
		record.setUpdatedBy(currentUser.getAccountId());
		boolean result = db.updateById(record);
		if (!result) {
			throw AdminException.buildUpdateException();
		}
	}

}