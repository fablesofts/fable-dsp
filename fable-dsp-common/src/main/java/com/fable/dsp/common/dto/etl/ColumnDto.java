package com.fable.dsp.common.dto.etl;

public class ColumnDto {
	private String name;
	private int index;
	private String type;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getIndex() {
		return index;
	}
	public void setIndex(int index) {
		this.index = index;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public ColumnDto(String name, int index, String type) {
		super();
		this.name = name;
		this.index = index;
		this.type = type;
	}
	public ColumnDto() {
		super();
	}
	
}
