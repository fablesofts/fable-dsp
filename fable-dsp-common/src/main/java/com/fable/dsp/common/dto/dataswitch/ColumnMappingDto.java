package com.fable.dsp.common.dto.dataswitch;

import java.io.Serializable;

public class ColumnMappingDto implements Serializable{

	private static final long serialVersionUID = 448900529421943015L;
	private String sourceColumnName;
	private int sourceColumnIndex;
	private String sourceColumnType;
	private String targetColumnName;
	private int targetColumnIndex;
	private String targetColumnType;
	private Long targetId;
	private Long sourceId;
	private String sourceTableName;
	private String targetTableName;
	
	public String getSourceColumnName() {
		return sourceColumnName;
	}
	public void setSourceColumnName(String sourceColumnName) {
		this.sourceColumnName = sourceColumnName;
	}
	public int getSourceColumnIndex() {
		return sourceColumnIndex;
	}
	public void setSourceColumnIndex(int sourceColumnIndex) {
		this.sourceColumnIndex = sourceColumnIndex;
	}
	
	public String getTargetColumnName() {
		return targetColumnName;
	}
	public void setTargetColumnName(String targetColumnName) {
		this.targetColumnName = targetColumnName;
	}
	public int getTargetColumnIndex() {
		return targetColumnIndex;
	}
	public void setTargetColumnIndex(int targetColumnIndex) {
		this.targetColumnIndex = targetColumnIndex;
	}
	public String getSourceColumnType() {
		return sourceColumnType;
	}
	public void setSourceColumnType(String sourceColumnType) {
		this.sourceColumnType = sourceColumnType;
	}
	public String getTargetColumnType() {
		return targetColumnType;
	}
	public void setTargetColumnType(String targetColumnType) {
		this.targetColumnType = targetColumnType;
	}
	public Long getTargetId() {
		return targetId;
	}
	public void setTargetId(Long targetId) {
		this.targetId = targetId;
	}
	public Long getSourceId() {
		return sourceId;
	}
	public void setSourceId(Long sourceId) {
		this.sourceId = sourceId;
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
	
}
