/*
 * Copyright (C) 2013-2033 Fable Limited.
 */
package com.fable.dsp.common.dto.dataswitch;

import java.io.Serializable;
import java.util.List;

/**
 * 
 * @author xieruidong 2014年3月12日 下午2:00:07
 */
public class FullTaskDto implements Serializable {
	
	private static final long serialVersionUID = 3416617528984760970L;
	private Long taskId;
	private String taskName;
	private String taskType;
	private String synchroType;
	private String taskDescription;
	private boolean deleteSourceData;
	private boolean rebuildTrigger;
	//是否关联行级日志
    private boolean rowLevelLog;
    //是否输出syslog
    private boolean sysLogPrint;
    //原文件处理方式
    private String sourceFile;
    //目标文件处理方式
    private String targetFile;
    //是否存在主从表关系
    private boolean association;
    private SourceDSInfo sourceDSInfo = new SourceDSInfo();
	private List<TargetDSInfo> targetDSInfo;
	
	private List<TimestampDto> timestamps ;
	private List<TableMappingInfo> tableMappingInfo;
	private List<ColumnMappingInfo> columnMappingInfo;
	private List<ColumnVirusFilterInfo> columnVirusFilterInfo;
	private List<ColumnFilterInfo> columnFilterInfo;
	private List<ColumnTransferInfo> columnTransferInfo;
	
	public String getTaskName() {
		return taskName;
	}

	public void setTaskName(String taskName) {
		this.taskName = taskName;
	}

	public String getTaskType() {
		return taskType;
	}

	public void setTaskType(String taskType) {
		this.taskType = taskType;
	}

	public String getSynchroType() {
		return synchroType;
	}

	public void setSynchroType(String synchroType) {
		this.synchroType = synchroType;
	}

	public String getTaskDescription() {
		return taskDescription;
	}

	public void setTaskDescription(String taskDescription) {
		this.taskDescription = taskDescription;
	}

	public boolean isDeleteSourceData() {
		return deleteSourceData;
	}

	public void setDeleteSourceData(boolean deleteSourceData) {
		this.deleteSourceData = deleteSourceData;
	}

	public boolean isRebuildTrigger() {
		return rebuildTrigger;
	}

	public void setRebuildTrigger(boolean rebuildTrigger) {
		this.rebuildTrigger = rebuildTrigger;
	}

	public SourceDSInfo getSourceDSInfo() {
		return sourceDSInfo;
	}

	public void setSourceDSInfo(SourceDSInfo sourceDSInfo) {
		this.sourceDSInfo = sourceDSInfo;
	}

	public List<TargetDSInfo> getTargetDSInfo() {
		return targetDSInfo;
	}

	public void setTargetDSInfo(List<TargetDSInfo> targetDSInfo) {
		this.targetDSInfo = targetDSInfo;
	}

    public boolean isRowLevelLog() {
        return rowLevelLog;
    }
    
    public void setRowLevelLog(boolean rowLevelLog) {
        this.rowLevelLog = rowLevelLog;
    }

    public boolean isAssociation() {
        return association;
    }

    public void setAssociation(boolean association) {
        this.association = association;
    }
    
    public Long getTaskId() {
        return taskId;
    }

    public void setTaskId(Long taskId) {
        this.taskId = taskId;
    }

    public List<TableMappingInfo> getTableMappingInfo() {
        return tableMappingInfo;
    }
    
    public void setTableMappingInfo(List<TableMappingInfo> tableMappingInfo) {
        this.tableMappingInfo = tableMappingInfo;
    }

    public List<ColumnMappingInfo> getColumnMappingInfo() {
        return columnMappingInfo;
    }
    
    public void setColumnMappingInfo(List<ColumnMappingInfo> columnMappingInfo) {
        this.columnMappingInfo = columnMappingInfo;
    }

    public List<ColumnVirusFilterInfo> getColumnVirusFilterInfo() {
        return columnVirusFilterInfo;
    }

    public void setColumnVirusFilterInfo(
        List<ColumnVirusFilterInfo> columnVirusFilterInfo) {
        this.columnVirusFilterInfo = columnVirusFilterInfo;
    }

    public List<ColumnFilterInfo> getColumnFilterInfo() {
        return columnFilterInfo;
    }
    
    public void setColumnFilterInfo(List<ColumnFilterInfo> columnFilterInfo) {
        this.columnFilterInfo = columnFilterInfo;
    }
    
    public List<ColumnTransferInfo> getColumnTransferInfo() {
        return columnTransferInfo;
    }

    public void
        setColumnTransferInfo(List<ColumnTransferInfo> columnTransferInfo) {
        this.columnTransferInfo = columnTransferInfo;
    }
    
    public List<TimestampDto> getTimestamps() {
        return timestamps;
    }

    public void setTimestamps(List<TimestampDto> timestamps) {
        this.timestamps = timestamps;
    }
    
    public boolean isSysLogPrint() {
        return sysLogPrint;
    }
    
    public void setSysLogPrint(boolean sysLogPrint) {
        this.sysLogPrint = sysLogPrint;
    }
    
    public String getSourceFile() {
        return sourceFile;
    }
    
    public void setSourceFile(String sourceFile) {
        this.sourceFile = sourceFile;
    }
    
    public String getTargetFile() {
        return targetFile;
    }
    
    public void setTargetFile(String targetFile) {
        this.targetFile = targetFile;
    }
}
