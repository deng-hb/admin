package com.denghb.admin.utils;

import com.denghb.admin.base.DataTablesResult;
import com.denghb.dbhelper.domain.PagingResult;

public class DataUtils {

	public  static <T> DataTablesResult<T> pagingResult2DataTablesResult(PagingResult<T> pagingResult) {
		DataTablesResult<T> object = new DataTablesResult<T>();
		object.setData(pagingResult.getList());
		object.setRecordsTotal(pagingResult.getPaging().getTotal());
		object.setRecordsFiltered(pagingResult.getPaging().getTotal());
		return object;
	}
}
