/*
 * Copyright (C) 2013-2033 Fable Limited.
 */
package com.fable.dsp.common.dto.dataswitch;

import java.io.Serializable;
import java.util.List;

/**
 * 
 * @author xieruidong 2014年3月18日 下午1:07:54
 */
public class ColumnFilterInfo implements Serializable {
	private static final long serialVersionUID = -7082414026399267644L;
	private Long sourceId;
	private Long targetId;
	private String sourceTableName;
	private String targetTableName;
	private String sourceColumnName;
	private String operator;
	private String columnValue;
	public Long getSourceId() {
		return sourceId;
	}
	public void setSourceId(Long sourceId) {
		this.sourceId = sourceId;
	}
	public Long getTargetId() {
		return targetId;
	}
	public void setTargetId(Long targetId) {
		this.targetId = targetId;
	}
	public String getSourceTableName() {
		return sourceTableName;
	}
	public void setSourceTableName(String sourceTableName) {
		this.sourceTableName = sourceTableName;
	}
	public String getTargetTableName() {
		return targetTableName;
	}
	public void setTargetTableName(String targetTableName) {
		this.targetTableName = targetTableName;
	}
	
	public String getSourceColumnName() {
		return sourceColumnName;
	}
	public void setSourceColumnName(String sourceColumnName) {
		this.sourceColumnName = sourceColumnName;
	}
	public String getOperator() {
		return operator;
	}
	public void setOperator(String operator) {
		this.operator = operator;
	}
	public String getColumnValue() {
		return columnValue;
	}
	public void setColumnValue(String columnValue) {
		this.columnValue = columnValue;
	}
}
