package com.denghb.admin.dao;

import java.util.List;

import com.denghb.dbhelper.DbHelper;

public class DbDao extends DbHelper {
	
	public <T> PagingResult<T> list(StringBuffer sql, Class<T> clazz, Paging paging) {
		PagingResult<T> result = new PagingResult<T>(paging);

		Object[] objects = paging.getParams().toArray();
		// 不分页 start
		long rows = paging.getRows();
		if (0 != rows) {
			// 先查总数
			String totalSql = "select count(*) as size ";

			String tempSql = sql.toString().toLowerCase();
			int fromIndex = tempSql.indexOf("from");
			if (0 < fromIndex) {
				totalSql += sql.substring(fromIndex, sql.length());
			}

			long total = queryForObject(totalSql, Long.class, objects);

			paging.setTotal(total);
			if (0 == total) {
				return result;
			}
		}
		// 不分页 end

		// start
		long page = paging.getPage() - 1;

		// 排序
		if (paging.isSort()) {
			// 判断是否有排序字段
			String[] sorts = paging.getSorts();
			if (null != sorts && 0 < sorts.length) {
				int sortIndex = paging.getSortIndex();

				// 大于排序的长度默认最后一个
				if (sortIndex >= sorts.length) {
					sortIndex = sorts.length - 1;
				}
				// 排序字段
				sql.append(" order by ");

				sql.append('`');
				sql.append(sorts[paging.getSortIndex()]);
				sql.append('`');

				// 排序方式
				if (paging.isDesc()) {
					sql.append(" desc");
				} else {
					sql.append(" asc");
				}
			}
		}

		if (0 != rows) {
			// TODO 分页mysql
			sql.append(" limit ");
			sql.append(page * rows);
			sql.append(",");
			sql.append(rows);
		}

		List<T> list = list(sql.toString(), clazz, objects);
		result.setList(list);

		return result;
	}
}
