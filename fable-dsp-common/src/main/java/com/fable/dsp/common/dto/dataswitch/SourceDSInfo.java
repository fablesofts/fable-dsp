/*
 * Copyright (C) 2013-2033 Fable Limited.
 */
package com.fable.dsp.common.dto.dataswitch;

import java.io.Serializable;

/**
 * 
 * @author xieruidong 2014年3月18日 下午1:05:19
 */
public class SourceDSInfo implements Serializable {
	
	private static final long serialVersionUID = -2474091758809951482L;
	
	private Long id;
	private Long sourceId;
	private String sourceName;
	private String tableName;
	private String location;
	
    
	
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public Long getSourceId() {
        return sourceId;
    }
    public void setSourceId(Long sourceId) {
        this.sourceId = sourceId;
    }
    public String getTableName() {
		return tableName;
	}
	public void setTableName(String tableName) {
		this.tableName = tableName;
	}
    public String getLocation() {
        return location;
    }
    public void setLocation(String location) {
        this.location = location;
    }
    public String getSourceName() {
        return sourceName;
    }
    public void setSourceName(String sourceName) {
        this.sourceName = sourceName;
    }
	
}
