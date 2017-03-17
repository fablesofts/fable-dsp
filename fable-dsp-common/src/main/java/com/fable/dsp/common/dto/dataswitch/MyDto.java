package com.fable.dsp.common.dto.dataswitch;

import java.io.Serializable;

public class MyDto implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1321312L;
	private String sourceid;
	private TreeDataDto []treeDataDtos;
	public String getSourceid() {
		return sourceid;
	}
	public void setSourceid(String sourceid) {
		this.sourceid = sourceid;
	}
	public TreeDataDto[] getTreeDataDtos() {
		return treeDataDtos;
	}
	public void setTreeDataDtos(TreeDataDto[] treeDataDtos) {
		this.treeDataDtos = treeDataDtos;
	}
	public MyDto(String sourceid, TreeDataDto[] treeDataDtos) {
		super();
		this.sourceid = sourceid;
		this.treeDataDtos = treeDataDtos;
	}
	public MyDto() {
		super();
	}
	
}
