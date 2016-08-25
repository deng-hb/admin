package com.denghb.admin.criteria;

import com.denghb.admin.dao.Paging;

public class AccountOnlineCriteria extends Paging {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4704561827171031638L;

	/** 是否查询最新 */
	private boolean isLast = true;

	public boolean isLast() {
		return isLast;
	}

	public void setLast(boolean isLast) {
		this.isLast = isLast;
	}

	@Override
	public String[] getSorts() {
		return new String[] { "id", "account_id", "ip_addr", "user_agent", "created_time" };
	}

}
