package com.denghb.admin.criteria;

import com.denghb.admin.dao.Paging;

public class SysResourceCriteria extends Paging {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public String[] getSorts() {
		// TODO Auto-generated method stub
		return new String[] { "id", "name", "url", "type", "parent_id", "sort", "updated_time" };
	}

}
