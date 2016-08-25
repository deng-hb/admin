package com.denghb.admin.criteria;

import com.denghb.admin.dao.Paging;

public class SysUserCriteria extends Paging {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4366258578933769210L;

	@Override
	public String[] getSorts() {
		return new String[] { "id", "name", "email", "mobile", "birthday", "updated_time" };
	}
}
