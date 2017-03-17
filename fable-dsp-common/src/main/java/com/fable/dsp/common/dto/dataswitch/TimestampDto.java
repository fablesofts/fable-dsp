package com.fable.dsp.common.dto.dataswitch;


public class TimestampDto {
    
    private String tableName;
    
    private String columnNames;
    
    private String timestamp;
    
    private Long sourceid;

    
    public String getTableName() {
        return tableName;
    }

    
    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    
    public String getColumnNames() {
        return columnNames;
    }

    
    public void setColumnNames(String columnNames) {
        this.columnNames = columnNames;
    }

    
    public String getTimestamp() {
        return timestamp;
    }

    
    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }


    public Long getSourceid() {
        return sourceid;
    }

    
    public void setSourceid(Long sourceid) {
        this.sourceid = sourceid;
    }
    
    
    
    
}
