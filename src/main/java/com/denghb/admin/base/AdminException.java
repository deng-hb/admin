package com.denghb.admin.base;

public class AdminException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public AdminException() {
		super();
		// TODO Auto-generated constructor stub
	}

	public AdminException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
		// TODO Auto-generated constructor stub
	}

	public AdminException(String message, Throwable cause) {
		super(message, cause);
		// TODO Auto-generated constructor stub
	}

	public AdminException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}

	public AdminException(Throwable cause) {
		super(cause);
		// TODO Auto-generated constructor stub
	}

	
	public static AdminException buildUpdateException(){
		
		return new AdminException("update error");
	}
	public static AdminException buildDeleteException(){
		
		return new AdminException("delete error");
	}
	public static AdminException buildCreateException(){
		
		return new AdminException("create error");
	}
	public static AdminException buildException(String msg){
		
		return new AdminException(msg);
	}
}
