package com.denghb.admin.utils;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.denghb.admin.base.ParameterException;
import com.denghb.dbhelper.domain.Paging;

/**
 * 参数处理工具
 * 
 * @author denghb
 *
 */
public class ParameterUtils {

	private final static Logger log = LoggerFactory.getLogger(ParameterUtils.class);

	/**
	 * 初始化table参数
	 * 
	 * @param request
	 * @param paging
	 */
	public static void initDataTablesParams(HttpServletRequest request, Paging paging) {

		try {

			String search = request.getParameter("search");
			String start = request.getParameter("start");
			String length = request.getParameter("length");
			String draw = request.getParameter("draw");

			String column = request.getParameter("column");
			String sort = request.getParameter("sort");
			// FIXME
			long startIndex = Long.parseLong(start);
			long len = Long.parseLong(length);
			if (startIndex >= len) {// 多于1页
				if (startIndex % len == 0) {
					paging.setPage(startIndex / len + 1);
				} else {
					paging.setPage(startIndex / len);
				}
			}
			paging.setRows(len);

		} catch (Exception e) {
			log.error(e.getMessage(), e);
		}
	}

	// 获取int
	public static int getInt(HttpServletRequest request, String name, String alias) throws ParameterException {
		String p = request.getParameter(name);
		// 纯数字
		if (StringUtils.isNotBlank(p) && StringUtils.isNumeric(p)) {
			return Integer.parseInt(p);
		}
		log.error("parameter error [int:{} value:{}]", name, p);
		throw new ParameterException(null == alias ? "" : alias + "参数错误");
	}

	// 获取long
	public static long getLong(HttpServletRequest request, String name, String alias) throws ParameterException {
		String p = request.getParameter(name);
		// 纯数字
		if (StringUtils.isNotBlank(p) && StringUtils.isNumeric(p)) {
			return Long.parseLong(p);
		}
		log.error("parameter error [long:{} value:{}]", name, p);
		throw new ParameterException(null == alias ? "" : alias + "参数错误");
	}

	// 获取has text String
	public static String getNotBlankString(HttpServletRequest request, String name, String alias)
			throws ParameterException {
		String p = request.getParameter(name);
		if (StringUtils.isNotBlank(p)) {
			return p;
		}
		log.error("parameter error [not blank string:{} value:{}]", name, p);
		throw new ParameterException(null == alias ? "" : alias + "不能为空");

	}

	public static long[] getLongArray(HttpServletRequest request, String name, String alias) throws ParameterException {
		String p = request.getParameter(name);
		String[] idsStringArray = p.split(",");// TODO 默认都是英文“,”隔开

		try {
			long[] ids = new long[idsStringArray.length];
			for (int i = 0; i < ids.length; i++) {
				ids[i] = getLong(request, name, alias);
			}
			return ids;
		} catch (Exception e) {
			log.error("parameter error [long[]:{} value:{}]", name, p);
		}
		throw new ParameterException(null == alias ? "" : alias + "参数错误");

	}

	// 获取日期
	public static Date getNotNullDate(HttpServletRequest request, String name, String pattern, String alias)
			throws ParameterException {
		String p = request.getParameter(name);
		if (StringUtils.isNotBlank(p)) {
			Date date = DateUtils.getDate(p, pattern);
			if (null != date) {
				return date;
			}
		}
		log.error("parameter error [date:{} value:{}]", name, p);
		throw new ParameterException(null == alias ? "" : alias + "参数错误");
	}

}
