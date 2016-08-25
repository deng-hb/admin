package com.denghb.admin.service.impl;

import java.util.UUID;

import org.apache.commons.lang.RandomStringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.denghb.admin.base.AdminException;
import com.denghb.admin.base.Consts.Deleted;
import com.denghb.admin.base.CurrentUser;
import com.denghb.admin.criteria.SysUserCriteria;
import com.denghb.admin.dao.DbDao;
import com.denghb.admin.dao.PagingResult;
import com.denghb.admin.domain.SysUser;
import com.denghb.admin.service.AccountService;
import com.denghb.admin.service.SysUserService;
import com.denghb.admin.utils.Md5Utils;
import com.denghb.dbhelper.DbHelperUtils;

@Service
public class SysUserServiceImpl implements SysUserService {

	private final static Logger log = LoggerFactory.getLogger(SysUserServiceImpl.class);

	@Autowired
	private AccountService accountService;

	@Autowired
	private DbDao db;

	@Override
	public void update(CurrentUser currentUser, SysUser user) throws AdminException {
		user.setUpdatedBy(currentUser.getAccountId());
		user.setDeleted(Deleted.FLASE);
		boolean result = db.updateById(user);
		if (!result) {
			log.error("update error");
			throw AdminException.buildException("update error");
		}
	}

	@Override
	public PagingResult<SysUser> list(CurrentUser currentUser, SysUserCriteria criteria) {
		StringBuffer sql = new StringBuffer();
		sql.append(DbHelperUtils.getSelectSql(SysUser.class));
		PagingResult<SysUser> result = db.list(sql, SysUser.class, criteria);
		return result;
	}

	@Override
	public void delete(CurrentUser currentUser, long[] ids) throws AdminException {
		for (int i = 0; i < ids.length; i++) {
			SysUser user = new SysUser();
			user.setId(ids[i]);
			user.setUpdatedBy(currentUser.getAccountId());
			user.setDeleted(Deleted.TRUE);
			boolean result = db.updateById(user);
			if (!result) {
				throw AdminException.buildDeleteException();
			}
		}
	}

	@Override
	public void create(CurrentUser currentUser, SysUser user) throws AdminException {

		// 随机生成8位密码
		String rd = RandomStringUtils.randomAlphabetic(8);
		String md5 = Md5Utils.hash(rd);

		// TODO 发邮件或短信通知密码
		// 创建账户
		CurrentUser cu = accountService.signUp(user.getEmail(), md5);

		// 创建信息
		user.setId(cu.getAccountId());
		user.setCreatedBy(currentUser.getAccountId());
		boolean result = db.insert(user);

		if (!result) {
			throw AdminException.buildCreateException();
		}
	}

	public static void main(String[] args) {
		String md5 = Md5Utils.hash("123213");
		System.out.println(md5);

		String uuid = UUID.randomUUID().toString();

		String pwd = uuid.substring(0, uuid.indexOf('-'));
		System.out.println(pwd);

		String rd = RandomStringUtils.randomAlphabetic(6);

		System.out.println(rd);
	}
}
