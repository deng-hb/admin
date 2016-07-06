package com.denghb.admin.base;

import java.io.Serializable;
import java.util.List;

/**
 * 
 * @author denghb
 *
 * @param <T>
 */
public class DataTablesResult<T> implements Serializable {

	private static final long serialVersionUID = 1L;

	private long draw;
	private long recordsTotal;
	private long recordsFiltered;
	
	private List<T> data;

	public long getDraw() {
		return draw;
	}

	public void setDraw(long draw) {
		this.draw = draw;
	}

	public long getRecordsTotal() {
		return recordsTotal;
	}

	public void setRecordsTotal(long recordsTotal) {
		this.recordsTotal = recordsTotal;
	}

	public long getRecordsFiltered() {
		return recordsFiltered;
	}

	public void setRecordsFiltered(long recordsFiltered) {
		this.recordsFiltered = recordsFiltered;
	}

	public List<T> getData() {
		return data;
	}

	public void setData(List<T> data) {
		this.data = data;
	}

}
