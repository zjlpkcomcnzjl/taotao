package com.taotao.common;

import java.io.Serializable;
import java.util.List;

public class EasyUIResult implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Long total;
	private List<?> rows;
	public Long getTotal() {
		return total;
	}
	public void setTotal(Long total) {
		this.total = total;
	}
	public List<?> getRows() {
		return rows;
	}
	public void setRows(List<?> rows) {
		this.rows = rows;
	}	

}
