package com.denghb.admin.criteria;

import com.denghb.dbhelper.domain.Paging;

public class AccountCriteria extends Paging {

	private static final long serialVersionUID = 1L;

	// 设置排序字段
	@Override
	public String[] getSorts() {
		return new String[] { "id", "username", "sys_flag", "user_flag", "status", "last_online_time", "created_time" };
	}

}
