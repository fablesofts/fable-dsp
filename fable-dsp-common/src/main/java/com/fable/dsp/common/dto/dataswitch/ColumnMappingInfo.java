/*
 * Copyright (C) 2013-2033 Fable Limited.
 */
package com.fable.dsp.common.dto.dataswitch;

import java.io.Serializable;

/**
 * 
 * @author xieruidong 2014年3月18日 下午1:08:35
 */
public class ColumnMappingInfo implements Serializable {
	private static final long serialVersionUID = -3297177887995066872L;
	private Long sourceId;
	private Long targetId;
	private String sourceTableName;
	private String targetTableName;
	private String sourceColumnName;
	private String targetColumnName;
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
	public String getTargetColumnName() {
		return targetColumnName;
	}
	public void setTargetColumnName(String targetColumnName) {
		this.targetColumnName = targetColumnName;
	}
}
