package com.denghb.admin.criteria;

import com.denghb.dbhelper.domain.Paging;

public class SysRoleCriteria extends Paging {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4366258578933769210L;

	@Override
	public String[] getSorts() {
		// TODO Auto-generated method stub
		return new String[] { "id", "name", "description", "updated_time" };
	}
}
