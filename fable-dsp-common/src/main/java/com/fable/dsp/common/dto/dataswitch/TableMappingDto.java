package com.fable.dsp.common.dto.dataswitch;

import java.io.Serializable;
public class TableMappingDto implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 8431648487665505467L;
	private String source;//源表名
	private int sourceid;	//源表所在的数据源编号
	private String target;	//目标表名
	private int targetid;//目标表所在的数据源编号
	private int page; // 当前页,名字必须为page
	private int rows; // 每页大小,名字必须为rows
	private String sort; // 排序字段
	private String order; // 排序规则
	public String getSource() {
		return source;
	}
	public void setSource(String source) {
		this.source = source;
	}
	public int getSourceid() {
		return sourceid;
	}
	public void setSourceid(int sourceid) {
		this.sourceid = sourceid;
	}
	public String getTarget() {
		return target;
	}
	public void setTarget(String target) {
		this.target = target;
	}
	public int getTargetid() {
		return targetid;
	}
	public void setTargetid(int targetid) {
		this.targetid = targetid;
	}
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
