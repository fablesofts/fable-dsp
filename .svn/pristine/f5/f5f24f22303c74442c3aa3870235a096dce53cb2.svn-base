package com.fable.dsp.dmo.dataswitch;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable 
public class TimestampPK implements Serializable {

    private static final long serialVersionUID = 7268618540252768269L;
    
    @Column(name = "TASK_ID")
    private Long taskId;
    @Column(name = "DATA_SOURCE_ID")
    private Long dataSourceId;
    @Column(name = "TABLE_NAME")
    private String tableName;
    
    public Long getTaskId() {
        return taskId;
    }
    public void setTaskId(Long taskId) {
        this.taskId = taskId;
    }
    public Long getDataSourceId() {
        return dataSourceId;
    }
    public void setDataSourceId(Long dataSourceId) {
        this.dataSourceId = dataSourceId;
    }
    public String getTableName() {
        return tableName;
    }
    public void setTableName(String tableName) {
        this.tableName = tableName;
    }
}
