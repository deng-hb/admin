package com.denghb.admin.base;

public class Consts {

	public static class User {
		public static final String SuperUsername = "admin";
	}

	public static class Session {
		/** 当前用户信息 **/
		public static final String CURRENT_USER = "CURRENT_USER";
		/** 当前用户拥有的菜单列表 **/
		public static final String CURRENT_USER_RESOURCE_MENU = "CURRENT_USER_RESOURCE_MENU";
		/** 当前用户拥有的资源列表 **/
		public static final String CURRENT_USER_RESOURCE_URLS = "CURRENT_USER_RESOURCE_URLS";

		public static final String TOKEN = "TOKEN";

		/** 安全｛保存用户当前登录的ip＋ua每次比较，不一致就提示安全问题｝ */
		public static final String SECURITY = "security";

		public static final String TIMEOUT = "/error/timeout";

		public static final String FORCE_OUT = "/error/forceout";

		public static final String ERROR_403 = "/error/403";

		public static final String ERROR_SECURITY = "/error/security";
		
	}

	public static class AA {
		public static class Status {
			/** 正常 */
			public static final int STATUS_0 = 0;
			/** 强制踢出 */
			public static final int STATUS_1 = 1;
		}
	}

	public static class Deleted {
		public static final int TRUE = 1;
		public static final int FLASE = 0;
	}

	public static class BOOL {
		public static final int TRUE = 1;
		public static final int FLASE = 0;
	}
}
