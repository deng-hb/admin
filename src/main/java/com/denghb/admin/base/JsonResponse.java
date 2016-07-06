package com.denghb.admin.base;

import java.io.Serializable;

public class JsonResponse implements Serializable {

	private static final long serialVersionUID = 7835309644291970332L;

	/** 1：成功 0：失败 */
	private int code = 1;

	/** 返回对象 */
	private Object object;

	/** 提示消息 */
	private String msg;

	/**
	 * 创建JSON对象
	 * 
	 * @return
	 */
	public static JsonResponse build() {
		return new JsonResponse();
	}

	/**
	 * 创建JSON对象
	 * 
	 * @return
	 */
	public static JsonResponse buildObject(Object object) {
		JsonResponse json = new JsonResponse();
		json.object = object;
		return json;
	}

	/**
	 * TODO code == 2 跳转页面
	 * 
	 * @return
	 */
	public static JsonResponse build2(String target) {
		JsonResponse json = new JsonResponse();
		json.code = 2;
		json.object = target;
		return json;
	}

	/**
	 * 创建JSON对象并带消息
	 * 
	 * @return
	 */
	public static JsonResponse buildObject(Object object, String msg) {
		JsonResponse json = new JsonResponse();
		json.code = 1;
		json.object = object;
		json.msg = msg;
		return json;
	}

	/**
	 * 创建返回JSON成功信息对象
	 * 
	 * @return
	 */
	public static JsonResponse buildSuccess(String msg) {
		JsonResponse josn = new JsonResponse();
		josn.setCode(1);
		josn.setMsg(msg);
		return josn;
	}

	public static JsonResponse buildSuccess() {
		return buildSuccess("操作成功");
	}

	/**
	 * 创建返回JSON错信息对象
	 * 
	 * @return
	 */
	public static JsonResponse buildFailure(String msg) {
		JsonResponse json = new JsonResponse();
		json.code = 0;
		json.msg = msg;
		return json;
	}

	public static JsonResponse buildFailure() {
		return buildFailure("操作失败");
	}

	private JsonResponse() {
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public Object getObject() {
		return object;
	}

	public void setObject(Object object) {
		this.object = object;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

}
