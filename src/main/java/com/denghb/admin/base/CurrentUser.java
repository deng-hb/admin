package com.denghb.admin.base;

import java.io.Serializable;

/**
 * Created by denghb on 15/11/18.
 */
public class CurrentUser implements Serializable {

	private static final long serialVersionUID = -8261664047015812991L;

	private long accountId;

	private String username;

	private int type;

	private int status;

	private CurrentUser() {

	}

	public static CurrentUser build(long accountId, String username, int type, int status) {
		CurrentUser currentUser = new CurrentUser();

		currentUser.accountId = accountId;
		currentUser.username = username;
		currentUser.type = type;
		currentUser.status = status;

		return currentUser;
	}

	/**
	 * 系统用户
	 * 
	 * @return
	 */
	public static CurrentUser sysUser() {
		return build(0L, "sys", 0, 0);
	}

	public long getAccountId() {
		return accountId;
	}

	public String getUsername() {
		return username;
	}

	public int getType() {
		return type;
	}

	public int getStatus() {
		return status;
	}

}
