package com.fable.dsp.core.datagrid;

/**
 * 
 * DATAGRID中默认分而查询参数
 * 
 * @author 汪朝 20130929
 */
public class DataGridModel implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3235849298794067262L;
	private int page; // 当前页,名字必须为page
	private int rows; // 每页大小,名字必须为rows
	private String sort; // 排序字段
	private String order; // 排序规则

	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}

	public int getRows() {
		return rows;
	}

	public void setRows(int rows) {
		this.rows = rows;
	}

	public String getSort() {
		return sort;
	}

	public void setSort(String sort) {
		this.sort = sort;
	}

	public String getOrder() {
		return order;
	}

	public void setOrder(String order) {
		this.order = order;
	}

}
